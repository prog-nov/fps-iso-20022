/*    */ package com.forms.ffp.msg.iclfps.bussiness;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CreateMessageFormContextBean
/*    */   extends FFPMsgFormContextBean
/*    */ {
/*    */   private String frMmbId;
/*    */   private String frMmbBic;
/*    */   private Map<String, Integer> countMap;
/*    */   
/*    */   public String getFrMmbId()
/*    */   {
/* 33 */     return this.frMmbId;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void setFrMmbId(String frMmbId)
/*    */   {
/* 40 */     this.frMmbId = frMmbId;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getFrMmbBic()
/*    */   {
/* 47 */     return this.frMmbBic;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void setFrMmbBic(String frMmbBic)
/*    */   {
/* 54 */     this.frMmbBic = frMmbBic;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Map<String, Integer> getCountMap()
/*    */   {
/* 61 */     return this.countMap;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void setCountMap(Map<String, Integer> countMap)
/*    */   {
/* 68 */     this.countMap = countMap;
/*    */   }
/*    */   
/*    */   public Map<String, Integer> getBeanCountMap()
/*    */   {
/* 73 */     Map<String, Integer> beanCountMap = new HashMap();
/*    */     
/* 75 */     for (String key : this.countMap.keySet()) {
/* 76 */       int count = 0;
/*    */       
/* 78 */       if ((getFormBeanMap() != null) && (getFormBeanMap().containsKey(key))) {
/* 79 */         BaseFormBean bean = (BaseFormBean)getFormBeanMap().get(key);
/* 80 */         if (bean != null) {
/* 81 */           count = bean.getCount();
/*    */         }
/*    */       }
/*    */       
/* 85 */       beanCountMap.put(key, Integer.valueOf(count));
/*    */     }
/*    */     
/*    */ 
/* 89 */     return beanCountMap;
/*    */   }
/*    */ }


/* Location:              C:\Users\qingzhizi\working\Tools\apache-tomcat-8.0.45\webapps\ICLFPS_BANK_SIMULATOR.war!\WEB-INF\classes\com\hkicl\fps\form\bean\CreateMessageFormContextBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */