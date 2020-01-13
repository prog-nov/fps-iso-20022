 package com.forms.ffp.msg.iclfps.bussiness;
 
 import com.hkicl.fps.data.validation.ValidationMessage;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class EndUserIdBean
 {
   @FFPValidateMessage(field="ID")
   private String id;
   @FFPValidateMessage(field="ID Type")
   private String idType;
   @FFPValidateMessage(field="Issuer")
   private String issuer;
   
   public String getId()
   {
     return this.id;
   }
   
 
 
 
   public void setId(String id)
   {
     this.id = id;
   }
   
 
 
 
   public String getIdType()
   {
     return this.idType;
   }
   
 
 
 
   public void setIdType(String idType)
   {
     this.idType = idType;
   }
   
 
 
 
   public String getIssuer()
   {
     return this.issuer;
   }
   
 
 
 
   public void setIssuer(String issuer)
   {
     this.issuer = issuer;
   }
 }


/* Location:              C:\Users\qingzhizi\working\Tools\apache-tomcat-8.0.45\webapps\ICLFPS_BANK_SIMULATOR.war!\WEB-INF\classes\com\hkicl\fps\form\bean\EndUserIdBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */