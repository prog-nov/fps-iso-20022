package com.forms.ffp.bussiness.iclfps.fpsadmi002;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.forms.ffp.adaptor.jaxb.iclfps.fps_admi_002_001_01.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.bussiness.utils.FFPJnlUtils;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsTxJnl.MSG_STATUS;
import com.forms.ffp.core.define.FFPConstantsTxJnl.TX_STATUS;
import com.forms.ffp.persistents.bean.FFPTxBase;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.FFPTxJnlAction;
import com.forms.ffp.persistents.bean.tx.m100.FFPJbM100;
import com.forms.ffp.persistents.dao.FFPIDao_TxJnl;
import com.forms.ffp.persistents.dao.ss.FFPIDao_System;
import com.forms.ffp.persistents.service.tx.m100.FFPDaoService_M100;

@Component("ICL.fps.admi.002.001.01")
@Scope("prototype")
public class FFPTxFPSAdmi002 extends FFPTxBase
{
	private static Logger _logger = LoggerFactory.getLogger(FFPTxFPSAdmi002.class);

	@Resource(name = "FFPDaoService_M100")
	private FFPDaoService_M100 m100Service;
	@Resource(name = "FFPIDao_TxJnl")
	private FFPIDao_TxJnl jnlDao;
	@Resource(name = "FFPIDao_System")
	private FFPIDao_System dao;

	@Override
	public void perform() throws Exception
	{
		if ("ICL.fps.admi.002.001.01".equals(this.serviceName))
		{
			FFPVo_FPSAdmi002 fpsadmi002 = (FFPVo_FPSAdmi002) txVo;
			FFPJbM100 m100 = m100Service.inquiryM100ByJnlNoOrMsgId(null, fpsadmi002.getOrgnlMsgId());
			if (m100 != null)
			{
				FFPTxJnl txJnl = jnlDao.inquiryByJnlNo(m100.getJnlNo());
				Date loc_date = new Date();
				txJnl.setLastUpdateTs(loc_date);
				m100.setTxJnl(txJnl);
				
				FFPTxJnlAction jnlAction = FFPJnlUtils.getInstance().newJnlAction(m100.getJnlNo(), fpsadmi002.getMsgId(), FFPConstants.MSG_DIRECTION_INWARD,
						FFPConstants.RELATION_SYSTEM_HKICL, fpsadmi002.getMsgDefIdr(), MSG_STATUS.MSG_STAT_MSYNC.getStatus(), fpsadmi002.getCreateDate(), loc_date, loc_date,
						fpsadmi002.getOrgnlMsgId());
				m100.getJnlActionList().add(jnlAction);
				
				m100.setAccpStatus(String.valueOf(fpsadmi002.getAccptd()));
				boolean Accepted = Boolean.valueOf(fpsadmi002.getAccptd());
				if(Accepted)
				{
					txJnl.setTxStat(TX_STATUS.TX_STAT_COMPL.getStatus());
					
				}
				else
				{
					m100.setRjctCd(fpsadmi002.getRejCd());
					if("MOL20003".equals(m100.getRjctCd()))
					{
						txJnl.setTxStat(TX_STATUS.TX_STAT_COMPL.getStatus());
					}
					else
					{
						txJnl.setTxStat(TX_STATUS.TX_STAT_FPS_REJCT.getStatus());
					}
				}
				m100Service.update(m100);
				if (m100.getRcptMd().equals(fpsadmi002.getRcptMd()))
				{
					dao.updateFpsReceiveMode(fpsadmi002.getRcptMd());
					_logger.info("switch receive mode successfully at " + fpsadmi002.getSwtchgTm());
				} else
				{
					_logger.warn("switch receive mode failure at " + fpsadmi002.getSwtchgTm());
				}
			} else
			{
				_logger.error("CAN NOT GET DATA FROM TB_TX_M100 FROM OrgnlMsgId: " + fpsadmi002.getOrgnlMsgId());
			}
		}
	}

	@Override
	public boolean validate() throws Exception
	{
		return true;
	}

	public void parseISO20022BizData(ISO20022BusinessDataV01 bizData) throws Exception
	{
		if ("ICL.fps.admi.002.001.01".equals(this.serviceName))
		{
			txVo = new FFPVo_FPSAdmi002();
			parseISO20022BizDataHead(bizData);
			FFPVo_FPSAdmi002 loc_jb = (FFPVo_FPSAdmi002) txVo;
			Document doc = (Document) bizData.getContent().get(1).getValue();
			
			loc_jb.setMsgId(doc.getRcptMdSwtchgRpt().getGrpHdr().getMsgId());
			loc_jb.setOrgnlMsgId(doc.getRcptMdSwtchgRpt().getOrgnlGrpHdr().getMsgId());
			
			loc_jb.setAccptd(doc.getRcptMdSwtchgRpt().getRcptMdRslt().isAccptd());
			
			if (doc.getRcptMdSwtchgRpt().getRcptMdRslt().getRjctRsn() != null)
			{
				loc_jb.setRejCd(doc.getRcptMdSwtchgRpt().getRcptMdRslt().getRjctRsn().getCd());
			}
			if (doc.getRcptMdSwtchgRpt().getRcptMdRslt().getRcptMd() != null)
			{
				loc_jb.setRcptMd(doc.getRcptMdSwtchgRpt().getRcptMdRslt().getRcptMd().value());
			}
			if (doc.getRcptMdSwtchgRpt().getRcptMdRslt().getSwtchgTm() != null)
			{
				loc_jb.setSwtchgTm(doc.getRcptMdSwtchgRpt().getRcptMdRslt().getSwtchgTm().toGregorianCalendar().getTime());
			}
		}
	}
}
