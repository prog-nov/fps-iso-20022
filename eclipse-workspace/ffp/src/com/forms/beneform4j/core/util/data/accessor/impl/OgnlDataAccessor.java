package com.forms.beneform4j.core.util.data.accessor.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.forms.beneform4j.core.util.data.accessor.IDataAccessor;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.exception.meta.ExceptionCodes;

import ognl.DefaultMemberAccess;
import ognl.MemberAccess;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用OGNL表达式实现的数据访问器对象<br>
 * Author : LinJisong <br>
 * Version : 1.0.1 <br>
 * Since : 1.0.1 <br>
 * Date : 2017-1-17<br>
 */
public class OgnlDataAccessor extends AbstractDataAccessor {

    private static final MemberAccess DEFAULT_MEMBER_ACCESS = new DefaultMemberAccess(true);

    private static final Map<String, Object> expCache = new ConcurrentHashMap<String, Object>();

    private final OgnlContext ognlContext;

    public OgnlDataAccessor() {
        this(null, null, null, null);
    }

    public OgnlDataAccessor(Object root) {
        this(null, null, root, null);
    }

    public OgnlDataAccessor(Object root, Map<String, Object> vars) {
        this(null, null, root, vars);
    }

    private OgnlDataAccessor(IDataAccessor parent, OgnlContext ognlContext, Object root, Map<String, Object> vars) {
        super(parent);
        this.ognlContext = initOnglContext(ognlContext, root, vars);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T value(String property, Class<T> cls) {
        Object tree = getOgnlTree(property);
        try {
            @SuppressWarnings("unchecked")
            T value = (T) Ognl.getValue(tree, ognlContext, ognlContext.getRoot(), cls);
            return value;
        } catch (OgnlException e) {
            throw Throw.createRuntimeException(ExceptionCodes.BF010003, e, property);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(String property, Object value) {
        Object tree = getOgnlTree(property);
        try {
            Ognl.setValue(tree, ognlContext, ognlContext.getRoot(), value);
        } catch (OgnlException e) {
            throw Throw.createRuntimeException(ExceptionCodes.BF010003, e, property);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addVar(String key, Object value) {
        ognlContext.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IDataAccessor doDerive(Object value) {
        return new OgnlDataAccessor(this, ognlContext, value, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getRoot() {
        return ognlContext.getRoot();
    }

    /**
     * 解析OGNL表达式并缓存
     * 
     * @param property
     * @return
     */
    private Object getOgnlTree(String property) {
        Object tree = expCache.get(property);
        if (null == tree) {
            synchronized (expCache) {
                tree = expCache.get(property);
                if (null == tree) {
                    try {
                        tree = Ognl.parseExpression(property);
                        expCache.put(property, tree);
                    } catch (OgnlException e) {
                        Throw.throwRuntimeException(ExceptionCodes.BF010003, e, property);
                    }
                }
            }
        }
        return tree;
    }

    /**
     * 初始化OGNL上下文
     * 
     * @param ognlContext
     * @param root
     * @param vars
     * @return
     */
    private OgnlContext initOnglContext(OgnlContext ognlContext, Object root, Map<String, Object> vars) {
        OgnlContext context = ognlContext;
        if (null == context) {
            context = new OgnlContext();
            context.setMemberAccess(DEFAULT_MEMBER_ACCESS);
        }
        context.setRoot(root);
        if (null != vars) {
            context.setValues(vars);
        }
        return context;
    }
}
