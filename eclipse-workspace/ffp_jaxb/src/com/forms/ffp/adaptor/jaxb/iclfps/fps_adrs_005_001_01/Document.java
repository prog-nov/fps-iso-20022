//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.30 at 11:51:13 ���� CST 
//


package com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_005_001_01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Document complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Document">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AdrStsUpdRpt" type="{urn:hkicl:fps:xsd:fps.adrs.005.001.01}MessageRoot"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Document", propOrder = {
    "adrStsUpdRpt"
})
public class Document {

    @XmlElement(name = "AdrStsUpdRpt", required = true)
    protected MessageRoot adrStsUpdRpt;

    /**
     * Gets the value of the adrStsUpdRpt property.
     * 
     * @return
     *     possible object is
     *     {@link MessageRoot }
     *     
     */
    public MessageRoot getAdrStsUpdRpt() {
        return adrStsUpdRpt;
    }

    /**
     * Sets the value of the adrStsUpdRpt property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageRoot }
     *     
     */
    public void setAdrStsUpdRpt(MessageRoot value) {
        this.adrStsUpdRpt = value;
    }

}