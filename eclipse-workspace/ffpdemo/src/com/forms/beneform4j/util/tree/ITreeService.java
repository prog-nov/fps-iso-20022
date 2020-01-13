package com.forms.beneform4j.util.tree;

import java.util.List;

public interface ITreeService<N extends ITreeNode> {

    /**
     * 加载整个树
     * 
     * @return
     */
    public ITree<N> load();

    /**
     * 获取子节点
     * 
     * @param parent
     * @return
     */
    public List<N> getChildren(N parent);

    /**
     * 获取节点
     * 
     * @param code
     * @return
     */
    public N getNode(N node);

    /**
     * 删除节点
     * 
     * @param node
     * @return
     */
    public int delete(N node);

    /**
     * 添加节点
     * 
     * @param node
     * @return
     */
    public int insert(N node);

    /**
     * 修改节点
     * 
     * @param node
     * @return
     */
    public int update(N node);

    /**
     * 在索引处插入节点
     * 
     * @param parent 新父节点
     * @param node 节点
     * @param index 索引 -1表示加在最后
     * @return
     */
    public int insertAtIndex(N parent, N node, int index);

}
