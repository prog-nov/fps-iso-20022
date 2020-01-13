package com.forms.beneform4j.webapp.authority.login.service.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.listener.impl.LoginListenerSupport;
import com.forms.beneform4j.security.core.login.token.IAuthenticationToken;
import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.util.param.common.IParamLoader;
import com.forms.beneform4j.util.param.common.impl.CacheParamStore;
import com.forms.beneform4j.util.param.single.ISingleParam;
import com.forms.beneform4j.util.param.single.impl.SingleParamService;
import com.forms.beneform4j.webapp.common.param.dao.IParamDao;
import com.forms.beneform4j.webapp.common.param.service.ParamHolder;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 设置用户参数服务<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
public class UserParamServiceListener extends LoginListenerSupport {

    @Resource(name = "paramDao")
    private IParamDao dao;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoginSuccess(IAuthenticationToken authenticationToken, IAuthenticationInfo info) {
        IUser user = info.getUser();
        final String userId = user.getUserId();
        SingleParamService service = new SingleParamService();
        service.setLoader(new IParamLoader<ISingleParam>() {
            @Override
            public Map<String, ISingleParam> loadParams(List<String> names) {
                Set<String> codes = ParamHolder.getTreeParamCodes("PARAM_GROUP", "USER_OPTION");
                if (null != codes && !codes.isEmpty()) {
                    return dao.loadUserParams(userId, new ArrayList<String>(codes), names);
                } else {
                    return dao.loadUserParams(userId, null, names);
                }
            }
        });
        service.setStore(new CacheParamStore<ISingleParam>(ISingleParam.class.getName() + "#" + userId));
        user.setParamService(service);
        service.refresh();

        int defaultRoleId = service.get(IUser.DEFAULT_ROLE_OPTION_NAME, -1, int.class);
        user.setCurrentRoleId(defaultRoleId);
    }
}
