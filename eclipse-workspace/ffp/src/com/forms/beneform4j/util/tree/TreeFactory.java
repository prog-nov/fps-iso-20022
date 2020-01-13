package com.forms.beneform4j.util.tree;

import java.util.List;

import com.forms.beneform4j.util.tree.impl.Tree;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 树工厂类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-10<br>
 */
public class TreeFactory {

    /**
     * 根据节点集构建树
     * 
     * @param nodeList
     * @return
     */
    public static <E extends ITreeNode> ITree<E> builder(List<E> nodeList) {
        return new Tree<E>(nodeList);
    }

    /**
     * 根据节点集构建树
     * 
     * @param nodeList
     * @param rootCode
     * @param rootText
     * @return
     */
    public static <E extends ITreeNode> ITree<E> builder(List<E> nodeList, String rootCode, String rootText) {
        return new Tree<E>(nodeList, rootCode, rootText);
    }

    /**
     * 根据节点集构建树，并返回根节点的直接子节点集
     * 
     * @param nodeList
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <E extends ITreeNode> List<E> rootChildren(List<E> nodeList) {
        ITree<E> tree = builder(nodeList);
        E root = tree.getRoot();
        if (null != root) {
            return (List<E>) root.getChildren();
        }
        return null;
    }

    /**
     * 获取节点的直接子节点
     * 
     * @param node 节点
     * @return 直接子节点集
     */
    @SuppressWarnings("unchecked")
    public static <E extends ITreeNode> List<E> getChildren(E node) {
        if (null != node) {
            return (List<E>) node.getChildren();
        }
        return null;
    }

    /**
     * 获取节点的子节点集
     * 
     * @param node
     * @param addSelf 是否添加自身
     * @return 所有子节点集
     */
    @SuppressWarnings("unchecked")
    public static <E extends ITreeNode> List<E> getAllChildren(E node, boolean addSelf) {
        if (null != node) {
            return (List<E>) node.getAllChildren(addSelf);
        }
        return null;
    }
}
