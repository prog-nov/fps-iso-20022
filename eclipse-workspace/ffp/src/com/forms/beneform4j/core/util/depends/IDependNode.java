package com.forms.beneform4j.core.util.depends;

import java.util.List;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 依赖图节点接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public interface IDependNode {

    /**
     * 获取依赖图节点ID
     * 
     * @return 节点ID
     */
    public String getId();

    /**
     * 获取依赖图节点的所有依赖
     * 
     * @return 节点的所有依赖节点列表
     */
    public List<String> getDepends();
}
