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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Classe Java de subarch complex type.
 *
 * <p>
 * O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro
 * desta classe.
 *
 * <pre>
 * &lt;complexType name="subarch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="component" type="{http://xml.hfd.emoreira.net}component" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="model" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="host" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlType(name = "subarch", propOrder = {
    "component"
})
public final class Subarch implements HFDStageElement{

    protected List<Component> component;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "model")
    protected String model;
    @XmlAttribute(name = "host")
    protected String host;
    @XmlAttribute(name = "x")
    protected Integer x;
    @XmlAttribute(name = "y")
    protected Integer y;
    @XmlAttribute(name = "width")
    protected Integer width;
    @XmlAttribute(name = "height")
    protected Integer height;

    /**
     * Gets the value of the component property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the component property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComponent().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Component }
     *
     *
     */
    public List<Component> getComponent() {
        if (component == null) {
            component = new ArrayList<Component>();
        }
        return this.component;
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
     * Obtém o valor da propriedade model.
     *
     * @return possible object is {@link String }
     *
     */
    public String getModel() {
        return model;
    }

    /**
     * Define o valor da propriedade model.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setModel(String value) {
        this.model = value;
    }

    /**
     * Obtém o valor da propriedade host.
     *
     * @return possible object is {@link String }
     *
     */
    public String getHost() {
        return host;
    }

    /**
     * Define o valor da propriedade host.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setHost(String value) {
        this.host = value;
    }
    
    /**
     * Obtém o valor da propriedade x.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getX() {
        return x;
    }

    /**
     * Define o valor da propriedade x.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setX(Integer value) {
        this.x = value;
    }

    /**
     * Obtém o valor da propriedade y.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getY() {
        return y;
    }

    /**
     * Define o valor da propriedade y.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setY(Integer value) {
        this.y = value;
    }

    /**
     * Obtém o valor da propriedade width.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Define o valor da propriedade width.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWidth(Integer value) {
        this.width = value;
    }

    /**
     * Obtém o valor da propriedade height.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Define o valor da propriedade height.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHeight(Integer value) {
        this.height = value;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder("\n"
                + "Subarch" + "\n"
                + (id == null ? "id: null" : "id: " + this.id) + "\n"
                + (name == null ? "name: null" : "name: " + this.name) + "\n"
                + (model == null ? "model: null" : "model: " + this.model) + "\n"
                + (host == null ? "host: null" : "host: " + this.host) + "\n"
                + (x == null ? "x: null" : "x: " + this.x) + "\n"
                + (y == null ? "y: null" : "y: " + this.y) + "\n"
                + (width == null ? "width: null" : "width: " + this.width) + "\n"
                + (height == null ? "height: null" : "height: " + this.height) + "\n");
        StringBuilder compoentnBuffer = new StringBuilder("Components\n");
        if (component != null) {
            for (Component comp : component) {
                compoentnBuffer.append(component.toString());
            }
        }
        buffer.append(compoentnBuffer.toString().replaceAll("\n", "\n\t"));
        buffer.append("End of Subarch \n");
        return buffer.toString();
    }
    
    @Override
    public boolean isComponent() {
        return false;
    }

    @Override
    public boolean isSubarch() {
        return true;
    }

    @Override
    public Component asComponent() {
        throw new UnsupportedOperationException("This HFDStageElement is not a Component.");
    }

    @Override
    public Subarch asSubarch() {
        return this;
    }

    @Override
    public Optional<Point> getPosition() {
        if(x != null && y != null){
            return Optional.of(new Point(x, y));
        }else{
            return Optional.absent();
        }
    }

    @Override
    public void setPosition(Point p) {
        if(p != null){
            x = (int)p.getX();
            y = (int)p.getY();
        }else{
            throw new IllegalArgumentException("Subarch.setPosition(Point p) doesn't accept null");
        }
    }

    @Override
    public Optional<Dimension> getDimension() {
        if(width != null && height != null){
            return Optional.of(new Dimension(width, height));
        }else{
            return Optional.absent();
        }
    }

    @Override
    public void setDimension(Dimension d) {
        if(d != null){
            width = (int)d.getWidth();
            height = (int)d.getHeight();
        }else{
            throw new IllegalArgumentException("Subarch.setDimension(Dimension d) doesn't accept null");
        }
    }
}
