package com.forms.beneform4j.core.dao.stream;

import java.util.List;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 流式查询返回结果接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public interface IListStreamReader<T> extends Iterable<T> {

    /**
     * 读取当前批次的数据列表，如果没有数据，返回null
     * 
     * @return 当前批次的数据列表
     */
    public List<T> read();

    /**
     * 重置读取批次
     */
    public void reset();

    /**
     * 读取数据总记录数，只有在调用read()或readAll()之后读取的数据才是正确的，返回-1表示未知
     * 
     * @return
     * @since 1.1.0
     */
    public int size();

    /**
     * 一次性读取所有数据
     * 
     * @return
     * @since 1.1.0
     */
    public List<T> readAll();
}
