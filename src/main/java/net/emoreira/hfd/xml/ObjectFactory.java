//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2015.05.12 às 05:35:34 PM GMT-03:00 
//


package net.emoreira.hfd.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the net.emoreira.hfd.xml package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Hfd_QNAME = new QName("http://xml.hfd.emoreira.net", "hfd");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.emoreira.hfd.xml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Hfd }
     * 
     */
    public Hfd createHfd() {
        return new Hfd();
    }

    /**
     * Create an instance of {@link Subarch }
     * 
     */
    public Subarch createSubarch() {
        return new Subarch();
    }

    /**
     * Create an instance of {@link Component }
     * 
     */
    public Component createComponent() {
        return new Component();
    }

    /**
     * Create an instance of {@link Catalog }
     * 
     */
    public Catalog createCatalog() {
        return new Catalog();
    }

    /**
     * Create an instance of {@link Binding }
     * 
     */
    public Binding createBinding() {
        return new Binding();
    }

    /**
     * Create an instance of {@link Interface }
     * 
     */
    public Interface createInterface() {
        return new Interface();
    }

    /**
     * Create an instance of {@link Architecture }
     * 
     */
    public Architecture createArchitecture() {
        return new Architecture();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hfd }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.hfd.emoreira.net", name = "hfd")
    public JAXBElement<Hfd> createHfd(Hfd value) {
        return new JAXBElement<Hfd>(_Hfd_QNAME, Hfd.class, null, value);
    }

}
