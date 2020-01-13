/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50713
Source Host           : 172.21.21.252:3306
Source Database       : ffp

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001
status                : unchecked

Date: 2018-05-14 17:04:16
*/

CREATE DATABASE IF NOT EXISTS ffp DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
use ffp;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bf_day_info
-- ----------------------------
DROP TABLE IF EXISTS `bf_day_info`;
CREATE TABLE `bf_day_info` (
  `SOLAR_DATE` char(8) NOT NULL COMMENT '阳历日期',
  `LUNAR_YEAR` char(4) DEFAULT NULL COMMENT '农历年',
  `LUNAR_MONTH` char(2) DEFAULT NULL COMMENT '农历月（闰月为12+闰月数）',
  `LUNAR_DAY` char(2) DEFAULT NULL COMMENT '农历日',
  `LUNAR_DESC` varchar(20) DEFAULT NULL COMMENT '农历日期描述',
  `WEEK` char(6) DEFAULT NULL COMMENT '星期',
  `CONSTELLATION` char(6) DEFAULT NULL COMMENT '星座',
  `MONTH_BEGIN` char(8) DEFAULT NULL COMMENT '月初日期',
  `MONTH_END` char(8) DEFAULT NULL COMMENT '月末日期',
  `YEAR_DAYS` int(11) DEFAULT NULL COMMENT '当年第几天',
  `MONTH_DAYS` int(11) DEFAULT NULL COMMENT '当月第几天',
  `IS_HOLIDAY_DAY` char(1) DEFAULT NULL COMMENT '是否节假日',
  `HOLIDAY_DESC` varchar(50) DEFAULT NULL COMMENT '节假日描述',
  `IS_MONTH_END` char(1) DEFAULT NULL COMMENT '是否月末',
  PRIMARY KEY (`SOLAR_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 日历信息表';

-- ----------------------------
-- Table structure for bf_function_guide
-- ----------------------------
DROP TABLE IF EXISTS `bf_function_guide`;
CREATE TABLE `bf_function_guide` (
  `MENU_ID` int(11) NOT NULL COMMENT '操作指引ID',
  `MENU_NAME` varchar(64) NOT NULL COMMENT '菜单名称',
  `GUIDE_TITLE` varchar(128) DEFAULT NULL COMMENT '指引标题',
  `GUIDE_CONTENT` text NOT NULL COMMENT '指引内容',
  `INST_DATE` varchar(8) NOT NULL COMMENT '新增日期',
  `INST_TIME` varchar(6) NOT NULL COMMENT '新增时间',
  `INST_OPER` varchar(20) NOT NULL COMMENT '新增操作人',
  `MODI_DATE` varchar(8) DEFAULT NULL COMMENT '修改日期',
  `MODI_TIME` varchar(6) DEFAULT NULL COMMENT '修改时间',
  `MODI_OPER` varchar(20) DEFAULT NULL COMMENT '修改操作人',
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表-业务操作指引表 平台表-对业务的操作指引存在在此表当中';

-- ----------------------------
-- Table structure for bf_function_guide_i18n
-- ----------------------------
DROP TABLE IF EXISTS `bf_function_guide_i18n`;
CREATE TABLE `bf_function_guide_i18n` (
  `MENU_ID` int(11) NOT NULL COMMENT '菜单ID',
  `LOCALE` varchar(64) NOT NULL COMMENT '语言地区',
  `GUIDE_CONTENT` text NOT NULL COMMENT '指引内容',
  PRIMARY KEY (`MENU_ID`,`LOCALE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表——操作指引国际化表';

-- ----------------------------
-- Table structure for bf_job_controller
-- ----------------------------
DROP TABLE IF EXISTS `bf_job_controller`;
CREATE TABLE `bf_job_controller` (
  `JOB_KEY` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `SCHED_NAME` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `BEGIN_FIRE_TIME` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `END_FIRE_TIME` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DURATION_TIME` int(11) DEFAULT NULL,
  `REAL_DURATION_TIME` int(11) DEFAULT NULL,
  `CAL_DURATION_TIME` int(11) DEFAULT NULL,
  `NEXT_FIRE_TIME` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CRON_EXPRESS` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `JOB_LEVEL` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SERVER_IP` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ENABLE_HISTORY` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ENABLE_TIMEOUT_WATCHER` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RUNNING_STATUS` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `OP_DATE` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `OP_TIME` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for bf_job_execute_log
-- ----------------------------
DROP TABLE IF EXISTS `bf_job_execute_log`;
CREATE TABLE `bf_job_execute_log` (
  `FLOW_ID` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `JOB_KEY` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `SCHED_NAME` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `JOB_CLASS_NAME` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SERVER_IP` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BEGIN_FIRE_TIME` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `END_FIRE_TIME` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REAL_DURATION_TIME` int(11) DEFAULT NULL,
  `CAL_DURATION_TIME` int(11) DEFAULT NULL,
  `CRON_EXPRESS` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `JOB_LEVEL` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RUNNING_STATUS` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `OP_DATE` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `OP_TIME` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`FLOW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for bf_key_cfg
-- ----------------------------
DROP TABLE IF EXISTS `bf_key_cfg`;
CREATE TABLE `bf_key_cfg` (
  `MODULE` varchar(200) NOT NULL COMMENT '模',
  `PUBLIC_EMPOENT` varchar(200) NOT NULL COMMENT '公钥',
  `PRIVATE_EMPOENT` varchar(200) NOT NULL COMMENT '私钥'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 加解密密钥表（登录功能用到）';

-- ----------------------------
-- Table structure for bf_log_login
-- ----------------------------
DROP TABLE IF EXISTS `bf_log_login`;
CREATE TABLE `bf_log_login` (
  `KEY_ID` varchar(32) NOT NULL COMMENT 'KEY主键 由系统自动产生不重复的值做为主键',
  `SESSION_ID` varchar(64) NOT NULL COMMENT '会话ID',
  `USER_ID` varchar(20) NOT NULL COMMENT '用户ID',
  `CLIENT_IP` varchar(32) DEFAULT NULL COMMENT '客户端IP',
  `SERVER_IP` varchar(32) DEFAULT NULL COMMENT '服务端IP',
  `BROWSER` varchar(32) DEFAULT NULL COMMENT '浏览器',
  `OS` varchar(32) DEFAULT NULL COMMENT '操作系统',
  `LIO_FLAG` varchar(32) NOT NULL COMMENT '标志 1：登入 2：登出   3：系统自动注销',
  `OPT_DATE` char(8) NOT NULL COMMENT '操作 日期',
  `OPT_TIME` char(6) NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`KEY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 登录日志表';

-- ----------------------------
-- Table structure for bf_log_visit
-- ----------------------------
DROP TABLE IF EXISTS `bf_log_visit`;
CREATE TABLE `bf_log_visit` (
  `KEY_ID` varchar(32) NOT NULL COMMENT 'KEY主键 由系统自动产生不重复的值做为主键',
  `REQUEST_ID` varchar(64) DEFAULT NULL COMMENT '请求ID',
  `SESSION_ID` varchar(64) DEFAULT NULL COMMENT '会话ID',
  `USER_ID` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `PERM_ID` int(11) DEFAULT NULL COMMENT '权限ID',
  `CLIENT_IP` varchar(32) DEFAULT NULL COMMENT '客户端IP',
  `SERVER_IP` varchar(32) DEFAULT NULL COMMENT '服务端IP',
  `BROWSER` varchar(32) DEFAULT NULL COMMENT '浏览器',
  `OS` varchar(32) DEFAULT NULL COMMENT '操作系统',
  `OPT_FLAG` varchar(32) DEFAULT NULL COMMENT '操作标志  0 拒绝访问  1 通过授权',
  `OPT_PATH` varchar(200) DEFAULT NULL COMMENT '访问路径',
  `OPT_URL` varchar(200) DEFAULT NULL COMMENT '访问URL',
  `OPT_PARAMS` varchar(500) DEFAULT NULL COMMENT '访问参数，只记录前200个字符',
  `OPT_DATE` char(8) DEFAULT NULL COMMENT '操作 日期',
  `OPT_TIME` char(6) DEFAULT NULL COMMENT '操作时间',
  `COST_TIME` int(11) DEFAULT NULL COMMENT '耗费时间',
  PRIMARY KEY (`KEY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 访问日志表';

-- ----------------------------
-- Table structure for bf_memo_def
-- ----------------------------
DROP TABLE IF EXISTS `bf_memo_def`;
CREATE TABLE `bf_memo_def` (
  `ID` varchar(32) NOT NULL COMMENT '唯一主键',
  `USER_ID` varchar(20) NOT NULL COMMENT '用户ID',
  `MEMO_TITLE` varchar(100) NOT NULL COMMENT '标题',
  `MEMO_CONTENT` varchar(600) DEFAULT NULL COMMENT '正文',
  `MEMO_DATE` varchar(8) NOT NULL COMMENT '备忘开始日期',
  `IS_REMIND` int(11) NOT NULL DEFAULT '0' COMMENT '是否需要提醒 默认 0：不需要提醒 1：需要提醒',
  `MEMO_TIME` varchar(6) DEFAULT NULL COMMENT '初始化时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 系统备忘表';

-- ----------------------------
-- Table structure for bf_memo_log
-- ----------------------------
DROP TABLE IF EXISTS `bf_memo_log`;
CREATE TABLE `bf_memo_log` (
  `ID` varchar(32) NOT NULL COMMENT '主键号',
  `USER_ID` varchar(20) NOT NULL COMMENT '用户ID',
  `MEMO_TITLE` varchar(100) NOT NULL COMMENT '标题',
  `MEMO_CONTENT` varchar(600) DEFAULT NULL COMMENT '正文',
  `MEMO_DATE` varchar(8) DEFAULT NULL COMMENT '备忘开始日期',
  `MEMO_TIME` varchar(6) NOT NULL COMMENT '备忘开始时间',
  `MEMO_ID` varchar(32) NOT NULL COMMENT '备忘ID',
  `OP_DATE` varchar(8) NOT NULL COMMENT '操作日期',
  `OP_TIME` varchar(6) NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 -  系统备忘日志表';

-- ----------------------------
-- Table structure for bf_menu
-- ----------------------------
DROP TABLE IF EXISTS `bf_menu`;
CREATE TABLE `bf_menu` (
  `MENU_ID` int(11) NOT NULL COMMENT '菜单ID',
  `PARENT_MENU_ID` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `MENU_NAME` varchar(64) NOT NULL COMMENT '菜单名称',
  `MENU_URL` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `DISPLAY_AREA` varchar(32) DEFAULT NULL COMMENT '显示区域 如果是菜单时，默认一级菜单在头部显示，子级菜单的显示由父级菜单的显示位置来决定。0：头部显示  1：侧部菜单',
  `DISPLAY_ICON` varchar(32) DEFAULT NULL COMMENT '样式名称',
  `DEPEND_MENU_ID` int(11) DEFAULT NULL COMMENT '菜单依赖 只能依赖一层',
  `AUTHOR_LEVEL` varchar(32) DEFAULT NULL COMMENT '授权级别 在登录时，0与1先检查，2需要进权限控制进行检查\r\n            0：无需登录即可访问\r\n            1：只要有效用户登录即可\r\n            2：需要授权方可访问',
  `MENU_FLAG` varchar(32) DEFAULT NULL COMMENT '菜单类型 0：菜单  1：功能项',
  `PERM_TREE_FLAG` varchar(32) DEFAULT NULL COMMENT '是否显示在权限树中 如果是菜单标志则操作标志一定为1  如果显示0，依赖菜单不能为空。 0--不在功能树中显示  1--在功能树中显示',
  `TARGET_PAGE` varchar(32) DEFAULT NULL COMMENT '目标页面 为空时，在选项卡中打开，否则打开新的页面',
  `SEQNO` int(11) DEFAULT NULL COMMENT '排序',
  `DES` varchar(200) DEFAULT NULL COMMENT '描述',
  `INST_DATE` char(8) DEFAULT NULL COMMENT '新增日期',
  `INST_TIME` char(6) DEFAULT NULL COMMENT '新增时间',
  `INST_OPER` char(20) DEFAULT NULL COMMENT '新增操作人',
  `MODI_DATE` char(8) DEFAULT NULL COMMENT '修改日期',
  `MODI_TIME` char(6) DEFAULT NULL COMMENT '修改时间',
  `MODI_OPER` char(20) DEFAULT NULL COMMENT '修改操作人',
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 菜单表\r\n其中一个权限子表（所有一级菜单显示在头部，根据一级菜单的显示属性，决定所有子孙节点是显示';

-- ----------------------------
-- Table structure for bf_menu_i18n
-- ----------------------------
DROP TABLE IF EXISTS `bf_menu_i18n`;
CREATE TABLE `bf_menu_i18n` (
  `MENU_ID` int(11) NOT NULL COMMENT '菜单ID',
  `LOCALE` varchar(64) NOT NULL COMMENT '语言地区',
  `MENU_NAME` varchar(64) NOT NULL COMMENT '菜单名称',
  `DES` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`MENU_ID`,`LOCALE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表——菜单国际化表';


-- ----------------------------
-- Table structure for bf_news
-- ----------------------------
DROP TABLE IF EXISTS `bf_news`;
CREATE TABLE `bf_news` (
  `MSG_ID` varchar(32) NOT NULL COMMENT '消息ID',
  `MSG_TITLE` varchar(100) NOT NULL COMMENT '消息标题',
  `MSG_CONTENT` varchar(500) NOT NULL COMMENT '消息内容',
  `TARGET_URL` varchar(200) DEFAULT NULL COMMENT '目标URL',
  `MSG_TYPE` char(1) DEFAULT NULL COMMENT '1-公告所有的人都可以看到\r\n            2-通知，登录后可以查看 3-信函，指定人/机构/角色可查看',
  `MSG_LEVEL` char(1) DEFAULT NULL COMMENT '1-高\r\n            2-中\r\n            3-低',
  `SEND_OPER` varchar(20) NOT NULL COMMENT '消息发送人',
  `SEND_DATE` char(8) NOT NULL COMMENT '消息发送日期',
  `SEND_TIME` char(6) NOT NULL COMMENT '消息发送时间',
  `RECV_NET` varchar(7) DEFAULT NULL COMMENT '消息接收机构',
  `RECV_OPER` varchar(20) DEFAULT NULL COMMENT '消息接收用户',
  `STATUS` char(1) DEFAULT '0' COMMENT '0-未阅读\r\n            1-已阅读',
  `MSG_ICON` varchar(20) DEFAULT NULL COMMENT '消息图标',
  `EFFECTIVE_FLAG` char(1) DEFAULT NULL COMMENT '生效标志  1：永久生效；2：天数生效；3：区间生效',
  `EFFECTIVE_DAY_CNT` int(11) DEFAULT NULL COMMENT '生效天数',
  `EFFECTIVE_START_DATE` char(8) DEFAULT NULL COMMENT '生效起始日期',
  `EFFECTIVE_START_TIME` char(8) DEFAULT NULL COMMENT '生效起始时间',
  `EFFECTIVE_END_DATE` char(8) DEFAULT NULL COMMENT '生效结束日期',
  `EFFECTIVE_END_TIME` char(8) DEFAULT NULL COMMENT '生效结束时间',
  PRIMARY KEY (`MSG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 -  消息表';

-- ----------------------------
-- ----------------------------
-- Table structure for bf_news_log
-- ----------------------------
DROP TABLE IF EXISTS `bf_news_log`;
CREATE TABLE `bf_news_log` (
  `USER_ID` varchar(20) NOT NULL COMMENT '用户号',
  `MSG_ID` varchar(32) NOT NULL COMMENT '消息ID',
  `OPER_DATE` char(8) NOT NULL COMMENT '阅读日期',
  `OPER_TIME` char(6) NOT NULL COMMENT '阅读时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 消息表已读消息记录表';

-- ----------------------------
-- Table structure for bf_online_user
-- ----------------------------
DROP TABLE IF EXISTS `bf_online_user`;
CREATE TABLE `bf_online_user` (
  `SESSION_ID` varchar(64) NOT NULL COMMENT '会话ID',
  `USER_ID` varchar(20) NOT NULL COMMENT '用户ID',
  `CLIENT_IP` varchar(32) DEFAULT NULL COMMENT '客户端IP地址',
  `SERVER_IP` varchar(32) DEFAULT NULL COMMENT '服务端IP地址',
  `BROWSER` varchar(32) DEFAULT NULL COMMENT '浏览器',
  `OS` varchar(32) DEFAULT NULL COMMENT '操作系统',
  `LOGIN_DATE` char(8) NOT NULL COMMENT '登录日期',
  `LOGIN_TIME` char(6) NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`SESSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 在线用户表';

-- ----------------------------
-- Table structure for bf_org
-- ----------------------------
DROP TABLE IF EXISTS `bf_org`;
CREATE TABLE `bf_org` (
  `ORG_ID` varchar(32) NOT NULL COMMENT '机构号',
  `ORG_NAME` varchar(100) NOT NULL COMMENT '机构名称',
  `SUP_ORG_ID` varchar(32) DEFAULT NULL COMMENT '上级机构号',
  `ORG_LEVEL` int(11) DEFAULT NULL COMMENT '机构级别',
  `ORG_TYPE` varchar(32) DEFAULT NULL COMMENT '机构类型（预留）',
  `DES` varchar(200) DEFAULT NULL COMMENT '描述',
  `INST_DATE` char(8) DEFAULT NULL COMMENT '新增日期',
  `INST_TIME` char(6) DEFAULT NULL COMMENT '新增时间',
  `INST_OPER` char(20) DEFAULT NULL COMMENT '新增人',
  `MODI_DATE` char(8) DEFAULT NULL COMMENT '修改日期',
  `MODI_TIME` char(6) DEFAULT NULL COMMENT '修改时间',
  `MODI_OPER` char(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ORG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 机构';

-- ----------------------------
-- Table structure for bf_param_cfg
-- ----------------------------
DROP TABLE IF EXISTS `bf_param_cfg`;
CREATE TABLE `bf_param_cfg` (
  `PARAM_CODE` varchar(32) NOT NULL COMMENT '参数 代码',
  `PARAM_VALUE` varchar(200) NOT NULL COMMENT '参数值',
  PRIMARY KEY (`PARAM_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 参数配置表';


-- ----------------------------
-- Table structure for bf_param_def
-- ----------------------------
DROP TABLE IF EXISTS `bf_param_def`;
CREATE TABLE `bf_param_def` (
  `PARAM_CODE` varchar(32) NOT NULL COMMENT '参数 代码',
  `PARAM_NAME` varchar(200) NOT NULL COMMENT '参数名称',
  `STORE_TYPE` varchar(32) NOT NULL COMMENT '存储方式\r\n            PLAINTEXT 明文\r\n            ENCRYPT 密文',
  `PARAM_GROUP` varchar(32) NOT NULL COMMENT '所属参数组别',
  `DATA_TYPE` varchar(32) NOT NULL COMMENT '数据类型',
  `EDITABLE` varchar(32) DEFAULT NULL COMMENT '是否可编辑 0 不可编辑  1 可编辑',
  `DEFAULT_VALUE` varchar(200) DEFAULT NULL COMMENT '默认值',
  `VALUE_RULE` varchar(32) DEFAULT NULL COMMENT '取值规则\r\n            如正则表达式、枚举等',
  `VALUE_RULE_PARAM` varchar(32) DEFAULT NULL COMMENT '取值规则参数\r\n            正则表达式的文本，枚举的类型等',
  `SEQNO` int(11) DEFAULT NULL COMMENT '序号',
  `DES` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`PARAM_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 参数属性定义表';


-- ----------------------------
-- Table structure for bf_param_enum_data
-- ----------------------------
DROP TABLE IF EXISTS `bf_param_enum_data`;
CREATE TABLE `bf_param_enum_data` (
  `PARAM_CODE` varchar(32) NOT NULL COMMENT '参数 代码',
  `DATA_CODE` varchar(32) NOT NULL COMMENT '数据代码',
  `DATA_TEXT` varchar(200) NOT NULL COMMENT '数据文本',
  `DATA_PARAM` varchar(200) DEFAULT NULL COMMENT '数据参数',
  `SEQNO` int(11) DEFAULT NULL COMMENT '序号',
  `DES` varchar(200) DEFAULT NULL COMMENT '参数描述',
  PRIMARY KEY (`PARAM_CODE`,`DATA_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 枚举参数数据表';



-- ----------------------------
-- Table structure for bf_param_enum_def
-- ----------------------------
DROP TABLE IF EXISTS `bf_param_enum_def`;
CREATE TABLE `bf_param_enum_def` (
  `PARAM_CODE` varchar(32) NOT NULL COMMENT '参数 代码',
  `PARAM_NAME` varchar(200) NOT NULL COMMENT '参数名称',
  `PARAM_GROUP` varchar(32) NOT NULL COMMENT '所属参数组别',
  `PARAM_ATTR` varchar(32) NOT NULL COMMENT '参数特性如LIST 列表枚举类型  TREE树型数据',
  `EDITABLE` varchar(32) DEFAULT NULL COMMENT '是否可编辑 0 不可编辑  1 可编辑',
  `SEQNO` int(11) DEFAULT NULL COMMENT '序号',
  `DES` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`PARAM_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 枚举参数属性定义表';


-- ----------------------------
-- Table structure for bf_param_tree_data
-- ----------------------------
DROP TABLE IF EXISTS `bf_param_tree_data`;
CREATE TABLE `bf_param_tree_data` (
  `PARAM_CODE` varchar(32) NOT NULL COMMENT '参数 代码',
  `DATA_CODE` varchar(32) NOT NULL COMMENT '数据代码',
  `DATA_TEXT` varchar(200) NOT NULL COMMENT '数据文本',
  `DATA_ICON` varchar(32) DEFAULT NULL COMMENT '数据图标',
  `PARENT_DATA_CODE` varchar(32) DEFAULT NULL COMMENT '父数据代码',
  `DATA_PARAM` varchar(200) DEFAULT NULL COMMENT '数据参数',
  `SEQNO` int(11) DEFAULT NULL COMMENT '序号',
  `DES` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`PARAM_CODE`,`DATA_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 树型参数数据表';

-- ----------------------------
-- Table structure for bf_perm
-- ----------------------------
DROP TABLE IF EXISTS `bf_perm`;
CREATE TABLE `bf_perm` (
  `PERM_ID` int(11) NOT NULL COMMENT '权限ID，由系统自动生成唯一的值',
  `PERM_TYPE` varchar(32) NOT NULL COMMENT '权限类型 如菜单、文件、或者不同子系统等',
  `PERM_TYPE_KEY` varchar(32) NOT NULL COMMENT '和权限类型对应的权限子表主键',
  `INST_DATE` char(8) DEFAULT NULL COMMENT '新增日期',
  `INST_TIME` char(6) DEFAULT NULL COMMENT '新增时间',
  `INST_OPER` varchar(20) DEFAULT NULL COMMENT '新增操作人',
  `MODI_DATE` char(8) DEFAULT NULL COMMENT '修改日期',
  `MODI_TIME` char(6) DEFAULT NULL COMMENT '修改时间',
  `MODI_OPER` varchar(20) DEFAULT NULL COMMENT '修改操作人',
  PRIMARY KEY (`PERM_ID`),
  UNIQUE KEY `UQ_PERM_TYPE_KEY` (`PERM_TYPE`,`PERM_TYPE_KEY`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 权限表';

-- ----------------------------
-- Table structure for bf_role
-- ----------------------------
DROP TABLE IF EXISTS `bf_role`;
CREATE TABLE `bf_role` (
  `ROLE_ID` int(11) NOT NULL COMMENT '角色编号',
  `ROLE_NAME` varchar(64) NOT NULL COMMENT '角色名称，由于角色代码自动生成，因此要求角色名称不能重复',
  `DES` varchar(200) DEFAULT NULL COMMENT '描述',
  `INST_DATE` char(8) DEFAULT NULL COMMENT '新增日期',
  `INST_TIME` char(6) DEFAULT NULL COMMENT '新增时间',
  `INST_OPER` char(20) DEFAULT NULL COMMENT '新增操作人',
  `MODI_DATE` char(8) DEFAULT NULL COMMENT '修改日期',
  `MODI_TIME` char(6) DEFAULT NULL COMMENT '修改时间',
  `MODI_OPER` char(20) DEFAULT NULL COMMENT '修改操作人',
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `IDX_BF_ROLE_ROLE_NAME` (`ROLE_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 角色表';

-- ----------------------------
-- Table structure for bf_role_allot
-- ----------------------------
DROP TABLE IF EXISTS `bf_role_allot`;
CREATE TABLE `bf_role_allot` (
  `ROLE_ALLOT_ID` int(11) NOT NULL COMMENT '角色(分配) ID',
  `ROLE_ALLOT_NAME` varchar(64) NOT NULL COMMENT '角色(分配)名称',
  `DES` varchar(200) DEFAULT NULL COMMENT '描述',
  `INST_DATE` char(8) DEFAULT NULL COMMENT '新增日期',
  `INST_TIME` char(6) DEFAULT NULL COMMENT '新增时间',
  `INST_OPER` char(20) DEFAULT NULL COMMENT '新增操作人',
  `MODI_DATE` char(8) DEFAULT NULL COMMENT '修改日期',
  `MODI_TIME` char(6) DEFAULT NULL COMMENT '修改时间',
  `MODI_OPER` char(20) DEFAULT NULL COMMENT '修改操作人',
  PRIMARY KEY (`ROLE_ALLOT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 角色(分配)表';


-- ----------------------------
-- Table structure for bf_role_allot_perm
-- ----------------------------
DROP TABLE IF EXISTS `bf_role_allot_perm`;
CREATE TABLE `bf_role_allot_perm` (
  `ROLE_ALLOT_ID` int(11) NOT NULL COMMENT '角色可分配的权限单元',
  `PERM_ID` int(11) NOT NULL COMMENT '权限ID',
  `INST_DATE` char(8) DEFAULT NULL COMMENT '新增日期',
  `INST_TIME` char(6) DEFAULT NULL COMMENT '新增时间',
  `INST_OPER` char(20) DEFAULT NULL COMMENT '新增操作人',
  `MODI_DATE` char(8) DEFAULT NULL COMMENT '修改日期',
  `MODI_TIME` char(6) DEFAULT NULL COMMENT '修改时间',
  `MODI_OPER` char(20) DEFAULT NULL COMMENT '修改操作人',
  PRIMARY KEY (`ROLE_ALLOT_ID`,`PERM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 角色(分配) 权限关系表';

-- ----------------------------
-- Table structure for bf_role_limit
-- ----------------------------
DROP TABLE IF EXISTS `bf_role_limit`;
CREATE TABLE `bf_role_limit` (
  `LIMIT_NO` varchar(32) NOT NULL COMMENT '约束编号',
  `ROLE_ID` int(11) NOT NULL COMMENT '角色编号',
  `DES` varchar(200) DEFAULT NULL COMMENT '描述',
  `INST_DATE` char(8) DEFAULT NULL COMMENT '新增日期',
  `INST_TIME` char(6) DEFAULT NULL COMMENT '新增时间',
  `INST_OPER` char(20) DEFAULT NULL COMMENT '新增操作人',
  `MODI_DATE` char(8) DEFAULT NULL COMMENT '修改日期',
  `MODI_TIME` char(6) DEFAULT NULL COMMENT '修改时间',
  `MODI_OPER` char(20) DEFAULT NULL COMMENT '修改操作人',
  PRIMARY KEY (`LIMIT_NO`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 角色约束表(控制角色互斥)';

-- ----------------------------
-- Table structure for bf_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `bf_role_perm`;
CREATE TABLE `bf_role_perm` (
  `ROLE_ID` int(11) NOT NULL COMMENT '角色编号',
  `PERM_ID` int(11) NOT NULL COMMENT '权限ID，由系统自动生成唯一的值',
  `PERM_ATTR` varchar(32) DEFAULT NULL COMMENT '权限特性\r\n            1：显示可操作\r\n            2：显示不可操作',
  `INST_DATE` char(8) DEFAULT NULL COMMENT '新增日期',
  `INST_TIME` char(6) DEFAULT NULL COMMENT '新增时间',
  `INST_OPER` char(20) DEFAULT NULL COMMENT '新增操作人',
  `MODI_DATE` char(8) DEFAULT NULL COMMENT '修改日期',
  `MODI_TIME` char(6) DEFAULT NULL COMMENT '修改时间',
  `MODI_OPER` char(20) DEFAULT NULL COMMENT '修改操作人',
  PRIMARY KEY (`ROLE_ID`,`PERM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 角色与权限关系表';

-- ----------------------------
-- Table structure for bf_role_role_allot
-- ----------------------------
DROP TABLE IF EXISTS `bf_role_role_allot`;
CREATE TABLE `bf_role_role_allot` (
  `ROLE_ID` int(11) NOT NULL COMMENT '角色编号',
  `ROLE_ALLOT_ID` int(11) NOT NULL COMMENT '角色(分配)ID',
  `INST_DATE` char(8) DEFAULT NULL COMMENT '新增日期',
  `INST_TIME` char(6) DEFAULT NULL COMMENT '新增时间',
  `INST_OPER` char(20) DEFAULT NULL COMMENT '新增操作人',
  `MODI_DATE` char(8) DEFAULT NULL COMMENT '修改日期',
  `MODI_TIME` char(6) DEFAULT NULL COMMENT '修改时间',
  `MODI_OPER` char(20) DEFAULT NULL COMMENT '修改操作人',
  PRIMARY KEY (`ROLE_ID`,`ROLE_ALLOT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 角色与角色(分配)关系表';


-- ----------------------------
-- Table structure for bf_short_menu
-- ----------------------------
DROP TABLE IF EXISTS `bf_short_menu`;
CREATE TABLE `bf_short_menu` (
  `KEY_ID` varchar(32) NOT NULL COMMENT '代理主键',
  `PARENT_ID` varchar(32) DEFAULT NULL COMMENT '父ID，和代理主键对应',
  `USER_ID` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `MENU_ID` int(11) DEFAULT NULL COMMENT '菜单ID',
  `SHORT_MENU_NAME` varchar(64) DEFAULT NULL COMMENT '快捷菜单名称',
  `DISPLAY_ICON` varchar(32) DEFAULT NULL COMMENT '显示图标',
  `SEQNO` int(11) DEFAULT NULL COMMENT '排序',
  `DES` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`KEY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 快捷菜单表';


-- ----------------------------
-- Table structure for bf_task_detail
-- ----------------------------
DROP TABLE IF EXISTS `bf_task_detail`;
CREATE TABLE `bf_task_detail` (
  `KEY_ID` varchar(64) NOT NULL COMMENT '任务主键号 可以是业务主键也可以是工作流的activid',
  `TASK_ID` varchar(32) NOT NULL COMMENT '任务队列编号',
  `BUSI_KEY` varchar(64) NOT NULL COMMENT '业务主键',
  `TASK_DATA` varchar(8) DEFAULT NULL COMMENT '任务日期',
  `TASK_TIME` varchar(8) DEFAULT NULL COMMENT '任务时间',
  `TARGET_URL` varchar(64) DEFAULT NULL COMMENT '跳转链接',
  `PARAMS` varchar(200) DEFAULT NULL COMMENT '参数值',
  PRIMARY KEY (`TASK_ID`,`BUSI_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 任务明细 提供数据任务统计的任务明细表';

-- ----------------------------
-- Table structure for bf_task_limit_orgs
-- ----------------------------
DROP TABLE IF EXISTS `bf_task_limit_orgs`;
CREATE TABLE `bf_task_limit_orgs` (
  `KEY_ID` varchar(64) DEFAULT NULL COMMENT '主键号 可以是业务主键也可以是工作流的activid',
  `ORG_ID` varchar(32) NOT NULL COMMENT '机构',
  KEY `IDX_BF_TASK_LRGS_KEY_ID` (`KEY_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 机构限定表 根据机构来限制任务的可见';

-- ----------------------------
-- Table structure for bf_task_limit_roles
-- ----------------------------
DROP TABLE IF EXISTS `bf_task_limit_roles`;
CREATE TABLE `bf_task_limit_roles` (
  `KEY_ID` varchar(64) DEFAULT NULL COMMENT '主键号 可以是业务主键也可以是工作流的activid',
  `ROLE_ID` varchar(32) NOT NULL COMMENT '角色',
  KEY `IDX_BF_TASK_LLES_KEY_ID` (`KEY_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 角色限定表 根据角色来限制任务的可见';


-- ----------------------------
-- Table structure for bf_task_limit_users
-- ----------------------------
DROP TABLE IF EXISTS `bf_task_limit_users`;
CREATE TABLE `bf_task_limit_users` (
  `KEY_ID` varchar(64) DEFAULT NULL COMMENT '主键号 可以是业务主键也可以是工作流的activid',
  `USER_ID` varchar(32) NOT NULL COMMENT '用户',
  KEY `IDX_BF_TASK_LERS_KEY_ID` (`KEY_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 用户限定表 根据用户来限制任务的可见';


-- ----------------------------
-- Table structure for bf_task_log
-- ----------------------------
DROP TABLE IF EXISTS `bf_task_log`;
CREATE TABLE `bf_task_log` (
  `LOG_ID` varchar(32) NOT NULL COMMENT '日志编号',
  `KEY_ID` varchar(32) NOT NULL COMMENT '任务主键号 对应任务表中的主键',
  `TASK_ID` varchar(200) NOT NULL COMMENT '任务编号',
  `TASK_DATE` varchar(32) NOT NULL COMMENT '任务日期',
  `TASK_TIME` varchar(20) NOT NULL COMMENT '任务时间',
  `DEAL_OPER` varchar(10) DEFAULT NULL COMMENT '操作人员',
  `DEAL_DATE` varchar(8) DEFAULT NULL COMMENT '操作日期',
  `DEAL_TIME` varchar(8) DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 任务处理日志表 平台表-任务处理日志表';


-- ----------------------------
-- Table structure for bf_task_owner
-- ----------------------------
DROP TABLE IF EXISTS `bf_task_owner`;
CREATE TABLE `bf_task_owner` (
  `KEY_ID` varchar(32) NOT NULL COMMENT '主键ID',
  `TASK_ID` varchar(32) NOT NULL COMMENT '任务编号',
  `TASK_NAME` varchar(200) DEFAULT NULL COMMENT '任务名称',
  `TASK_ICON` varchar(32) DEFAULT NULL COMMENT '任务图标',
  `TASK_OWNER` varchar(20) NOT NULL COMMENT '所属用户',
  `SEQNO` int(11) NOT NULL COMMENT '排列顺序',
  `MENU_ID` int(11) NOT NULL COMMENT '菜单ID',
  `TASK_DESC` varchar(500) DEFAULT NULL COMMENT '任务描述',
  `SHOW_FLAG` varchar(1) DEFAULT NULL COMMENT '显示标志 0：独自显示 1：在包中显示 2：不显示 ',
  `PAKG_ID` varchar(32) DEFAULT NULL COMMENT '包主键ID 如果show_flag=1，则非空',
  `PAKG_FLAG` varchar(1) DEFAULT NULL COMMENT '包标记 0：非包  1：是包',
  PRIMARY KEY (`KEY_ID`),
  UNIQUE KEY `IDXU_BF_TASK_OWNER_TASK_ID` (`TASK_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 待办任务码显示基础表 平台表-待办任务码显示基础表';

-- ----------------------------
-- Table structure for bf_task_param
-- ----------------------------
DROP TABLE IF EXISTS `bf_task_param`;
CREATE TABLE `bf_task_param` (
  `TASK_ID` varchar(32) NOT NULL COMMENT '节点id',
  `TASK_NAME` varchar(64) NOT NULL COMMENT '节点名称',
  `MENU_ID` int(11) DEFAULT NULL COMMENT '菜单ID 为非，则表示只是显示统计数据',
  `MENU_NAME` varchar(64) DEFAULT NULL COMMENT '菜单名称',
  `RULE_ID` varchar(32) DEFAULT NULL COMMENT '规则编号',
  `DETAIL_MENU_ID` varchar(64) DEFAULT NULL COMMENT '明细菜单ID',
  PRIMARY KEY (`TASK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 任务参数表 个人快捷菜单信息表';


-- ----------------------------
-- Table structure for bf_task_regex
-- ----------------------------
DROP TABLE IF EXISTS `bf_task_regex`;
CREATE TABLE `bf_task_regex` (
  `RULE_ID` varchar(32) NOT NULL COMMENT '规则主键ID',
  `TASK_ID` varchar(32) NOT NULL COMMENT '节点',
  `OPER_FLAG` varchar(1) DEFAULT NULL COMMENT '是否指定到人 Y：指定到人；N：不用到人',
  `ROLE_FLAG` varchar(1) DEFAULT NULL COMMENT '是否限定角色 Y：限定角色；N：不限定',
  `ORG_FLAG` varchar(1) DEFAULT NULL COMMENT '是否限定机构 Y：限定机构；N：不限定',
  `DETAIL_FLAG` varchar(1) DEFAULT NULL COMMENT '是否进操作页面 Y：能进入操作页；N：只出列表页面',
  `DETAIL_MENU_ID` varchar(64) DEFAULT NULL COMMENT '进入明细的menuid',
  `INST_OPER` varchar(20) DEFAULT NULL COMMENT '新增人',
  `INST_DATE` varchar(10) DEFAULT NULL COMMENT '新增日期',
  `INST_TIME` varchar(10) DEFAULT NULL COMMENT '新增时间',
  `MODI_OPER` varchar(20) DEFAULT NULL COMMENT '修改人',
  `MODI_DATE` varchar(10) DEFAULT NULL COMMENT '修改日期',
  `MODI_TIME` varchar(10) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`RULE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 任务规则表-限制关联主键';

-- ----------------------------
-- Table structure for bf_task_regex_sub_info
-- ----------------------------
DROP TABLE IF EXISTS `bf_task_regex_sub_info`;
CREATE TABLE `bf_task_regex_sub_info` (
  `RULE_ID` varchar(32) NOT NULL COMMENT '规则主键ID',
  `LIMIT_FLAG` varchar(1) DEFAULT NULL COMMENT '限制类型 R:角色 U:用户 O:规则',
  `LIMIT_KEY_ID` varchar(32) DEFAULT NULL COMMENT '对应主键',
  KEY `IDX_BTR_SUB_RULE_ID` (`RULE_ID`,`LIMIT_FLAG`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 任务规则表-限制关联主键';

-- ----------------------------
-- Table structure for bf_user
-- ----------------------------
DROP TABLE IF EXISTS `bf_user`;
CREATE TABLE `bf_user` (
  `USER_ID` varchar(20) NOT NULL COMMENT '用户号',
  `USER_NAME` varchar(64) NOT NULL COMMENT '用户名称',
  `NICK_NAME` varchar(64) DEFAULT NULL COMMENT '昵称',
  `USER_PWD` varchar(32) NOT NULL COMMENT '用户密码 用户与密码进行MD5加密处理存储',
  `USER_STATUS` varchar(32) NOT NULL COMMENT '用户 状态\r\n            1：启用\r\n            0：停用',
  `ORG_ID` varchar(10) DEFAULT NULL COMMENT '机构 号',
  `CERT_TYPE` varchar(2) DEFAULT NULL COMMENT '证件 类型',
  `CERT_NO` varchar(32) DEFAULT NULL COMMENT 'CERT_NO',
  `MOBILE_PHONE` varchar(32) DEFAULT NULL COMMENT '移动电话',
  `TELEPHONE` varchar(32) DEFAULT NULL COMMENT '电话号码',
  `EMAIL` varchar(64) DEFAULT NULL COMMENT '电子邮箱',
  `LIMIT_IP` varchar(200) DEFAULT NULL COMMENT '登录受限IP 只能使用该IP，或IP组登录，ip组用半角逗号隔开',
  `ONLINE_SESSION_NUM` int(11) DEFAULT NULL COMMENT '在线会话数\r\n            登录成功加1，退出时减1；异常情况，由定时器置0处理。',
  `LOCK_FLAG` varchar(32) DEFAULT NULL COMMENT '锁定标志',
  `LOCK_DATE` char(8) DEFAULT NULL COMMENT '锁定日期',
  `LOCK_TIME` char(6) DEFAULT NULL COMMENT '锁定时间',
  `LOGIN_NUM` int(11) DEFAULT NULL COMMENT '登录尝试次数',
  `LAST_LOGIN_IP` varchar(32) DEFAULT NULL COMMENT '最后登录IP',
  `LAST_LOGIN_DATE` char(8) DEFAULT NULL COMMENT '最后登录日期',
  `LAST_LOGIN_TIME` char(6) DEFAULT NULL COMMENT '最后登录时间',
  `INST_DATE` char(8) DEFAULT NULL COMMENT '新增日期',
  `INST_TIME` char(6) DEFAULT NULL COMMENT '新增时间',
  `INST_OPER` varchar(20) DEFAULT NULL COMMENT '新增操作人',
  `MODI_DATE` char(8) DEFAULT NULL COMMENT '修改日期',
  `MODI_TIME` char(6) DEFAULT NULL COMMENT '修改时间',
  `MODI_OPER` varchar(20) DEFAULT NULL COMMENT '修改操作人',
  `MODI_PWD_DATE` char(8) DEFAULT NULL COMMENT '最后一次修改密码的日期',
  `MODI_PWD_TIME` char(6) DEFAULT NULL COMMENT '最后一次修改密码的时间',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 用户表';

-- ----------------------------
-- Table structure for bf_user_cfg
-- ----------------------------
DROP TABLE IF EXISTS `bf_user_cfg`;
CREATE TABLE `bf_user_cfg` (
  `USER_ID` varchar(20) NOT NULL COMMENT '用户号',
  `PARAM_CODE` varchar(32) NOT NULL COMMENT '参数 代码',
  `PARAM_VALUE` varchar(200) NOT NULL COMMENT '参数值',
  PRIMARY KEY (`USER_ID`,`PARAM_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 用户选项表';

-- ----------------------------
-- Table structure for bf_user_role
-- ----------------------------
DROP TABLE IF EXISTS `bf_user_role`;
CREATE TABLE `bf_user_role` (
  `USER_ID` varchar(20) NOT NULL COMMENT '用户号',
  `ROLE_ID` int(11) NOT NULL COMMENT '角色编号',
  `INST_DATE` char(8) DEFAULT NULL COMMENT '新增日期',
  `INST_TIME` char(6) DEFAULT NULL COMMENT '新增时间',
  `INST_OPER` char(20) DEFAULT NULL COMMENT '新增操作人',
  `MODI_DATE` char(8) DEFAULT NULL COMMENT '修改日期',
  `MODI_TIME` char(6) DEFAULT NULL COMMENT '修改时间',
  `MODI_OPER` char(20) DEFAULT NULL COMMENT '修改操作人',
  PRIMARY KEY (`USER_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表 - 用户与角色关系表';

 
-- ----------------------------
-- Table structure for tb_bh_fpserri(start for FFP)
-- ----------------------------
DROP TABLE IF EXISTS `tb_bh_fpserri`;
CREATE TABLE `tb_bh_fpserri` (
  `BATCH_ACDT` date NOT NULL,
  `FILE_NAME` varchar(30) NOT NULL,
  `BATCH_ID` varchar(35) DEFAULT NULL,
  `DOC_CNT` int(11) DEFAULT NULL,
  `STATUS` varchar(1) DEFAULT NULL,
  `RLTD_REF_MSGID` varchar(35) DEFAULT NULL,
  `RJCTG_PTY_RSN` varchar(30) DEFAULT NULL,
  `RJCTN_DT` date DEFAULT NULL,
  `RSN_DESC` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`BATCH_ACDT`,`FILE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_bh_fpspyci
-- ----------------------------
DROP TABLE IF EXISTS `tb_bh_fpspyci`;
CREATE TABLE `tb_bh_fpspyci` (
  `ID` int(11) NOT NULL,
  `BATCH_ID` varchar(35) DEFAULT NULL,
  `STATUS` varchar(1) DEFAULT NULL,
  `FILE_NAME` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for tb_bh_generated_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_bh_generated_file`;
CREATE TABLE `tb_bh_generated_file` (
  `FILE_NAME` varchar(255) NOT NULL,
  `FILE_TYPE` varchar(255) DEFAULT NULL,
  `GENERATE_TS` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`FILE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_bh_init_ecert
-- ----------------------------
DROP TABLE IF EXISTS `tb_bh_init_ecert`;
CREATE TABLE `tb_bh_init_ecert` (
  `ID` int(11) NOT NULL,
  `STATUS` varchar(1) DEFAULT NULL,
  `FILE_NAME` varchar(30) DEFAULT NULL,
  `ECERT_KEY` varchar(5000) DEFAULT NULL,
  `EFFECTIVE_DATE` timestamp NULL DEFAULT NULL,
  `EXPIRY_DATE` timestamp NULL DEFAULT NULL,
  `CREATE_DATE` date DEFAULT NULL,
  `LAST_MODIFY_DATE` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_bh_revoke_ceri
-- ----------------------------
DROP TABLE IF EXISTS `tb_bh_revoke_ceri`;
CREATE TABLE `tb_bh_revoke_ceri` (
  `CID` varchar(255) NOT NULL,
  `FILE_NAME` varchar(255) DEFAULT NULL,
  `CREATE_TS` timestamp NULL DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `ECERT_KEY` varchar(5000) DEFAULT NULL,
  `REVOKED_TS` timestamp NULL DEFAULT NULL,
  `MSG_ID` varchar(255) DEFAULT NULL,
  `BATCH_ID` varchar(255) DEFAULT NULL,
  `RANKING` int(11) DEFAULT NULL,
  PRIMARY KEY (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for tb_bh_inward_fpspyci
-- ----------------------------
DROP TABLE IF EXISTS `tb_bh_inward_fpspyci`;
CREATE TABLE `tb_bh_inward_fpspyci` (
  `ID` int(11) NOT NULL,
  `BATCH_ID` varchar(35) DEFAULT NULL,
  `STATUS` varchar(1) DEFAULT NULL,
  `FILE_NAME` varchar(30) DEFAULT NULL,
  `TRANSACTION_ID` varchar(35) DEFAULT NULL,
  `END_TO_END_ID` varchar(35) DEFAULT NULL,
  `FPS_REF` varchar(35) DEFAULT NULL,
  `INSTRUCTION_ID` varchar(35) DEFAULT NULL,
  `CATEGORY_PURPOSE` varchar(6) DEFAULT NULL,
  `ACCT_VERF` varchar(15) DEFAULT NULL,
  `SETTLEMENT_CUR` varchar(3) DEFAULT NULL,
  `SETTLEMENT_AMT` varchar(16) DEFAULT NULL,
  `SETTLEMENT_DATE` date DEFAULT NULL,
  `INSTRUCTED_CUR` varchar(3) DEFAULT NULL,
  `INSTRUCTED_AMT` varchar(16) DEFAULT NULL,
  `CHG_AGT_ID` varchar(3) DEFAULT NULL,
  `CHG_AGT_BIC` varchar(11) DEFAULT NULL,
  `CHG_CUR` varchar(3) DEFAULT NULL,
  `CHG_AMT` varchar(16) DEFAULT NULL,
  `DEBTOR_NAME` varchar(420) DEFAULT NULL,
  `DEBTOR_ACCTNO` varchar(34) DEFAULT NULL,
  `DEBTOR_ACCTNO_TYPE` varchar(4) DEFAULT NULL,
  `DEBTOR_AGT_ID` varchar(3) DEFAULT NULL,
  `DEBTOR_AGT_BIC` varchar(11) DEFAULT NULL,
  `CREDITOR_NAME` varchar(420) DEFAULT NULL,
  `CREDITOR_ACCTNO` varchar(34) DEFAULT NULL,
  `CREDITOR_ACCTNO_TYPE` varchar(4) DEFAULT NULL,
  `CREDITOR_AGT_ID` varchar(3) DEFAULT NULL,
  `CREDITOR_AGT_BIC` varchar(11) DEFAULT NULL,
  `PURPOSE_TYPE` varchar(1) DEFAULT NULL,
  `PURPOSE_CODE` varchar(4) DEFAULT NULL,
  `PURPOSE_OTHER` varchar(140) DEFAULT NULL,
  `REMIT_INFO` varchar(140) DEFAULT NULL,
  `MSG_INST_DATE` date DEFAULT NULL,
  `LAST_MODI_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `BIZ_SVC_TYPE` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_bh_inward_not_fpspyci
-- ----------------------------
DROP TABLE IF EXISTS `tb_bh_inward_not_fpspyci`;
CREATE TABLE `tb_bh_inward_not_fpspyci` (
  `ID` int(11) NOT NULL,
  `BATCH_ID` varchar(35) DEFAULT NULL,
  `STATUS` varchar(1) DEFAULT NULL,
  `FILE_NAME` varchar(30) DEFAULT NULL,
  `MSG_ID` varchar(35) DEFAULT NULL,
  `MSG_CREATE_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `NOT_ID` varchar(35) DEFAULT NULL,
  `NOT_CREATE_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `NOT_ACCT_ID` varchar(3) DEFAULT NULL,
  `NOT_ACCT_TYPE` varchar(4) DEFAULT NULL,
  `NOT_ENTRY_AMT` decimal(15,2) DEFAULT NULL,
  `NOT_ENTRY_CUR` varchar(3) DEFAULT NULL,
  `NOT_ENTRY_CDT_DBT_IND` varchar(4) DEFAULT NULL,
  `NOT_ENTRY_STATUS` varchar(4) DEFAULT NULL,
  `NOT_BANK_TX_CODE` varchar(6) DEFAULT NULL,
  `NOT_ENTRY_DTL_END_TO_END_ID` varchar(35) DEFAULT NULL,
  `NOT_ENTRY_DTL_TX_ID` varchar(35) DEFAULT NULL,
  `NOT_ENTRY_DTL_MANT_ID` varchar(35) DEFAULT NULL,
  `NOT_ENTRY_DTL_CLS_REF` varchar(35) DEFAULT NULL,
  `NOT_ENTRY_DTL_AMT` decimal(15,2) DEFAULT NULL,
  `NOT_ENTRY_DTL_CUR` varchar(3) DEFAULT NULL,
  `NOT_ENTRY_DTL_CDT_DBT_IND` varchar(4) DEFAULT NULL,
  `NOT_ENTRY_DTL_CHG_AMT` decimal(15,2) DEFAULT NULL,
  `NOT_ENTRY_DTL_CHG_CUR` varchar(3) DEFAULT NULL,
  `NOT_ENTRY_DTL_CHG_BEAR` varchar(4) DEFAULT NULL,
  `NOT_ENTRY_DTL_CHG_AGT_BIC` varchar(11) DEFAULT NULL,
  `NOT_ENTRY_DTL_CHG_AGT_ID` varchar(3) DEFAULT NULL,
  `RLTD_PTIES_DBTR_NAME` varchar(420) DEFAULT NULL,
  `RLTD_PTIES_DBTR_ORGID_BIC` varchar(11) DEFAULT NULL,
  `RLTD_PTIES_DBTR_ORGID_OTH_ID` varchar(35) DEFAULT NULL,
  `RLTD_PTIES_DBTR_ORGID_OTH_SCHME` varchar(4) DEFAULT NULL,
  `RLTD_PTIES_DBTR_ORGID_OTH_ISS` varchar(35) DEFAULT NULL,
  `RLTD_PTIES_DBTR_PRVTID_OTH_ID` varchar(35) DEFAULT NULL,
  `RLTD_PTIES_DBTR_PRVTID_OTH_SCHME` varchar(4) DEFAULT NULL,
  `RLTD_PTIES_DBTR_PRVTID_OTH_ISS` varchar(35) DEFAULT NULL,
  `RLTD_PTIES_DBTR_CONT_MOBILE` varchar(35) DEFAULT NULL,
  `RLTD_PTIES_DBTR_CONT_EMAIL` varchar(254) DEFAULT NULL,
  `RLTD_PTIES_DBTR_ACCT_ID` varchar(34) DEFAULT NULL,
  `RLTD_PTIES_DBTR_ACCT_SCHME` varchar(4) DEFAULT NULL,
  `RLTD_PTIES_CDTR_NAME` varchar(420) DEFAULT NULL,
  `RLTD_PTIES_CDTR_ORGID_BIC` varchar(11) DEFAULT NULL,
  `RLTD_PTIES_CDTR_ORGID_OTH_ID` varchar(35) DEFAULT NULL,
  `RLTD_PTIES_CDTR_ORGID_OTH_SCHME` varchar(4) DEFAULT NULL,
  `RLTD_PTIES_CDTR_ORGID_OTH_ISS` varchar(35) DEFAULT NULL,
  `RLTD_PTIES_CDTR_PRVTID_OTH_ID` varchar(35) DEFAULT NULL,
  `RLTD_PTIES_CDTR_PRVTID_OTH_SCHME` varchar(4) DEFAULT NULL,
  `RLTD_PTIES_CDTR_PRVTID_OTH_ISS` varchar(35) DEFAULT NULL,
  `RLTD_PTIES_CDTR_CONT_MOBILE` varchar(35) DEFAULT NULL,
  `RLTD_PTIES_CDTR_CONT_EMAIL` varchar(254) DEFAULT NULL,
  `RLTD_PTIES_CDTR_ACCT_ID` varchar(34) DEFAULT NULL,
  `RLTD_PTIES_CDTR_ACCT_SCHME` varchar(4) DEFAULT NULL,
  `RLTD_DBTR_AGT_BIC` varchar(11) DEFAULT NULL,
  `RLTD_DBTR_AGT_ID` varchar(3) DEFAULT NULL,
  `RLTD_CDTR_AGT_BIC` varchar(11) DEFAULT NULL,
  `RLTD_CDTR_AGT_ID` varchar(3) DEFAULT NULL,
  `PURP_CODE` varchar(4) DEFAULT NULL,
  `PURP_OTHER` varchar(35) DEFAULT NULL,
  `REMI_INFO` varchar(420) DEFAULT NULL,
  `RLTD_INTER_SETTLE_DATE` date DEFAULT NULL,
  `RLTD_TX_DATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `RETURN_INFO_CODE` varchar(4) DEFAULT NULL,
  `RETURN_INFO_RSN` varchar(420) DEFAULT NULL,
  `BIZ_SVC_TYPE` varchar(6) DEFAULT NULL,
  `MSG_INST_DATE` date DEFAULT NULL,
  `LAST_MODI_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for tb_bh_inward_return_fpspyci
-- ----------------------------
DROP TABLE IF EXISTS `tb_bh_inward_return_fpspyci`;
CREATE TABLE `tb_bh_inward_return_fpspyci` (
  `ID` int(11) NOT NULL,
  `RETURN_ID` varchar(35) DEFAULT NULL,
  `BATCH_ID` varchar(35) DEFAULT NULL,
  `STATUS` varchar(1) DEFAULT NULL,
  `FILE_NAME` varchar(30) DEFAULT NULL,
  `SETTLEMENT_METHOD` varchar(4) DEFAULT NULL,
  `CLEARING_SYSTEM` varchar(3) DEFAULT NULL,
  `RETURN_SETTLEMENT_CUR` varchar(3) DEFAULT NULL,
  `RETURN_SETTLEMENT_AMT` varchar(16) DEFAULT NULL,
  `RETURN_SETTLEMENT_DATE` date DEFAULT NULL,
  `RETURN_INSTRUCTED_CUR` varchar(3) DEFAULT NULL,
  `RETURN_INSTRUCTED_AMT` varchar(16) DEFAULT NULL,
  `RETURN_CHG_AGT_ID` varchar(3) DEFAULT NULL,
  `RETURN_CHG_AGT_BIC` varchar(11) DEFAULT NULL,
  `RETURN_CHG_CUR` varchar(3) DEFAULT NULL,
  `RETURN_CHG_AMT` varchar(16) DEFAULT NULL,
  `RETURN_CODE` varchar(8) DEFAULT NULL,
  `RETURN_REASON` varchar(500) DEFAULT NULL,
  `BIZ_SVC_TYPE` varchar(6) DEFAULT NULL,
  `ORIG_INSTRUCTION_ID` varchar(35) DEFAULT NULL,
  `ORIG_END_TO_END_ID` varchar(35) DEFAULT NULL,
  `ORIG_TRANSACTION_ID` varchar(35) DEFAULT NULL,
  `ORIG_FPS_REF` varchar(35) DEFAULT NULL,
  `ORIG_SETTLEMENT_AMT` varchar(16) DEFAULT NULL,
  `ORIG_SETTLEMENT_CUR` varchar(3) DEFAULT NULL,
  `ORIG_SETTLEMENT_DATE` date DEFAULT NULL,
  `ORIG_CATEGORY_PURPOSE` varchar(6) DEFAULT NULL,
  `ORIG_MANDATE_INFO` varchar(35) DEFAULT NULL,
  `ORIG_REM_INFO` varchar(140) DEFAULT NULL,
  `ORIG_DEBTOR_NAME` varchar(420) DEFAULT NULL,
  `ORIG_DEBTOR_ACCTNO` varchar(34) DEFAULT NULL,
  `ORIG_DEBTOR_ACCTNO_TYPE` varchar(4) DEFAULT NULL,
  `ORIG_DEBTOR_AGT_ID` varchar(3) DEFAULT NULL,
  `ORIG_DEBTOR_AGT_BIC` varchar(11) DEFAULT NULL,
  `ORIG_CREDITOR_NAME` varchar(420) DEFAULT NULL,
  `ORIG_CREDITOR_ACCTNO` varchar(34) DEFAULT NULL,
  `ORIG_CREDITOR_ACCTNO_TYPE` varchar(4) DEFAULT NULL,
  `ORIG_CREDITOR_AGT_ID` varchar(3) DEFAULT NULL,
  `ORIG_CREDITOR_AGT_BIC` varchar(11) DEFAULT NULL,
  `MSG_INST_DATE` date DEFAULT NULL,
  `LAST_MODI_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_bh_job_jnl
-- ----------------------------
DROP TABLE IF EXISTS `tb_bh_job_jnl`;
CREATE TABLE `tb_bh_job_jnl` (
  `BATCH_AC_DATE` varchar(10) NOT NULL,
  `JOB_ID` varchar(64) NOT NULL,
  `JNL_SEQ` int(11) NOT NULL,
  `JOB_STAT` varchar(1) DEFAULT NULL,
  `RESULT` varchar(1) DEFAULT NULL,
  `START_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ACTION_JNL` varchar(1500) DEFAULT NULL,
  `END_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `BREAK_POINT` varchar(1500) DEFAULT NULL,
  PRIMARY KEY (`BATCH_AC_DATE`,`JOB_ID`,`JNL_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_bh_outward_fpspcrr
-- ----------------------------
DROP TABLE IF EXISTS `tb_bh_outward_fpspcrr`;
CREATE TABLE `tb_bh_outward_fpspcrr` (
  `ID` int(11) NOT NULL,
  `BATCH_ID` varchar(35) DEFAULT NULL,
  `STATUS` varchar(1) DEFAULT NULL,
  `FILE_NAME` varchar(30) DEFAULT NULL,
  `MSG_ID` varchar(35) DEFAULT NULL,
  `MSG_CREATE_TS` timestamp NULL DEFAULT NULL,
  `ORG_MSG_ID` varchar(35) DEFAULT NULL,
  `ORG_MSG_NM_ID` varchar(35) DEFAULT NULL,
  `GRP_STS` varchar(4) DEFAULT NULL,
  `GRP_REJ_CODE` varchar(8) DEFAULT NULL,
  `GRP_REJ_RSN` varchar(500) DEFAULT NULL,
  `ORG_END_TO_END_ID` varchar(35) DEFAULT NULL,
  `ORG_TRANSACTION_ID` varchar(35) DEFAULT NULL,
  `TX_STAT` varchar(4) DEFAULT NULL,
  `TX_REJ_CODE` varchar(8) DEFAULT NULL,
  `TX_ADD_INFO` varchar(500) DEFAULT NULL,
  `TX_ACCEPT_TS` timestamp NULL DEFAULT NULL,
  `TX_CLR_SYS_REF` varchar(35) DEFAULT NULL,
  `ORG_INTR_BANK_SETT_AMT` decimal(15,2) DEFAULT NULL,
  `ORG_INTR_BANK_SETT_CUR` varchar(3) DEFAULT NULL,
  `ORG_INTR_BANK_SETT_DATE` date DEFAULT NULL,
  `BIZ_SVC_TYPE` varchar(6) DEFAULT NULL,
  `CREATE_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_MODI_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_bh_processed_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_bh_processed_file`;
CREATE TABLE `tb_bh_processed_file` (
  `FILE_NAME` varchar(30) NOT NULL,
  `PROCESSED_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`FILE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for tb_dt_addressing
-- ----------------------------
DROP TABLE IF EXISTS `tb_dt_addressing`;
CREATE TABLE `tb_dt_addressing` (
  `CUS_ID` varchar(34) NOT NULL,
  `CUS_TP` varchar(4) DEFAULT NULL,
  `PROXY_ID` varchar(34) NOT NULL,
  `PROXY_ID_TP` varchar(4) NOT NULL,
  `DFLT` varchar(3) DEFAULT NULL,
  `CLR_CD` varchar(3) DEFAULT NULL COMMENT 'clearing code',
  `FULL_NM_EN` varchar(140) DEFAULT NULL,
  `DISP_NM_EN` varchar(140) DEFAULT NULL,
  `FULL_NM_ZH` varchar(140) DEFAULT NULL,
  `DISP_NM_ZH` varchar(140) DEFAULT NULL,
  `SUP_OP_CD` varchar(4) DEFAULT NULL,
  `PURP_CD` varchar(4) DEFAULT NULL,
  `LSTUP_JNL` varchar(35) DEFAULT NULL COMMENT 'last update jnl_no',
  PRIMARY KEY (`CUS_ID`,`PROXY_ID`,`PROXY_ID_TP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




-- ----------------------------
-- Table structure for tb_dt_addressing_acct
-- ----------------------------
DROP TABLE IF EXISTS `tb_dt_addressing_acct`;
CREATE TABLE `tb_dt_addressing_acct` (
  `CUS_ID` varchar(34) NOT NULL,
  `PROXY_ID` varchar(34) NOT NULL,
  `PROXY_ID_TP` varchar(4) NOT NULL,
  `ACCT_NUM` varchar(14) NOT NULL,
  `ACCT_TP` varchar(5) DEFAULT NULL,
  `ACCT_CUR` varchar(3) DEFAULT NULL,
  `ACCT_DEF` decimal(3,0) DEFAULT NULL,
  `CLR_CD` varchar(3) DEFAULT NULL COMMENT 'clearing code',
  `SRVC_TP` varchar(2) DEFAULT NULL,
  `LSTUP_JNL` varchar(35) DEFAULT NULL COMMENT 'last update jnl_no',
  PRIMARY KEY (`CUS_ID`,`PROXY_ID`,`PROXY_ID_TP`,`ACCT_NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for tb_dt_fps_participant_mode
-- ----------------------------
DROP TABLE IF EXISTS `tb_dt_fps_participant_mode`;
CREATE TABLE `tb_dt_fps_participant_mode` (
  `Clearing_CODE` varchar(3) NOT NULL,
  `Receipt_Mode` varchar(5) NOT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `REF_MSG_ID` varchar(35) NOT NULL,
  PRIMARY KEY (`Clearing_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_dt_fps_payment_notification
-- ----------------------------
DROP TABLE IF EXISTS `tb_dt_fps_payment_notification`;
CREATE TABLE `tb_dt_fps_payment_notification` (
  `MSG_ID` varchar(35) NOT NULL,
  `MSG_Create_Ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ntfctnId` varchar(35) NOT NULL,
  `ntfctnCreateTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ntfctnAcctId` varchar(3) DEFAULT NULL,
  `ntfctnAcctType` varchar(4) DEFAULT NULL,
  `ntryAmt` decimal(15,2) DEFAULT NULL,
  `ntryAmtCcy` varchar(3) DEFAULT NULL,
  `ntryCdtDbtInd` varchar(4) DEFAULT NULL,
  `ntryStatus` varchar(4) DEFAULT NULL,
  `ntryBankTransCode` varchar(6) DEFAULT NULL,
  `ntryDetailEndToEndId` varchar(35) DEFAULT NULL,
  `ntryDetailTxId` varchar(35) DEFAULT NULL,
  `ntryDetailMandateId` varchar(35) DEFAULT NULL,
  `ntryDetailClrSysRef` varchar(35) DEFAULT NULL,
  `ntryDetailAmt` decimal(15,2) DEFAULT NULL,
  `ntryDetailAmtCcy` varchar(3) DEFAULT NULL,
  `ntryDetailCdtDbtInd` varchar(4) DEFAULT NULL,
  `ntryDetailChrgsAmt` decimal(15,2) DEFAULT NULL,
  `ntryDetailChrgsAmtCcy` varchar(3) DEFAULT NULL,
  `ntryDetailChrgsBr` varchar(4) DEFAULT NULL,
  `ntryDetailChrgsAgtBic` varchar(11) DEFAULT NULL,
  `ntryDetailChrgsAgtMmbId` varchar(3) DEFAULT NULL,
  `RltdPtiesDbtrName` varchar(420) DEFAULT NULL,
  `RltdPtiesDbtrOrgIdBIC` varchar(11) DEFAULT NULL,
  `RltdPtiesDbtrOrgIdOthrId` varchar(35) DEFAULT NULL,
  `RltdPtiesDbtrOrgIdOthrSchme` varchar(4) DEFAULT NULL,
  `RltdPtiesDbtrOrgIdOthrIssr` varchar(35) DEFAULT NULL,
  `RltdPtiesDbtrPrvtIdOthrId` varchar(35) DEFAULT NULL,
  `RltdPtiesDbtrPrvtIdOthrSchme` varchar(4) DEFAULT NULL,
  `RltdPtiesDbtrPrvtIdOthrIssr` varchar(35) DEFAULT NULL,
  `RltdPtiesDbtrContactMobile` varchar(35) DEFAULT NULL,
  `RltdPtiesDbtrContactEmail` varchar(254) DEFAULT NULL,
  `RltdPtiesDbtrAcctId` varchar(34) DEFAULT NULL,
  `RltdPtiesDbtrAcctScheme` varchar(4) DEFAULT NULL,
  `RltdPtiesCdtrName` varchar(420) DEFAULT NULL,
  `RltdPtiesCdtrOrgIdBIC` varchar(11) DEFAULT NULL,
  `RltdPtiesCdtrOrgIdOthrId` varchar(35) DEFAULT NULL,
  `RltdPtiesCdtrOrgIdOthrSchme` varchar(4) DEFAULT NULL,
  `RltdPtiesCdtrOrgIdOthrIssr` varchar(35) DEFAULT NULL,
  `RltdPtiesCdtrPrvtIdOthrId` varchar(35) DEFAULT NULL,
  `RltdPtiesCdtrPrvtIdOthrSchme` varchar(4) DEFAULT NULL,
  `RltdPtiesCdtrPrvtIdOthrIssr` varchar(35) DEFAULT NULL,
  `RltdPtiesCdtrContactMobile` varchar(35) DEFAULT NULL,
  `RltdPtiesCdtrContactEmail` varchar(254) DEFAULT NULL,
  `RltdPtiesCdtrAcctId` varchar(34) DEFAULT NULL,
  `RltdPtiesCdtrAcctScheme` varchar(4) DEFAULT NULL,
  `relatedAgentsDbtrBIC` varchar(11) DEFAULT NULL,
  `relatedAgentsDbtrMmbId` varchar(3) DEFAULT NULL,
  `relatedAgentsCdtrBIC` varchar(11) DEFAULT NULL,
  `relatedAgentsCdtrMmbId` varchar(3) DEFAULT NULL,
  `PurpCode` varchar(4) DEFAULT NULL,
  `PurpOther` varchar(35) DEFAULT NULL,
  `remitInfUstrd` varchar(420) DEFAULT NULL,
  `relatedDatesIntrSettlDate` date DEFAULT NULL,
  `relatedDatesTransTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `returnInfRsn` varchar(4) DEFAULT NULL,
  `returnInfMsg` varchar(420) DEFAULT NULL,
  PRIMARY KEY (`MSG_ID`,`ntfctnId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_ss_cutoff
-- ----------------------------
DROP TABLE IF EXISTS `tb_ss_cutoff`;
CREATE TABLE `tb_ss_cutoff` (
  `CUTOFF_TYPE` varchar(5) NOT NULL,
  `WORKDAY_START` time NOT NULL,
  `WORKDAY_END` time NOT NULL,
  `SAT_START` time NOT NULL,
  `SAT_END` time NOT NULL,
  `HOLIDAY_START` time NOT NULL,
  `HOLIDAY_END` time NOT NULL,
  PRIMARY KEY (`CUTOFF_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_ss_seqnum
-- ----------------------------
DROP TABLE IF EXISTS `tb_ss_seqnum`;
CREATE TABLE `tb_ss_seqnum` (
  `SEQ_NAME` varchar(32) NOT NULL,
  `CURRENT_VAL` int(10) NOT NULL,
  `INCREMENT_VAL` int(3) NOT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_ss_system
-- ----------------------------
DROP TABLE IF EXISTS `tb_ss_system`;
CREATE TABLE `tb_ss_system` (
  `DATA_KEY` varchar(4) NOT NULL,
  `BATCH_ACDT` date NOT NULL,
  `RUNNING_MODE` varchar(5) NOT NULL,
  `FPS_RECEIVE_MODE` varchar(5) NOT NULL,
  `REALTIME_CONTROL_STAT` varchar(2) NOT NULL,
  `REALTIME_LISTENER_STAT` varchar(2) NOT NULL,
  `BATCH_LISTENER_STAT` varchar(2) NOT NULL,
  PRIMARY KEY (`DATA_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_tx_a100dat
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_a100dat`;
CREATE TABLE `tb_tx_a100dat` (
  `JNL_NO` varchar(35) NOT NULL,
  `ADR_REQ_ID` varchar(35) DEFAULT NULL COMMENT 'code generate, communicate with ICL',
  `MSG_TP` varchar(4) DEFAULT NULL,
  `PROXY_ID` varchar(34) DEFAULT NULL,
  `PROXY_ID_TP` varchar(4) DEFAULT NULL,
  `CLR_CD` varchar(3) DEFAULT NULL,
  `LANG_EN` varchar(2) DEFAULT NULL,
  `FULL_NM_EN` varchar(140) DEFAULT NULL,
  `DISP_NM_EN` varchar(140) DEFAULT NULL,
  `LANG_ZH` varchar(2) DEFAULT NULL,
  `FULL_NM_ZH` varchar(140) DEFAULT NULL,
  `DISP_NM_ZH` varchar(140) DEFAULT NULL,
  `CUS_ID` varchar(34) DEFAULT NULL,
  `CUS_TP` varchar(4) DEFAULT NULL,
  `SUP_OP_CD` varchar(4) DEFAULT NULL,
  `DFLT` varchar(3) DEFAULT NULL,
  `PURP_CD` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`JNL_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for tb_tx_a110dat
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_a110dat`;
CREATE TABLE `tb_tx_a110dat` (
  `JNL_NO` varchar(35) NOT NULL,
  `STAT` varchar(1) DEFAULT NULL,
  `ADR_REQ_ID` varchar(35) DEFAULT NULL,
  `PROXY_ID` varchar(34) DEFAULT NULL,
  `PROXY_ID_TP` varchar(4) DEFAULT NULL,
  `NOOFADR` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`JNL_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_tx_a110dat_dtl
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_a110dat_dtl`;
CREATE TABLE `tb_tx_a110dat_dtl` (
  `JNL_NO` varchar(35) NOT NULL,
  `ADR_REQ_ID` varchar(35) DEFAULT NULL,
  `STS` varchar(4) DEFAULT NULL,
  `RJ_CD` varchar(8) DEFAULT NULL,
  `PROXY_ID` varchar(34) DEFAULT NULL,
  `PROXY_ID_TP` varchar(4) DEFAULT NULL,
  `NO_OF_ADR` varchar(15) DEFAULT NULL,
  `LANG_EN` varchar(2) DEFAULT NULL,
  `DISP_NM_EN` varchar(140) DEFAULT NULL,
  `LANG_ZH` varchar(2) DEFAULT NULL,
  `DISP_NM_ZH` varchar(140) DEFAULT NULL,
  `MMBID` varchar(3) DEFAULT NULL,
  `DEF_IND` varchar(3) DEFAULT NULL,
  `PURP_CD` varchar(4) DEFAULT NULL,
  `CRE_DT_TM` datetime DEFAULT NULL,
  `LST_UPD_DT_TM` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_tx_a120dat
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_a120dat`;
CREATE TABLE `tb_tx_a120dat` (
  `JNL_NO` varchar(35) NOT NULL,
  `STAT` varchar(1) DEFAULT NULL,
  `ADR_REQ_ID` varchar(35) DEFAULT NULL,
  `PROXY_ID` varchar(34) DEFAULT NULL,
  `PROXY_ID_TP` varchar(4) DEFAULT NULL,
  `PURP_CD` varchar(4) DEFAULT NULL,
  `MMBID` varchar(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_tx_a120dat_dtl
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_a120dat_dtl`;
CREATE TABLE `tb_tx_a120dat_dtl` (
  `JNL_NO` varchar(35) NOT NULL,
  `ADR_REQ_ID` varchar(35) DEFAULT NULL,
  `STS` varchar(4) DEFAULT NULL,
  `RSN_INF_CD` varchar(8) DEFAULT NULL,
  `PROXY_ID` varchar(34) DEFAULT NULL,
  `PROXY_ID_TP` varchar(4) DEFAULT NULL,
  `PURP_CD` varchar(4) DEFAULT NULL,
  `LANG_EN` varchar(2) DEFAULT NULL,
  `FULL_NM_EN` varchar(140) DEFAULT NULL,
  `DISP_NM_EN` varchar(140) DEFAULT NULL,
  `LANG_ZH` varchar(2) DEFAULT NULL,
  `FULL_NM_ZH` varchar(140) DEFAULT NULL,
  `DISP_NM_ZH` varchar(140) DEFAULT NULL,
  `RSLT_CUS_ID` varchar(34) DEFAULT NULL,
  `MMBID` varchar(3) DEFAULT NULL,
  `RSLT_CUS_TP` varchar(4) DEFAULT NULL,
  `SUP_OP_CD` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_tx_check_temp
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_check_temp`;
CREATE TABLE `tb_tx_check_temp` (
  `CID` varchar(21) NOT NULL,
  `FILE_NAME` varchar(255) DEFAULT NULL,
  `CHECK_STATUS` varchar(1) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `REQUEST_ID` varchar(255) DEFAULT NULL,
  `TRANSACTION_DATE` varchar(255) DEFAULT NULL,
  `TRANSACTION_TIME` varchar(255) DEFAULT NULL,
  `REQUEST_REF_NO` varchar(255) DEFAULT NULL,
  `ACCOUNTING_DATE` varchar(255) DEFAULT NULL,
  `RESPONSE_ID` varchar(255) DEFAULT NULL,
  `MESSAGE_TYPE` varchar(255) DEFAULT NULL,
  `SYSTEM_REF_NO` varchar(255) DEFAULT NULL,
  `SYSTEM_BEGIN_TIME` varchar(255) DEFAULT NULL,
  `SYSTEM_END_TIME` varchar(255) DEFAULT NULL,
  `RESPONSE_REF_NO` varchar(255) DEFAULT NULL,
  `RESPONSE_BEGIN_TIME` varchar(255) DEFAULT NULL,
  `RESPONSE_END_TIME` varchar(255) DEFAULT NULL,
  `RESPONSE_STATUS` varchar(255) DEFAULT NULL,
  `FINAL_NODE` varchar(255) DEFAULT NULL,
  `SYSTEM_MESSAGE_CODE` varchar(255) DEFAULT NULL,
  `RESPONSE_MESSAGE_CODE` varchar(255) DEFAULT NULL,
  `RESPONSE_MESSAGE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_tx_e100dat
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_e100dat`;
CREATE TABLE `tb_tx_e100dat` (
  `JNL_NO` varchar(35) NOT NULL,
  `MSG_ID` varchar(35) NOT NULL,
  `CRE_DT_TM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MNDT_ID` varchar(16) NOT NULL,
  `MNDT_REQ_ID` varchar(16) DEFAULT NULL,
  `TP_LOCAL_CD` varchar(16) DEFAULT NULL,
  `SEQ_TP` varchar(16) DEFAULT NULL,
  `PRD_TP` varchar(16) DEFAULT NULL,
  `CNT_PER_PRD` varchar(18) DEFAULT NULL,
  `FR_DT` varchar(16) DEFAULT NULL,
  `TO_DT` varchar(16) DEFAULT NULL,
  `TRCKGIND` varchar(16) DEFAULT NULL,
  `COLLTN_AMT` varchar(16) DEFAULT NULL,
  `COLLTN_AMT_CCY` varchar(16) DEFAULT NULL,
  `MAX_AMT` varchar(16) DEFAULT NULL,
  `MAX_AMT_CCY` varchar(16) DEFAULT NULL,
  `RSN_PRTRY` varchar(16) DEFAULT NULL,
  `CDTR_NM` varchar(16) DEFAULT NULL,
  `CDTR_OTHR_ID` varchar(35) DEFAULT NULL,
  `CDTR_OTHR_CD` varchar(16) DEFAULT NULL,
  `CDTR_OTHR_PRVTID_ID` varchar(16) DEFAULT NULL,
  `CDTR_OTHR_PRVTID_CD` varchar(16) DEFAULT NULL,
  `CDTRACCT_OTHER_ID` varchar(35) DEFAULT NULL,
  `CDTRACCT_PRTRY` varchar(16) DEFAULT NULL,
  `CDTRAGT_BICFI` varchar(16) DEFAULT NULL,
  `CDTRAGT_MMBID` varchar(16) DEFAULT NULL,
  `DBTR_NM` varchar(16) DEFAULT NULL,
  `DBTR_OTHER_ID` varchar(35) DEFAULT NULL,
  `DBTR_OTHER_CD` varchar(16) DEFAULT NULL,
  `DBTR_OTHER_PRVTID_ID` varchar(16) DEFAULT NULL,
  `DBTR_OTHER_PRVTID_CD` varchar(16) DEFAULT NULL,
  `DBTRACCT_OTHER_ID` varchar(35) DEFAULT NULL,
  `DBTRACCT_PRTRY` varchar(16) DEFAULT NULL,
  `DBTRACCT_BICFI` varchar(16) DEFAULT NULL,
  `DBTRACCT_MMBID` varchar(16) DEFAULT NULL,
  `ULTMT_DBTR_NM` varchar(16) DEFAULT NULL,
  `CDTR_REF` varchar(35) DEFAULT NULL,
  `OPERA` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`JNL_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for tb_tx_e100dat_dtl
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_e100dat_dtl`;
CREATE TABLE `tb_tx_e100dat_dtl` (
  `JNL_NO` varchar(35) NOT NULL,
  `MSG_ID` varchar(35) NOT NULL,
  `CRE_DT_TM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MNDT_ID` varchar(16) NOT NULL,
  `MNDT_REQ_ID` varchar(16) DEFAULT NULL,
  `TP_LOCAL_CD` varchar(16) DEFAULT NULL,
  `SEQ_TP` varchar(16) DEFAULT NULL,
  `PRD_TP` varchar(16) DEFAULT NULL,
  `CNT_PER_PRD` varchar(18) DEFAULT NULL,
  `FR_DT` varchar(16) DEFAULT NULL,
  `TO_DT` varchar(16) DEFAULT NULL,
  `TRCKGIND` varchar(16) DEFAULT NULL,
  `COLLTN_AMT` varchar(16) DEFAULT NULL,
  `COLLTN_AMT_CCY` varchar(16) DEFAULT NULL,
  `MAX_AMT` varchar(16) DEFAULT NULL,
  `MAX_AMT_CCY` varchar(16) DEFAULT NULL,
  `RSN_PRTRY` varchar(16) DEFAULT NULL,
  `CDTR_NM` varchar(16) DEFAULT NULL,
  `CDTR_OTHR_ID` varchar(35) DEFAULT NULL,
  `CDTR_OTHR_CD` varchar(16) DEFAULT NULL,
  `CDTR_OTHR_PRVTID_ID` varchar(16) DEFAULT NULL,
  `CDTR_OTHR_PRVTID_CD` varchar(16) DEFAULT NULL,
  `CDTRACCT_OTHER_ID` varchar(35) DEFAULT NULL,
  `CDTRACCT_PRTRY` varchar(16) DEFAULT NULL,
  `CDTRAGT_BICFI` varchar(16) DEFAULT NULL,
  `CDTRAGT_MMBID` varchar(16) DEFAULT NULL,
  `DBTR_NM` varchar(16) DEFAULT NULL,
  `DBTR_OTHER_ID` varchar(35) DEFAULT NULL,
  `DBTR_OTHER_CD` varchar(16) DEFAULT NULL,
  `DBTR_OTHER_PRVTID_ID` varchar(16) DEFAULT NULL,
  `DBTR_OTHER_PRVTID_CD` varchar(16) DEFAULT NULL,
  `DBTRACCT_OTHER_ID` varchar(35) DEFAULT NULL,
  `DBTRACCT_PRTRY` varchar(16) DEFAULT NULL,
  `DBTRACCT_BICFI` varchar(16) DEFAULT NULL,
  `DBTRACCT_MMBID` varchar(16) DEFAULT NULL,
  `ULTMT_DBTR_NM` varchar(16) DEFAULT NULL,
  `CDTR_REF` varchar(35) DEFAULT NULL,
  `STATUS` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`JNL_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for tb_tx_i100dat
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_i100dat`;
CREATE TABLE `tb_tx_i100dat` (
  `JNL_NO` varchar(35) NOT NULL,
  `MSG_ID` varchar(35) NOT NULL,
  `CCY` varchar(3) NOT NULL,
  `BALANCE_TYPE_CODE` varchar(4) DEFAULT NULL,
  `BALANCE` decimal(17,2) DEFAULT NULL,
  `CREDIT_DEBIT_IND` varchar(4) DEFAULT NULL,
  `BALANCE_RPT_TS` date DEFAULT NULL,
  PRIMARY KEY (`JNL_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_tx_i110dat
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_i110dat`;
CREATE TABLE `tb_tx_i110dat` (
  `JNL_NO` varchar(35) NOT NULL,
  `TX_STAT` varchar(15) DEFAULT NULL,
  `TX_CODE` varchar(5) DEFAULT NULL,
  `CLR_SYS_REF` varchar(32) DEFAULT NULL,
  `TRANSACTION_ID` varchar(35) DEFAULT NULL,
  `DBTR_AGT_MMB_ID` varchar(16) DEFAULT NULL,
  `CDTR_AGT_MMB_ID` varchar(16) DEFAULT NULL,
  `END_TO_END_ID` varchar(35) DEFAULT NULL,
  `TX_REJ_CODE` varchar(20) DEFAULT NULL,
  `TX_REJ_REASON` varchar(10000) DEFAULT NULL,
  `CREATE_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`JNL_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_tx_jnl
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_jnl`;
CREATE TABLE `tb_tx_jnl` (
  `JNL_NO` varchar(35) NOT NULL,
  `SRC_REF_NM` varchar(30) DEFAULT NULL,
  `TX_STAT` varchar(15) NOT NULL,
  `TX_CODE` varchar(5) NOT NULL,
  `TX_SRC` varchar(10) NOT NULL,
  `TX_MODE` varchar(8) NOT NULL,
  `TRANSACTION_ID` varchar(35) DEFAULT NULL,
  `END_TO_END_ID` varchar(35) DEFAULT NULL,
  `TX_FILE_NAME` varchar(30) DEFAULT NULL,
  `FPS_REF_NUM` varchar(35) DEFAULT NULL,
  `RESEND_COUNT` int(1) unsigned DEFAULT '0',
  `TX_REJ_CODE` varchar(20) DEFAULT NULL,
  `TX_REJ_REASON` varchar(1000) DEFAULT NULL,
  `JNL_REF` varchar(35) DEFAULT NULL,
  `CREATE_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`JNL_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_tx_jnlaction
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_jnlaction`;
CREATE TABLE `tb_tx_jnlaction` (
  `JNL_NO` varchar(35) NOT NULL,
  `MSG_ID` varchar(35) NOT NULL,
  `MSG_DIRECTION` varchar(1) NOT NULL,
  `MSG_SYSTEMID` varchar(11) NOT NULL,
  `MSG_TYPE` varchar(50) NOT NULL,
  `MSG_STATUS` varchar(15) NOT NULL,
  `MSG_CODE` varchar(15) DEFAULT NULL,
  `MSG_RESULT` varchar(255) DEFAULT NULL,
  `MSG_CREAT_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MSG_PROCE_TS` timestamp NULL DEFAULT NULL,
  `MSG_COMPL_TS` timestamp NULL DEFAULT NULL,
  `REF_MSG_ID` varchar(35) DEFAULT NULL,
  `IS_AUTOCHECK` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`JNL_NO`,`MSG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_tx_jnl_res_temp
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_jnl_res_temp`;
CREATE TABLE `tb_tx_jnl_res_temp` (
  `TRANSACTION_ID` varchar(35) DEFAULT NULL,
  `END_TO_END_ID` varchar(35) DEFAULT NULL,
  `TX_STAT` varchar(15) DEFAULT NULL,
  `REJ_CODE` varchar(15) DEFAULT NULL,
  `REJ_RSN` varchar(255) DEFAULT NULL,
  `CREATE_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_tx_m100dat
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_m100dat`;
CREATE TABLE `tb_tx_m100dat` (
  `JNL_NO` varchar(35) NOT NULL,
  `MSG_ID` varchar(32) NOT NULL,
  `RCPT_MD` varchar(16) NOT NULL,
  `ACCP_STATUS` varchar(16) DEFAULT NULL,
  `RJCT_CD` varchar(16) DEFAULT NULL,
  `SWTCHG_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`JNL_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for tb_tx_p100dat
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_p100dat`;
CREATE TABLE `tb_tx_p100dat` (
  `JNL_NO` varchar(35) NOT NULL,
  `CATEGORY_PURPOSE` varchar(6) DEFAULT NULL,
  `ACCT_VERF` varchar(15) DEFAULT NULL,
  `SETTLEMENT_CUR` varchar(3) DEFAULT NULL,
  `SETTLEMENT_AMT` varchar(16) DEFAULT NULL,
  `SETTLEMENT_DATE` date DEFAULT NULL,
  `INSTRUCTED_CUR` varchar(3) DEFAULT NULL,
  `INSTRUCTED_AMT` varchar(16) DEFAULT NULL,
  `CHG_AGT_ID` varchar(3) DEFAULT NULL,
  `CHG_AGT_BIC` varchar(11) DEFAULT NULL,
  `CHG_CUR` varchar(3) DEFAULT NULL,
  `CHG_AMT` varchar(16) DEFAULT NULL,
  `DEBTOR_NAME` varchar(420) DEFAULT NULL,
  `DEBTOR_ORGID_ANYBIC` varchar(11) DEFAULT NULL,
  `DEBTOR_ORGID_OTHRID` varchar(35) DEFAULT NULL,
  `DEBTOR_ORGID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `DEBTOR_ORGID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `DEBTOR_PRVTID_OTHRID` varchar(35) DEFAULT NULL,
  `DEBTOR_PRVTID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `DEBTOR_PRVTID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `DEBTOR_CONT_PHONE` varchar(35) DEFAULT NULL,
  `DEBTOR_CONT_EMADDR` varchar(254) DEFAULT NULL,
  `DEBTOR_ACCTNO` varchar(34) DEFAULT NULL,
  `DEBTOR_ACCTNO_TYPE` varchar(4) DEFAULT NULL,
  `DEBTOR_AGT_ID` varchar(3) DEFAULT NULL,
  `DEBTOR_AGT_BIC` varchar(11) DEFAULT NULL,
  `CREDITOR_NAME` varchar(420) DEFAULT NULL,
  `CREDITOR_ORGID_ANYBIC` varchar(11) DEFAULT NULL,
  `CREDITOR_ORGID_OTHRID` varchar(35) DEFAULT NULL,
  `CREDITOR_ORGID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `CREDITOR_ORGID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `CREDITOR_PRVTID_OTHRID` varchar(35) DEFAULT NULL,
  `CREDITOR_PRVTID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `CREDITOR_PRVTID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `CREDITOR_CONT_PHONE` varchar(35) DEFAULT NULL,
  `CREDITOR_CONT_EMADDR` varchar(254) DEFAULT NULL,
  `CREDITOR_ACCTNO` varchar(34) DEFAULT NULL,
  `CREDITOR_ACCTNO_TYPE` varchar(4) DEFAULT NULL,
  `CREDITOR_AGT_ID` varchar(3) DEFAULT NULL,
  `CREDITOR_AGT_BIC` varchar(11) DEFAULT NULL,
  `PURPOSE_TYPE` varchar(5) DEFAULT NULL,
  `PURPOSE_CODE` varchar(4) DEFAULT NULL,
  `PURPOSE_OTHER` varchar(140) DEFAULT NULL,
  `REMIT_INFO` varchar(140) DEFAULT NULL,
  `SRVC_MODE` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`JNL_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for tb_tx_p110dat
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_p110dat`;
CREATE TABLE `tb_tx_p110dat` (
  `JNL_NO` varchar(35) NOT NULL,
  `CATEGORY_PURPOSE` varchar(6) DEFAULT NULL,
  `ACCT_VERF` varchar(15) DEFAULT NULL,
  `SETTLEMENT_CUR` varchar(3) DEFAULT NULL,
  `SETTLEMENT_AMT` varchar(16) DEFAULT NULL,
  `SETTLEMENT_DATE` date DEFAULT NULL,
  `INSTRUCTED_CUR` varchar(3) DEFAULT NULL,
  `INSTRUCTED_AMT` varchar(16) DEFAULT NULL,
  `CHG_AGT_ID` varchar(3) DEFAULT NULL,
  `CHG_AGT_BIC` varchar(11) DEFAULT NULL,
  `CHG_CUR` varchar(3) DEFAULT NULL,
  `CHG_AMT` varchar(16) DEFAULT NULL,
  `DEBTOR_NAME` varchar(420) DEFAULT NULL,
  `DEBTOR_ORGID_ANYBIC` varchar(11) DEFAULT NULL,
  `DEBTOR_ORGID_OTHRID` varchar(35) DEFAULT NULL,
  `DEBTOR_ORGID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `DEBTOR_ORGID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `DEBTOR_PRVTID_OTHRID` varchar(35) DEFAULT NULL,
  `DEBTOR_PRVTID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `DEBTOR_PRVTID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `DEBTOR_CONT_PHONE` varchar(35) DEFAULT NULL,
  `DEBTOR_CONT_EMADDR` varchar(254) DEFAULT NULL,
  `DEBTOR_ACCTNO` varchar(34) DEFAULT NULL,
  `DEBTOR_ACCTNO_TYPE` varchar(4) DEFAULT NULL,
  `DEBTOR_AGT_ID` varchar(3) DEFAULT NULL,
  `DEBTOR_AGT_BIC` varchar(11) DEFAULT NULL,
  `CREDITOR_NAME` varchar(420) DEFAULT NULL,
  `CREDITOR_ORGID_ANYBIC` varchar(11) DEFAULT NULL,
  `CREDITOR_ORGID_OTHRID` varchar(35) DEFAULT NULL,
  `CREDITOR_ORGID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `CREDITOR_ORGID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `CREDITOR_PRVTID_OTHRID` varchar(35) DEFAULT NULL,
  `CREDITOR_PRVTID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `CREDITOR_PRVTID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `CREDITOR_CONT_PHONE` varchar(35) DEFAULT NULL,
  `CREDITOR_CONT_EMADDR` varchar(254) DEFAULT NULL,
  `CREDITOR_ACCTNO` varchar(34) DEFAULT NULL,
  `CREDITOR_ACCTNO_TYPE` varchar(4) DEFAULT NULL,
  `CREDITOR_AGT_ID` varchar(3) DEFAULT NULL,
  `CREDITOR_AGT_BIC` varchar(11) DEFAULT NULL,
  `PURPOSE_TYPE` varchar(5) DEFAULT NULL,
  `PURPOSE_CODE` varchar(4) DEFAULT NULL,
  `PURPOSE_OTHER` varchar(140) DEFAULT NULL,
  `REMIT_INFO` varchar(140) DEFAULT NULL,
  `SRVC_MODE` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`JNL_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for tb_tx_p200dat
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_p200dat`;
CREATE TABLE `tb_tx_p200dat` (
  `JNL_NO` varchar(35) NOT NULL,
  `CATEGORY_PURPOSE` varchar(6) DEFAULT NULL,
  `SETTLEMENT_CUR` varchar(3) DEFAULT NULL,
  `SETTLEMENT_AMT` varchar(16) DEFAULT NULL,
  `SETTLEMENT_DATE` date DEFAULT NULL,
  `INSTRUCTED_CUR` varchar(3) DEFAULT NULL,
  `INSTRUCTED_AMT` varchar(16) DEFAULT NULL,
  `CHG_AGT_ID` varchar(3) DEFAULT NULL,
  `CHG_AGT_BIC` varchar(11) DEFAULT NULL,
  `CHG_CUR` varchar(3) DEFAULT NULL,
  `CHG_AMT` varchar(16) DEFAULT NULL,
  `DRCTDBT_MNDTID` varchar(35) DEFAULT NULL,
  `DEBTOR_NAME` varchar(420) DEFAULT NULL,
  `DEBTOR_ORGID_ANYBIC` varchar(11) DEFAULT NULL,
  `DEBTOR_ORGID_OTHRID` varchar(35) DEFAULT NULL,
  `DEBTOR_ORGID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `DEBTOR_ORGID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `DEBTOR_PRVTID_OTHRID` varchar(35) DEFAULT NULL,
  `DEBTOR_PRVTID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `DEBTOR_PRVTID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `DEBTOR_CONT_PHONE` varchar(35) DEFAULT NULL,
  `DEBTOR_CONT_EMADDR` varchar(254) DEFAULT NULL,
  `DEBTOR_ACCTNO` varchar(34) DEFAULT NULL,
  `DEBTOR_ACCTNO_TYPE` varchar(4) DEFAULT NULL,
  `DEBTOR_AGT_ID` varchar(3) DEFAULT NULL,
  `DEBTOR_AGT_BIC` varchar(11) DEFAULT NULL,
  `CREDITOR_NAME` varchar(420) DEFAULT NULL,
  `CREDITOR_ORGID_ANYBIC` varchar(11) DEFAULT NULL,
  `CREDITOR_ORGID_OTHRID` varchar(35) DEFAULT NULL,
  `CREDITOR_ORGID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `CREDITOR_ORGID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `CREDITOR_PRVTID_OTHRID` varchar(35) DEFAULT NULL,
  `CREDITOR_PRVTID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `CREDITOR_PRVTID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `CREDITOR_CONT_PHONE` varchar(35) DEFAULT NULL,
  `CREDITOR_CONT_EMADDR` varchar(254) DEFAULT NULL,
  `CREDITOR_ACCTNO` varchar(34) DEFAULT NULL,
  `CREDITOR_ACCTNO_TYPE` varchar(4) DEFAULT NULL,
  `CREDITOR_AGT_ID` varchar(3) DEFAULT NULL,
  `CREDITOR_AGT_BIC` varchar(11) DEFAULT NULL,
  `PURPOSE_TYPE` varchar(5) DEFAULT NULL,
  `PURPOSE_CODE` varchar(4) DEFAULT NULL,
  `PURPOSE_OTHER` varchar(140) DEFAULT NULL,
  `REMIT_INFO` varchar(140) DEFAULT NULL,
  `SRVC_MODE` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`JNL_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





-- ----------------------------
-- Table structure for tb_tx_p210dat
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_p210dat`;
CREATE TABLE `tb_tx_p210dat` (
  `JNL_NO` varchar(35) NOT NULL,
  `CATEGORY_PURPOSE` varchar(6) DEFAULT NULL,
  `SETTLEMENT_CUR` varchar(3) DEFAULT NULL,
  `SETTLEMENT_AMT` varchar(16) DEFAULT NULL,
  `SETTLEMENT_DATE` date DEFAULT NULL,
  `INSTRUCTED_CUR` varchar(3) DEFAULT NULL,
  `INSTRUCTED_AMT` varchar(16) DEFAULT NULL,
  `CHG_AGT_ID` varchar(3) DEFAULT NULL,
  `CHG_AGT_BIC` varchar(11) DEFAULT NULL,
  `CHG_CUR` varchar(3) DEFAULT NULL,
  `CHG_AMT` varchar(16) DEFAULT NULL,
  `DRCTDBT_MNDTID` varchar(35) DEFAULT NULL,
  `DEBTOR_NAME` varchar(420) DEFAULT NULL,
  `DEBTOR_ORGID_ANYBIC` varchar(11) DEFAULT NULL,
  `DEBTOR_ORGID_OTHRID` varchar(35) DEFAULT NULL,
  `DEBTOR_ORGID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `DEBTOR_ORGID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `DEBTOR_PRVTID_OTHRID` varchar(35) DEFAULT NULL,
  `DEBTOR_PRVTID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `DEBTOR_PRVTID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `DEBTOR_CONT_PHONE` varchar(35) DEFAULT NULL,
  `DEBTOR_CONT_EMADDR` varchar(254) DEFAULT NULL,
  `DEBTOR_ACCTNO` varchar(34) DEFAULT NULL,
  `DEBTOR_ACCTNO_TYPE` varchar(4) DEFAULT NULL,
  `DEBTOR_AGT_ID` varchar(3) DEFAULT NULL,
  `DEBTOR_AGT_BIC` varchar(11) DEFAULT NULL,
  `CREDITOR_NAME` varchar(420) DEFAULT NULL,
  `CREDITOR_ORGID_ANYBIC` varchar(11) DEFAULT NULL,
  `CREDITOR_ORGID_OTHRID` varchar(35) DEFAULT NULL,
  `CREDITOR_ORGID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `CREDITOR_ORGID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `CREDITOR_PRVTID_OTHRID` varchar(35) DEFAULT NULL,
  `CREDITOR_PRVTID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `CREDITOR_PRVTID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `CREDITOR_CONT_PHONE` varchar(35) DEFAULT NULL,
  `CREDITOR_CONT_EMADDR` varchar(254) DEFAULT NULL,
  `CREDITOR_ACCTNO` varchar(34) DEFAULT NULL,
  `CREDITOR_ACCTNO_TYPE` varchar(4) DEFAULT NULL,
  `CREDITOR_AGT_ID` varchar(3) DEFAULT NULL,
  `CREDITOR_AGT_BIC` varchar(11) DEFAULT NULL,
  `PURPOSE_TYPE` varchar(5) DEFAULT NULL,
  `PURPOSE_CODE` varchar(4) DEFAULT NULL,
  `PURPOSE_OTHER` varchar(140) DEFAULT NULL,
  `REMIT_INFO` varchar(140) DEFAULT NULL,
  `SRVC_MODE` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`JNL_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_tx_p300dat
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_p300dat`;
CREATE TABLE `tb_tx_p300dat` (
  `JNL_NO` varchar(35) NOT NULL,
  `RETURN_ID` varchar(35) DEFAULT NULL,
  `ORGNL_INSTR_ID` varchar(35) DEFAULT NULL,
  `ORGNL_ENDTOEND_ID` varchar(35) DEFAULT NULL,
  `ORGNL_TX_ID` varchar(35) DEFAULT NULL,
  `ORGNL_CLRSYS_REF` varchar(35) DEFAULT NULL,
  `RETURN_SETTLEMENT_CUR` varchar(3) DEFAULT NULL,
  `RETURN_SETTLEMENT_AMT` varchar(16) DEFAULT NULL,
  `RETURN_SETTLEMENT_DATE` date DEFAULT NULL,
  `RETURN_INSTRUCTED_CUR` varchar(3) DEFAULT NULL,
  `RETURN_INSTRUCTED_AMT` varchar(16) DEFAULT NULL,
  `RETURN_CHRGBR` varchar(4) DEFAULT NULL,
  `RETURN_CHG_CUR` varchar(3) DEFAULT NULL,
  `RETURN_CHG_AMT` varchar(16) DEFAULT NULL,
  `RETURN_CHG_AGT_ID` varchar(3) DEFAULT NULL,
  `RETURN_CHG_AGT_BIC` varchar(11) DEFAULT NULL,
  `RETURN_CODE` varchar(8) DEFAULT NULL,
  `RETURN_REASON` varchar(500) DEFAULT NULL,
  `ORIG_SETTLEMENT_AMT` varchar(16) DEFAULT NULL,
  `ORIG_SETTLEMENT_CUR` varchar(3) DEFAULT NULL,
  `ORIG_SETTLEMENT_DATE` date DEFAULT NULL,
  `ORIG_CATEGORY_PURPOSE` varchar(6) DEFAULT NULL,
  `ORIG_MANDATE_INFO` varchar(35) DEFAULT NULL,
  `ORIG_REM_INFO` varchar(140) DEFAULT NULL,
  `ORIG_DEBTOR_NAME` varchar(420) DEFAULT NULL,
  `ORIG_DEBTOR_ORGID_ANYBIC` varchar(11) DEFAULT NULL,
  `ORIG_DEBTOR_ORGID_OTHRID` varchar(35) DEFAULT NULL,
  `ORIG_DEBTOR_ORGID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `ORIG_DEBTOR_ORGID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `ORIG_DEBTOR_PRVTID_OTHRID` varchar(35) DEFAULT NULL,
  `ORIG_DEBTOR_PRVTID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `ORIG_DEBTOR_PRVTID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `ORIG_DEBTOR_PHNO` varchar(35) DEFAULT NULL,
  `ORIG_DEBTOR_EMADDR` varchar(254) DEFAULT NULL,
  `ORIG_DEBTOR_ACCTNO` varchar(34) DEFAULT NULL,
  `ORIG_DEBTOR_ACCTNO_TYPE` varchar(4) DEFAULT NULL,
  `ORIG_DEBTOR_AGT_ID` varchar(3) DEFAULT NULL,
  `ORIG_DEBTOR_AGT_BIC` varchar(11) DEFAULT NULL,
  `ORIG_CREDITOR_NAME` varchar(420) DEFAULT NULL,
  `ORIG_CREDITOR_ORGID_ANYBIC` varchar(11) DEFAULT NULL,
  `ORIG_CREDITOR_ORGID_OTHRID` varchar(35) DEFAULT NULL,
  `ORIG_CREDITOR_ORGID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `ORIG_CREDITOR_ORGID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `ORIG_CREDITOR_PRVTID_OTHRID` varchar(35) DEFAULT NULL,
  `ORIG_CREDITOR_PRVTID_OTHRID_SCHMENM` varchar(4) DEFAULT NULL,
  `ORIG_CREDITOR_PRVTID_OTHR_ISSR` varchar(35) DEFAULT NULL,
  `ORIG_CREDITOR_PHNO` varchar(35) DEFAULT NULL,
  `ORIG_CREDITOR_EMADDR` varchar(254) DEFAULT NULL,
  `ORIG_CREDITOR_ACCTNO` varchar(34) DEFAULT NULL,
  `ORIG_CREDITOR_ACCTNO_TYPE` varchar(4) DEFAULT NULL,
  `ORIG_CREDITOR_AGT_ID` varchar(3) DEFAULT NULL,
  `ORIG_CREDITOR_AGT_BIC` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`JNL_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_tx_res_fpspddr_temp
-- ----------------------------
DROP TABLE IF EXISTS `tb_tx_res_fpspddr_temp`;
CREATE TABLE `tb_tx_res_fpspddr_temp` (
  `FID` varchar(255) NOT NULL,
  `FILE_NAME` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `FROM` varchar(255) DEFAULT NULL,
  `TO` varchar(255) DEFAULT NULL,
  `BIZ_MSG_IDR` varchar(255) DEFAULT NULL,
  `MSG_DEF_IDR` varchar(255) DEFAULT NULL,
  `BIZ_SVC` varchar(255) DEFAULT NULL,
  `CRE_DT` varchar(255) DEFAULT NULL,
  `MSG_ID` varchar(255) DEFAULT NULL,
  `CRE_DT_TM` varchar(255) DEFAULT NULL,
  `ORGNL_MSG_ID` varchar(255) DEFAULT NULL,
  `ORGNL_MSG_NM_ID` varchar(255) DEFAULT NULL,
  `ORGNL_END_TO_END_ID` varchar(255) DEFAULT NULL,
  `ORGNL_TX_ID` varchar(255) DEFAULT NULL,
  `TXSTS` varchar(255) DEFAULT NULL,
  `RSN` varchar(255) DEFAULT NULL,
  `ADDITIONAL_INF` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for bf_holiday(begin by ourself)
-- ----------------------------
DROP TABLE IF EXISTS `bf_holiday`;
CREATE TABLE `bf_holiday` (
  `SOLAR_DATE` char(8) NOT NULL,
  `IS_HOLIDAY_DAY` char(1) DEFAULT NULL,
  `HOLIDAY_DESC` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SOLAR_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for tb_dt_ecert
-- ----------------------------
DROP TABLE IF EXISTS `tb_dt_ecert`;
CREATE TABLE `tb_dt_ecert` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SYSTEM_ID` varchar(11) NOT NULL,
  `ECERT_KEY` varchar(5000) NOT NULL,
  `EFFECTIVE_DATE` timestamp NOT NULL ,
  `EXPIRY_DATE` timestamp NOT NULL ,
  `REVOKED_TS` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Function structure for get_fpspcro_trans_num
-- ----------------------------
DROP FUNCTION IF EXISTS `get_fpspcro_trans_num`;
DELIMITER ;;
CREATE DEFINER=`zt`@`%` FUNCTION `get_fpspcro_trans_num`() RETURNS varchar(26) CHARSET utf8
BEGIN
	DECLARE getval VARCHAR(26);
	SET getval = (SELECT LPAD((SELECT ffp.next_trans_num('FPSPCRO')), 8, '0'));
	RETURN getval;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for next_trans_num
-- ----------------------------
DROP FUNCTION IF EXISTS `next_trans_num`;
DELIMITER ;;
CREATE DEFINER=`zt`@`%` FUNCTION `next_trans_num`(`seqname` VARCHAR(30)) RETURNS varchar(32) CHARSET utf8
BEGIN
	UPDATE tb_ss_seqnum SET CURRENT_VAL = LAST_INSERT_ID(CURRENT_VAL + INCREMENT_VAL) WHERE SEQ_NAME = seqname;
	RETURN LAST_INSERT_ID();
END
;;
DELIMITER ;