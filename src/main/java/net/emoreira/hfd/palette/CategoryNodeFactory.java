/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd.palette;

import java.util.List;
import net.emoreira.hfd.model.Catalog;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 *
 * @author edson
 */
public class CategoryNodeFactory extends ChildFactory<Catalog> {

    private Catalog catalog;

    public CategoryNodeFactory(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    protected boolean createKeys(List<Catalog> list) {
        list.add(catalog);
        return true;
    }

    @Override
    protected Node[] createNodesForKey(Catalog key) {
        return new Node[]{
            new CategoryNode(PaletteCategory.Subarchitecture, catalog),
            new CategoryNode(PaletteCategory.Component, catalog),};
    }

}
