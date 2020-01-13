-- 从Oracle导出脚本文件转换为H2脚本文件的步骤
-- 1.替换所有prompt 为 -- prompt
-- 2.替换所有set 为 -- set
-- 3.替换所有VARCHAR2 为VARCHAR
-- 4.替换所有cascade constraints;为 -- cascade constraints;
-- 5.单独处理Oracle表结构中含CLOB字段的表结构
-- 6.第一次操作，需要替换drop table语句为 -- drop table

-- prompt PL/SQL Developer import file
-- prompt Created on 2016年12月30日 by lenovo
-- set feedback off
-- set define off
-- prompt Dropping BF_ATTACH...
DROP TABLE IF EXISTS BF_DAY_INFO;
create table BF_DAY_INFO
(
  solar_date     CHAR(8) not null,
  lunar_year     CHAR(4),
  lunar_month    CHAR(2),
  lunar_day      CHAR(2),
  lunar_desc     VARCHAR(20),
  week           CHAR(6),
  constellation  CHAR(6),
  month_begin    CHAR(8),
  month_end      CHAR(8),
  year_days      INTEGER,
  month_days     INTEGER,
  is_holiday_day CHAR(1),
  holiday_desc   VARCHAR(50),
  is_month_end   CHAR(1)
)
;
comment on table BF_DAY_INFO
  is '平台表 - 日历信息表';
comment on column BF_DAY_INFO.solar_date
  is '阳历日期';
comment on column BF_DAY_INFO.lunar_year
  is '农历年';
comment on column BF_DAY_INFO.lunar_month
  is '农历月（闰月为12+闰月数）';
comment on column BF_DAY_INFO.lunar_day
  is '农历日';
comment on column BF_DAY_INFO.lunar_desc
  is '农历日期描述';
comment on column BF_DAY_INFO.week
  is '星期';
comment on column BF_DAY_INFO.constellation
  is '星座';
comment on column BF_DAY_INFO.month_begin
  is '月初日期';
comment on column BF_DAY_INFO.month_end
  is '月末日期';
comment on column BF_DAY_INFO.year_days
  is '当年第几天';
comment on column BF_DAY_INFO.month_days
  is '当月第几天';
comment on column BF_DAY_INFO.is_holiday_day
  is '是否节假日';
comment on column BF_DAY_INFO.holiday_desc
  is '节假日描述';
comment on column BF_DAY_INFO.is_month_end
  is '是否月末';
alter table BF_DAY_INFO
  add constraint PK_BF_DAY_INFO primary key (SOLAR_DATE);

DROP TABLE IF EXISTS BF_JOB_CONTROLLER;
create table BF_JOB_CONTROLLER
(
  job_key                VARCHAR(120) not null,
  sched_name             VARCHAR(120) not null,
  trigger_name           VARCHAR(200) not null,
  trigger_group          VARCHAR(200) not null,
  job_name               VARCHAR(200) not null,
  job_group              VARCHAR(200) not null,
  begin_fire_time        VARCHAR(32),
  end_fire_time          VARCHAR(32),
  duration_time          NUMBER,
  real_duration_time     NUMBER,
  cal_duration_time      NUMBER,
  next_fire_time         VARCHAR(32),
  cron_express           VARCHAR(120),
  description            VARCHAR(300),
  job_level              VARCHAR(10),
  job_class_name         VARCHAR(250),
  server_ip              VARCHAR(32),
  enable_history         VARCHAR(10),
  enable_timeout_watcher VARCHAR(10),
  running_status         VARCHAR(10) not null,
  op_date                VARCHAR(8) not null,
  op_time                VARCHAR(6) not null
)
;
alter table BF_JOB_CONTROLLER
  add constraint PK_JOB_CONTROLLER primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP, JOB_NAME, JOB_GROUP);

DROP TABLE IF EXISTS BF_JOB_EXECUTE_LOG;
create table BF_JOB_EXECUTE_LOG
(
  flow_id            VARCHAR(60) not null,
  job_key            VARCHAR(120) not null,
  sched_name         VARCHAR(120) not null,
  trigger_name       VARCHAR(200) not null,
  trigger_group      VARCHAR(200) not null,
  job_name           VARCHAR(200) not null,
  job_group          VARCHAR(200) not null,
  job_class_name     VARCHAR(250),
  server_ip          VARCHAR(32),
  begin_fire_time    VARCHAR(32),
  end_fire_time      VARCHAR(32),
  real_duration_time NUMBER,
  cal_duration_time  NUMBER,
  cron_express       VARCHAR(120),
  description        VARCHAR(300),
  job_level          VARCHAR(10),
  running_status     VARCHAR(10) not null,
  op_date            VARCHAR(8) not null,
  op_time            VARCHAR(6) not null
)
;
alter table BF_JOB_EXECUTE_LOG
  add constraint PK_JOB_EXECUTE_LOG primary key (FLOW_ID);

DROP TABLE IF EXISTS BF_KEY_CFG;
create table BF_KEY_CFG
(
  module          VARCHAR(200) not null,
  public_empoent  VARCHAR(200) not null,
  private_empoent VARCHAR(200) not null
)
;
comment on table BF_KEY_CFG
  is '平台表 - 加解密密钥表（登录功能用到）';
comment on column BF_KEY_CFG.module
  is '模';
comment on column BF_KEY_CFG.public_empoent
  is '公钥';
comment on column BF_KEY_CFG.private_empoent
  is '私钥';

DROP TABLE IF EXISTS BF_LOG_LOGIN;
create table BF_LOG_LOGIN
(
  key_id     VARCHAR(32) not null,
  session_id VARCHAR(64) not null,
  user_id    VARCHAR(20) not null,
  client_ip  VARCHAR(32),
  server_ip  VARCHAR(32),
  browser    VARCHAR(32),
  os         VARCHAR(32),
  lio_flag   VARCHAR(32) not null,
  opt_date   CHAR(8) not null,
  opt_time   CHAR(6) not null
)
;
comment on table BF_LOG_LOGIN
  is '平台表 - 登录日志表';
comment on column BF_LOG_LOGIN.key_id
  is 'KEY主键 由系统自动产生不重复的值做为主键';
comment on column BF_LOG_LOGIN.session_id
  is '会话ID';
comment on column BF_LOG_LOGIN.user_id
  is '用户ID';
comment on column BF_LOG_LOGIN.client_ip
  is '客户端IP';
comment on column BF_LOG_LOGIN.server_ip
  is '服务端IP';
comment on column BF_LOG_LOGIN.browser
  is '浏览器';
comment on column BF_LOG_LOGIN.os
  is '操作系统';
comment on column BF_LOG_LOGIN.lio_flag
  is '标志 1：登入 2：登出   3：系统自动注销';
comment on column BF_LOG_LOGIN.opt_date
  is '操作 日期';
comment on column BF_LOG_LOGIN.opt_time
  is '操作时间';
alter table BF_LOG_LOGIN
  add constraint PK_BF_LOG_LOGIN_KEY_ID primary key (KEY_ID);

