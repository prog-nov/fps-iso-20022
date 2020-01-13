package com.forms.ffp.persistents.service.addressing;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.ffp.persistents.bean.FFPJbBase;
import com.forms.ffp.persistents.bean.addressing.FFPJbA100;
import com.forms.ffp.persistents.bean.addressing.FFPJbA110;
import com.forms.ffp.persistents.bean.addressing.FFPJbA110dtl;
import com.forms.ffp.persistents.bean.addressing.FFPJbA120dtl;
import com.forms.ffp.persistents.dao.addressing.FFPIDao_A100;
import com.forms.ffp.persistents.dao.addressing.FFPIDao_A110;
import com.forms.ffp.persistents.dao.addressing.FFPIDao_A120dtl;

@Service("FFPDaoService_A120dtl")
public class FFPDaoService_A120dtl implements FFPIDaoService_A120dtl
{
	
	@Resource(name="FFPIDao_A120dtl")
	private FFPIDao_A120dtl dao;
	
	@Override
	public int sInsert(List<FFPJbA120dtl> form) throws Exception {
		int rs = dao.INSERTLIST_TB_TX_A120DAT_DTL(form);
		return rs;
	}

	@Override
	public int updateJnlStat(FFPJbBase form) throws Exception {
		int[] rs = dao.updateJnlStat(form);
        return 0;
	}

	@Override
	public List<FFPJbA120dtl> inqueryAllDtlFrA120Dtl(String jnlNo) {
		
		return dao.inqueryAddress_A120Dtl(jnlNo);
	}
	
	
}
