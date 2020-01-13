package com.forms.beneform4j.util;

import com.forms.beneform4j.util.toolimpl.BeanUtilsImpl;
import com.forms.beneform4j.util.toolimpl.CheckUtilsImpl;
import com.forms.beneform4j.util.toolimpl.CollectionUtilsImpl;
import com.forms.beneform4j.util.toolimpl.ConvertUtilsImpl;
import com.forms.beneform4j.util.toolimpl.DateUtilsImpl;
import com.forms.beneform4j.util.toolimpl.FileUtilsImpl;
import com.forms.beneform4j.util.toolimpl.FtpUtilsImpl;
import com.forms.beneform4j.util.toolimpl.IoUtilsImpl;
import com.forms.beneform4j.util.toolimpl.JsonUtilsImpl;
import com.forms.beneform4j.util.toolimpl.LocaleUtilsImpl;
import com.forms.beneform4j.util.toolimpl.OSUtilsImpl;
import com.forms.beneform4j.util.toolimpl.ReflectUtilsImpl;
import com.forms.beneform4j.util.toolimpl.ScriptUtilsImpl;
import com.forms.beneform4j.util.toolimpl.StringUtilsImpl;
import com.forms.beneform4j.util.toolimpl.TemplateUtilsImpl;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 工具类统一入口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
final public class Tool {

    /**
     * Bean操作工具类
     */
    public static final BeanUtilsImpl BEAN = BeanUtilsImpl.getInstance();

    /**
     * 检验工具类
     */
    public static final CheckUtilsImpl CHECK = CheckUtilsImpl.getInstance();

    /**
     * 集合操作工具类
     */
    public static final CollectionUtilsImpl COLLECTION = CollectionUtilsImpl.getInstance();

    /**
     * 类型转换工具类
     */
    public static final ConvertUtilsImpl CONVERT = ConvertUtilsImpl.getInstance();

    /**
     * 日期工具类
     */
    public static final DateUtilsImpl DATE = DateUtilsImpl.getInstance();

    /**
     * IO工具类
     */
    public static final IoUtilsImpl IO = IoUtilsImpl.getInstance();

    /**
     * JSON工具类
     */
    public static final JsonUtilsImpl JSON = JsonUtilsImpl.getInstance();

    /**
     * 本地化工具类
     */
    public static final LocaleUtilsImpl LOCALE = LocaleUtilsImpl.getInstance();

    /**
     * 文件工具类
     */
    public static final FileUtilsImpl FILE = FileUtilsImpl.getInstance();

    /**
     * FTP/SFTP工具类
     */
    public static final FtpUtilsImpl FTP = FtpUtilsImpl.getInstance();

    /**
     * 反射工具类
     */
    public static final ReflectUtilsImpl REFLECT = ReflectUtilsImpl.getInstance();

    /**
     * 脚本语言工具类
     */
    public static final ScriptUtilsImpl SCRIPT = ScriptUtilsImpl.getInstance();

    /**
     * 字符串工具类
     */
    public static final StringUtilsImpl STRING = StringUtilsImpl.getInstance();

    /**
     * 操作系统工具类
     */
    public static final OSUtilsImpl OS = OSUtilsImpl.getInstance();

    /**
     * 模板工具类
     */
    public static final TemplateUtilsImpl TEMPLATE = TemplateUtilsImpl.getInstance();
}
