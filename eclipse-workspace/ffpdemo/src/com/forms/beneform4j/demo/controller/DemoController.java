package com.forms.beneform4j.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.demo.form.MoneyForm;
import com.forms.beneform4j.web.annotation.PageJsonBody;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : DEMO控制层<br>
 * Author : oulinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-12<br>
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    static List<MoneyForm> listMoneys;

    /**
     * DEMO页面1
     * 
     * @return
     */
    @RequestMapping("/demo1/demo1")
    public ModelAndView demo1() {
        return new ModelAndView("demo/demo1/demo1");
    }

    /**
     * DEMO页面2
     * 
     * @return
     */
    @RequestMapping("/demo2/demo2")
    public ModelAndView demo2() {
        return new ModelAndView("demo/demo2/demo2");
    }

    /**
     * DEMO页面3
     * 
     * @return
     */
    @RequestMapping("/demo3/demo3")
    public ModelAndView demo3() {
        return new ModelAndView("demo/demo3/demo3");
    }

    @RequestMapping("/demo5")
    public ModelAndView demo5() {
        ModelAndView modelAndView = new ModelAndView("demo/demo5");
        modelAndView.addObject("hello", "hello5");
        return modelAndView;
    }

    @RequestMapping("/login1")
    public ModelAndView login1() {
        ModelAndView modelAndView = new ModelAndView("demo/login/login1");
        return modelAndView;
    }

    @RequestMapping("/login2")
    public ModelAndView login2() {
        ModelAndView modelAndView = new ModelAndView("demo/login/login2");
        return modelAndView;
    }

    /**
     * DEMO listView
     * 
     * @return
     */
    @RequestMapping("/demo01/listView")
    public ModelAndView listView() {
        return new ModelAndView("demo/demo01/listView");
    }

    /**
     * DEMO listUser
     * 
     * @return
     */
    @RequestMapping("/demo01/listUser")
    public ModelAndView listUser() {
        return new ModelAndView("demo/demo01/listUser");
    }

    /**
     * DEMO iconconfig
     * 
     * @return
     */
    @RequestMapping("/iconconfig/iconconfig")
    public ModelAndView iconConfig() {
        return new ModelAndView("demo/iconconfig/iconconfig");
    }

    /**
     * Author : luow <br>
     * Version : 1.0.0 <br>
     * Since : 1.0.0 <br>
     * Date : 2016-5-20<br>
     */
    @RequestMapping("money/list")
    @PageJsonBody
    public List<MoneyForm> list(MoneyForm moneyForm, IPage page) {
        return listMoneys;
    }

    /**
     * Author : luow <br>
     * Version : 1.0.0 <br>
     * Since : 1.0.0 <br>
     * Date : 2016-5-20<br>
     */
    static {
        listMoneys = new ArrayList<MoneyForm>();
        for (int i = 0; i < 5; i++) {
            listMoneys.add(new MoneyForm("1" + i, "12654132132.05", "21", "25.12", "1.022", "a", "12345413121", "0.1"));
        }
    }

}
