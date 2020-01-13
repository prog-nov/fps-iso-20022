package com.forms.beneform4j.webapp.common.exception;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 异常代码<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-28<br>
 */
public class ExceptionCodes {

    public static final String AP000000 = "AP000000";

    // 公共异常
    public static final String AP010000 = "AP010000";
    public static final String AP010001 = "AP010001";
    // 公共数据访问异常
    public static final String DATA_NOT_FOUND = "AP010101";// 未找到记录
    public static final String HAS_MANY_DATAS = "AP010102";// 找到多条记录
    public static final String DATA_HAS_EXIST = "AP010103";// 记录已存在

    // 系统管理
    public static final String AP020000 = "AP020000";
    // 用户管理
    public static final String AP020100 = "AP020100";
    public static final String AP020101 = "AP020101";
    // 角色管理
    public static final String AP020300 = "AP020300";
    public static final String AP020301 = "AP020301";

    // 消息管理后台异常码
    public static final String AP020201 = "AP020201";

    // 消息文档管理
    public static final String AP020401 = "AP020401";// 文档状态不正确不能下载
    public static final String AP020402 = "AP020402";// 文件不存在，下载失败
    public static final String AP020403 = "AP020403";// 没有写入权限
    public static final String AP020404 = "AP020404";// 文件{0}保存记录出错
    public static final String AP020405 = "AP020405";// 文件已经上传过，不能再次上传
    public static final String AP020406 = "AP020406";// 插件返回的fileKey为空，处理失败
    public static final String AP020407 = "AP020407";// 文件下载异常
    public static final String AP020408 = "AP020408";// 传入的文件或列表当中文件[{0}]不存在或文件是目录
    public static final String AP020409 = "AP020409";// ftp模式【ftp模式｜sftp模式】参数没有配置

    // 待办任务
    public static final String AP020501 = "AP020501";// {0}已设置规则，请到修改页面修改。
    public static final String AP020502 = "AP020502";// 参数缺失BEAN缺失，请联系技术人员
    public static final String AP020503 = "AP020503";// 参数缺失：业务编号缺失。，请联系技术人员
    public static final String AP020504 = "AP020504";// 参数缺失：下一个任务节点缺失。，请联系技术人员
    public static final String AP020505 = "AP020505";// 参数缺失：该任务节点为进入明细，对应业务参数缺失。，请联系技术人员
    public static final String AP020506 = "AP020506";// 代办任务生成失败，找不到代办任务URL，请联系技术人员

    // 机构管理
    public static final String AP020600 = "AP020600";// 机构管理
    public static final String AP020601 = "AP020601";// 拖动机构时，机构号和父机构号不能相同
    public static final String AP020602 = "AP020602";// 拖动机构时，父机构不存在
    public static final String AP020603 = "AP020603";// 拖动机构时，父机构不能是当前机构的子机构
}
