package com.forms.beneform4j.webapp.authority.access.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.security.core.access.permission.IPermission;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 访问控制数据访问接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
@Repository("accessControlDao")
public interface IAccessControlDao {

    /**
     * 根据请求URL查找所有可能的菜单权限
     * 
     * @param url 请求URL
     * @return 所有可能的菜单权限
     */
    public List<IPermission> getPermissions(@Param("url") String url);

    /**
     * 写访问日志
     * 
     * @param params
     */
    public void writeVisitLog(Object params);

    /**
     * 删除访问日志
     * 
     * @param params
     */
    public void deleteVisitLog(@Param("dateBefore") String dateBefore);

}
