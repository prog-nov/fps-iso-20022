package com.forms.beneform4j.webapp.common.form;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 可记录日志的表单对象<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-15<br>
 */
public class LogableForm extends BaseForm {

    private static final long serialVersionUID = 6514681341567114370L;

    /**
     * 新增用户
     */
    private String instOper;

    /**
     * 新增日期
     */
    private String instDate;

    /**
     * 新增时间
     */
    private String instTime;

    /**
     * 修改用户
     */
    private String modiOper;

    /**
     * 修改日期
     */
    private String modiDate;

    /**
     * 修改时间
     */
    private String modiTime;

    public String getInstOper() {
        return instOper;
    }

    public void setInstOper(String instOper) {
        this.instOper = instOper;
    }

    public String getInstDate() {
        return instDate;
    }

    public void setInstDate(String instDate) {
        this.instDate = instDate;
    }

    public String getInstTime() {
        return instTime;
    }

    public void setInstTime(String instTime) {
        this.instTime = instTime;
    }

    public String getModiOper() {
        return modiOper;
    }

    public void setModiOper(String modiOper) {
        this.modiOper = modiOper;
    }

    public String getModiDate() {
        return modiDate;
    }

    public void setModiDate(String modiDate) {
        this.modiDate = modiDate;
    }

    public String getModiTime() {
        return modiTime;
    }

    public void setModiTime(String modiTime) {
        this.modiTime = modiTime;
    }
}
