package com.forms.beneform4j.demo.upload.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forms.beneform4j.demo.upload.bean.UploadBean;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.upload.IUploadFile;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 上传演示<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-28<br>
 */
@Controller
public class UploadController {

    @RequestMapping("doUpload")
    @JsonBody
    public Map<String, Object> uploadDemo(IUploadFile file, @RequestParam(value = "testParam", required = false) String testParam) {

        Map<String, Object> response = new HashMap<String, Object>();
        response.put("param", testParam);
        response.put("fileName", file.getName());
        response.put("fileId", Tool.STRING.getRandomKeyId());
        response.put("fileSize", file.getSize());
        return response;
    }

    @RequestMapping("save")
    @JsonBody
    public int save(UploadBean bean) {
        return this.doSave(bean);
    }

    @RequestMapping("initList")
    @JsonBody
    public List<String> initList() {
        List<String> list = new ArrayList<String>();
        list.add("文件一");
        list.add("文件二");
        return list;
    }

    @RequestMapping("preUpload")
    public String preUpload() {
        return "demo/upload";
    }

    private int doSave(UploadBean bean) {
        System.out.println(bean.getTitle());
        System.out.println(bean.getContent());
        if (null != bean.getFileKeys()) {
            System.out.println(bean.getFileKeys().length);
        }
        System.out.println("===");
        if (null != bean.getFileKeysAuto()) {
            System.out.println(bean.getFileKeysAuto().length);
        }
        return 0;
    }

}
