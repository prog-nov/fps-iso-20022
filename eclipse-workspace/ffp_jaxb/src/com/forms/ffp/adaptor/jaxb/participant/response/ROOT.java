package com.forms.ffp.adaptor.jaxb.participant.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.forms.ffp.adaptor.jaxb.participant.response.head.HEAD;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "head", "body" })
@XmlRootElement(name = "ROOT")
public class ROOT
{
	@XmlElement(name = "HEAD", required = true)
	protected HEAD head;
	@XmlElement(name = "BODY", required = true)
	protected BODY body;

	/**
	 * Gets the value of the head property.
	 * 
	 * @return possible object is {@link HEAD }
	 * 
	 */
	public HEAD getHEAD()
	{
		return head;
	}

	/**
	 * Sets the value of the head property.
	 * 
	 * @param value
	 *            allowed object is {@link HEAD }
	 * 
	 */
	public void setHEAD(HEAD value)
	{
		this.head = value;
	}

	/**
	 * Gets the value of the body property.
	 * 
	 * @return possible object is {@link BODY }
	 * 
	 */
	public BODY getBODY()
	{
		return body;
	}

	/**
	 * Sets the value of the body property.
	 * 
	 * @param value
	 *            allowed object is {@link BODY }
	 * 
	 */
	public void setBODY(BODY value)
	{
		this.body = value;
	}

}
