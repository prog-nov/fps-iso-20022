//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.30 at 11:51:13 ���� CST 
//


package com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AppHdr_QNAME = new QName("urn:iso:std:iso:20022:tech:xsd:head.001.001.01", "AppHdr");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BusinessApplicationHeaderV01 }
     * 
     */
    public BusinessApplicationHeaderV01 createBusinessApplicationHeaderV01() {
        return new BusinessApplicationHeaderV01();
    }

    /**
     * Create an instance of {@link FinancialInstitutionIdentification81 }
     * 
     */
    public FinancialInstitutionIdentification81 createFinancialInstitutionIdentification81() {
        return new FinancialInstitutionIdentification81();
    }

    /**
     * Create an instance of {@link ClearingSystemMemberIdentification21 }
     * 
     */
    public ClearingSystemMemberIdentification21 createClearingSystemMemberIdentification21() {
        return new ClearingSystemMemberIdentification21();
    }

    /**
     * Create an instance of {@link BranchAndFinancialInstitutionIdentification51 }
     * 
     */
    public BranchAndFinancialInstitutionIdentification51 createBranchAndFinancialInstitutionIdentification51() {
        return new BranchAndFinancialInstitutionIdentification51();
    }

    /**
     * Create an instance of {@link Party9Choice1 }
     * 
     */
    public Party9Choice1 createParty9Choice1() {
        return new Party9Choice1();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusinessApplicationHeaderV01 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:iso:std:iso:20022:tech:xsd:head.001.001.01", name = "AppHdr")
    public JAXBElement<BusinessApplicationHeaderV01> createAppHdr(BusinessApplicationHeaderV01 value) {
        return new JAXBElement<BusinessApplicationHeaderV01>(_AppHdr_QNAME, BusinessApplicationHeaderV01 .class, null, value);
    }

}
