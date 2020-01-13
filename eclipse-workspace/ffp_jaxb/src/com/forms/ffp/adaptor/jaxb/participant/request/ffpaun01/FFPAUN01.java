//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.05.11 at 10:51:17 上午 CST 
//


package com.forms.ffp.adaptor.jaxb.participant.request.ffpaun01;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.forms.ffp.adaptor.jaxb.participant.request.BODY;


/**
 * <p>Java class for FFPAUN01 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FFPAUN01">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SrcRefNm" type="{}RestrictedFINZText_Max30"/>
 *         &lt;element name="AdrSchme" type="{}ADDRESSINGSCHEMETYPE" maxOccurs="5000"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FFPAUN01", propOrder = {
    "srcRefNm",
    "adrSchme"
})
public class FFPAUN01 extends BODY{

    @XmlElement(name = "SrcRefNm", required = true)
    protected String srcRefNm;
    @XmlElement(name = "AdrSchme", required = true)
    protected List<ADDRESSINGSCHEMETYPE> adrSchme;

    /**
     * Gets the value of the srcRefNm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcRefNm() {
        return srcRefNm;
    }

    /**
     * Sets the value of the srcRefNm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcRefNm(String value) {
        this.srcRefNm = value;
    }

    /**
     * Gets the value of the adrSchme property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the adrSchme property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdrSchme().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ADDRESSINGSCHEMETYPE }
     * 
     * 
     */
    public List<ADDRESSINGSCHEMETYPE> getAdrSchme() {
        if (adrSchme == null) {
            adrSchme = new ArrayList<ADDRESSINGSCHEMETYPE>();
        }
        return this.adrSchme;
    }

}