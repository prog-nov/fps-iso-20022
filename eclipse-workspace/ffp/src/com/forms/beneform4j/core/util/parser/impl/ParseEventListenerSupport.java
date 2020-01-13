package com.forms.beneform4j.core.util.parser.impl;

import java.util.Collection;

import org.springframework.core.io.Resource;

import com.forms.beneform4j.core.util.parser.IParseEventListener;
import com.forms.beneform4j.core.util.parser.IParserContext;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 解析器侦听器支持类，侦听器的空实现类，让开发人员可以自由选择实现其中的某个侦听方法<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public class ParseEventListenerSupport implements IParseEventListener {

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBeforeParse(IParserContext context, Collection<Resource> resources) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFinishParse(IParserContext context, Collection<Resource> resources) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBeforeParseOneResource(IParserContext context, Resource resource) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFailureParseOneResource(IParserContext context, Resource resource, Exception e) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSuccessParseOneResource(IParserContext context, Resource resource) {

    }
}
