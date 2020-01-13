package com.forms.beneform4j.core.util.parser.impl;

import org.springframework.beans.factory.parsing.FailFastProblemReporter;
import org.springframework.beans.factory.parsing.NullSourceExtractor;
import org.springframework.beans.factory.parsing.ProblemReporter;
import org.springframework.beans.factory.parsing.SourceExtractor;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;

import com.forms.beneform4j.core.util.parser.IParseEventListener;
import com.forms.beneform4j.core.util.parser.IParserComponentFactory;
import com.forms.beneform4j.core.util.parser.IParserContext;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 解析器组件工厂实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public class ParserComponentFactory implements IParserComponentFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public IParserContext newParserContext() {
        return new ParserContext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProblemReporter newProblemReporter() {
        return new FailFastProblemReporter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IParseEventListener newEventListener() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SourceExtractor newSourceExtractor() {
        return new NullSourceExtractor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Environment newEnvironment() {
        return new StandardEnvironment();
    }
}
