package com.forms.ffp.webapp.debit.controller;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.web.annotation.PageJsonBody;
import com.forms.beneform4j.webapp.common.docmanager.form.DocForm;


@Scope("request")
@Controller
@RequestMapping("ffp/debit/debit")
public class DebitController {
	    @RequestMapping("list")
	    @PageJsonBody
	    public List<Object> list(DocForm docForm, IPage page) {
	        return null;
	    }
}
