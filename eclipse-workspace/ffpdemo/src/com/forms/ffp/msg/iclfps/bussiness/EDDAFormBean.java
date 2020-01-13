 package com.forms.ffp.msg.iclfps.bussiness;
 
 import java.util.List;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class EDDAFormBean
   extends BaseFormBean
 {
   private List<MandateInitiationBean> mandateInitiationList;
   private List<FFPMandateAmendmentBean> mandateAmendmentList;
   private List<MandateCancellationBean> mandateCancellationList;
   private List<MandateSuspensionBean> mandateSuspensionList;
   private List<MandateAcceptanceBean> mandateAcceptanceList;
   
   public List<MandateInitiationBean> getMandateInitiationList()
   {
     return this.mandateInitiationList;
   }
   
 
 
 
 
   public void setMandateInitiationList(List<MandateInitiationBean> mandateInitiationList)
   {
     this.mandateInitiationList = mandateInitiationList;
   }
   
 
 
 
   public List<FFPMandateAmendmentBean> getMandateAmendmentList()
   {
     return this.mandateAmendmentList;
   }
   
 
 
 
 
   public void setMandateAmendmentList(List<FFPMandateAmendmentBean> mandateAmendmentList)
   {
     this.mandateAmendmentList = mandateAmendmentList;
   }
   
 
 
 
   public List<MandateCancellationBean> getMandateCancellationList()
   {
     return this.mandateCancellationList;
   }
   
 
 
 
 
   public void setMandateCancellationList(List<MandateCancellationBean> mandateCancellationList)
   {
     this.mandateCancellationList = mandateCancellationList;
   }
   
 
 
 
   public List<MandateSuspensionBean> getMandateSuspensionList()
   {
     return this.mandateSuspensionList;
   }
   
 
 
 
 
   public void setMandateSuspensionList(List<MandateSuspensionBean> mandateSuspensionList)
   {
     this.mandateSuspensionList = mandateSuspensionList;
   }
   
 
 
 
   public List<MandateAcceptanceBean> getMandateAcceptanceList()
   {
     return this.mandateAcceptanceList;
   }
   
 
 
 
 
   public void setMandateAcceptanceList(List<MandateAcceptanceBean> mandateAcceptanceList)
   {
     this.mandateAcceptanceList = mandateAcceptanceList;
   }
   
 
 
 
 
   public int getCount()
   {
     int count = 0;
     count += countBean(this.mandateInitiationList);
     count += countBean(this.mandateAmendmentList);
     count += countBean(this.mandateCancellationList);
     count += countBean(this.mandateSuspensionList);
     count += countBean(this.mandateAcceptanceList);
     return count;
   }
 }


/* Location:              C:\Users\qingzhizi\working\Tools\apache-tomcat-8.0.45\webapps\ICLFPS_BANK_SIMULATOR.war!\WEB-INF\classes\com\hkicl\fps\form\bean\EDDAFormBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */