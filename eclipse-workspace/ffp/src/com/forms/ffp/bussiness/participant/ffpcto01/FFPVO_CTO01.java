package com.forms.ffp.bussiness.participant.ffpcto01;

import com.forms.ffp.bussiness.FFPVOBase;
import com.forms.ffp.persistents.bean.payment.credittransfer.FFPJbP100;

public class FFPVO_CTO01 extends FFPVOBase
{
	private FFPJbP100 p100Jb;

	public FFPJbP100 getP100Jb()
	{
		return p100Jb;
	}

	public void setP100Jb(FFPJbP100 p100Jb)
	{
		this.p100Jb = p100Jb;
	}

}
