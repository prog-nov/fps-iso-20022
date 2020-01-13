 package com.forms.ffp.msg.iclfps.bussiness;
 
 import com.hkicl.fps.data.validation.Validating;
 import com.hkicl.fps.data.validation.ValidationMessage;
 import java.util.List;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class StatusReportBean
   extends BaseTemplateFormBean
 {
   @FFPValidating
   private List<TransactionInfo> transactionInfo;
   @FFPValidateMessage(field="Original Message ID")
   private String oMessageId;
   @FFPValidateMessage(field="Original Message Definition")
   private String oMessageNmId;
   
   public List<TransactionInfo> getTransactionInfo()
   {
     return this.transactionInfo;
   }
   
 
 
 
   public void setTransactionInfo(List<TransactionInfo> transactionInfo)
   {
     this.transactionInfo = transactionInfo;
   }
   
 
 
 
   public String getoMessageId()
   {
     return this.oMessageId;
   }
   
 
 
 
   public void setoMessageId(String oMessageId)
   {
     this.oMessageId = oMessageId;
   }
   
 
 
 
   public String getoMessageNmId()
   {
     return this.oMessageNmId;
   }
   
 
 
 
   public void setoMessageNmId(String oMessageNmId)
   {
     this.oMessageNmId = oMessageNmId;
   }
   
 
 
 
 
   public int getCount()
   {
     int count = 0;
     if (this.transactionInfo != null) { count += this.transactionInfo.size();
     }
     return count;
   }
 }


/* Location:              C:\Users\qingzhizi\working\Tools\apache-tomcat-8.0.45\webapps\ICLFPS_BANK_SIMULATOR.war!\WEB-INF\classes\com\hkicl\fps\form\bean\StatusReportBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */