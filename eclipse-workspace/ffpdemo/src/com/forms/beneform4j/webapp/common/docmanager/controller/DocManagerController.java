package com.forms.beneform4j.webapp.common.docmanager.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.web.WebUtils;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.User;
import com.forms.beneform4j.web.upload.IUploadFile;
import com.forms.beneform4j.webapp.common.docmanager.bean.DataParamBean;
import com.forms.beneform4j.webapp.common.docmanager.form.DocForm;
import com.forms.beneform4j.webapp.common.docmanager.service.IDocManagerService;
import com.forms.beneform4j.webapp.common.param.service.ParamHolder;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 文件下载控制类<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-16<br>
 */
@Controller
@RequestMapping("/doc")
public class DocManagerController {

    @Resource(name = "docManagerService")
    private IDocManagerService docManagerService;

    /**
     * @param attachId 1、不为空，且docIds有值，则下载附件对应的文档（可能多个）进行压纹后传到前端 </br>
     *        2、 如果docIds没有值，则下载附件所有的文档
     * @param docIds 1、如果attachId为空，此值必须有值，此值也为空，则异常 </br>
     *        2、 如果attachId为空时， 此值如果是一个文档id,则直接下载</br>
     *        3、如果是多个文档ID，则会根据文档ID下载对应的文件，并压缩返回给前台
     * @param attr
     * @return
     * @throws Exception
     */
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(String attachId, String[] docIds, RedirectAttributes attr) throws Exception {
        DataParamBean fileBean = docManagerService.sDownLoadFile(attachId, docIds);
        HttpHeaders headers = getHeader(fileBean.getFileName());
        try {
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(fileBean.getFile()), headers, HttpStatus.OK);
        } catch (Exception e) {
            attr.addAttribute("msgInfo", e.getMessage());
        }
        return null;
    }

    /**
     * 插件上传文件 文件上传的统一入口
     * 
     * @param file 固定写法，为一个文件接口类，Spring会根据这种类型的接口参数实现文件上传的处理
     * @param userId 当前操作人员
     * @return 返回结果为MD5，可以通过前端回调参数当中获取 例如：data
     */
    @RequestMapping("/upload")
    @JsonBody
    public DocForm upload(IUploadFile file, @User String userId) {
        DocForm docForm = new DocForm();
        docForm.setDocName(file.getOriginalFilename());// 设置原文件名
        docForm.setSuffix(FilenameUtils.getExtension(docForm.getDocName()));// 设置后缀
        docForm.setDocId(Tool.STRING.getRandomKeyId());
        docForm.setInstOper(userId);
        // 获取临时目录
        String tempDir = FilenameUtils.getFullPathNoEndSeparator(ParamHolder.getParameter("DOC_FILE_PATH_TEMP")) + "/" + Tool.DATE.getDate() + "/" + docForm.getDocId() + "." + docForm.getSuffix();
        docForm.setStorePath(tempDir);

        File f = new File(docForm.getStorePath());
        File dir = new File(FilenameUtils.getFullPath(tempDir));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 存放文件到临时目录
        file.transferTo(f);
        // 将数据保存到后台临时数据库
        docManagerService.sPlugUpload(docForm);
        return docForm;
    }

    /**
     * 批量删除选择的文件
     * 
     * @param docId
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    @JsonBody
    public int toDelete(String docId) throws Exception {
        return docManagerService.sDeleteFile(docId);
    }

    /**
     * 生成下载流头部信息，文件信息等
     * 
     * @param fileName 文件名
     * @return
     * @throws UnsupportedEncodingException
     */
    private HttpHeaders getHeader(String fileName) throws UnsupportedEncodingException {
        HttpHeaders headers = new HttpHeaders();
        HttpServletRequest request = WebUtils.getRequest();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setConnection("close");
        // 解决中文乱码的问题，IE和Chrome可以用URLEncode,Firefox要用ISO8859-1重新编码
        // 欧林海 2016-08-05
        headers.setContentDispositionFormData("attachment", request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0 ? new String(fileName.getBytes("UTF-8"), "ISO8859-1") : URLEncoder.encode(fileName, "UTF-8"));
        return headers;
    }
}
