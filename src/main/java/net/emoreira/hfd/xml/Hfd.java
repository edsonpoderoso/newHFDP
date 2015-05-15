//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2015.05.12 às 05:35:34 PM GMT-03:00 
//


package net.emoreira.hfd.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de hfd complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="hfd">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="catalog" type="{http://xml.hfd.emoreira.net}catalog"/>
 *         &lt;element name="architecture" type="{http://xml.hfd.emoreira.net}architecture"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hfd", propOrder = {
    "catalog",
    "architecture"
})
public class Hfd {

    @XmlElement(required = true)
    protected Catalog catalog;
    @XmlElement(required = true)
    protected Architecture architecture;

    /**
     * Obtém o valor da propriedade catalog.
     * 
     * @return
     *     possible object is
     *     {@link Catalog }
     *     
     */
    public Catalog getCatalog() {
        return catalog;
    }

    /**
     * Define o valor da propriedade catalog.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalog }
     *     
     */
    public void setCatalog(Catalog value) {
        this.catalog = value;
    }

    /**
     * Obtém o valor da propriedade architecture.
     * 
     * @return
     *     possible object is
     *     {@link Architecture }
     *     
     */
    public Architecture getArchitecture() {
        return architecture;
    }

    /**
     * Define o valor da propriedade architecture.
     * 
     * @param value
     *     allowed object is
     *     {@link Architecture }
     *     
     */
    public void setArchitecture(Architecture value) {
        this.architecture = value;
    }

}
