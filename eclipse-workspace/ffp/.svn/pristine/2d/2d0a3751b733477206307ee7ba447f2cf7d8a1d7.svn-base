package com.forms.beneform4j.web.springmvc.download;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.forms.beneform4j.util.param.IParamService;
import com.forms.beneform4j.util.param.enums.IEnumParam;
import com.forms.beneform4j.util.param.enums.IEnumParamItem;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 枚举参数转换服务，主要用于下载文件中把枚举参数的代码转换为文本<br>
 * Author : LinJisong <br>
 * Version : 1.1.0 <br>
 * Since : 1.1.0 <br>
 * Date : 2017-2-23<br>
 */
@Component("enum")
public class EnumServiceRender {

    /**
     * 参数服务实现类
     */
    @Autowired(required = false)
    private IParamService service;

    public IParamService getService() {
        return service;
    }

    public void setService(IParamService service) {
        this.service = service;
    }

    public Object val(String type, String code) {
        if (null == service) {
            return code;
        }
        IEnumParam ep = service.getEnumParameter(type);
        if (null == ep) {
            return code;
        } else {
            IEnumParamItem item = ep.getItem(code);
            if (null == item) {
                return code;
            } else {
                return item.getDataText();
            }
        }
    }
}
