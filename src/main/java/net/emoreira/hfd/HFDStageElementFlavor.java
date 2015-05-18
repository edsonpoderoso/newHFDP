/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd;

import java.awt.datatransfer.DataFlavor;
import net.emoreira.hfd.model.HFDStageElement;

/**
 *
 * @author edson
 */
public class HFDStageElementFlavor extends DataFlavor {
    
    public static final DataFlavor HFDELEMENT_FLAVOR = new HFDStageElementFlavor();

    public HFDStageElementFlavor() {
        super(HFDStageElement.class, "HFDStageElement");
    }
    
}
