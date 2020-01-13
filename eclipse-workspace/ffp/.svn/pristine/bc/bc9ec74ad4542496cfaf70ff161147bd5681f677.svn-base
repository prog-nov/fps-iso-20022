package com.forms.ffp.persistents.dao.dt;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.ffp.persistents.bean.dt.FFPDtECert;

@Repository("FFPIDao_ECert")
public interface FFPIDao_ECert
{
	public List<FFPDtECert> dFindValidECert(@Param("systemId") String systemId, @Param("eCertKey") String eCertKey);
	
	public int dUpdate(FFPDtECert eCert);
	
	public int dUpdateByEcertKey(@Param("systemId") String systemId, @Param("eCertKey") String eCertKey, @Param("RevokedTs") Date RevokedTs);
	
	public int dInsert(FFPDtECert eCert);
}
