package com.forms.beneform4j.webapp.common.param.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.util.param.enums.IEnumParam;
import com.forms.beneform4j.util.param.single.ISingleParam;
import com.forms.beneform4j.util.param.tree.ITreeParam;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 参数访问DAO<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-12<br>
 */
@Repository("paramDao")
public interface IParamDao {

    /**
     * 加载系统参数
     * 
     * @return
     */
    @MapKey("paramCode")
    public Map<String, ISingleParam> loadParams(@Param("groups") List<String> groups, @Param("names") List<String> names);

    /**
     * 加载用户参数
     * 
     * @return
     */
    @MapKey("paramCode")
    public Map<String, ISingleParam> loadUserParams(@Param("userId") String userId, @Param("groups") List<String> groups, @Param("names") List<String> names);

    /**
     * 加载枚举参数
     * 
     * @param names
     * @return
     */
    @MapKey("paramCode")
    public Map<String, IEnumParam> loadEnumParams(@Param("names") List<String> names);

    /**
     * 加载树型枚举参数
     * 
     * @param names
     * @return
     */
    @MapKey("paramCode")
    public Map<String, ITreeParam> loadTreeParams(@Param("names") List<String> names);

}
