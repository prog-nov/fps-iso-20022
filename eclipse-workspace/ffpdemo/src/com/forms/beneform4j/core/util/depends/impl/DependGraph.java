package com.forms.beneform4j.core.util.depends.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.forms.beneform4j.core.util.depends.IDependGraph;
import com.forms.beneform4j.core.util.depends.IDependNode;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.exception.meta.ExceptionCodes;
import com.forms.beneform4j.core.util.logger.CommonLogger;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 依赖图实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class DependGraph<E extends IDependNode> implements IDependGraph<E> {

    private List<E> nodeList;

    private List<E> sortList;

    private boolean hasSort = false;

    /**
     * 构造函数
     */
    public DependGraph() {}

    /**
     * 根据节点集构造
     * 
     * @param nodeList 节点集
     */
    public DependGraph(List<E> nodeList) {
        this.setNodeList(nodeList);
    }

    /**
     * 分析依赖并排序
     * 
     * @return 返回排好序后的依赖图节点集，忽略图中无效依赖，存在循环依赖时抛出异常
     */
    public List<E> sort() {
        if (!hasSort) {
            this.sortList = sortNodeList();
            hasSort = true;
        }
        return sortList;
    }

    /**
     * 获取节点集
     * 
     * @return
     */
    public List<E> getNodeList() {
        return nodeList;
    }

    /**
     * 设置节点集，去除重复的节点
     * 
     * @param nodeList
     */
    public void setNodeList(List<E> nodeList) {
        if (null != nodeList && nodeList.size() >= 2) {
            List<String> keys = new ArrayList<String>();
            for (int i = 0; i < nodeList.size(); i++) {
                if (keys.contains(nodeList.get(i).getId())) {
                    nodeList.remove(i);
                } else {
                    keys.add(nodeList.get(i).getId());
                }
            }
        }
        this.nodeList = nodeList;
    }

    /**
     * 分析依赖并排序
     * 
     * @return 返回排好序后的依赖图节点集，忽略图中无效依赖，存在循环依赖时抛出异常
     */
    private List<E> sortNodeList() {
        List<E> nodeList = getNodeList();
        if (null == nodeList || nodeList.isEmpty()) {
            return nodeList;
        }
        /**
         * 记录所有有效的节点，以便于去除依赖中的无效节点
         */
        Map<String, DependGraphNodeAdapter<E>> nodeMap = new LinkedHashMap<String, DependGraphNodeAdapter<E>>();
        for (E node : nodeList) {// 设置所有节点
            String id = node.getId();
            nodeMap.put(id, new DependGraphNodeAdapter<E>(node));
        }
        List<E> sortList = new ArrayList<E>();
        for (DependGraphNodeAdapter<E> node : nodeMap.values()) {// 分析依赖
            sortNodeList(node, nodeMap, sortList);
        }
        return sortList;
    }

    /**
     * 递归分析依赖
     * 
     * @param node
     * @param nodeMap
     * @param sortList
     */
    private void sortNodeList(DependGraphNodeAdapter<E> node, Map<String, DependGraphNodeAdapter<E>> nodeMap, List<E> sortList) {
        int status = node.nodeStatus;
        if (1 == status) {// 依赖已经分析完成，直接回溯
            goBackSortNodeList(node, nodeMap, sortList);
        } else if (2 == status) {// 正在分析依赖中，如果再次分析到该节点，说明存成循环依赖
            DependGraphNodeAdapter<E> srcNode = node.srcNode;
            String srcNodeId = srcNode.getId();
            String id = node.getId();
            StringBuffer sb = new StringBuffer(id);
            while (!srcNodeId.equals(id)) {
                sb.insert(0, srcNodeId + "-->");
                srcNode = srcNode.srcNode;// .getSrcNode();
                if (null == srcNode) {
                    break;
                }
                srcNodeId = srcNode.getId();
            }
            sb.insert(0, id + "-->");
            Throw.throwRuntimeException(ExceptionCodes.BF010001, sb);
        } else {
            node.nodeStatus = 2;
            int index = node.dependIndex;
            List<String> depends = node.getDepends();
            // 如果没有依赖或者依赖已经全部分析完成，设置为分析完成状态，然后再回溯分析
            if (null == depends || depends.isEmpty() || index == depends.size()) {
                sortList.add(node.node);
                node.nodeStatus = 1;
                goBackSortNodeList(node, nodeMap, sortList);
            } else {// 分析第index个依赖
                String depend = depends.get(index);
                DependGraphNodeAdapter<E> dependNode = nodeMap.get(depend);
                if (null == dependNode) {
                    depends.remove(depend);
                    CommonLogger.warn("没有找到" + node.getId() + "的依赖" + depend + "，将忽略该依赖");
                    if (index == depends.size()) {// 移除无效依赖之后，再检查是否分析完毕
                        sortList.add(node.node);
                        node.nodeStatus = 1;
                        goBackSortNodeList(node, nodeMap, sortList);
                    }
                } else {
                    dependNode.srcNode = node;// 设置分析的下一个节点的来源节点为当前节点
                    sortNodeList(dependNode, nodeMap, sortList);
                }
            }
        }
    }

    /**
     * 回溯分析
     * 
     * @param node
     * @param nodeMap
     * @param sortList
     */
    private void goBackSortNodeList(DependGraphNodeAdapter<E> node, Map<String, DependGraphNodeAdapter<E>> nodeMap, List<E> sortList) {
        DependGraphNodeAdapter<E> srcNode = node.srcNode;
        if (null != srcNode) {
            node.srcNode = null;
            srcNode.nodeStatus = 0;
            srcNode.dependIndex = srcNode.dependIndex + 1;
            sortNodeList(srcNode, nodeMap, sortList);
        }
    }

    private static class DependGraphNodeAdapter<E extends IDependNode> {
        /**
         * 原节点
         */
        private E node;
        /**
         * 从哪个节点导致当前节点处于依赖分析中
         */
        private DependGraphNodeAdapter<E> srcNode;

        /**
         * 0 未开始分析依赖 1 依赖已经分析完成，直接回溯 2 正在分析依赖中，如果再次分析到该节点，说明存成循环依赖
         */
        private int nodeStatus;

        /**
         * 已经分析了的依赖节点的最大索引
         */
        private int dependIndex;

        private DependGraphNodeAdapter(E node) {
            this.node = node;
            this.nodeStatus = 0;
            this.dependIndex = 0;
        }

        private String getId() {
            return node.getId();
        }

        private List<String> getDepends() {
            return node.getDepends();
        }
    }
}
