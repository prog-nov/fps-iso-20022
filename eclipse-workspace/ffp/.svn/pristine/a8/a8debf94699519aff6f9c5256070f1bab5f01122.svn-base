package com.forms.beneform4j.util.tree;

import java.io.Serializable;
import java.util.List;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 树模型接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-9<br>
 */
public interface ITree<E extends ITreeNode> extends Serializable {

    /**
     * 获取根节点
     */
    public E getRoot();

    /**
     * 根据内部节点ID获取节点
     * 
     * @param id 内部节点ID，构建树时自动生成的ID
     * @return 树节点
     */
    public E getNode(int id);

    /**
     * 根据代码查找节点
     * 
     * @param code 节点代码
     * @return 树节点
     */
    public E getNode(String code);

    /**
     * 是否包括指定代码的节点
     * 
     * @param code 节点代码
     * @return 是否包含
     */
    public boolean containsCode(String node);

    /**
     * 深度优先获取节点集(深度优先)
     * 
     * @return 节点集
     */
    public List<E> getNodeList();

    /**
     * 获取节点集，并使用过滤器过滤(深度优先)
     * 
     * @param filter 节点过滤器
     * @return 节点集
     */
    public List<E> getNodeList(ITreeNodeFilter filter);

    /**
     * 根据根节点代码创建子树
     * 
     * @param code 节点代码
     * @return 子树
     */
    public ITree<E> getSubTree(String code);

    /**
     * 根据子树深度创建子树
     * 
     * @param depth 子树深度
     * @return 子树
     */
    public ITree<E> getSubTree(int depth);

    /**
     * 根据根节点代码和子树深度创建子树
     * 
     * @param code 节点代码
     * @param depth 子树深度，注意：不是原树中的深度
     * @return 子树
     */
    public ITree<E> getSubTree(String code, int depth);

    /**
     * 根据子节点过滤器创建子树
     * 
     * @param filter 节点过滤器
     * @param firstFilter true 先使用过滤器，再获取子节点；false 先获取所有子节点，再使用过滤器过滤
     * @return 子树
     */
    public ITree<E> getSubTree(ITreeNodeFilter filter, boolean firstFilter);

    /**
     * 根据根节点代码和子节点过滤器创建子树
     * 
     * @param code 节点代码
     * @param filter 节点过滤器
     * @param firstFilter true 先使用过滤器，再获取子节点；false 先获取所有子节点，再使用过滤器过滤
     * @return 子树
     */
    public ITree<E> getSubTree(String code, ITreeNodeFilter filter, boolean firstFilter);
}
