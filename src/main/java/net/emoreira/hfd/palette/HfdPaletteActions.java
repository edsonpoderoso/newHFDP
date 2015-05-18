/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd.palette;

import javax.swing.Action;
import org.netbeans.spi.palette.PaletteActions;
import org.openide.util.Lookup;

/**
 *
 * @author edson
 */
public class HfdPaletteActions extends PaletteActions {

    @Override
    public Action[] getImportActions() {
        return new Action[0];
    }

    @Override
    public Action[] getCustomPaletteActions() {
        return new Action[0];
    }

    @Override
    public Action[] getCustomCategoryActions(Lookup lkp) {
        return new Action[0];
    }

    @Override
    public Action[] getCustomItemActions(Lookup lkp) {
        return new Action[0];
    }

    @Override
    public Action getPreferredAction(Lookup lkp) {
        return null;
    }
    
}
