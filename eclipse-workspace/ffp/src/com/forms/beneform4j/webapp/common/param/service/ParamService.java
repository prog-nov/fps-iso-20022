package com.forms.beneform4j.webapp.common.param.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.util.param.AbstractParamService;
import com.forms.beneform4j.util.param.IParamService;
import com.forms.beneform4j.util.param.common.IParamLoader;
import com.forms.beneform4j.util.param.enums.IEnumParam;
import com.forms.beneform4j.util.param.enums.impl.EnumParamService;
import com.forms.beneform4j.util.param.single.ISingleParam;
import com.forms.beneform4j.util.param.single.impl.SingleParamService;
import com.forms.beneform4j.util.param.tree.ITreeParam;
import com.forms.beneform4j.util.param.tree.impl.TreeParamService;
import com.forms.beneform4j.webapp.common.param.dao.IParamDao;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 参数服务实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-12<br>
 */
@Service("paramService")
public class ParamService extends AbstractParamService implements IParamService, InitializingBean {

    @Resource(name = "paramDao")
    private IParamDao dao;

    @Override
    public void afterPropertiesSet() throws Exception {
        SingleParamService service = new SingleParamService();
        service.setLoader(new IParamLoader<ISingleParam>() {
            @Override
            public Map<String, ISingleParam> loadParams(List<String> names) {
                Set<String> codes = ParamHolder.getTreeParamCodes("PARAM_GROUP", "SYSTEM_CONFIG");
                if (null != codes && !codes.isEmpty()) {
                    return dao.loadParams(new ArrayList<String>(codes), names);
                } else {
                    return dao.loadParams(null, names);
                }
            }
        });
        super.setService(service);

        EnumParamService enumService = new EnumParamService();
        enumService.setLoader(new IParamLoader<IEnumParam>() {
            @Override
            public Map<String, IEnumParam> loadParams(List<String> names) {
                return dao.loadEnumParams(names);
            }
        });
        super.setEnumService(enumService);

        TreeParamService treeService = new TreeParamService();
        treeService.setLoader(new IParamLoader<ITreeParam>() {
            @Override
            public Map<String, ITreeParam> loadParams(List<String> names) {
                return dao.loadTreeParams(names);
            }
        });
        super.setTreeService(treeService);
    }
}