DROP TABLE IF EXISTS BF_LOG_VISIT;
create table BF_LOG_VISIT
(
  key_id     VARCHAR(32) not null,
  request_id VARCHAR(64),
  session_id VARCHAR(64),
  user_id    VARCHAR(20),
  perm_id    INTEGER,
  client_ip  VARCHAR(32),
  server_ip  VARCHAR(32),
  browser    VARCHAR(32),
  os         VARCHAR(32),
  opt_flag   VARCHAR(32),
  opt_path   VARCHAR(200),
  opt_url    VARCHAR(200),
  opt_params VARCHAR(500),
  opt_date   CHAR(8),
  opt_time   CHAR(6),
  cost_time  INTEGER
)
;
comment on table BF_LOG_VISIT
  is '平台表 - 访问日志表';
comment on column BF_LOG_VISIT.key_id
  is 'KEY主键 由系统自动产生不重复的值做为主键';
comment on column BF_LOG_VISIT.request_id
  is '请求ID';
comment on column BF_LOG_VISIT.session_id
  is '会话ID';
comment on column BF_LOG_VISIT.user_id
  is '用户ID';
comment on column BF_LOG_VISIT.perm_id
  is '权限ID';
comment on column BF_LOG_VISIT.client_ip
  is '客户端IP';
comment on column BF_LOG_VISIT.server_ip
  is '服务端IP';
comment on column BF_LOG_VISIT.browser
  is '浏览器';
comment on column BF_LOG_VISIT.os
  is '操作系统';
comment on column BF_LOG_VISIT.opt_flag
  is '操作标志  0 拒绝访问  1 通过授权';
comment on column BF_LOG_VISIT.opt_path
  is '访问路径';
comment on column BF_LOG_VISIT.opt_url
  is '访问URL';
comment on column BF_LOG_VISIT.opt_params
  is '访问参数，只记录前200个字符';
comment on column BF_LOG_VISIT.opt_date
  is '操作 日期';
comment on column BF_LOG_VISIT.opt_time
  is '操作时间';
comment on column BF_LOG_VISIT.cost_time
  is '耗费时间';
alter table BF_LOG_VISIT
  add primary key (KEY_ID);

DROP TABLE IF EXISTS BF_MEMO_DEF;
create table BF_MEMO_DEF
(
  id           VARCHAR(32) not null,
  user_id      VARCHAR(20) not null,
  memo_title   VARCHAR(100) not null,
  memo_content VARCHAR(600),
  memo_date    VARCHAR(8) not null,
  is_remind    INTEGER default 0 not null,
  memo_time    VARCHAR(6)
)
;
comment on table BF_MEMO_DEF
  is '平台表 - 系统备忘表';
comment on column BF_MEMO_DEF.id
  is '唯一主键';
comment on column BF_MEMO_DEF.user_id
  is '用户ID';
comment on column BF_MEMO_DEF.memo_title
  is '标题';
comment on column BF_MEMO_DEF.memo_content
  is '正文';
comment on column BF_MEMO_DEF.memo_date
  is '备忘开始日期';
comment on column BF_MEMO_DEF.is_remind
  is '是否需要提醒 默认 0：不需要提醒 1：需要提醒';
comment on column BF_MEMO_DEF.memo_time
  is '初始化时间';
alter table BF_MEMO_DEF
  add constraint PRIMARY_ID primary key (ID);

DROP TABLE IF EXISTS BF_MEMO_LOG;
create table BF_MEMO_LOG
(
  id           VARCHAR(32) not null,
  user_id      VARCHAR(20) not null,
  memo_title   VARCHAR(100) not null,
  memo_content VARCHAR(600),
  memo_date    VARCHAR(8),
  memo_time    VARCHAR(6) not null,
  memo_id      VARCHAR(32) not null,
  op_date      VARCHAR(8) not null,
  op_time      VARCHAR(6) not null
)
;
comment on table BF_MEMO_LOG
  is '平台表 -  系统备忘日志表';
comment on column BF_MEMO_LOG.id
  is '主键号';
comment on column BF_MEMO_LOG.user_id
  is '用户ID';
comment on column BF_MEMO_LOG.memo_title
  is '标题';
comment on column BF_MEMO_LOG.memo_content
  is '正文';
comment on column BF_MEMO_LOG.memo_date
  is '备忘开始日期';
comment on column BF_MEMO_LOG.memo_time
  is '备忘开始时间';
comment on column BF_MEMO_LOG.memo_id
  is '备忘ID';
comment on column BF_MEMO_LOG.op_date
  is '操作日期';
comment on column BF_MEMO_LOG.op_time
  is '操作时间';
alter table BF_MEMO_LOG
  add constraint PRIMARY_LOG_ID primary key (ID);

DROP TABLE IF EXISTS BF_MENU;
create table BF_MENU
(
  menu_id        INTEGER not null,
  parent_menu_id INTEGER,
  menu_name      VARCHAR(64) not null,
  menu_url       VARCHAR(200),
  display_area   VARCHAR(32),
  display_icon   VARCHAR(32),
  depend_menu_id INTEGER,
  author_level   VARCHAR(32),
  menu_flag      VARCHAR(32),
  perm_tree_flag VARCHAR(32),
  target_page    VARCHAR(32),
  seqno          INTEGER,
  des            VARCHAR(200),
  inst_date      CHAR(8),
  inst_time      CHAR(6),
  inst_oper      CHAR(20),
  modi_date      CHAR(8),
  modi_time      CHAR(6),
  modi_oper      CHAR(20)
)
;
comment on table BF_MENU
  is '平台表 - 菜单表
其中一个权限子表（所有一级菜单显示在头部，根据一级菜单的显示属性，决定所有子孙节点是显示在头部，还是在左边）';
comment on column BF_MENU.menu_id
  is '菜单ID';
comment on column BF_MENU.parent_menu_id
  is '父菜单ID';
comment on column BF_MENU.menu_name
  is '菜单名称';
comment on column BF_MENU.menu_url
  is '菜单URL';
comment on column BF_MENU.display_area
  is '显示区域 如果是菜单时，默认一级菜单在头部显示，子级菜单的显示由父级菜单的显示位置来决定。0：头部显示  1：侧部菜单';
comment on column BF_MENU.display_icon
  is '样式名称';
comment on column BF_MENU.depend_menu_id
  is '菜单依赖 只能依赖一层';
comment on column BF_MENU.author_level
  is '授权级别 在登录时，0与1先检查，2需要进权限控制进行检查
0：无需登录即可访问
1：只要有效用户登录即可
2：需要授权方可访问';
comment on column BF_MENU.menu_flag
  is '菜单类型 0：菜单  1：功能项';
comment on column BF_MENU.perm_tree_flag
  is '是否显示在权限树中 如果是菜单标志则操作标志一定为1  如果显示0，依赖菜单不能为空。 0--不在功能树中显示  1--在功能树中显示';
comment on column BF_MENU.target_page
  is '目标页面 为空时，在选项卡中打开，否则打开新的页面';
comment on column BF_MENU.seqno
  is '排序';
comment on column BF_MENU.des
  is '描述';
comment on column BF_MENU.inst_date
  is '新增日期';
comment on column BF_MENU.inst_time
  is '新增时间';
