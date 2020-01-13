package com.forms.batch.job.unit.iclfps.payment.notification;

import java.util.ArrayList;
import java.util.List;

import com.forms.ffp.core.define.FFPStatus;
import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.EntityManager;

public class PaymentInwardNotificationProcessor extends BatchBaseJob
{
	public void init()
	{
		
	}
	
	
	@Override
	public boolean execute() throws BatchJobException 
	{
		try
		{
			this.process();
			return true;
		}
		catch(Exception ex)
		{
			throw new BatchJobException(ex);
		}
	}

	
	public void process() throws BatchJobException
	{
		batchLogger.info("Started processor payment notification batch file data");
		
		//Connection con = null;
		
		try
		{
			String inset_select_sql = "INSERT INTO TB_DT_FPS_PAYMENT_NOTIFICATION "																			+
												   "(MSG_ID, MSG_CREATE_TS, NTFCTNID, NTFCTNCREATETS, NTFCTNACCTID, NTFCTNACCTTYPE, NTRYAMT, " 				+
												   "NTRYAMTCCY, NTRYCDTDBTIND, NTRYSTATUS, NTRYBANKTRANSCODE, NTRYDETAILENDTOENDID, NTRYDETAILTXID, " 		+
												   "NTRYDETAILMANDATEID, NTRYDETAILCLRSYSREF, NTRYDETAILAMT, NTRYDETAILAMTCCY, NTRYDETAILCDTDBTIND, " 		+
												   "NTRYDETAILCHRGSAMT, NTRYDETAILCHRGSAMTCCY, NTRYDETAILCHRGSBR, NTRYDETAILCHRGSAGTBIC, "			  		+ 
												   "NTRYDETAILCHRGSAGTMMBID, RLTDPTIESDBTRNAME, RLTDPTIESDBTRORGIDBIC, RLTDPTIESDBTRORGIDOTHRID," 			+
												   "RLTDPTIESDBTRORGIDOTHRSCHME, RLTDPTIESDBTRORGIDOTHRISSR, RLTDPTIESDBTRPRVTIDOTHRID, " 					+
												   "RLTDPTIESDBTRPRVTIDOTHRSCHME, RLTDPTIESDBTRPRVTIDOTHRISSR, RLTDPTIESDBTRCONTACTMOBILE, " 				+
												   "RLTDPTIESDBTRCONTACTEMAIL, RLTDPTIESDBTRACCTID, RLTDPTIESDBTRACCTSCHEME, RLTDPTIESCDTRNAME, " 			+
												   "RLTDPTIESCDTRORGIDBIC, RLTDPTIESCDTRORGIDOTHRID, RLTDPTIESCDTRORGIDOTHRSCHME, " 						+
												   "RLTDPTIESCDTRORGIDOTHRISSR, RLTDPTIESCDTRPRVTIDOTHRID, RLTDPTIESCDTRPRVTIDOTHRSCHME, " 					+
												   "RLTDPTIESCDTRPRVTIDOTHRISSR, RLTDPTIESCDTRCONTACTMOBILE, RLTDPTIESCDTRCONTACTEMAIL, " 					+ 
												   "RLTDPTIESCDTRACCTID, RLTDPTIESCDTRACCTSCHEME, RELATEDAGENTSDBTRBIC, RELATEDAGENTSDBTRMMBID, " 			+
												   "RELATEDAGENTSCDTRBIC, RELATEDAGENTSCDTRMMBID, PURPCODE, PURPOTHER, REMITINFUSTRD, " 					+ 
												   "RELATEDDATESINTRSETTLDATE, RELATEDDATESTRANSTS, RETURNINFRSN, RETURNINFMSG) " 							+ 
									  "SELECT "																												+
												   "MSG_ID, MSG_CREATE_TS, NOT_ID, NOT_CREATE_TS, NOT_ACCT_ID, "  											+
												   "NOT_ACCT_TYPE, NOT_ENTRY_AMT, NOT_ENTRY_CUR, "				  											+
												   "NOT_ENTRY_CDT_DBT_IND, NOT_ENTRY_STATUS, NOT_BANK_TX_CODE, "  											+
												   "NOT_ENTRY_DTL_END_TO_END_ID, NOT_ENTRY_DTL_TX_ID, " 													+
												   "NOT_ENTRY_DTL_MANT_ID, NOT_ENTRY_DTL_CLS_REF, NOT_ENTRY_DTL_AMT, " 										+ 
												   "NOT_ENTRY_DTL_CUR, NOT_ENTRY_DTL_CDT_DBT_IND, NOT_ENTRY_DTL_CHG_AMT, " 									+ 
												   "NOT_ENTRY_DTL_CHG_CUR, NOT_ENTRY_DTL_CHG_BEAR, NOT_ENTRY_DTL_CHG_AGT_BIC, " 							+ 
												   "NOT_ENTRY_DTL_CHG_AGT_ID, RLTD_PTIES_DBTR_NAME, RLTD_PTIES_DBTR_ORGID_BIC, " 							+
												   "RLTD_PTIES_DBTR_ORGID_OTH_ID, RLTD_PTIES_DBTR_ORGID_OTH_SCHME, RLTD_PTIES_DBTR_ORGID_OTH_ISS, " 		+ 
												   "RLTD_PTIES_DBTR_PRVTID_OTH_ID, RLTD_PTIES_DBTR_PRVTID_OTH_SCHME, RLTD_PTIES_DBTR_PRVTID_OTH_ISS, " 		+
												   "RLTD_PTIES_DBTR_CONT_MOBILE, RLTD_PTIES_DBTR_CONT_EMAIL, RLTD_PTIES_DBTR_ACCT_ID, " 					+
												   "RLTD_PTIES_DBTR_ACCT_SCHME, RLTD_PTIES_CDTR_NAME, RLTD_PTIES_CDTR_ORGID_BIC, " 							+
												   "RLTD_PTIES_CDTR_ORGID_OTH_ID, RLTD_PTIES_CDTR_ORGID_OTH_SCHME, RLTD_PTIES_CDTR_ORGID_OTH_ISS, " 		+
												   "RLTD_PTIES_CDTR_PRVTID_OTH_ID, RLTD_PTIES_CDTR_PRVTID_OTH_SCHME, RLTD_PTIES_CDTR_PRVTID_OTH_ISS, " 		+
												   "RLTD_PTIES_CDTR_CONT_MOBILE, RLTD_PTIES_CDTR_CONT_EMAIL, RLTD_PTIES_CDTR_ACCT_ID, " 					+
												   "RLTD_PTIES_CDTR_ACCT_SCHME, RLTD_DBTR_AGT_BIC, RLTD_DBTR_AGT_ID, RLTD_CDTR_AGT_BIC, " 					+
												   "RLTD_CDTR_AGT_ID, PURP_CODE, PURP_OTHER, REMI_INFO, RLTD_INTER_SETTLE_DATE, RLTD_TX_DATE_TIME, " 		+
												   "RETURN_INFO_CODE, RETURN_INFO_RSN " 																	+
									  "FROM TB_BH_INWARD_NOT_FPSPYCI "																						+
									  "WHERE STATUS = ? ";
			
			List<Object> params = new ArrayList<Object>();
			params.add(FFPStatus.TEMP_NOT_STATUS.INWARD_INITATE.getCode());
			
			int res = EntityManager.update(inset_select_sql, params.toArray());
			batchLogger.info(String.format("Process payment notification count[%s]", res));
			if(res > 0)
			{
				params.clear();
				params.add(FFPStatus.TEMP_NOT_STATUS.INWARD_FINISH.getCode());
				params.add(FFPStatus.TEMP_NOT_STATUS.INWARD_INITATE.getCode());
				EntityManager.update("UPDATE TB_BH_INWARD_NOT_FPSPYCI SET STATUS = ? WHERE STATUS = ? AND NOT_ID IN (SELECT NTFCTNID FROM TB_DT_FPS_PAYMENT_NOTIFICATION)", params.toArray());
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			batchLogger.error("Process payment notification file data error.", ex);
			throw new BatchJobException(ex);
		}
	}
}
