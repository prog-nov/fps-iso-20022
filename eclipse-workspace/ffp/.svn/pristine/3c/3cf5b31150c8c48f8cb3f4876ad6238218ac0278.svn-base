package com.forms.beneform4j.util.tree.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.forms.beneform4j.util.tree.ITreeNode;
import com.forms.beneform4j.util.tree.ITreeNodeFilter;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象树节点模型实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-9<br>
 */
public class TreeNode implements ITreeNode {

    private static final long serialVersionUID = 5319603439022859019L;

    /**
     * 复制节点时不需要复制的属性
     */
    private static final List<String> notCopyProperties = Arrays.asList("serialVersionUID", "notCopyProperties", "id", "depth", "path", "leaf", "parent", "children", "checkStatus");

    /**
     * =============在构建树的过程中需要设置的属性=================
     */
    private int id = -1; // 节点索引，虚拟根节点为0

    private int depth; // 节点深度，虚拟根节点为0

    private String path; // 节点路径

    private boolean leaf = true; // 是否为叶子节点

    private ITreeNode parent; // 父节点

    private List<ITreeNode> children; // 直接子节点

    private int checkStatus = -1; // 选择状态

    /**
     * =============节点业务数据，一般需要持久化的属性=================
     */
    private String code; // 节点代码

    private String parentCode; // 父节点代码

    private String text; // 节点文本

    private String icon; // 节点图标

    private String url; // 节点URL

    private String des; // 描述

    private int seqno; // 排序

    private String userData; // 用户数据

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    @Override
    public ITreeNode getParent() {
        return parent;
    }

    public void setParent(ITreeNode parent) {
        this.parent = parent;
    }

    @Override
    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    @Override
    public List<ITreeNode> getChildren() {
        return children;
    }

    @Override
    public List<ITreeNode> getChildren(ITreeNodeFilter filter) {
        if (children == null) {
            return null;
        } else {
            List<ITreeNode> list = new ArrayList<ITreeNode>();
            for (ITreeNode node : children) {
                if (null == filter || filter.accept(node)) {
                    list.add(node);
                }
            }
            return list;
        }
    }

    @Override
    public List<ITreeNode> getAllChildren(boolean addSelf) {
        return getAllChildren(null, addSelf, true);
    }

    @Override
    public List<ITreeNode> getAllChildren(ITreeNodeFilter filter, boolean addSelf, boolean firstFilter) {
        if (firstFilter || null == filter) {
            List<ITreeNode> sons = getChildren(filter);
            if (null == sons || sons.isEmpty()) {
                if (addSelf) {
                    sons = new ArrayList<ITreeNode>();
                    sons.add(this);
                }
                return sons;
            } else {
                if (addSelf) {
                    sons.add(this);
                }
                List<ITreeNode> children = new ArrayList<ITreeNode>();
                for (ITreeNode child : sons) {
                    add(filter, child, children);
                }
                return children;
            }
        } else {
            List<ITreeNode> children = getAllChildren(null, addSelf, true);// 先不用过滤器
            if (null == children || children.isEmpty()) {
                return null;
            } else {
                List<ITreeNode> list = new ArrayList<ITreeNode>();
                for (ITreeNode node : children) {
                    if (filter.accept(node)) {
                        list.add(node);
                    }
                }
                return list;
            }
        }
    }

    private void add(ITreeNodeFilter filter, ITreeNode node, List<ITreeNode> list) {
        list.add(node);
        List<ITreeNode> children = node.getChildren(filter);
        if (null != children && !children.isEmpty()) {
            for (ITreeNode child : children) {
                add(filter, child, list);
            }
        }
    }

    @Override
    public Set<String> getAllChildrenCodes(boolean addSelf) {
        Set<String> codes = new HashSet<String>();
        List<ITreeNode> allChildren = getAllChildren(addSelf);
        if (null != allChildren && !allChildren.isEmpty()) {
            for (ITreeNode node : allChildren) {
                codes.add(node.getCode());
            }
        }
        return codes;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public int getSeqno() {
        return seqno;
    }

    public void setSeqno(int seqno) {
        this.seqno = seqno;
    }

    /**
     * 添加一个子节点
     * 
     * @param child
     * @return
     */
    public void addChild(ITreeNode child) {
        if (null != child) {
            if (null == this.children) {
                this.children = new ArrayList<ITreeNode>();
            }
            if (!this.children.contains(child)) {
                this.children.add(child);
            } /*
               * else{ throw new
               * RuntimeException("has exist the child --> the code is ["+child.getCode()+"]"); }
               */
            if (this.isLeaf()) {
                this.leaf = false;
            }
        }
    }

    @Override
    public ITreeNode copyNodeData() {
        try {
            Class<?> cls = this.getClass();
            ITreeNode bean = (ITreeNode) cls.newInstance();
            for (;;) {
                for (Field field : cls.getDeclaredFields()) {
                    String name = field.getName();
                    if (!notCopyProperties.contains(name)) {
                        try {
                            if (!field.isAccessible()) {
                                field.setAccessible(true);
                            }
                            field.set(bean, field.get(this));
                        } catch (Exception e) {
                            // e.printStackTrace();
                        }
                    }
                }
                if (cls.equals(TreeNode.class)) {
                    break;
                } else {
                    cls = cls.getSuperclass();
                }
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取用户数据
     * 
     * @return
     */
    @Override
    public String getUserData() {
        return userData;
    }

    /**
     * 设置用户数据
     * 
     * @param userData
     */
    @Override
    public void setUserData(String userData) {
        this.userData = userData;
    }

    /**
     * 覆盖Object中的方法
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" Node :").append(this.text);
        sb.append(" id is ").append(this.id);
        if (null != this.code) {
            sb.append(" code is :").append(this.code);
        }
        if (null != this.parent) {
            sb.append(" parent id is :").append(this.parent.getId());
        }
        if (null != this.path) {
            sb.append(" path is " + this.path);
        }
        sb.append(" \n");
        return sb.toString();
    }

    /**
     * 判断是否为指定节点的子节点，一般来说，子类可覆盖
     * 
     * @param parent 父节点
     * @return 是否为参数节点的子节点
     */
    public boolean isChild(ITreeNode parent) {
        String pCode = parent.getCode();
        String parentCode = getParentCode();
        return pCode == null ? parentCode == null : pCode.equalsIgnoreCase(parentCode);
    }
}
