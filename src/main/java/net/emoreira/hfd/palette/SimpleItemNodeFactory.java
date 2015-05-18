/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd.palette;

import java.util.ArrayList;
import java.util.List;
import net.emoreira.hfd.model.Catalog;
import net.emoreira.hfd.model.Component;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 *
 * @author edson
 */
class SimpleItemNodeFactory extends ChildFactory<Catalog> {
    PaletteCategory category;
    Catalog catalog;

    public SimpleItemNodeFactory(PaletteCategory category, Catalog catalog) {
        if(category == null || catalog == null){
            throw new IllegalArgumentException("null is not an acceptable parameter for class SimpleItemNodeFactory");
        }
        this.category = category;
        this.catalog = catalog;
    }
    
    @Override
    protected boolean createKeys(List<Catalog> list) {
        list.add(catalog);
        return true;
    }

    @Override
    protected Node[] createNodesForKey(Catalog key) {
        if (this.category.equals(PaletteCategory.Subarchitecture)) {
            return new Node[]{
                new SubarchNode()
            };
        } else {
            return createNodesFromCatalog();
        }
    }

    private Node[] createNodesFromCatalog() {
        List<Component> list = catalog.getComponent();
        List<ComponentNode> result = new ArrayList<>();
        for (Component e : list) {
            result.add(new ComponentNode(e));
        }
        Node[] nodes = new Node[result.size()];
        for(int i =0; i< nodes.length; i++){
            nodes[i] = result.get(i);
        }
        return nodes;
    }
    
}
