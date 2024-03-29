package com.forms.batch.job.unit.iclfps.payment.certificate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.forms.ffp.core.define.FFPConstants;
import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.persistence.EntityManager;

public class FPSInitCertificateProcessor extends BatchBaseJob
{

	public void init()
	{
		
	}
	
	@Override
	public boolean execute() throws BatchJobException 
	{
		try 
		{
			this.processor();
			return true;
		} 
		catch (Exception ex)
		{
			throw new BatchJobException(ex);
		}
	}

	
	public void processor() throws Exception
	{
		batchLogger.info("Started processor FPS certificate list batch file data");
		
		Connection con = null;
		String query_sql = "SELECT ID, ECERT_KEY, EFFECTIVE_DATE, EXPIRY_DATE FROM TB_BH_INIT_ECERT WHERE STATUS = '%s'";
		String insert_sql = "INSERT INTO TB_DT_ECERT(SYSTEM_ID, ECERT_KEY, EFFECTIVE_DATE, EXPIRY_DATE) VALUES(?,?,?,?)";
		String update_sql = "UPDATE TB_BH_INIT_ECERT SET STATUS = ? WHERE STATUS = ? AND ID = ?";
		
		try
		{
			List<Object[]> res_list = EntityManager.queryArrayList(String.format(query_sql, "I"));
			if(res_list == null)
			{
				batchLogger.info("Not find invalid FPS payment certs");
				return;
			}
			con = ConnectionManager.getInstance().getConnection();
			
			List<Object> insParam = new ArrayList<Object>();
			List<Object> updParam = new ArrayList<Object>();
			for(Object[] objArr : res_list)
			{
				int id = (int)objArr[0];//Not be null
				String ecert = (String)objArr[1];
				Date effDate = (Date)objArr[2];
				Date expDate = (Date)objArr[3];
				
				insParam.add(FFPConstants.RELATION_SYSTEM_HKICL);
				insParam.add(ecert);
				insParam.add(effDate);
				insParam.add(expDate);
				
				updParam.add("F");
				updParam.add("I");
				updParam.add(id);
				
				con.setAutoCommit(false);
				
				EntityManager.update(con, insert_sql, insParam.toArray());
				EntityManager.update(con, update_sql, updParam.toArray());
				
				con.commit();
				con.setAutoCommit(true);
				
				insParam.clear();
				updParam.clear();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(con != null)
				con.rollback();
			batchLogger.error("Process inward file error", ex);
			throw new BatchJobException(ex);
		}
		finally
		{
			if(con != null)
				con.close();
		}
	}
}