comment on column BF_MENU.inst_oper
  is '新增操作人';
comment on column BF_MENU.modi_date
  is '修改日期';
comment on column BF_MENU.modi_time
  is '修改时间';
comment on column BF_MENU.modi_oper
  is '修改操作人';
alter table BF_MENU
  add constraint PK_BF_AUTHOR_ITEM_AUTHOR_NO primary key (MENU_ID);

DROP TABLE IF EXISTS BF_MENU_I18N;
create table BF_MENU_I18N
(
  menu_id   INTEGER not null,
  locale    VARCHAR(64) not null,
  menu_name VARCHAR(64) not null,
  des       VARCHAR(200)
)
;
comment on table BF_MENU_I18N
  is '平台表——菜单国际化表';
comment on column BF_MENU_I18N.menu_id
  is '菜单ID';
comment on column BF_MENU_I18N.locale
  is '语言地区';
comment on column BF_MENU_I18N.menu_name
  is '菜单名称';
comment on column BF_MENU_I18N.des
  is '描述';
alter table BF_MENU_I18N
  add constraint PK_BF_MENU_I18N primary key (MENU_ID, LOCALE);

DROP TABLE IF EXISTS BF_NEWS;
create table BF_NEWS
(
  msg_id               VARCHAR(32) not null,
  msg_title            VARCHAR(100) not null,
  msg_content          VARCHAR(500) not null,
  target_url           VARCHAR(200),
  msg_type             CHAR(1),
  msg_level            CHAR(1),
  send_oper            VARCHAR(20) not null,
  send_date            CHAR(8) not null,
  send_time            CHAR(6) not null,
  recv_net             VARCHAR(7),
  recv_oper            VARCHAR(20),
  status               CHAR(1) default '0',
  msg_icon             VARCHAR(20),
  effective_flag       CHAR(1),
  effective_day_cnt    INTEGER,
  effective_start_date CHAR(8),
  effective_start_time CHAR(8),
  effective_end_date   CHAR(8),
  effective_end_time   CHAR(8)
)
;
comment on table BF_NEWS
  is '平台表 -  消息表';
comment on column BF_NEWS.msg_id
  is '消息ID';
comment on column BF_NEWS.msg_title
  is '消息标题';
comment on column BF_NEWS.msg_content
  is '消息内容';
comment on column BF_NEWS.target_url
  is '目标URL';
comment on column BF_NEWS.msg_type
  is '1-公告所有的人都可以看到
2-通知，登录后可以查看 3-信函，指定人/机构/角色可查看';
comment on column BF_NEWS.msg_level
  is '1-高
2-中
3-低';
comment on column BF_NEWS.send_oper
  is '消息发送人';
comment on column BF_NEWS.send_date
  is '消息发送日期';
comment on column BF_NEWS.send_time
  is '消息发送时间';
comment on column BF_NEWS.recv_net
  is '消息接收机构';
comment on column BF_NEWS.recv_oper
  is '消息接收用户';
comment on column BF_NEWS.status
  is '0-未阅读
1-已阅读';
comment on column BF_NEWS.msg_icon
  is '消息图标';
comment on column BF_NEWS.effective_flag
  is '生效标志  1：永久生效；2：天数生效；3：区间生效';
comment on column BF_NEWS.effective_day_cnt
  is '生效天数';
comment on column BF_NEWS.effective_start_date
  is '生效起始日期';
comment on column BF_NEWS.effective_start_time
  is '生效起始时间';
comment on column BF_NEWS.effective_end_date
  is '生效结束日期';
comment on column BF_NEWS.effective_end_time
  is '生效结束时间';
alter table BF_NEWS
  add constraint PK_BF_NEWS primary key (MSG_ID);

DROP TABLE IF EXISTS BF_NEWS_LOG;
create table BF_NEWS_LOG
(
  user_id   VARCHAR(20) not null,
  msg_id    VARCHAR(32) not null,
  oper_date CHAR(8) not null,
  oper_time CHAR(6) not null
)
;
comment on table BF_NEWS_LOG
  is '平台表 - 消息表已读消息记录表';
comment on column BF_NEWS_LOG.user_id
  is '用户号';
comment on column BF_NEWS_LOG.msg_id
  is '消息ID';
comment on column BF_NEWS_LOG.oper_date
  is '阅读日期';
comment on column BF_NEWS_LOG.oper_time
  is '阅读时间';

DROP TABLE IF EXISTS BF_ONLINE_USER;
create table BF_ONLINE_USER
(
  session_id VARCHAR(64) not null,
  user_id    VARCHAR(20) not null,
  client_ip  VARCHAR(32),
  server_ip  VARCHAR(32),
  browser    VARCHAR(32),
  os         VARCHAR(32),
  login_date CHAR(8) not null,
  login_time CHAR(6) not null
)
;
comment on table BF_ONLINE_USER
  is '平台表 - 在线用户表';
comment on column BF_ONLINE_USER.session_id
  is '会话ID';
comment on column BF_ONLINE_USER.user_id
  is '用户ID';
comment on column BF_ONLINE_USER.client_ip
  is '客户端IP地址';
comment on column BF_ONLINE_USER.server_ip
  is '服务端IP地址';
comment on column BF_ONLINE_USER.browser
  is '浏览器';
comment on column BF_ONLINE_USER.os
  is '操作系统';
comment on column BF_ONLINE_USER.login_date
  is '登录日期';
comment on column BF_ONLINE_USER.login_time
  is '登录时间';
alter table BF_ONLINE_USER
  add primary key (SESSION_ID);

DROP TABLE IF EXISTS BF_ORG;
create table BF_ORG
(
  org_id     VARCHAR(32) not null,
  org_name   VARCHAR(100) not null,
  sup_org_id VARCHAR(32),
  org_level  INTEGER,
  org_type   VARCHAR(32),
  des        VARCHAR(200),
  inst_date  CHAR(8),
  inst_time  CHAR(6),
  inst_oper  CHAR(20),
  modi_date  CHAR(8),
  modi_time  CHAR(6),
  modi_oper  CHAR(20)
)
;
comment on table BF_ORG
  is '平台表 - 机构';
comment on column BF_ORG.org_id
  is '机构号';
comment on column BF_ORG.org_name
  is '机构名称';
comment on column BF_ORG.sup_org_id
  is '上级机构号';
comment on column BF_ORG.org_level
  is '机构级别';
comment on column BF_ORG.org_type
  is '机构类型（预留）';
comment on column BF_ORG.des
  is '描述';
comment on column BF_ORG.inst_date
  is '新增日期';
comment on column BF_ORG.inst_time
  is '新增时间';
comment on column BF_ORG.inst_oper
  is '新增人';
comment on column BF_ORG.modi_date
  is '修改日期';
comment on column BF_ORG.modi_time
  is '修改时间';
comment on column BF_ORG.modi_oper
  is '修改人';
alter table BF_ORG
  add primary key (ORG_ID);

DROP TABLE IF EXISTS BF_PARAM_CFG;
create table BF_PARAM_CFG
(
  param_code  VARCHAR(32) not null,
  param_value VARCHAR(200) not null
)
;
comment on table BF_PARAM_CFG
  is '平台表 - 参数配置表';
