//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2015.05.12 às 05:35:34 PM GMT-03:00 
//
package net.emoreira.hfd.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de architecture complex type.
 *
 * <p>
 * O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro
 * desta classe.
 *
 * <pre>
 * &lt;complexType name="architecture">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subarch" type="{http://xml.hfd.emoreira.net}subarch" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="binding" type="{http://xml.hfd.emoreira.net}binding" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "architecture", propOrder = {
    "subarch",
    "binding"
})
public final class Architecture {

    protected List<Subarch> subarch;
    protected List<Binding> binding;

    /**
     * Gets the value of the subarch property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the subarch property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubarch().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Subarch }
     *
     *
     */
    public List<Subarch> getSubarch() {
        if (subarch == null) {
            subarch = new ArrayList<Subarch>();
        }
        return this.subarch;
    }

    /**
     * Gets the value of the binding property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the binding property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBinding().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Binding }
     *
     *
     */
    public List<Binding> getBinding() {
        if (binding == null) {
            binding = new ArrayList<Binding>();
        }
        return this.binding;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder("\n"
                + "Architecture" + "\n");
        StringBuilder subarchBuffer;
        subarchBuffer = new StringBuilder("Subarchs:\n");
        if (subarch != null) {
            for (Subarch suba : subarch) {
                subarchBuffer.append(suba.toString());
            }
        }
        buffer.append(subarchBuffer.toString().replaceAll("\n", "\n\t"));

        StringBuilder bindingsBuffer;
        bindingsBuffer = new StringBuilder("Bindings:\n");
        if (binding != null) {
            for (Binding bin : binding) {
                bindingsBuffer.append(bin.toString());
            }
        }
        buffer.append(bindingsBuffer.toString().replaceAll("\n", "\n\t"));
        buffer.append("End of Architecture\n");
        return buffer.toString();
    }

}
