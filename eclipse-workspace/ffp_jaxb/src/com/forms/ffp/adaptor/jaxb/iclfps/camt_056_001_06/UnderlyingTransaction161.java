//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.30 at 11:51:13 ���� CST 
//


package com.forms.ffp.adaptor.jaxb.iclfps.camt_056_001_06;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnderlyingTransaction16__1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnderlyingTransaction16__1">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TxInf" type="{urn:iso:std:iso:20022:tech:xsd:camt.056.001.06}PaymentTransaction75__1"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnderlyingTransaction16__1", propOrder = {
    "txInf"
})
public class UnderlyingTransaction161 {

    @XmlElement(name = "TxInf", required = true)
    protected PaymentTransaction751 txInf;

    /**
     * Gets the value of the txInf property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentTransaction751 }
     *     
     */
    public PaymentTransaction751 getTxInf() {
        return txInf;
    }

    /**
     * Sets the value of the txInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentTransaction751 }
     *     
     */
    public void setTxInf(PaymentTransaction751 value) {
        this.txInf = value;
    }

}