comment on column BF_PARAM_CFG.param_code
  is '参数 代码';
comment on column BF_PARAM_CFG.param_value
  is '参数值';
alter table BF_PARAM_CFG
  add constraint PK_BF_PARAM_CFG primary key (PARAM_CODE);

DROP TABLE IF EXISTS BF_PARAM_DEF;
create table BF_PARAM_DEF
(
  param_code       VARCHAR(32) not null,
  param_name       VARCHAR(200) not null,
  store_type       VARCHAR(32) not null,
  param_group      VARCHAR(32) not null,
  data_type        VARCHAR(32) not null,
  editable         VARCHAR(32),
  default_value    VARCHAR(200),
  value_rule       VARCHAR(32),
  value_rule_param VARCHAR(32),
  seqno            INTEGER,
  des              VARCHAR(200)
)
;
comment on table BF_PARAM_DEF
  is '平台表 - 参数属性定义表';
comment on column BF_PARAM_DEF.param_code
  is '参数 代码';
comment on column BF_PARAM_DEF.param_name
  is '参数名称';
comment on column BF_PARAM_DEF.store_type
  is '存储方式
PLAINTEXT 明文
ENCRYPT 密文';
comment on column BF_PARAM_DEF.param_group
  is '所属参数组别';
comment on column BF_PARAM_DEF.data_type
  is '数据类型';
comment on column BF_PARAM_DEF.editable
  is '是否可编辑 0 不可编辑  1 可编辑';
comment on column BF_PARAM_DEF.default_value
  is '默认值';
comment on column BF_PARAM_DEF.value_rule
  is '取值规则
如正则表达式、枚举等';
comment on column BF_PARAM_DEF.value_rule_param
  is '取值规则参数
正则表达式的文本，枚举的类型等';
comment on column BF_PARAM_DEF.seqno
  is '序号';
comment on column BF_PARAM_DEF.des
  is '描述';
alter table BF_PARAM_DEF
  add constraint PK_BF_PARAM_DEF primary key (PARAM_CODE);

DROP TABLE IF EXISTS BF_PARAM_ENUM_DATA;
create table BF_PARAM_ENUM_DATA
(
  param_code VARCHAR(32) not null,
  data_code  VARCHAR(32) not null,
  data_text  VARCHAR(200) not null,
  data_param VARCHAR(200),
  seqno      INTEGER,
  des        VARCHAR(200)
)
;
comment on table BF_PARAM_ENUM_DATA
  is '平台表 - 枚举参数数据表';
comment on column BF_PARAM_ENUM_DATA.param_code
  is '参数 代码';
comment on column BF_PARAM_ENUM_DATA.data_code
  is '数据代码';
comment on column BF_PARAM_ENUM_DATA.data_text
  is '数据文本';
comment on column BF_PARAM_ENUM_DATA.data_param
  is '数据参数';
comment on column BF_PARAM_ENUM_DATA.seqno
  is '序号';
comment on column BF_PARAM_ENUM_DATA.des
  is '参数描述';
alter table BF_PARAM_ENUM_DATA
  add constraint PK_BF_PARAM_ENUM_DATA primary key (PARAM_CODE, DATA_CODE);

DROP TABLE IF EXISTS BF_PARAM_ENUM_DEF;
create table BF_PARAM_ENUM_DEF
(
  param_code  VARCHAR(32) not null,
  param_name  VARCHAR(200) not null,
  param_group VARCHAR(32) not null,
  param_attr  VARCHAR(32) not null,
  editable    VARCHAR(32),
  seqno       INTEGER,
  des         VARCHAR(200)
)
;
comment on table BF_PARAM_ENUM_DEF
  is '平台表 - 枚举参数属性定义表';
comment on column BF_PARAM_ENUM_DEF.param_code
  is '参数 代码';
comment on column BF_PARAM_ENUM_DEF.param_name
  is '参数名称';
comment on column BF_PARAM_ENUM_DEF.param_group
  is '所属参数组别';
comment on column BF_PARAM_ENUM_DEF.param_attr
  is '参数特性如LIST 列表枚举类型  TREE树型数据';
comment on column BF_PARAM_ENUM_DEF.editable
  is '是否可编辑 0 不可编辑  1 可编辑';
comment on column BF_PARAM_ENUM_DEF.seqno
  is '序号';
comment on column BF_PARAM_ENUM_DEF.des
  is '描述';
alter table BF_PARAM_ENUM_DEF
  add constraint PK_BF_PARAM_ENUM_DEF primary key (PARAM_CODE);

DROP TABLE IF EXISTS BF_PARAM_TREE_DATA;
create table BF_PARAM_TREE_DATA
(
  param_code       VARCHAR(32) not null,
  data_code        VARCHAR(32) not null,
  data_text        VARCHAR(200) not null,
  data_icon        VARCHAR(32),
  parent_data_code VARCHAR(32),
  data_param       VARCHAR(200),
  seqno            INTEGER,
  des              VARCHAR(200)
)
;
comment on table BF_PARAM_TREE_DATA
  is '平台表 - 树型参数数据表';
comment on column BF_PARAM_TREE_DATA.param_code
  is '参数 代码';
comment on column BF_PARAM_TREE_DATA.data_code
  is '数据代码';
comment on column BF_PARAM_TREE_DATA.data_text
  is '数据文本';
comment on column BF_PARAM_TREE_DATA.data_icon
  is '数据图标';
comment on column BF_PARAM_TREE_DATA.parent_data_code
  is '父数据代码';
comment on column BF_PARAM_TREE_DATA.data_param
  is '数据参数';
comment on column BF_PARAM_TREE_DATA.seqno
  is '序号';
comment on column BF_PARAM_TREE_DATA.des
  is '描述';
alter table BF_PARAM_TREE_DATA
  add constraint PK_BF_PARAM_TREE_DATA primary key (PARAM_CODE, DATA_CODE);

DROP TABLE IF EXISTS BF_PERM;
create table BF_PERM
(
  perm_id       INTEGER not null,
  perm_type     VARCHAR(32) not null,
  perm_type_key VARCHAR(32) not null,
  inst_date     CHAR(8),
  inst_time     CHAR(6),
  inst_oper     VARCHAR(20),
  modi_date     CHAR(8),
  modi_time     CHAR(6),
  modi_oper     VARCHAR(20)
)
;
comment on table BF_PERM
  is '平台表 - 权限表';
comment on column BF_PERM.perm_id
  is '权限ID，由系统自动生成唯一的值';
comment on column BF_PERM.perm_type
  is '权限类型 如菜单、文件、或者不同子系统等';
comment on column BF_PERM.perm_type_key
  is '和权限类型对应的权限子表主键';
comment on column BF_PERM.inst_date
  is '新增日期';
comment on column BF_PERM.inst_time
  is '新增时间';
comment on column BF_PERM.inst_oper
  is '新增操作人';
comment on column BF_PERM.modi_date
  is '修改日期';
comment on column BF_PERM.modi_time
  is '修改时间';
comment on column BF_PERM.modi_oper
  is '修改操作人';
