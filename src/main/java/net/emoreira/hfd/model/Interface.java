//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2015.05.12 às 05:35:34 PM GMT-03:00 
//
package net.emoreira.hfd.model;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlTransient;

/**
 * <p>
 * Classe Java de interface complex type.
 *
 * <p>
 * O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro
 * desta classe.
 *
 * <pre>
 * &lt;complexType name="interface">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="signature" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "interface")
public final class Interface {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "signature", required = true)
    protected String signature;
    @XmlTransient
    private InterfaceType type;
    @XmlTransient
    private Component parent;

    public Interface() {
    }
    
    /**
     * This is a safe copy mechanism for the class Interface.
     * It doesn't copy Id so it is JAXB Safety and it doesn't copy parent so
     * it's modeling safe.
     * 
     * @param anInterface 
     */
    public Interface(Interface anInterface) {
        this.name = anInterface.name;
        this.signature = anInterface.signature;
        this.type = anInterface.type;
    }
    
    /**
     * Obtém o valor da propriedade id.
     *
     * @return possible object is {@link String }
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Define o valor da propriedade id.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtém o valor da propriedade name.
     *
     * @return possible object is {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Define o valor da propriedade name.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtém o valor da propriedade signature.
     *
     * @return possible object is {@link String }
     *
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Define o valor da propriedade signature.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setSignature(String value) {
        this.signature = value;
    }

    /**
     * Obtem o valor da propriedade parent
     *
     * @return possible object is {@link Component}
     */
    public Component getParent() {
        return parent;
    }

    /**
     * Verifica se a interface é do tipo provida
     *
     * @return true se a interace for provida, falso em qualquer outro caso.
     */
    public boolean isProvidedInterface() {
        return InterfaceType.provided.equals(type);
    }

    /**
     * Verifica se a interface é do tipo requerida
     *
     * @return true se a interace for requerida, falso em qualquer outro caso.
     */
    public boolean isRequiredInterface() {
        return InterfaceType.required.equals(type);
    }

    /**
     * Define a interface como requerida
     */
    protected void setRequiredInterface() {
        type = InterfaceType.required;
    }

    /**
     * Define a interface como provida
     */
    protected void setProvidedInterface() {
        type = InterfaceType.provided;
    }

    /**
     * Registra o objeto pai da interface após o seu Unmarshalling.
     * Este método não deve ser chamado pelo usuário.
     *
     * @param unmarshaller the {@link Unmarshaller} of this object
     * @param parent the parent of this object on the object tree. Allowed
     * object is {@link Component}
     */
    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        if (parent != null && parent instanceof Component) {
            this.parent = (Component) parent;
        }
    }

    private enum InterfaceType {
        provided,
        required;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 37 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 37 * hash + (this.signature != null ? this.signature.hashCode() : 0);
        hash = 37 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 37 * hash + (this.parent != null ? this.parent.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    public boolean matchesForConnection(Interface obj) {
        if (obj == null) {
            return false;
        }
        final Interface other = obj;
        if ((this.id == null) ? (other.id != null) : this.id.equals(other.id)) {
            return false;
        }
        //Até onde sei não tem problema os nomes das interfaces serem diferentes.
//        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
//            return false;
//        }
        if (((this.signature == null) || (other.signature == null)) || !this.signature.equals(other.signature)) {
            return false;
        }
        if ((this.type == null || other.type == null) || (this.type != other.type)) {
            return false;
        }
        return !((this.parent == null) || (other.parent == null));
    }

    @Override
    public String toString() {
        return ("\n"+
                "Interface"+ "\n"+
                (id == null?"id: null": "id: " + this.id) + "\n"+
                (name == null?"name: null": "name: " + this.name) + "\n"+
                (signature == null?"signature: null": "signature: " + this.signature) + "\n"+
                (type == null?"type: null": "type: " + this.type) + "\n"+
                (parent == null?"parent: null": "parent: present")+ "\n"+
                "End of Interface \n");    
    }
    
    

}
