package com.forms.beneform4j.core.util.parser;

import org.springframework.beans.factory.parsing.ProblemReporter;
import org.springframework.beans.factory.parsing.SourceExtractor;
import org.springframework.core.env.Environment;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 解析器组件工厂接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public interface IParserComponentFactory {

    /**
     * 创建解析器上下文环境
     * 
     * @return 上下文环境
     */
    public IParserContext newParserContext();

    /**
     * 创建问题报告者
     * 
     * @return 问题报告者
     */
    public ProblemReporter newProblemReporter();

    /**
     * 创建事件侦听器
     * 
     * @return 事件侦听器
     */
    public IParseEventListener newEventListener();

    /**
     * 创建源提取者
     * 
     * @return 源提取者
     */
    public SourceExtractor newSourceExtractor();

    /**
     * 创建环境
     * 
     * @return 环境
     */
    public Environment newEnvironment();
}
