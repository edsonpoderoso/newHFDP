/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd.stage;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.io.IOException;
import static java.lang.Math.max;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.JPopupMenu;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;
import net.emoreira.hfd.HFDStageElementFlavor;
import net.emoreira.hfd.model.Binding;
import net.emoreira.hfd.model.Component;
import net.emoreira.hfd.model.HFDStageElement;
import net.emoreira.hfd.model.Interface;
import net.emoreira.hfd.model.Subarch;
import org.netbeans.api.visual.action.AcceptProvider;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.ConnectProvider;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.action.MoveProvider;
import org.netbeans.api.visual.action.PopupMenuProvider;
import org.netbeans.api.visual.action.RectangularSelectDecorator;
import org.netbeans.api.visual.action.ResizeProvider;
import org.netbeans.api.visual.action.SelectProvider;
import org.netbeans.api.visual.anchor.Anchor;
import org.netbeans.api.visual.anchor.AnchorShapeFactory;
import org.netbeans.api.visual.graph.GraphPinScene;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.router.RouterFactory;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.core.spi.multiview.MultiViewElementCallback;
import org.openide.awt.UndoRedo;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ProxyLookup;
import org.openide.windows.IOProvider;
import org.openide.windows.OutputWriter;

/**
 *
 * @author edson
 */
public class Stage extends GraphPinScene<HFDStageElement, Binding, Interface> implements UndoRedo.Provider {
    
    //Prefix
    private final String COMPONENT_ID_PREFIX = "comp ";
    private final String SUBARCH_ID_PREFIX = "suba ";
    private final String INTERFACE_ID_PREFIX = "inter ";
    private final String TOKENIZER_DELIMITER = "_ ";

    private final int ARROW_DEGREES = 40;
    private final int ARROW_SIZE = 12;
    private final int PROVIDER_PIN_SIZE = 0;
    //Scene Layers 
    private final LayerWidget dropLayer = new LayerWidget(this);
    private final LayerWidget archLayer = new LayerWidget(this);
    private final LayerWidget componentsLayer = new LayerWidget(this);
    private final LayerWidget edgesLayer = new LayerWidget(this);
    private final LayerWidget sateliteLayer = new LayerWidget(this);
    private transient MultiViewElementCallback callback;

    //Lookup setup
    private final InstanceContent ic = new InstanceContent();
    private final Lookup dynamicLookup = new AbstractLookup(ic);

    //UndoRedo Setup
    private final transient UndoRedo.Manager undoRedo = new UndoRedo.Manager();
    private long componentIdTracker = 1000;
    private long subArchIdTracker = 1000;
    private long interfaceIdTracker = 1000;

    public void setCallback(MultiViewElementCallback callback) {
        this.callback = callback;
    }

    //Widget Action Providers
    private final ConnectProvider myConnectProvider = new ConnectProvider() {
        @Override
        public boolean isSourceWidget(Widget widget) {
            Collection<Binding> edges = Stage.this.getEdges();
            for (Binding edge : edges) {
                if (Stage.this.findWidget(Stage.this.getEdgeSource(edge)) == widget) {
                    return false;
                }
            }
            return widget instanceof RequiredInterfacePinWidget;
        }

        @Override
        public ConnectorState isTargetWidget(Widget sourceWidget, Widget targetWidget) {
            if (targetWidget instanceof ProvidedInterfacePinWidget) {
                Interface sourcePin = (Interface) Stage.this.findObject(sourceWidget);
                Interface targetPin = (Interface) Stage.this.findObject(targetWidget);
                if (sourcePin.matchesForConnection(targetPin)) {
                    return ConnectorState.ACCEPT;
                }
            }
            return ConnectorState.REJECT;
        }

        @Override
        public boolean hasCustomTargetWidgetResolver(Scene scene) {
            return false;
        }

        @Override
        public Widget resolveTargetWidget(Scene scene, Point point) {
            return null;
        }

        @Override
        public void createConnection(Widget sourceWidget, Widget targetWidget) {
            Binding edge = new Binding();
            Interface sourceInterface = (Interface) Stage.this.findObject(sourceWidget);
            Interface targetInterface = (Interface) Stage.this.findObject(targetWidget);
            edge.setProtocol("undetermined");
            edge.setClientInterface(sourceInterface);
            edge.setServerInterface(targetInterface);
            undoRedo.addEdit(new AddEdgeEdit(edge));
        }
    };

