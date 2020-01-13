package com.forms.beneform4j.core.util.depends;

import java.util.List;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 依赖图接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public interface IDependGraph<E extends IDependNode> {

    /**
     * 分析依赖并排序
     * 
     * @return 返回排好序后的依赖图节点集，忽略图中无效依赖，存在循环依赖时抛出异常
     */
    public List<? extends E> sort();
}
