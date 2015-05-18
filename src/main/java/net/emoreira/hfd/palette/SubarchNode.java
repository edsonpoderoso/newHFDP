/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd.palette;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import net.emoreira.hfd.HFDStageElementFlavor;
import net.emoreira.hfd.model.HFDStageElement;
import net.emoreira.hfd.model.Subarch;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.NbBundle;
import org.openide.util.datatransfer.ExTransferable;

/**
 *
 * @author edson
 */
@NbBundle.Messages("SUBARCH_ICON_BASENAME=net/emoreira/hfd/palette/SubarchIcon.png")
class SubarchNode extends AbstractNode {
    private final int SUBARCH_INITIAL_WIDTH = 200;
    private final int SUBARCH_INITIAL_HEIGHT = 200;

    SubarchNode() {
        super(Children.LEAF);
        this.setDisplayName("Sub-Architecture");
        this.setIconBaseWithExtension(Bundle.SUBARCH_ICON_BASENAME());
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
        Transferable answer = new ExTransferable.Single(HFDStageElementFlavor.HFDELEMENT_FLAVOR) {
            @Override
            protected Object getData() throws IOException, UnsupportedFlavorException {
                return getHfdStageElement();
            }
        };
        return answer;
    }
    
    private HFDStageElement getHfdStageElement(){
        Subarch sub = new Subarch();
        sub.setName("Untitled");
        sub.setModel("Undefined");
        sub.setHost("Undefined");
        sub.setDimension(new Dimension(SUBARCH_INITIAL_WIDTH, SUBARCH_INITIAL_HEIGHT));
        sub.setPosition(new Point(0, 0));
        return sub;
    }
}
