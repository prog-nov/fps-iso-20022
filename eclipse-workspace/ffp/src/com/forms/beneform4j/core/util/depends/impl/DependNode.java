package com.forms.beneform4j.core.util.depends.impl;

import java.util.ArrayList;
import java.util.List;

import com.forms.beneform4j.core.util.depends.IDependNode;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 依赖图节点实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class DependNode implements IDependNode {

    private String id;

    private List<String> depends;

    /**
     * 构造没有依赖的节点
     * 
     * @param id 节点ID
     */
    public DependNode(String id) {
        this.id = id;
    }

    /**
     * 根据节点id和依赖构造，去除重复的依赖
     * 
     * @param id
     * @param depends
     */
    public DependNode(String id, List<?> depends) {
        this.id = id;
        if (null != depends && depends.size() > 0) {
            List<String> dps = new ArrayList<String>();
            for (Object dp : depends) {
                if (null != dp) {
                    String depend = (dp instanceof IDependNode) ? (((IDependNode) dp).getId()) : dp.toString();
                    if (!dps.contains(depend)) {
                        dps.add(depend);
                    }
                }
            }
            this.depends = dps;
        }
    }

    /**
     * 根据节点id和依赖构造，去除重复的依赖
     * 
     * @param id
     * @param depends
     */
    public DependNode(String id, Object... depends) {
        this.id = id;
        if (null != depends && depends.length > 0) {
            List<String> dps = new ArrayList<String>();
            for (Object dp : depends) {
                if (null != dp) {
                    String depend = (dp instanceof IDependNode) ? (((IDependNode) dp).getId()) : dp.toString();
                    if (!dps.contains(depend)) {
                        dps.add(depend);
                    }
                }
            }
            this.depends = dps;
        }
    }

    /**
     * 获取依赖图节点ID
     * 
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * 获取依赖图节点的所有依赖
     * 
     * @return
     */
    public List<String> getDepends() {
        return depends;
    }
}
