package com.forms.beneform4j.security.core.access.permission;

import com.forms.beneform4j.util.tree.ITreeNode;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 菜单权限接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-19<br>
 */
public interface IMenuPermission extends ITreeNode {

    /**
     * 获取权限ID
     * 
     * @return 权限ID
     */
    public int getPermId();

    /**
     * 获取权限属性 显示是否可操作
     * 
     * @return 权限属性
     */
    public String getPermAttr();

    /**
     * 获取显示区域
     * 
     * @return
     */
    public String getDisplayArea();

    /**
     * 获取依赖菜单ID
     * 
     * @return
     */
    public int getDependMenuId();

    /**
     * 获取授权级别
     * 
     * @return
     */
    public String getAuthorLevel();

    /**
     * 获取菜单标志
     * 
     * @return
     */
    public String getMenuFlag();

    /**
     * 获取权限树标志
     * 
     * @return
     */
    public String getPermTreeFlag();

    /**
     * 获取目标页面名称
     * 
     * @return
     */
    public String getTargetPage();

    /**
     * 获取排序
     * 
     * @return 排序
     */
    public int getSeqno();
}
