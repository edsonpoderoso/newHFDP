//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2015.05.12 às 05:35:34 PM GMT-03:00 
//
package net.emoreira.hfd.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de binding complex type.
 *
 * <p>
 * O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro
 * desta classe.
 *
 * <pre>
 * &lt;complexType name="binding">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="serverInterface" use="required" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="clientInterface" use="required" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="protocol" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "binding")
public final class Binding {

    @XmlAttribute(name = "serverInterface", required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object serverInterface;
    @XmlAttribute(name = "clientInterface", required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object clientInterface;
    @XmlAttribute(name = "protocol")
    protected String protocol;

    /**
     * Obtém o valor da propriedade serverInterface.
     *
     * @return possible object is {@link Object }
     *
     */
    public Object getServerInterface() {
        return serverInterface;
    }

    /**
     * Define o valor da propriedade serverInterface.
     *
     * @param value allowed object is {@link Object }
     *
     */
    public void setServerInterface(Object value) {
        this.serverInterface = value;
    }

    /**
     * Obtém o valor da propriedade clientInterface.
     *
     * @return possible object is {@link Object }
     *
     */
    public Object getClientInterface() {
        return clientInterface;
    }

    /**
     * Define o valor da propriedade clientInterface.
     *
     * @param value allowed object is {@link Object }
     *
     */
    public void setClientInterface(Object value) {
        this.clientInterface = value;
    }

    /**
     * Obtém o valor da propriedade protocol.
     *
     * @return possible object is {@link String }
     *
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Define o valor da propriedade protocol.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setProtocol(String value) {
        this.protocol = value;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder("\n"
                + "Binding" + "\n"
                + (serverInterface == null ? "serverInterface: null" : "serverInterface: " + this.serverInterface) + "\n"
                + (clientInterface == null ? "clientInterface: null" : "clientInterface: " + this.clientInterface) + "\n"
                + (protocol == null ? "protocol: null" : "protocol: " + this.protocol) + "\n");
        return buffer.toString();
    }

}
