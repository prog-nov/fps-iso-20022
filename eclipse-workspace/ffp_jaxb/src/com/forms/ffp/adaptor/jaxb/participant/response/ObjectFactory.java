package com.forms.ffp.adaptor.jaxb.participant.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.forms.ffp.adaptor.jaxb.participant.response.ffpadr03.FFPADR03;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpadr04.FFPADR04;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpamr01.FFPAMR01;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpchk01.FFPCHK01;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpcti01.FFPCTI01;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpcti02.FFPCTI02;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpcto01.FFPCTO01;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpddi01.FFPDDI01;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpddi02.FFPDDI02;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpddo01.FFPDDO01;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpenq01.FFPENQ01;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpgf004.FFPGF004;
import com.forms.ffp.adaptor.jaxb.participant.response.ffphc001.FFPHC001;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpms001.FFPMS001;
import com.forms.ffp.adaptor.jaxb.participant.response.ffprri01.FFPRRI01;
import com.forms.ffp.adaptor.jaxb.participant.response.ffprro01.FFPRRO01;


@XmlRegistry
public class ObjectFactory {

    private final static QName _ROOT_QNAME = new QName("", "ROOT");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.forms.ffp.adaptor.jaxb.participant.request
     * 
     */
    public ObjectFactory() {
    }

    public ROOT createROOT() {
        return new ROOT();
    }

    @XmlElementDecl(namespace = "", name = "ROOT")
    public JAXBElement<ROOT> createROOT(ROOT value) {
        return new JAXBElement<ROOT>(_ROOT_QNAME, ROOT.class, null, value);
    }
    
    @XmlElementDecl(namespace = "", name = "BODY")
    public JAXBElement<?> createBODY(BODY value)
    {
    	if(value instanceof FFPCTI01)
    		return (new com.forms.ffp.adaptor.jaxb.participant.response.ffpcti01.ObjectFactory()).createBODY((FFPCTI01)value);
    	else if(value instanceof FFPCTI02)
    		return (new com.forms.ffp.adaptor.jaxb.participant.response.ffpcti02.ObjectFactory()).createBODY((FFPCTI02)value);
    	else if(value instanceof FFPCTO01)
    		return (new com.forms.ffp.adaptor.jaxb.participant.response.ffpcto01.ObjectFactory()).createBODY((FFPCTO01)value);
    	else if(value instanceof FFPDDI01)
    		return (new com.forms.ffp.adaptor.jaxb.participant.response.ffpddi01.ObjectFactory()).createBODY((FFPDDI01)value);
    	else if(value instanceof FFPDDI02)
    		return (new com.forms.ffp.adaptor.jaxb.participant.response.ffpddi02.ObjectFactory()).createBODY((FFPDDI02)value);
    	else if(value instanceof FFPDDO01)
    		return (new com.forms.ffp.adaptor.jaxb.participant.response.ffpddo01.ObjectFactory()).createBODY((FFPDDO01)value);
    	else if(value instanceof FFPMS001)
    		return (new com.forms.ffp.adaptor.jaxb.participant.response.ffpms001.ObjectFactory()).createBODY((FFPMS001)value);
    	else if(value instanceof FFPHC001)
    		return (new com.forms.ffp.adaptor.jaxb.participant.response.ffphc001.ObjectFactory()).createBODY((FFPHC001)value);
    	else if(value instanceof FFPRRI01)
    		return (new com.forms.ffp.adaptor.jaxb.participant.response.ffprri01.ObjectFactory()).createBODY((FFPRRI01)value);
    	else if(value instanceof FFPCHK01)
    		return (new com.forms.ffp.adaptor.jaxb.participant.response.ffpchk01.ObjectFactory()).createBODY((FFPCHK01)value);
    	else if(value instanceof FFPADR03)
    		return (new com.forms.ffp.adaptor.jaxb.participant.response.ffpadr03.ObjectFactory()).createBODY((FFPADR03)value);
    	else if(value instanceof FFPADR04)
    		return (new com.forms.ffp.adaptor.jaxb.participant.response.ffpadr04.ObjectFactory()).createBODY((FFPADR04)value);
    	else if(value instanceof FFPAMR01)
    		return (new com.forms.ffp.adaptor.jaxb.participant.response.ffpamr01.ObjectFactory()).createBODY((FFPAMR01)value);
    	else if(value instanceof FFPRRO01)
    		return (new com.forms.ffp.adaptor.jaxb.participant.response.ffprro01.ObjectFactory()).createBODY((FFPRRO01)value);
    	else if(value instanceof FFPENQ01)
    		return (new com.forms.ffp.adaptor.jaxb.participant.response.ffpenq01.ObjectFactory()).createBODY((FFPENQ01)value);
    	else if(value instanceof FFPGF004)
    		return (new com.forms.ffp.adaptor.jaxb.participant.response.ffpgf004.ObjectFactory()).createBODY((FFPGF004)value);
    	return null;
    }
    
}