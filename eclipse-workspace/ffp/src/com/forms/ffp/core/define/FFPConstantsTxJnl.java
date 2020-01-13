package com.forms.ffp.core.define;

public class FFPConstantsTxJnl
{
	public enum TX_CODE
	{
		TX_CODE_P100, TX_CODE_P110,
		TX_CODE_P200, TX_CODE_P210,
		TX_CODE_P300,
		TX_CODE_I100, TX_CODE_I110,
		TX_CODE_M100,
		TX_CODE_A100, TX_CODE_A110,
		TX_CODE_A120,
		TX_CODE_A200
		;

		public String getCode()
		{
			switch (this)
			{
			case TX_CODE_P100:
				return "P100";
			case TX_CODE_P110:
				return "P110";
			case TX_CODE_P200:
				return "P200";
			case TX_CODE_I110:
				return "I110";
			case TX_CODE_P210:
				return "P210";
			case TX_CODE_P300:
				return "P300";
			case TX_CODE_A100:
				return "A100";
			case TX_CODE_A110:
				return "A110";
			case TX_CODE_A120:
				return "A120";
			case TX_CODE_M100:
				return "M100";
			case TX_CODE_I100:
				return "I100";
			case TX_CODE_A200:
				return "A200";
			default:
				return "";
			}
		}
	}

	/**
	 * TB_TX_JNL STATUS
	 * 
	 * @author admin
	 *
	 */
	public enum TX_STATUS
	{

		TX_STAT_CREAT, TX_STAT_PDNG, TX_STAT_APPST,
		TX_STAT_FPS_REJCT, TX_STAT_SRJCT, TX_STAT_AGENTREJCT,
		TX_STAT_CANCEL,
		TX_STAT_COMPL, TX_STAT_RETURN, TX_STAT_REFUND,
		TX_STAT_TMOUT,  
		TX_STAT_ERROR,TX_STAT_ACSC,TX_STAT_ACSP, TX_STAT_REJCT,
		TX_STAT_ACCP;

		public String getStatus()
		{
			switch (this)
			{
			case TX_STAT_CREAT:
				return "CREAT";
			case TX_STAT_APPST:
				return "APPST";
			case TX_STAT_CANCEL:
				return "CANCEL";
			case TX_STAT_AGENTREJCT:
				return "REJCT";
			case TX_STAT_COMPL:
				return "COMPL";
			case TX_STAT_TMOUT:
				return "TMOUT";
			case TX_STAT_ERROR:
				return "ERROR";
			case TX_STAT_ACSC:
				return "ACSC";
			case TX_STAT_ACSP:
				return "ACSP";
			case TX_STAT_ACCP:
				return "ACCP";
			case TX_STAT_PDNG:
				return "PDNG";
			case TX_STAT_FPS_REJCT:
				return "FRJCT";
			case TX_STAT_RETURN:
				return "RETURN";
			case TX_STAT_REFUND:
				return "REFUND";
			case TX_STAT_SRJCT:
				return "SRJCT";
				
			case TX_STAT_REJCT:
			 return "REJCT";
			default:
				return "";
			}
		}
	}

	/**
	 * TB_TX_JNLACTION STATUS
	 * 
	 * @author admin
	 *
	 */
	public enum MSG_STATUS
	{

		MSG_STAT_CREAT, MSG_STAT_APPST, MSG_STAT_TMOUT, MSG_STAT_REJCT, MSG_STAT_MSYNC;

		public String getStatus()
		{
			switch (this)
			{
			case MSG_STAT_CREAT:
				return "CREAT";
			case MSG_STAT_APPST:
				return "APPST";
			case MSG_STAT_TMOUT:
				return "TMOUT";
			case MSG_STAT_REJCT:
				return "REJCT";
			case MSG_STAT_MSYNC:
				return "MSYNC";
			default:
				return "";
			}
		}
	}

}
