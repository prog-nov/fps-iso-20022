package com.forms.producebean.com.ffp.dao;

import java.util.Date;

/**
 * Copy Right Information : Forms Syntron <br> 
 * Project : zhangying <br> 
 * Description : tb_msg_jnl_action  <br> 
 * Author : zhangying <br> 
 * Version : 1.0.0 <br> 
 * Since : 1.0.0 <br> 
 * Date : 2018-2-7 <br> 
 */
public class TbMsgJnlAction {
    /**
     * tb_msg_jnl_action.MSG_JNL 
     */
    private int msgJnl;

    /**
     * tb_msg_jnl_action.MSG_TRAN_ID 
     */
    private String msgTranId;

    /**
     * tb_msg_jnl_action.MSG_ID 
     */
    private String msgId;

    /**
     * tb_msg_jnl_action.ACTION_TYPE 
     */
    private String actionType;

    /**
     * tb_msg_jnl_action.END_TO_END_ID 
     */
    private String endToEndId;

    /**
     * tb_msg_jnl_action.ACTION_TRANS_STATUS 
     */
    private String actionTransStatus;

    /**
     * tb_msg_jnl_action.MSG_ACCE_DATE 
     */
    private Date msgAcceDate;

    /**
     * tb_msg_jnl_action.MSG_INST_DATE 
     */
    private Date msgInstDate;

    public int getMsgJnl() {
        return msgJnl;
    }

    public void setMsgJnl(int msgJnl) {
        this.msgJnl = msgJnl;
    }

    public String getMsgTranId() {
        return msgTranId;
    }

    public void setMsgTranId(String msgTranId) {
        this.msgTranId = msgTranId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getEndToEndId() {
        return endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public String getActionTransStatus() {
        return actionTransStatus;
    }

    public void setActionTransStatus(String actionTransStatus) {
        this.actionTransStatus = actionTransStatus;
    }

    public Date getMsgAcceDate() {
        return msgAcceDate;
    }

    public void setMsgAcceDate(Date msgAcceDate) {
        this.msgAcceDate = msgAcceDate;
    }

    public Date getMsgInstDate() {
        return msgInstDate;
    }

    public void setMsgInstDate(Date msgInstDate) {
        this.msgInstDate = msgInstDate;
    }
}