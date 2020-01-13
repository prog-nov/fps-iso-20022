package com.forms.beneform4j.core.util.parser.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.parser.IParseEventListener;
import com.forms.beneform4j.core.util.parser.IParser;
import com.forms.beneform4j.core.util.parser.IParserContext;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象的解析器实现类，实现防止资源重复解析，解析侦听等功能<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public abstract class AbstractParser<E extends IParserContext> implements IParser<E> {

    /**
     * 已经被解析的资源
     */
    private final ThreadLocal<Set<Resource>> resourcesCurrentlyBeingLoaded = new ThreadLocal<Set<Resource>>();

    /**
     * 当前解析环境
     */
    private final ThreadLocal<E> currentParserContext = new ThreadLocal<E>();

    /**
     * 默认解析回调
     */
    private final ParseCallback<E, Void> defaultParseCallback = new ParseCallback<E, Void>() {
        @Override
        public Void callback(E parserContext, EncodedResource resource) throws IOException {
            AbstractParser.this.doParse(parserContext, resource);
            return null;
        }
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(E parserContext, String locationPattern) {
        parse(parserContext, new String[] {locationPattern});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(E parserContext, String[] locationPatterns) {
        Environment environment = extractEnvironment(parserContext);
        Set<Resource> resources = CoreUtils.getResources(environment, locationPatterns);
        doParse(parserContext, resources, defaultParseCallback);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(E parserContext, InputStream inputStream) {
        parse(parserContext, new InputStreamResource(inputStream));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(E parserContext, Resource resource) {
        doParse(parserContext, Arrays.asList(resource), defaultParseCallback);
    }

    /**
     * 执行解析
     * 
     * @param parserContext 解析环境
     * @param resource 解析资源
     * @throws IOException 抛出的异常
     */
    protected abstract void doParse(E parserContext, EncodedResource resource) throws IOException;

    /**
     * 执行解析，解析一组资源
     * 
     * @param parserContext 解析环境
     * @param resources 解析资源
     * @param callback 回调函数
     * @return 解析结果
     */
    protected <R> List<R> doParse(E parserContext, Collection<Resource> resources, ParseCallback<E, R> callback) {
        if (null != resources && !resources.isEmpty()) {
            IParseEventListener listener = parserContext.getEventListener();// 侦听器
            try {
                if (null != listener) {
                    listener.onBeforeParse(parserContext, resources);
                }
                currentParserContext.set(parserContext);// 将执行环境存到当前线程
                List<R> rs = new ArrayList<R>();
                for (Resource resource : resources) {
                    R r = nonRepeatedParse(parserContext, resource, listener, callback);
                    rs.add(r);
                }
                return rs;
            } finally {
                try {
                    if (null != listener) {
                        listener.onFinishParse(parserContext, resources);
                    }
                } finally {
                    currentParserContext.remove();// 从当前线程移除执行环境
                }
            }
        }
        return null;
    }

    /**
     * 获取解析上下文环境，供子类调用
     * 
     * @return 上下文环境
     */
    protected E getParserContext() {
        return currentParserContext.get();
    }

    /**
     * 执行解析，保证不重复解析资源
     * 
     * @param parserContext 上下文环境
     * @param resource 资源对象
     * @param listener 侦听器
     * @param callback 资源解析回调
     * @return 解析结果
     */
    private <R> R nonRepeatedParse(E parserContext, Resource resource, IParseEventListener listener, ParseCallback<E, R> callback) {
        Set<Resource> currentResources = this.resourcesCurrentlyBeingLoaded.get();// 当前线程已经解析的资源对象
        if (currentResources == null) {
            currentResources = new HashSet<Resource>(4);
            this.resourcesCurrentlyBeingLoaded.set(currentResources);
        }
        if (!currentResources.add(resource)) {
            throw new RuntimeException("Detected cyclic loading of " + resource + " !");
        }
        try {
            if (null != listener) {
                listener.onBeforeParseOneResource(parserContext, resource);
            }
            EncodedResource encodedResource = decorateEncodedResource(resource);
            R rs = callback.callback(parserContext, encodedResource);// 执行真正的解析操作
            if (null != listener) {
                listener.onSuccessParseOneResource(parserContext, resource);
            }
            return rs;
        } catch (Exception ex) {
            if (null != listener) {
                listener.onFailureParseOneResource(parserContext, resource, ex);
            }
            throw new RuntimeException("IOException parsing from " + resource, ex);
        } finally {
            currentResources.remove(resource);
            if (currentResources.isEmpty()) {
                this.resourcesCurrentlyBeingLoaded.remove();
            }
        }
    }

    /**
     * 编码装饰，子类可覆盖，也可不覆盖
     * 
     * @param resource 资源对象
     * @return 含字符集编码的资源对象
     */
    protected EncodedResource decorateEncodedResource(Resource resource) {
        EncodedResource encodedResource = null;
        if (!(resource instanceof EncodedResource)) {
            encodedResource = new EncodedResource(resource);
        } else {
            encodedResource = (EncodedResource) resource;
        }
        return encodedResource;
    }

    /**
     * 根据当前环境解析path，子类也可调用
     * 
     * @param environment 环境
     * @param path 路径
     * @return 解析变量之后的路径
     */
    protected String resolvePath(Environment environment, String path) {
        return environment == null ? path : environment.resolveRequiredPlaceholders(path);
    }

    /**
     * 获取当前环境
     * 
     * @param parserContext 上下文环境
     * @return 当前环境
     */
    protected Environment extractEnvironment(E parserContext) {
        Environment environment = parserContext.getEnvironment();
        return environment;
    }

    /**
     * 解析回调接口
     * 
     * @param <E> 上下文环境
     * @param <R> 解析结果
     */
    protected interface ParseCallback<E, R> {
        R callback(E parserContext, EncodedResource resource) throws IOException;
    }
}
