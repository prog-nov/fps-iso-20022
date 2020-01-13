package com.forms.beneform4j.core.dao.mybatis.stream;

import java.util.List;

import com.forms.beneform4j.core.dao.IDaoTemplate;
import com.forms.beneform4j.core.dao.stream.impl.AbstractListStreamReader;
import com.forms.beneform4j.core.util.page.IPage;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用Mybatis实现的流式查询返回结果实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class MybatisListStreamReader<T> extends AbstractListStreamReader<T> {

    /**
     * dao模板
     */
    private final IDaoTemplate daoTemplate;
    /**
     * 执行的SQLID
     */
    private final String statement;

    /**
     * 查询参数
     */
    private final Object parameter;

    /**
     * 使用sql-id和查询参数的构造函数
     * 
     * @param daoTemplate dao模板
     * @param statement sql-id
     * @param parameter 查询参数
     */
    public MybatisListStreamReader(IDaoTemplate daoTemplate, String statement, Object parameter) {
        super();
        this.daoTemplate = daoTemplate;
        this.statement = statement;
        this.parameter = parameter;
    }

    /**
     * 使用sql-id和查询参数的构造函数
     * 
     * @param daoTemplate dao模板
     * @param statement sql-id
     * @param parameter 查询参数
     * @param fetchSize 每次读取的记录条数
     */
    public MybatisListStreamReader(IDaoTemplate daoTemplate, String statement, Object parameter, int fetchSize) {
        super(fetchSize);
        this.daoTemplate = daoTemplate;
        this.statement = statement;
        this.parameter = parameter;
    }

    /**
     * 执行实际的当前页数据读取
     */
    @Override
    protected List<T> doRead(IPage page) {
        return daoTemplate.selectList(statement, parameter, page);
    }
}
