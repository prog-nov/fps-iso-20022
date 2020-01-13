package com.forms.beneform4j.util.toolimpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;

import com.forms.beneform4j.util.Tool;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 脚本语言工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class ScriptUtilsImpl {

    private static final ScriptUtilsImpl instance = new ScriptUtilsImpl() {};

    private ScriptUtilsImpl() {}

    /**
     * 获取单实例
     * 
     * @return
     */
    public static ScriptUtilsImpl getInstance() {
        return instance;
    }

    /**
     * 将js字符串转换为Java对象
     * 
     * @param jsString js字符串
     * @return Java对象
     */
    public Object js2Java(String jsString) {
        try {
            Context cx = ContextFactory.getGlobal().enterContext();
            Scriptable scope = cx.initStandardObjects();
            // js.eval();
            Object value = cx.evaluateString(scope, "$aa=" + jsString, null, 1, null);
            return parseNativeValue(value);
        } catch (Exception e) {
            return jsString;
        } finally {
            Context.exit();
        }
    }

    // /**
    // * 将js字符串转换为Java对象
    // * @param jsString
    // * @return
    // */
    // public Object js2Java(String jsString){
    // ScriptEngineManager manager = new ScriptEngineManager();
    // ScriptEngine js = manager.getEngineByName("javascript");
    // try {
    // js.eval("$aa="+jsString);
    // return parseNativeValue(js.get("$aa"));
    // } catch (ScriptException e) {
    // return jsString;
    // }
    // }

    /**
     * 解析值
     * 
     * @param value
     * @return
     */
    private Object parseNativeValue(Object value) {
        if (null == value || value instanceof Undefined) {// Null、Undefined
            return null;
        } else if (value instanceof String) {// 字符串
            String v = (String) value;
            if (!Tool.CHECK.isEmpty(v)) {
                return v;
            }
        } else if (value instanceof NativeArray) {// 数组
            NativeArray array = (NativeArray) value;
            List<Object> list = new ArrayList<Object>();
            for (int i = 0, l = (int) array.getLength(); i < l; i++) {
                Object v = parseNativeValue(array.get(i, array));
                // 这里即使v为null也需要添加到list中
                list.add(v);
            }
            if (null != list && !list.isEmpty()) {
                return list;
            }
        } else if (value instanceof NativeObject) {// 对象
            NativeObject jsObj = (NativeObject) value;
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            for (Object c : jsObj.getAllIds()) {
                String key = c.toString();
                Object v = parseNativeValue(jsObj.get(key, jsObj));
                if (null != v) {
                    map.put(key, v);
                }
            }
            if (null != map && !map.isEmpty()) {
                return map;
            }
        } else {
            return value;
        }
        return null;
    }
}
