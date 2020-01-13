package com.forms.beneform4j.core.dao.stream.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.forms.beneform4j.core.dao.exception.DaoExceptionCodes;
import com.forms.beneform4j.core.dao.stream.IListStreamReader;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.core.util.page.impl.BasePage;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象的流式查询结果实现类，内置分页对象实现流式查询<br>
 * <p>
 * 默认每次读取的记录数为1000条，该参数有效范围为(0, 10000]，如超出范围，抛出运行时异常
 * </p>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public abstract class AbstractListStreamReader<T> implements IListStreamReader<T> {

    /**
     * 默认的每次读取记录数
     */
    private static final int defaultFetchSize = 1000;

    /**
     * 最大的每次读取记录数
     */
    private static final int maxFetchSize = 10000;

    /**
     * 实际的每次读取记录数
     */
    private final int fetchSize;

    /**
     * 分页对象
     */
    private final IPage page;

    /**
     * 迭代器
     */
    private final ListStreamIterator iterator = new ListStreamIterator();

    /**
     * 是否完成的标志
     */
    private final AtomicBoolean finish = new AtomicBoolean(false);

    /**
     * 无参构造函数
     */
    public AbstractListStreamReader() {
        this(defaultFetchSize);
    }

    /**
     * 使用指定读取数大小的构造函数
     * 
     * @param fetchSize 每次读取的记录条数
     */
    public AbstractListStreamReader(int fetchSize) {
        if (fetchSize <= 0) {
            fetchSize = defaultFetchSize;
        } else if (fetchSize > maxFetchSize) {
            Throw.throwRuntimeException(DaoExceptionCodes.BF020012, fetchSize, "(0, " + maxFetchSize + "]");
        }
        this.fetchSize = fetchSize;
        BasePage page = new BasePage();
        page.setPageSize(fetchSize);
        this.page = page;
    }

    /**
     * 读取当前批次的列表数据，读取的时候会加锁
     */
    @Override
    public List<T> read() {
        if (!finish.get()) {
            synchronized (finish) {
                if (!finish.get()) {
                    List<T> rs = doRead(page);// 查询当前页数据
                    if (null != rs) {
                        iterator.inner = rs.iterator();
                    }
                    if (page.hasNextPage()) {// 有下一页，游标指向下一页
                        page.setPageProperty(page.getTotalRecords(), page.getCurrentPage() + 1, fetchSize);
                    } else {// 没有下一页，完成
                        finish.set(true);
                    }
                    return rs;
                }
            }
        }
        return null;
    }

    /**
     * 执行实际的读取操作
     * 
     * @param page 分页对象
     * @return 和分页对象相对应的数据记录列表
     */
    abstract protected List<T> doRead(IPage page);

    /**
     * 重置读取批次，重置过程中会加锁
     */
    @Override
    public synchronized void reset() {
        this.finish.set(false);
        this.page.setPageProperty(this.page.getTotalRecords(), 1, fetchSize);
        this.iterator.inner = null;
    }

    @Override
    public Iterator<T> iterator() {
        return iterator;
    }

    @Override
    public int size() {
        return page == null ? -1 : (int) page.getTotalRecords();
    }

    @Override
    public List<T> readAll() {
        List<T> list = new ArrayList<T>();
        for (Iterator<T> iterator = iterator(); iterator.hasNext();) {
            list.add(iterator.next());
        }
        return list;
    }

    private class ListStreamIterator implements Iterator<T> {

        private Iterator<T> inner;

        @Override
        public boolean hasNext() {
            if (null == inner || !inner.hasNext()) {
                read();
            }
            return null != inner && inner.hasNext();
        }

        @Override
        public T next() {
            return null == inner ? null : inner.next();
        }

        @Override
        public void remove() {
            if (null != inner) {
                inner.remove();
            }
        }
    }
}