create unique index UQ_PERM_TYPE_KEY on BF_PERM (PERM_TYPE, PERM_TYPE_KEY);
alter table BF_PERM
  add constraint PK_BF_AUTHOR_KEY_ID primary key (PERM_ID);

DROP TABLE IF EXISTS BF_ROLE;
create table BF_ROLE
(
  role_id   INTEGER not null,
  role_name VARCHAR(64) not null,
  des       VARCHAR(200),
  inst_date CHAR(8),
  inst_time CHAR(6),
  inst_oper CHAR(20),
  modi_date CHAR(8),
  modi_time CHAR(6),
  modi_oper CHAR(20)
)
;
comment on table BF_ROLE
  is '平台表 - 角色表';
comment on column BF_ROLE.role_id
  is '角色编号';
comment on column BF_ROLE.role_name
  is '角色名称，由于角色代码自动生成，因此要求角色名称不能重复';
comment on column BF_ROLE.des
  is '描述';
comment on column BF_ROLE.inst_date
  is '新增日期';
comment on column BF_ROLE.inst_time
  is '新增时间';
comment on column BF_ROLE.inst_oper
  is '新增操作人';
comment on column BF_ROLE.modi_date
  is '修改日期';
comment on column BF_ROLE.modi_time
  is '修改时间';
comment on column BF_ROLE.modi_oper
  is '修改操作人';
create unique index IDX_BF_ROLE_ROLE_NAME on BF_ROLE (ROLE_NAME);
alter table BF_ROLE
  add constraint PK_BF_ROLE_ROLE_ID primary key (ROLE_ID);

DROP TABLE IF EXISTS BF_ROLE_ALLOT;
create table BF_ROLE_ALLOT
(
  role_allot_id   INTEGER not null,
  role_allot_name VARCHAR(64) not null,
  des             VARCHAR(200),
  inst_date       CHAR(8),
  inst_time       CHAR(6),
  inst_oper       CHAR(20),
  modi_date       CHAR(8),
  modi_time       CHAR(6),
  modi_oper       CHAR(20)
)
;
comment on table BF_ROLE_ALLOT
  is '平台表 - 角色(分配)表';
comment on column BF_ROLE_ALLOT.role_allot_id
  is '角色(分配) ID';
comment on column BF_ROLE_ALLOT.role_allot_name
  is '角色(分配)名称';
comment on column BF_ROLE_ALLOT.des
  is '描述';
comment on column BF_ROLE_ALLOT.inst_date
  is '新增日期';
comment on column BF_ROLE_ALLOT.inst_time
  is '新增时间';
comment on column BF_ROLE_ALLOT.inst_oper
  is '新增操作人';
comment on column BF_ROLE_ALLOT.modi_date
  is '修改日期';
comment on column BF_ROLE_ALLOT.modi_time
  is '修改时间';
comment on column BF_ROLE_ALLOT.modi_oper
  is '修改操作人';
alter table BF_ROLE_ALLOT
  add constraint PK_BF_AUTHOR_ALLOT_SUITE_ID primary key (ROLE_ALLOT_ID);

DROP TABLE IF EXISTS BF_ROLE_ALLOT_PERM;
create table BF_ROLE_ALLOT_PERM
(
  role_allot_id INTEGER not null,
  perm_id       INTEGER not null,
  inst_date     CHAR(8),
  inst_time     CHAR(6),
  inst_oper     CHAR(20),
  modi_date     CHAR(8),
  modi_time     CHAR(6),
  modi_oper     CHAR(20)
)
;
comment on table BF_ROLE_ALLOT_PERM
  is '平台表 - 角色(分配) 权限关系表';
comment on column BF_ROLE_ALLOT_PERM.role_allot_id
  is '角色可分配的权限单元';
comment on column BF_ROLE_ALLOT_PERM.perm_id
  is '权限ID';
comment on column BF_ROLE_ALLOT_PERM.inst_date
  is '新增日期';
comment on column BF_ROLE_ALLOT_PERM.inst_time
  is '新增时间';
comment on column BF_ROLE_ALLOT_PERM.inst_oper
  is '新增操作人';
comment on column BF_ROLE_ALLOT_PERM.modi_date
  is '修改日期';
comment on column BF_ROLE_ALLOT_PERM.modi_time
  is '修改时间';
comment on column BF_ROLE_ALLOT_PERM.modi_oper
  is '修改操作人';
alter table BF_ROLE_ALLOT_PERM
  add constraint PK_BF_AUTHORION_SUITE_ID primary key (ROLE_ALLOT_ID, PERM_ID);

DROP TABLE IF EXISTS BF_ROLE_LIMIT;
create table BF_ROLE_LIMIT
(
  limit_no  VARCHAR(32) not null,
  role_id   INTEGER not null,
  des       VARCHAR(200),
  inst_date CHAR(8),
  inst_time CHAR(6),
  inst_oper CHAR(20),
  modi_date CHAR(8),
  modi_time CHAR(6),
  modi_oper CHAR(20)
)
;
comment on table BF_ROLE_LIMIT
  is '平台表 - 角色约束表(控制角色互斥)';
comment on column BF_ROLE_LIMIT.limit_no
  is '约束编号';
comment on column BF_ROLE_LIMIT.role_id
  is '角色编号';
comment on column BF_ROLE_LIMIT.des
  is '描述';
comment on column BF_ROLE_LIMIT.inst_date
  is '新增日期';
comment on column BF_ROLE_LIMIT.inst_time
  is '新增时间';
comment on column BF_ROLE_LIMIT.inst_oper
  is '新增操作人';
comment on column BF_ROLE_LIMIT.modi_date
  is '修改日期';
comment on column BF_ROLE_LIMIT.modi_time
  is '修改时间';
comment on column BF_ROLE_LIMIT.modi_oper
  is '修改操作人';
alter table BF_ROLE_LIMIT
  add constraint PK_BF_ROLE_REGEX_ROLE_ID primary key (LIMIT_NO, ROLE_ID);

DROP TABLE IF EXISTS BF_ROLE_PERM;
create table BF_ROLE_PERM
(
  role_id   INTEGER not null,
  perm_id   INTEGER not null,
  perm_attr VARCHAR(32),
  inst_date CHAR(8),
  inst_time CHAR(6),
  inst_oper CHAR(20),
  modi_date CHAR(8),
  modi_time CHAR(6),
  modi_oper CHAR(20)
)
;
comment on table BF_ROLE_PERM
  is '平台表 - 角色与权限关系表';
comment on column BF_ROLE_PERM.role_id
  is '角色编号';
comment on column BF_ROLE_PERM.perm_id
  is '权限ID，由系统自动生成唯一的值';
comment on column BF_ROLE_PERM.perm_attr
  is '权限特性
1：显示可操作
2：显示不可操作';
comment on column BF_ROLE_PERM.inst_date
  is '新增日期';
comment on column BF_ROLE_PERM.inst_time
  is '新增时间';
comment on column BF_ROLE_PERM.inst_oper
  is '新增操作人';
comment on column BF_ROLE_PERM.modi_date
  is '修改日期';
comment on column BF_ROLE_PERM.modi_time
  is '修改时间';