    private final MoveProvider myMultiMoveProvider = new MoveProvider() {
        private final HashMap<Widget, Point> originals = new HashMap<>();
        private Point original;

        //undoRedo Management
        @Override
        public void movementStarted(Widget widget) {
            Object object = findObject(widget);
            if (isNode(object)) {
                for (Object o : getSelectedObjects()) {
                    if (isNode(o)) {
                        Widget w = findWidget(o);
                        if (w != null) {
                            originals.put(w, w.getPreferredLocation());
                        }
                    }
                }
            } else {
                originals.put(widget, widget.getPreferredLocation());
            }
        }

        @Override
        public void movementFinished(Widget widget) {
            //undoRedo Management
            HashMap<Object, Point> done = new HashMap<>();
            HashMap<Object, Point> undone = new HashMap<>();
            for (Map.Entry<Widget, Point> entry : originals.entrySet()) {
                //sync with model
                Stage.this.syncElementPosition(entry.getKey());
                Object object = findObject(entry.getKey());
                undone.put(object, entry.getValue());
                done.put(object, entry.getKey().getLocation());
            }
            undoRedo.addEdit(new MoveWidgetEdit(undone, done));

            originals.clear();
            original = null;
        }

        @Override
        public Point getOriginalLocation(Widget widget) {
            original = widget.getPreferredLocation();
            return original;
        }

        @Override
        public void setNewLocation(Widget widget, Point location) {
            int dx = location.x - original.x;
            int dy = location.y - original.y;
            for (Map.Entry<Widget, Point> entry : originals.entrySet()) {
                Point point = entry.getValue();
                entry.getKey().setPreferredLocation(new Point(point.x + dx, point.y + dy));
            }

        }

    };

    private final SelectProvider mySelectProvider = new SelectProvider() {
        List<Node> nodes = new ArrayList<Node>();

        @Override
        public boolean isAimingAllowed(Widget widget, Point point, boolean invertSelection) {
            return false;
        }

        @Override
        public boolean isSelectionAllowed(Widget widget, Point point, boolean invertSelection) {
            return findObject(widget) != null || widget == Stage.this;
        }

        @Override
        public void select(Widget widget, Point point, boolean invertSelection) {
            Set<?> oldSelection = Stage.this.getSelectedObjects();
            Set<?> newSelection;

            if (widget == Stage.this) {
                Stage.this.setSelectedObjects(Collections.EMPTY_SET);
            } else {
                Object object = findObject(widget);
                setFocusedObject(object);
                if (object != null) {
                    if (!invertSelection && getSelectedObjects().contains(object)) {
                        return;
                    }
                    userSelectionSuggested(Collections.singleton(object), invertSelection);
                } else {
                    userSelectionSuggested(Collections.emptySet(), invertSelection);
                }
            }
            selectionChanged();
        }

        private void selectionChanged() {
            Set<?> selected = Stage.this.getSelectedObjects();
            if (selected.isEmpty()) {
                for (Node n : nodes) {
                    ic.remove(n);
                }
                nodes.clear();//NODE TEST
            } else {
                for (Object o : selected) {
                    if (o instanceof Subarch) {
                        Subarch co = (Subarch) o;
                        nodes.add(co.getNode());
                        ic.add(co.getNode());
                    } else if (o instanceof Binding) {
                        Binding co = (Binding) o;
                        nodes.add(co.getNode());
                        ic.add(co.getNode());
                    }
                }
            }
        }
    };

