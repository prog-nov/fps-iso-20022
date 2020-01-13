package com.forms.beneform4j.webapp.common.guider;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.beneform4j.webapp.common.guider.service.IGuiderService;
import com.forms.beneform4j.webapp.systemmanage.guide.service.IOperGuideService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 操作指引<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-28<br>
 */
@Service("guiderService")
public class GuiderService implements IGuiderService {

    @Resource(name = "operGuideService")
    IOperGuideService guideService;

    /**
     * 获取操作指引
     */
    @Override
    public Map<String, String> getGuider(String menuId) {
        return guideService.sFind(menuId);
    }
}
