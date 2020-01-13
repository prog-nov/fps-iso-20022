package com.forms.beneform4j.security.core.access.permission;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 权限接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public interface IPermission {

    /**
     * 获取权限ID
     * 
     * @return 权限ID
     */
    public int getPermId();

    /**
     * 获取权限类型
     * 
     * @return 权限类型，如MENU菜单、FILE文件等
     */
    public String getPermType();

    /**
     * 获取权限类型的键值
     * 
     * @return 权限类型的键值，和权限类型对应，如菜单ID、文件名称等
     */
    public String getPermTypeKey();

    /**
     * 获取权限级别 0 公共功能 1有效用户 2授权用户
     * 
     * @return 权限级别
     */
    public int getPermLevel();

    /**
     * 获取权限功能地址
     * 
     * @return 权限功能地址
     */
    public String getPermUrl();

    /**
     * 获取权限属性 显示是否可操作
     * 
     * @return 权限属性
     */
    public String getPermAttr();
}
