/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd;

import java.awt.datatransfer.DataFlavor;
import net.emoreira.hfd.model.Component;

/**
 *
 * @author edson.moreira
 */
public class HFDComponentFlavor extends DataFlavor{

    
    public static final DataFlavor HFD_COMPONENT_FLAVOR = new HFDComponentFlavor();

    public HFDComponentFlavor() {
        super(Component.class, "Component");
    }
    
}
