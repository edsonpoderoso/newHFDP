/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd;

import com.google.inject.AbstractModule;

/**
 *
 * @author edson.moreira
 */
public class HFDServiceModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(FileHandler.class).to(XmlFileHandler.class);
    }
    
}
