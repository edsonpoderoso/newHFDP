/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd.model;

import com.google.common.base.Optional;
import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author edson
 */
public interface HFDStageElement {
    public boolean isComponent();
    public boolean isSubarch();
    public Component asComponent();
    public Subarch asSubarch();
    public Optional<Point> getPosition();
    public void setPosition(Point p);
    public Optional<Dimension> getDimension();
    public void setDimension(Dimension d);
}
