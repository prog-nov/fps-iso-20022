package com.forms.beneform4j.util.param.tree;

import com.forms.beneform4j.util.param.IParam;
import com.forms.beneform4j.util.tree.ITree;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 树型参数接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-12<br>
 */
public interface ITreeParam extends ITree<ITreeParamNode>, IParam {

    /**
     * 构建树
     */
    public void build();
}
