package com.forms.ffp.bussiness.iclfps.fpscel001;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.forms.ffp.adaptor.jaxb.iclfps.fps_cel_001_001_01.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_cel_001_001_01.RevokedCertificate;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.persistents.bean.FFPTxBase;
import com.forms.ffp.persistents.dao.dt.FFPIDao_ECert;

@Component("ICL.fps.cel.001.001.01")
@Scope("prototype")
public class FFPTxFpscel001 extends FFPTxBase
{
	private Logger logger = LoggerFactory.getLogger(FFPTxFpscel001.class);
	
	@Resource(name = "FFPIDao_ECert")
	private FFPIDao_ECert ecertDao;

	@Override
	public void perform() throws Exception
	{
		FFPVOFpsCel001 cel = (FFPVOFpsCel001) txVo;
		for(RevokedCertificate ecert : cel.getRevokedEcertList())
		{
			String key = Base64Utils.encodeToString(ecert.getECert());
			int result = ecertDao.dUpdateByEcertKey(FFPConstants.RELATION_SYSTEM_HKICL, key, ecert.getRevocationDate().toGregorianCalendar().getTime());
			if(result <= 0)
			{
				logger.warn("FFPTxFpscel001:ECERT(" + key + " NOT IN FFP DB!");
			}
		}
	}

	@Override
	public boolean validate() throws Exception
	{
		FFPVOFpsCel001 cel = (FFPVOFpsCel001) txVo;
		if (cel.getTotalRevokedCert() <= 0)
			return false;
		if(cel.getRevokedEcertList() == null || cel.getRevokedEcertList().size() == 0)
			return false;
		return true;

	}

	@Override
	public void parseISO20022BizData(ISO20022BusinessDataV01 bizData) throws Exception
	{
		txVo = new FFPVOFpsCel001();
		FFPVOFpsCel001 cel = (FFPVOFpsCel001) txVo;

		parseISO20022BizDataHead(bizData);
		Document doc = (Document) bizData.getContent().get(1).getValue();
		cel.setMsgId(doc.getCertNoti().getGrpHdr().getMsgId());
		cel.setCreDtTm(doc.getCertNoti().getGrpHdr().getCreDtTm().toGregorianCalendar().getTime());
		cel.setTotalRevokedCert(Integer.valueOf(doc.getCertNoti().getGrpHdr().getTotalRevokedCert()));
		if (doc.getCertNoti().getRevokedCerts() != null)
		{
			cel.setRevokedEcertList(doc.getCertNoti().getRevokedCerts().getRevokedCert());
		}
	}
}
