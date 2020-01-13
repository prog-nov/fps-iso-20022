package com.forms.ffp.bussiness.iclfps.camt056;

import java.util.Date;
import java.util.List;

import com.forms.ffp.bussiness.FFPVOBase;
import com.forms.ffp.persistents.bean.payment.credittransfer.FFPJbP110;

public class FFPVOCamt056 extends FFPVOBase
{

	private String id;
	private String assgnrMmBid;
	private String assgneMmBid;
	private Date creDtTm;

	private String orgnlInstrId;
	private String orgnlEndToEndId;
	private String orgnlTxId;
	private String orgnlClrSysRef;
	private String cxlRsnInfPrtry;
	private List<String> addtlInf;

	private FFPJbP110 p110;

	public FFPJbP110 getP110() {
		return p110;
	}

	public void setP110(FFPJbP110 p110) {
		this.p110 = p110;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getAssgnrMmBid()
	{
		return assgnrMmBid;
	}

	public void setAssgnrMmBid(String assgnrMmBid)
	{
		this.assgnrMmBid = assgnrMmBid;
	}

	public String getAssgneMmBid()
	{
		return assgneMmBid;
	}

	public void setAssgneMmBid(String assgneMmBid)
	{
		this.assgneMmBid = assgneMmBid;
	}

	public Date getCreDtTm()
	{
		return creDtTm;
	}

	public void setCreDtTm(Date creDtTm)
	{
		this.creDtTm = creDtTm;
	}

	public List<String> getAddtlInf()
	{
		return addtlInf;
	}

	public void setAddtlInf(List<String> addtlInf)
	{
		this.addtlInf = addtlInf;
	}
	
	public String getOrgnlInstrId()
	{
		return orgnlInstrId;
	}

	public void setOrgnlInstrId(String orgnlInstrId)
	{
		this.orgnlInstrId = orgnlInstrId;
	}

	public String getOrgnlEndToEndId()
	{
		return orgnlEndToEndId;
	}

	public void setOrgnlEndToEndId(String orgnlEndToEndId)
	{
		this.orgnlEndToEndId = orgnlEndToEndId;
	}

	public String getOrgnlTxId()
	{
		return orgnlTxId;
	}

	public void setOrgnlTxId(String orgnlTxId)
	{
		this.orgnlTxId = orgnlTxId;
	}

	public String getOrgnlClrSysRef()
	{
		return orgnlClrSysRef;
	}

	public void setOrgnlClrSysRef(String orgnlClrSysRef)
	{
		this.orgnlClrSysRef = orgnlClrSysRef;
	}

	public String getCxlRsnInfPrtry()
	{
		return cxlRsnInfPrtry;
	}

	public void setCxlRsnInfPrtry(String cxlRsnInfPrtry)
	{
		this.cxlRsnInfPrtry = cxlRsnInfPrtry;
	}
}
