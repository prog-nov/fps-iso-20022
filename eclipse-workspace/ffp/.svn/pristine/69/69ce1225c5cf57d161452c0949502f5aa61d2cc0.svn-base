package com.forms.beneform4j.core.util.parser.impl;

import org.springframework.beans.factory.parsing.ProblemReporter;
import org.springframework.beans.factory.parsing.SourceExtractor;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import com.forms.beneform4j.core.util.config.BaseConfig;
import com.forms.beneform4j.core.util.parser.IParseEventListener;
import com.forms.beneform4j.core.util.parser.IParserContext;
import com.forms.beneform4j.core.util.parser.ParserComponents;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 解析器上下文环境实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public class ParserContext implements IParserContext {

    private ResourceLoader resourceLoader;

    private ProblemReporter problemReporter;

    private IParseEventListener eventListener;

    private SourceExtractor sourceExtractor;

    private Environment environment;

    /**
     * {@inheritDoc}
     */
    public ResourceLoader getResourceLoader() {
        if (null == this.resourceLoader) {
            this.resourceLoader = BaseConfig.getResourcePatternResolver();
        }
        return resourceLoader;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * {@inheritDoc}
     */
    public ProblemReporter getProblemReporter() {
        if (null == this.problemReporter) {
            this.problemReporter = ParserComponents.getFactory().newProblemReporter();
        }
        return problemReporter;
    }

    public void setProblemReporter(ProblemReporter problemReporter) {
        this.problemReporter = problemReporter;
    }

    /**
     * {@inheritDoc}
     */
    public IParseEventListener getEventListener() {
        if (null == this.eventListener) {
            this.eventListener = ParserComponents.getFactory().newEventListener();
        }
        return eventListener;
    }

    public void setEventListener(IParseEventListener eventListener) {
        this.eventListener = eventListener;
    }

    /**
     * {@inheritDoc}
     */
    public SourceExtractor getSourceExtractor() {
        if (null == this.sourceExtractor) {
            this.sourceExtractor = ParserComponents.getFactory().newSourceExtractor();
        }
        return sourceExtractor;
    }

    public void setSourceExtractor(SourceExtractor sourceExtractor) {
        this.sourceExtractor = sourceExtractor;
    }

    /**
     * {@inheritDoc}
     */
    public Environment getEnvironment() {
        if (null == this.environment) {
            this.environment = ParserComponents.getFactory().newEnvironment();
        }
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
