/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emoreira.hfd.xml;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import net.emoreira.hfd.FileHandler;
import net.emoreira.hfd.guice.JaxbHfdContext;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;

/**
 *
 * @author edson
 */
public class XMLFileHandler implements FileHandler{
    String context;
    private JAXBContext jc;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
    
    @Inject
    public XMLFileHandler(@JaxbHfdContext String context) {
        this.context = context;
    }
    
    private void setupJAXB() throws JAXBException{
        if(jc == null){
            jc = JAXBContext.newInstance(context);
        }
        if(marshaller == null){
            marshaller = jc.createMarshaller();
        }
        if(unmarshaller == null){
            unmarshaller = jc.createUnmarshaller();
        }
    }
    
    @Override
    public Optional<Hfd> readFile(FileObject fileObject) {
        
        try (InputStream is = fileObject.getInputStream()) {
            setupJAXB();
            Hfd root = (Hfd) unmarshaller.unmarshal(is);
            return Optional.of(root);
        } catch (JAXBException ex) {
            Exceptions.printStackTrace(ex);
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex){
            Exceptions.printStackTrace(ex);
        }
        return Optional.absent();
    }

    @Override
    public boolean writeFile(Hfd hfd, FileObject fileObject) {
        try (OutputStream os = fileObject.getOutputStream()){
            setupJAXB();
            marshaller.marshal(hfd, os);
            return true;
        } catch (JAXBException | IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return false;
    }
}
