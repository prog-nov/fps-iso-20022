package com.forms.ffp.core.define;

public class FFPConstantsMsg
{
	public static final String RESP_MSG_STS_SUCCESS = "COMPL";
	public static final String RESP_MSG_STS_TMOUT = "TMOUT";
	
	public static final String RESP_MSG_REJCODE_ACCOUNT_ERROR = "001";
	public static final String RESP_MSG_REJCODE_TX_CANCEL = "002";
	
	public enum RESP_MSG{
		
		RESP_TO_AGENT_ERROR, RESP_TO_AGENT_SUCCESS;
		
		public String getMsg(){
			switch (this) {
			case RESP_TO_AGENT_ERROR:
				return "Error Msg";
			case RESP_TO_AGENT_SUCCESS:
				return "ACK";
			}
			throw new RuntimeException(String.format("RESP_MSG[%s] IS NOT VALID", this.name()));
		}
	}
	
}
