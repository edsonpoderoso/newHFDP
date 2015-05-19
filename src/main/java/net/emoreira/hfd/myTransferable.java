/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author edson.moreira
 */
public abstract class myTransferable implements Transferable {

    DataFlavor flavor;

    public myTransferable(DataFlavor flavor) {
        this.flavor = flavor;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{
            flavor
        };
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor that) {
        return flavor.equals(that);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (isDataFlavorSupported(flavor)) {
            return this.getData();
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
    protected abstract Object getData()throws UnsupportedFlavorException, IOException;
}
