package com.forms.beneform4j.core.util.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 简单的XML解析错误处理类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public class BaseErrorHandler implements ErrorHandler {

    /**
     * 警告
     */
    private final List<SAXParseException> warns = new ArrayList<SAXParseException>();

    /**
     * 错误
     */
    private final List<SAXParseException> errors = new ArrayList<SAXParseException>();

    /**
     * 致命
     */
    private final List<SAXParseException> fatals = new ArrayList<SAXParseException>();

    /**
     * 有警告时是否抛出异常
     */
    private boolean throwWhenWarning = false;

    /**
     * 有错误时是否抛出异常，如果{@link #throwWhenWarning}为true，有错误时肯定抛出异常
     */
    private boolean throwWhenError = true;

    /**
     * 有致命错误时是否抛出异常，如果{@link #throwWhenWarning}或{@link #throwWhenError}为true，则由fatal时肯定抛出异常
     */
    private boolean throwWhenFatal = true;

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        warns.add(exception);
        if (this.isThrowWhenWarning()) {
            throw exception;
        }
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        errors.add(exception);
        if (this.isThrowWhenWarning() || this.isThrowWhenError()) {
            throw exception;
        }
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        fatals.add(exception);
        if (this.isThrowWhenWarning() || this.isThrowWhenError() || this.isThrowWhenFatal()) {
            throw exception;
        }
    }

    public boolean isThrowWhenWarning() {
        return throwWhenWarning;
    }

    public void setThrowWhenWarning(boolean throwWhenWarning) {
        this.throwWhenWarning = throwWhenWarning;
    }

    public boolean isThrowWhenError() {
        return throwWhenError;
    }

    public void setThrowWhenError(boolean throwWhenError) {
        this.throwWhenError = throwWhenError;
    }

    public boolean isThrowWhenFatal() {
        return throwWhenFatal;
    }

    public void setThrowWhenFatal(boolean throwWhenFatal) {
        this.throwWhenFatal = throwWhenFatal;
    }

    public List<SAXParseException> getWarns() {
        return warns;
    }

    public List<SAXParseException> getErrors() {
        return errors;
    }

    public List<SAXParseException> getFatals() {
        return fatals;
    }
}
