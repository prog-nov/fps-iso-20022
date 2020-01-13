package com.forms.ffp.webapp.cashmanagement.return_refund.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.ffp.webapp.cashmanagement.return_refund.bean.ReturnRefundBean;
import com.forms.ffp.webapp.cashmanagement.return_refund.bean.ReturnRefundDetailBean;
import com.forms.ffp.webapp.cashmanagement.return_refund.form.ReturnRefundSearchForm;

@Repository("ReturnRefundDao")
public interface ReturnRefundDao {
	/**
	 * @param name
	 * @param accountNm
	 * @param date
	 * @return
	 */
	List<ReturnRefundBean> inqueryReturnMsg(ReturnRefundSearchForm form,IPage page);

	 
	/**
	 * for detail
	 * @param txCode
	 * @param jnlNo
	 * @return
	 */
	List<ReturnRefundDetailBean>  inqueryReturnDetailMsg(@Param("txCode")String txCode,
			@Param("jnlNo")String jnlNo);
	
	

}