    private final AcceptProvider myAcceptProvider = new AcceptProvider() {

        @Override
        public ConnectorState isAcceptable(Widget widget, Point point, Transferable t) {
            if (widget == Stage.this && t.isDataFlavorSupported(HFDStageElementFlavor.HFDELEMENT_FLAVOR)) {
                return ConnectorState.ACCEPT;
            } else {
                return ConnectorState.REJECT;
            }
        }

        @Override
        public void accept(Widget widget, Point point, Transferable t
        ) {
            try {
                HFDStageElement hfdElement = (HFDStageElement) t.getTransferData(HFDStageElementFlavor.HFDELEMENT_FLAVOR);
                hfdElement.setPosition((Point)point.clone());
                Set<HFDStageElement> set = new HashSet<>();
                if (hfdElement.isComponent()) {
                    Component component = hfdElement.asComponent();
                    component.setId(COMPONENT_ID_PREFIX + componentIdTracker++);
                    List<Interface> providedInterface = component.getProvidedInterface();
                    for(Interface p:providedInterface){
                        p.setId(INTERFACE_ID_PREFIX + interfaceIdTracker++);
                    }
                    List<Interface> requiredInterface = component.getRequiredInterface();
                    for(Interface r:requiredInterface){
                        r.setId(INTERFACE_ID_PREFIX + interfaceIdTracker++);
                    }
                    
                } else {
                    hfdElement.asSubarch().setId(SUBARCH_ID_PREFIX + subArchIdTracker++);
                }
                set.add(hfdElement);
                undoRedo.addEdit(new AddElementsEdit(set));
                callback.getTopComponent().requestActive();
            } catch (UnsupportedFlavorException | IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    };

    private final ResizeProvider myResizeProvider = new ResizeProvider() {
        Rectangle originalBounds;
        Point originalPosition;

        @Override
        public void resizingStarted(Widget widget) {
            assert widget != null;
            originalBounds = (Rectangle) widget.getBounds().clone();
            originalPosition = (Point) widget.getLocation().clone();
        }

        @Override
        public void resizingFinished(Widget widget) {
            Rectangle newBounds = widget.getBounds();
            assert newBounds  != null;
            Point nbPos = newBounds.getLocation();
            Point correctedPosition = new Point((int) (originalPosition.getX() + nbPos.getX()), (int) (originalPosition.getY() + nbPos.getY()));
            widget.setPreferredLocation(correctedPosition);
            Rectangle correctedBounds = new Rectangle(0, 0, (int) newBounds.getWidth(), (int) newBounds.getHeight());
            widget.setPreferredBounds(correctedBounds);
            Subarch sa = (Subarch) Stage.this.findObject(widget);
            sa.setDimension(correctedBounds.getSize());
            sa.setPosition((Point)correctedPosition.clone());
            undoRedo.addEdit(new ResizeEdit((HFDStageElement) Stage.this.findObject(widget), originalPosition, originalBounds, correctedPosition, correctedBounds));
        }
    };

    private final PopupMenuProvider myPopupMenuProvider = new PopupMenuProvider() {

        @Override
        public JPopupMenu getPopupMenu(Widget widget, Point point) {
            JPopupMenu menu = new JPopupMenu();
            menu.add(new AbstractAction("Delete selected") {

                @Override
                public boolean isEnabled() {
                    return super.isEnabled() && !Stage.this.getSelectedObjects().isEmpty();
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    undoRedo.addEdit(new DeleteEdit());
                }
            });
            return menu;
        }
    };

    private final RectangularSelectDecorator myRectangularSelectDecorator = new RectangularSelectDecorator() {

        @Override
        public Widget createSelectionWidget() {
            return new Widget(Stage.this) {

                @Override
                protected Rectangle calculateClientArea() {
                    return new Rectangle(1, 1);
                }

                @Override
                protected void paintWidget() {
                    Graphics2D gc = getGraphics();
                    gc.setColor(Color.BLUE);
                    Composite x = gc.getComposite();
                    gc.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.1f));
                    gc.fillRect((int) getPreferredBounds().getX(), (int) getPreferredBounds().getY(), (int) getPreferredBounds().getWidth(), (int) getPreferredBounds().getHeight());
                    gc.setComposite(x);
                    gc.drawRect((int) getPreferredBounds().getX(), (int) getPreferredBounds().getY(), (int) getPreferredBounds().getWidth(), (int) getPreferredBounds().getHeight());
                    gc.dispose();
                }
            };
        }
    };

