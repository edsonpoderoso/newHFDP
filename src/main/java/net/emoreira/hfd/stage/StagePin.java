/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd.stage;

import net.emoreira.hfd.model.Interface;

/**
 *
 * @author edson
 */
class StagePin {

    private final Interface inter;

    public StagePin(Interface inter) {
        assert(inter != null);
        this.inter = inter;
    }

    public Interface getInter() {
        return inter;
    }
}