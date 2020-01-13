package com.forms.beneform4j.util.tree.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.forms.beneform4j.util.tree.ITree;
import com.forms.beneform4j.util.tree.ITreeNode;
import com.forms.beneform4j.util.tree.ITreeNodeFilter;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 树模型实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-9<br>
 */
public class Tree<E extends ITreeNode> implements ITree<E> {

    /**
     * 
     */
    private static final long serialVersionUID = 4367174862859216659L;

    private E root; // 根节点

    private List<E> nodeList = new ArrayList<E>(); // 所有节点

    private Map<String, Integer> codeIdMapping = new HashMap<String, Integer>(); // 节点代码与ID的映射

    /**
     * 无参构造函数
     */
    public Tree() {
        this(null);
    }

    /**
     * 根据节点集构建树
     * 
     * @param nodeList 节点集
     */
    public Tree(List<E> nodeList) {
        this(nodeList, "", "根节点");
    }

    /**
     * 根据节点集和根节点代码、文本构建树
     * 
     * @param nodeList
     * @param rootCode
     * @param rootText
     */
    public Tree(List<E> nodeList, String rootCode, String rootText) {
        build(nodeList, rootCode, rootText);
    }

    /**
     * 初始化构建
     * 
     * @param nodeList
     */
    protected void build(List<E> nodeList, String rootCode, String rootText) {
        this.root = null;
        this.nodeList.clear();
        this.codeIdMapping.clear();
        try {
            if (null == nodeList || nodeList.isEmpty()) {
                // this.root = getRootNode(null, rootCode, rootText);
                // Throw.throwRuntimeException(ExceptionCodes.BF010305);
            } else {
                /**
                 * 检查节点，并存储节点代码集，用于判断是否为一级子节点或孤立子节点
                 */
                Set<String> codes = new HashSet<String>();
                List<E> nodes = new ArrayList<E>(nodeList.size());
                for (E node : nodeList) {
                    String code = node.getCode();
                    if (codes.contains(code)) {
                        throw new RuntimeException("there is a duplicate tree node --> [" + code + "]" + node.getText());
                    } else {
                        codes.add(code);
                        nodes.add(node);
                    }
                }
                this.root = getRootNode(nodeList.get(0), rootCode, rootText);
                setSiblingsChildren(this.root, nodes, 0, codes);
                if (nodes.size() != this.nodeList.size()) {// 如果不相等，说明存在孤立的节点
                    StringBuffer sb = new StringBuffer();
                    for (E node : nodes) {
                        if (!this.nodeList.contains(node)) {
                            sb.append(",[" + node.getCode() + "]" + node.getText() + "-->" + node.getParentCode());
                        }
                    }
                    throw new RuntimeException("there is isolated nodes or circular dependency --> " + sb.substring(1));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("there is a exception when build the tree", e);
        }
    }

    /**
     * 根据代码查找节点
     * 
     * @param code
     * @return
     */
    @Override
    public E getNode(String code) {
        Integer id = this.codeIdMapping.get(code);
        if (null == id) {
            throw new RuntimeException("not found the tree node --> [" + code + "]");
        } else {
            return this.getNode(id);
        }
    }

    /**
     * 根据节点索引获取节点 虚拟根节点ID为0，其它节点ID和节点集中位置一一对应，所以直接返回ID索引处的节点
     * 
     * @param id
     * @return
     */
    @Override
    public E getNode(int id) {
        if (id == 0) {
            return getRoot();
        } else if (id < 0 || id > this.nodeList.size()) {
            throw new RuntimeException("Tree node index out of range: " + id + ", the valid range is [0, " + (this.nodeList.size() - 1) + "]");
        }
        return this.nodeList.get(id - 1);
    }

    /**
     * 获取根节点
     */
    @Override
    public E getRoot() {
        return this.root;
    }

    /**
     * 是否包括指定代码的节点
     * 
     * @param code 节点代码
     * @return 是否包含
     */
    @Override
    public boolean containsCode(String code) {
        return null != codeIdMapping && codeIdMapping.containsKey(code);
    }

    /**
     * 获取节点集
     */
    @Override
    public List<E> getNodeList() {
        return Collections.unmodifiableList(nodeList);
    }

    /**
     * 获取节点集
     */
    @Override
    public List<E> getNodeList(ITreeNodeFilter filter) {
        if (null == nodeList) {
            return null;
        } else {
            List<E> list = new ArrayList<E>();
            for (E node : nodeList) {
                if (null == filter || filter.accept(node)) {
                    list.add(node);
                }
            }
            return list;
        }
    }

    /**
     * 根据根节点代码创建子树
     * 
     * @param code
     * @return
     */
    @Override
    public ITree<E> getSubTree(String code) {
        return getSubTree(code, (ITreeNodeFilter) null, true);
    }

    /**
     * 根据子树深度创建子树
     * 
     * @param depth
     * @return
     */
    @Override
    public ITree<E> getSubTree(final int depth) {
        return getSubTree(new ITreeNodeFilter() {
            public boolean accept(ITreeNode node) {
                return node.getDepth() <= depth;
            }
        }, true);
    }

    /**
     * 根据根节点代码和子树深度创建子树
     * 
     * @param code
     * @param depth
     * @return
     */
    @Override
    public ITree<E> getSubTree(String code, int depth) {
        E node = getNode(code);
        final int childDepth = node.getDepth() + depth;
        return getSubTree(code, new ITreeNodeFilter() {
            public boolean accept(ITreeNode node) {
                return node.getDepth() <= childDepth;
            }
        }, true);
    }

    /**
     * 根据根节点代码和子节点过滤器创建子树
     * 
     * @param filter
     * @param firstFilter
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public ITree<E> getSubTree(ITreeNodeFilter filter, boolean firstFilter) {
        E root = getRoot();
        List<E> newNodeList = new ArrayList<E>();
        List<ITreeNode> nodeList = root.getAllChildren(filter, false, firstFilter);
        if (null != nodeList && !nodeList.isEmpty()) {
            for (ITreeNode node : nodeList) {
                newNodeList.add((E) node.copyNodeData());
            }
        }
        return new Tree<E>(newNodeList);
    }

    /**
     * 根据根节点代码和子节点过滤器创建子树
     * 
     * @param code
     * @param filter
     * @param firstFilter
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public ITree<E> getSubTree(String code, ITreeNodeFilter filter, boolean firstFilter) {
        E root = getNode(code);
        List<E> newNodeList = new ArrayList<E>();
        newNodeList.add((E) root.copyNodeData());
        List<ITreeNode> nodeList = root.getAllChildren(filter, false, firstFilter);
        if (null != nodeList && !nodeList.isEmpty()) {
            for (ITreeNode node : nodeList) {
                newNodeList.add((E) node.copyNodeData());
            }
        }
        return new Tree<E>(newNodeList);
    }

    /**
     * 描述
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tree :\n");
        if (null != root) {
            toInnerString(sb, root);
        }
        sb.append("Tree End\n");
        return sb.toString();
    }

    /**
     * ===========以下为私有方法============================================
     */
    /**
     * 循环设置子节点
     * 
     * @param parentNode
     * @param nodes
     * @param id
     * @param codes
     * @return
     */
    private int setSiblingsChildren(E parentNode, List<E> nodes, int id, Set<String> codes) {
        boolean isRoot = (id == 0);
        for (E node : nodes) {
            if (this.nodeList.contains(node)) {
                continue;
            }

            /**
             * 存在父子关系或者是孤立的节点
             */
            if (node.isChild(parentNode) || isRoot && !codes.contains(node.getParentCode())) {
                id = id + 1;
                this.nodeList.add(node);
                this.codeIdMapping.put(node.getCode(), id);

                node.setId(id);
                node.setDepth(parentNode.getDepth() + 1);
                node.setPath(isRoot ? node.getText() : (parentNode.getPath() + "-->" + node.getText()));
                // System.out.println(id + "==> " + node.getPath());
                parentNode.addChild(node);
                id = setSiblingsChildren(node, nodes, id, codes);
            }
        }
        return id;
    }

    /**
     * 获取虚拟根节点
     * 
     * @param node
     * @return
     */
    @SuppressWarnings("unchecked")
    private E getRootNode(E node, String rootCode, String rootText) {
        E root = null;
        try {
            Class<E> cls = null;
            if (null != node) {
                cls = (Class<E>) node.getClass();
            } else {
                cls = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            }
            root = cls.newInstance();
            root.setId(ITreeNode.ROOT_ID);
            root.setDepth(ITreeNode.ROOT_DEPTH);
            root.setPath(rootCode);
            root.setText(rootText);
            return root;
        } catch (Exception e) {
            throw new RuntimeException("there is a exception when parse the virtual root node", e);
        }
    }

    /**
     * 描述
     * 
     * @param sb
     * @param node
     */
    private void toInnerString(StringBuilder sb, ITreeNode node) {
        sb.append(node.toString());
        if (null != node.getChildren()) {
            for (ITreeNode n : node.getChildren()) {
                toInnerString(sb, n);
            }
        }
    }
}