    //Label handler for connections (NOT THE BEST WAY TO DO IT BUT IT WORKS)
    private final Map<Binding, LabelWidget> ConToLabel = new HashMap<Binding, LabelWidget>();
    private final ChangeListener connectionChangeListener = new ChangeListener() {

        @Override
        public void stateChanged(ChangeEvent e) {
            assert e.getSource() instanceof Binding;
            Binding con = (Binding) e.getSource();
            LabelWidget lw = ConToLabel.get(con);
            assert lw != null;
            lw.setLabel(con.getProtocol());

        }
    };
    //End of Label handler

    public Stage() {
        super();
        //Configures the layers on the scene
        this.addChild(archLayer);
        this.addChild(componentsLayer);
        this.addChild(edgesLayer);
        this.addChild(dropLayer);
        this.addChild(sateliteLayer);

        //Configures the zoom, pan, selection and Accept actions on the scene
        this.getActions().addAction(ActionFactory.createMouseCenteredZoomAction(1.1));
        this.getActions().addAction(ActionFactory.createPanAction());
        this.getActions().addAction(ActionFactory.createSelectAction(mySelectProvider));
        this.getActions().addAction(ActionFactory.createAcceptAction(myAcceptProvider));
        this.getActions().addAction(ActionFactory.createPopupMenuAction(myPopupMenuProvider));
        this.getActions().addAction(ActionFactory.createRectangularSelectAction(myRectangularSelectDecorator,
                sateliteLayer,
                ActionFactory.createObjectSceneRectangularSelectProvider(this)));
    }

    //This method adds a new node while creates its pins
    public void myAddNode(HFDStageElement element) {
        OutputWriter out = IOProvider.getDefault().getStdOut();
        assert element != null;
        if (element.isComponent()) {
            Component component = element.asComponent();
            out.print(component.toString());
            Set<Interface> Pins = new HashSet<Interface>();
            
            //creating pins
            List<Interface> providedInterfaces = component.getProvidedInterface();
            for (Interface e : providedInterfaces) {
                Pins.add(e);
            }
            List<Interface> requiredInterfaces = component.getRequiredInterface();
            for (Interface e : requiredInterfaces) {
                Pins.add(e);
            }
            
            //adding node and pins
            Widget widget = addNode(element);
            assert widget != null;
            for (Interface p : Pins) {
                addPin(element, p);
            }
        } else {
            addNode(element);
        }
    }

    //this method  adds a new edge while creating its label
    public void myAddEdge(Binding e) {
        assert e != null: "The parameter of myAddEdge cannot be null";
        ConnectionWidget w = (ConnectionWidget) this.addEdge(e);
        Interface sourcePin = (Interface)e.getClientInterface();
        Interface targetPin = (Interface)e.getServerInterface();
        this.setEdgeSource(e, sourcePin);
        this.setEdgeTarget(e, targetPin);

        //Label Making
        LabelWidget label1 = new LabelWidget(this, e.getProtocol() == null? "": e.getProtocol());
        label1.setOpaque(false);
        w.addChild(label1);
        w.setConstraint(label1, LayoutFactory.ConnectionWidgetLayoutAlignment.TOP_CENTER, 0.5f);
        ConToLabel.put(e, label1);
        e.registerChangeListener(connectionChangeListener);

    }

