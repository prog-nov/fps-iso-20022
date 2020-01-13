package com.forms.beneform4j.security.core.session;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 会话管理器接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-9<br>
 */
public interface ISessionManager {

    /**
     * 创建会话
     * 
     * @return
     */
    public ISession createSession();

    /**
     * 保存会话
     * 
     * @param session
     */
    public void save(ISession session);

    /**
     * 获取会话
     * 
     * @param id
     * @return
     */
    public ISession getSession(String id);

    /**
     * 删除会话
     * 
     * @param id
     */
    public void delete(String id);
}
