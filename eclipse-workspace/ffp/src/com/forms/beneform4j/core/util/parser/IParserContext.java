package com.forms.beneform4j.core.util.parser;

import org.springframework.beans.factory.parsing.ProblemReporter;
import org.springframework.beans.factory.parsing.SourceExtractor;
import org.springframework.core.env.Environment;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 解析器上下文环境接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public interface IParserContext {

    /**
     * 获取问题报告者
     * 
     * @return 问题报告者
     */
    public ProblemReporter getProblemReporter();

    /**
     * 获取解析侦听器
     * 
     * @return 解析侦听器
     */
    public IParseEventListener getEventListener();

    /**
     * 获取源提取者
     * 
     * @return 源提取者
     */
    public SourceExtractor getSourceExtractor();

    /**
     * 获取环境
     * 
     * @return 环境
     */
    public Environment getEnvironment();
}
