package com.forms.beneform4j.demo.form;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 金额处理程序类注意：建议字段设置全部用字符串,如果用 int 或是long 或是double，在spring容器进行字段的映射或转换时，
 * 如果为空，则会出错，为数据绑定出错的问题 Author : luow<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-20<br>
 */
public class MoneyForm {

    private String id;

    private String money1;

    private String money2;

    private String money3;

    private String money4;

    private String money5;

    private String money6;

    private String money7;

    public String getMoney1() {
        return money1;
    }

    public void setMoney1(String money1) {
        this.money1 = money1;
    }

    public String getMoney2() {
        return money2;
    }

    public void setMoney2(String money2) {
        this.money2 = money2;
    }

    public String getMoney3() {
        return money3;
    }

    public void setMoney3(String money3) {
        this.money3 = money3;
    }

    public String getMoney4() {
        return money4;
    }

    public void setMoney4(String money4) {
        this.money4 = money4;
    }

    public String getMoney5() {
        return money5;
    }

    public void setMoney5(String money5) {
        this.money5 = money5;
    }

    public String getMoney6() {
        return money6;
    }

    public void setMoney6(String money6) {
        this.money6 = money6;
    }

    public String getMoney7() {
        return money7;
    }

    public void setMoney7(String money7) {
        this.money7 = money7;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MoneyForm(String id, String money1, String money2, String money3, String money4, String money5, String money6, String money7) {
        super();
        this.id = id;
        this.money1 = money1;
        this.money2 = money2;
        this.money3 = money3;
        this.money4 = money4;
        this.money5 = money5;
        this.money6 = money6;
        this.money7 = money7;
    }

    public MoneyForm() {

    }
}