    //this helper method syncs a node object position with its widget position
    private void syncElementPosition(Widget widget) {
        assert !(widget instanceof ConnectionWidget);
        HFDStageElement element = (HFDStageElement) this.findObject(widget);
        element.setPosition(widget.getPreferredLocation());
    }

    //this method syncs a widget position with its node object position
    private void syncWidgetPosition(HFDStageElement element) {
        Widget widget = this.findWidget(element);
        assert widget != null;
        widget.setPreferredLocation(element.getPosition().isPresent()? element.getPosition().get(): new Point());
    }

    @Override
    protected Widget attachNodeWidget(HFDStageElement n) {
        assert n != null;
        Widget widget;
        Point pos = n.getPosition().isPresent()? n.getPosition().get(): new Point();
        if (n.isComponent()) {
            Component component = n.asComponent();
            widget = new StageComponentWidget(this, component);
            componentsLayer.addChild(widget);
        } else {
            widget = new ModelSubarchWidget(this, n.asSubarch());
            archLayer.addChild(widget);
            widget.getActions().addAction(ActionFactory.createResizeAction(ActionFactory.createFreeResizeStategy(),
                    ActionFactory.createDefaultResizeControlPointResolver(),
                    myResizeProvider
            ));
        }
        
        widget.getActions().addAction(ActionFactory.createSelectAction(mySelectProvider));
        widget.getActions().addAction(ActionFactory.createMoveAction(null, myMultiMoveProvider));
        widget.setPreferredLocation(pos);

        this.validate();
        return widget;
    }

    @Override
    protected Widget attachEdgeWidget(Binding e) {
        ConnectionWidget widget = new ConnectionWidget(this);
        widget.setRouter(RouterFactory.createOrthogonalSearchRouter(this.componentsLayer));
        widget.setForeground(Color.BLACK);
        widget.setTargetAnchorShape(AnchorShapeFactory.createArrowAnchorShape(ARROW_DEGREES, ARROW_SIZE));
        widget.setStroke(new BasicStroke(1.0f));
        widget.getActions().addAction(ActionFactory.createSelectAction(mySelectProvider));
        edgesLayer.addChild(widget);
        return widget;
    }

    //this helper method is used for finding sources and target objects for edges
//    private Component getComponentById(long id) {
//        Set<?> elements = this.getObjects();
//        for (Object o : elements) {
//            if (o instanceof Component
//                    && ((Component) o).getId() == id) {
//                return (Component) o;
//            }
//        }
//        throw new IllegalArgumentException("The component with the given id is not present on the scene");
//    }

    //this helper method find a model Pin by its parent Component and its interface
//    private Interface getNodePinByInterface(Component n, String inter, Interface.InterfaceType type) {
//        Collection<Interface> pins = this.getNodePins(n);
//        for (Interface p : pins) {
//            if (p.getType().equals(type) && p.getInter().equals(inter)) {
//                return p;
//            }
//        }
//        throw new IllegalArgumentException("The given arguments do not match a single Pin on the scene");
//    }

    @Override
    protected Widget attachPinWidget(HFDStageElement n, Interface p) {
        Component component = n.asComponent();
        StageComponentWidget widget = (StageComponentWidget) this.findWidget(component);
        Point pos;
        Widget pin;
        if (p.isProvidedInterface()) {
            pos = widget.getProvidedInterfacePinPosition(p);
            pin = new ProvidedInterfacePinWidget(this);
        } else {
            pos = widget.getRequiredInterfacePinPosition(p);
            pin = new RequiredInterfacePinWidget(this);
        }
        widget.addChild(pin);
        pin.setPreferredLocation(pos);
        pin.getActions().addAction(ActionFactory.createConnectAction(dropLayer, myConnectProvider));
        return pin;

    }

    @Override
    protected void attachEdgeSourceAnchor(Binding edge, Interface oldSourcePin, Interface sourcePin
    ) {
        Widget sourcePinWidget = findWidget(sourcePin);
        Anchor sourceAnchor = new EditorAnchor(sourcePinWidget, 0); //AnchorFactory.createCircularAnchor(sourcePinWidget, 0);
        ConnectionWidget edgeWidget = (ConnectionWidget) findWidget(edge);
        edgeWidget.setSourceAnchor(sourceAnchor);
    }

