/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd.palette;

import net.emoreira.hfd.model.Catalog;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author edson
 */
public class CategoryNode extends AbstractNode{
        CategoryNode(PaletteCategory category, Catalog catalog) {
        super(Children.create(new SimpleItemNodeFactory(category, catalog), true));
        this.setDisplayName(category.toString());
    }
}
