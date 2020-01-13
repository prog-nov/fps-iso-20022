package com.forms.beneform4j.util.tree;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 节点过滤器接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2013-10-4<br>
 */
public interface ITreeNodeFilter {

    /**
     * 根据节点过滤
     * 
     * @param node
     * @return
     */
    boolean accept(ITreeNode node);
}
