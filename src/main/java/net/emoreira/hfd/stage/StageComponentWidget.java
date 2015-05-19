/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd.stage;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import net.emoreira.hfd.model.Component;
import net.emoreira.hfd.model.Interface;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author edson
 */
public class StageComponentWidget extends Widget {

    private final int INTERFACES_IDENT = 5;
    private final int CELL_PADDING = 3;
    private final int BORDER_THICKNESS = 3;
    private final Component component;
    private final List<Interface> providedInterfaces;
    private final List<Interface> requiredInterfaces;

    public StageComponentWidget(Scene scene, Component component) {
        super(scene);
        this.component = component;
        providedInterfaces = component.getProvidedInterface();
        requiredInterfaces = component.getRequiredInterface();
        //Collections.sort(providedInterfaces);
        //Collections.sort(requiredInterfaces);
    }

    private int getNumberOfCells() {
        return 3 + providedInterfaces.size() + requiredInterfaces.size();
    }

    protected int getCellHeight() {
        Graphics2D g = this.getGraphics();
        FontMetrics metrics = g.getFontMetrics(this.getFont());
        return (int) (2 * CELL_PADDING + metrics.getHeight());
    }

    private int getCellWidth() {
        Graphics2D g = this.getGraphics();
        FontMetrics metrics = g.getFontMetrics(this.getFont());
        double width = 2 * CELL_PADDING + metrics.stringWidth(component.getName());
        width = Math.max(width, 2 * CELL_PADDING + metrics.stringWidth("Provided Interfaces"));
        width = Math.max(width, 2 * CELL_PADDING + metrics.stringWidth("Required Interfaces"));
        for (Interface e : providedInterfaces) {
            width = Math.max(width, metrics.stringWidth(e.getName() + ":" + e.getSignature()) + INTERFACES_IDENT + 2 * CELL_PADDING);
        }
        for (Interface e : requiredInterfaces) {
            width = Math.max(width, metrics.stringWidth(e.getName() + ":" + e.getSignature()) + INTERFACES_IDENT + 2 * CELL_PADDING);
        }
        return (int) width;
    }

    @Override
    protected Rectangle calculateClientArea() {
        Graphics2D g = this.getGraphics();
        int numberOfCells = 3 + providedInterfaces.size() + requiredInterfaces.size();
        int height = numberOfCells * getCellHeight();
        int width = getCellWidth();
        return new Rectangle((int) width, (int) height);
    }

    protected Point getProvidedInterfacePinPosition(Interface _interface) {
        if (providedInterfaces.contains(_interface)) {
            int cellHeight = this.getCellHeight();
            int cellWidth = this.getCellWidth();
            int pos = providedInterfaces.indexOf(_interface);
            pos += 2;
            return new Point(cellWidth + 1, pos * cellHeight + 1);
        } else {
            throw new IllegalArgumentException(_interface + " is not a provided interface on this component");
        }
    }

    protected Point getRequiredInterfacePinPosition(Interface _interface) {
        if (requiredInterfaces.contains(_interface)) {
            int cellHeight = this.getCellHeight();
            int cellWidth = this.getCellWidth();
            int pos = requiredInterfaces.indexOf(_interface);
            pos += 3 + providedInterfaces.size();
            return new Point(cellWidth + 1, pos * cellHeight + 1);
        } else {
            throw new IllegalArgumentException(_interface + " is not a required interface on this component");
        }
    }

    @Override
    protected void paintWidget() {
        paintBackground();
        Rectangle r = calculateClientArea();
        Graphics2D g = this.getGraphics();
        g.setColor(this.getForeground());
        g.drawRect(0, 0, (int) r.getWidth(), (int) r.getHeight());

        int ascent = g.getFontMetrics().getAscent();
        int fontSize = g.getFontMetrics().getHeight();
        int cellHeight = this.getCellHeight();
        int cellWidth = this.getCellWidth();

        //Writes the component name and draws a divider line
        g.drawString(component.getName(), CELL_PADDING, CELL_PADDING + ascent);
        g.drawLine(0, cellHeight, cellWidth, cellHeight);

        //Writes Provided Interfaces e lists the provided interfaces
        g.drawString("Provided Interfaces", CELL_PADDING, CELL_PADDING + cellHeight + ascent);
        int pInterfacesSize = providedInterfaces.size();
        for (int i = 0; i < pInterfacesSize; i++) {
            Interface e = providedInterfaces.get(i);
            g.drawString(e.getName() + ":" + e.getSignature(), CELL_PADDING + INTERFACES_IDENT, CELL_PADDING + (cellHeight * 2) + (i * cellHeight) + ascent);
        }

        //Writes Required Interfaces and lists the required interfaces
        g.drawString("Required Interfaces", CELL_PADDING, CELL_PADDING + (cellHeight * 2) + (cellHeight * providedInterfaces.size()) + ascent);
        int rInterfacesSize = requiredInterfaces.size();
        for (int i = 0; i < rInterfacesSize; i++) {
            Interface e = requiredInterfaces.get(i);
            g.drawString(e.getName() + ":" + e.getSignature(), CELL_PADDING + INTERFACES_IDENT, CELL_PADDING + (cellHeight * providedInterfaces.size()) + (cellHeight * 3) + (i * cellHeight) + ascent);
        }
        paintBorder();
    }

    //Paints the background of the widget
    @Override
    protected void paintBackground() {
        Color header = getColor(100, 150, 200);
        Color sub = getColor(160, 200, 240);
        Color nodes = getColor(245, 245, 255);
        Graphics2D g = getGraphics();
        int cellHeight = getCellHeight();
        int cellWidth = getCellWidth();
        int cells = getNumberOfCells();
        for (int i = 0; i < cells; i++) {
            if (i == 0) {
                g.setColor(header);
            } else if (i == 1 || i == 2 + providedInterfaces.size()) {
                g.setColor(sub);
            } else {
                g.setColor(nodes);
            }
            g.fillRect(0, i * cellHeight, cellWidth, cellHeight);
        }
    }

    @Override
    protected void notifyStateChanged(ObjectState previousState, ObjectState state) {
        super.notifyStateChanged(previousState, state); //To change body of generated methods, choose Tools | Templates.
        if (state.isSelected()) {
            this.setBorder(BorderFactory.createLineBorder(BORDER_THICKNESS, Color.MAGENTA));
        } else {
            this.setBorder(BorderFactory.createEmptyBorder());
        }
    }

    private Color getColor(int r, int g, int b) {
        return Color.decode("0x" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b));
    }

}
