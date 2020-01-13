package com.forms.beneform4j.util.tree.impl;

import java.util.ArrayList;
import java.util.List;

import com.forms.beneform4j.util.tree.ITree;
import com.forms.beneform4j.util.tree.ITreeNode;
import com.forms.beneform4j.util.tree.ITreeService;
import com.forms.beneform4j.util.tree.TreeFactory;

public abstract class AbstractTreeService<N extends ITreeNode> implements ITreeService<N> {

    /**
     * 加载树
     */
    @Override
    public ITree<N> load() {
        List<N> nodes = loadAllNodes();
        ITree<N> tree = TreeFactory.builder(nodes);
        return tree;
    }

    /**
     * 加载所有树节点
     * 
     * @return
     */
    protected abstract List<N> loadAllNodes();

    /**
     * 删除节点
     */
    @Override
    public int delete(N node) {
        int rs = deleteNode(node);
        int count = 0;
        do {
            count = deleteInvalidNodes(node);
            rs += count;
        } while (count > 0);
        return rs;
    }

    /**
     * 执行数据库中的删除操作
     * 
     * @param node
     * @return
     */
    protected abstract int deleteNode(N node);

    /**
     * 删除无效的节点
     * 
     * @param node
     * @return
     */
    protected abstract int deleteInvalidNodes(N node);

    /**
     * 在父节点的指定索引处插入节点
     */
    @SuppressWarnings("unchecked")
    @Override
    public int insertAtIndex(N parent, N node, int index) {
        List<N> nodes = new ArrayList<N>();
        boolean hasAddNode = false;
        int nodeIndex = 0;
        if (index == 0) {// 如果小于或等于0，放入最前面
            nodeIndex++;
            nodes.add(node);
            hasAddNode = true;
        }
        if (null != parent) {
            ((TreeNode) node).setParentCode(parent.getCode());
            List<ITreeNode> children = parent.getChildren();
            if (null != children && !children.isEmpty()) {
                for (ITreeNode child : children) {
                    if (!hasAddNode && index == nodeIndex) {// 尚未添加节点
                        nodeIndex++;
                        nodes.add(node);
                        hasAddNode = true;
                    }
                    if (!node.getCode().equals(child.getCode())) {// 如果移动的节点为原父节点的子节点，这里跳过
                        nodeIndex++;
                        nodes.add((N) child);
                    }
                }
            }
        }
        if (!hasAddNode) {// 循环结束尚未添加节点
            nodes.add(node);
        }
        this.updateParentAndSeqnos(nodes);
        return 0;
    }

    /**
     * 更新节点父节点和顺序
     * 
     * @param nodes
     */
    protected abstract void updateParentAndSeqnos(List<N> nodes);
}
