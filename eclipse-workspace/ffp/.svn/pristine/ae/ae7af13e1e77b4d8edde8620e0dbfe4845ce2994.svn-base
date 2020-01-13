package com.forms.ffp.core.define;

public class FFPStatus 
{
	/**
	 * Specify status for transaction
	 */
	/*public enum P100_STATUS
	{
		INITATE, SUCESS, REJECT, TENDING;
		
		public String getCode()
		{
			switch(this)
			{
				case INITATE:
					return "I";
				case SUCESS:
					return "S";
				case REJECT:
					return "R";
				case TENDING:
					return "T";	
			}
			throw new RuntimeException(String.format("STATUS[%s] IS NOT VALID", this.name()));
		}
	}*/
	public enum TEMP_NOT_STATUS
	{
		INWARD_INITATE, INWARD_ERROR, INWARD_FINISH;
		
		public String getCode()
		{
			switch(this)
			{
				case INWARD_INITATE:
					return "I";
				case INWARD_ERROR:
					return "E";
				case INWARD_FINISH:
					return "F";
			}
			throw new RuntimeException(String.format("STATUS[%s] IS NOT VALID", this.name()));
		}
	}
	
	public enum TEMP_RETURN_REFUND_STATUS
	{
		INWARD_INITATE, INWARD_ERROR, INWARD_FINISH;
		
		public String getCode()
		{
			switch(this)
			{
				case INWARD_INITATE:
					return "I";
				case INWARD_ERROR:
					return "E";
				case INWARD_FINISH:
					return "F";
			}
			throw new RuntimeException(String.format("STATUS[%s] IS NOT VALID", this.name()));
		}
	}
	
	public enum TEMP_CREDIT_STATUS
	{
		INWARD_INITATE, INWARD_ERROR, INWARD_FINISH;
		
		public String getCode()
		{
			switch(this)
			{
				case INWARD_INITATE:
					return "I";
				case INWARD_ERROR:
					return "E";
				case INWARD_FINISH:
					return "F";
			}
			throw new RuntimeException(String.format("STATUS[%s] IS NOT VALID", this.name()));
		}
	}
	
	//Transaction type
	public enum TX_TYPE
	{
		DIRECT_DEBIT_IN, DIRECT_DEBIT_OUT, CREDIT_TRANSFER_OUT, CREDIT_TRANSFER_IN;
		
		public String getCode()
		{
			switch(this)
			{
				case DIRECT_DEBIT_IN:
					return "DBI";
				case DIRECT_DEBIT_OUT:
					return "DBO";
				case CREDIT_TRANSFER_OUT:
					return "CTO";
				case CREDIT_TRANSFER_IN:
					return "CTI";	
			}
			throw new RuntimeException(String.format("TRANSACTION TYPE[%s] IS NOT VALID", this.name()));
		}
	}
	
	//Group Status : for outward response file(pacs002)
	public enum GROUP_STATUS
	{
		PARTIALLY_ACCEPTED, ALL_REJECTED, ALL_ACCEPTED_SETTLEMENT_COMPLETED;
		public String getCode()
		{
			switch(this)
			{
				case PARTIALLY_ACCEPTED:
					return "PART";
				case ALL_REJECTED:
					return "RJCT";
				case ALL_ACCEPTED_SETTLEMENT_COMPLETED:
					return "ACSC";
			}
			throw new RuntimeException(String.format("GROUP STATUS[%s] IS NOT VALID", this.name()));
		}
	}
}
