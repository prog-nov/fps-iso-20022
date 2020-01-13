package com.forms.beneform4j.core.dao.mybatis.call;

import org.apache.ibatis.session.RowBounds;

import com.forms.beneform4j.core.dao.call.ICallResult;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 调用存储过程的Mybatis参数适配类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24 <br>
 */
public class CallAdapter extends RowBounds {

    /**
     * 存储过程返回结果
     */
    private ICallResult callResult;

    /**
     * 返回调用存储过程的返回结果
     * 
     * @return ICallResult对象
     */
    public ICallResult getCallResult() {
        return callResult;
    }

    /**
     * 设置调用存储过程的返回结果
     * 
     * @param callResult
     */
    public void setCallResult(ICallResult callResult) {
        this.callResult = callResult;
    }
}
