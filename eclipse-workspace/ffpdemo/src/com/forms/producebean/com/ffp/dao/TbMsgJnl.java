package com.forms.producebean.com.ffp.dao;

import java.util.Date;

/**
 * Copy Right Information : Forms Syntron <br> 
 * Project : zhangying <br> 
 * Description : tb_msg_jnl  <br> 
 * Author : zhangying <br> 
 * Version : 1.0.0 <br> 
 * Since : 1.0.0 <br> 
 * Date : 2018-2-7 <br> 
 */
public class TbMsgJnl {
    /**
     * tb_msg_jnl.MSG_JNL 
     */
    private int msgJnl;

    /**
     * tb_msg_jnl.MSG_TRAN_ID 
     */
    private String msgTranId;

    /**
     * tb_msg_jnl.END_TO_END_ID 
     */
    private String endToEndId;

    /**
     * tb_msg_jnl.MSG_REASON 
     */
    private String msgReason;

    /**
     * tb_msg_jnl.MSG_TRANS_STATUS 
     */
    private String msgTransStatus;

    /**
     * tb_msg_jnl.MSG_INST_DATE 
     */
    private Date msgInstDate;

    /**
     * tb_msg_jnl.MSG_ACCE_DATE 
     */
    private Date msgAcceDate;

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

    public String getEndToEndId() {
        return endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public String getMsgReason() {
        return msgReason;
    }

    public void setMsgReason(String msgReason) {
        this.msgReason = msgReason;
    }

    public String getMsgTransStatus() {
        return msgTransStatus;
    }

    public void setMsgTransStatus(String msgTransStatus) {
        this.msgTransStatus = msgTransStatus;
    }

    public Date getMsgInstDate() {
        return msgInstDate;
    }

    public void setMsgInstDate(Date msgInstDate) {
        this.msgInstDate = msgInstDate;
    }

    public Date getMsgAcceDate() {
        return msgAcceDate;
    }

    public void setMsgAcceDate(Date msgAcceDate) {
        this.msgAcceDate = msgAcceDate;
    }
}