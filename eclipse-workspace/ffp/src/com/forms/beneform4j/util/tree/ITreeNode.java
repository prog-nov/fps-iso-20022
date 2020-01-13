package com.forms.beneform4j.util.tree;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 树节点模型接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-9<br>
 */
public interface ITreeNode extends Serializable {

    /**
     * 虚拟根节点内部ID
     */
    public int ROOT_ID = 0;

    /**
     * 虚拟根节点深度
     */
    public int ROOT_DEPTH = 0;

    /**
     * 对于叶子节点表示未选中状态 对于非叶子节点表示所有子节点都没有选中
     */
    public int STATUS_UNCHECKED = 0;

    /**
     * 对于叶子节点表示选中状态 对于非叶子节点表示所有子节点全部选中
     */
    public int STATUS_CHECKED = 1;

    /**
     * 对于叶子节点无意义 对于非叶子节点表示有部分子节点选中，有部分子节点未选中
     */
    public int STATUS_PART_CHECKED = 2;

    /**
     * 获取内部节点ID（虚拟根节点ID为0）
     * 
     * @return 内部节点ID
     */
    public int getId();

    /**
     * 设置内部节点ID
     * 
     * @param id 内部节点ID
     */
    public void setId(int id);

    /**
     * 获取节点在树中的深度（虚拟根节点深度为0）
     * 
     * @return 节点深度
     */
    public int getDepth();

    /**
     * 设置节点深度
     * 
     * @param depth 节点深度
     */
    public void setDepth(int depth);

    /**
     * 获取节点路径
     * 
     * @return 节点路径
     */
    public String getPath();

    /**
     * 设置节点路径
     * 
     * @param path 节点路径
     */
    public void setPath(String path);

    /**
     * 获取父节点
     * 
     * @return 父节点
     */
    public ITreeNode getParent();

    /**
     * 设置父节点
     * 
     * @param parent 父节点
     */
    public void setParent(ITreeNode parent);

    /**
     * 是否为叶子节点
     * 
     * @return 是否叶子节点
     */
    public boolean isLeaf();

    /**
     * 获取选择状态 -1表示未设置
     * <p>
     * <ul>
     * <li>1.叶子节点 0 未选中 1 选中
     * <li>2.非叶子节点 0 所有子节点全部未选中 1 所有子节点全部选中 2 部分子节点选中
     * </ul>
     * </p>
     * 
     * @return 选择状态
     */
    public int getCheckStatus();

    /**
     * 获取节点代码
     * 
     * @return 节点代码
     */
    public String getCode();

    /**
     * 设置节点代码
     * 
     * @param code 节点代码
     */
    public void setCode(String code);

    /**
     * 获取节点文本
     * 
     * @return 节点文本
     */
    public String getText();

    /**
     * 设置节点文本
     * 
     * @param text
     */
    public void setText(String text);

    /**
     * 获取父节点
     * 
     * @return 父节点
     */
    public String getParentCode();

    /**
     * 获取节点图标/样式
     * 
     * @return 节点图标/样式
     */
    public String getIcon();

    /**
     * 获取节点链接
     * 
     * @return 节点链接
     */
    public String getUrl();

    /**
     * 获取节点描述
     * 
     * @return 节点描述
     */
    public String getDes();

    /**
     * 获取节点排序
     * 
     * @return 节点排序
     */
    public int getSeqno();

    /**
     * 判断parent是否为当前节点的父节点
     * 
     * @param parent 待判断父节点的节点
     * @return parent是否为当前节点的父节点
     */
    public boolean isChild(ITreeNode parent);

    /**
     * 添加子节点
     * 
     * @param child 子节点
     */
    public void addChild(ITreeNode child);

    /**
     * 获取直接子节点集
     * 
     * @return 直接子节点集
     */
    public List<ITreeNode> getChildren();

    /**
     * 获取直接子节点集，并使用过滤器过滤
     * 
     * @param filter 节点过滤器
     * @return 过滤后的直接子节点集
     */
    public List<ITreeNode> getChildren(ITreeNodeFilter filter);

    /**
     * 获取所有子节点(深度优先，不包含自身)
     * 
     * @param addSelf 是否添加当前节点本身
     * @return 所有子节点
     */
    public List<ITreeNode> getAllChildren(boolean addSelf);

    /**
     * 根据过滤器获取所有子节点(深度优先，不包含自身)
     * 
     * @param filter 节点过滤器
     * @param addSelf 是否添加当前节点本身
     * @param firstFilter true 逐级使用过滤器，再获取子节点；false 先获取所有子节点，再使用过滤器过滤
     * @return 过滤后的子节点集
     */
    public List<ITreeNode> getAllChildren(ITreeNodeFilter filter, boolean addSelf, boolean firstFilter);

    /**
     * 返回所有子节点的代码
     * 
     * @param addSelf 是否添加节点本身的代码
     * @return 所有子节点的代码
     */
    public Set<String> getAllChildrenCodes(boolean addSelf);

    /**
     * 获取用户数据
     * 
     * @return 用户数据
     */
    public String getUserData();

    /**
     * 设置用户数据
     * 
     * @param userData 用户数据
     */
    public void setUserData(String userData);

    /**
     * 复制节点数据，不复制节点关系
     * 
     * @return 复制的节点（只包括业务属性，不包括构建树时生成的属性）
     */
    public ITreeNode copyNodeData();
}
