/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd.palette;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;
import net.emoreira.hfd.HFDComponentFlavor;
import net.emoreira.hfd.model.Component;
import net.emoreira.hfd.model.HFDStageElement;
import net.emoreira.hfd.model.Interface;
import net.emoreira.hfd.myTransferable;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.NbBundle;
import org.openide.util.datatransfer.ExTransferable;

/**
 *
 * @author edson
 */
@NbBundle.Messages("COMPONENT_ICON_BASENAME=net/emoreira/hfd/palette/ComponentIcon.png")
public class ComponentNode extends AbstractNode{
    Component component;

    public ComponentNode(Component component) {
        super(Children.LEAF);
        this.component = component;
        this.setDisplayName(this.getLabel());
        this.setIconBaseWithExtension(Bundle.COMPONENT_ICON_BASENAME());
    }

    private HFDStageElement getHFDStageElement() {
        Component hfdElement = new Component(component);
        return hfdElement;
    }

    @Override
    public boolean canCut() {
        return false;
    }

    @Override
    public boolean canCopy() {
        return true;
    }

    @Override
    public boolean canDestroy() {
        return false;
    }

    @Override
    public Transferable clipboardCopy() throws IOException {
        Transferable answer = new myTransferable(HFDComponentFlavor.HFD_COMPONENT_FLAVOR) {

            @Override
            protected Object getData() throws IOException, UnsupportedFlavorException {
                return getHFDStageElement().asComponent();
            }
        };
        return answer;
    }

    @Override
    public String getHtmlDisplayName() {
        return "<b>" + component.getName() + "</b>";
    }

    @Override
    public String getShortDescription() {
        return this.getDescriptionLabel();
    }
    
    private String getLabel(){
        StringBuilder result = new StringBuilder();
        result.append("<html>");
        result.append("<table cellspacing=\"0\" cellpadding=\"1\">");
        result.append("<tr>");
        result.append("<td><b>").append(component.getName()).append("</b></td>");
        result.append("</tr>");
        result.append("<tr>");
        result.append("<td>").append(component.getShortDescription()).append("</td>");
        result.append("</tr>");
        result.append("</table>");
        result.append("</html>");
        return result.toString();
    }
    
    private String getDescriptionLabel() {
        StringBuilder result = new StringBuilder();
        result.append("<html>");
        result.append("<table cellspacing=\"0\" cellpadding=\"1\">");
        result.append("<tr>");
        result.append("<td><b>Name</b></td>");
        result.append("<td>").append(component.getName()).append("</td>");
        result.append("</tr>");
        result.append("<tr>");
        result.append("<td><b>Description</b></td>");
        result.append("<td>").append(component.getShortDescription()).append("</td>");
        result.append("</tr>");
        result.append("<tr>");
        result.append("<td><b>Provided Interfaces</b></td>");

        List<Interface> pInterfaces = component.getProvidedInterface();
        //Vefiry if it it useful to implement comparable and sort the collection
        //Collections.sort(pInterfaces); 
        if (!pInterfaces.isEmpty()) {
            boolean first = true;
            for (Interface e : pInterfaces) {
                if (first) {
                    result.append("<td>").append(e.getName()).append(":").append(e.getSignature()).append("</td></tr>");
                    first = false;
                } else {
                    result.append("<tr><td></td><td>");
                    result.append(e.getName()).append(":").append(e.getSignature());
                    result.append("</td></tr>");
                    first = false;
                }
            }
        } else {
            result.append("<td></td></tr>");
        }
        result.append("<tr><td><b>Required Interfaces</b></td>");
        List<Interface> rInterfaces = component.getRequiredInterface();
        //Vefiry if it it useful to implement comparable and sort the collection
        //Collections.sort(rInterfaces);
        if (!rInterfaces.isEmpty()) {
            boolean first = true;
            for (Interface e : rInterfaces) {
                if (first) {
                    result.append("<td>").append(e.getName()).append(":").append(e.getSignature()).append("</td></tr>");
                    first = false;
                } else {
                    result.append("<tr><td></td><td>");
                    result.append(e.getName()).append(":").append(e.getSignature());
                    result.append("</td></tr>");
                    first = false;
                }
            }
        } else {
            result.append("<td></td></tr>");
        }
        result.append("</table>");
        result.append("</html>");
        return result.toString();
    }
    
}