comment on column BF_ROLE_PERM.modi_oper
  is '修改操作人';
alter table BF_ROLE_PERM
  add constraint PK_BF_ROLE_AUTHOR_ROLE_ID primary key (ROLE_ID, PERM_ID);

DROP TABLE IF EXISTS BF_ROLE_ROLE_ALLOT;
create table BF_ROLE_ROLE_ALLOT
(
  role_id       INTEGER not null,
  role_allot_id INTEGER not null,
  inst_date     CHAR(8),
  inst_time     CHAR(6),
  inst_oper     CHAR(20),
  modi_date     CHAR(8),
  modi_time     CHAR(6),
  modi_oper     CHAR(20)
)
;
comment on table BF_ROLE_ROLE_ALLOT
  is '平台表 - 角色与角色(分配)关系表';
comment on column BF_ROLE_ROLE_ALLOT.role_id
  is '角色编号';
comment on column BF_ROLE_ROLE_ALLOT.role_allot_id
  is '角色(分配)ID';
comment on column BF_ROLE_ROLE_ALLOT.inst_date
  is '新增日期';
comment on column BF_ROLE_ROLE_ALLOT.inst_time
  is '新增时间';
comment on column BF_ROLE_ROLE_ALLOT.inst_oper
  is '新增操作人';
comment on column BF_ROLE_ROLE_ALLOT.modi_date
  is '修改日期';
comment on column BF_ROLE_ROLE_ALLOT.modi_time
  is '修改时间';
comment on column BF_ROLE_ROLE_ALLOT.modi_oper
  is '修改操作人';
alter table BF_ROLE_ROLE_ALLOT
  add constraint PK_BF_ROLE_ALLOT_ROLE_ID primary key (ROLE_ID, ROLE_ALLOT_ID);

DROP TABLE IF EXISTS BF_SHORT_MENU;
create table BF_SHORT_MENU
(
  key_id          VARCHAR(32) not null,
  parent_id       VARCHAR(32),
  user_id         VARCHAR(20),
  menu_id         INTEGER,
  short_menu_name VARCHAR(64),
  display_icon    VARCHAR(32),
  seqno           INTEGER,
  des             VARCHAR(200)
)
;
comment on table BF_SHORT_MENU
  is '平台表 - 快捷菜单表';
comment on column BF_SHORT_MENU.key_id
  is '代理主键';
comment on column BF_SHORT_MENU.parent_id
  is '父ID，和代理主键对应';
comment on column BF_SHORT_MENU.user_id
  is '用户ID';
comment on column BF_SHORT_MENU.menu_id
  is '菜单ID';
comment on column BF_SHORT_MENU.short_menu_name
  is '快捷菜单名称';
comment on column BF_SHORT_MENU.display_icon
  is '显示图标';
comment on column BF_SHORT_MENU.seqno
  is '排序';
comment on column BF_SHORT_MENU.des
  is '描述';
alter table BF_SHORT_MENU
  add primary key (KEY_ID);

DROP TABLE IF EXISTS BF_TASK_DETAIL;
create table BF_TASK_DETAIL
(
  key_id     VARCHAR(64) not null,
  task_id    VARCHAR(32) not null,
  busi_key   VARCHAR(64) not null,
  task_data  VARCHAR(8),
  task_time  VARCHAR(8),
  target_url VARCHAR(64),
  params     VARCHAR(200)
)
;
comment on table BF_TASK_DETAIL
  is '平台表 - 任务明细 提供数据任务统计的任务明细表';
comment on column BF_TASK_DETAIL.key_id
  is '任务主键号 可以是业务主键也可以是工作流的activid';
comment on column BF_TASK_DETAIL.task_id
  is '任务队列编号';
comment on column BF_TASK_DETAIL.busi_key
  is '业务主键';
comment on column BF_TASK_DETAIL.task_data
  is '任务日期';
comment on column BF_TASK_DETAIL.task_time
  is '任务时间';
comment on column BF_TASK_DETAIL.target_url
  is '跳转链接';
comment on column BF_TASK_DETAIL.params
  is '参数值';
alter table BF_TASK_DETAIL
  add constraint PK_BF_TASK_DETAIL_KEY_ID primary key (TASK_ID, BUSI_KEY);

DROP TABLE IF EXISTS BF_TASK_LIMIT_ORGS;
create table BF_TASK_LIMIT_ORGS
(
  key_id VARCHAR(64),
  org_id VARCHAR(32) not null
)
;
comment on table BF_TASK_LIMIT_ORGS
  is '平台表 - 机构限定表 根据机构来限制任务的可见';
comment on column BF_TASK_LIMIT_ORGS.key_id
  is '主键号 可以是业务主键也可以是工作流的activid';
comment on column BF_TASK_LIMIT_ORGS.org_id
  is '机构';
create index IDX_BF_TASK_LRGS_KEY_ID on BF_TASK_LIMIT_ORGS (KEY_ID);

DROP TABLE IF EXISTS BF_TASK_LIMIT_ROLES;
create table BF_TASK_LIMIT_ROLES
(
  key_id  VARCHAR(64),
  role_id VARCHAR(32) not null
)
;
comment on table BF_TASK_LIMIT_ROLES
  is '平台表 - 角色限定表 根据角色来限制任务的可见';
comment on column BF_TASK_LIMIT_ROLES.key_id
  is '主键号 可以是业务主键也可以是工作流的activid';
comment on column BF_TASK_LIMIT_ROLES.role_id
  is '角色';
create index IDX_BF_TASK_LLES_KEY_ID on BF_TASK_LIMIT_ROLES (KEY_ID);

DROP TABLE IF EXISTS BF_TASK_LIMIT_USERS;
create table BF_TASK_LIMIT_USERS
(
  key_id  VARCHAR(64),
  user_id VARCHAR(32) not null
)
;
comment on table BF_TASK_LIMIT_USERS
  is '平台表 - 用户限定表 根据用户来限制任务的可见';
comment on column BF_TASK_LIMIT_USERS.key_id
  is '主键号 可以是业务主键也可以是工作流的activid';
comment on column BF_TASK_LIMIT_USERS.user_id
  is '用户';
create index IDX_BF_TASK_LERS_KEY_ID on BF_TASK_LIMIT_USERS (KEY_ID);

DROP TABLE IF EXISTS BF_TASK_LOG;
create table BF_TASK_LOG
(
  log_id    VARCHAR(32) not null,
  key_id    VARCHAR(32) not null,
  task_id   VARCHAR(200) not null,
  task_date VARCHAR(32) not null,
  task_time VARCHAR(20) not null,
  deal_oper VARCHAR(10),
  deal_date VARCHAR(8),
  deal_time VARCHAR(8)
)
;
comment on table BF_TASK_LOG
  is '平台表 - 任务处理日志表 平台表-任务处理日志表';
comment on column BF_TASK_LOG.log_id
  is '日志编号';
comment on column BF_TASK_LOG.key_id
  is '任务主键号 对应任务表中的主键';
comment on column BF_TASK_LOG.task_id
  is '任务编号';
comment on column BF_TASK_LOG.task_date
  is '任务日期';
comment on column BF_TASK_LOG.task_time
  is '任务时间';
