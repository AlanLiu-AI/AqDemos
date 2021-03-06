
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.aqacquisitionservice.FieldVisit;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fv" type="{http://schemas.datacontract.org/2004/07/AQAcquisitionService.Dto}FieldVisit" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fv"
})
@XmlRootElement(name = "SaveFieldVisit")
public class SaveFieldVisit {

    @XmlElementRef(name = "fv", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<FieldVisit> fv;

    /**
     * Gets the value of the fv property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link FieldVisit }{@code >}
     *     
     */
    public JAXBElement<FieldVisit> getFv() {
        return fv;
    }

    /**
     * Sets the value of the fv property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link FieldVisit }{@code >}
     *     
     */
    public void setFv(JAXBElement<FieldVisit> value) {
        this.fv = value;
    }

}