    @Override
    protected void attachEdgeTargetAnchor(Binding edge, Interface oldTargetPin, Interface targetPin
    ) {
        Widget targetPinWidget = findWidget(targetPin);
        Anchor targetAnchor = new EditorAnchor(targetPinWidget, PROVIDER_PIN_SIZE);//AnchorFactory.createCircularAnchor(targetPinWidget, PROVIDER_PIN_SIZE);//Utilizar um valor calculado posteriormente
        ConnectionWidget edgeWidget = (ConnectionWidget) findWidget(edge);
        edgeWidget.setTargetAnchor(targetAnchor);
    }

    @Override
    public Lookup getLookup() {
        return new ProxyLookup(dynamicLookup);
    }

    @Override
    public UndoRedo getUndoRedo() {
        return undoRedo;

    }

    public void ready() {
        Collection<HFDStageElement> nodes = this.getNodes();
        long maxC = 0;
        long maxS = 0;
        long maxI = 0;
        for(HFDStageElement node:nodes){
            if(node.isComponent()){
                Component comp = node.asComponent();
                String c_id = comp.getId();
                int compIdNumber = Integer.parseInt(c_id.replaceAll("[^0-9]", ""));
                maxC = max(compIdNumber, maxC);
                List<Interface> provInterfaces = comp.getProvidedInterface();
                for(Interface in:provInterfaces){
                    String in_id = in.getId();
                    int interIdNumber = Integer.parseInt(in_id.replaceAll("[^0-9]", ""));
                    maxI = max(interIdNumber, maxI);
                }
                List<Interface> reqInterfaces = comp.getRequiredInterface();
                for(Interface in:reqInterfaces){
                    String in_id = in.getId();
                    int interIdNumber = Integer.parseInt(in_id.replaceAll("[^0-9]", ""));
                    maxI = max(interIdNumber, maxI);
                }
            }
            if(node.isSubarch()){
                String s_id = node.asSubarch().getId();
                int subaIdNumber = Integer.parseInt(s_id.replaceAll("[^0-9]", ""));
                maxS = max(subaIdNumber, maxS);
            }
        }
        subArchIdTracker = maxS+1;
        componentIdTracker = maxC+1;
        interfaceIdTracker = maxI+1;
        //undoRedo.discardAllEdits();
    }

    //Undoable Edits
    private final class ResizeEdit extends AbstractUndoableEdit {

        Point undonePosition;
        Rectangle undoneBounds;
        Point donePosition;
        Rectangle doneBounds;
        HFDStageElement element;
        Widget widget;

        public ResizeEdit(HFDStageElement element, Point originalPosition, Rectangle originalBounds, Point correctedPosition, Rectangle correctedBounds) {
            this.element = element;
            this.undonePosition = (Point) originalPosition.clone();
            this.undoneBounds = (Rectangle) originalBounds.clone();
            this.donePosition = (Point) correctedPosition.clone();
            this.doneBounds = (Rectangle) correctedBounds.clone();
        }

        @Override
        public String getPresentationName() {
            return "resize Subarch";
        }

        @Override
        public void undo() throws CannotUndoException {
            super.undo(); //To change body of generated methods, choose Tools | Templates.
            element.setDimension(undoneBounds.getSize());
            element.setPosition((Point)undonePosition.clone());
            widget = Stage.this.findWidget(element);
            widget.setPreferredBounds((Rectangle) undoneBounds.clone());
            widget.setPreferredLocation((Point) undonePosition.clone());
        }

