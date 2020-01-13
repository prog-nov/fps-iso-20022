package com.forms.beneform4j.core.util.data.accessor.impl;

import java.util.Map;

import com.forms.beneform4j.core.util.data.accessor.IDataAccessor;
import com.forms.beneform4j.core.util.spring.SpEL;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用SpEL表达式实现的数据访问器对象<br>
 * Author : LinJisong <br>
 * Version : 1.0.1 <br>
 * Since : 1.0.1 <br>
 * Date : 2017-1-17<br>
 */
public class SpelDataAccessor extends AbstractDataAccessor {

    private final Object root;

    private final Map<String, Object> vars;

    public SpelDataAccessor() {
        this(null, null, null);
    }

    public SpelDataAccessor(Object root) {
        this(null, root, null);
    }

    public SpelDataAccessor(Object root, Map<String, Object> vars) {
        this(null, root, vars);
    }

    private SpelDataAccessor(IDataAccessor parent, Object root, Map<String, Object> vars) {
        super(parent);
        this.root = root;
        this.vars = vars;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T value(String property, Class<T> cls) {
        return SpEL.getValue(root, property, vars, cls);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(String property, Object value) {
        SpEL.setValue(root, property, vars, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addVar(String key, Object value) {
        SpEL.addVar(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getRoot() {
        return root;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IDataAccessor doDerive(Object value) {
        return new SpelDataAccessor(this, value, vars);
    }
}
