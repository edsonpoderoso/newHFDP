/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd.xml;

import com.google.inject.AbstractModule;
import net.emoreira.hfd.FileHandler;
import net.emoreira.hfd.guice.JaxbHfdContext;

/**
 *
 * @author edson
 */
public class XMLModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(FileHandler.class).to(XMLFileHandler.class);
        bind(String.class).annotatedWith(JaxbHfdContext.class).toInstance("net.emoreira.hfd.xml");
    }
}