comment on column BF_TASK_LOG.deal_oper
  is '操作人员';
comment on column BF_TASK_LOG.deal_date
  is '操作日期';
comment on column BF_TASK_LOG.deal_time
  is '操作时间';
alter table BF_TASK_LOG
  add constraint PK_BF_TASK_LOG_LOG_ID primary key (LOG_ID);

DROP TABLE IF EXISTS BF_TASK_OWNER;
create table BF_TASK_OWNER
(
  key_id     VARCHAR(32) not null,
  task_id    VARCHAR(32) not null,
  task_name  VARCHAR(200),
  task_icon  VARCHAR(32),
  task_owner VARCHAR(20) not null,
  seqno      INTEGER not null,
  menu_id    INTEGER not null,
  task_desc  VARCHAR(500),
  show_flag  VARCHAR(1),
  pakg_id    VARCHAR(32),
  pakg_flag  VARCHAR(1)
)
;
comment on table BF_TASK_OWNER
  is '平台表 - 待办任务码显示基础表 平台表-待办任务码显示基础表';
comment on column BF_TASK_OWNER.key_id
  is '主键ID';
comment on column BF_TASK_OWNER.task_id
  is '任务编号';
comment on column BF_TASK_OWNER.task_name
  is '任务名称';
comment on column BF_TASK_OWNER.task_icon
  is '任务图标';
comment on column BF_TASK_OWNER.task_owner
  is '所属用户';
comment on column BF_TASK_OWNER.seqno
  is '排列顺序';
comment on column BF_TASK_OWNER.menu_id
  is '菜单ID';
comment on column BF_TASK_OWNER.task_desc
  is '任务描述';
comment on column BF_TASK_OWNER.show_flag
  is '显示标志 0：独自显示 1：在包中显示 2：不显示 ';
comment on column BF_TASK_OWNER.pakg_id
  is '包主键ID 如果show_flag=1，则非空';
comment on column BF_TASK_OWNER.pakg_flag
  is '包标记 0：非包  1：是包';
create unique index IDXU_BF_TASK_OWNER_TASK_ID on BF_TASK_OWNER (TASK_ID);
alter table BF_TASK_OWNER
  add constraint PK_BF_TASK_OWNER_KEY_ID primary key (KEY_ID);

DROP TABLE IF EXISTS BF_TASK_PARAM;
create table BF_TASK_PARAM
(
  task_id        VARCHAR(32) not null,
  task_name      VARCHAR(64) not null,
  menu_id        INTEGER,
  menu_name      VARCHAR(64),
  rule_id        VARCHAR(32),
  detail_menu_id VARCHAR(64)
)
;
comment on table BF_TASK_PARAM
  is '平台表 - 任务参数表 个人快捷菜单信息表';
comment on column BF_TASK_PARAM.task_id
  is '节点id';
comment on column BF_TASK_PARAM.task_name
  is '节点名称';
comment on column BF_TASK_PARAM.menu_id
  is '菜单ID 为非，则表示只是显示统计数据';
comment on column BF_TASK_PARAM.menu_name
  is '菜单名称';
comment on column BF_TASK_PARAM.rule_id
  is '规则编号';
comment on column BF_TASK_PARAM.detail_menu_id
  is '明细菜单ID';
alter table BF_TASK_PARAM
  add constraint PK_BF_TASK_PARAM_TASK_ID primary key (TASK_ID);

DROP TABLE IF EXISTS BF_TASK_REGEX;
create table BF_TASK_REGEX
(
  rule_id        VARCHAR(32) not null,
  task_id        VARCHAR(32) not null,
  oper_flag      VARCHAR(1),
  role_flag      VARCHAR(1),
  org_flag       VARCHAR(1),
  detail_flag    VARCHAR(1),
  detail_menu_id VARCHAR(64),
  inst_oper      VARCHAR(20),
  inst_date      VARCHAR(10),
  inst_time      VARCHAR(10),
  modi_oper      VARCHAR(20),
  modi_date      VARCHAR(10),
  modi_time      VARCHAR(10)
)
;
comment on table BF_TASK_REGEX
  is '平台表 - 任务规则表-限制关联主键';
comment on column BF_TASK_REGEX.rule_id
  is '规则主键ID';
comment on column BF_TASK_REGEX.task_id
  is '节点';
comment on column BF_TASK_REGEX.oper_flag
  is '是否指定到人 Y：指定到人；N：不用到人';
comment on column BF_TASK_REGEX.role_flag
  is '是否限定角色 Y：限定角色；N：不限定';
comment on column BF_TASK_REGEX.org_flag
  is '是否限定机构 Y：限定机构；N：不限定';
comment on column BF_TASK_REGEX.detail_flag
  is '是否进操作页面 Y：能进入操作页；N：只出列表页面';
comment on column BF_TASK_REGEX.detail_menu_id
  is '进入明细的menuid';
comment on column BF_TASK_REGEX.inst_oper
  is '新增人';
comment on column BF_TASK_REGEX.inst_date
  is '新增日期';
comment on column BF_TASK_REGEX.inst_time
  is '新增时间';
comment on column BF_TASK_REGEX.modi_oper
  is '修改人';
comment on column BF_TASK_REGEX.modi_date
  is '修改日期';
comment on column BF_TASK_REGEX.modi_time
  is '修改时间';
alter table BF_TASK_REGEX
  add constraint PK_BF_TASK_REGEX_RULE_ID primary key (RULE_ID);

DROP TABLE IF EXISTS BF_TASK_REGEX_SUB_INFO;
create table BF_TASK_REGEX_SUB_INFO
(
  rule_id      VARCHAR(32) not null,
  limit_flag   VARCHAR(1),
  limit_key_id VARCHAR(32)
)
;
comment on table BF_TASK_REGEX_SUB_INFO
  is '平台表 - 任务规则表-限制关联主键';
comment on column BF_TASK_REGEX_SUB_INFO.rule_id
  is '规则主键ID';
comment on column BF_TASK_REGEX_SUB_INFO.limit_flag
  is '限制类型 R:角色 U:用户 O:规则';
comment on column BF_TASK_REGEX_SUB_INFO.limit_key_id
  is '对应主键';
create index IDX_BTR_SUB_RULE_ID on BF_TASK_REGEX_SUB_INFO (RULE_ID, LIMIT_FLAG);

DROP TABLE IF EXISTS BF_USER;
create table BF_USER
(
  user_id            VARCHAR(20) not null,
  user_name          VARCHAR(64) not null,
  nick_name          VARCHAR(64),
  user_pwd           VARCHAR(32) not null,
  user_status        VARCHAR(32) not null,
  org_id             VARCHAR(10),
  cert_type          VARCHAR(2),
  cert_no            VARCHAR(32),
  mobile_phone       VARCHAR(32),
  telephone          VARCHAR(32),
  email              VARCHAR(64),
  limit_ip           VARCHAR(200),
  online_session_num INTEGER,
  lock_flag          VARCHAR(32),
  lock_date          CHAR(8),
  lock_time          CHAR(6),
  login_num          INTEGER,
  last_login_ip      VARCHAR(32),
  last_login_date    CHAR(8),
  last_login_time    CHAR(6),
  inst_date          CHAR(8),
  inst_time          CHAR(6),
  inst_oper          VARCHAR(20),
  modi_date          CHAR(8),
  modi_time          CHAR(6),
  modi_oper          VARCHAR(20),
  modi_pwd_date      CHAR(8),
  modi_pwd_time      CHAR(6)
)
;
comment on table BF_USER
  is '平台表 - 用户表';
