package com.forms.beneform4j.util.param.tree.impl;

import com.forms.beneform4j.util.param.tree.ITreeParamNode;
import com.forms.beneform4j.util.tree.impl.TreeNode;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 树型参数节点实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-12<br>
 */
public class TreeParamNode extends TreeNode implements ITreeParamNode {

    /**
     * 
     */
    private static final long serialVersionUID = -2764873861475501074L;

    private String paramCode;

    @Override
    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    @Override
    public String getDataCode() {
        return getCode();
    }

    @Override
    public String getDataText() {
        return getText();
    }

    @Override
    public String getDataParam() {
        return getUserData();
    }
}
