package com.forms.beneform4j.webapp.systemmanage.param.single.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forms.beneform4j.util.param.tree.ITreeParam;
import com.forms.beneform4j.util.param.tree.ITreeParamNode;
import com.forms.beneform4j.webapp.common.param.service.ParamHolder;
import com.forms.beneform4j.webapp.systemmanage.param.single.bean.SingleParamBean;
import com.forms.beneform4j.webapp.systemmanage.param.single.dao.ISingleParamManagerDao;
import com.forms.beneform4j.webapp.systemmanage.param.single.form.SingleParamForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 单值参数维护服务类<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-12<br>
 */
@Service("paramManagerService")
@Scope("prototype")
public class ParamManagerService implements IParamManagerService {

    @Resource(name = "singleParamManagerDao")
    private ISingleParamManagerDao paramDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, List<SingleParamBean>> queryParamList() {
        Map<String, List<SingleParamBean>> rs = new HashMap<String, List<SingleParamBean>>();
        ITreeParam treeParameter = ParamHolder.getTreeParameter("PARAM_GROUP");
        ITreeParamNode node = treeParameter.getNode("SYSTEM_CONFIG");
        List<SingleParamBean> paramList = paramDao.queryParamList(node.getAllChildrenCodes(true));
        for (SingleParamBean singleParamBean : paramList) {
            String dataText = singleParamBean.getDataText();
            if (rs.containsKey(dataText)) {
                List<SingleParamBean> list = rs.get(dataText);
                list.add(singleParamBean);
            } else {
                List<SingleParamBean> r = new ArrayList<SingleParamBean>();
                r.add(singleParamBean);
                rs.put(dataText, r);
            }
        }
        return rs;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public int updateParamValue(String[] codes, String[] values) {

        if (null != codes && null != values && codes.length == values.length) {
            for (int n = 0; n < codes.length; n++) {
                SingleParamForm form = new SingleParamForm();
                form.setParamCode(codes[n]);
                form.setParamValue(values[n]);
                int i = paramDao.updateParamValue(form);
                if (i <= 0) {
                    paramDao.addParamValue(form);
                }
            }
            ParamHolder.refreshParameters();
            return codes.length;
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteParamValue(SingleParamForm param) {
        return paramDao.deleteParamValue(param);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SingleParamBean> dGetSingleParamComboData(SingleParamForm form) {
        ITreeParam treeParameter = ParamHolder.getTreeParameter("PARAM_GROUP");
        ITreeParamNode node = treeParameter.getNode("SYSTEM_CONFIG");
        return paramDao.dGetSingleParamComboData(node.getAllChildrenCodes(true), form);
    }

}
