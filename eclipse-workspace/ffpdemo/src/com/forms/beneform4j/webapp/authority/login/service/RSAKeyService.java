package com.forms.beneform4j.webapp.authority.login.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.core.util.rsa.impl.AbstractRSAKeyService;
import com.forms.beneform4j.core.util.rsa.impl.KeyProperty;
import com.forms.beneform4j.webapp.authority.login.dao.ILoginDao;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 登录公钥获取服务类<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-30<br>
 */
@Service("rsaKeyService")
@Scope("prototype")
public class RSAKeyService extends AbstractRSAKeyService<KeyProperty> {

    @Resource(name = "loginDao")
    private ILoginDao dao;

    /**
     * {@inheritDoc}
     */
    @Override
    public KeyProperty getKey() {
        return dao.dGetKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KeyProperty initKey() {
        KeyProperty kp = buildKey();
        return initKey(kp);
    }

    /**
     * 初始化Key
     * 
     * @param kp
     * @return
     */
    public KeyProperty initKey(KeyProperty kp) {
        dao.dTruncateKey();
        dao.dInitKey(kp);
        return kp;
    }

}