        @Override
        public void redo() throws CannotRedoException {
            super.redo(); //To change body of generated methods, choose Tools | Templates.
            element.setDimension(doneBounds.getSize());
            element.setPosition((Point)donePosition.clone());
            widget = Stage.this.findWidget(element);
            widget.setPreferredBounds((Rectangle) doneBounds.clone());
            widget.setPreferredLocation((Point) donePosition.clone());
        }
    }

//    private final class SelectEdit extends AbstractUndoableEdit {
//
//        Set<?> oldSelection;
//        Set<?> newSelection;
//
//        public SelectEdit(Set<?> oldSelection, Set<?> newSelection) {
//            super();
//            this.oldSelection = oldSelection;
//            this.newSelection = newSelection;
//        }
//
//        @Override
//        public String getPresentationName() {
//            return "Selection change";
//        }
//
//        @Override
//        public void undo() throws CannotUndoException {
//            super.undo(); //To change body of generated methods, choose Tools | Templates.
//            Stage.this.setSelectedObjects(oldSelection);
//        }
//
//        @Override
//        public void redo() throws CannotRedoException {
//            super.redo(); //To change body of generated methods, choose Tools | Templates.
//            Stage.this.setSelectedObjects(newSelection);
//        }
//
//    }

    private final class AddEdgeEdit implements UndoableEdit {

        boolean alive = true;
        boolean done = false;
        Binding connection;

        public AddEdgeEdit(Binding connection) {
            assert (connection != null);
            this.connection = connection;
            redo();
        }

        @Override
        public void undo() throws CannotUndoException {
            if (canUndo()) {
                Stage.this.removeEdge(connection);
                done = false;
            } else {
                throw new CannotUndoException();
            }
        }

        @Override
        public boolean canUndo() {
            return done && alive;
        }

        @Override
        public void redo() throws CannotRedoException {
            if (canRedo()) {
                Stage.this.myAddEdge(connection);
                done = true;
            } else {
                throw new CannotRedoException();
            }
        }

        @Override
        public boolean canRedo() {
            return !done && alive;
        }

        @Override
        public void die() {
            alive = false;
        }

        @Override
        public boolean addEdit(UndoableEdit anEdit) {
            return false;
        }

        @Override
        public boolean replaceEdit(UndoableEdit anEdit) {
            return false;
        }

        @Override
        public boolean isSignificant() {
            return true;
        }

        @Override
        public String getPresentationName() {
            return "Create connection";
        }

        @Override
        public String getUndoPresentationName() {
            return getPresentationName();
        }

        @Override
        public String getRedoPresentationName() {
            return getPresentationName();
        }
    }

    private final class AddElementsEdit implements UndoableEdit {

        Set<HFDStageElement> elements;
        boolean alive = true;
        boolean done = false;

        public AddElementsEdit(Set<HFDStageElement> elements) {
            if (!elements.isEmpty()) {
                this.elements = elements;
                this.redo();
            } else {
                throw new IllegalArgumentException("An AddElementsEdit needs at least one element to add");
            }
        }

        @Override
        public void undo() throws CannotUndoException {
            if (canUndo()) {
                for (HFDStageElement element : elements) {
                    Stage.this.removeNode(element);
                }
                done = false;
            } else {
                throw new CannotUndoException();
            }
        }

        @Override
        public boolean canUndo() {
            return alive && done;
        }

        @Override
        public void redo() throws CannotRedoException {
            if (canRedo()) {
                for (HFDStageElement element : elements) {
                    Stage.this.myAddNode(element);
                }
                this.done = true;
            } else {
                throw new CannotRedoException();
            }

        }

        @Override
        public boolean canRedo() {
            return (!done) && alive;
        }

        @Override
        public void die() {
            alive = false;
        }

        @Override
        public boolean addEdit(UndoableEdit anEdit) {
            return false;
        }

        @Override
        public boolean replaceEdit(UndoableEdit anEdit) {
            return false;
        }

        @Override
        public boolean isSignificant() {
            return true;
        }

        @Override
        public String getPresentationName() {
            return "Add " + elements.size() + " element" + (elements.size() == 1 ? "" : "s");
        }

