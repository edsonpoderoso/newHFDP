/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd;

import com.google.inject.Inject;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
/**
 *
 * @author edson.moreira
 */
public class XmlFileHandler implements FileHandler{
    Marshaller marshaller;
    Unmarshaller unmarshaller;
    
    @Inject
    XmlFileHandler(Marshaller marshaller, Unmarshaller unmarshaller){
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
    }
    
    
}
