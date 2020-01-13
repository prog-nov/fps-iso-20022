package com.forms.beneform4j.security.core.session;

import java.io.Serializable;
import java.util.Set;

import com.forms.beneform4j.security.core.login.user.IUser;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 会话接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-9<br>
 */
public interface ISession extends Serializable {

    /**
     * 获取会话ID
     * 
     * @return 会话ID
     */
    public String getId();

    /**
     * 获取属性
     * 
     * @param attributeName 属性名称
     * @return 属性值
     */
    public <T> T getAttribute(String attributeName);

    /**
     * 获取属性名称集合
     * 
     * @return 属性名称集合
     */
    public Set<String> getAttributeNames();

    /**
     * 设置属性
     * 
     * @param attributeName 属性名称
     * @param attributeValue 属性值
     */
    public void setAttribute(String attributeName, Object attributeValue);

    /**
     * 移除属性
     * 
     * @param attributeName 属性名称
     */
    public void removeAttribute(String attributeName);

    /**
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public long getCreationTime();

    /**
     * 设置最后访问时间
     * 
     * @param lastAccessedTime 最后访问时间
     */
    public void setLastAccessedTime(long lastAccessedTime);

    /**
     * 获取最后访问时间
     * 
     * @return 最后访问时间
     */
    public long getLastAccessedTime();

    /**
     * 设置交互的最大间隔秒数，超过该秒数即为过期
     * 
     * @param interval 交互的最大间隔秒数
     */
    public void setMaxInactiveIntervalInSeconds(int interval);

    /**
     * 获取交互的最大间隔秒数
     * 
     * @return 交互的最大间隔秒数
     */
    public int getMaxInactiveIntervalInSeconds();

    /**
     * 是否过期
     * 
     * @return 是否过期
     */
    public boolean isExpired();

    /**
     * 获取当前用户对象
     * 
     * @return 用户对象
     */
    public IUser getUser();

    /**
     * 设置当前用户对象
     * 
     * @param user 用户对象
     */
    public void setUser(IUser user);
}
