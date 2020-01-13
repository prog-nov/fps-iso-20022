package com.forms.batch.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.xml.bind.annotation.XmlElement;

import com.forms.ffp.core.utils.FFPStringUtils;

public class FFPMandatoryFieldsCheckUtils 
{
	public static void validateRequestMessage(com.forms.ffp.adaptor.jaxb.participant.request.ROOT root)
	{
		com.forms.ffp.adaptor.jaxb.participant.request.head.HEAD head = root.getHEAD();
		com.forms.ffp.adaptor.jaxb.participant.request.BODY body = root.getBODY();
		
		try
		{
			
		}
		catch(Exception ex)
		{
			String msg = ex.getMessage();
			
			
		}
	}
	
	public static void validateRequestHead(com.forms.ffp.adaptor.jaxb.participant.request.head.HEAD head) throws Exception
	{
		Field[] fields = head.getClass().getDeclaredFields();
		
		for(Field f : fields)
		{
			f.setAccessible(true);
			
			Annotation[] allAnnotations = f.getDeclaredAnnotations();
			for(Annotation an : allAnnotations)
			{
				Class<? extends Annotation> anType = an.annotationType();
				
				Annotation annotationDes = f.getDeclaredAnnotation(anType);
				if(annotationDes instanceof XmlElement)
				{
					XmlElement xmlElement = (XmlElement)annotationDes;
					
					String anName = xmlElement.name();
					boolean anRequired = xmlElement.required();
					
					//Mandatory field
					if(anRequired)
					{
						Object obj = f.get(head);
						if(obj == null || (obj instanceof String && FFPStringUtils.isEmptyOrNull((String)obj)))
						{
							throw new Exception(String.format("Request xml message is missing the mandatory field : %s", anName));
						}
					}
				}
				else
				{
					//Add other validation when more than one annotation used
				}
			}
		}
	}
	
	public static void validateRequestBody(com.forms.ffp.adaptor.jaxb.participant.request.BODY body) throws Exception
	{
		Field[] fields = body.getClass().getDeclaredFields();
		
		for(Field f : fields)
		{
			f.setAccessible(true);
			
			//String fieldName = f.getName();
			//Annotation annotation = f.getDeclaredAnnotation(XmlElement.class);
			Annotation[] allAnnotations = f.getDeclaredAnnotations();
			for(Annotation an : allAnnotations)
			{
				Class<? extends Annotation> anType = an.annotationType();
				
				Annotation annotationDes = f.getDeclaredAnnotation(anType);
				if(annotationDes instanceof XmlElement)
				{
					XmlElement xmlElement = (XmlElement)annotationDes;
					
					String anName = xmlElement.name();
					boolean anRequired = xmlElement.required();
					
					//Mandatory field
					if(anRequired)
					{
						//Method method = cl.getMethod("get" + anName, type);
						/*System.out.println(f.getGenericType().toString());
						if(f.getGenericType().toString().compareTo("class com.forms.ffp.adaptor.jaxb.participant.request.ffpcto01.FPSPARTICIPANTID") == 0)
						{
							body.getClass().getTypeName();
						}*/
						
						Object obj = f.get(body);
						if(obj == null || (obj instanceof String && FFPStringUtils.isEmptyOrNull((String)obj)))
						{
							throw new Exception(String.format("Request xml message is missing the mandatory field : %s", anName));
						}
					}
				}
				else
				{
					//Add other validation when more than one annotation used
				}
			}
		}
	}
}
