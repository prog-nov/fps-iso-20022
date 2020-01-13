 package com.forms.ffp.msg.iclfps.bussiness;
 
 import com.hkicl.fps.data.validation.ValidatePattern;
 import com.hkicl.fps.data.validation.Validating;
 import com.hkicl.fps.data.validation.ValidationMessage;
 import java.util.ArrayList;
 import java.util.List;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class EndUserDetailBean
 {
   @FFPValidatePattern(pattern={"[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}"})
   @FFPValidateMessage(field="Organisation BIC")
   private String organisationBic;
   @FFPValidating
   private List<EndUserIdBean> id;
   private Integer idSize;
   @FFPValidatePattern(pattern={"\\+[0-9]{1,3}-[0-9()+\\-]{1,30}"})
   @FFPValidateMessage(field="Mobile Number")
   private String mobileNumber;
   @FFPValidateMessage(field="Email Address")
   private String emailAddress;
   
   public EndUserDetailBean()
   {
     this.idSize = Integer.valueOf(1);
   }
   
   public EndUserDetailBean(int idSize) {
     this.idSize = Integer.valueOf(idSize);
   }
   
 
 
 
   public String getOrganisationBic()
   {
     return this.organisationBic;
   }
   
 
 
 
   public void setOrganisationBic(String organisationBic)
   {
     this.organisationBic = organisationBic;
   }
   
 
 
 
   public List<EndUserIdBean> getId()
   {
     if (this.id == null) {
       this.id = new ArrayList();
     }
     
     if (this.id.size() < this.idSize.intValue()) {
       for (int i = this.id.size(); i < this.idSize.intValue(); i++) {
         this.id.add(new EndUserIdBean());
       }
     }
     
     return this.id;
   }
   
 
 
 
   public void setId(List<EndUserIdBean> id)
   {
     this.id = id;
   }
   
 
 
 
   public Integer getIdSize()
   {
     return this.idSize;
   }
   
 
 
 
   public void setIdSize(Integer idSize)
   {
     this.idSize = idSize;
   }
   
 
 
 
   public String getMobileNumber()
   {
     return this.mobileNumber;
   }
   
 
 
 
   public void setMobileNumber(String mobileNumber)
   {
     this.mobileNumber = mobileNumber;
   }
   
 
 
 
   public String getEmailAddress()
   {
     return this.emailAddress;
   }
   
 
 
 
   public void setEmailAddress(String emailAddress)
   {
     this.emailAddress = emailAddress;
   }
 }


/* Location:              C:\Users\qingzhizi\working\Tools\apache-tomcat-8.0.45\webapps\ICLFPS_BANK_SIMULATOR.war!\WEB-INF\classes\com\hkicl\fps\form\bean\EndUserDetailBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */