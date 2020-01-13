 package com.forms.ffp.msg.iclfps.bussiness;
 
 import java.util.Date;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class MessageRejectBean
   extends BaseTemplateFormBean
 {
   private String reference;
   private String reason;
   private Date rejectDate;
   private String reasonDesc;
   private String additionalInformation;
   
   public String getReference()
   {
     return this.reference;
   }
   
 
 
 
 
   public void setReference(String reference)
   {
     this.reference = reference;
   }
   
 
 
 
 
   public String getReason()
   {
     return this.reason;
   }
   
 
 
 
 
   public void setReason(String reason)
   {
     this.reason = reason;
   }
   
 
 
 
 
   public Date getRejectDate()
   {
     return this.rejectDate;
   }
   
 
 
 
 
   public void setRejectDate(Date rejectDate)
   {
     this.rejectDate = rejectDate;
   }
   
 
 
 
 
   public String getReasonDesc()
   {
     return this.reasonDesc;
   }
   
 
 
 
 
   public void setReasonDesc(String reasonDesc)
   {
     this.reasonDesc = reasonDesc;
   }
   
 
 
 
 
   public String getAdditionalInformation()
   {
     return this.additionalInformation;
   }
   
 
 
 
 
   public void setAdditionalInformation(String additionalInformation)
   {
     this.additionalInformation = additionalInformation;
   }
   
 
 
 
 
 
 
   public int getCount()
   {
     return 0;
   }
 }


/* Location:              C:\Users\qingzhizi\working\Tools\apache-tomcat-8.0.45\webapps\ICLFPS_BANK_SIMULATOR.war!\WEB-INF\classes\com\hkicl\fps\form\bean\MessageRejectBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */