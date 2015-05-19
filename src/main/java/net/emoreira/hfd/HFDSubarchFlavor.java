/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd;

import java.awt.datatransfer.DataFlavor;
import net.emoreira.hfd.model.Subarch;

/**
 *
 * @author edson.moreira
 */
public class HFDSubarchFlavor extends DataFlavor{

    public static final DataFlavor HFD_SUBARCH_FLAVOR = new HFDSubarchFlavor();

    public HFDSubarchFlavor() {
        super(Subarch.class, "Subarch");
    }
}
