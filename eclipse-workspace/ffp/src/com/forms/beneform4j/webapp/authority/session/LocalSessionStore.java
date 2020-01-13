package com.forms.beneform4j.webapp.authority.session;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 单机会话存储<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-11<br>
 */

public class LocalSessionStore {

    /**
     * 会话缓存
     */
    private static final Map<String, HttpSession> cache = new HashMap<String, HttpSession>();

    public static HttpSession get(String id) {
        return cache.get(id);
    }

    public static void remove(String id) {
        cache.remove(id);
    }

    public static void save(String id, HttpSession session) {
        if (!contains(id)) {
            cache.put(id, session);
        }
    }

    public static boolean contains(String id) {
        return cache.containsKey(id);
    }

    public static void clear() {
        cache.clear();
    }

    public static Map<String, HttpSession> list() {
        return cache;
    }

}
