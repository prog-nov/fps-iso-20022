package com.forms.beneform4j.util.json.serial.wrapper.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.forms.beneform4j.util.json.serial.wrapper.IJsonWrapper;
import com.forms.beneform4j.util.tree.ITree;
import com.forms.beneform4j.util.tree.TreeFactory;
import com.forms.beneform4j.util.tree.impl.TreeNode;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 树型结果返回结果JSON包装器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-18<br>
 */
public class TreeJsonWrapper implements IJsonWrapper {

    /**
     * 只有一个节点时是否返回数组，默认为true
     */
    private boolean returnArrayWhenOnlyOneNode = true;

    @SuppressWarnings("unchecked")
    @Override
    public Object wrap(Object original) {
        if (original == null) {
            return null;
        }

        List<?> rs = null;
        if (original instanceof ITree) {
            rs = ((ITree<?>) original).getRoot().getChildren();
        } else if (original instanceof Collection) {
            List<TreeNode> nodes = new ArrayList<TreeNode>();
            for (Iterator<TreeNode> i = ((Collection<TreeNode>) original).iterator(); i.hasNext();) {
                nodes.add(i.next());
            }
            rs = TreeFactory.rootChildren(nodes);
        }

        if (null != rs) {
            if (rs.size() == 1 && !returnArrayWhenOnlyOneNode) {
                return rs.get(0);
            } else {
                return rs;
            }
        } else {
            return original;
        }
    }

    public boolean isReturnArrayWhenOnlyOneNode() {
        return returnArrayWhenOnlyOneNode;
    }

    public void setReturnArrayWhenOnlyOneNode(boolean returnArrayWhenOnlyOneNode) {
        this.returnArrayWhenOnlyOneNode = returnArrayWhenOnlyOneNode;
    }
}
