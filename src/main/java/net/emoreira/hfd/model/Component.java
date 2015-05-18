//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2015.05.12 às 05:35:34 PM GMT-03:00 
//
package net.emoreira.hfd.model;

import com.google.common.base.Optional;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Classe Java de component complex type.
 *
 * <p>
 * O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro
 * desta classe.
 *
 * <pre>
 * &lt;complexType name="component">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="providedInterface" type="{http://xml.hfd.emoreira.net}interface" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="requiredInterface" type="{http://xml.hfd.emoreira.net}interface" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="shortDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="longDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="x" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="y" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="width" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="height" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "component", propOrder = {
    "providedInterface",
    "requiredInterface",
    "shortDescription",
    "longDescription"
})
public final class Component implements HFDStageElement {

    protected List<Interface> providedInterface;
    protected List<Interface> requiredInterface;
    protected String shortDescription;
    protected String longDescription;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "x")
    protected Integer x;
    @XmlAttribute(name = "y")
    protected Integer y;
    @XmlAttribute(name = "width")
    protected Integer width;
    @XmlAttribute(name = "height")
    protected Integer height;

    public Component() {
    }

    /**
     * This is a safe copy mechanism for the class component. It doesn't copy Id
     * so it is JAXB Safety and it doesn't copy parent so it's modeling safe.
     *
     * @param aComponent the component to be copied.
     */
    public Component(Component aComponent) {
        this.name = aComponent.name;
        this.x = new Integer(aComponent.x);
        this.y = new Integer(aComponent.y);
        this.width = new Integer(aComponent.width);
        this.height = new Integer(aComponent.height);
        List<Interface> thisProv = this.getProvidedInterface();
        List<Interface> aCompProv = aComponent.getProvidedInterface();
        for (Interface i : aCompProv) {
            thisProv.add(new Interface(i));
        }
        List<Interface> thisReq = this.getRequiredInterface();
        List<Interface> aCompReq = aComponent.getRequiredInterface();
        for (Interface i : aCompReq) {
            thisReq.add(new Interface(i));
        }
    }

    /**
     * Gets the value of the providedInterface property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the providedInterface property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProvidedInterface().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Interface }
     *
     *
     */
    public List<Interface> getProvidedInterface() {
        if (providedInterface == null) {
            providedInterface = new ArrayList<Interface>();
        }
        return this.providedInterface;
    }

    /**
     * Gets the value of the requiredInterface property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the requiredInterface property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequiredInterface().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Interface }
     *
     *
     */
    public List<Interface> getRequiredInterface() {
        if (requiredInterface == null) {
            requiredInterface = new ArrayList<Interface>();
        }
        return this.requiredInterface;
    }

    /**
     * Obtém o valor da propriedade shortDescription.
     *
     * @return possible object is {@link String }
     *
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Define o valor da propriedade shortDescription.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setShortDescription(String value) {
        this.shortDescription = value;
    }

    /**
     * Obtém o valor da propriedade longDescription.
     *
     * @return possible object is {@link String }
     *
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * Define o valor da propriedade longDescription.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setLongDescription(String value) {
        this.longDescription = value;
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
     * Obtém o valor da propriedade x.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getX() {
        return x;
    }

    /**
     * Define o valor da propriedade x.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setX(Integer value) {
        this.x = value;
    }

    /**
     * Obtém o valor da propriedade y.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getY() {
        return y;
    }

    /**
     * Define o valor da propriedade y.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setY(Integer value) {
        this.y = value;
    }

    /**
     * Obtém o valor da propriedade width.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Define o valor da propriedade width.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setWidth(Integer value) {
        this.width = value;
    }

    /**
     * Obtém o valor da propriedade height.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Define o valor da propriedade height.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setHeight(Integer value) {
        this.height = value;
    }

    @XmlTransient
    private Optional<Subarch> parent;

    /**
     * Obtem o valor da propriedade parent (Só é válida já na arquitetura).
     *
     * @return possible object is {@link Subarch}
     */
    public Optional<Subarch> getParent() {
        return parent;
    }

    public void setParent(Subarch parent) {
        this.parent = Optional.fromNullable(parent);
    }

    /**
     * Configura o parent do objeto no Unmarshalling (Apenas Subarch deve ser
     * considerado parent de um componente). Posteriormente ele configura as
     * interfaces do componente apropriadamente como providas ou requeridas pois
     * o processo de Unmarshalling não pode realizar essa operação sozinho com a
     * estrutura do XML adotado.
     *
     * @param unmarshaller
     * @param parent
     */
    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        if (parent instanceof Subarch) {
            setParent((Subarch) parent);
        } else {
            setParent(null);
        }
        //Let's have a test drive
        if (providedInterface != null) {
            for (Interface p : providedInterface) {
                p.setProvidedInterface();
            }
        }
        if (requiredInterface != null) {
            for (Interface r : requiredInterface) {
                r.setRequiredInterface();
            }
        }

    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("\n"
                + "Component" + "\n"
                + (id == null ? "id: null" : "id: " + this.id) + "\n"
                + (name == null ? "name: null" : "name: " + this.name) + "\n"
                + (x == null ? "x: null" : "x: " + this.x) + "\n"
                + (y == null ? "y: null" : "y: " + this.y) + "\n"
                + (width == null ? "width: null" : "width: " + this.width) + "\n"
                + (height == null ? "height: null" : "height: " + this.height) + "\n"
                + (longDescription == null ? "longDescription: null" : "longDescription: " + this.longDescription) + "\n"
                + (shortDescription == null ? "shortDescription: null" : "shortDescription: " + this.shortDescription) + "\n"
                + (!parent.isPresent() ? "parent: absent" : "parent: present") + "\n");
        StringBuffer interfaceBuffer;
        interfaceBuffer = new StringBuffer("Provided Interfaces:\n");
        if (providedInterface != null) {
            for (Interface inter : providedInterface) {
                interfaceBuffer.append(inter.toString());
            }
        }
        buffer.append(interfaceBuffer.toString().replaceAll("\n", "\n\t"));
        buffer.append("\n");

        interfaceBuffer = new StringBuffer("Required Interfaces:\n");
        if (requiredInterface != null) {
            for (Interface inter : requiredInterface) {
                interfaceBuffer.append(inter.toString());
            }
        }
        buffer.append(interfaceBuffer.toString().replaceAll("\n", "\n\t"));
        buffer.append("\n");
        buffer.append("End of Component \n");
        return buffer.toString();
    }

    @Override
    public boolean isComponent() {
        return true;
    }

    @Override
    public boolean isSubarch() {
        return false;
    }

    @Override
    public Component asComponent() {
        return this;
    }

    @Override
    public Subarch asSubarch() {
        throw new UnsupportedOperationException("This HFDStageElement is not a Subarch.");
    }

    @Override
    public Optional<Point> getPosition() {
        if (x != null && y != null) {
            return Optional.of(new Point(x, y));
        } else {
            return Optional.absent();
        }
    }

    @Override
    public void setPosition(Point p) {
        if (p != null) {
            x = (int) p.getX();
            y = (int) p.getY();
        } else {
            throw new IllegalArgumentException("Component.setPosition(Point p) doesn't accept null");
        }
    }

    @Override
    public Optional<Dimension> getDimension() {
        if (width != null && height != null) {
            return Optional.of(new Dimension(width, height));
        } else {
            return Optional.absent();
        }
    }

    @Override
    public void setDimension(Dimension d) {
        if (d != null) {
            width = (int) d.getWidth();
            height = (int) d.getHeight();
        } else {
            throw new IllegalArgumentException("Component.setDimension(Dimension d) doesn't accept null");
        }
    }
}
