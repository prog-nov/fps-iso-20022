package com.forms.ffp.bussiness.iclfps.pacs002;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.forms.ffp.persistents.bean.payment.credittransfer.FFPJbP100;
import com.forms.ffp.persistents.bean.payment.credittransfer.FFPJbP110;
import com.forms.ffp.persistents.bean.payment.directdebit.FFPJbP200;
import com.forms.ffp.persistents.bean.payment.directdebit.FFPJbP210;
import com.forms.ffp.persistents.bean.payment.returnrefund.FFPJbP300;

public class FFPVO_Pacs002_TxInfAndSts
{
	private String OrgnlEndToEndId;
	private String OrgnlTxId;
	private String TxSts;
	private String TxStsRsnCode;
	private List<String> TxStsAddtlInfList;
	private Date accptncDtTm;
	private String clrSysRef;
	private String settlementCurrency;
	private BigDecimal settlementAmount;
	private Date settlementDate;

	private FFPJbP100 p100Jb;
	
	private FFPJbP110 p110Jb;
	private FFPVO_Pacs002_CTI01REPLY cti01Reply;
	
	private FFPJbP200 p200Jb;
	private FFPVO_Pacs002_DDI01REPLY ddi01Reply;
	
	private FFPJbP210 p210Jb;
	
	private FFPJbP300 p300Jb;
	
	private int tempBatchId; //Just use for batch data
	
	public int getTempBatchId() {
		return tempBatchId;
	}

	public void setTempBatchId(int tempBatchId) {
		this.tempBatchId = tempBatchId;
	}

	public String getOrgnlEndToEndId()
	{
		return OrgnlEndToEndId;
	}

	public void setOrgnlEndToEndId(String orgnlEndToEndId)
	{
		OrgnlEndToEndId = orgnlEndToEndId;
	}

	public String getOrgnlTxId()
	{
		return OrgnlTxId;
	}

	public void setOrgnlTxId(String orgnlTxId)
	{
		OrgnlTxId = orgnlTxId;
	}

	public String getTxSts()
	{
		return TxSts;
	}

	public void setTxSts(String txSts)
	{
		TxSts = txSts;
	}

	public String getTxStsRsnCode()
	{
		return TxStsRsnCode;
	}

	public void setTxStsRsnCode(String txStsRsnCode)
	{
		TxStsRsnCode = txStsRsnCode;
	}

	public List<String> getTxStsAddtlInf()
	{
		return TxStsAddtlInfList;
	}

	public void setTxStsAddtlInf(List<String> txStsAddtlInfList)
	{
		TxStsAddtlInfList = txStsAddtlInfList;
	}

	public List<String> getTxStsAddtlInfList()
	{
		return TxStsAddtlInfList;
	}

	public void setTxStsAddtlInfList(List<String> txStsAddtlInfList)
	{
		TxStsAddtlInfList = txStsAddtlInfList;
	}

	public Date getAccptncDtTm()
	{
		return accptncDtTm;
	}

	public void setAccptncDtTm(Date accptncDtTm)
	{
		this.accptncDtTm = accptncDtTm;
	}

	public String getClrSysRef()
	{
		return clrSysRef;
	}

	public void setClrSysRef(String clrSysRef)
	{
		this.clrSysRef = clrSysRef;
	}

	public String getSettlementCurrency()
	{
		return settlementCurrency;
	}

	public void setSettlementCurrency(String settlementCurrency)
	{
		this.settlementCurrency = settlementCurrency;
	}

	public BigDecimal getSettlementAmount()
	{
		return settlementAmount;
	}

	public void setSettlementAmount(BigDecimal settlementAmount)
	{
		this.settlementAmount = settlementAmount;
	}

	public FFPJbP100 getP100Jb()
	{
		return p100Jb;
	}

	public void setP100Jb(FFPJbP100 p100Jb)
	{
		this.p100Jb = p100Jb;
	}
	
	public FFPJbP110 getP110Jb()
	{
		return p110Jb;
	}

	public void setP110Jb(FFPJbP110 p110Jb)
	{
		this.p110Jb = p110Jb;
	}
	
	public FFPJbP200 getP200Jb()
	{
		return p200Jb;
	}

	public void setP200Jb(FFPJbP200 p200Jb)
	{
		this.p200Jb = p200Jb;
	}
	
	public FFPJbP210 getP210Jb()
	{
		return p210Jb;
	}

	public void setP210Jb(FFPJbP210 p210Jb)
	{
		this.p210Jb = p210Jb;
	}
	
	
	public FFPJbP300 getP300Jb() {
		return p300Jb;
	}

	public void setP300Jb(FFPJbP300 p300Jb) {
		this.p300Jb = p300Jb;
	}

	public FFPVO_Pacs002_CTI01REPLY getCti01Reply()
	{
		return cti01Reply;
	}

	public void setCti01Reply(FFPVO_Pacs002_CTI01REPLY cti01Reply)
	{
		this.cti01Reply = cti01Reply;
	}
	
	public FFPVO_Pacs002_DDI01REPLY getDdi01Reply()
	{
		return ddi01Reply;
	}

	public void setDdi01Reply(FFPVO_Pacs002_DDI01REPLY ddi01Reply)
	{
		this.ddi01Reply = ddi01Reply;
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

}
