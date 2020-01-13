package com.forms.beneform4j.core.util.parser.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.core.io.Resource;

import com.forms.beneform4j.core.util.parser.IParseEventListener;
import com.forms.beneform4j.core.util.parser.IParserContext;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 解析侦听链，解析前事件依次顺序执行，解析失败、解析完成事件依次反序执行<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public class ParseEventListenerChain implements IParseEventListener {

    private List<? extends IParseEventListener> listeners;

    public void setListeners(List<? extends IParseEventListener> listeners) {
        this.listeners = listeners;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBeforeParse(IParserContext context, Collection<Resource> resources) {
        if (null != listeners) {
            for (IParseEventListener listener : listeners) {
                listener.onBeforeParse(context, resources);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFinishParse(IParserContext context, Collection<Resource> resources) {
        if (null != listeners) {
            for (int i = listeners.size() - 1; i >= 0; i--) {
                IParseEventListener listener = listeners.get(i);
                listener.onFinishParse(context, resources);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBeforeParseOneResource(IParserContext context, Resource resource) {
        if (null != listeners) {
            for (IParseEventListener listener : listeners) {
                listener.onBeforeParseOneResource(context, resource);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFailureParseOneResource(IParserContext context, Resource resource, Exception e) {
        if (null != listeners) {
            for (int i = listeners.size() - 1; i >= 0; i--) {
                IParseEventListener listener = listeners.get(i);
                listener.onFailureParseOneResource(context, resource, e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSuccessParseOneResource(IParserContext context, Resource resource) {
        if (null != listeners) {
            for (int i = listeners.size() - 1; i >= 0; i--) {
                IParseEventListener listener = listeners.get(i);
                listener.onSuccessParseOneResource(context, resource);
            }
        }
    }
}
