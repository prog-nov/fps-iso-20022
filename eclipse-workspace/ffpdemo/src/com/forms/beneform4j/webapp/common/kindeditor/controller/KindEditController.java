package com.forms.beneform4j.webapp.common.kindeditor.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.common.kindeditor.service.IKindeditorService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 富文本附件（图片、文档等）的后台请求处理<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-28<br>
 */
@Controller
public class KindEditController {

    @Resource(name = "kindeditorService")
    private IKindeditorService kindeditorService;

    @RequestMapping("/attached/{fileType}/{uploadDate}/{fileName}.{suffix}")
    public void attached(HttpServletRequest request, HttpServletResponse response, @PathVariable String fileType, @PathVariable String uploadDate, @PathVariable String suffix, @PathVariable String fileName) throws IOException {
        File f = kindeditorService.sGetServerFile(fileType + File.separator + uploadDate + File.separator, fileName + "." + suffix);
        kindeditorService.sOutputAttachedFile(f, new BufferedOutputStream(response.getOutputStream()));
    }

    /**
     * 文件上传处理
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/kindEdit/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> fileUpload(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html; charset=UTF-8");
        if (!ServletFileUpload.isMultipartContent(request)) {
            return kindeditorService.sGetError(Tool.LOCALE.getMessage("systemmanager.doc.file.please.select"));
        }
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        upload.setHeaderEncoding("UTF-8");
        List<?> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException fe) {
            return kindeditorService.sGetError(Tool.LOCALE.getMessage("systemmanager.doc.file.upload.error"));
        }
        return kindeditorService.sFileUpload(request.getParameter("dir"), request.getContextPath(), items);
    }

    /**
     * 文件管理处理，可以查看服务上的附件
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/kindEdit/fileManager")
    @ResponseBody
    public Object fileManager(HttpServletRequest request, HttpServletResponse response) {
        String dirName = request.getParameter("dir");
        String path = !Tool.CHECK.isBlank(request.getParameter("path")) ? request.getParameter("path") : "";
        // 排序形式，name or size or type
        String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";
        return kindeditorService.sFileManager(dirName, request.getContextPath(), path, order);
    }
}