        @Override
        public String getUndoPresentationName() {
            return getPresentationName();
        }

        @Override
        public String getRedoPresentationName() {
            return getPresentationName();
        }

    }

    private final class MoveWidgetEdit extends AbstractUndoableEdit {

        Map<Object, Point> pre;
        Map<Object, Point> post;

        @Override
        public String getPresentationName() {
            return "Move element" + ((pre.size() == 1) ? "" : "s");
        }

        public MoveWidgetEdit(Map<Object, Point> pre, Map<Object, Point> post) {
            super();
            this.pre = pre;
            this.post = post;
        }

        @Override
        public void undo() throws CannotUndoException {
            super.undo();
            for (Map.Entry<Object, Point> e : pre.entrySet()) {
                Widget widget = Stage.this.findWidget(e.getKey());
                widget.setPreferredLocation(e.getValue());
                Stage.this.syncElementPosition(widget);
            }
        }

        @Override
        public void redo() throws CannotRedoException {
            super.redo(); 
            for (Map.Entry<Object, Point> e : post.entrySet()) {
                Widget widget = Stage.this.findWidget(e.getKey());
                widget.setPreferredLocation(e.getValue());
                Stage.this.syncElementPosition(widget);
            }
        }

    }

    private final class DeleteEdit extends AbstractUndoableEdit {

        Set<Object> set = new HashSet<>();
        Set<Binding> connections = new HashSet<>();
        Set<HFDStageElement> nodes = new HashSet<>();

        public DeleteEdit() {
            Set<?> selected = Stage.this.getSelectedObjects();
            for (Object o : selected) {
                set.add(o);
            }
            for (Object e : set) {
                if (e instanceof Binding) {
                    connections.add((Binding) e);
                    Stage.this.removeEdge((Binding) e);
                } else if (e instanceof HFDStageElement) {
                    HFDStageElement element = (HFDStageElement) e;
                    nodes.add(element);
                }
            }
            //Performance wise nodes should be removed after connections, otherwise you have to check if the connection is still on the scene
            for (HFDStageElement n : nodes) {
                if (n.isComponent()) {
                    Collection<Interface> pins = Stage.this.getNodePins(n);
                    pins = (pins == null ? Collections.EMPTY_SET : pins);
                    for (Interface p : pins) {
                        Collection<Binding> edges;
                        edges = findPinEdges(p, true, true);
                        connections.addAll(edges);
                    }
                }
                Stage.this.removeNodeWithEdges(n);
            }

        }

        @Override
        public void undo() throws CannotUndoException {
            super.undo(); //To change body of generated methods, choose Tools | Templates.
            for (HFDStageElement e : nodes) {
                myAddNode(e);
            }
            for (Binding c : connections) {
                myAddEdge(c);
            }
        }

        @Override
        public void redo() throws CannotRedoException {
            super.redo();
            for (Binding c : connections) {
                Stage.this.removeEdge(c);
            }
            for (HFDStageElement e : nodes) {
                Stage.this.removeNodeWithEdges(e);
            }

        }

        @Override
        public String getPresentationName() {
            return "delete selected";
        }

    }

    //Anchor for connections
    private class EditorAnchor extends Anchor {
        int gap = 7;
        
        EditorAnchor(Widget widget, int gap){
            super(widget);
            this.gap = gap;
        }
        
        @Override
        public Result compute(Entry entry) {
            Widget widget = getRelatedWidget();
            Rectangle bounds = widget.convertLocalToScene(widget.getBounds());
            Point center = getCenter(bounds);
            return new Anchor.Result (new Point (center.x + gap, center.y), Direction.RIGHT);
        }
        

        private Point getCenter(Rectangle bounds) {
            double  x = bounds.getX() + (bounds.getMaxX() - bounds.getMinX())/2;
            double y = bounds.getY() + (bounds.getMaxY() - bounds.getMinY())/2;
            return new Point((int)x,(int)y);
        }

    }
    
    

}
