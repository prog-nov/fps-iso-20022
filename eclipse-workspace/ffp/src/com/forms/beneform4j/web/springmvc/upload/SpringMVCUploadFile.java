package com.forms.beneform4j.web.springmvc.upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.web.exception.WebExceptionCodes;
import com.forms.beneform4j.web.upload.IUploadFile;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用Spring MVC中MultipartFile实现的上传文件<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-25<br>
 */
public class SpringMVCUploadFile implements IUploadFile {

    private MultipartFile delegete;

    public SpringMVCUploadFile(MultipartFile file) {
        this.delegete = file;
    }

    public String getName() {
        return delegete.getName();
    }

    public String getOriginalFilename() {
        return delegete.getOriginalFilename();
    }

    public String getContentType() {
        return delegete.getContentType();
    }

    public boolean isEmpty() {
        return delegete.isEmpty();
    }

    public long getSize() {
        return delegete.getSize();
    }

    public byte[] getBytes() {
        try {
            return delegete.getBytes();
        } catch (IOException e) {
            throw Throw.createRuntimeException(WebExceptionCodes.BF060001, e, getOriginalFilename());
        }
    }

    public InputStream getInputStream() {
        try {
            return delegete.getInputStream();
        } catch (IOException e) {
            throw Throw.createRuntimeException(WebExceptionCodes.BF060001, e, getOriginalFilename());
        }
    }

    public void transferTo(File file) {
        try {
            delegete.transferTo(file);
        } catch (IOException e) {
            throw Throw.createRuntimeException(WebExceptionCodes.BF060001, e, getOriginalFilename());
        }
    }

    public void deleteTmpFile() {

    }
}