comment on column BF_USER.user_id
  is '用户号';
comment on column BF_USER.user_name
  is '用户名称';
comment on column BF_USER.nick_name
  is '昵称';
comment on column BF_USER.user_pwd
  is '用户密码 用户与密码进行MD5加密处理存储';
comment on column BF_USER.user_status
  is '用户 状态
1：启用
0：停用';
comment on column BF_USER.org_id
  is '机构 号';
comment on column BF_USER.cert_type
  is '证件 类型';
comment on column BF_USER.cert_no
  is 'CERT_NO';
comment on column BF_USER.mobile_phone
  is '移动电话';
comment on column BF_USER.telephone
  is '电话号码';
comment on column BF_USER.email
  is '电子邮箱';
comment on column BF_USER.limit_ip
  is '登录受限IP 只能使用该IP，或IP组登录，ip组用半角逗号隔开';
comment on column BF_USER.online_session_num
  is '在线会话数
登录成功加1，退出时减1；异常情况，由定时器置0处理。';
comment on column BF_USER.lock_flag
  is '锁定标志';
comment on column BF_USER.lock_date
  is '锁定日期';
comment on column BF_USER.lock_time
  is '锁定时间';
comment on column BF_USER.login_num
  is '登录尝试次数';
comment on column BF_USER.last_login_ip
  is '最后登录IP';
comment on column BF_USER.last_login_date
  is '最后登录日期';
comment on column BF_USER.last_login_time
  is '最后登录时间';
comment on column BF_USER.inst_date
  is '新增日期';
comment on column BF_USER.inst_time
  is '新增时间';
comment on column BF_USER.inst_oper
  is '新增操作人';
comment on column BF_USER.modi_date
  is '修改日期';
comment on column BF_USER.modi_time
  is '修改时间';
comment on column BF_USER.modi_oper
  is '修改操作人';
comment on column BF_USER.modi_pwd_date
  is '最后一次修改密码的日期';
comment on column BF_USER.modi_pwd_time
  is '最后一次修改密码的时间';
alter table BF_USER
  add constraint PK_BF_USER primary key (USER_ID);

DROP TABLE IF EXISTS BF_USER_CFG;
create table BF_USER_CFG
(
  user_id     VARCHAR(20) not null,
  param_code  VARCHAR(32) not null,
  param_value VARCHAR(200) not null
)
;
comment on table BF_USER_CFG
  is '平台表 - 用户选项表';
comment on column BF_USER_CFG.user_id
  is '用户号';
comment on column BF_USER_CFG.param_code
  is '参数 代码';
comment on column BF_USER_CFG.param_value
  is '参数值';
alter table BF_USER_CFG
  add constraint PK_BF_USER_CFG primary key (USER_ID, PARAM_CODE);

DROP TABLE IF EXISTS BF_USER_ROLE;
create table BF_USER_ROLE
(
  user_id   VARCHAR(20) not null,
  role_id   INTEGER not null,
  inst_date CHAR(8),
  inst_time CHAR(6),
  inst_oper CHAR(20),
  modi_date CHAR(8),
  modi_time CHAR(6),
  modi_oper CHAR(20)
)
;
comment on table BF_USER_ROLE
  is '平台表 - 用户与角色关系表';
comment on column BF_USER_ROLE.user_id
  is '用户号';
comment on column BF_USER_ROLE.role_id
  is '角色编号';
comment on column BF_USER_ROLE.inst_date
  is '新增日期';
comment on column BF_USER_ROLE.inst_time
  is '新增时间';
comment on column BF_USER_ROLE.inst_oper
  is '新增操作人';
comment on column BF_USER_ROLE.modi_date
  is '修改日期';
comment on column BF_USER_ROLE.modi_time
  is '修改时间';
comment on column BF_USER_ROLE.modi_oper
  is '修改操作人';
alter table BF_USER_ROLE
  add constraint PK_BF_USER_ROLE_USER_ID primary key (USER_ID, ROLE_ID);

DROP TABLE IF EXISTS BF_FUNCTION_GUIDE;
create table BF_FUNCTION_GUIDE
(
  menu_id       INTEGER not null,
  menu_name     VARCHAR(64) not null,
  guide_title   VARCHAR(128),
  guide_content CLOB not null,
  inst_date     VARCHAR(8) not null,
  inst_time     VARCHAR(6) not null,
  inst_oper     VARCHAR(20) not null,
  modi_date     VARCHAR(8),
  modi_time     VARCHAR(6),
  modi_oper     VARCHAR(20)
);
-- Add comments to the table 
comment on table BF_FUNCTION_GUIDE
  is '平台表-业务操作指引表 平台表-对业务的操作指引存在在此表当中';
-- Add comments to the columns 
comment on column BF_FUNCTION_GUIDE.menu_id
  is '操作指引ID';
comment on column BF_FUNCTION_GUIDE.menu_name
  is '菜单名称';
comment on column BF_FUNCTION_GUIDE.guide_title
  is '指引标题';
comment on column BF_FUNCTION_GUIDE.guide_content
  is '指引内容';
comment on column BF_FUNCTION_GUIDE.inst_date
  is '新增日期';
comment on column BF_FUNCTION_GUIDE.inst_time
  is '新增时间';
comment on column BF_FUNCTION_GUIDE.inst_oper
  is '新增操作人';
comment on column BF_FUNCTION_GUIDE.modi_date
  is '修改日期';
comment on column BF_FUNCTION_GUIDE.modi_time
  is '修改时间';
comment on column BF_FUNCTION_GUIDE.modi_oper
  is '修改操作人';
-- Create/Recreate primary, unique and foreign key constraints 
alter table BF_FUNCTION_GUIDE
  add constraint PK_BF_GUIDE_MENU_ID primary key (MENU_ID);

DROP TABLE IF EXISTS BF_FUNCTION_GUIDE_I18N;
create table BF_FUNCTION_GUIDE_I18N
(
  menu_id       INTEGER not null,
  locale        VARCHAR(64) not null,
  guide_content CLOB not null
);
-- Add comments to the table 
comment on table BF_FUNCTION_GUIDE_I18N
  is '平台表——操作指引国际化表';
-- Add comments to the columns 
comment on column BF_FUNCTION_GUIDE_I18N.menu_id
  is '菜单ID';
comment on column BF_FUNCTION_GUIDE_I18N.locale
  is '语言地区';
comment on column BF_FUNCTION_GUIDE_I18N.guide_content
  is '指引内容';
-- Create/Recreate primary, unique and foreign key constraints 
alter table BF_FUNCTION_GUIDE_I18N
  add constraint PK_BF_FUNCTION_GUIDE_I18N primary key (MENU_ID, LOCALE);

 
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