prompt PL/SQL Developer import file
prompt Created on 2016��12��30�� by lenovo
set feedback off
set define off
prompt Dropping BF_ATTACH...
drop table BF_ATTACH cascade constraints;
prompt Dropping BF_DAY_INFO...
drop table BF_DAY_INFO cascade constraints;
prompt Dropping BF_DOC...
drop table BF_DOC cascade constraints;
prompt Dropping BF_DOC_MID...
drop table BF_DOC_MID cascade constraints;
prompt Dropping BF_JOB_CONTROLLER...
drop table BF_JOB_CONTROLLER cascade constraints;
prompt Dropping BF_JOB_EXECUTE_LOG...
drop table BF_JOB_EXECUTE_LOG cascade constraints;
prompt Dropping BF_KEY_CFG...
drop table BF_KEY_CFG cascade constraints;
prompt Dropping BF_LOG_LOGIN...
drop table BF_LOG_LOGIN cascade constraints;
prompt Dropping BF_LOG_VISIT...
drop table BF_LOG_VISIT cascade constraints;
prompt Dropping BF_MEMO_DEF...
drop table BF_MEMO_DEF cascade constraints;
prompt Dropping BF_MEMO_LOG...
drop table BF_MEMO_LOG cascade constraints;
prompt Dropping BF_MENU...
drop table BF_MENU cascade constraints;
prompt Dropping BF_MENU_I18N...
drop table BF_MENU_I18N cascade constraints;
prompt Dropping BF_NEWS...
drop table BF_NEWS cascade constraints;
prompt Dropping BF_NEWS_LOG...
drop table BF_NEWS_LOG cascade constraints;
prompt Dropping BF_ONLINE_USER...
drop table BF_ONLINE_USER cascade constraints;
prompt Dropping BF_ORG...
drop table BF_ORG cascade constraints;
prompt Dropping BF_PARAM_CFG...
drop table BF_PARAM_CFG cascade constraints;
prompt Dropping BF_PARAM_DEF...
drop table BF_PARAM_DEF cascade constraints;
prompt Dropping BF_PARAM_ENUM_DATA...
drop table BF_PARAM_ENUM_DATA cascade constraints;
prompt Dropping BF_PARAM_ENUM_DEF...
drop table BF_PARAM_ENUM_DEF cascade constraints;
prompt Dropping BF_PARAM_TREE_DATA...
drop table BF_PARAM_TREE_DATA cascade constraints;
prompt Dropping BF_PERM...
drop table BF_PERM cascade constraints;
prompt Dropping BF_ROLE...
drop table BF_ROLE cascade constraints;
prompt Dropping BF_ROLE_ALLOT...
drop table BF_ROLE_ALLOT cascade constraints;
prompt Dropping BF_ROLE_ALLOT_PERM...
drop table BF_ROLE_ALLOT_PERM cascade constraints;
prompt Dropping BF_ROLE_LIMIT...
drop table BF_ROLE_LIMIT cascade constraints;
prompt Dropping BF_ROLE_PERM...
drop table BF_ROLE_PERM cascade constraints;
prompt Dropping BF_ROLE_ROLE_ALLOT...
drop table BF_ROLE_ROLE_ALLOT cascade constraints;
prompt Dropping BF_SHORT_MENU...
drop table BF_SHORT_MENU cascade constraints;
prompt Dropping BF_TASK_DETAIL...
drop table BF_TASK_DETAIL cascade constraints;
prompt Dropping BF_TASK_LIMIT_ORGS...
drop table BF_TASK_LIMIT_ORGS cascade constraints;
prompt Dropping BF_TASK_LIMIT_ROLES...
drop table BF_TASK_LIMIT_ROLES cascade constraints;
prompt Dropping BF_TASK_LIMIT_USERS...
drop table BF_TASK_LIMIT_USERS cascade constraints;
prompt Dropping BF_TASK_LOG...
drop table BF_TASK_LOG cascade constraints;
prompt Dropping BF_TASK_OWNER...
drop table BF_TASK_OWNER cascade constraints;
prompt Dropping BF_TASK_PARAM...
drop table BF_TASK_PARAM cascade constraints;
prompt Dropping BF_TASK_REGEX...
drop table BF_TASK_REGEX cascade constraints;
prompt Dropping BF_TASK_REGEX_SUB_INFO...
drop table BF_TASK_REGEX_SUB_INFO cascade constraints;
prompt Dropping BF_USER...
drop table BF_USER cascade constraints;
prompt Dropping BF_USER_CFG...
drop table BF_USER_CFG cascade constraints;
prompt Dropping BF_USER_ROLE...
drop table BF_USER_ROLE cascade constraints;
prompt Creating BF_ATTACH...
create table BF_ATTACH
(
  attach_id     VARCHAR2(32) not null,
  doc_id        VARCHAR2(32) not null,
  bussiness_key VARCHAR2(32)
)
;
comment on table BF_ATTACH
  is 'ƽ̨�� - ������';
comment on column BF_ATTACH.attach_id
  is '����ID';
comment on column BF_ATTACH.doc_id
  is '�ĵ�ID';
comment on column BF_ATTACH.bussiness_key
  is 'ҵ��ֵ';
alter table BF_ATTACH
  add primary key (ATTACH_ID, DOC_ID);

prompt Creating BF_DAY_INFO...
create table BF_DAY_INFO
(
  solar_date     CHAR(8) not null,
  lunar_year     CHAR(4),
  lunar_month    CHAR(2),
  lunar_day      CHAR(2),
  lunar_desc     VARCHAR2(20),
  week           CHAR(6),
  constellation  CHAR(6),
  month_begin    CHAR(8),
  month_end      CHAR(8),
  year_days      INTEGER,
  month_days     INTEGER,
  is_holiday_day CHAR(1),
  holiday_desc   VARCHAR2(50),
  is_month_end   CHAR(1)
)
;
comment on table BF_DAY_INFO
  is 'ƽ̨�� - ������Ϣ��';
comment on column BF_DAY_INFO.solar_date
  is '��������';
comment on column BF_DAY_INFO.lunar_year
  is 'ũ����';
comment on column BF_DAY_INFO.lunar_month
  is 'ũ���£�����Ϊ12+��������';
comment on column BF_DAY_INFO.lunar_day
  is 'ũ����';
comment on column BF_DAY_INFO.lunar_desc
  is 'ũ����������';
comment on column BF_DAY_INFO.week
  is '����';
comment on column BF_DAY_INFO.constellation
  is '����';
comment on column BF_DAY_INFO.month_begin
  is '�³�����';
comment on column BF_DAY_INFO.month_end
  is '��ĩ����';
comment on column BF_DAY_INFO.year_days
  is '����ڼ���';
comment on column BF_DAY_INFO.month_days
  is '���µڼ���';
comment on column BF_DAY_INFO.is_holiday_day
  is '�Ƿ�ڼ���';
comment on column BF_DAY_INFO.holiday_desc
  is '�ڼ�������';
comment on column BF_DAY_INFO.is_month_end
  is '�Ƿ���ĩ';
alter table BF_DAY_INFO
  add constraint PK_BF_DAY_INFO primary key (SOLAR_DATE);

prompt Creating BF_DOC...
create table BF_DOC
(
  doc_id         VARCHAR2(32) not null,
  doc_name       VARCHAR2(200) not null,
  suffix         VARCHAR2(32),
  check_sum_type VARCHAR2(32),
  check_sum      VARCHAR2(64),
  doc_size       INTEGER not null,
  doc_type       VARCHAR2(32),
  doc_state      VARCHAR2(32),
  store_path     VARCHAR2(200),
  inst_date      CHAR(8),
  inst_time      CHAR(6),
  inst_oper      VARCHAR2(20),
  modi_date      CHAR(8),
  modi_time      CHAR(6),
  modi_oper      VARCHAR2(20)
)
;
comment on table BF_DOC
  is 'ƽ̨�� - �ĵ���';
comment on column BF_DOC.doc_id
  is '�ĵ�ID';
comment on column BF_DOC.doc_name
  is '�ĵ����� ����ǰ���ĵ����ƣ�������·��';
comment on column BF_DOC.suffix
  is '�ļ���׺  ������ţ���xls��txt';
comment on column BF_DOC.check_sum_type
  is '�ļ�У������ �� MD5��SHA';
comment on column BF_DOC.check_sum
  is '�ļ�У��ֵ';
comment on column BF_DOC.doc_size
  is '�ĵ���С';
comment on column BF_DOC.doc_type
  is '�ĵ���� Ԥ���������ĵ��Ĺ���';
comment on column BF_DOC.doc_state
  is '�ĵ�״̬ 0 ���� 1 ����';
comment on column BF_DOC.store_path
  is '�洢·�� �Ѿ�������������ļ���ȫ·���������ƽ̨�ĵ����ĵ����·��';
comment on column BF_DOC.inst_date
  is '��������';
comment on column BF_DOC.inst_time
  is '����ʱ��';
comment on column BF_DOC.inst_oper
  is '�����û�';
comment on column BF_DOC.modi_date
  is '�޸�����';
comment on column BF_DOC.modi_time
  is '�޸�ʱ��';
comment on column BF_DOC.modi_oper
  is '�޸��û�';
alter table BF_DOC
  add primary key (DOC_ID);

prompt Creating BF_DOC_MID...
create table BF_DOC_MID
(
  doc_id         VARCHAR2(32) not null,
  doc_name       VARCHAR2(200) not null,
  suffix         VARCHAR2(32),
  check_sum_type VARCHAR2(32),
  check_sum      VARCHAR2(64),
  doc_size       INTEGER not null,
  doc_type       VARCHAR2(32),
  doc_state      VARCHAR2(32),
  store_path     VARCHAR2(200),
  inst_date      CHAR(8),
  inst_time      CHAR(6),
  inst_oper      VARCHAR2(20)
)
;
comment on table BF_DOC_MID
  is 'ƽ̨�� - �ĵ���';
comment on column BF_DOC_MID.doc_id
  is '�ĵ�ID';
comment on column BF_DOC_MID.doc_name
  is '�ĵ����� ����ǰ���ĵ����ƣ�������·��';
comment on column BF_DOC_MID.suffix
  is '�ļ���׺  ������ţ���xls��txt';
comment on column BF_DOC_MID.check_sum_type
  is '�ļ�У������ �� MD5��SHA';
comment on column BF_DOC_MID.check_sum
  is '�ļ�У��ֵ';
comment on column BF_DOC_MID.doc_size
  is '�ĵ���С';
comment on column BF_DOC_MID.doc_type
  is '�ĵ���� Ԥ���������ĵ��Ĺ���';
comment on column BF_DOC_MID.doc_state
  is '�ĵ�״̬ 0 ���� 1 ����';
comment on column BF_DOC_MID.store_path
  is '�洢·�� �Ѿ�������������ļ���ȫ·���������ƽ̨�ĵ����ĵ����·��';
comment on column BF_DOC_MID.inst_date
  is '��������';
comment on column BF_DOC_MID.inst_time
  is '����ʱ��';
comment on column BF_DOC_MID.inst_oper
  is '����������Ա';

prompt Creating BF_JOB_CONTROLLER...
create table BF_JOB_CONTROLLER
(
  job_key                VARCHAR2(120) not null,
  sched_name             VARCHAR2(120) not null,
  trigger_name           VARCHAR2(200) not null,
  trigger_group          VARCHAR2(200) not null,
  job_name               VARCHAR2(200) not null,
  job_group              VARCHAR2(200) not null,
  begin_fire_time        VARCHAR2(32),
  end_fire_time          VARCHAR2(32),
  duration_time          NUMBER,
  real_duration_time     NUMBER,
  cal_duration_time      NUMBER,
  next_fire_time         VARCHAR2(32),
  cron_express           VARCHAR2(120),
  description            VARCHAR2(300),
  job_level              VARCHAR2(10),
  job_class_name         VARCHAR2(250),
  server_ip              VARCHAR2(32),
  enable_history         VARCHAR2(10),
  enable_timeout_watcher VARCHAR2(10),
  running_status         VARCHAR2(10) not null,
  op_date                VARCHAR2(8) not null,
  op_time                VARCHAR2(6) not null
)
;
alter table BF_JOB_CONTROLLER
  add constraint PK_JOB_CONTROLLER primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP, JOB_NAME, JOB_GROUP);

prompt Creating BF_JOB_EXECUTE_LOG...
create table BF_JOB_EXECUTE_LOG
(
  flow_id            VARCHAR2(60) not null,
  job_key            VARCHAR2(120) not null,
  sched_name         VARCHAR2(120) not null,
  trigger_name       VARCHAR2(200) not null,
  trigger_group      VARCHAR2(200) not null,
  job_name           VARCHAR2(200) not null,
  job_group          VARCHAR2(200) not null,
  job_class_name     VARCHAR2(250),
  server_ip          VARCHAR2(32),
  begin_fire_time    VARCHAR2(32),
  end_fire_time      VARCHAR2(32),
  real_duration_time NUMBER,
  cal_duration_time  NUMBER,
  cron_express       VARCHAR2(120),
  description        VARCHAR2(300),
  job_level          VARCHAR2(10),
  running_status     VARCHAR2(10) not null,
  op_date            VARCHAR2(8) not null,
  op_time            VARCHAR2(6) not null
)
;
alter table BF_JOB_EXECUTE_LOG
  add constraint PK_JOB_EXECUTE_LOG primary key (FLOW_ID);

prompt Creating BF_KEY_CFG...
create table BF_KEY_CFG
(
  module          VARCHAR2(200) not null,
  public_empoent  VARCHAR2(200) not null,
  private_empoent VARCHAR2(200) not null
)
;
comment on table BF_KEY_CFG
  is 'ƽ̨�� - �ӽ�����Կ����¼�����õ���';
comment on column BF_KEY_CFG.module
  is 'ģ';
comment on column BF_KEY_CFG.public_empoent
  is '��Կ';
comment on column BF_KEY_CFG.private_empoent
  is '˽Կ';

prompt Creating BF_LOG_LOGIN...
create table BF_LOG_LOGIN
(
  key_id     VARCHAR2(32) not null,
  session_id VARCHAR2(64) not null,
  user_id    VARCHAR2(20) not null,
  client_ip  VARCHAR2(32),
  server_ip  VARCHAR2(32),
  browser    VARCHAR2(32),
  os         VARCHAR2(32),
  lio_flag   VARCHAR2(32) not null,
  opt_date   CHAR(8) not null,
  opt_time   CHAR(6) not null
)
;
comment on table BF_LOG_LOGIN
  is 'ƽ̨�� - ��¼��־��';
comment on column BF_LOG_LOGIN.key_id
  is 'KEY���� ��ϵͳ�Զ��������ظ���ֵ��Ϊ����';
comment on column BF_LOG_LOGIN.session_id
  is '�ỰID';
comment on column BF_LOG_LOGIN.user_id
  is '�û�ID';
comment on column BF_LOG_LOGIN.client_ip
  is '�ͻ���IP';
comment on column BF_LOG_LOGIN.server_ip
  is '�����IP';
comment on column BF_LOG_LOGIN.browser
  is '�����';
comment on column BF_LOG_LOGIN.os
  is '����ϵͳ';
comment on column BF_LOG_LOGIN.lio_flag
  is '��־ 1������ 2���ǳ�   3��ϵͳ�Զ�ע��';
comment on column BF_LOG_LOGIN.opt_date
  is '���� ����';
comment on column BF_LOG_LOGIN.opt_time
  is '����ʱ��';
alter table BF_LOG_LOGIN
  add constraint PK_BF_LOG_LOGIN_KEY_ID primary key (KEY_ID);

prompt Creating BF_LOG_VISIT...
create table BF_LOG_VISIT
(
  key_id     VARCHAR2(32) not null,
  request_id VARCHAR2(64),
  session_id VARCHAR2(64),
  user_id    VARCHAR2(20),
  perm_id    INTEGER,
  client_ip  VARCHAR2(32),
  server_ip  VARCHAR2(32),
  browser    VARCHAR2(32),
  os         VARCHAR2(32),
  opt_flag   VARCHAR2(32),
  opt_path   VARCHAR2(200),
  opt_url    VARCHAR2(200),
  opt_params VARCHAR2(500),
  opt_date   CHAR(8),
  opt_time   CHAR(6),
  cost_time  INTEGER
)
;
comment on table BF_LOG_VISIT
  is 'ƽ̨�� - ������־��';
comment on column BF_LOG_VISIT.key_id
  is 'KEY���� ��ϵͳ�Զ��������ظ���ֵ��Ϊ����';
comment on column BF_LOG_VISIT.request_id
  is '����ID';
comment on column BF_LOG_VISIT.session_id
  is '�ỰID';
comment on column BF_LOG_VISIT.user_id
  is '�û�ID';
comment on column BF_LOG_VISIT.perm_id
  is 'Ȩ��ID';
comment on column BF_LOG_VISIT.client_ip
  is '�ͻ���IP';
comment on column BF_LOG_VISIT.server_ip
  is '�����IP';
comment on column BF_LOG_VISIT.browser
  is '�����';
comment on column BF_LOG_VISIT.os
  is '����ϵͳ';
comment on column BF_LOG_VISIT.opt_flag
  is '������־  0 �ܾ�����  1 ͨ����Ȩ';
comment on column BF_LOG_VISIT.opt_path
  is '����·��';
comment on column BF_LOG_VISIT.opt_url
  is '����URL';
comment on column BF_LOG_VISIT.opt_params
  is '���ʲ�����ֻ��¼ǰ200���ַ�';
comment on column BF_LOG_VISIT.opt_date
  is '���� ����';
comment on column BF_LOG_VISIT.opt_time
  is '����ʱ��';
comment on column BF_LOG_VISIT.cost_time
  is '�ķ�ʱ��';
alter table BF_LOG_VISIT
  add primary key (KEY_ID);

prompt Creating BF_MEMO_DEF...
create table BF_MEMO_DEF
(
  id           VARCHAR2(32) not null,
  user_id      VARCHAR2(20) not null,
  memo_title   VARCHAR2(100) not null,
  memo_content VARCHAR2(600),
  memo_date    VARCHAR2(8) not null,
  is_remind    INTEGER default 0 not null,
  memo_time    VARCHAR2(6)
)
;
comment on table BF_MEMO_DEF
  is 'ƽ̨�� - ϵͳ������';
comment on column BF_MEMO_DEF.id
  is 'Ψһ����';
comment on column BF_MEMO_DEF.user_id
  is '�û�ID';
comment on column BF_MEMO_DEF.memo_title
  is '����';
comment on column BF_MEMO_DEF.memo_content
  is '����';
comment on column BF_MEMO_DEF.memo_date
  is '������ʼ����';
comment on column BF_MEMO_DEF.is_remind
  is '�Ƿ���Ҫ���� Ĭ�� 0������Ҫ���� 1����Ҫ����';
comment on column BF_MEMO_DEF.memo_time
  is '��ʼ��ʱ��';
alter table BF_MEMO_DEF
  add constraint PRIMARY_ID primary key (ID);

prompt Creating BF_MEMO_LOG...
create table BF_MEMO_LOG
(
  id           VARCHAR2(32) not null,
  user_id      VARCHAR2(20) not null,
  memo_title   VARCHAR2(100) not null,
  memo_content VARCHAR2(600),
  memo_date    VARCHAR2(8),
  memo_time    VARCHAR2(6) not null,
  memo_id      VARCHAR2(32) not null,
  op_date      VARCHAR2(8) not null,
  op_time      VARCHAR2(6) not null
)
;
comment on table BF_MEMO_LOG
  is 'ƽ̨�� -  ϵͳ������־��';
comment on column BF_MEMO_LOG.id
  is '������';
comment on column BF_MEMO_LOG.user_id
  is '�û�ID';
comment on column BF_MEMO_LOG.memo_title
  is '����';
comment on column BF_MEMO_LOG.memo_content
  is '����';
comment on column BF_MEMO_LOG.memo_date
  is '������ʼ����';
comment on column BF_MEMO_LOG.memo_time
  is '������ʼʱ��';
comment on column BF_MEMO_LOG.memo_id
  is '����ID';
comment on column BF_MEMO_LOG.op_date
  is '��������';
comment on column BF_MEMO_LOG.op_time
  is '����ʱ��';
alter table BF_MEMO_LOG
  add constraint PRIMARY_LOG_ID primary key (ID);

prompt Creating BF_MENU...
create table BF_MENU
(
  menu_id        INTEGER not null,
  parent_menu_id INTEGER,
  menu_name      VARCHAR2(64) not null,
  menu_url       VARCHAR2(200),
  display_area   VARCHAR2(32),
  display_icon   VARCHAR2(32),
  depend_menu_id INTEGER,
  author_level   VARCHAR2(32),
  menu_flag      VARCHAR2(32),
  perm_tree_flag VARCHAR2(32),
  target_page    VARCHAR2(32),
  seqno          INTEGER,
  des            VARCHAR2(200),
  inst_date      CHAR(8),
  inst_time      CHAR(6),
  inst_oper      CHAR(20),
  modi_date      CHAR(8),
  modi_time      CHAR(6),
  modi_oper      CHAR(20)
)
;
comment on table BF_MENU
  is 'ƽ̨�� - �˵���
����һ��Ȩ���ӱ�����һ���˵���ʾ��ͷ��������һ���˵�����ʾ���ԣ�������������ڵ�����ʾ��ͷ������������ߣ�';
comment on column BF_MENU.menu_id
  is '�˵�ID';
comment on column BF_MENU.parent_menu_id
  is '���˵�ID';
comment on column BF_MENU.menu_name
  is '�˵�����';
comment on column BF_MENU.menu_url
  is '�˵�URL';
comment on column BF_MENU.display_area
  is '��ʾ���� ����ǲ˵�ʱ��Ĭ��һ���˵���ͷ����ʾ���Ӽ��˵�����ʾ�ɸ����˵�����ʾλ����������0��ͷ����ʾ  1���ಿ�˵�';
comment on column BF_MENU.display_icon
  is '��ʽ����';
comment on column BF_MENU.depend_menu_id
  is '�˵����� ֻ������һ��';
comment on column BF_MENU.author_level
  is '��Ȩ���� �ڵ�¼ʱ��0��1�ȼ�飬2��Ҫ��Ȩ�޿��ƽ��м��
0�������¼���ɷ���
1��ֻҪ��Ч�û���¼����
2����Ҫ��Ȩ���ɷ���';
comment on column BF_MENU.menu_flag
  is '�˵����� 0���˵�  1��������';
comment on column BF_MENU.perm_tree_flag
  is '�Ƿ���ʾ��Ȩ������ ����ǲ˵���־�������־һ��Ϊ1  �����ʾ0�������˵�����Ϊ�ա� 0--���ڹ���������ʾ  1--�ڹ���������ʾ';
comment on column BF_MENU.target_page
  is 'Ŀ��ҳ�� Ϊ��ʱ����ѡ��д򿪣�������µ�ҳ��';
comment on column BF_MENU.seqno
  is '����';
comment on column BF_MENU.des
  is '����';
comment on column BF_MENU.inst_date
  is '��������';
comment on column BF_MENU.inst_time
  is '����ʱ��';
comment on column BF_MENU.inst_oper
  is '����������';
comment on column BF_MENU.modi_date
  is '�޸�����';
comment on column BF_MENU.modi_time
  is '�޸�ʱ��';
comment on column BF_MENU.modi_oper
  is '�޸Ĳ�����';
alter table BF_MENU
  add constraint PK_BF_AUTHOR_ITEM_AUTHOR_NO primary key (MENU_ID);

prompt Creating BF_MENU_I18N...
create table BF_MENU_I18N
(
  menu_id   INTEGER not null,
  locale    VARCHAR2(64) not null,
  menu_name VARCHAR2(64) not null,
  des       VARCHAR2(200)
)
;
comment on table BF_MENU_I18N
  is 'ƽ̨�����˵����ʻ���';
comment on column BF_MENU_I18N.menu_id
  is '�˵�ID';
comment on column BF_MENU_I18N.locale
  is '���Ե���';
comment on column BF_MENU_I18N.menu_name
  is '�˵�����';
comment on column BF_MENU_I18N.des
  is '����';
alter table BF_MENU_I18N
  add constraint PK_BF_MENU_I18N primary key (MENU_ID, LOCALE);

prompt Creating BF_NEWS...
create table BF_NEWS
(
  msg_id               VARCHAR2(32) not null,
  msg_title            VARCHAR2(100) not null,
  msg_content          VARCHAR2(500) not null,
  target_url           VARCHAR2(200),
  msg_type             CHAR(1),
  msg_level            CHAR(1),
  send_oper            VARCHAR2(20) not null,
  send_date            CHAR(8) not null,
  send_time            CHAR(6) not null,
  recv_net             VARCHAR2(7),
  recv_oper            VARCHAR2(20),
  status               CHAR(1) default '0',
  msg_icon             VARCHAR2(20),
  effective_flag       CHAR(1),
  effective_day_cnt    INTEGER,
  effective_start_date CHAR(8),
  effective_start_time CHAR(8),
  effective_end_date   CHAR(8),
  effective_end_time   CHAR(8)
)
;
comment on table BF_NEWS
  is 'ƽ̨�� -  ��Ϣ��';
comment on column BF_NEWS.msg_id
  is '��ϢID';
comment on column BF_NEWS.msg_title
  is '��Ϣ����';
comment on column BF_NEWS.msg_content
  is '��Ϣ����';
comment on column BF_NEWS.target_url
  is 'Ŀ��URL';
comment on column BF_NEWS.msg_type
  is '1-�������е��˶����Կ���
2-֪ͨ����¼����Բ鿴 3-�ź���ָ����/����/��ɫ�ɲ鿴';
comment on column BF_NEWS.msg_level
  is '1-��
2-��
3-��';
comment on column BF_NEWS.send_oper
  is '��Ϣ������';
comment on column BF_NEWS.send_date
  is '��Ϣ��������';
comment on column BF_NEWS.send_time
  is '��Ϣ����ʱ��';
comment on column BF_NEWS.recv_net
  is '��Ϣ���ջ���';
comment on column BF_NEWS.recv_oper
  is '��Ϣ�����û�';
comment on column BF_NEWS.status
  is '0-δ�Ķ�
1-���Ķ�';
comment on column BF_NEWS.msg_icon
  is '��Ϣͼ��';
comment on column BF_NEWS.effective_flag
  is '��Ч��־  1��������Ч��2��������Ч��3��������Ч';
comment on column BF_NEWS.effective_day_cnt
  is '��Ч����';
comment on column BF_NEWS.effective_start_date
  is '��Ч��ʼ����';
comment on column BF_NEWS.effective_start_time
  is '��Ч��ʼʱ��';
comment on column BF_NEWS.effective_end_date
  is '��Ч��������';
comment on column BF_NEWS.effective_end_time
  is '��Ч����ʱ��';
alter table BF_NEWS
  add constraint PK_BF_NEWS primary key (MSG_ID);

prompt Creating BF_NEWS_LOG...
create table BF_NEWS_LOG
(
  user_id   VARCHAR2(20) not null,
  msg_id    VARCHAR2(32) not null,
  oper_date CHAR(8) not null,
  oper_time CHAR(6) not null
)
;
comment on table BF_NEWS_LOG
  is 'ƽ̨�� - ��Ϣ���Ѷ���Ϣ��¼��';
comment on column BF_NEWS_LOG.user_id
  is '�û���';
comment on column BF_NEWS_LOG.msg_id
  is '��ϢID';
comment on column BF_NEWS_LOG.oper_date
  is '�Ķ�����';
comment on column BF_NEWS_LOG.oper_time
  is '�Ķ�ʱ��';

prompt Creating BF_ONLINE_USER...
create table BF_ONLINE_USER
(
  session_id VARCHAR2(64) not null,
  user_id    VARCHAR2(20) not null,
  client_ip  VARCHAR2(32),
  server_ip  VARCHAR2(32),
  browser    VARCHAR2(32),
  os         VARCHAR2(32),
  login_date CHAR(8) not null,
  login_time CHAR(6) not null
)
;
comment on table BF_ONLINE_USER
  is 'ƽ̨�� - �����û���';
comment on column BF_ONLINE_USER.session_id
  is '�ỰID';
comment on column BF_ONLINE_USER.user_id
  is '�û�ID';
comment on column BF_ONLINE_USER.client_ip
  is '�ͻ���IP��ַ';
comment on column BF_ONLINE_USER.server_ip
  is '�����IP��ַ';
comment on column BF_ONLINE_USER.browser
  is '�����';
comment on column BF_ONLINE_USER.os
  is '����ϵͳ';
comment on column BF_ONLINE_USER.login_date
  is '��¼����';
comment on column BF_ONLINE_USER.login_time
  is '��¼ʱ��';
alter table BF_ONLINE_USER
  add primary key (SESSION_ID);

prompt Creating BF_ORG...
create table BF_ORG
(
  org_id     VARCHAR2(32) not null,
  org_name   VARCHAR2(100) not null,
  sup_org_id VARCHAR2(32),
  org_level  INTEGER,
  org_type   VARCHAR2(32),
  des        VARCHAR2(200),
  inst_date  CHAR(8),
  inst_time  CHAR(6),
  inst_oper  CHAR(20),
  modi_date  CHAR(8),
  modi_time  CHAR(6),
  modi_oper  CHAR(20)
)
;
comment on table BF_ORG
  is 'ƽ̨�� - ����';
comment on column BF_ORG.org_id
  is '������';
comment on column BF_ORG.org_name
  is '��������';
comment on column BF_ORG.sup_org_id
  is '�ϼ�������';
comment on column BF_ORG.org_level
  is '��������';
comment on column BF_ORG.org_type
  is '�������ͣ�Ԥ����';
comment on column BF_ORG.des
  is '����';
comment on column BF_ORG.inst_date
  is '��������';
comment on column BF_ORG.inst_time
  is '����ʱ��';
comment on column BF_ORG.inst_oper
  is '������';
comment on column BF_ORG.modi_date
  is '�޸�����';
comment on column BF_ORG.modi_time
  is '�޸�ʱ��';
comment on column BF_ORG.modi_oper
  is '�޸���';
alter table BF_ORG
  add primary key (ORG_ID);

prompt Creating BF_PARAM_CFG...
create table BF_PARAM_CFG
(
  param_code  VARCHAR2(32) not null,
  param_value VARCHAR2(200) not null
)
;
comment on table BF_PARAM_CFG
  is 'ƽ̨�� - �������ñ�';
comment on column BF_PARAM_CFG.param_code
  is '���� ����';
comment on column BF_PARAM_CFG.param_value
  is '����ֵ';
alter table BF_PARAM_CFG
  add constraint PK_BF_PARAM_CFG primary key (PARAM_CODE);

prompt Creating BF_PARAM_DEF...
create table BF_PARAM_DEF
(
  param_code       VARCHAR2(32) not null,
  param_name       VARCHAR2(200) not null,
  store_type       VARCHAR2(32) not null,
  param_group      VARCHAR2(32) not null,
  data_type        VARCHAR2(32) not null,
  editable         VARCHAR2(32),
  default_value    VARCHAR2(200),
  value_rule       VARCHAR2(32),
  value_rule_param VARCHAR2(32),
  seqno            INTEGER,
  des              VARCHAR2(200)
)
;
comment on table BF_PARAM_DEF
  is 'ƽ̨�� - �������Զ����';
comment on column BF_PARAM_DEF.param_code
  is '���� ����';
comment on column BF_PARAM_DEF.param_name
  is '��������';
comment on column BF_PARAM_DEF.store_type
  is '�洢��ʽ
PLAINTEXT ����
ENCRYPT ����';
comment on column BF_PARAM_DEF.param_group
  is '�����������';
comment on column BF_PARAM_DEF.data_type
  is '��������';
comment on column BF_PARAM_DEF.editable
  is '�Ƿ�ɱ༭ 0 ���ɱ༭  1 �ɱ༭';
comment on column BF_PARAM_DEF.default_value
  is 'Ĭ��ֵ';
comment on column BF_PARAM_DEF.value_rule
  is 'ȡֵ����
��������ʽ��ö�ٵ�';
comment on column BF_PARAM_DEF.value_rule_param
  is 'ȡֵ�������
������ʽ���ı���ö�ٵ����͵�';
comment on column BF_PARAM_DEF.seqno
  is '���';
comment on column BF_PARAM_DEF.des
  is '����';
alter table BF_PARAM_DEF
  add constraint PK_BF_PARAM_DEF primary key (PARAM_CODE);

prompt Creating BF_PARAM_ENUM_DATA...
create table BF_PARAM_ENUM_DATA
(
  param_code VARCHAR2(32) not null,
  data_code  VARCHAR2(32) not null,
  data_text  VARCHAR2(200) not null,
  data_param VARCHAR2(200),
  seqno      INTEGER,
  des        VARCHAR2(200)
)
;
comment on table BF_PARAM_ENUM_DATA
  is 'ƽ̨�� - ö�ٲ������ݱ�';
comment on column BF_PARAM_ENUM_DATA.param_code
  is '���� ����';
comment on column BF_PARAM_ENUM_DATA.data_code
  is '���ݴ���';
comment on column BF_PARAM_ENUM_DATA.data_text
  is '�����ı�';
comment on column BF_PARAM_ENUM_DATA.data_param
  is '���ݲ���';
comment on column BF_PARAM_ENUM_DATA.seqno
  is '���';
comment on column BF_PARAM_ENUM_DATA.des
  is '��������';
alter table BF_PARAM_ENUM_DATA
  add constraint PK_BF_PARAM_ENUM_DATA primary key (PARAM_CODE, DATA_CODE);

prompt Creating BF_PARAM_ENUM_DEF...
create table BF_PARAM_ENUM_DEF
(
  param_code  VARCHAR2(32) not null,
  param_name  VARCHAR2(200) not null,
  param_group VARCHAR2(32) not null,
  param_attr  VARCHAR2(32) not null,
  editable    VARCHAR2(32),
  seqno       INTEGER,
  des         VARCHAR2(200)
)
;
comment on table BF_PARAM_ENUM_DEF
  is 'ƽ̨�� - ö�ٲ������Զ����';
comment on column BF_PARAM_ENUM_DEF.param_code
  is '���� ����';
comment on column BF_PARAM_ENUM_DEF.param_name
  is '��������';
comment on column BF_PARAM_ENUM_DEF.param_group
  is '�����������';
comment on column BF_PARAM_ENUM_DEF.param_attr
  is '����������LIST �б�ö������  TREE��������';
comment on column BF_PARAM_ENUM_DEF.editable
  is '�Ƿ�ɱ༭ 0 ���ɱ༭  1 �ɱ༭';
comment on column BF_PARAM_ENUM_DEF.seqno
  is '���';
comment on column BF_PARAM_ENUM_DEF.des
  is '����';
alter table BF_PARAM_ENUM_DEF
  add constraint PK_BF_PARAM_ENUM_DEF primary key (PARAM_CODE);

prompt Creating BF_PARAM_TREE_DATA...
create table BF_PARAM_TREE_DATA
(
  param_code       VARCHAR2(32) not null,
  data_code        VARCHAR2(32) not null,
  data_text        VARCHAR2(200) not null,
  data_icon        VARCHAR2(32),
  parent_data_code VARCHAR2(32),
  data_param       VARCHAR2(200),
  seqno            INTEGER,
  des              VARCHAR2(200)
)
;
comment on table BF_PARAM_TREE_DATA
  is 'ƽ̨�� - ���Ͳ������ݱ�';
comment on column BF_PARAM_TREE_DATA.param_code
  is '���� ����';
comment on column BF_PARAM_TREE_DATA.data_code
  is '���ݴ���';
comment on column BF_PARAM_TREE_DATA.data_text
  is '�����ı�';
comment on column BF_PARAM_TREE_DATA.data_icon
  is '����ͼ��';
comment on column BF_PARAM_TREE_DATA.parent_data_code
  is '�����ݴ���';
comment on column BF_PARAM_TREE_DATA.data_param
  is '���ݲ���';
comment on column BF_PARAM_TREE_DATA.seqno
  is '���';
comment on column BF_PARAM_TREE_DATA.des
  is '����';
alter table BF_PARAM_TREE_DATA
  add constraint PK_BF_PARAM_TREE_DATA primary key (PARAM_CODE, DATA_CODE);

prompt Creating BF_PERM...
create table BF_PERM
(
  perm_id       INTEGER not null,
  perm_type     VARCHAR2(32) not null,
  perm_type_key VARCHAR2(32) not null,
  inst_date     CHAR(8),
  inst_time     CHAR(6),
  inst_oper     VARCHAR2(20),
  modi_date     CHAR(8),
  modi_time     CHAR(6),
  modi_oper     VARCHAR2(20)
)
;
comment on table BF_PERM
  is 'ƽ̨�� - Ȩ�ޱ�';
comment on column BF_PERM.perm_id
  is 'Ȩ��ID����ϵͳ�Զ�����Ψһ��ֵ';
comment on column BF_PERM.perm_type
  is 'Ȩ������ ��˵����ļ������߲�ͬ��ϵͳ��';
comment on column BF_PERM.perm_type_key
  is '��Ȩ�����Ͷ�Ӧ��Ȩ���ӱ�����';
comment on column BF_PERM.inst_date
  is '��������';
comment on column BF_PERM.inst_time
  is '����ʱ��';
comment on column BF_PERM.inst_oper
  is '����������';
comment on column BF_PERM.modi_date
  is '�޸�����';
comment on column BF_PERM.modi_time
  is '�޸�ʱ��';
comment on column BF_PERM.modi_oper
  is '�޸Ĳ�����';
create unique index UQ_PERM_TYPE_KEY on BF_PERM (PERM_TYPE, PERM_TYPE_KEY);
alter table BF_PERM
  add constraint PK_BF_AUTHOR_KEY_ID primary key (PERM_ID);

prompt Creating BF_ROLE...
create table BF_ROLE
(
  role_id   INTEGER not null,
  role_name VARCHAR2(64) not null,
  des       VARCHAR2(200),
  inst_date CHAR(8),
  inst_time CHAR(6),
  inst_oper CHAR(20),
  modi_date CHAR(8),
  modi_time CHAR(6),
  modi_oper CHAR(20)
)
;
comment on table BF_ROLE
  is 'ƽ̨�� - ��ɫ��';
comment on column BF_ROLE.role_id
  is '��ɫ���';
comment on column BF_ROLE.role_name
  is '��ɫ���ƣ����ڽ�ɫ�����Զ����ɣ����Ҫ���ɫ���Ʋ����ظ�';
comment on column BF_ROLE.des
  is '����';
comment on column BF_ROLE.inst_date
  is '��������';
comment on column BF_ROLE.inst_time
  is '����ʱ��';
comment on column BF_ROLE.inst_oper
  is '����������';
comment on column BF_ROLE.modi_date
  is '�޸�����';
comment on column BF_ROLE.modi_time
  is '�޸�ʱ��';
comment on column BF_ROLE.modi_oper
  is '�޸Ĳ�����';
create unique index IDX_BF_ROLE_ROLE_NAME on BF_ROLE (ROLE_NAME);
alter table BF_ROLE
  add constraint PK_BF_ROLE_ROLE_ID primary key (ROLE_ID);

prompt Creating BF_ROLE_ALLOT...
create table BF_ROLE_ALLOT
(
  role_allot_id   INTEGER not null,
  role_allot_name VARCHAR2(64) not null,
  des             VARCHAR2(200),
  inst_date       CHAR(8),
  inst_time       CHAR(6),
  inst_oper       CHAR(20),
  modi_date       CHAR(8),
  modi_time       CHAR(6),
  modi_oper       CHAR(20)
)
;
comment on table BF_ROLE_ALLOT
  is 'ƽ̨�� - ��ɫ(����)��';
comment on column BF_ROLE_ALLOT.role_allot_id
  is '��ɫ(����) ID';
comment on column BF_ROLE_ALLOT.role_allot_name
  is '��ɫ(����)����';
comment on column BF_ROLE_ALLOT.des
  is '����';
comment on column BF_ROLE_ALLOT.inst_date
  is '��������';
comment on column BF_ROLE_ALLOT.inst_time
  is '����ʱ��';
comment on column BF_ROLE_ALLOT.inst_oper
  is '����������';
comment on column BF_ROLE_ALLOT.modi_date
  is '�޸�����';
comment on column BF_ROLE_ALLOT.modi_time
  is '�޸�ʱ��';
comment on column BF_ROLE_ALLOT.modi_oper
  is '�޸Ĳ�����';
alter table BF_ROLE_ALLOT
  add constraint PK_BF_AUTHOR_ALLOT_SUITE_ID primary key (ROLE_ALLOT_ID);

prompt Creating BF_ROLE_ALLOT_PERM...
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
  is 'ƽ̨�� - ��ɫ(����) Ȩ�޹�ϵ��';
comment on column BF_ROLE_ALLOT_PERM.role_allot_id
  is '��ɫ�ɷ����Ȩ�޵�Ԫ';
comment on column BF_ROLE_ALLOT_PERM.perm_id
  is 'Ȩ��ID';
comment on column BF_ROLE_ALLOT_PERM.inst_date
  is '��������';
comment on column BF_ROLE_ALLOT_PERM.inst_time
  is '����ʱ��';
comment on column BF_ROLE_ALLOT_PERM.inst_oper
  is '����������';
comment on column BF_ROLE_ALLOT_PERM.modi_date
  is '�޸�����';
comment on column BF_ROLE_ALLOT_PERM.modi_time
  is '�޸�ʱ��';
comment on column BF_ROLE_ALLOT_PERM.modi_oper
  is '�޸Ĳ�����';
alter table BF_ROLE_ALLOT_PERM
  add constraint PK_BF_AUTHORION_SUITE_ID primary key (ROLE_ALLOT_ID, PERM_ID);

prompt Creating BF_ROLE_LIMIT...
create table BF_ROLE_LIMIT
(
  limit_no  VARCHAR2(32) not null,
  role_id   INTEGER not null,
  des       VARCHAR2(200),
  inst_date CHAR(8),
  inst_time CHAR(6),
  inst_oper CHAR(20),
  modi_date CHAR(8),
  modi_time CHAR(6),
  modi_oper CHAR(20)
)
;
comment on table BF_ROLE_LIMIT
  is 'ƽ̨�� - ��ɫԼ����(���ƽ�ɫ����)';
comment on column BF_ROLE_LIMIT.limit_no
  is 'Լ�����';
comment on column BF_ROLE_LIMIT.role_id
  is '��ɫ���';
comment on column BF_ROLE_LIMIT.des
  is '����';
comment on column BF_ROLE_LIMIT.inst_date
  is '��������';
comment on column BF_ROLE_LIMIT.inst_time
  is '����ʱ��';
comment on column BF_ROLE_LIMIT.inst_oper
  is '����������';
comment on column BF_ROLE_LIMIT.modi_date
  is '�޸�����';
comment on column BF_ROLE_LIMIT.modi_time
  is '�޸�ʱ��';
comment on column BF_ROLE_LIMIT.modi_oper
  is '�޸Ĳ�����';
alter table BF_ROLE_LIMIT
  add constraint PK_BF_ROLE_REGEX_ROLE_ID primary key (LIMIT_NO, ROLE_ID);

prompt Creating BF_ROLE_PERM...
create table BF_ROLE_PERM
(
  role_id   INTEGER not null,
  perm_id   INTEGER not null,
  perm_attr VARCHAR2(32),
  inst_date CHAR(8),
  inst_time CHAR(6),
  inst_oper CHAR(20),
  modi_date CHAR(8),
  modi_time CHAR(6),
  modi_oper CHAR(20)
)
;
comment on table BF_ROLE_PERM
  is 'ƽ̨�� - ��ɫ��Ȩ�޹�ϵ��';
comment on column BF_ROLE_PERM.role_id
  is '��ɫ���';
comment on column BF_ROLE_PERM.perm_id
  is 'Ȩ��ID����ϵͳ�Զ�����Ψһ��ֵ';
comment on column BF_ROLE_PERM.perm_attr
  is 'Ȩ������
1����ʾ�ɲ���
2����ʾ���ɲ���';
comment on column BF_ROLE_PERM.inst_date
  is '��������';
comment on column BF_ROLE_PERM.inst_time
  is '����ʱ��';
comment on column BF_ROLE_PERM.inst_oper
  is '����������';
comment on column BF_ROLE_PERM.modi_date
  is '�޸�����';
comment on column BF_ROLE_PERM.modi_time
  is '�޸�ʱ��';
comment on column BF_ROLE_PERM.modi_oper
  is '�޸Ĳ�����';
alter table BF_ROLE_PERM
  add constraint PK_BF_ROLE_AUTHOR_ROLE_ID primary key (ROLE_ID, PERM_ID);

prompt Creating BF_ROLE_ROLE_ALLOT...
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
  is 'ƽ̨�� - ��ɫ���ɫ(����)��ϵ��';
comment on column BF_ROLE_ROLE_ALLOT.role_id
  is '��ɫ���';
comment on column BF_ROLE_ROLE_ALLOT.role_allot_id
  is '��ɫ(����)ID';
comment on column BF_ROLE_ROLE_ALLOT.inst_date
  is '��������';
comment on column BF_ROLE_ROLE_ALLOT.inst_time
  is '����ʱ��';
comment on column BF_ROLE_ROLE_ALLOT.inst_oper
  is '����������';
comment on column BF_ROLE_ROLE_ALLOT.modi_date
  is '�޸�����';
comment on column BF_ROLE_ROLE_ALLOT.modi_time
  is '�޸�ʱ��';
comment on column BF_ROLE_ROLE_ALLOT.modi_oper
  is '�޸Ĳ�����';
alter table BF_ROLE_ROLE_ALLOT
  add constraint PK_BF_ROLE_ALLOT_ROLE_ID primary key (ROLE_ID, ROLE_ALLOT_ID);

prompt Creating BF_SHORT_MENU...
create table BF_SHORT_MENU
(
  key_id          VARCHAR2(32) not null,
  parent_id       VARCHAR2(32),
  user_id         VARCHAR2(20),
  menu_id         INTEGER,
  short_menu_name VARCHAR2(64),
  display_icon    VARCHAR2(32),
  seqno           INTEGER,
  des             VARCHAR2(200)
)
;
comment on table BF_SHORT_MENU
  is 'ƽ̨�� - ��ݲ˵���';
comment on column BF_SHORT_MENU.key_id
  is '��������';
comment on column BF_SHORT_MENU.parent_id
  is '��ID���ʹ���������Ӧ';
comment on column BF_SHORT_MENU.user_id
  is '�û�ID';
comment on column BF_SHORT_MENU.menu_id
  is '�˵�ID';
comment on column BF_SHORT_MENU.short_menu_name
  is '��ݲ˵�����';
comment on column BF_SHORT_MENU.display_icon
  is '��ʾͼ��';
comment on column BF_SHORT_MENU.seqno
  is '����';
comment on column BF_SHORT_MENU.des
  is '����';
alter table BF_SHORT_MENU
  add primary key (KEY_ID);

prompt Creating BF_TASK_DETAIL...
create table BF_TASK_DETAIL
(
  key_id     VARCHAR2(64) not null,
  task_id    VARCHAR2(32) not null,
  busi_key   VARCHAR2(64) not null,
  task_data  VARCHAR2(8),
  task_time  VARCHAR2(8),
  target_url VARCHAR2(64),
  params     VARCHAR2(200)
)
;
comment on table BF_TASK_DETAIL
  is 'ƽ̨�� - ������ϸ �ṩ��������ͳ�Ƶ�������ϸ��';
comment on column BF_TASK_DETAIL.key_id
  is '���������� ������ҵ������Ҳ�����ǹ�������activid';
comment on column BF_TASK_DETAIL.task_id
  is '������б��';
comment on column BF_TASK_DETAIL.busi_key
  is 'ҵ������';
comment on column BF_TASK_DETAIL.task_data
  is '��������';
comment on column BF_TASK_DETAIL.task_time
  is '����ʱ��';
comment on column BF_TASK_DETAIL.target_url
  is '��ת����';
comment on column BF_TASK_DETAIL.params
  is '����ֵ';
alter table BF_TASK_DETAIL
  add constraint PK_BF_TASK_DETAIL_KEY_ID primary key (TASK_ID, BUSI_KEY);

prompt Creating BF_TASK_LIMIT_ORGS...
create table BF_TASK_LIMIT_ORGS
(
  key_id VARCHAR2(64),
  org_id VARCHAR2(32) not null
)
;
comment on table BF_TASK_LIMIT_ORGS
  is 'ƽ̨�� - �����޶��� ���ݻ�������������Ŀɼ�';
comment on column BF_TASK_LIMIT_ORGS.key_id
  is '������ ������ҵ������Ҳ�����ǹ�������activid';
comment on column BF_TASK_LIMIT_ORGS.org_id
  is '����';
create index IDX_BF_TASK_LRGS_KEY_ID on BF_TASK_LIMIT_ORGS (KEY_ID);

prompt Creating BF_TASK_LIMIT_ROLES...
create table BF_TASK_LIMIT_ROLES
(
  key_id  VARCHAR2(64),
  role_id VARCHAR2(32) not null
)
;
comment on table BF_TASK_LIMIT_ROLES
  is 'ƽ̨�� - ��ɫ�޶��� ���ݽ�ɫ����������Ŀɼ�';
comment on column BF_TASK_LIMIT_ROLES.key_id
  is '������ ������ҵ������Ҳ�����ǹ�������activid';
comment on column BF_TASK_LIMIT_ROLES.role_id
  is '��ɫ';
create index IDX_BF_TASK_LLES_KEY_ID on BF_TASK_LIMIT_ROLES (KEY_ID);

prompt Creating BF_TASK_LIMIT_USERS...
create table BF_TASK_LIMIT_USERS
(
  key_id  VARCHAR2(64),
  user_id VARCHAR2(32) not null
)
;
comment on table BF_TASK_LIMIT_USERS
  is 'ƽ̨�� - �û��޶��� �����û�����������Ŀɼ�';
comment on column BF_TASK_LIMIT_USERS.key_id
  is '������ ������ҵ������Ҳ�����ǹ�������activid';
comment on column BF_TASK_LIMIT_USERS.user_id
  is '�û�';
create index IDX_BF_TASK_LERS_KEY_ID on BF_TASK_LIMIT_USERS (KEY_ID);

prompt Creating BF_TASK_LOG...
create table BF_TASK_LOG
(
  log_id    VARCHAR2(32) not null,
  key_id    VARCHAR2(32) not null,
  task_id   VARCHAR2(200) not null,
  task_date VARCHAR2(32) not null,
  task_time VARCHAR2(20) not null,
  deal_oper VARCHAR2(10),
  deal_date VARCHAR2(8),
  deal_time VARCHAR2(8)
)
;
comment on table BF_TASK_LOG
  is 'ƽ̨�� - ��������־�� ƽ̨��-��������־��';
comment on column BF_TASK_LOG.log_id
  is '��־���';
comment on column BF_TASK_LOG.key_id
  is '���������� ��Ӧ������е�����';
comment on column BF_TASK_LOG.task_id
  is '������';
comment on column BF_TASK_LOG.task_date
  is '��������';
comment on column BF_TASK_LOG.task_time
  is '����ʱ��';
comment on column BF_TASK_LOG.deal_oper
  is '������Ա';
comment on column BF_TASK_LOG.deal_date
  is '��������';
comment on column BF_TASK_LOG.deal_time
  is '����ʱ��';
alter table BF_TASK_LOG
  add constraint PK_BF_TASK_LOG_LOG_ID primary key (LOG_ID);

prompt Creating BF_TASK_OWNER...
create table BF_TASK_OWNER
(
  key_id     VARCHAR2(32) not null,
  task_id    VARCHAR2(32) not null,
  task_name  VARCHAR2(200),
  task_icon  VARCHAR2(32),
  task_owner VARCHAR2(20) not null,
  seqno      INTEGER not null,
  menu_id    INTEGER not null,
  task_desc  VARCHAR2(500),
  show_flag  VARCHAR2(1),
  pakg_id    VARCHAR2(32),
  pakg_flag  VARCHAR2(1)
)
;
comment on table BF_TASK_OWNER
  is 'ƽ̨�� - ������������ʾ������ ƽ̨��-������������ʾ������';
comment on column BF_TASK_OWNER.key_id
  is '����ID';
comment on column BF_TASK_OWNER.task_id
  is '������';
comment on column BF_TASK_OWNER.task_name
  is '��������';
comment on column BF_TASK_OWNER.task_icon
  is '����ͼ��';
comment on column BF_TASK_OWNER.task_owner
  is '�����û�';
comment on column BF_TASK_OWNER.seqno
  is '����˳��';
comment on column BF_TASK_OWNER.menu_id
  is '�˵�ID';
comment on column BF_TASK_OWNER.task_desc
  is '��������';
comment on column BF_TASK_OWNER.show_flag
  is '��ʾ��־ 0��������ʾ 1���ڰ�����ʾ 2������ʾ ';
comment on column BF_TASK_OWNER.pakg_id
  is '������ID ���show_flag=1����ǿ�';
comment on column BF_TASK_OWNER.pakg_flag
  is '����� 0���ǰ�  1���ǰ�';
create unique index IDXU_BF_TASK_OWNER_TASK_ID on BF_TASK_OWNER (TASK_ID);
alter table BF_TASK_OWNER
  add constraint PK_BF_TASK_OWNER_KEY_ID primary key (KEY_ID);

prompt Creating BF_TASK_PARAM...
create table BF_TASK_PARAM
(
  task_id        VARCHAR2(32) not null,
  task_name      VARCHAR2(64) not null,
  menu_id        INTEGER,
  menu_name      VARCHAR2(64),
  rule_id        VARCHAR2(32),
  detail_menu_id VARCHAR2(64)
)
;
comment on table BF_TASK_PARAM
  is 'ƽ̨�� - ��������� ���˿�ݲ˵���Ϣ��';
comment on column BF_TASK_PARAM.task_id
  is '�ڵ�id';
comment on column BF_TASK_PARAM.task_name
  is '�ڵ�����';
comment on column BF_TASK_PARAM.menu_id
  is '�˵�ID Ϊ�ǣ����ʾֻ����ʾͳ������';
comment on column BF_TASK_PARAM.menu_name
  is '�˵�����';
comment on column BF_TASK_PARAM.rule_id
  is '������';
comment on column BF_TASK_PARAM.detail_menu_id
  is '��ϸ�˵�ID';
alter table BF_TASK_PARAM
  add constraint PK_BF_TASK_PARAM_TASK_ID primary key (TASK_ID);

prompt Creating BF_TASK_REGEX...
create table BF_TASK_REGEX
(
  rule_id        VARCHAR2(32) not null,
  task_id        VARCHAR2(32) not null,
  oper_flag      VARCHAR2(1),
  role_flag      VARCHAR2(1),
  org_flag       VARCHAR2(1),
  detail_flag    VARCHAR2(1),
  detail_menu_id VARCHAR2(64),
  inst_oper      VARCHAR2(20),
  inst_date      VARCHAR2(10),
  inst_time      VARCHAR2(10),
  modi_oper      VARCHAR2(20),
  modi_date      VARCHAR2(10),
  modi_time      VARCHAR2(10)
)
;
comment on table BF_TASK_REGEX
  is 'ƽ̨�� - ��������-���ƹ�������';
comment on column BF_TASK_REGEX.rule_id
  is '��������ID';
comment on column BF_TASK_REGEX.task_id
  is '�ڵ�';
comment on column BF_TASK_REGEX.oper_flag
  is '�Ƿ�ָ������ Y��ָ�����ˣ�N�����õ���';
comment on column BF_TASK_REGEX.role_flag
  is '�Ƿ��޶���ɫ Y���޶���ɫ��N�����޶�';
comment on column BF_TASK_REGEX.org_flag
  is '�Ƿ��޶����� Y���޶�������N�����޶�';
comment on column BF_TASK_REGEX.detail_flag
  is '�Ƿ������ҳ�� Y���ܽ������ҳ��N��ֻ���б�ҳ��';
comment on column BF_TASK_REGEX.detail_menu_id
  is '������ϸ��menuid';
comment on column BF_TASK_REGEX.inst_oper
  is '������';
comment on column BF_TASK_REGEX.inst_date
  is '��������';
comment on column BF_TASK_REGEX.inst_time
  is '����ʱ��';
comment on column BF_TASK_REGEX.modi_oper
  is '�޸���';
comment on column BF_TASK_REGEX.modi_date
  is '�޸�����';
comment on column BF_TASK_REGEX.modi_time
  is '�޸�ʱ��';
alter table BF_TASK_REGEX
  add constraint PK_BF_TASK_REGEX_RULE_ID primary key (RULE_ID);

prompt Creating BF_TASK_REGEX_SUB_INFO...
create table BF_TASK_REGEX_SUB_INFO
(
  rule_id      VARCHAR2(32) not null,
  limit_flag   VARCHAR2(1),
  limit_key_id VARCHAR2(32)
)
;
comment on table BF_TASK_REGEX_SUB_INFO
  is 'ƽ̨�� - ��������-���ƹ�������';
comment on column BF_TASK_REGEX_SUB_INFO.rule_id
  is '��������ID';
comment on column BF_TASK_REGEX_SUB_INFO.limit_flag
  is '�������� R:��ɫ U:�û� O:����';
comment on column BF_TASK_REGEX_SUB_INFO.limit_key_id
  is '��Ӧ����';
create index IDX_BTR_SUB_RULE_ID on BF_TASK_REGEX_SUB_INFO (RULE_ID, LIMIT_FLAG);

prompt Creating BF_USER...
create table BF_USER
(
  user_id            VARCHAR2(20) not null,
  user_name          VARCHAR2(64) not null,
  nick_name          VARCHAR2(64),
  user_pwd           VARCHAR2(32) not null,
  user_status        VARCHAR2(32) not null,
  org_id             VARCHAR2(10),
  cert_type          VARCHAR2(2),
  cert_no            VARCHAR2(32),
  mobile_phone       VARCHAR2(32),
  telephone          VARCHAR2(32),
  email              VARCHAR2(64),
  limit_ip           VARCHAR2(200),
  online_session_num INTEGER,
  lock_flag          VARCHAR2(32),
  lock_date          CHAR(8),
  lock_time          CHAR(6),
  login_num          INTEGER,
  last_login_ip      VARCHAR2(32),
  last_login_date    CHAR(8),
  last_login_time    CHAR(6),
  inst_date          CHAR(8),
  inst_time          CHAR(6),
  inst_oper          VARCHAR2(20),
  modi_date          CHAR(8),
  modi_time          CHAR(6),
  modi_oper          VARCHAR2(20),
  modi_pwd_date      CHAR(8),
  modi_pwd_time      CHAR(6)
)
;
comment on table BF_USER
  is 'ƽ̨�� - �û���';
comment on column BF_USER.user_id
  is '�û���';
comment on column BF_USER.user_name
  is '�û�����';
comment on column BF_USER.nick_name
  is '�ǳ�';
comment on column BF_USER.user_pwd
  is '�û����� �û����������MD5���ܴ���洢';
comment on column BF_USER.user_status
  is '�û� ״̬
1������
0��ͣ��';
comment on column BF_USER.org_id
  is '���� ��';
comment on column BF_USER.cert_type
  is '֤�� ����';
comment on column BF_USER.cert_no
  is 'CERT_NO';
comment on column BF_USER.mobile_phone
  is '�ƶ��绰';
comment on column BF_USER.telephone
  is '�绰����';
comment on column BF_USER.email
  is '��������';
comment on column BF_USER.limit_ip
  is '��¼����IP ֻ��ʹ�ø�IP����IP���¼��ip���ð�Ƕ��Ÿ���';
comment on column BF_USER.online_session_num
  is '���߻Ự��
��¼�ɹ���1���˳�ʱ��1���쳣������ɶ�ʱ����0����';
comment on column BF_USER.lock_flag
  is '������־';
comment on column BF_USER.lock_date
  is '��������';
comment on column BF_USER.lock_time
  is '����ʱ��';
comment on column BF_USER.login_num
  is '��¼���Դ���';
comment on column BF_USER.last_login_ip
  is '����¼IP';
comment on column BF_USER.last_login_date
  is '����¼����';
comment on column BF_USER.last_login_time
  is '����¼ʱ��';
comment on column BF_USER.inst_date
  is '��������';
comment on column BF_USER.inst_time
  is '����ʱ��';
comment on column BF_USER.inst_oper
  is '����������';
comment on column BF_USER.modi_date
  is '�޸�����';
comment on column BF_USER.modi_time
  is '�޸�ʱ��';
comment on column BF_USER.modi_oper
  is '�޸Ĳ�����';
comment on column BF_USER.modi_pwd_date
  is '���һ���޸����������';
comment on column BF_USER.modi_pwd_time
  is '���һ���޸������ʱ��';
alter table BF_USER
  add constraint PK_BF_USER primary key (USER_ID);

prompt Creating BF_USER_CFG...
create table BF_USER_CFG
(
  user_id     VARCHAR2(20) not null,
  param_code  VARCHAR2(32) not null,
  param_value VARCHAR2(200) not null
)
;
comment on table BF_USER_CFG
  is 'ƽ̨�� - �û�ѡ���';
comment on column BF_USER_CFG.user_id
  is '�û���';
comment on column BF_USER_CFG.param_code
  is '���� ����';
comment on column BF_USER_CFG.param_value
  is '����ֵ';
alter table BF_USER_CFG
  add constraint PK_BF_USER_CFG primary key (USER_ID, PARAM_CODE);

prompt Creating BF_USER_ROLE...
create table BF_USER_ROLE
(
  user_id   VARCHAR2(20) not null,
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
  is 'ƽ̨�� - �û����ɫ��ϵ��';
comment on column BF_USER_ROLE.user_id
  is '�û���';
comment on column BF_USER_ROLE.role_id
  is '��ɫ���';
comment on column BF_USER_ROLE.inst_date
  is '��������';
comment on column BF_USER_ROLE.inst_time
  is '����ʱ��';
comment on column BF_USER_ROLE.inst_oper
  is '����������';
comment on column BF_USER_ROLE.modi_date
  is '�޸�����';
comment on column BF_USER_ROLE.modi_time
  is '�޸�ʱ��';
comment on column BF_USER_ROLE.modi_oper
  is '�޸Ĳ�����';
alter table BF_USER_ROLE
  add constraint PK_BF_USER_ROLE_USER_ID primary key (USER_ID, ROLE_ID);

prompt Loading BF_ATTACH...
insert into BF_ATTACH (attach_id, doc_id, bussiness_key)
values ('20160707104424364536102388461511', '20160707104419978326842788091349', null);
insert into BF_ATTACH (attach_id, doc_id, bussiness_key)
values ('20160707105030073512730050995224', '20160707105024334119409912270442', null);
insert into BF_ATTACH (attach_id, doc_id, bussiness_key)
values ('20160707105314453040427468599200', '20160707105309989339792159269881', null);
insert into BF_ATTACH (attach_id, doc_id, bussiness_key)
values ('20160707105712001875917807502025', '20160707105708301693977303229732', null);
insert into BF_ATTACH (attach_id, doc_id, bussiness_key)
values ('20160714212006947711818969762419', '20160714212002173047782962029238', null);
insert into BF_ATTACH (attach_id, doc_id, bussiness_key)
values ('20160921150917337423475548803530', '20160921150912068622568945234465', null);
commit;
prompt 6 records loaded
prompt Loading BF_DAY_INFO...
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161005', '2016', '9 ', '5 ', '���³���', '������', 'ˮƿ��', '20161001', '20161031', 279, 5, '1', '�����', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161006', '2016', '9 ', '6 ', '���³���', '������', 'ˮƿ��', '20161001', '20161031', 280, 6, '1', '�����', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161007', '2016', '9 ', '7 ', '���³���', '������', 'ˮƿ��', '20161001', '20161031', 281, 7, '1', '�����', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161008', '2016', '9 ', '8 ', '���³���', '������', 'ˮƿ��', '20161001', '20161031', 282, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161009', '2016', '9 ', '9 ', '���³���', '������', 'ˮƿ��', '20161001', '20161031', 283, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161010', '2016', '9 ', '10', '���³�ʮ', '����һ', 'ˮƿ��', '20161001', '20161031', 284, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161011', '2016', '9 ', '11', '����ʮһ', '���ڶ�', 'ˮƿ��', '20161001', '20161031', 285, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161012', '2016', '9 ', '12', '����ʮ��', '������', 'ˮƿ��', '20161001', '20161031', 286, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161013', '2016', '9 ', '13', '����ʮ��', '������', 'ˮƿ��', '20161001', '20161031', 287, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161014', '2016', '9 ', '14', '����ʮ��', '������', 'ˮƿ��', '20161001', '20161031', 288, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161015', '2016', '9 ', '15', '����ʮ��', '������', 'ˮƿ��', '20161001', '20161031', 289, 15, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161016', '2016', '9 ', '16', '����ʮ��', '������', 'ˮƿ��', '20161001', '20161031', 290, 16, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161017', '2016', '9 ', '17', '����ʮ��', '����һ', 'ˮƿ��', '20161001', '20161031', 291, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161018', '2016', '9 ', '18', '����ʮ��', '���ڶ�', 'ˮƿ��', '20161001', '20161031', 292, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161019', '2016', '9 ', '19', '����ʮ��', '������', 'ˮƿ��', '20161001', '20161031', 293, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161020', '2016', '9 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20161001', '20161031', 294, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161021', '2016', '9 ', '21', '����إһ', '������', 'ˮƿ��', '20161001', '20161031', 295, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161022', '2016', '9 ', '22', '����إ��', '������', 'ˮƿ��', '20161001', '20161031', 296, 22, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161023', '2016', '9 ', '23', '����إ��', '������', 'ˮƿ��', '20161001', '20161031', 297, 23, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161024', '2016', '9 ', '24', '����إ��', '����һ', 'ˮƿ��', '20161001', '20161031', 298, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161025', '2016', '9 ', '25', '����إ��', '���ڶ�', 'ˮƿ��', '20161001', '20161031', 299, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161026', '2016', '9 ', '26', '����إ��', '������', 'ˮƿ��', '20161001', '20161031', 300, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161027', '2016', '9 ', '27', '����إ��', '������', 'ˮƿ��', '20161001', '20161031', 301, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161028', '2016', '9 ', '28', '����إ��', '������', 'ˮƿ��', '20161001', '20161031', 302, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161029', '2016', '9 ', '29', '����إ��', '������', 'ˮƿ��', '20161001', '20161031', 303, 29, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161030', '2016', '9 ', '30', '������ʮ', '������', 'ˮƿ��', '20161001', '20161031', 304, 30, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161031', '2016', '10', '1 ', 'ʮ�³�һ', '����һ', 'ˮƿ��', '20161001', '20161031', 305, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161101', '2016', '10', '2 ', 'ʮ�³���', '���ڶ�', 'ˮƿ��', '20161101', '20161130', 306, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161102', '2016', '10', '3 ', 'ʮ�³���', '������', 'ˮƿ��', '20161101', '20161130', 307, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161103', '2016', '10', '4 ', 'ʮ�³���', '������', 'ˮƿ��', '20161101', '20161130', 308, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161104', '2016', '10', '5 ', 'ʮ�³���', '������', 'ˮƿ��', '20161101', '20161130', 309, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161105', '2016', '10', '6 ', 'ʮ�³���', '������', 'ˮƿ��', '20161101', '20161130', 310, 5, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161106', '2016', '10', '7 ', 'ʮ�³���', '������', 'ˮƿ��', '20161101', '20161130', 311, 6, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161107', '2016', '10', '8 ', 'ʮ�³���', '����һ', 'ˮƿ��', '20161101', '20161130', 312, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161108', '2016', '10', '9 ', 'ʮ�³���', '���ڶ�', 'ˮƿ��', '20161101', '20161130', 313, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161109', '2016', '10', '10', 'ʮ�³�ʮ', '������', 'ˮƿ��', '20161101', '20161130', 314, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161110', '2016', '10', '11', 'ʮ��ʮһ', '������', 'ˮƿ��', '20161101', '20161130', 315, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161111', '2016', '10', '12', 'ʮ��ʮ��', '������', 'ˮƿ��', '20161101', '20161130', 316, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161112', '2016', '10', '13', 'ʮ��ʮ��', '������', 'ˮƿ��', '20161101', '20161130', 317, 12, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161113', '2016', '10', '14', 'ʮ��ʮ��', '������', 'ˮƿ��', '20161101', '20161130', 318, 13, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161114', '2016', '10', '15', 'ʮ��ʮ��', '����һ', 'ˮƿ��', '20161101', '20161130', 319, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161115', '2016', '10', '16', 'ʮ��ʮ��', '���ڶ�', 'ˮƿ��', '20161101', '20161130', 320, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161116', '2016', '10', '17', 'ʮ��ʮ��', '������', 'ˮƿ��', '20161101', '20161130', 321, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161117', '2016', '10', '18', 'ʮ��ʮ��', '������', 'ˮƿ��', '20161101', '20161130', 322, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161118', '2016', '10', '19', 'ʮ��ʮ��', '������', 'ˮƿ��', '20161101', '20161130', 323, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161119', '2016', '10', '20', 'ʮ�¶�ʮ', '������', 'ˮƿ��', '20161101', '20161130', 324, 19, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161120', '2016', '10', '21', 'ʮ��إһ', '������', 'ˮƿ��', '20161101', '20161130', 325, 20, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161121', '2016', '10', '22', 'ʮ��إ��', '����һ', 'ˮƿ��', '20161101', '20161130', 326, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161122', '2016', '10', '23', 'ʮ��إ��', '���ڶ�', 'ˮƿ��', '20161101', '20161130', 327, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161123', '2016', '10', '24', 'ʮ��إ��', '������', 'ˮƿ��', '20161101', '20161130', 328, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161124', '2016', '10', '25', 'ʮ��إ��', '������', 'ˮƿ��', '20161101', '20161130', 329, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161125', '2016', '10', '26', 'ʮ��إ��', '������', 'ˮƿ��', '20161101', '20161130', 330, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161126', '2016', '10', '27', 'ʮ��إ��', '������', 'ˮƿ��', '20161101', '20161130', 331, 26, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161127', '2016', '10', '28', 'ʮ��إ��', '������', 'ˮƿ��', '20161101', '20161130', 332, 27, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161128', '2016', '10', '29', 'ʮ��إ��', '����һ', 'ˮƿ��', '20161101', '20161130', 333, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161129', '2016', '11', '1 ', 'ʮһ�³�һ', '���ڶ�', 'ˮƿ��', '20161101', '20161130', 334, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161130', '2016', '11', '2 ', 'ʮһ�³���', '������', 'ˮƿ��', '20161101', '20161130', 335, 30, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161201', '2016', '11', '3 ', 'ʮһ�³���', '������', 'ˮƿ��', '20161201', '20161231', 336, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161202', '2016', '11', '4 ', 'ʮһ�³���', '������', 'ˮƿ��', '20161201', '20161231', 337, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161203', '2016', '11', '5 ', 'ʮһ�³���', '������', 'ˮƿ��', '20161201', '20161231', 338, 3, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161204', '2016', '11', '6 ', 'ʮһ�³���', '������', 'ˮƿ��', '20161201', '20161231', 339, 4, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161205', '2016', '11', '7 ', 'ʮһ�³���', '����һ', 'ˮƿ��', '20161201', '20161231', 340, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161206', '2016', '11', '8 ', 'ʮһ�³���', '���ڶ�', 'ˮƿ��', '20161201', '20161231', 341, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161207', '2016', '11', '9 ', 'ʮһ�³���', '������', 'ˮƿ��', '20161201', '20161231', 342, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161208', '2016', '11', '10', 'ʮһ�³�ʮ', '������', 'ˮƿ��', '20161201', '20161231', 343, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161209', '2016', '11', '11', 'ʮһ��ʮһ', '������', 'ˮƿ��', '20161201', '20161231', 344, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161210', '2016', '11', '12', 'ʮһ��ʮ��', '������', 'ˮƿ��', '20161201', '20161231', 345, 10, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161211', '2016', '11', '13', 'ʮһ��ʮ��', '������', 'ˮƿ��', '20161201', '20161231', 346, 11, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161212', '2016', '11', '14', 'ʮһ��ʮ��', '����һ', 'ˮƿ��', '20161201', '20161231', 347, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161213', '2016', '11', '15', 'ʮһ��ʮ��', '���ڶ�', 'ˮƿ��', '20161201', '20161231', 348, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161214', '2016', '11', '16', 'ʮһ��ʮ��', '������', 'ˮƿ��', '20161201', '20161231', 349, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161215', '2016', '11', '17', 'ʮһ��ʮ��', '������', 'ˮƿ��', '20161201', '20161231', 350, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161216', '2016', '11', '18', 'ʮһ��ʮ��', '������', 'ˮƿ��', '20161201', '20161231', 351, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161217', '2016', '11', '19', 'ʮһ��ʮ��', '������', 'ˮƿ��', '20161201', '20161231', 352, 17, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161218', '2016', '11', '20', 'ʮһ�¶�ʮ', '������', 'ˮƿ��', '20161201', '20161231', 353, 18, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161219', '2016', '11', '21', 'ʮһ��إһ', '����һ', 'ˮƿ��', '20161201', '20161231', 354, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161220', '2016', '11', '22', 'ʮһ��إ��', '���ڶ�', 'ˮƿ��', '20161201', '20161231', 355, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161221', '2016', '11', '23', 'ʮһ��إ��', '������', 'ˮƿ��', '20161201', '20161231', 356, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161222', '2016', '11', '24', 'ʮһ��إ��', '������', 'Ħ����', '20161201', '20161231', 357, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161223', '2016', '11', '25', 'ʮһ��إ��', '������', 'Ħ����', '20161201', '20161231', 358, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161224', '2016', '11', '26', 'ʮһ��إ��', '������', 'Ħ����', '20161201', '20161231', 359, 24, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161225', '2016', '11', '27', 'ʮһ��إ��', '������', 'Ħ����', '20161201', '20161231', 360, 25, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161226', '2016', '11', '28', 'ʮһ��إ��', '����һ', 'Ħ����', '20161201', '20161231', 361, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161227', '2016', '11', '29', 'ʮһ��إ��', '���ڶ�', 'Ħ����', '20161201', '20161231', 362, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161228', '2016', '11', '30', 'ʮһ����ʮ', '������', 'Ħ����', '20161201', '20161231', 363, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161229', '2016', '12', '1 ', '���³�һ', '������', 'Ħ����', '20161201', '20161231', 364, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161230', '2016', '12', '2 ', '���³���', '������', 'Ħ����', '20161201', '20161231', 365, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161231', '2016', '12', '3 ', '���³���', '������', 'Ħ����', '20161201', '20161231', 366, 31, '1', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170101', '2016', '12', '4 ', '���³���', '������', 'Ħ����', '20170101', '20170131', 1, 1, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170102', '2016', '12', '5 ', '���³���', '����һ', 'Ħ����', '20170101', '20170131', 2, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170103', '2016', '12', '6 ', '���³���', '���ڶ�', 'Ħ����', '20170101', '20170131', 3, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170104', '2016', '12', '7 ', '���³���', '������', 'Ħ����', '20170101', '20170131', 4, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170105', '2016', '12', '8 ', '���³���', '������', 'Ħ����', '20170101', '20170131', 5, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160405', '2016', '2 ', '28', '����إ��', '���ڶ�', 'ˮƿ��', '20160401', '20160430', 96, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160406', '2016', '2 ', '29', '����إ��', '������', 'ˮƿ��', '20160401', '20160430', 97, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160407', '2016', '3 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20160401', '20160430', 98, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160408', '2016', '3 ', '2 ', '���³���', '������', 'ˮƿ��', '20160401', '20160430', 99, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160409', '2016', '3 ', '3 ', '���³���', '������', 'ˮƿ��', '20160401', '20160430', 100, 9, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160410', '2016', '3 ', '4 ', '���³���', '������', 'ˮƿ��', '20160401', '20160430', 101, 10, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160411', '2016', '3 ', '5 ', '���³���', '����һ', 'ˮƿ��', '20160401', '20160430', 102, 11, '0', null, 'N');
commit;
prompt 100 records committed...
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160412', '2016', '3 ', '6 ', '���³���', '���ڶ�', 'ˮƿ��', '20160401', '20160430', 103, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160413', '2016', '3 ', '7 ', '���³���', '������', 'ˮƿ��', '20160401', '20160430', 104, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160414', '2016', '3 ', '8 ', '���³���', '������', 'ˮƿ��', '20160401', '20160430', 105, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160415', '2016', '3 ', '9 ', '���³���', '������', 'ˮƿ��', '20160401', '20160430', 106, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160416', '2016', '3 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20160401', '20160430', 107, 16, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160417', '2016', '3 ', '11', '����ʮһ', '������', 'ˮƿ��', '20160401', '20160430', 108, 17, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160418', '2016', '3 ', '12', '����ʮ��', '����һ', 'ˮƿ��', '20160401', '20160430', 109, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160419', '2016', '3 ', '13', '����ʮ��', '���ڶ�', 'ˮƿ��', '20160401', '20160430', 110, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160420', '2016', '3 ', '14', '����ʮ��', '������', 'ˮƿ��', '20160401', '20160430', 111, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160421', '2016', '3 ', '15', '����ʮ��', '������', 'ˮƿ��', '20160401', '20160430', 112, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160422', '2016', '3 ', '16', '����ʮ��', '������', 'ˮƿ��', '20160401', '20160430', 113, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160423', '2016', '3 ', '17', '����ʮ��', '������', 'ˮƿ��', '20160401', '20160430', 114, 23, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160424', '2016', '3 ', '18', '����ʮ��', '������', 'ˮƿ��', '20160401', '20160430', 115, 24, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160425', '2016', '3 ', '19', '����ʮ��', '����һ', 'ˮƿ��', '20160401', '20160430', 116, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160426', '2016', '3 ', '20', '���¶�ʮ', '���ڶ�', 'ˮƿ��', '20160401', '20160430', 117, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160427', '2016', '3 ', '21', '����إһ', '������', 'ˮƿ��', '20160401', '20160430', 118, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160428', '2016', '3 ', '22', '����إ��', '������', 'ˮƿ��', '20160401', '20160430', 119, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160429', '2016', '3 ', '23', '����إ��', '������', 'ˮƿ��', '20160401', '20160430', 120, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160430', '2016', '3 ', '24', '����إ��', '������', 'ˮƿ��', '20160401', '20160430', 121, 30, '1', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160501', '2016', '3 ', '25', '����إ��', '������', 'ˮƿ��', '20160501', '20160531', 122, 1, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160502', '2016', '3 ', '26', '����إ��', '����һ', 'ˮƿ��', '20160501', '20160531', 123, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160503', '2016', '3 ', '27', '����إ��', '���ڶ�', 'ˮƿ��', '20160501', '20160531', 124, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160504', '2016', '3 ', '28', '����إ��', '������', 'ˮƿ��', '20160501', '20160531', 125, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160505', '2016', '3 ', '29', '����إ��', '������', 'ˮƿ��', '20160501', '20160531', 126, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160506', '2016', '3 ', '30', '������ʮ', '������', 'ˮƿ��', '20160501', '20160531', 127, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160507', '2016', '4 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20160501', '20160531', 128, 7, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160508', '2016', '4 ', '2 ', '���³���', '������', 'ˮƿ��', '20160501', '20160531', 129, 8, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160509', '2016', '4 ', '3 ', '���³���', '����һ', 'ˮƿ��', '20160501', '20160531', 130, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160510', '2016', '4 ', '4 ', '���³���', '���ڶ�', 'ˮƿ��', '20160501', '20160531', 131, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160511', '2016', '4 ', '5 ', '���³���', '������', 'ˮƿ��', '20160501', '20160531', 132, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160512', '2016', '4 ', '6 ', '���³���', '������', 'ˮƿ��', '20160501', '20160531', 133, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160513', '2016', '4 ', '7 ', '���³���', '������', 'ˮƿ��', '20160501', '20160531', 134, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160514', '2016', '4 ', '8 ', '���³���', '������', 'ˮƿ��', '20160501', '20160531', 135, 14, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160515', '2016', '4 ', '9 ', '���³���', '������', 'ˮƿ��', '20160501', '20160531', 136, 15, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160516', '2016', '4 ', '10', '���³�ʮ', '����һ', 'ˮƿ��', '20160501', '20160531', 137, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160517', '2016', '4 ', '11', '����ʮһ', '���ڶ�', 'ˮƿ��', '20160501', '20160531', 138, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160518', '2016', '4 ', '12', '����ʮ��', '������', 'ˮƿ��', '20160501', '20160531', 139, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160519', '2016', '4 ', '13', '����ʮ��', '������', 'ˮƿ��', '20160501', '20160531', 140, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160520', '2016', '4 ', '14', '����ʮ��', '������', 'ˮƿ��', '20160501', '20160531', 141, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160521', '2016', '4 ', '15', '����ʮ��', '������', 'ˮƿ��', '20160501', '20160531', 142, 21, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160522', '2016', '4 ', '16', '����ʮ��', '������', 'ˮƿ��', '20160501', '20160531', 143, 22, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160523', '2016', '4 ', '17', '����ʮ��', '����һ', 'ˮƿ��', '20160501', '20160531', 144, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160524', '2016', '4 ', '18', '����ʮ��', '���ڶ�', 'ˮƿ��', '20160501', '20160531', 145, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160525', '2016', '4 ', '19', '����ʮ��', '������', 'ˮƿ��', '20160501', '20160531', 146, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160526', '2016', '4 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20160501', '20160531', 147, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160527', '2016', '4 ', '21', '����إһ', '������', 'ˮƿ��', '20160501', '20160531', 148, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160528', '2016', '4 ', '22', '����إ��', '������', 'ˮƿ��', '20160501', '20160531', 149, 28, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160529', '2016', '4 ', '23', '����إ��', '������', 'ˮƿ��', '20160501', '20160531', 150, 29, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160530', '2016', '4 ', '24', '����إ��', '����һ', 'ˮƿ��', '20160501', '20160531', 151, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160531', '2016', '4 ', '25', '����إ��', '���ڶ�', 'ˮƿ��', '20160501', '20160531', 152, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160601', '2016', '4 ', '26', '����إ��', '������', 'ˮƿ��', '20160601', '20160630', 153, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160602', '2016', '4 ', '27', '����إ��', '������', 'ˮƿ��', '20160601', '20160630', 154, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160603', '2016', '4 ', '28', '����إ��', '������', 'ˮƿ��', '20160601', '20160630', 155, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160604', '2016', '4 ', '29', '����إ��', '������', 'ˮƿ��', '20160601', '20160630', 156, 4, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160605', '2016', '5 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20160601', '20160630', 157, 5, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160606', '2016', '5 ', '2 ', '���³���', '����һ', 'ˮƿ��', '20160601', '20160630', 158, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160607', '2016', '5 ', '3 ', '���³���', '���ڶ�', 'ˮƿ��', '20160601', '20160630', 159, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160608', '2016', '5 ', '4 ', '���³���', '������', 'ˮƿ��', '20160601', '20160630', 160, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160609', '2016', '5 ', '5 ', '���³���', '������', 'ˮƿ��', '20160601', '20160630', 161, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160610', '2016', '5 ', '6 ', '���³���', '������', 'ˮƿ��', '20160601', '20160630', 162, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160611', '2016', '5 ', '7 ', '���³���', '������', 'ˮƿ��', '20160601', '20160630', 163, 11, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160612', '2016', '5 ', '8 ', '���³���', '������', 'ˮƿ��', '20160601', '20160630', 164, 12, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160613', '2016', '5 ', '9 ', '���³���', '����һ', 'ˮƿ��', '20160601', '20160630', 165, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160614', '2016', '5 ', '10', '���³�ʮ', '���ڶ�', 'ˮƿ��', '20160601', '20160630', 166, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160615', '2016', '5 ', '11', '����ʮһ', '������', 'ˮƿ��', '20160601', '20160630', 167, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160616', '2016', '5 ', '12', '����ʮ��', '������', 'ˮƿ��', '20160601', '20160630', 168, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160617', '2016', '5 ', '13', '����ʮ��', '������', 'ˮƿ��', '20160601', '20160630', 169, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160618', '2016', '5 ', '14', '����ʮ��', '������', 'ˮƿ��', '20160601', '20160630', 170, 18, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160619', '2016', '5 ', '15', '����ʮ��', '������', 'ˮƿ��', '20160601', '20160630', 171, 19, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160620', '2016', '5 ', '16', '����ʮ��', '����һ', 'ˮƿ��', '20160601', '20160630', 172, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160621', '2016', '5 ', '17', '����ʮ��', '���ڶ�', 'ˮƿ��', '20160601', '20160630', 173, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160622', '2016', '5 ', '18', '����ʮ��', '������', 'ˮƿ��', '20160601', '20160630', 174, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160623', '2016', '5 ', '19', '����ʮ��', '������', 'ˮƿ��', '20160601', '20160630', 175, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160624', '2016', '5 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20160601', '20160630', 176, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160625', '2016', '5 ', '21', '����إһ', '������', 'ˮƿ��', '20160601', '20160630', 177, 25, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160626', '2016', '5 ', '22', '����إ��', '������', 'ˮƿ��', '20160601', '20160630', 178, 26, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160627', '2016', '5 ', '23', '����إ��', '����һ', 'ˮƿ��', '20160601', '20160630', 179, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160628', '2016', '5 ', '24', '����إ��', '���ڶ�', 'ˮƿ��', '20160601', '20160630', 180, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160629', '2016', '5 ', '25', '����إ��', '������', 'ˮƿ��', '20160601', '20160630', 181, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160630', '2016', '5 ', '26', '����إ��', '������', 'ˮƿ��', '20160601', '20160630', 182, 30, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160701', '2016', '5 ', '27', '����إ��', '������', 'ˮƿ��', '20160701', '20160731', 183, 1, '1', '���Խ���', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160702', '2016', '5 ', '28', '����إ��', '������', 'ˮƿ��', '20160701', '20160731', 184, 2, '1', '����2', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160703', '2016', '5 ', '29', '����إ��', '������', 'ˮƿ��', '20160701', '20160731', 185, 3, '1', '����2', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160704', '2016', '6 ', '1 ', '���³�һ', '����һ', 'ˮƿ��', '20160701', '20160731', 186, 4, '1', '����2', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170106', '2016', '12', '9 ', '���³���', '������', 'Ħ����', '20170101', '20170131', 6, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170107', '2016', '12', '10', '���³�ʮ', '������', 'Ħ����', '20170101', '20170131', 7, 7, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170108', '2016', '12', '11', '����ʮһ', '������', 'Ħ����', '20170101', '20170131', 8, 8, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170109', '2016', '12', '12', '����ʮ��', '����һ', 'Ħ����', '20170101', '20170131', 9, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170110', '2016', '12', '13', '����ʮ��', '���ڶ�', 'Ħ����', '20170101', '20170131', 10, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170111', '2016', '12', '14', '����ʮ��', '������', 'Ħ����', '20170101', '20170131', 11, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170112', '2016', '12', '15', '����ʮ��', '������', 'Ħ����', '20170101', '20170131', 12, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170113', '2016', '12', '16', '����ʮ��', '������', 'Ħ����', '20170101', '20170131', 13, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170114', '2016', '12', '17', '����ʮ��', '������', 'Ħ����', '20170101', '20170131', 14, 14, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170115', '2016', '12', '18', '����ʮ��', '������', 'Ħ����', '20170101', '20170131', 15, 15, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170116', '2016', '12', '19', '����ʮ��', '����һ', 'Ħ����', '20170101', '20170131', 16, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170117', '2016', '12', '20', '���¶�ʮ', '���ڶ�', 'Ħ����', '20170101', '20170131', 17, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170118', '2016', '12', '21', '����إһ', '������', 'Ħ����', '20170101', '20170131', 18, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170119', '2016', '12', '22', '����إ��', '������', 'Ħ����', '20170101', '20170131', 19, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170120', '2016', '12', '23', '����إ��', '������', 'Ħ����', '20170101', '20170131', 20, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170121', '2016', '12', '24', '����إ��', '������', 'Ħ����', '20170101', '20170131', 21, 21, '1', null, 'N');
commit;
prompt 200 records committed...
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170122', '2016', '12', '25', '����إ��', '������', 'ˮƿ��', '20170101', '20170131', 22, 22, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170123', '2016', '12', '26', '����إ��', '����һ', 'ˮƿ��', '20170101', '20170131', 23, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170124', '2016', '12', '27', '����إ��', '���ڶ�', 'ˮƿ��', '20170101', '20170131', 24, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170125', '2016', '12', '28', '����إ��', '������', 'ˮƿ��', '20170101', '20170131', 25, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170126', '2016', '12', '29', '����إ��', '������', 'ˮƿ��', '20170101', '20170131', 26, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170127', '2016', '12', '30', '������ʮ', '������', 'ˮƿ��', '20170101', '20170131', 27, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170128', '2017', '1 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20170101', '20170131', 28, 28, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170129', '2017', '1 ', '2 ', '���³���', '������', 'ˮƿ��', '20170101', '20170131', 29, 29, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170130', '2017', '1 ', '3 ', '���³���', '����һ', 'ˮƿ��', '20170101', '20170131', 30, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170131', '2017', '1 ', '4 ', '���³���', '���ڶ�', 'ˮƿ��', '20170101', '20170131', 31, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170201', '2017', '1 ', '5 ', '���³���', '������', 'ˮƿ��', '20170201', '20170228', 32, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170202', '2017', '1 ', '6 ', '���³���', '������', 'ˮƿ��', '20170201', '20170228', 33, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170203', '2017', '1 ', '7 ', '���³���', '������', 'ˮƿ��', '20170201', '20170228', 34, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170204', '2017', '1 ', '8 ', '���³���', '������', 'ˮƿ��', '20170201', '20170228', 35, 4, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170205', '2017', '1 ', '9 ', '���³���', '������', 'ˮƿ��', '20170201', '20170228', 36, 5, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170206', '2017', '1 ', '10', '���³�ʮ', '����һ', 'ˮƿ��', '20170201', '20170228', 37, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170207', '2017', '1 ', '11', '����ʮһ', '���ڶ�', 'ˮƿ��', '20170201', '20170228', 38, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170208', '2017', '1 ', '12', '����ʮ��', '������', 'ˮƿ��', '20170201', '20170228', 39, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170209', '2017', '1 ', '13', '����ʮ��', '������', 'ˮƿ��', '20170201', '20170228', 40, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170210', '2017', '1 ', '14', '����ʮ��', '������', 'ˮƿ��', '20170201', '20170228', 41, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170211', '2017', '1 ', '15', '����ʮ��', '������', 'ˮƿ��', '20170201', '20170228', 42, 11, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170212', '2017', '1 ', '16', '����ʮ��', '������', 'ˮƿ��', '20170201', '20170228', 43, 12, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170213', '2017', '1 ', '17', '����ʮ��', '����һ', 'ˮƿ��', '20170201', '20170228', 44, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170214', '2017', '1 ', '18', '����ʮ��', '���ڶ�', 'ˮƿ��', '20170201', '20170228', 45, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170215', '2017', '1 ', '19', '����ʮ��', '������', 'ˮƿ��', '20170201', '20170228', 46, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170216', '2017', '1 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20170201', '20170228', 47, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170217', '2017', '1 ', '21', '����إһ', '������', 'ˮƿ��', '20170201', '20170228', 48, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170218', '2017', '1 ', '22', '����إ��', '������', 'ˮƿ��', '20170201', '20170228', 49, 18, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170219', '2017', '1 ', '23', '����إ��', '������', 'ˮƿ��', '20170201', '20170228', 50, 19, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170220', '2017', '1 ', '24', '����إ��', '����һ', 'ˮƿ��', '20170201', '20170228', 51, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170221', '2017', '1 ', '25', '����إ��', '���ڶ�', 'ˮƿ��', '20170201', '20170228', 52, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170222', '2017', '1 ', '26', '����إ��', '������', 'ˮƿ��', '20170201', '20170228', 53, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170223', '2017', '1 ', '27', '����إ��', '������', 'ˮƿ��', '20170201', '20170228', 54, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170224', '2017', '1 ', '28', '����إ��', '������', 'ˮƿ��', '20170201', '20170228', 55, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170225', '2017', '1 ', '29', '����إ��', '������', 'ˮƿ��', '20170201', '20170228', 56, 25, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170226', '2017', '2 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20170201', '20170228', 57, 26, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170227', '2017', '2 ', '2 ', '���³���', '����һ', 'ˮƿ��', '20170201', '20170228', 58, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170228', '2017', '2 ', '3 ', '���³���', '���ڶ�', 'ˮƿ��', '20170201', '20170228', 59, 28, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170301', '2017', '2 ', '4 ', '���³���', '������', 'ˮƿ��', '20170301', '20170331', 60, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170302', '2017', '2 ', '5 ', '���³���', '������', 'ˮƿ��', '20170301', '20170331', 61, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170303', '2017', '2 ', '6 ', '���³���', '������', 'ˮƿ��', '20170301', '20170331', 62, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170304', '2017', '2 ', '7 ', '���³���', '������', 'ˮƿ��', '20170301', '20170331', 63, 4, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170305', '2017', '2 ', '8 ', '���³���', '������', 'ˮƿ��', '20170301', '20170331', 64, 5, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170306', '2017', '2 ', '9 ', '���³���', '����һ', 'ˮƿ��', '20170301', '20170331', 65, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170307', '2017', '2 ', '10', '���³�ʮ', '���ڶ�', 'ˮƿ��', '20170301', '20170331', 66, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170308', '2017', '2 ', '11', '����ʮһ', '������', 'ˮƿ��', '20170301', '20170331', 67, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170309', '2017', '2 ', '12', '����ʮ��', '������', 'ˮƿ��', '20170301', '20170331', 68, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170310', '2017', '2 ', '13', '����ʮ��', '������', 'ˮƿ��', '20170301', '20170331', 69, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170311', '2017', '2 ', '14', '����ʮ��', '������', 'ˮƿ��', '20170301', '20170331', 70, 11, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170312', '2017', '2 ', '15', '����ʮ��', '������', 'ˮƿ��', '20170301', '20170331', 71, 12, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170313', '2017', '2 ', '16', '����ʮ��', '����һ', 'ˮƿ��', '20170301', '20170331', 72, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170314', '2017', '2 ', '17', '����ʮ��', '���ڶ�', 'ˮƿ��', '20170301', '20170331', 73, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170315', '2017', '2 ', '18', '����ʮ��', '������', 'ˮƿ��', '20170301', '20170331', 74, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170316', '2017', '2 ', '19', '����ʮ��', '������', 'ˮƿ��', '20170301', '20170331', 75, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170317', '2017', '2 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20170301', '20170331', 76, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170318', '2017', '2 ', '21', '����إһ', '������', 'ˮƿ��', '20170301', '20170331', 77, 18, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170319', '2017', '2 ', '22', '����إ��', '������', 'ˮƿ��', '20170301', '20170331', 78, 19, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170320', '2017', '2 ', '23', '����إ��', '����һ', 'ˮƿ��', '20170301', '20170331', 79, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170321', '2017', '2 ', '24', '����إ��', '���ڶ�', 'ˮƿ��', '20170301', '20170331', 80, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170322', '2017', '2 ', '25', '����إ��', '������', 'ˮƿ��', '20170301', '20170331', 81, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170323', '2017', '2 ', '26', '����إ��', '������', 'ˮƿ��', '20170301', '20170331', 82, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170324', '2017', '2 ', '27', '����إ��', '������', 'ˮƿ��', '20170301', '20170331', 83, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170325', '2017', '2 ', '28', '����إ��', '������', 'ˮƿ��', '20170301', '20170331', 84, 25, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170326', '2017', '2 ', '29', '����إ��', '������', 'ˮƿ��', '20170301', '20170331', 85, 26, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170327', '2017', '2 ', '30', '������ʮ', '����һ', 'ˮƿ��', '20170301', '20170331', 86, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170328', '2017', '3 ', '1 ', '���³�һ', '���ڶ�', 'ˮƿ��', '20170301', '20170331', 87, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170329', '2017', '3 ', '2 ', '���³���', '������', 'ˮƿ��', '20170301', '20170331', 88, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170330', '2017', '3 ', '3 ', '���³���', '������', 'ˮƿ��', '20170301', '20170331', 89, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170331', '2017', '3 ', '4 ', '���³���', '������', 'ˮƿ��', '20170301', '20170331', 90, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170401', '2017', '3 ', '5 ', '���³���', '������', 'ˮƿ��', '20170401', '20170430', 91, 1, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170402', '2017', '3 ', '6 ', '���³���', '������', 'ˮƿ��', '20170401', '20170430', 92, 2, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170403', '2017', '3 ', '7 ', '���³���', '����һ', 'ˮƿ��', '20170401', '20170430', 93, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170404', '2017', '3 ', '8 ', '���³���', '���ڶ�', 'ˮƿ��', '20170401', '20170430', 94, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170405', '2017', '3 ', '9 ', '���³���', '������', 'ˮƿ��', '20170401', '20170430', 95, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170406', '2017', '3 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20170401', '20170430', 96, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170407', '2017', '3 ', '11', '����ʮһ', '������', 'ˮƿ��', '20170401', '20170430', 97, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170408', '2017', '3 ', '12', '����ʮ��', '������', 'ˮƿ��', '20170401', '20170430', 98, 8, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160101', '2015', '11', '22', 'ʮһ��إ��', '������', 'Ħ����', '20160101', '20160131', 1, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160102', '2015', '11', '23', 'ʮһ��إ��', '������', 'Ħ����', '20160101', '20160131', 2, 2, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160103', '2015', '11', '24', 'ʮһ��إ��', '������', 'Ħ����', '20160101', '20160131', 3, 3, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160104', '2015', '11', '25', 'ʮһ��إ��', '����һ', 'Ħ����', '20160101', '20160131', 4, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160105', '2015', '11', '26', 'ʮһ��إ��', '���ڶ�', 'Ħ����', '20160101', '20160131', 5, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160106', '2015', '11', '27', 'ʮһ��إ��', '������', 'Ħ����', '20160101', '20160131', 6, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160107', '2015', '11', '28', 'ʮһ��إ��', '������', 'Ħ����', '20160101', '20160131', 7, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160108', '2015', '11', '29', 'ʮһ��إ��', '������', 'Ħ����', '20160101', '20160131', 8, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160109', '2015', '11', '30', 'ʮһ����ʮ', '������', 'Ħ����', '20160101', '20160131', 9, 9, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160110', '2015', '12', '1 ', '���³�һ', '������', 'Ħ����', '20160101', '20160131', 10, 10, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160111', '2015', '12', '2 ', '���³���', '����һ', 'Ħ����', '20160101', '20160131', 11, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160112', '2015', '12', '3 ', '���³���', '���ڶ�', 'Ħ����', '20160101', '20160131', 12, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160113', '2015', '12', '4 ', '���³���', '������', 'Ħ����', '20160101', '20160131', 13, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160114', '2015', '12', '5 ', '���³���', '������', 'Ħ����', '20160101', '20160131', 14, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160115', '2015', '12', '6 ', '���³���', '������', 'Ħ����', '20160101', '20160131', 15, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160116', '2015', '12', '7 ', '���³���', '������', 'Ħ����', '20160101', '20160131', 16, 16, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160117', '2015', '12', '8 ', '���³���', '������', 'Ħ����', '20160101', '20160131', 17, 17, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160118', '2015', '12', '9 ', '���³���', '����һ', 'Ħ����', '20160101', '20160131', 18, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160119', '2015', '12', '10', '���³�ʮ', '���ڶ�', 'Ħ����', '20160101', '20160131', 19, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160120', '2015', '12', '11', '����ʮһ', '������', 'Ħ����', '20160101', '20160131', 20, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160121', '2015', '12', '12', '����ʮ��', '������', 'Ħ����', '20160101', '20160131', 21, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160122', '2015', '12', '13', '����ʮ��', '������', 'ˮƿ��', '20160101', '20160131', 22, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160123', '2015', '12', '14', '����ʮ��', '������', 'ˮƿ��', '20160101', '20160131', 23, 23, '1', null, 'N');
commit;
prompt 300 records committed...
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160124', '2015', '12', '15', '����ʮ��', '������', 'ˮƿ��', '20160101', '20160131', 24, 24, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160125', '2015', '12', '16', '����ʮ��', '����һ', 'ˮƿ��', '20160101', '20160131', 25, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160126', '2015', '12', '17', '����ʮ��', '���ڶ�', 'ˮƿ��', '20160101', '20160131', 26, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160127', '2015', '12', '18', '����ʮ��', '������', 'ˮƿ��', '20160101', '20160131', 27, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160128', '2015', '12', '19', '����ʮ��', '������', 'ˮƿ��', '20160101', '20160131', 28, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160129', '2015', '12', '20', '���¶�ʮ', '������', 'ˮƿ��', '20160101', '20160131', 29, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160130', '2015', '12', '21', '����إһ', '������', 'ˮƿ��', '20160101', '20160131', 30, 30, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160131', '2015', '12', '22', '����إ��', '������', 'ˮƿ��', '20160101', '20160131', 31, 31, '1', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160201', '2015', '12', '23', '����إ��', '����һ', 'ˮƿ��', '20160201', '20160229', 32, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160202', '2015', '12', '24', '����إ��', '���ڶ�', 'ˮƿ��', '20160201', '20160229', 33, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160203', '2015', '12', '25', '����إ��', '������', 'ˮƿ��', '20160201', '20160229', 34, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160204', '2015', '12', '26', '����إ��', '������', 'ˮƿ��', '20160201', '20160229', 35, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160205', '2015', '12', '27', '����إ��', '������', 'ˮƿ��', '20160201', '20160229', 36, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160206', '2015', '12', '28', '����إ��', '������', 'ˮƿ��', '20160201', '20160229', 37, 6, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160207', '2015', '12', '29', '����إ��', '������', 'ˮƿ��', '20160201', '20160229', 38, 7, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160208', '2016', '1 ', '1 ', '���³�һ', '����һ', 'ˮƿ��', '20160201', '20160229', 39, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160209', '2016', '1 ', '2 ', '���³���', '���ڶ�', 'ˮƿ��', '20160201', '20160229', 40, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160210', '2016', '1 ', '3 ', '���³���', '������', 'ˮƿ��', '20160201', '20160229', 41, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160211', '2016', '1 ', '4 ', '���³���', '������', 'ˮƿ��', '20160201', '20160229', 42, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160212', '2016', '1 ', '5 ', '���³���', '������', 'ˮƿ��', '20160201', '20160229', 43, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160213', '2016', '1 ', '6 ', '���³���', '������', 'ˮƿ��', '20160201', '20160229', 44, 13, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160214', '2016', '1 ', '7 ', '���³���', '������', 'ˮƿ��', '20160201', '20160229', 45, 14, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160215', '2016', '1 ', '8 ', '���³���', '����һ', 'ˮƿ��', '20160201', '20160229', 46, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160216', '2016', '1 ', '9 ', '���³���', '���ڶ�', 'ˮƿ��', '20160201', '20160229', 47, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160217', '2016', '1 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20160201', '20160229', 48, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160218', '2016', '1 ', '11', '����ʮһ', '������', 'ˮƿ��', '20160201', '20160229', 49, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160219', '2016', '1 ', '12', '����ʮ��', '������', 'ˮƿ��', '20160201', '20160229', 50, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160220', '2016', '1 ', '13', '����ʮ��', '������', 'ˮƿ��', '20160201', '20160229', 51, 20, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160221', '2016', '1 ', '14', '����ʮ��', '������', 'ˮƿ��', '20160201', '20160229', 52, 21, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160222', '2016', '1 ', '15', '����ʮ��', '����һ', 'ˮƿ��', '20160201', '20160229', 53, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160223', '2016', '1 ', '16', '����ʮ��', '���ڶ�', 'ˮƿ��', '20160201', '20160229', 54, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160224', '2016', '1 ', '17', '����ʮ��', '������', 'ˮƿ��', '20160201', '20160229', 55, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160225', '2016', '1 ', '18', '����ʮ��', '������', 'ˮƿ��', '20160201', '20160229', 56, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160226', '2016', '1 ', '19', '����ʮ��', '������', 'ˮƿ��', '20160201', '20160229', 57, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160227', '2016', '1 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20160201', '20160229', 58, 27, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160228', '2016', '1 ', '21', '����إһ', '������', 'ˮƿ��', '20160201', '20160229', 59, 28, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160229', '2016', '1 ', '22', '����إ��', '����һ', 'ˮƿ��', '20160201', '20160229', 60, 29, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160301', '2016', '1 ', '23', '����إ��', '���ڶ�', 'ˮƿ��', '20160301', '20160331', 61, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160302', '2016', '1 ', '24', '����إ��', '������', 'ˮƿ��', '20160301', '20160331', 62, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160303', '2016', '1 ', '25', '����إ��', '������', 'ˮƿ��', '20160301', '20160331', 63, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160304', '2016', '1 ', '26', '����إ��', '������', 'ˮƿ��', '20160301', '20160331', 64, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160305', '2016', '1 ', '27', '����إ��', '������', 'ˮƿ��', '20160301', '20160331', 65, 5, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160306', '2016', '1 ', '28', '����إ��', '������', 'ˮƿ��', '20160301', '20160331', 66, 6, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160307', '2016', '1 ', '29', '����إ��', '����һ', 'ˮƿ��', '20160301', '20160331', 67, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160308', '2016', '1 ', '30', '������ʮ', '���ڶ�', 'ˮƿ��', '20160301', '20160331', 68, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160309', '2016', '2 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20160301', '20160331', 69, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160310', '2016', '2 ', '2 ', '���³���', '������', 'ˮƿ��', '20160301', '20160331', 70, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160311', '2016', '2 ', '3 ', '���³���', '������', 'ˮƿ��', '20160301', '20160331', 71, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160312', '2016', '2 ', '4 ', '���³���', '������', 'ˮƿ��', '20160301', '20160331', 72, 12, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160313', '2016', '2 ', '5 ', '���³���', '������', 'ˮƿ��', '20160301', '20160331', 73, 13, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160314', '2016', '2 ', '6 ', '���³���', '����һ', 'ˮƿ��', '20160301', '20160331', 74, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160315', '2016', '2 ', '7 ', '���³���', '���ڶ�', 'ˮƿ��', '20160301', '20160331', 75, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160316', '2016', '2 ', '8 ', '���³���', '������', 'ˮƿ��', '20160301', '20160331', 76, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160317', '2016', '2 ', '9 ', '���³���', '������', 'ˮƿ��', '20160301', '20160331', 77, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160318', '2016', '2 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20160301', '20160331', 78, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160319', '2016', '2 ', '11', '����ʮһ', '������', 'ˮƿ��', '20160301', '20160331', 79, 19, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160320', '2016', '2 ', '12', '����ʮ��', '������', 'ˮƿ��', '20160301', '20160331', 80, 20, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160321', '2016', '2 ', '13', '����ʮ��', '����һ', 'ˮƿ��', '20160301', '20160331', 81, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160322', '2016', '2 ', '14', '����ʮ��', '���ڶ�', 'ˮƿ��', '20160301', '20160331', 82, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160323', '2016', '2 ', '15', '����ʮ��', '������', 'ˮƿ��', '20160301', '20160331', 83, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160324', '2016', '2 ', '16', '����ʮ��', '������', 'ˮƿ��', '20160301', '20160331', 84, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160325', '2016', '2 ', '17', '����ʮ��', '������', 'ˮƿ��', '20160301', '20160331', 85, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160326', '2016', '2 ', '18', '����ʮ��', '������', 'ˮƿ��', '20160301', '20160331', 86, 26, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160327', '2016', '2 ', '19', '����ʮ��', '������', 'ˮƿ��', '20160301', '20160331', 87, 27, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160328', '2016', '2 ', '20', '���¶�ʮ', '����һ', 'ˮƿ��', '20160301', '20160331', 88, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160329', '2016', '2 ', '21', '����إһ', '���ڶ�', 'ˮƿ��', '20160301', '20160331', 89, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160330', '2016', '2 ', '22', '����إ��', '������', 'ˮƿ��', '20160301', '20160331', 90, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160331', '2016', '2 ', '23', '����إ��', '������', 'ˮƿ��', '20160301', '20160331', 91, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160401', '2016', '2 ', '24', '����إ��', '������', 'ˮƿ��', '20160401', '20160430', 92, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160402', '2016', '2 ', '25', '����إ��', '������', 'ˮƿ��', '20160401', '20160430', 93, 2, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160403', '2016', '2 ', '26', '����إ��', '������', 'ˮƿ��', '20160401', '20160430', 94, 3, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160404', '2016', '2 ', '27', '����إ��', '����һ', 'ˮƿ��', '20160401', '20160430', 95, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160705', '2016', '6 ', '2 ', '���³���', '���ڶ�', 'ˮƿ��', '20160701', '20160731', 187, 5, '1', '����2', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160706', '2016', '6 ', '3 ', '���³���', '������', 'ˮƿ��', '20160701', '20160731', 188, 6, '1', '����2', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160707', '2016', '6 ', '4 ', '���³���', '������', 'ˮƿ��', '20160701', '20160731', 189, 7, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160708', '2016', '6 ', '5 ', '���³���', '������', 'ˮƿ��', '20160701', '20160731', 190, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160709', '2016', '6 ', '6 ', '���³���', '������', 'ˮƿ��', '20160701', '20160731', 191, 9, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160710', '2016', '6 ', '7 ', '���³���', '������', 'ˮƿ��', '20160701', '20160731', 192, 10, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160711', '2016', '6 ', '8 ', '���³���', '����һ', 'ˮƿ��', '20160701', '20160731', 193, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160712', '2016', '6 ', '9 ', '���³���', '���ڶ�', 'ˮƿ��', '20160701', '20160731', 194, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160713', '2016', '6 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20160701', '20160731', 195, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160714', '2016', '6 ', '11', '����ʮһ', '������', 'ˮƿ��', '20160701', '20160731', 196, 14, '1', '���Խ���', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160715', '2016', '6 ', '12', '����ʮ��', '������', 'ˮƿ��', '20160701', '20160731', 197, 15, '1', '���Խ���', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160716', '2016', '6 ', '13', '����ʮ��', '������', 'ˮƿ��', '20160701', '20160731', 198, 16, '1', '���Խ���', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160717', '2016', '6 ', '14', '����ʮ��', '������', 'ˮƿ��', '20160701', '20160731', 199, 17, '1', '���Խ���', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160718', '2016', '6 ', '15', '����ʮ��', '����һ', 'ˮƿ��', '20160701', '20160731', 200, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160719', '2016', '6 ', '16', '����ʮ��', '���ڶ�', 'ˮƿ��', '20160701', '20160731', 201, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160720', '2016', '6 ', '17', '����ʮ��', '������', 'ˮƿ��', '20160701', '20160731', 202, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160721', '2016', '6 ', '18', '����ʮ��', '������', 'ˮƿ��', '20160701', '20160731', 203, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160722', '2016', '6 ', '19', '����ʮ��', '������', 'ˮƿ��', '20160701', '20160731', 204, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160723', '2016', '6 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20160701', '20160731', 205, 23, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160724', '2016', '6 ', '21', '����إһ', '������', 'ˮƿ��', '20160701', '20160731', 206, 24, '1', '���Խ���', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160725', '2016', '6 ', '22', '����إ��', '����һ', 'ˮƿ��', '20160701', '20160731', 207, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160726', '2016', '6 ', '23', '����إ��', '���ڶ�', 'ˮƿ��', '20160701', '20160731', 208, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160727', '2016', '6 ', '24', '����إ��', '������', 'ˮƿ��', '20160701', '20160731', 209, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160728', '2016', '6 ', '25', '����إ��', '������', 'ˮƿ��', '20160701', '20160731', 210, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160729', '2016', '6 ', '26', '����إ��', '������', 'ˮƿ��', '20160701', '20160731', 211, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160730', '2016', '6 ', '27', '����إ��', '������', 'ˮƿ��', '20160701', '20160731', 212, 30, '1', '����', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160731', '2016', '6 ', '28', '����إ��', '������', 'ˮƿ��', '20160701', '20160731', 213, 31, '1', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160801', '2016', '6 ', '29', '����إ��', '����һ', 'ˮƿ��', '20160801', '20160831', 214, 1, '0', null, 'N');
commit;
prompt 400 records committed...
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160802', '2016', '6 ', '30', '������ʮ', '���ڶ�', 'ˮƿ��', '20160801', '20160831', 215, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160803', '2016', '7 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20160801', '20160831', 216, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160804', '2016', '7 ', '2 ', '���³���', '������', 'ˮƿ��', '20160801', '20160831', 217, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160805', '2016', '7 ', '3 ', '���³���', '������', 'ˮƿ��', '20160801', '20160831', 218, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160806', '2016', '7 ', '4 ', '���³���', '������', 'ˮƿ��', '20160801', '20160831', 219, 6, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160807', '2016', '7 ', '5 ', '���³���', '������', 'ˮƿ��', '20160801', '20160831', 220, 7, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160808', '2016', '7 ', '6 ', '���³���', '����һ', 'ˮƿ��', '20160801', '20160831', 221, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160809', '2016', '7 ', '7 ', '���³���', '���ڶ�', 'ˮƿ��', '20160801', '20160831', 222, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160810', '2016', '7 ', '8 ', '���³���', '������', 'ˮƿ��', '20160801', '20160831', 223, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160811', '2016', '7 ', '9 ', '���³���', '������', 'ˮƿ��', '20160801', '20160831', 224, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160812', '2016', '7 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20160801', '20160831', 225, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160813', '2016', '7 ', '11', '����ʮһ', '������', 'ˮƿ��', '20160801', '20160831', 226, 13, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160814', '2016', '7 ', '12', '����ʮ��', '������', 'ˮƿ��', '20160801', '20160831', 227, 14, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160815', '2016', '7 ', '13', '����ʮ��', '����һ', 'ˮƿ��', '20160801', '20160831', 228, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160816', '2016', '7 ', '14', '����ʮ��', '���ڶ�', 'ˮƿ��', '20160801', '20160831', 229, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160817', '2016', '7 ', '15', '����ʮ��', '������', 'ˮƿ��', '20160801', '20160831', 230, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160818', '2016', '7 ', '16', '����ʮ��', '������', 'ˮƿ��', '20160801', '20160831', 231, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160819', '2016', '7 ', '17', '����ʮ��', '������', 'ˮƿ��', '20160801', '20160831', 232, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160820', '2016', '7 ', '18', '����ʮ��', '������', 'ˮƿ��', '20160801', '20160831', 233, 20, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160821', '2016', '7 ', '19', '����ʮ��', '������', 'ˮƿ��', '20160801', '20160831', 234, 21, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160822', '2016', '7 ', '20', '���¶�ʮ', '����һ', 'ˮƿ��', '20160801', '20160831', 235, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160823', '2016', '7 ', '21', '����إһ', '���ڶ�', 'ˮƿ��', '20160801', '20160831', 236, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160824', '2016', '7 ', '22', '����إ��', '������', 'ˮƿ��', '20160801', '20160831', 237, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160825', '2016', '7 ', '23', '����إ��', '������', 'ˮƿ��', '20160801', '20160831', 238, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160826', '2016', '7 ', '24', '����إ��', '������', 'ˮƿ��', '20160801', '20160831', 239, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160827', '2016', '7 ', '25', '����إ��', '������', 'ˮƿ��', '20160801', '20160831', 240, 27, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160828', '2016', '7 ', '26', '����إ��', '������', 'ˮƿ��', '20160801', '20160831', 241, 28, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160829', '2016', '7 ', '27', '����إ��', '����һ', 'ˮƿ��', '20160801', '20160831', 242, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160830', '2016', '7 ', '28', '����إ��', '���ڶ�', 'ˮƿ��', '20160801', '20160831', 243, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160831', '2016', '7 ', '29', '����إ��', '������', 'ˮƿ��', '20160801', '20160831', 244, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160901', '2016', '8 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20160901', '20160930', 245, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160902', '2016', '8 ', '2 ', '���³���', '������', 'ˮƿ��', '20160901', '20160930', 246, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160903', '2016', '8 ', '3 ', '���³���', '������', 'ˮƿ��', '20160901', '20160930', 247, 3, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160904', '2016', '8 ', '4 ', '���³���', '������', 'ˮƿ��', '20160901', '20160930', 248, 4, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160905', '2016', '8 ', '5 ', '���³���', '����һ', 'ˮƿ��', '20160901', '20160930', 249, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160906', '2016', '8 ', '6 ', '���³���', '���ڶ�', 'ˮƿ��', '20160901', '20160930', 250, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160907', '2016', '8 ', '7 ', '���³���', '������', 'ˮƿ��', '20160901', '20160930', 251, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160908', '2016', '8 ', '8 ', '���³���', '������', 'ˮƿ��', '20160901', '20160930', 252, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160909', '2016', '8 ', '9 ', '���³���', '������', 'ˮƿ��', '20160901', '20160930', 253, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160910', '2016', '8 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20160901', '20160930', 254, 10, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160911', '2016', '8 ', '11', '����ʮһ', '������', 'ˮƿ��', '20160901', '20160930', 255, 11, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160912', '2016', '8 ', '12', '����ʮ��', '����һ', 'ˮƿ��', '20160901', '20160930', 256, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160913', '2016', '8 ', '13', '����ʮ��', '���ڶ�', 'ˮƿ��', '20160901', '20160930', 257, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160914', '2016', '8 ', '14', '����ʮ��', '������', 'ˮƿ��', '20160901', '20160930', 258, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160915', '2016', '8 ', '15', '����ʮ��', '������', 'ˮƿ��', '20160901', '20160930', 259, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160916', '2016', '8 ', '16', '����ʮ��', '������', 'ˮƿ��', '20160901', '20160930', 260, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160917', '2016', '8 ', '17', '����ʮ��', '������', 'ˮƿ��', '20160901', '20160930', 261, 17, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160918', '2016', '8 ', '18', '����ʮ��', '������', 'ˮƿ��', '20160901', '20160930', 262, 18, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160919', '2016', '8 ', '19', '����ʮ��', '����һ', 'ˮƿ��', '20160901', '20160930', 263, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160920', '2016', '8 ', '20', '���¶�ʮ', '���ڶ�', 'ˮƿ��', '20160901', '20160930', 264, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160921', '2016', '8 ', '21', '����إһ', '������', 'ˮƿ��', '20160901', '20160930', 265, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160922', '2016', '8 ', '22', '����إ��', '������', 'ˮƿ��', '20160901', '20160930', 266, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160923', '2016', '8 ', '23', '����إ��', '������', 'ˮƿ��', '20160901', '20160930', 267, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160924', '2016', '8 ', '24', '����إ��', '������', 'ˮƿ��', '20160901', '20160930', 268, 24, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160925', '2016', '8 ', '25', '����إ��', '������', 'ˮƿ��', '20160901', '20160930', 269, 25, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160926', '2016', '8 ', '26', '����إ��', '����һ', 'ˮƿ��', '20160901', '20160930', 270, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160927', '2016', '8 ', '27', '����إ��', '���ڶ�', 'ˮƿ��', '20160901', '20160930', 271, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160928', '2016', '8 ', '28', '����إ��', '������', 'ˮƿ��', '20160901', '20160930', 272, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160929', '2016', '8 ', '29', '����إ��', '������', 'ˮƿ��', '20160901', '20160930', 273, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20160930', '2016', '8 ', '30', '������ʮ', '������', 'ˮƿ��', '20160901', '20160930', 274, 30, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161001', '2016', '9 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20161001', '20161031', 275, 1, '1', '�����', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161002', '2016', '9 ', '2 ', '���³���', '������', 'ˮƿ��', '20161001', '20161031', 276, 2, '1', '�����', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161003', '2016', '9 ', '3 ', '���³���', '����һ', 'ˮƿ��', '20161001', '20161031', 277, 3, '1', '�����', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20161004', '2016', '9 ', '4 ', '���³���', '���ڶ�', 'ˮƿ��', '20161001', '20161031', 278, 4, '1', '�����', 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170409', '2017', '3 ', '13', '����ʮ��', '������', 'ˮƿ��', '20170401', '20170430', 99, 9, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170410', '2017', '3 ', '14', '����ʮ��', '����һ', 'ˮƿ��', '20170401', '20170430', 100, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170411', '2017', '3 ', '15', '����ʮ��', '���ڶ�', 'ˮƿ��', '20170401', '20170430', 101, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170412', '2017', '3 ', '16', '����ʮ��', '������', 'ˮƿ��', '20170401', '20170430', 102, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170413', '2017', '3 ', '17', '����ʮ��', '������', 'ˮƿ��', '20170401', '20170430', 103, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170414', '2017', '3 ', '18', '����ʮ��', '������', 'ˮƿ��', '20170401', '20170430', 104, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170415', '2017', '3 ', '19', '����ʮ��', '������', 'ˮƿ��', '20170401', '20170430', 105, 15, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170416', '2017', '3 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20170401', '20170430', 106, 16, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170417', '2017', '3 ', '21', '����إһ', '����һ', 'ˮƿ��', '20170401', '20170430', 107, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170418', '2017', '3 ', '22', '����إ��', '���ڶ�', 'ˮƿ��', '20170401', '20170430', 108, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170419', '2017', '3 ', '23', '����إ��', '������', 'ˮƿ��', '20170401', '20170430', 109, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170420', '2017', '3 ', '24', '����إ��', '������', 'ˮƿ��', '20170401', '20170430', 110, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170421', '2017', '3 ', '25', '����إ��', '������', 'ˮƿ��', '20170401', '20170430', 111, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170422', '2017', '3 ', '26', '����إ��', '������', 'ˮƿ��', '20170401', '20170430', 112, 22, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170423', '2017', '3 ', '27', '����إ��', '������', 'ˮƿ��', '20170401', '20170430', 113, 23, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170424', '2017', '3 ', '28', '����إ��', '����һ', 'ˮƿ��', '20170401', '20170430', 114, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170425', '2017', '3 ', '29', '����إ��', '���ڶ�', 'ˮƿ��', '20170401', '20170430', 115, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170426', '2017', '4 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20170401', '20170430', 116, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170427', '2017', '4 ', '2 ', '���³���', '������', 'ˮƿ��', '20170401', '20170430', 117, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170428', '2017', '4 ', '3 ', '���³���', '������', 'ˮƿ��', '20170401', '20170430', 118, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170429', '2017', '4 ', '4 ', '���³���', '������', 'ˮƿ��', '20170401', '20170430', 119, 29, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170430', '2017', '4 ', '5 ', '���³���', '������', 'ˮƿ��', '20170401', '20170430', 120, 30, '1', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170501', '2017', '4 ', '6 ', '���³���', '����һ', 'ˮƿ��', '20170501', '20170531', 121, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170502', '2017', '4 ', '7 ', '���³���', '���ڶ�', 'ˮƿ��', '20170501', '20170531', 122, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170503', '2017', '4 ', '8 ', '���³���', '������', 'ˮƿ��', '20170501', '20170531', 123, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170504', '2017', '4 ', '9 ', '���³���', '������', 'ˮƿ��', '20170501', '20170531', 124, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170505', '2017', '4 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20170501', '20170531', 125, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170506', '2017', '4 ', '11', '����ʮһ', '������', 'ˮƿ��', '20170501', '20170531', 126, 6, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170507', '2017', '4 ', '12', '����ʮ��', '������', 'ˮƿ��', '20170501', '20170531', 127, 7, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170508', '2017', '4 ', '13', '����ʮ��', '����һ', 'ˮƿ��', '20170501', '20170531', 128, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170509', '2017', '4 ', '14', '����ʮ��', '���ڶ�', 'ˮƿ��', '20170501', '20170531', 129, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170510', '2017', '4 ', '15', '����ʮ��', '������', 'ˮƿ��', '20170501', '20170531', 130, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170511', '2017', '4 ', '16', '����ʮ��', '������', 'ˮƿ��', '20170501', '20170531', 131, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170512', '2017', '4 ', '17', '����ʮ��', '������', 'ˮƿ��', '20170501', '20170531', 132, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170513', '2017', '4 ', '18', '����ʮ��', '������', 'ˮƿ��', '20170501', '20170531', 133, 13, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170514', '2017', '4 ', '19', '����ʮ��', '������', 'ˮƿ��', '20170501', '20170531', 134, 14, '1', null, 'N');
commit;
prompt 500 records committed...
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170515', '2017', '4 ', '20', '���¶�ʮ', '����һ', 'ˮƿ��', '20170501', '20170531', 135, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170516', '2017', '4 ', '21', '����إһ', '���ڶ�', 'ˮƿ��', '20170501', '20170531', 136, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170517', '2017', '4 ', '22', '����إ��', '������', 'ˮƿ��', '20170501', '20170531', 137, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170518', '2017', '4 ', '23', '����إ��', '������', 'ˮƿ��', '20170501', '20170531', 138, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170519', '2017', '4 ', '24', '����إ��', '������', 'ˮƿ��', '20170501', '20170531', 139, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170520', '2017', '4 ', '25', '����إ��', '������', 'ˮƿ��', '20170501', '20170531', 140, 20, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170521', '2017', '4 ', '26', '����إ��', '������', 'ˮƿ��', '20170501', '20170531', 141, 21, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170522', '2017', '4 ', '27', '����إ��', '����һ', 'ˮƿ��', '20170501', '20170531', 142, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170523', '2017', '4 ', '28', '����إ��', '���ڶ�', 'ˮƿ��', '20170501', '20170531', 143, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170524', '2017', '4 ', '29', '����إ��', '������', 'ˮƿ��', '20170501', '20170531', 144, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170525', '2017', '4 ', '30', '������ʮ', '������', 'ˮƿ��', '20170501', '20170531', 145, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170526', '2017', '5 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20170501', '20170531', 146, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170527', '2017', '5 ', '2 ', '���³���', '������', 'ˮƿ��', '20170501', '20170531', 147, 27, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170528', '2017', '5 ', '3 ', '���³���', '������', 'ˮƿ��', '20170501', '20170531', 148, 28, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170529', '2017', '5 ', '4 ', '���³���', '����һ', 'ˮƿ��', '20170501', '20170531', 149, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170530', '2017', '5 ', '5 ', '���³���', '���ڶ�', 'ˮƿ��', '20170501', '20170531', 150, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170531', '2017', '5 ', '6 ', '���³���', '������', 'ˮƿ��', '20170501', '20170531', 151, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170601', '2017', '5 ', '7 ', '���³���', '������', 'ˮƿ��', '20170601', '20170630', 152, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170602', '2017', '5 ', '8 ', '���³���', '������', 'ˮƿ��', '20170601', '20170630', 153, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170603', '2017', '5 ', '9 ', '���³���', '������', 'ˮƿ��', '20170601', '20170630', 154, 3, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170604', '2017', '5 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20170601', '20170630', 155, 4, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170605', '2017', '5 ', '11', '����ʮһ', '����һ', 'ˮƿ��', '20170601', '20170630', 156, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170606', '2017', '5 ', '12', '����ʮ��', '���ڶ�', 'ˮƿ��', '20170601', '20170630', 157, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170607', '2017', '5 ', '13', '����ʮ��', '������', 'ˮƿ��', '20170601', '20170630', 158, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170608', '2017', '5 ', '14', '����ʮ��', '������', 'ˮƿ��', '20170601', '20170630', 159, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170609', '2017', '5 ', '15', '����ʮ��', '������', 'ˮƿ��', '20170601', '20170630', 160, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170610', '2017', '5 ', '16', '����ʮ��', '������', 'ˮƿ��', '20170601', '20170630', 161, 10, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170611', '2017', '5 ', '17', '����ʮ��', '������', 'ˮƿ��', '20170601', '20170630', 162, 11, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170612', '2017', '5 ', '18', '����ʮ��', '����һ', 'ˮƿ��', '20170601', '20170630', 163, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170613', '2017', '5 ', '19', '����ʮ��', '���ڶ�', 'ˮƿ��', '20170601', '20170630', 164, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170614', '2017', '5 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20170601', '20170630', 165, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170615', '2017', '5 ', '21', '����إһ', '������', 'ˮƿ��', '20170601', '20170630', 166, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170616', '2017', '5 ', '22', '����إ��', '������', 'ˮƿ��', '20170601', '20170630', 167, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170617', '2017', '5 ', '23', '����إ��', '������', 'ˮƿ��', '20170601', '20170630', 168, 17, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170618', '2017', '5 ', '24', '����إ��', '������', 'ˮƿ��', '20170601', '20170630', 169, 18, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170619', '2017', '5 ', '25', '����إ��', '����һ', 'ˮƿ��', '20170601', '20170630', 170, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170620', '2017', '5 ', '26', '����إ��', '���ڶ�', 'ˮƿ��', '20170601', '20170630', 171, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170621', '2017', '5 ', '27', '����إ��', '������', 'ˮƿ��', '20170601', '20170630', 172, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170622', '2017', '5 ', '28', '����إ��', '������', 'ˮƿ��', '20170601', '20170630', 173, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170623', '2017', '5 ', '29', '����إ��', '������', 'ˮƿ��', '20170601', '20170630', 174, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170624', '2017', '6 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20170601', '20170630', 175, 24, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170625', '2017', '6 ', '2 ', '���³���', '������', 'ˮƿ��', '20170601', '20170630', 176, 25, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170626', '2017', '6 ', '3 ', '���³���', '����һ', 'ˮƿ��', '20170601', '20170630', 177, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170627', '2017', '6 ', '4 ', '���³���', '���ڶ�', 'ˮƿ��', '20170601', '20170630', 178, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170628', '2017', '6 ', '5 ', '���³���', '������', 'ˮƿ��', '20170601', '20170630', 179, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170629', '2017', '6 ', '6 ', '���³���', '������', 'ˮƿ��', '20170601', '20170630', 180, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170630', '2017', '6 ', '7 ', '���³���', '������', 'ˮƿ��', '20170601', '20170630', 181, 30, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170701', '2017', '6 ', '8 ', '���³���', '������', 'ˮƿ��', '20170701', '20170731', 182, 1, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170702', '2017', '6 ', '9 ', '���³���', '������', 'ˮƿ��', '20170701', '20170731', 183, 2, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170703', '2017', '6 ', '10', '���³�ʮ', '����һ', 'ˮƿ��', '20170701', '20170731', 184, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170704', '2017', '6 ', '11', '����ʮһ', '���ڶ�', 'ˮƿ��', '20170701', '20170731', 185, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170705', '2017', '6 ', '12', '����ʮ��', '������', 'ˮƿ��', '20170701', '20170731', 186, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170706', '2017', '6 ', '13', '����ʮ��', '������', 'ˮƿ��', '20170701', '20170731', 187, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170707', '2017', '6 ', '14', '����ʮ��', '������', 'ˮƿ��', '20170701', '20170731', 188, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170708', '2017', '6 ', '15', '����ʮ��', '������', 'ˮƿ��', '20170701', '20170731', 189, 8, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170709', '2017', '6 ', '16', '����ʮ��', '������', 'ˮƿ��', '20170701', '20170731', 190, 9, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170710', '2017', '6 ', '17', '����ʮ��', '����һ', 'ˮƿ��', '20170701', '20170731', 191, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170711', '2017', '6 ', '18', '����ʮ��', '���ڶ�', 'ˮƿ��', '20170701', '20170731', 192, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180416', '2018', '3 ', '1 ', '���³�һ', '����һ', 'ˮƿ��', '20180401', '20180430', 106, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180417', '2018', '3 ', '2 ', '���³���', '���ڶ�', 'ˮƿ��', '20180401', '20180430', 107, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180418', '2018', '3 ', '3 ', '���³���', '������', 'ˮƿ��', '20180401', '20180430', 108, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180419', '2018', '3 ', '4 ', '���³���', '������', 'ˮƿ��', '20180401', '20180430', 109, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180420', '2018', '3 ', '5 ', '���³���', '������', 'ˮƿ��', '20180401', '20180430', 110, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180421', '2018', '3 ', '6 ', '���³���', '������', 'ˮƿ��', '20180401', '20180430', 111, 21, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180422', '2018', '3 ', '7 ', '���³���', '������', 'ˮƿ��', '20180401', '20180430', 112, 22, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180423', '2018', '3 ', '8 ', '���³���', '����һ', 'ˮƿ��', '20180401', '20180430', 113, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180424', '2018', '3 ', '9 ', '���³���', '���ڶ�', 'ˮƿ��', '20180401', '20180430', 114, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180425', '2018', '3 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20180401', '20180430', 115, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180426', '2018', '3 ', '11', '����ʮһ', '������', 'ˮƿ��', '20180401', '20180430', 116, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180427', '2018', '3 ', '12', '����ʮ��', '������', 'ˮƿ��', '20180401', '20180430', 117, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180428', '2018', '3 ', '13', '����ʮ��', '������', 'ˮƿ��', '20180401', '20180430', 118, 28, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180429', '2018', '3 ', '14', '����ʮ��', '������', 'ˮƿ��', '20180401', '20180430', 119, 29, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180430', '2018', '3 ', '15', '����ʮ��', '����һ', 'ˮƿ��', '20180401', '20180430', 120, 30, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180501', '2018', '3 ', '16', '����ʮ��', '���ڶ�', 'ˮƿ��', '20180501', '20180531', 121, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180502', '2018', '3 ', '17', '����ʮ��', '������', 'ˮƿ��', '20180501', '20180531', 122, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180503', '2018', '3 ', '18', '����ʮ��', '������', 'ˮƿ��', '20180501', '20180531', 123, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180504', '2018', '3 ', '19', '����ʮ��', '������', 'ˮƿ��', '20180501', '20180531', 124, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180505', '2018', '3 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20180501', '20180531', 125, 5, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180506', '2018', '3 ', '21', '����إһ', '������', 'ˮƿ��', '20180501', '20180531', 126, 6, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180507', '2018', '3 ', '22', '����إ��', '����һ', 'ˮƿ��', '20180501', '20180531', 127, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180508', '2018', '3 ', '23', '����إ��', '���ڶ�', 'ˮƿ��', '20180501', '20180531', 128, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180509', '2018', '3 ', '24', '����إ��', '������', 'ˮƿ��', '20180501', '20180531', 129, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180510', '2018', '3 ', '25', '����إ��', '������', 'ˮƿ��', '20180501', '20180531', 130, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180511', '2018', '3 ', '26', '����إ��', '������', 'ˮƿ��', '20180501', '20180531', 131, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180512', '2018', '3 ', '27', '����إ��', '������', 'ˮƿ��', '20180501', '20180531', 132, 12, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180513', '2018', '3 ', '28', '����إ��', '������', 'ˮƿ��', '20180501', '20180531', 133, 13, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180514', '2018', '3 ', '29', '����إ��', '����һ', 'ˮƿ��', '20180501', '20180531', 134, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180515', '2018', '4 ', '1 ', '���³�һ', '���ڶ�', 'ˮƿ��', '20180501', '20180531', 135, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180516', '2018', '4 ', '2 ', '���³���', '������', 'ˮƿ��', '20180501', '20180531', 136, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180517', '2018', '4 ', '3 ', '���³���', '������', 'ˮƿ��', '20180501', '20180531', 137, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180518', '2018', '4 ', '4 ', '���³���', '������', 'ˮƿ��', '20180501', '20180531', 138, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180519', '2018', '4 ', '5 ', '���³���', '������', 'ˮƿ��', '20180501', '20180531', 139, 19, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180520', '2018', '4 ', '6 ', '���³���', '������', 'ˮƿ��', '20180501', '20180531', 140, 20, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180521', '2018', '4 ', '7 ', '���³���', '����һ', 'ˮƿ��', '20180501', '20180531', 141, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180522', '2018', '4 ', '8 ', '���³���', '���ڶ�', 'ˮƿ��', '20180501', '20180531', 142, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180523', '2018', '4 ', '9 ', '���³���', '������', 'ˮƿ��', '20180501', '20180531', 143, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180524', '2018', '4 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20180501', '20180531', 144, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180525', '2018', '4 ', '11', '����ʮһ', '������', 'ˮƿ��', '20180501', '20180531', 145, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180526', '2018', '4 ', '12', '����ʮ��', '������', 'ˮƿ��', '20180501', '20180531', 146, 26, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180527', '2018', '4 ', '13', '����ʮ��', '������', 'ˮƿ��', '20180501', '20180531', 147, 27, '1', null, 'N');
commit;
prompt 600 records committed...
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180528', '2018', '4 ', '14', '����ʮ��', '����һ', 'ˮƿ��', '20180501', '20180531', 148, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180529', '2018', '4 ', '15', '����ʮ��', '���ڶ�', 'ˮƿ��', '20180501', '20180531', 149, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180530', '2018', '4 ', '16', '����ʮ��', '������', 'ˮƿ��', '20180501', '20180531', 150, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180531', '2018', '4 ', '17', '����ʮ��', '������', 'ˮƿ��', '20180501', '20180531', 151, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180601', '2018', '4 ', '18', '����ʮ��', '������', 'ˮƿ��', '20180601', '20180630', 152, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180602', '2018', '4 ', '19', '����ʮ��', '������', 'ˮƿ��', '20180601', '20180630', 153, 2, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180603', '2018', '4 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20180601', '20180630', 154, 3, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180604', '2018', '4 ', '21', '����إһ', '����һ', 'ˮƿ��', '20180601', '20180630', 155, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180605', '2018', '4 ', '22', '����إ��', '���ڶ�', 'ˮƿ��', '20180601', '20180630', 156, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180606', '2018', '4 ', '23', '����إ��', '������', 'ˮƿ��', '20180601', '20180630', 157, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180607', '2018', '4 ', '24', '����إ��', '������', 'ˮƿ��', '20180601', '20180630', 158, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180608', '2018', '4 ', '25', '����إ��', '������', 'ˮƿ��', '20180601', '20180630', 159, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180609', '2018', '4 ', '26', '����إ��', '������', 'ˮƿ��', '20180601', '20180630', 160, 9, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180610', '2018', '4 ', '27', '����إ��', '������', 'ˮƿ��', '20180601', '20180630', 161, 10, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180611', '2018', '4 ', '28', '����إ��', '����һ', 'ˮƿ��', '20180601', '20180630', 162, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180612', '2018', '4 ', '29', '����إ��', '���ڶ�', 'ˮƿ��', '20180601', '20180630', 163, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180613', '2018', '4 ', '30', '������ʮ', '������', 'ˮƿ��', '20180601', '20180630', 164, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180614', '2018', '5 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20180601', '20180630', 165, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180615', '2018', '5 ', '2 ', '���³���', '������', 'ˮƿ��', '20180601', '20180630', 166, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180616', '2018', '5 ', '3 ', '���³���', '������', 'ˮƿ��', '20180601', '20180630', 167, 16, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180617', '2018', '5 ', '4 ', '���³���', '������', 'ˮƿ��', '20180601', '20180630', 168, 17, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180618', '2018', '5 ', '5 ', '���³���', '����һ', 'ˮƿ��', '20180601', '20180630', 169, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180619', '2018', '5 ', '6 ', '���³���', '���ڶ�', 'ˮƿ��', '20180601', '20180630', 170, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180620', '2018', '5 ', '7 ', '���³���', '������', 'ˮƿ��', '20180601', '20180630', 171, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180621', '2018', '5 ', '8 ', '���³���', '������', 'ˮƿ��', '20180601', '20180630', 172, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180622', '2018', '5 ', '9 ', '���³���', '������', 'ˮƿ��', '20180601', '20180630', 173, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180623', '2018', '5 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20180601', '20180630', 174, 23, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180624', '2018', '5 ', '11', '����ʮһ', '������', 'ˮƿ��', '20180601', '20180630', 175, 24, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180625', '2018', '5 ', '12', '����ʮ��', '����һ', 'ˮƿ��', '20180601', '20180630', 176, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180626', '2018', '5 ', '13', '����ʮ��', '���ڶ�', 'ˮƿ��', '20180601', '20180630', 177, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180627', '2018', '5 ', '14', '����ʮ��', '������', 'ˮƿ��', '20180601', '20180630', 178, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180628', '2018', '5 ', '15', '����ʮ��', '������', 'ˮƿ��', '20180601', '20180630', 179, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180629', '2018', '5 ', '16', '����ʮ��', '������', 'ˮƿ��', '20180601', '20180630', 180, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180630', '2018', '5 ', '17', '����ʮ��', '������', 'ˮƿ��', '20180601', '20180630', 181, 30, '1', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180701', '2018', '5 ', '18', '����ʮ��', '������', 'ˮƿ��', '20180701', '20180731', 182, 1, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180702', '2018', '5 ', '19', '����ʮ��', '����һ', 'ˮƿ��', '20180701', '20180731', 183, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180703', '2018', '5 ', '20', '���¶�ʮ', '���ڶ�', 'ˮƿ��', '20180701', '20180731', 184, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180704', '2018', '5 ', '21', '����إһ', '������', 'ˮƿ��', '20180701', '20180731', 185, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180705', '2018', '5 ', '22', '����إ��', '������', 'ˮƿ��', '20180701', '20180731', 186, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180706', '2018', '5 ', '23', '����إ��', '������', 'ˮƿ��', '20180701', '20180731', 187, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180707', '2018', '5 ', '24', '����إ��', '������', 'ˮƿ��', '20180701', '20180731', 188, 7, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180708', '2018', '5 ', '25', '����إ��', '������', 'ˮƿ��', '20180701', '20180731', 189, 8, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180709', '2018', '5 ', '26', '����إ��', '����һ', 'ˮƿ��', '20180701', '20180731', 190, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180710', '2018', '5 ', '27', '����إ��', '���ڶ�', 'ˮƿ��', '20180701', '20180731', 191, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180711', '2018', '5 ', '28', '����إ��', '������', 'ˮƿ��', '20180701', '20180731', 192, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180712', '2018', '5 ', '29', '����إ��', '������', 'ˮƿ��', '20180701', '20180731', 193, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180713', '2018', '6 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20180701', '20180731', 194, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180714', '2018', '6 ', '2 ', '���³���', '������', 'ˮƿ��', '20180701', '20180731', 195, 14, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180715', '2018', '6 ', '3 ', '���³���', '������', 'ˮƿ��', '20180701', '20180731', 196, 15, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180716', '2018', '6 ', '4 ', '���³���', '����һ', 'ˮƿ��', '20180701', '20180731', 197, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170712', '2017', '6 ', '19', '����ʮ��', '������', 'ˮƿ��', '20170701', '20170731', 193, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170713', '2017', '6 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20170701', '20170731', 194, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170714', '2017', '6 ', '21', '����إһ', '������', 'ˮƿ��', '20170701', '20170731', 195, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170715', '2017', '6 ', '22', '����إ��', '������', 'ˮƿ��', '20170701', '20170731', 196, 15, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170716', '2017', '6 ', '23', '����إ��', '������', 'ˮƿ��', '20170701', '20170731', 197, 16, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170717', '2017', '6 ', '24', '����إ��', '����һ', 'ˮƿ��', '20170701', '20170731', 198, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170718', '2017', '6 ', '25', '����إ��', '���ڶ�', 'ˮƿ��', '20170701', '20170731', 199, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170719', '2017', '6 ', '26', '����إ��', '������', 'ˮƿ��', '20170701', '20170731', 200, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170720', '2017', '6 ', '27', '����إ��', '������', 'ˮƿ��', '20170701', '20170731', 201, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170721', '2017', '6 ', '28', '����إ��', '������', 'ˮƿ��', '20170701', '20170731', 202, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170722', '2017', '6 ', '29', '����إ��', '������', 'ˮƿ��', '20170701', '20170731', 203, 22, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170723', '2017', '18', '1 ', '�����³�һ', '������', 'ˮƿ��', '20170701', '20170731', 204, 23, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170724', '2017', '18', '2 ', '�����³���', '����һ', 'ˮƿ��', '20170701', '20170731', 205, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170725', '2017', '18', '3 ', '�����³���', '���ڶ�', 'ˮƿ��', '20170701', '20170731', 206, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170726', '2017', '18', '4 ', '�����³���', '������', 'ˮƿ��', '20170701', '20170731', 207, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170727', '2017', '18', '5 ', '�����³���', '������', 'ˮƿ��', '20170701', '20170731', 208, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170728', '2017', '18', '6 ', '�����³���', '������', 'ˮƿ��', '20170701', '20170731', 209, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170729', '2017', '18', '7 ', '�����³���', '������', 'ˮƿ��', '20170701', '20170731', 210, 29, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170730', '2017', '18', '8 ', '�����³���', '������', 'ˮƿ��', '20170701', '20170731', 211, 30, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170731', '2017', '18', '9 ', '�����³���', '����һ', 'ˮƿ��', '20170701', '20170731', 212, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170801', '2017', '18', '10', '�����³�ʮ', '���ڶ�', 'ˮƿ��', '20170801', '20170831', 213, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170802', '2017', '18', '11', '������ʮһ', '������', 'ˮƿ��', '20170801', '20170831', 214, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170803', '2017', '18', '12', '������ʮ��', '������', 'ˮƿ��', '20170801', '20170831', 215, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170804', '2017', '18', '13', '������ʮ��', '������', 'ˮƿ��', '20170801', '20170831', 216, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170805', '2017', '18', '14', '������ʮ��', '������', 'ˮƿ��', '20170801', '20170831', 217, 5, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170806', '2017', '18', '15', '������ʮ��', '������', 'ˮƿ��', '20170801', '20170831', 218, 6, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170807', '2017', '18', '16', '������ʮ��', '����һ', 'ˮƿ��', '20170801', '20170831', 219, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170808', '2017', '18', '17', '������ʮ��', '���ڶ�', 'ˮƿ��', '20170801', '20170831', 220, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170809', '2017', '18', '18', '������ʮ��', '������', 'ˮƿ��', '20170801', '20170831', 221, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170810', '2017', '18', '19', '������ʮ��', '������', 'ˮƿ��', '20170801', '20170831', 222, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170811', '2017', '18', '20', '�����¶�ʮ', '������', 'ˮƿ��', '20170801', '20170831', 223, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170812', '2017', '18', '21', '������إһ', '������', 'ˮƿ��', '20170801', '20170831', 224, 12, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170813', '2017', '18', '22', '������إ��', '������', 'ˮƿ��', '20170801', '20170831', 225, 13, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170814', '2017', '18', '23', '������إ��', '����һ', 'ˮƿ��', '20170801', '20170831', 226, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170815', '2017', '18', '24', '������إ��', '���ڶ�', 'ˮƿ��', '20170801', '20170831', 227, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170816', '2017', '18', '25', '������إ��', '������', 'ˮƿ��', '20170801', '20170831', 228, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170817', '2017', '18', '26', '������إ��', '������', 'ˮƿ��', '20170801', '20170831', 229, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170818', '2017', '18', '27', '������إ��', '������', 'ˮƿ��', '20170801', '20170831', 230, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170819', '2017', '18', '28', '������إ��', '������', 'ˮƿ��', '20170801', '20170831', 231, 19, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170820', '2017', '18', '29', '������إ��', '������', 'ˮƿ��', '20170801', '20170831', 232, 20, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170821', '2017', '18', '30', '��������ʮ', '����һ', 'ˮƿ��', '20170801', '20170831', 233, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170822', '2017', '7 ', '1 ', '���³�һ', '���ڶ�', 'ˮƿ��', '20170801', '20170831', 234, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170823', '2017', '7 ', '2 ', '���³���', '������', 'ˮƿ��', '20170801', '20170831', 235, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170824', '2017', '7 ', '3 ', '���³���', '������', 'ˮƿ��', '20170801', '20170831', 236, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170825', '2017', '7 ', '4 ', '���³���', '������', 'ˮƿ��', '20170801', '20170831', 237, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170826', '2017', '7 ', '5 ', '���³���', '������', 'ˮƿ��', '20170801', '20170831', 238, 26, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170827', '2017', '7 ', '6 ', '���³���', '������', 'ˮƿ��', '20170801', '20170831', 239, 27, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170828', '2017', '7 ', '7 ', '���³���', '����һ', 'ˮƿ��', '20170801', '20170831', 240, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170829', '2017', '7 ', '8 ', '���³���', '���ڶ�', 'ˮƿ��', '20170801', '20170831', 241, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170830', '2017', '7 ', '9 ', '���³���', '������', 'ˮƿ��', '20170801', '20170831', 242, 30, '0', null, 'N');
commit;
prompt 700 records committed...
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170831', '2017', '7 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20170801', '20170831', 243, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170901', '2017', '7 ', '11', '����ʮһ', '������', 'ˮƿ��', '20170901', '20170930', 244, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170902', '2017', '7 ', '12', '����ʮ��', '������', 'ˮƿ��', '20170901', '20170930', 245, 2, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170903', '2017', '7 ', '13', '����ʮ��', '������', 'ˮƿ��', '20170901', '20170930', 246, 3, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170904', '2017', '7 ', '14', '����ʮ��', '����һ', 'ˮƿ��', '20170901', '20170930', 247, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170905', '2017', '7 ', '15', '����ʮ��', '���ڶ�', 'ˮƿ��', '20170901', '20170930', 248, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170906', '2017', '7 ', '16', '����ʮ��', '������', 'ˮƿ��', '20170901', '20170930', 249, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170907', '2017', '7 ', '17', '����ʮ��', '������', 'ˮƿ��', '20170901', '20170930', 250, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170908', '2017', '7 ', '18', '����ʮ��', '������', 'ˮƿ��', '20170901', '20170930', 251, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170909', '2017', '7 ', '19', '����ʮ��', '������', 'ˮƿ��', '20170901', '20170930', 252, 9, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170910', '2017', '7 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20170901', '20170930', 253, 10, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170911', '2017', '7 ', '21', '����إһ', '����һ', 'ˮƿ��', '20170901', '20170930', 254, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170912', '2017', '7 ', '22', '����إ��', '���ڶ�', 'ˮƿ��', '20170901', '20170930', 255, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170913', '2017', '7 ', '23', '����إ��', '������', 'ˮƿ��', '20170901', '20170930', 256, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170914', '2017', '7 ', '24', '����إ��', '������', 'ˮƿ��', '20170901', '20170930', 257, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170915', '2017', '7 ', '25', '����إ��', '������', 'ˮƿ��', '20170901', '20170930', 258, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170916', '2017', '7 ', '26', '����إ��', '������', 'ˮƿ��', '20170901', '20170930', 259, 16, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170917', '2017', '7 ', '27', '����إ��', '������', 'ˮƿ��', '20170901', '20170930', 260, 17, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170918', '2017', '7 ', '28', '����إ��', '����һ', 'ˮƿ��', '20170901', '20170930', 261, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170919', '2017', '7 ', '29', '����إ��', '���ڶ�', 'ˮƿ��', '20170901', '20170930', 262, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170920', '2017', '8 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20170901', '20170930', 263, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170921', '2017', '8 ', '2 ', '���³���', '������', 'ˮƿ��', '20170901', '20170930', 264, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170922', '2017', '8 ', '3 ', '���³���', '������', 'ˮƿ��', '20170901', '20170930', 265, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170923', '2017', '8 ', '4 ', '���³���', '������', 'ˮƿ��', '20170901', '20170930', 266, 23, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170924', '2017', '8 ', '5 ', '���³���', '������', 'ˮƿ��', '20170901', '20170930', 267, 24, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170925', '2017', '8 ', '6 ', '���³���', '����һ', 'ˮƿ��', '20170901', '20170930', 268, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170926', '2017', '8 ', '7 ', '���³���', '���ڶ�', 'ˮƿ��', '20170901', '20170930', 269, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170927', '2017', '8 ', '8 ', '���³���', '������', 'ˮƿ��', '20170901', '20170930', 270, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170928', '2017', '8 ', '9 ', '���³���', '������', 'ˮƿ��', '20170901', '20170930', 271, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170929', '2017', '8 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20170901', '20170930', 272, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20170930', '2017', '8 ', '11', '����ʮһ', '������', 'ˮƿ��', '20170901', '20170930', 273, 30, '1', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171001', '2017', '8 ', '12', '����ʮ��', '������', 'ˮƿ��', '20171001', '20171031', 274, 1, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171002', '2017', '8 ', '13', '����ʮ��', '����һ', 'ˮƿ��', '20171001', '20171031', 275, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171003', '2017', '8 ', '14', '����ʮ��', '���ڶ�', 'ˮƿ��', '20171001', '20171031', 276, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171004', '2017', '8 ', '15', '����ʮ��', '������', 'ˮƿ��', '20171001', '20171031', 277, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171005', '2017', '8 ', '16', '����ʮ��', '������', 'ˮƿ��', '20171001', '20171031', 278, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171006', '2017', '8 ', '17', '����ʮ��', '������', 'ˮƿ��', '20171001', '20171031', 279, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171007', '2017', '8 ', '18', '����ʮ��', '������', 'ˮƿ��', '20171001', '20171031', 280, 7, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171008', '2017', '8 ', '19', '����ʮ��', '������', 'ˮƿ��', '20171001', '20171031', 281, 8, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171009', '2017', '8 ', '20', '���¶�ʮ', '����һ', 'ˮƿ��', '20171001', '20171031', 282, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171010', '2017', '8 ', '21', '����إһ', '���ڶ�', 'ˮƿ��', '20171001', '20171031', 283, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171011', '2017', '8 ', '22', '����إ��', '������', 'ˮƿ��', '20171001', '20171031', 284, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171012', '2017', '8 ', '23', '����إ��', '������', 'ˮƿ��', '20171001', '20171031', 285, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171013', '2017', '8 ', '24', '����إ��', '������', 'ˮƿ��', '20171001', '20171031', 286, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171014', '2017', '8 ', '25', '����إ��', '������', 'ˮƿ��', '20171001', '20171031', 287, 14, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171015', '2017', '8 ', '26', '����إ��', '������', 'ˮƿ��', '20171001', '20171031', 288, 15, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171016', '2017', '8 ', '27', '����إ��', '����һ', 'ˮƿ��', '20171001', '20171031', 289, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171017', '2017', '8 ', '28', '����إ��', '���ڶ�', 'ˮƿ��', '20171001', '20171031', 290, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171018', '2017', '8 ', '29', '����إ��', '������', 'ˮƿ��', '20171001', '20171031', 291, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171019', '2017', '8 ', '30', '������ʮ', '������', 'ˮƿ��', '20171001', '20171031', 292, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171020', '2017', '9 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20171001', '20171031', 293, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171021', '2017', '9 ', '2 ', '���³���', '������', 'ˮƿ��', '20171001', '20171031', 294, 21, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171022', '2017', '9 ', '3 ', '���³���', '������', 'ˮƿ��', '20171001', '20171031', 295, 22, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171023', '2017', '9 ', '4 ', '���³���', '����һ', 'ˮƿ��', '20171001', '20171031', 296, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171024', '2017', '9 ', '5 ', '���³���', '���ڶ�', 'ˮƿ��', '20171001', '20171031', 297, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171025', '2017', '9 ', '6 ', '���³���', '������', 'ˮƿ��', '20171001', '20171031', 298, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171026', '2017', '9 ', '7 ', '���³���', '������', 'ˮƿ��', '20171001', '20171031', 299, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171027', '2017', '9 ', '8 ', '���³���', '������', 'ˮƿ��', '20171001', '20171031', 300, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171028', '2017', '9 ', '9 ', '���³���', '������', 'ˮƿ��', '20171001', '20171031', 301, 28, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171029', '2017', '9 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20171001', '20171031', 302, 29, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171030', '2017', '9 ', '11', '����ʮһ', '����һ', 'ˮƿ��', '20171001', '20171031', 303, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171031', '2017', '9 ', '12', '����ʮ��', '���ڶ�', 'ˮƿ��', '20171001', '20171031', 304, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171101', '2017', '9 ', '13', '����ʮ��', '������', 'ˮƿ��', '20171101', '20171130', 305, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171102', '2017', '9 ', '14', '����ʮ��', '������', 'ˮƿ��', '20171101', '20171130', 306, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171103', '2017', '9 ', '15', '����ʮ��', '������', 'ˮƿ��', '20171101', '20171130', 307, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171104', '2017', '9 ', '16', '����ʮ��', '������', 'ˮƿ��', '20171101', '20171130', 308, 4, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171105', '2017', '9 ', '17', '����ʮ��', '������', 'ˮƿ��', '20171101', '20171130', 309, 5, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171106', '2017', '9 ', '18', '����ʮ��', '����һ', 'ˮƿ��', '20171101', '20171130', 310, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171107', '2017', '9 ', '19', '����ʮ��', '���ڶ�', 'ˮƿ��', '20171101', '20171130', 311, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171108', '2017', '9 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20171101', '20171130', 312, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171109', '2017', '9 ', '21', '����إһ', '������', 'ˮƿ��', '20171101', '20171130', 313, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171110', '2017', '9 ', '22', '����إ��', '������', 'ˮƿ��', '20171101', '20171130', 314, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171111', '2017', '9 ', '23', '����إ��', '������', 'ˮƿ��', '20171101', '20171130', 315, 11, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171112', '2017', '9 ', '24', '����إ��', '������', 'ˮƿ��', '20171101', '20171130', 316, 12, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171113', '2017', '9 ', '25', '����إ��', '����һ', 'ˮƿ��', '20171101', '20171130', 317, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171114', '2017', '9 ', '26', '����إ��', '���ڶ�', 'ˮƿ��', '20171101', '20171130', 318, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171115', '2017', '9 ', '27', '����إ��', '������', 'ˮƿ��', '20171101', '20171130', 319, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171116', '2017', '9 ', '28', '����إ��', '������', 'ˮƿ��', '20171101', '20171130', 320, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171117', '2017', '9 ', '29', '����إ��', '������', 'ˮƿ��', '20171101', '20171130', 321, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171118', '2017', '10', '1 ', 'ʮ�³�һ', '������', 'ˮƿ��', '20171101', '20171130', 322, 18, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171119', '2017', '10', '2 ', 'ʮ�³���', '������', 'ˮƿ��', '20171101', '20171130', 323, 19, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171120', '2017', '10', '3 ', 'ʮ�³���', '����һ', 'ˮƿ��', '20171101', '20171130', 324, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171121', '2017', '10', '4 ', 'ʮ�³���', '���ڶ�', 'ˮƿ��', '20171101', '20171130', 325, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171122', '2017', '10', '5 ', 'ʮ�³���', '������', 'ˮƿ��', '20171101', '20171130', 326, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171123', '2017', '10', '6 ', 'ʮ�³���', '������', 'ˮƿ��', '20171101', '20171130', 327, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171124', '2017', '10', '7 ', 'ʮ�³���', '������', 'ˮƿ��', '20171101', '20171130', 328, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171125', '2017', '10', '8 ', 'ʮ�³���', '������', 'ˮƿ��', '20171101', '20171130', 329, 25, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171126', '2017', '10', '9 ', 'ʮ�³���', '������', 'ˮƿ��', '20171101', '20171130', 330, 26, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171127', '2017', '10', '10', 'ʮ�³�ʮ', '����һ', 'ˮƿ��', '20171101', '20171130', 331, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171128', '2017', '10', '11', 'ʮ��ʮһ', '���ڶ�', 'ˮƿ��', '20171101', '20171130', 332, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171129', '2017', '10', '12', 'ʮ��ʮ��', '������', 'ˮƿ��', '20171101', '20171130', 333, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171130', '2017', '10', '13', 'ʮ��ʮ��', '������', 'ˮƿ��', '20171101', '20171130', 334, 30, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171201', '2017', '10', '14', 'ʮ��ʮ��', '������', 'ˮƿ��', '20171201', '20171231', 335, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171202', '2017', '10', '15', 'ʮ��ʮ��', '������', 'ˮƿ��', '20171201', '20171231', 336, 2, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171203', '2017', '10', '16', 'ʮ��ʮ��', '������', 'ˮƿ��', '20171201', '20171231', 337, 3, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171204', '2017', '10', '17', 'ʮ��ʮ��', '����һ', 'ˮƿ��', '20171201', '20171231', 338, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171205', '2017', '10', '18', 'ʮ��ʮ��', '���ڶ�', 'ˮƿ��', '20171201', '20171231', 339, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171206', '2017', '10', '19', 'ʮ��ʮ��', '������', 'ˮƿ��', '20171201', '20171231', 340, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171207', '2017', '10', '20', 'ʮ�¶�ʮ', '������', 'ˮƿ��', '20171201', '20171231', 341, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171208', '2017', '10', '21', 'ʮ��إһ', '������', 'ˮƿ��', '20171201', '20171231', 342, 8, '0', null, 'N');
commit;
prompt 800 records committed...
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171209', '2017', '10', '22', 'ʮ��إ��', '������', 'ˮƿ��', '20171201', '20171231', 343, 9, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171210', '2017', '10', '23', 'ʮ��إ��', '������', 'ˮƿ��', '20171201', '20171231', 344, 10, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171211', '2017', '10', '24', 'ʮ��إ��', '����һ', 'ˮƿ��', '20171201', '20171231', 345, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171212', '2017', '10', '25', 'ʮ��إ��', '���ڶ�', 'ˮƿ��', '20171201', '20171231', 346, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171213', '2017', '10', '26', 'ʮ��إ��', '������', 'ˮƿ��', '20171201', '20171231', 347, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171214', '2017', '10', '27', 'ʮ��إ��', '������', 'ˮƿ��', '20171201', '20171231', 348, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171215', '2017', '10', '28', 'ʮ��إ��', '������', 'ˮƿ��', '20171201', '20171231', 349, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171216', '2017', '10', '29', 'ʮ��إ��', '������', 'ˮƿ��', '20171201', '20171231', 350, 16, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171217', '2017', '10', '30', 'ʮ����ʮ', '������', 'ˮƿ��', '20171201', '20171231', 351, 17, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171218', '2017', '11', '1 ', 'ʮһ�³�һ', '����һ', 'ˮƿ��', '20171201', '20171231', 352, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171219', '2017', '11', '2 ', 'ʮһ�³���', '���ڶ�', 'ˮƿ��', '20171201', '20171231', 353, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171220', '2017', '11', '3 ', 'ʮһ�³���', '������', 'ˮƿ��', '20171201', '20171231', 354, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171221', '2017', '11', '4 ', 'ʮһ�³���', '������', 'ˮƿ��', '20171201', '20171231', 355, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171222', '2017', '11', '5 ', 'ʮһ�³���', '������', 'Ħ����', '20171201', '20171231', 356, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171223', '2017', '11', '6 ', 'ʮһ�³���', '������', 'Ħ����', '20171201', '20171231', 357, 23, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171224', '2017', '11', '7 ', 'ʮһ�³���', '������', 'Ħ����', '20171201', '20171231', 358, 24, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171225', '2017', '11', '8 ', 'ʮһ�³���', '����һ', 'Ħ����', '20171201', '20171231', 359, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171226', '2017', '11', '9 ', 'ʮһ�³���', '���ڶ�', 'Ħ����', '20171201', '20171231', 360, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171227', '2017', '11', '10', 'ʮһ�³�ʮ', '������', 'Ħ����', '20171201', '20171231', 361, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171228', '2017', '11', '11', 'ʮһ��ʮһ', '������', 'Ħ����', '20171201', '20171231', 362, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171229', '2017', '11', '12', 'ʮһ��ʮ��', '������', 'Ħ����', '20171201', '20171231', 363, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171230', '2017', '11', '13', 'ʮһ��ʮ��', '������', 'Ħ����', '20171201', '20171231', 364, 30, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20171231', '2017', '11', '14', 'ʮһ��ʮ��', '������', 'Ħ����', '20171201', '20171231', 365, 31, '1', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180101', '2017', '11', '15', 'ʮһ��ʮ��', '����һ', 'Ħ����', '20180101', '20180131', 1, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180102', '2017', '11', '16', 'ʮһ��ʮ��', '���ڶ�', 'Ħ����', '20180101', '20180131', 2, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180103', '2017', '11', '17', 'ʮһ��ʮ��', '������', 'Ħ����', '20180101', '20180131', 3, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180104', '2017', '11', '18', 'ʮһ��ʮ��', '������', 'Ħ����', '20180101', '20180131', 4, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180105', '2017', '11', '19', 'ʮһ��ʮ��', '������', 'Ħ����', '20180101', '20180131', 5, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180106', '2017', '11', '20', 'ʮһ�¶�ʮ', '������', 'Ħ����', '20180101', '20180131', 6, 6, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180107', '2017', '11', '21', 'ʮһ��إһ', '������', 'Ħ����', '20180101', '20180131', 7, 7, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180108', '2017', '11', '22', 'ʮһ��إ��', '����һ', 'Ħ����', '20180101', '20180131', 8, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180109', '2017', '11', '23', 'ʮһ��إ��', '���ڶ�', 'Ħ����', '20180101', '20180131', 9, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180110', '2017', '11', '24', 'ʮһ��إ��', '������', 'Ħ����', '20180101', '20180131', 10, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180111', '2017', '11', '25', 'ʮһ��إ��', '������', 'Ħ����', '20180101', '20180131', 11, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180112', '2017', '11', '26', 'ʮһ��إ��', '������', 'Ħ����', '20180101', '20180131', 12, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180113', '2017', '11', '27', 'ʮһ��إ��', '������', 'Ħ����', '20180101', '20180131', 13, 13, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180114', '2017', '11', '28', 'ʮһ��إ��', '������', 'Ħ����', '20180101', '20180131', 14, 14, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180115', '2017', '11', '29', 'ʮһ��إ��', '����һ', 'Ħ����', '20180101', '20180131', 15, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180116', '2017', '11', '30', 'ʮһ����ʮ', '���ڶ�', 'Ħ����', '20180101', '20180131', 16, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180117', '2017', '12', '1 ', '���³�һ', '������', 'Ħ����', '20180101', '20180131', 17, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180118', '2017', '12', '2 ', '���³���', '������', 'Ħ����', '20180101', '20180131', 18, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180119', '2017', '12', '3 ', '���³���', '������', 'Ħ����', '20180101', '20180131', 19, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180120', '2017', '12', '4 ', '���³���', '������', 'Ħ����', '20180101', '20180131', 20, 20, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180121', '2017', '12', '5 ', '���³���', '������', 'Ħ����', '20180101', '20180131', 21, 21, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180122', '2017', '12', '6 ', '���³���', '����һ', 'ˮƿ��', '20180101', '20180131', 22, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180123', '2017', '12', '7 ', '���³���', '���ڶ�', 'ˮƿ��', '20180101', '20180131', 23, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180124', '2017', '12', '8 ', '���³���', '������', 'ˮƿ��', '20180101', '20180131', 24, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180125', '2017', '12', '9 ', '���³���', '������', 'ˮƿ��', '20180101', '20180131', 25, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180126', '2017', '12', '10', '���³�ʮ', '������', 'ˮƿ��', '20180101', '20180131', 26, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180127', '2017', '12', '11', '����ʮһ', '������', 'ˮƿ��', '20180101', '20180131', 27, 27, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180128', '2017', '12', '12', '����ʮ��', '������', 'ˮƿ��', '20180101', '20180131', 28, 28, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180129', '2017', '12', '13', '����ʮ��', '����һ', 'ˮƿ��', '20180101', '20180131', 29, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180130', '2017', '12', '14', '����ʮ��', '���ڶ�', 'ˮƿ��', '20180101', '20180131', 30, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180131', '2017', '12', '15', '����ʮ��', '������', 'ˮƿ��', '20180101', '20180131', 31, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180201', '2017', '12', '16', '����ʮ��', '������', 'ˮƿ��', '20180201', '20180228', 32, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180202', '2017', '12', '17', '����ʮ��', '������', 'ˮƿ��', '20180201', '20180228', 33, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180203', '2017', '12', '18', '����ʮ��', '������', 'ˮƿ��', '20180201', '20180228', 34, 3, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180204', '2017', '12', '19', '����ʮ��', '������', 'ˮƿ��', '20180201', '20180228', 35, 4, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180205', '2017', '12', '20', '���¶�ʮ', '����һ', 'ˮƿ��', '20180201', '20180228', 36, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180206', '2017', '12', '21', '����إһ', '���ڶ�', 'ˮƿ��', '20180201', '20180228', 37, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180207', '2017', '12', '22', '����إ��', '������', 'ˮƿ��', '20180201', '20180228', 38, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180208', '2017', '12', '23', '����إ��', '������', 'ˮƿ��', '20180201', '20180228', 39, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180209', '2017', '12', '24', '����إ��', '������', 'ˮƿ��', '20180201', '20180228', 40, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180210', '2017', '12', '25', '����إ��', '������', 'ˮƿ��', '20180201', '20180228', 41, 10, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180211', '2017', '12', '26', '����إ��', '������', 'ˮƿ��', '20180201', '20180228', 42, 11, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180212', '2017', '12', '27', '����إ��', '����һ', 'ˮƿ��', '20180201', '20180228', 43, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180213', '2017', '12', '28', '����إ��', '���ڶ�', 'ˮƿ��', '20180201', '20180228', 44, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180214', '2017', '12', '29', '����إ��', '������', 'ˮƿ��', '20180201', '20180228', 45, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180215', '2017', '12', '30', '������ʮ', '������', 'ˮƿ��', '20180201', '20180228', 46, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180216', '2018', '1 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20180201', '20180228', 47, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180217', '2018', '1 ', '2 ', '���³���', '������', 'ˮƿ��', '20180201', '20180228', 48, 17, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180218', '2018', '1 ', '3 ', '���³���', '������', 'ˮƿ��', '20180201', '20180228', 49, 18, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180219', '2018', '1 ', '4 ', '���³���', '����һ', 'ˮƿ��', '20180201', '20180228', 50, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180220', '2018', '1 ', '5 ', '���³���', '���ڶ�', 'ˮƿ��', '20180201', '20180228', 51, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180221', '2018', '1 ', '6 ', '���³���', '������', 'ˮƿ��', '20180201', '20180228', 52, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180222', '2018', '1 ', '7 ', '���³���', '������', 'ˮƿ��', '20180201', '20180228', 53, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180223', '2018', '1 ', '8 ', '���³���', '������', 'ˮƿ��', '20180201', '20180228', 54, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180224', '2018', '1 ', '9 ', '���³���', '������', 'ˮƿ��', '20180201', '20180228', 55, 24, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180225', '2018', '1 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20180201', '20180228', 56, 25, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180226', '2018', '1 ', '11', '����ʮһ', '����һ', 'ˮƿ��', '20180201', '20180228', 57, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180227', '2018', '1 ', '12', '����ʮ��', '���ڶ�', 'ˮƿ��', '20180201', '20180228', 58, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180228', '2018', '1 ', '13', '����ʮ��', '������', 'ˮƿ��', '20180201', '20180228', 59, 28, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180301', '2018', '1 ', '14', '����ʮ��', '������', 'ˮƿ��', '20180301', '20180331', 60, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180302', '2018', '1 ', '15', '����ʮ��', '������', 'ˮƿ��', '20180301', '20180331', 61, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180303', '2018', '1 ', '16', '����ʮ��', '������', 'ˮƿ��', '20180301', '20180331', 62, 3, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180304', '2018', '1 ', '17', '����ʮ��', '������', 'ˮƿ��', '20180301', '20180331', 63, 4, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180305', '2018', '1 ', '18', '����ʮ��', '����һ', 'ˮƿ��', '20180301', '20180331', 64, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180306', '2018', '1 ', '19', '����ʮ��', '���ڶ�', 'ˮƿ��', '20180301', '20180331', 65, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180307', '2018', '1 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20180301', '20180331', 66, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180308', '2018', '1 ', '21', '����إһ', '������', 'ˮƿ��', '20180301', '20180331', 67, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180309', '2018', '1 ', '22', '����إ��', '������', 'ˮƿ��', '20180301', '20180331', 68, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180310', '2018', '1 ', '23', '����إ��', '������', 'ˮƿ��', '20180301', '20180331', 69, 10, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180311', '2018', '1 ', '24', '����إ��', '������', 'ˮƿ��', '20180301', '20180331', 70, 11, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180312', '2018', '1 ', '25', '����إ��', '����һ', 'ˮƿ��', '20180301', '20180331', 71, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180313', '2018', '1 ', '26', '����إ��', '���ڶ�', 'ˮƿ��', '20180301', '20180331', 72, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180314', '2018', '1 ', '27', '����إ��', '������', 'ˮƿ��', '20180301', '20180331', 73, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180315', '2018', '1 ', '28', '����إ��', '������', 'ˮƿ��', '20180301', '20180331', 74, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180316', '2018', '1 ', '29', '����إ��', '������', 'ˮƿ��', '20180301', '20180331', 75, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180317', '2018', '2 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20180301', '20180331', 76, 17, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180318', '2018', '2 ', '2 ', '���³���', '������', 'ˮƿ��', '20180301', '20180331', 77, 18, '1', null, 'N');
commit;
prompt 900 records committed...
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180319', '2018', '2 ', '3 ', '���³���', '����һ', 'ˮƿ��', '20180301', '20180331', 78, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180320', '2018', '2 ', '4 ', '���³���', '���ڶ�', 'ˮƿ��', '20180301', '20180331', 79, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180321', '2018', '2 ', '5 ', '���³���', '������', 'ˮƿ��', '20180301', '20180331', 80, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180322', '2018', '2 ', '6 ', '���³���', '������', 'ˮƿ��', '20180301', '20180331', 81, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180323', '2018', '2 ', '7 ', '���³���', '������', 'ˮƿ��', '20180301', '20180331', 82, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180324', '2018', '2 ', '8 ', '���³���', '������', 'ˮƿ��', '20180301', '20180331', 83, 24, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180325', '2018', '2 ', '9 ', '���³���', '������', 'ˮƿ��', '20180301', '20180331', 84, 25, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180326', '2018', '2 ', '10', '���³�ʮ', '����һ', 'ˮƿ��', '20180301', '20180331', 85, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180327', '2018', '2 ', '11', '����ʮһ', '���ڶ�', 'ˮƿ��', '20180301', '20180331', 86, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180328', '2018', '2 ', '12', '����ʮ��', '������', 'ˮƿ��', '20180301', '20180331', 87, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180329', '2018', '2 ', '13', '����ʮ��', '������', 'ˮƿ��', '20180301', '20180331', 88, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180330', '2018', '2 ', '14', '����ʮ��', '������', 'ˮƿ��', '20180301', '20180331', 89, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180331', '2018', '2 ', '15', '����ʮ��', '������', 'ˮƿ��', '20180301', '20180331', 90, 31, '1', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180401', '2018', '2 ', '16', '����ʮ��', '������', 'ˮƿ��', '20180401', '20180430', 91, 1, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180402', '2018', '2 ', '17', '����ʮ��', '����һ', 'ˮƿ��', '20180401', '20180430', 92, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180403', '2018', '2 ', '18', '����ʮ��', '���ڶ�', 'ˮƿ��', '20180401', '20180430', 93, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180404', '2018', '2 ', '19', '����ʮ��', '������', 'ˮƿ��', '20180401', '20180430', 94, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180405', '2018', '2 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20180401', '20180430', 95, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180406', '2018', '2 ', '21', '����إһ', '������', 'ˮƿ��', '20180401', '20180430', 96, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180407', '2018', '2 ', '22', '����إ��', '������', 'ˮƿ��', '20180401', '20180430', 97, 7, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180408', '2018', '2 ', '23', '����إ��', '������', 'ˮƿ��', '20180401', '20180430', 98, 8, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180409', '2018', '2 ', '24', '����إ��', '����һ', 'ˮƿ��', '20180401', '20180430', 99, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180410', '2018', '2 ', '25', '����إ��', '���ڶ�', 'ˮƿ��', '20180401', '20180430', 100, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180411', '2018', '2 ', '26', '����إ��', '������', 'ˮƿ��', '20180401', '20180430', 101, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180412', '2018', '2 ', '27', '����إ��', '������', 'ˮƿ��', '20180401', '20180430', 102, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180413', '2018', '2 ', '28', '����إ��', '������', 'ˮƿ��', '20180401', '20180430', 103, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180414', '2018', '2 ', '29', '����إ��', '������', 'ˮƿ��', '20180401', '20180430', 104, 14, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180415', '2018', '2 ', '30', '������ʮ', '������', 'ˮƿ��', '20180401', '20180430', 105, 15, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180717', '2018', '6 ', '5 ', '���³���', '���ڶ�', 'ˮƿ��', '20180701', '20180731', 198, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180718', '2018', '6 ', '6 ', '���³���', '������', 'ˮƿ��', '20180701', '20180731', 199, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180719', '2018', '6 ', '7 ', '���³���', '������', 'ˮƿ��', '20180701', '20180731', 200, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180720', '2018', '6 ', '8 ', '���³���', '������', 'ˮƿ��', '20180701', '20180731', 201, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180721', '2018', '6 ', '9 ', '���³���', '������', 'ˮƿ��', '20180701', '20180731', 202, 21, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180722', '2018', '6 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20180701', '20180731', 203, 22, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180723', '2018', '6 ', '11', '����ʮһ', '����һ', 'ˮƿ��', '20180701', '20180731', 204, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180724', '2018', '6 ', '12', '����ʮ��', '���ڶ�', 'ˮƿ��', '20180701', '20180731', 205, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180725', '2018', '6 ', '13', '����ʮ��', '������', 'ˮƿ��', '20180701', '20180731', 206, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180726', '2018', '6 ', '14', '����ʮ��', '������', 'ˮƿ��', '20180701', '20180731', 207, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180727', '2018', '6 ', '15', '����ʮ��', '������', 'ˮƿ��', '20180701', '20180731', 208, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180728', '2018', '6 ', '16', '����ʮ��', '������', 'ˮƿ��', '20180701', '20180731', 209, 28, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180729', '2018', '6 ', '17', '����ʮ��', '������', 'ˮƿ��', '20180701', '20180731', 210, 29, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180730', '2018', '6 ', '18', '����ʮ��', '����һ', 'ˮƿ��', '20180701', '20180731', 211, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180731', '2018', '6 ', '19', '����ʮ��', '���ڶ�', 'ˮƿ��', '20180701', '20180731', 212, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180801', '2018', '6 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20180801', '20180831', 213, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180802', '2018', '6 ', '21', '����إһ', '������', 'ˮƿ��', '20180801', '20180831', 214, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180803', '2018', '6 ', '22', '����إ��', '������', 'ˮƿ��', '20180801', '20180831', 215, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180804', '2018', '6 ', '23', '����إ��', '������', 'ˮƿ��', '20180801', '20180831', 216, 4, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180805', '2018', '6 ', '24', '����إ��', '������', 'ˮƿ��', '20180801', '20180831', 217, 5, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180806', '2018', '6 ', '25', '����إ��', '����һ', 'ˮƿ��', '20180801', '20180831', 218, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180807', '2018', '6 ', '26', '����إ��', '���ڶ�', 'ˮƿ��', '20180801', '20180831', 219, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180808', '2018', '6 ', '27', '����إ��', '������', 'ˮƿ��', '20180801', '20180831', 220, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180809', '2018', '6 ', '28', '����إ��', '������', 'ˮƿ��', '20180801', '20180831', 221, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180810', '2018', '6 ', '29', '����إ��', '������', 'ˮƿ��', '20180801', '20180831', 222, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180811', '2018', '7 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20180801', '20180831', 223, 11, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180812', '2018', '7 ', '2 ', '���³���', '������', 'ˮƿ��', '20180801', '20180831', 224, 12, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180813', '2018', '7 ', '3 ', '���³���', '����һ', 'ˮƿ��', '20180801', '20180831', 225, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180814', '2018', '7 ', '4 ', '���³���', '���ڶ�', 'ˮƿ��', '20180801', '20180831', 226, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180815', '2018', '7 ', '5 ', '���³���', '������', 'ˮƿ��', '20180801', '20180831', 227, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180816', '2018', '7 ', '6 ', '���³���', '������', 'ˮƿ��', '20180801', '20180831', 228, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180817', '2018', '7 ', '7 ', '���³���', '������', 'ˮƿ��', '20180801', '20180831', 229, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180818', '2018', '7 ', '8 ', '���³���', '������', 'ˮƿ��', '20180801', '20180831', 230, 18, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180819', '2018', '7 ', '9 ', '���³���', '������', 'ˮƿ��', '20180801', '20180831', 231, 19, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180820', '2018', '7 ', '10', '���³�ʮ', '����һ', 'ˮƿ��', '20180801', '20180831', 232, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180821', '2018', '7 ', '11', '����ʮһ', '���ڶ�', 'ˮƿ��', '20180801', '20180831', 233, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180822', '2018', '7 ', '12', '����ʮ��', '������', 'ˮƿ��', '20180801', '20180831', 234, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180823', '2018', '7 ', '13', '����ʮ��', '������', 'ˮƿ��', '20180801', '20180831', 235, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180824', '2018', '7 ', '14', '����ʮ��', '������', 'ˮƿ��', '20180801', '20180831', 236, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180825', '2018', '7 ', '15', '����ʮ��', '������', 'ˮƿ��', '20180801', '20180831', 237, 25, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180826', '2018', '7 ', '16', '����ʮ��', '������', 'ˮƿ��', '20180801', '20180831', 238, 26, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180827', '2018', '7 ', '17', '����ʮ��', '����һ', 'ˮƿ��', '20180801', '20180831', 239, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180828', '2018', '7 ', '18', '����ʮ��', '���ڶ�', 'ˮƿ��', '20180801', '20180831', 240, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180829', '2018', '7 ', '19', '����ʮ��', '������', 'ˮƿ��', '20180801', '20180831', 241, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180830', '2018', '7 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20180801', '20180831', 242, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180831', '2018', '7 ', '21', '����إһ', '������', 'ˮƿ��', '20180801', '20180831', 243, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180901', '2018', '7 ', '22', '����إ��', '������', 'ˮƿ��', '20180901', '20180930', 244, 1, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180902', '2018', '7 ', '23', '����إ��', '������', 'ˮƿ��', '20180901', '20180930', 245, 2, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180903', '2018', '7 ', '24', '����إ��', '����һ', 'ˮƿ��', '20180901', '20180930', 246, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180904', '2018', '7 ', '25', '����إ��', '���ڶ�', 'ˮƿ��', '20180901', '20180930', 247, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180905', '2018', '7 ', '26', '����إ��', '������', 'ˮƿ��', '20180901', '20180930', 248, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180906', '2018', '7 ', '27', '����إ��', '������', 'ˮƿ��', '20180901', '20180930', 249, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180907', '2018', '7 ', '28', '����إ��', '������', 'ˮƿ��', '20180901', '20180930', 250, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180908', '2018', '7 ', '29', '����إ��', '������', 'ˮƿ��', '20180901', '20180930', 251, 8, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180909', '2018', '7 ', '30', '������ʮ', '������', 'ˮƿ��', '20180901', '20180930', 252, 9, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180910', '2018', '8 ', '1 ', '���³�һ', '����һ', 'ˮƿ��', '20180901', '20180930', 253, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180911', '2018', '8 ', '2 ', '���³���', '���ڶ�', 'ˮƿ��', '20180901', '20180930', 254, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180912', '2018', '8 ', '3 ', '���³���', '������', 'ˮƿ��', '20180901', '20180930', 255, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180913', '2018', '8 ', '4 ', '���³���', '������', 'ˮƿ��', '20180901', '20180930', 256, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180914', '2018', '8 ', '5 ', '���³���', '������', 'ˮƿ��', '20180901', '20180930', 257, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180915', '2018', '8 ', '6 ', '���³���', '������', 'ˮƿ��', '20180901', '20180930', 258, 15, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180916', '2018', '8 ', '7 ', '���³���', '������', 'ˮƿ��', '20180901', '20180930', 259, 16, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180917', '2018', '8 ', '8 ', '���³���', '����һ', 'ˮƿ��', '20180901', '20180930', 260, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180918', '2018', '8 ', '9 ', '���³���', '���ڶ�', 'ˮƿ��', '20180901', '20180930', 261, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180919', '2018', '8 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20180901', '20180930', 262, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180920', '2018', '8 ', '11', '����ʮһ', '������', 'ˮƿ��', '20180901', '20180930', 263, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180921', '2018', '8 ', '12', '����ʮ��', '������', 'ˮƿ��', '20180901', '20180930', 264, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180922', '2018', '8 ', '13', '����ʮ��', '������', 'ˮƿ��', '20180901', '20180930', 265, 22, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180923', '2018', '8 ', '14', '����ʮ��', '������', 'ˮƿ��', '20180901', '20180930', 266, 23, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180924', '2018', '8 ', '15', '����ʮ��', '����һ', 'ˮƿ��', '20180901', '20180930', 267, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180925', '2018', '8 ', '16', '����ʮ��', '���ڶ�', 'ˮƿ��', '20180901', '20180930', 268, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180926', '2018', '8 ', '17', '����ʮ��', '������', 'ˮƿ��', '20180901', '20180930', 269, 26, '0', null, 'N');
commit;
prompt 1000 records committed...
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180927', '2018', '8 ', '18', '����ʮ��', '������', 'ˮƿ��', '20180901', '20180930', 270, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180928', '2018', '8 ', '19', '����ʮ��', '������', 'ˮƿ��', '20180901', '20180930', 271, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180929', '2018', '8 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20180901', '20180930', 272, 29, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20180930', '2018', '8 ', '21', '����إһ', '������', 'ˮƿ��', '20180901', '20180930', 273, 30, '1', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181001', '2018', '8 ', '22', '����إ��', '����һ', 'ˮƿ��', '20181001', '20181031', 274, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181002', '2018', '8 ', '23', '����إ��', '���ڶ�', 'ˮƿ��', '20181001', '20181031', 275, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181003', '2018', '8 ', '24', '����إ��', '������', 'ˮƿ��', '20181001', '20181031', 276, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181004', '2018', '8 ', '25', '����إ��', '������', 'ˮƿ��', '20181001', '20181031', 277, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181005', '2018', '8 ', '26', '����إ��', '������', 'ˮƿ��', '20181001', '20181031', 278, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181006', '2018', '8 ', '27', '����إ��', '������', 'ˮƿ��', '20181001', '20181031', 279, 6, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181007', '2018', '8 ', '28', '����إ��', '������', 'ˮƿ��', '20181001', '20181031', 280, 7, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181008', '2018', '8 ', '29', '����إ��', '����һ', 'ˮƿ��', '20181001', '20181031', 281, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181009', '2018', '9 ', '1 ', '���³�һ', '���ڶ�', 'ˮƿ��', '20181001', '20181031', 282, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181010', '2018', '9 ', '2 ', '���³���', '������', 'ˮƿ��', '20181001', '20181031', 283, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181011', '2018', '9 ', '3 ', '���³���', '������', 'ˮƿ��', '20181001', '20181031', 284, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181012', '2018', '9 ', '4 ', '���³���', '������', 'ˮƿ��', '20181001', '20181031', 285, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181013', '2018', '9 ', '5 ', '���³���', '������', 'ˮƿ��', '20181001', '20181031', 286, 13, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181014', '2018', '9 ', '6 ', '���³���', '������', 'ˮƿ��', '20181001', '20181031', 287, 14, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181015', '2018', '9 ', '7 ', '���³���', '����һ', 'ˮƿ��', '20181001', '20181031', 288, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181016', '2018', '9 ', '8 ', '���³���', '���ڶ�', 'ˮƿ��', '20181001', '20181031', 289, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181017', '2018', '9 ', '9 ', '���³���', '������', 'ˮƿ��', '20181001', '20181031', 290, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181018', '2018', '9 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20181001', '20181031', 291, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190119', '2018', '12', '14', '����ʮ��', '������', 'Ħ����', '20190101', '20190131', 19, 19, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190120', '2018', '12', '15', '����ʮ��', '������', 'Ħ����', '20190101', '20190131', 20, 20, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190121', '2018', '12', '16', '����ʮ��', '����һ', 'Ħ����', '20190101', '20190131', 21, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190122', '2018', '12', '17', '����ʮ��', '���ڶ�', 'ˮƿ��', '20190101', '20190131', 22, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190123', '2018', '12', '18', '����ʮ��', '������', 'ˮƿ��', '20190101', '20190131', 23, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190124', '2018', '12', '19', '����ʮ��', '������', 'ˮƿ��', '20190101', '20190131', 24, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190125', '2018', '12', '20', '���¶�ʮ', '������', 'ˮƿ��', '20190101', '20190131', 25, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190126', '2018', '12', '21', '����إһ', '������', 'ˮƿ��', '20190101', '20190131', 26, 26, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190127', '2018', '12', '22', '����إ��', '������', 'ˮƿ��', '20190101', '20190131', 27, 27, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190128', '2018', '12', '23', '����إ��', '����һ', 'ˮƿ��', '20190101', '20190131', 28, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190129', '2018', '12', '24', '����إ��', '���ڶ�', 'ˮƿ��', '20190101', '20190131', 29, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190130', '2018', '12', '25', '����إ��', '������', 'ˮƿ��', '20190101', '20190131', 30, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190131', '2018', '12', '26', '����إ��', '������', 'ˮƿ��', '20190101', '20190131', 31, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190201', '2018', '12', '27', '����إ��', '������', 'ˮƿ��', '20190201', '20190228', 32, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190202', '2018', '12', '28', '����إ��', '������', 'ˮƿ��', '20190201', '20190228', 33, 2, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190203', '2018', '12', '29', '����إ��', '������', 'ˮƿ��', '20190201', '20190228', 34, 3, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190204', '2018', '12', '30', '������ʮ', '����һ', 'ˮƿ��', '20190201', '20190228', 35, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190205', '2019', '1 ', '1 ', '���³�һ', '���ڶ�', 'ˮƿ��', '20190201', '20190228', 36, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190206', '2019', '1 ', '2 ', '���³���', '������', 'ˮƿ��', '20190201', '20190228', 37, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190207', '2019', '1 ', '3 ', '���³���', '������', 'ˮƿ��', '20190201', '20190228', 38, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190208', '2019', '1 ', '4 ', '���³���', '������', 'ˮƿ��', '20190201', '20190228', 39, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190209', '2019', '1 ', '5 ', '���³���', '������', 'ˮƿ��', '20190201', '20190228', 40, 9, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190210', '2019', '1 ', '6 ', '���³���', '������', 'ˮƿ��', '20190201', '20190228', 41, 10, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190211', '2019', '1 ', '7 ', '���³���', '����һ', 'ˮƿ��', '20190201', '20190228', 42, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190212', '2019', '1 ', '8 ', '���³���', '���ڶ�', 'ˮƿ��', '20190201', '20190228', 43, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190213', '2019', '1 ', '9 ', '���³���', '������', 'ˮƿ��', '20190201', '20190228', 44, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190214', '2019', '1 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20190201', '20190228', 45, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190215', '2019', '1 ', '11', '����ʮһ', '������', 'ˮƿ��', '20190201', '20190228', 46, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190216', '2019', '1 ', '12', '����ʮ��', '������', 'ˮƿ��', '20190201', '20190228', 47, 16, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190217', '2019', '1 ', '13', '����ʮ��', '������', 'ˮƿ��', '20190201', '20190228', 48, 17, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190218', '2019', '1 ', '14', '����ʮ��', '����һ', 'ˮƿ��', '20190201', '20190228', 49, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190219', '2019', '1 ', '15', '����ʮ��', '���ڶ�', 'ˮƿ��', '20190201', '20190228', 50, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190220', '2019', '1 ', '16', '����ʮ��', '������', 'ˮƿ��', '20190201', '20190228', 51, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190221', '2019', '1 ', '17', '����ʮ��', '������', 'ˮƿ��', '20190201', '20190228', 52, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190222', '2019', '1 ', '18', '����ʮ��', '������', 'ˮƿ��', '20190201', '20190228', 53, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190223', '2019', '1 ', '19', '����ʮ��', '������', 'ˮƿ��', '20190201', '20190228', 54, 23, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190224', '2019', '1 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20190201', '20190228', 55, 24, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190225', '2019', '1 ', '21', '����إһ', '����һ', 'ˮƿ��', '20190201', '20190228', 56, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190226', '2019', '1 ', '22', '����إ��', '���ڶ�', 'ˮƿ��', '20190201', '20190228', 57, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190227', '2019', '1 ', '23', '����إ��', '������', 'ˮƿ��', '20190201', '20190228', 58, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190228', '2019', '1 ', '24', '����إ��', '������', 'ˮƿ��', '20190201', '20190228', 59, 28, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190301', '2019', '1 ', '25', '����إ��', '������', 'ˮƿ��', '20190301', '20190331', 60, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190302', '2019', '1 ', '26', '����إ��', '������', 'ˮƿ��', '20190301', '20190331', 61, 2, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190303', '2019', '1 ', '27', '����إ��', '������', 'ˮƿ��', '20190301', '20190331', 62, 3, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190304', '2019', '1 ', '28', '����إ��', '����һ', 'ˮƿ��', '20190301', '20190331', 63, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190305', '2019', '1 ', '29', '����إ��', '���ڶ�', 'ˮƿ��', '20190301', '20190331', 64, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190306', '2019', '1 ', '30', '������ʮ', '������', 'ˮƿ��', '20190301', '20190331', 65, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190307', '2019', '2 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20190301', '20190331', 66, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190308', '2019', '2 ', '2 ', '���³���', '������', 'ˮƿ��', '20190301', '20190331', 67, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190309', '2019', '2 ', '3 ', '���³���', '������', 'ˮƿ��', '20190301', '20190331', 68, 9, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190310', '2019', '2 ', '4 ', '���³���', '������', 'ˮƿ��', '20190301', '20190331', 69, 10, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190311', '2019', '2 ', '5 ', '���³���', '����һ', 'ˮƿ��', '20190301', '20190331', 70, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190312', '2019', '2 ', '6 ', '���³���', '���ڶ�', 'ˮƿ��', '20190301', '20190331', 71, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190313', '2019', '2 ', '7 ', '���³���', '������', 'ˮƿ��', '20190301', '20190331', 72, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190314', '2019', '2 ', '8 ', '���³���', '������', 'ˮƿ��', '20190301', '20190331', 73, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190315', '2019', '2 ', '9 ', '���³���', '������', 'ˮƿ��', '20190301', '20190331', 74, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190316', '2019', '2 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20190301', '20190331', 75, 16, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190317', '2019', '2 ', '11', '����ʮһ', '������', 'ˮƿ��', '20190301', '20190331', 76, 17, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190318', '2019', '2 ', '12', '����ʮ��', '����һ', 'ˮƿ��', '20190301', '20190331', 77, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190319', '2019', '2 ', '13', '����ʮ��', '���ڶ�', 'ˮƿ��', '20190301', '20190331', 78, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190320', '2019', '2 ', '14', '����ʮ��', '������', 'ˮƿ��', '20190301', '20190331', 79, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190321', '2019', '2 ', '15', '����ʮ��', '������', 'ˮƿ��', '20190301', '20190331', 80, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190322', '2019', '2 ', '16', '����ʮ��', '������', 'ˮƿ��', '20190301', '20190331', 81, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190323', '2019', '2 ', '17', '����ʮ��', '������', 'ˮƿ��', '20190301', '20190331', 82, 23, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190324', '2019', '2 ', '18', '����ʮ��', '������', 'ˮƿ��', '20190301', '20190331', 83, 24, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190325', '2019', '2 ', '19', '����ʮ��', '����һ', 'ˮƿ��', '20190301', '20190331', 84, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190326', '2019', '2 ', '20', '���¶�ʮ', '���ڶ�', 'ˮƿ��', '20190301', '20190331', 85, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190327', '2019', '2 ', '21', '����إһ', '������', 'ˮƿ��', '20190301', '20190331', 86, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190328', '2019', '2 ', '22', '����إ��', '������', 'ˮƿ��', '20190301', '20190331', 87, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190329', '2019', '2 ', '23', '����إ��', '������', 'ˮƿ��', '20190301', '20190331', 88, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190330', '2019', '2 ', '24', '����إ��', '������', 'ˮƿ��', '20190301', '20190331', 89, 30, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190331', '2019', '2 ', '25', '����إ��', '������', 'ˮƿ��', '20190301', '20190331', 90, 31, '1', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190401', '2019', '2 ', '26', '����إ��', '����һ', 'ˮƿ��', '20190401', '20190430', 91, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190402', '2019', '2 ', '27', '����إ��', '���ڶ�', 'ˮƿ��', '20190401', '20190430', 92, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190403', '2019', '2 ', '28', '����إ��', '������', 'ˮƿ��', '20190401', '20190430', 93, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190404', '2019', '2 ', '29', '����إ��', '������', 'ˮƿ��', '20190401', '20190430', 94, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190405', '2019', '3 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20190401', '20190430', 95, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190406', '2019', '3 ', '2 ', '���³���', '������', 'ˮƿ��', '20190401', '20190430', 96, 6, '1', null, 'N');
commit;
prompt 1100 records committed...
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190407', '2019', '3 ', '3 ', '���³���', '������', 'ˮƿ��', '20190401', '20190430', 97, 7, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190408', '2019', '3 ', '4 ', '���³���', '����һ', 'ˮƿ��', '20190401', '20190430', 98, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190409', '2019', '3 ', '5 ', '���³���', '���ڶ�', 'ˮƿ��', '20190401', '20190430', 99, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190410', '2019', '3 ', '6 ', '���³���', '������', 'ˮƿ��', '20190401', '20190430', 100, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190411', '2019', '3 ', '7 ', '���³���', '������', 'ˮƿ��', '20190401', '20190430', 101, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190412', '2019', '3 ', '8 ', '���³���', '������', 'ˮƿ��', '20190401', '20190430', 102, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190413', '2019', '3 ', '9 ', '���³���', '������', 'ˮƿ��', '20190401', '20190430', 103, 13, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190414', '2019', '3 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20190401', '20190430', 104, 14, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190415', '2019', '3 ', '11', '����ʮһ', '����һ', 'ˮƿ��', '20190401', '20190430', 105, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190416', '2019', '3 ', '12', '����ʮ��', '���ڶ�', 'ˮƿ��', '20190401', '20190430', 106, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190417', '2019', '3 ', '13', '����ʮ��', '������', 'ˮƿ��', '20190401', '20190430', 107, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190418', '2019', '3 ', '14', '����ʮ��', '������', 'ˮƿ��', '20190401', '20190430', 108, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190419', '2019', '3 ', '15', '����ʮ��', '������', 'ˮƿ��', '20190401', '20190430', 109, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190420', '2019', '3 ', '16', '����ʮ��', '������', 'ˮƿ��', '20190401', '20190430', 110, 20, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190421', '2019', '3 ', '17', '����ʮ��', '������', 'ˮƿ��', '20190401', '20190430', 111, 21, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181019', '2018', '9 ', '11', '����ʮһ', '������', 'ˮƿ��', '20181001', '20181031', 292, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181020', '2018', '9 ', '12', '����ʮ��', '������', 'ˮƿ��', '20181001', '20181031', 293, 20, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181021', '2018', '9 ', '13', '����ʮ��', '������', 'ˮƿ��', '20181001', '20181031', 294, 21, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181022', '2018', '9 ', '14', '����ʮ��', '����һ', 'ˮƿ��', '20181001', '20181031', 295, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181023', '2018', '9 ', '15', '����ʮ��', '���ڶ�', 'ˮƿ��', '20181001', '20181031', 296, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181024', '2018', '9 ', '16', '����ʮ��', '������', 'ˮƿ��', '20181001', '20181031', 297, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181025', '2018', '9 ', '17', '����ʮ��', '������', 'ˮƿ��', '20181001', '20181031', 298, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181026', '2018', '9 ', '18', '����ʮ��', '������', 'ˮƿ��', '20181001', '20181031', 299, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181027', '2018', '9 ', '19', '����ʮ��', '������', 'ˮƿ��', '20181001', '20181031', 300, 27, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181028', '2018', '9 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20181001', '20181031', 301, 28, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181029', '2018', '9 ', '21', '����إһ', '����һ', 'ˮƿ��', '20181001', '20181031', 302, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181030', '2018', '9 ', '22', '����إ��', '���ڶ�', 'ˮƿ��', '20181001', '20181031', 303, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181031', '2018', '9 ', '23', '����إ��', '������', 'ˮƿ��', '20181001', '20181031', 304, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181101', '2018', '9 ', '24', '����إ��', '������', 'ˮƿ��', '20181101', '20181130', 305, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181102', '2018', '9 ', '25', '����إ��', '������', 'ˮƿ��', '20181101', '20181130', 306, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181103', '2018', '9 ', '26', '����إ��', '������', 'ˮƿ��', '20181101', '20181130', 307, 3, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181104', '2018', '9 ', '27', '����إ��', '������', 'ˮƿ��', '20181101', '20181130', 308, 4, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181105', '2018', '9 ', '28', '����إ��', '����һ', 'ˮƿ��', '20181101', '20181130', 309, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181106', '2018', '9 ', '29', '����إ��', '���ڶ�', 'ˮƿ��', '20181101', '20181130', 310, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181107', '2018', '9 ', '30', '������ʮ', '������', 'ˮƿ��', '20181101', '20181130', 311, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181108', '2018', '10', '1 ', 'ʮ�³�һ', '������', 'ˮƿ��', '20181101', '20181130', 312, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181109', '2018', '10', '2 ', 'ʮ�³���', '������', 'ˮƿ��', '20181101', '20181130', 313, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181110', '2018', '10', '3 ', 'ʮ�³���', '������', 'ˮƿ��', '20181101', '20181130', 314, 10, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181111', '2018', '10', '4 ', 'ʮ�³���', '������', 'ˮƿ��', '20181101', '20181130', 315, 11, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181112', '2018', '10', '5 ', 'ʮ�³���', '����һ', 'ˮƿ��', '20181101', '20181130', 316, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181113', '2018', '10', '6 ', 'ʮ�³���', '���ڶ�', 'ˮƿ��', '20181101', '20181130', 317, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181114', '2018', '10', '7 ', 'ʮ�³���', '������', 'ˮƿ��', '20181101', '20181130', 318, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181115', '2018', '10', '8 ', 'ʮ�³���', '������', 'ˮƿ��', '20181101', '20181130', 319, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181116', '2018', '10', '9 ', 'ʮ�³���', '������', 'ˮƿ��', '20181101', '20181130', 320, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181117', '2018', '10', '10', 'ʮ�³�ʮ', '������', 'ˮƿ��', '20181101', '20181130', 321, 17, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181118', '2018', '10', '11', 'ʮ��ʮһ', '������', 'ˮƿ��', '20181101', '20181130', 322, 18, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181119', '2018', '10', '12', 'ʮ��ʮ��', '����һ', 'ˮƿ��', '20181101', '20181130', 323, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181120', '2018', '10', '13', 'ʮ��ʮ��', '���ڶ�', 'ˮƿ��', '20181101', '20181130', 324, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181121', '2018', '10', '14', 'ʮ��ʮ��', '������', 'ˮƿ��', '20181101', '20181130', 325, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181122', '2018', '10', '15', 'ʮ��ʮ��', '������', 'ˮƿ��', '20181101', '20181130', 326, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181123', '2018', '10', '16', 'ʮ��ʮ��', '������', 'ˮƿ��', '20181101', '20181130', 327, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181124', '2018', '10', '17', 'ʮ��ʮ��', '������', 'ˮƿ��', '20181101', '20181130', 328, 24, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181125', '2018', '10', '18', 'ʮ��ʮ��', '������', 'ˮƿ��', '20181101', '20181130', 329, 25, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181126', '2018', '10', '19', 'ʮ��ʮ��', '����һ', 'ˮƿ��', '20181101', '20181130', 330, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181127', '2018', '10', '20', 'ʮ�¶�ʮ', '���ڶ�', 'ˮƿ��', '20181101', '20181130', 331, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181128', '2018', '10', '21', 'ʮ��إһ', '������', 'ˮƿ��', '20181101', '20181130', 332, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181129', '2018', '10', '22', 'ʮ��إ��', '������', 'ˮƿ��', '20181101', '20181130', 333, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181130', '2018', '10', '23', 'ʮ��إ��', '������', 'ˮƿ��', '20181101', '20181130', 334, 30, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181201', '2018', '10', '24', 'ʮ��إ��', '������', 'ˮƿ��', '20181201', '20181231', 335, 1, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181202', '2018', '10', '25', 'ʮ��إ��', '������', 'ˮƿ��', '20181201', '20181231', 336, 2, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181203', '2018', '10', '26', 'ʮ��إ��', '����һ', 'ˮƿ��', '20181201', '20181231', 337, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181204', '2018', '10', '27', 'ʮ��إ��', '���ڶ�', 'ˮƿ��', '20181201', '20181231', 338, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181205', '2018', '10', '28', 'ʮ��إ��', '������', 'ˮƿ��', '20181201', '20181231', 339, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181206', '2018', '10', '29', 'ʮ��إ��', '������', 'ˮƿ��', '20181201', '20181231', 340, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181207', '2018', '11', '1 ', 'ʮһ�³�һ', '������', 'ˮƿ��', '20181201', '20181231', 341, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181208', '2018', '11', '2 ', 'ʮһ�³���', '������', 'ˮƿ��', '20181201', '20181231', 342, 8, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181209', '2018', '11', '3 ', 'ʮһ�³���', '������', 'ˮƿ��', '20181201', '20181231', 343, 9, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181210', '2018', '11', '4 ', 'ʮһ�³���', '����һ', 'ˮƿ��', '20181201', '20181231', 344, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181211', '2018', '11', '5 ', 'ʮһ�³���', '���ڶ�', 'ˮƿ��', '20181201', '20181231', 345, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181212', '2018', '11', '6 ', 'ʮһ�³���', '������', 'ˮƿ��', '20181201', '20181231', 346, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181213', '2018', '11', '7 ', 'ʮһ�³���', '������', 'ˮƿ��', '20181201', '20181231', 347, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181214', '2018', '11', '8 ', 'ʮһ�³���', '������', 'ˮƿ��', '20181201', '20181231', 348, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181215', '2018', '11', '9 ', 'ʮһ�³���', '������', 'ˮƿ��', '20181201', '20181231', 349, 15, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181216', '2018', '11', '10', 'ʮһ�³�ʮ', '������', 'ˮƿ��', '20181201', '20181231', 350, 16, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181217', '2018', '11', '11', 'ʮһ��ʮһ', '����һ', 'ˮƿ��', '20181201', '20181231', 351, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181218', '2018', '11', '12', 'ʮһ��ʮ��', '���ڶ�', 'ˮƿ��', '20181201', '20181231', 352, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181219', '2018', '11', '13', 'ʮһ��ʮ��', '������', 'ˮƿ��', '20181201', '20181231', 353, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181220', '2018', '11', '14', 'ʮһ��ʮ��', '������', 'ˮƿ��', '20181201', '20181231', 354, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181221', '2018', '11', '15', 'ʮһ��ʮ��', '������', 'ˮƿ��', '20181201', '20181231', 355, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181222', '2018', '11', '16', 'ʮһ��ʮ��', '������', 'Ħ����', '20181201', '20181231', 356, 22, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181223', '2018', '11', '17', 'ʮһ��ʮ��', '������', 'Ħ����', '20181201', '20181231', 357, 23, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181224', '2018', '11', '18', 'ʮһ��ʮ��', '����һ', 'Ħ����', '20181201', '20181231', 358, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181225', '2018', '11', '19', 'ʮһ��ʮ��', '���ڶ�', 'Ħ����', '20181201', '20181231', 359, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181226', '2018', '11', '20', 'ʮһ�¶�ʮ', '������', 'Ħ����', '20181201', '20181231', 360, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181227', '2018', '11', '21', 'ʮһ��إһ', '������', 'Ħ����', '20181201', '20181231', 361, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181228', '2018', '11', '22', 'ʮһ��إ��', '������', 'Ħ����', '20181201', '20181231', 362, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181229', '2018', '11', '23', 'ʮһ��إ��', '������', 'Ħ����', '20181201', '20181231', 363, 29, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181230', '2018', '11', '24', 'ʮһ��إ��', '������', 'Ħ����', '20181201', '20181231', 364, 30, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20181231', '2018', '11', '25', 'ʮһ��إ��', '����һ', 'Ħ����', '20181201', '20181231', 365, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190101', '2018', '11', '26', 'ʮһ��إ��', '���ڶ�', 'Ħ����', '20190101', '20190131', 1, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190102', '2018', '11', '27', 'ʮһ��إ��', '������', 'Ħ����', '20190101', '20190131', 2, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190103', '2018', '11', '28', 'ʮһ��إ��', '������', 'Ħ����', '20190101', '20190131', 3, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190104', '2018', '11', '29', 'ʮһ��إ��', '������', 'Ħ����', '20190101', '20190131', 4, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190105', '2018', '11', '30', 'ʮһ����ʮ', '������', 'Ħ����', '20190101', '20190131', 5, 5, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190106', '2018', '12', '1 ', '���³�һ', '������', 'Ħ����', '20190101', '20190131', 6, 6, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190107', '2018', '12', '2 ', '���³���', '����һ', 'Ħ����', '20190101', '20190131', 7, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190108', '2018', '12', '3 ', '���³���', '���ڶ�', 'Ħ����', '20190101', '20190131', 8, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190109', '2018', '12', '4 ', '���³���', '������', 'Ħ����', '20190101', '20190131', 9, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190110', '2018', '12', '5 ', '���³���', '������', 'Ħ����', '20190101', '20190131', 10, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190111', '2018', '12', '6 ', '���³���', '������', 'Ħ����', '20190101', '20190131', 11, 11, '0', null, 'N');
commit;
prompt 1200 records committed...
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190112', '2018', '12', '7 ', '���³���', '������', 'Ħ����', '20190101', '20190131', 12, 12, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190113', '2018', '12', '8 ', '���³���', '������', 'Ħ����', '20190101', '20190131', 13, 13, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190114', '2018', '12', '9 ', '���³���', '����һ', 'Ħ����', '20190101', '20190131', 14, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190115', '2018', '12', '10', '���³�ʮ', '���ڶ�', 'Ħ����', '20190101', '20190131', 15, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190116', '2018', '12', '11', '����ʮһ', '������', 'Ħ����', '20190101', '20190131', 16, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190117', '2018', '12', '12', '����ʮ��', '������', 'Ħ����', '20190101', '20190131', 17, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190118', '2018', '12', '13', '����ʮ��', '������', 'Ħ����', '20190101', '20190131', 18, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190422', '2019', '3 ', '18', '����ʮ��', '����һ', 'ˮƿ��', '20190401', '20190430', 112, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190423', '2019', '3 ', '19', '����ʮ��', '���ڶ�', 'ˮƿ��', '20190401', '20190430', 113, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190424', '2019', '3 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20190401', '20190430', 114, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190425', '2019', '3 ', '21', '����إһ', '������', 'ˮƿ��', '20190401', '20190430', 115, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190426', '2019', '3 ', '22', '����إ��', '������', 'ˮƿ��', '20190401', '20190430', 116, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190427', '2019', '3 ', '23', '����إ��', '������', 'ˮƿ��', '20190401', '20190430', 117, 27, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190428', '2019', '3 ', '24', '����إ��', '������', 'ˮƿ��', '20190401', '20190430', 118, 28, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190429', '2019', '3 ', '25', '����إ��', '����һ', 'ˮƿ��', '20190401', '20190430', 119, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190430', '2019', '3 ', '26', '����إ��', '���ڶ�', 'ˮƿ��', '20190401', '20190430', 120, 30, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190501', '2019', '3 ', '27', '����إ��', '������', 'ˮƿ��', '20190501', '20190531', 121, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190502', '2019', '3 ', '28', '����إ��', '������', 'ˮƿ��', '20190501', '20190531', 122, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190503', '2019', '3 ', '29', '����إ��', '������', 'ˮƿ��', '20190501', '20190531', 123, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190504', '2019', '3 ', '30', '������ʮ', '������', 'ˮƿ��', '20190501', '20190531', 124, 4, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190505', '2019', '4 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20190501', '20190531', 125, 5, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190506', '2019', '4 ', '2 ', '���³���', '����һ', 'ˮƿ��', '20190501', '20190531', 126, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190507', '2019', '4 ', '3 ', '���³���', '���ڶ�', 'ˮƿ��', '20190501', '20190531', 127, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190508', '2019', '4 ', '4 ', '���³���', '������', 'ˮƿ��', '20190501', '20190531', 128, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190509', '2019', '4 ', '5 ', '���³���', '������', 'ˮƿ��', '20190501', '20190531', 129, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190510', '2019', '4 ', '6 ', '���³���', '������', 'ˮƿ��', '20190501', '20190531', 130, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190511', '2019', '4 ', '7 ', '���³���', '������', 'ˮƿ��', '20190501', '20190531', 131, 11, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190512', '2019', '4 ', '8 ', '���³���', '������', 'ˮƿ��', '20190501', '20190531', 132, 12, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190513', '2019', '4 ', '9 ', '���³���', '����һ', 'ˮƿ��', '20190501', '20190531', 133, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190514', '2019', '4 ', '10', '���³�ʮ', '���ڶ�', 'ˮƿ��', '20190501', '20190531', 134, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190515', '2019', '4 ', '11', '����ʮһ', '������', 'ˮƿ��', '20190501', '20190531', 135, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190516', '2019', '4 ', '12', '����ʮ��', '������', 'ˮƿ��', '20190501', '20190531', 136, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190517', '2019', '4 ', '13', '����ʮ��', '������', 'ˮƿ��', '20190501', '20190531', 137, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190518', '2019', '4 ', '14', '����ʮ��', '������', 'ˮƿ��', '20190501', '20190531', 138, 18, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190519', '2019', '4 ', '15', '����ʮ��', '������', 'ˮƿ��', '20190501', '20190531', 139, 19, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190520', '2019', '4 ', '16', '����ʮ��', '����һ', 'ˮƿ��', '20190501', '20190531', 140, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190521', '2019', '4 ', '17', '����ʮ��', '���ڶ�', 'ˮƿ��', '20190501', '20190531', 141, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190522', '2019', '4 ', '18', '����ʮ��', '������', 'ˮƿ��', '20190501', '20190531', 142, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190523', '2019', '4 ', '19', '����ʮ��', '������', 'ˮƿ��', '20190501', '20190531', 143, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190524', '2019', '4 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20190501', '20190531', 144, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190525', '2019', '4 ', '21', '����إһ', '������', 'ˮƿ��', '20190501', '20190531', 145, 25, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190526', '2019', '4 ', '22', '����إ��', '������', 'ˮƿ��', '20190501', '20190531', 146, 26, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190527', '2019', '4 ', '23', '����إ��', '����һ', 'ˮƿ��', '20190501', '20190531', 147, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190528', '2019', '4 ', '24', '����إ��', '���ڶ�', 'ˮƿ��', '20190501', '20190531', 148, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190529', '2019', '4 ', '25', '����إ��', '������', 'ˮƿ��', '20190501', '20190531', 149, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190530', '2019', '4 ', '26', '����إ��', '������', 'ˮƿ��', '20190501', '20190531', 150, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190531', '2019', '4 ', '27', '����إ��', '������', 'ˮƿ��', '20190501', '20190531', 151, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190601', '2019', '4 ', '28', '����إ��', '������', 'ˮƿ��', '20190601', '20190630', 152, 1, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190602', '2019', '4 ', '29', '����إ��', '������', 'ˮƿ��', '20190601', '20190630', 153, 2, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190603', '2019', '5 ', '1 ', '���³�һ', '����һ', 'ˮƿ��', '20190601', '20190630', 154, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190604', '2019', '5 ', '2 ', '���³���', '���ڶ�', 'ˮƿ��', '20190601', '20190630', 155, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190605', '2019', '5 ', '3 ', '���³���', '������', 'ˮƿ��', '20190601', '20190630', 156, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190606', '2019', '5 ', '4 ', '���³���', '������', 'ˮƿ��', '20190601', '20190630', 157, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190607', '2019', '5 ', '5 ', '���³���', '������', 'ˮƿ��', '20190601', '20190630', 158, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190608', '2019', '5 ', '6 ', '���³���', '������', 'ˮƿ��', '20190601', '20190630', 159, 8, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190609', '2019', '5 ', '7 ', '���³���', '������', 'ˮƿ��', '20190601', '20190630', 160, 9, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190610', '2019', '5 ', '8 ', '���³���', '����һ', 'ˮƿ��', '20190601', '20190630', 161, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190611', '2019', '5 ', '9 ', '���³���', '���ڶ�', 'ˮƿ��', '20190601', '20190630', 162, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190612', '2019', '5 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20190601', '20190630', 163, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190613', '2019', '5 ', '11', '����ʮһ', '������', 'ˮƿ��', '20190601', '20190630', 164, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190614', '2019', '5 ', '12', '����ʮ��', '������', 'ˮƿ��', '20190601', '20190630', 165, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190615', '2019', '5 ', '13', '����ʮ��', '������', 'ˮƿ��', '20190601', '20190630', 166, 15, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190616', '2019', '5 ', '14', '����ʮ��', '������', 'ˮƿ��', '20190601', '20190630', 167, 16, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190617', '2019', '5 ', '15', '����ʮ��', '����һ', 'ˮƿ��', '20190601', '20190630', 168, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190618', '2019', '5 ', '16', '����ʮ��', '���ڶ�', 'ˮƿ��', '20190601', '20190630', 169, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190619', '2019', '5 ', '17', '����ʮ��', '������', 'ˮƿ��', '20190601', '20190630', 170, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190620', '2019', '5 ', '18', '����ʮ��', '������', 'ˮƿ��', '20190601', '20190630', 171, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190621', '2019', '5 ', '19', '����ʮ��', '������', 'ˮƿ��', '20190601', '20190630', 172, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190622', '2019', '5 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20190601', '20190630', 173, 22, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190623', '2019', '5 ', '21', '����إһ', '������', 'ˮƿ��', '20190601', '20190630', 174, 23, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190624', '2019', '5 ', '22', '����إ��', '����һ', 'ˮƿ��', '20190601', '20190630', 175, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190625', '2019', '5 ', '23', '����إ��', '���ڶ�', 'ˮƿ��', '20190601', '20190630', 176, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190626', '2019', '5 ', '24', '����إ��', '������', 'ˮƿ��', '20190601', '20190630', 177, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190627', '2019', '5 ', '25', '����إ��', '������', 'ˮƿ��', '20190601', '20190630', 178, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190628', '2019', '5 ', '26', '����إ��', '������', 'ˮƿ��', '20190601', '20190630', 179, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190629', '2019', '5 ', '27', '����إ��', '������', 'ˮƿ��', '20190601', '20190630', 180, 29, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190630', '2019', '5 ', '28', '����إ��', '������', 'ˮƿ��', '20190601', '20190630', 181, 30, '1', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190701', '2019', '5 ', '29', '����إ��', '����һ', 'ˮƿ��', '20190701', '20190731', 182, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190702', '2019', '5 ', '30', '������ʮ', '���ڶ�', 'ˮƿ��', '20190701', '20190731', 183, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190703', '2019', '6 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20190701', '20190731', 184, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190704', '2019', '6 ', '2 ', '���³���', '������', 'ˮƿ��', '20190701', '20190731', 185, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190705', '2019', '6 ', '3 ', '���³���', '������', 'ˮƿ��', '20190701', '20190731', 186, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190706', '2019', '6 ', '4 ', '���³���', '������', 'ˮƿ��', '20190701', '20190731', 187, 6, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190707', '2019', '6 ', '5 ', '���³���', '������', 'ˮƿ��', '20190701', '20190731', 188, 7, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190708', '2019', '6 ', '6 ', '���³���', '����һ', 'ˮƿ��', '20190701', '20190731', 189, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190709', '2019', '6 ', '7 ', '���³���', '���ڶ�', 'ˮƿ��', '20190701', '20190731', 190, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190710', '2019', '6 ', '8 ', '���³���', '������', 'ˮƿ��', '20190701', '20190731', 191, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190711', '2019', '6 ', '9 ', '���³���', '������', 'ˮƿ��', '20190701', '20190731', 192, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190712', '2019', '6 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20190701', '20190731', 193, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190713', '2019', '6 ', '11', '����ʮһ', '������', 'ˮƿ��', '20190701', '20190731', 194, 13, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190714', '2019', '6 ', '12', '����ʮ��', '������', 'ˮƿ��', '20190701', '20190731', 195, 14, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190715', '2019', '6 ', '13', '����ʮ��', '����һ', 'ˮƿ��', '20190701', '20190731', 196, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190716', '2019', '6 ', '14', '����ʮ��', '���ڶ�', 'ˮƿ��', '20190701', '20190731', 197, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190717', '2019', '6 ', '15', '����ʮ��', '������', 'ˮƿ��', '20190701', '20190731', 198, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190718', '2019', '6 ', '16', '����ʮ��', '������', 'ˮƿ��', '20190701', '20190731', 199, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190719', '2019', '6 ', '17', '����ʮ��', '������', 'ˮƿ��', '20190701', '20190731', 200, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190720', '2019', '6 ', '18', '����ʮ��', '������', 'ˮƿ��', '20190701', '20190731', 201, 20, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190721', '2019', '6 ', '19', '����ʮ��', '������', 'ˮƿ��', '20190701', '20190731', 202, 21, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190722', '2019', '6 ', '20', '���¶�ʮ', '����һ', 'ˮƿ��', '20190701', '20190731', 203, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190723', '2019', '6 ', '21', '����إһ', '���ڶ�', 'ˮƿ��', '20190701', '20190731', 204, 23, '0', null, 'N');
commit;
prompt 1300 records committed...
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190724', '2019', '6 ', '22', '����إ��', '������', 'ˮƿ��', '20190701', '20190731', 205, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190725', '2019', '6 ', '23', '����إ��', '������', 'ˮƿ��', '20190701', '20190731', 206, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190726', '2019', '6 ', '24', '����إ��', '������', 'ˮƿ��', '20190701', '20190731', 207, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190727', '2019', '6 ', '25', '����إ��', '������', 'ˮƿ��', '20190701', '20190731', 208, 27, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190728', '2019', '6 ', '26', '����إ��', '������', 'ˮƿ��', '20190701', '20190731', 209, 28, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190729', '2019', '6 ', '27', '����إ��', '����һ', 'ˮƿ��', '20190701', '20190731', 210, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190730', '2019', '6 ', '28', '����إ��', '���ڶ�', 'ˮƿ��', '20190701', '20190731', 211, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190731', '2019', '6 ', '29', '����إ��', '������', 'ˮƿ��', '20190701', '20190731', 212, 31, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190801', '2019', '7 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20190801', '20190831', 213, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190802', '2019', '7 ', '2 ', '���³���', '������', 'ˮƿ��', '20190801', '20190831', 214, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190803', '2019', '7 ', '3 ', '���³���', '������', 'ˮƿ��', '20190801', '20190831', 215, 3, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190804', '2019', '7 ', '4 ', '���³���', '������', 'ˮƿ��', '20190801', '20190831', 216, 4, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190805', '2019', '7 ', '5 ', '���³���', '����һ', 'ˮƿ��', '20190801', '20190831', 217, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190806', '2019', '7 ', '6 ', '���³���', '���ڶ�', 'ˮƿ��', '20190801', '20190831', 218, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190807', '2019', '7 ', '7 ', '���³���', '������', 'ˮƿ��', '20190801', '20190831', 219, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190808', '2019', '7 ', '8 ', '���³���', '������', 'ˮƿ��', '20190801', '20190831', 220, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190809', '2019', '7 ', '9 ', '���³���', '������', 'ˮƿ��', '20190801', '20190831', 221, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190810', '2019', '7 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20190801', '20190831', 222, 10, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190811', '2019', '7 ', '11', '����ʮһ', '������', 'ˮƿ��', '20190801', '20190831', 223, 11, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190812', '2019', '7 ', '12', '����ʮ��', '����һ', 'ˮƿ��', '20190801', '20190831', 224, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190813', '2019', '7 ', '13', '����ʮ��', '���ڶ�', 'ˮƿ��', '20190801', '20190831', 225, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190814', '2019', '7 ', '14', '����ʮ��', '������', 'ˮƿ��', '20190801', '20190831', 226, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190815', '2019', '7 ', '15', '����ʮ��', '������', 'ˮƿ��', '20190801', '20190831', 227, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190816', '2019', '7 ', '16', '����ʮ��', '������', 'ˮƿ��', '20190801', '20190831', 228, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190817', '2019', '7 ', '17', '����ʮ��', '������', 'ˮƿ��', '20190801', '20190831', 229, 17, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190818', '2019', '7 ', '18', '����ʮ��', '������', 'ˮƿ��', '20190801', '20190831', 230, 18, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190819', '2019', '7 ', '19', '����ʮ��', '����һ', 'ˮƿ��', '20190801', '20190831', 231, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190820', '2019', '7 ', '20', '���¶�ʮ', '���ڶ�', 'ˮƿ��', '20190801', '20190831', 232, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190821', '2019', '7 ', '21', '����إһ', '������', 'ˮƿ��', '20190801', '20190831', 233, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190822', '2019', '7 ', '22', '����إ��', '������', 'ˮƿ��', '20190801', '20190831', 234, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190823', '2019', '7 ', '23', '����إ��', '������', 'ˮƿ��', '20190801', '20190831', 235, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190824', '2019', '7 ', '24', '����إ��', '������', 'ˮƿ��', '20190801', '20190831', 236, 24, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190825', '2019', '7 ', '25', '����إ��', '������', 'ˮƿ��', '20190801', '20190831', 237, 25, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190826', '2019', '7 ', '26', '����إ��', '����һ', 'ˮƿ��', '20190801', '20190831', 238, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190827', '2019', '7 ', '27', '����إ��', '���ڶ�', 'ˮƿ��', '20190801', '20190831', 239, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190828', '2019', '7 ', '28', '����إ��', '������', 'ˮƿ��', '20190801', '20190831', 240, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190829', '2019', '7 ', '29', '����إ��', '������', 'ˮƿ��', '20190801', '20190831', 241, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190830', '2019', '8 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20190801', '20190831', 242, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190831', '2019', '8 ', '2 ', '���³���', '������', 'ˮƿ��', '20190801', '20190831', 243, 31, '1', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190901', '2019', '8 ', '3 ', '���³���', '������', 'ˮƿ��', '20190901', '20190930', 244, 1, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190902', '2019', '8 ', '4 ', '���³���', '����һ', 'ˮƿ��', '20190901', '20190930', 245, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190903', '2019', '8 ', '5 ', '���³���', '���ڶ�', 'ˮƿ��', '20190901', '20190930', 246, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190904', '2019', '8 ', '6 ', '���³���', '������', 'ˮƿ��', '20190901', '20190930', 247, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190905', '2019', '8 ', '7 ', '���³���', '������', 'ˮƿ��', '20190901', '20190930', 248, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190906', '2019', '8 ', '8 ', '���³���', '������', 'ˮƿ��', '20190901', '20190930', 249, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190907', '2019', '8 ', '9 ', '���³���', '������', 'ˮƿ��', '20190901', '20190930', 250, 7, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190908', '2019', '8 ', '10', '���³�ʮ', '������', 'ˮƿ��', '20190901', '20190930', 251, 8, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190909', '2019', '8 ', '11', '����ʮһ', '����һ', 'ˮƿ��', '20190901', '20190930', 252, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190910', '2019', '8 ', '12', '����ʮ��', '���ڶ�', 'ˮƿ��', '20190901', '20190930', 253, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190911', '2019', '8 ', '13', '����ʮ��', '������', 'ˮƿ��', '20190901', '20190930', 254, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190912', '2019', '8 ', '14', '����ʮ��', '������', 'ˮƿ��', '20190901', '20190930', 255, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190913', '2019', '8 ', '15', '����ʮ��', '������', 'ˮƿ��', '20190901', '20190930', 256, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190914', '2019', '8 ', '16', '����ʮ��', '������', 'ˮƿ��', '20190901', '20190930', 257, 14, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190915', '2019', '8 ', '17', '����ʮ��', '������', 'ˮƿ��', '20190901', '20190930', 258, 15, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190916', '2019', '8 ', '18', '����ʮ��', '����һ', 'ˮƿ��', '20190901', '20190930', 259, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190917', '2019', '8 ', '19', '����ʮ��', '���ڶ�', 'ˮƿ��', '20190901', '20190930', 260, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190918', '2019', '8 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20190901', '20190930', 261, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190919', '2019', '8 ', '21', '����إһ', '������', 'ˮƿ��', '20190901', '20190930', 262, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190920', '2019', '8 ', '22', '����إ��', '������', 'ˮƿ��', '20190901', '20190930', 263, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190921', '2019', '8 ', '23', '����إ��', '������', 'ˮƿ��', '20190901', '20190930', 264, 21, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190922', '2019', '8 ', '24', '����إ��', '������', 'ˮƿ��', '20190901', '20190930', 265, 22, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190923', '2019', '8 ', '25', '����إ��', '����һ', 'ˮƿ��', '20190901', '20190930', 266, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190924', '2019', '8 ', '26', '����إ��', '���ڶ�', 'ˮƿ��', '20190901', '20190930', 267, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190925', '2019', '8 ', '27', '����إ��', '������', 'ˮƿ��', '20190901', '20190930', 268, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190926', '2019', '8 ', '28', '����إ��', '������', 'ˮƿ��', '20190901', '20190930', 269, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190927', '2019', '8 ', '29', '����إ��', '������', 'ˮƿ��', '20190901', '20190930', 270, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190928', '2019', '8 ', '30', '������ʮ', '������', 'ˮƿ��', '20190901', '20190930', 271, 28, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190929', '2019', '9 ', '1 ', '���³�һ', '������', 'ˮƿ��', '20190901', '20190930', 272, 29, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20190930', '2019', '9 ', '2 ', '���³���', '����һ', 'ˮƿ��', '20190901', '20190930', 273, 30, '0', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191001', '2019', '9 ', '3 ', '���³���', '���ڶ�', 'ˮƿ��', '20191001', '20191031', 274, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191002', '2019', '9 ', '4 ', '���³���', '������', 'ˮƿ��', '20191001', '20191031', 275, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191003', '2019', '9 ', '5 ', '���³���', '������', 'ˮƿ��', '20191001', '20191031', 276, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191004', '2019', '9 ', '6 ', '���³���', '������', 'ˮƿ��', '20191001', '20191031', 277, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191005', '2019', '9 ', '7 ', '���³���', '������', 'ˮƿ��', '20191001', '20191031', 278, 5, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191006', '2019', '9 ', '8 ', '���³���', '������', 'ˮƿ��', '20191001', '20191031', 279, 6, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191007', '2019', '9 ', '9 ', '���³���', '����һ', 'ˮƿ��', '20191001', '20191031', 280, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191008', '2019', '9 ', '10', '���³�ʮ', '���ڶ�', 'ˮƿ��', '20191001', '20191031', 281, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191009', '2019', '9 ', '11', '����ʮһ', '������', 'ˮƿ��', '20191001', '20191031', 282, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191010', '2019', '9 ', '12', '����ʮ��', '������', 'ˮƿ��', '20191001', '20191031', 283, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191011', '2019', '9 ', '13', '����ʮ��', '������', 'ˮƿ��', '20191001', '20191031', 284, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191012', '2019', '9 ', '14', '����ʮ��', '������', 'ˮƿ��', '20191001', '20191031', 285, 12, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191013', '2019', '9 ', '15', '����ʮ��', '������', 'ˮƿ��', '20191001', '20191031', 286, 13, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191014', '2019', '9 ', '16', '����ʮ��', '����һ', 'ˮƿ��', '20191001', '20191031', 287, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191015', '2019', '9 ', '17', '����ʮ��', '���ڶ�', 'ˮƿ��', '20191001', '20191031', 288, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191016', '2019', '9 ', '18', '����ʮ��', '������', 'ˮƿ��', '20191001', '20191031', 289, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191017', '2019', '9 ', '19', '����ʮ��', '������', 'ˮƿ��', '20191001', '20191031', 290, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191018', '2019', '9 ', '20', '���¶�ʮ', '������', 'ˮƿ��', '20191001', '20191031', 291, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191019', '2019', '9 ', '21', '����إһ', '������', 'ˮƿ��', '20191001', '20191031', 292, 19, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191020', '2019', '9 ', '22', '����إ��', '������', 'ˮƿ��', '20191001', '20191031', 293, 20, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191021', '2019', '9 ', '23', '����إ��', '����һ', 'ˮƿ��', '20191001', '20191031', 294, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191022', '2019', '9 ', '24', '����إ��', '���ڶ�', 'ˮƿ��', '20191001', '20191031', 295, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191023', '2019', '9 ', '25', '����إ��', '������', 'ˮƿ��', '20191001', '20191031', 296, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191024', '2019', '9 ', '26', '����إ��', '������', 'ˮƿ��', '20191001', '20191031', 297, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191025', '2019', '9 ', '27', '����إ��', '������', 'ˮƿ��', '20191001', '20191031', 298, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191026', '2019', '9 ', '28', '����إ��', '������', 'ˮƿ��', '20191001', '20191031', 299, 26, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191027', '2019', '9 ', '29', '����إ��', '������', 'ˮƿ��', '20191001', '20191031', 300, 27, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191028', '2019', '10', '1 ', 'ʮ�³�һ', '����һ', 'ˮƿ��', '20191001', '20191031', 301, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191029', '2019', '10', '2 ', 'ʮ�³���', '���ڶ�', 'ˮƿ��', '20191001', '20191031', 302, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191030', '2019', '10', '3 ', 'ʮ�³���', '������', 'ˮƿ��', '20191001', '20191031', 303, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191031', '2019', '10', '4 ', 'ʮ�³���', '������', 'ˮƿ��', '20191001', '20191031', 304, 31, '0', null, 'Y');
commit;
prompt 1400 records committed...
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191101', '2019', '10', '5 ', 'ʮ�³���', '������', 'ˮƿ��', '20191101', '20191130', 305, 1, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191102', '2019', '10', '6 ', 'ʮ�³���', '������', 'ˮƿ��', '20191101', '20191130', 306, 2, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191103', '2019', '10', '7 ', 'ʮ�³���', '������', 'ˮƿ��', '20191101', '20191130', 307, 3, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191104', '2019', '10', '8 ', 'ʮ�³���', '����һ', 'ˮƿ��', '20191101', '20191130', 308, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191105', '2019', '10', '9 ', 'ʮ�³���', '���ڶ�', 'ˮƿ��', '20191101', '20191130', 309, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191106', '2019', '10', '10', 'ʮ�³�ʮ', '������', 'ˮƿ��', '20191101', '20191130', 310, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191107', '2019', '10', '11', 'ʮ��ʮһ', '������', 'ˮƿ��', '20191101', '20191130', 311, 7, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191108', '2019', '10', '12', 'ʮ��ʮ��', '������', 'ˮƿ��', '20191101', '20191130', 312, 8, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191109', '2019', '10', '13', 'ʮ��ʮ��', '������', 'ˮƿ��', '20191101', '20191130', 313, 9, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191110', '2019', '10', '14', 'ʮ��ʮ��', '������', 'ˮƿ��', '20191101', '20191130', 314, 10, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191111', '2019', '10', '15', 'ʮ��ʮ��', '����һ', 'ˮƿ��', '20191101', '20191130', 315, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191112', '2019', '10', '16', 'ʮ��ʮ��', '���ڶ�', 'ˮƿ��', '20191101', '20191130', 316, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191113', '2019', '10', '17', 'ʮ��ʮ��', '������', 'ˮƿ��', '20191101', '20191130', 317, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191114', '2019', '10', '18', 'ʮ��ʮ��', '������', 'ˮƿ��', '20191101', '20191130', 318, 14, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191115', '2019', '10', '19', 'ʮ��ʮ��', '������', 'ˮƿ��', '20191101', '20191130', 319, 15, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191116', '2019', '10', '20', 'ʮ�¶�ʮ', '������', 'ˮƿ��', '20191101', '20191130', 320, 16, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191117', '2019', '10', '21', 'ʮ��إһ', '������', 'ˮƿ��', '20191101', '20191130', 321, 17, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191118', '2019', '10', '22', 'ʮ��إ��', '����һ', 'ˮƿ��', '20191101', '20191130', 322, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191119', '2019', '10', '23', 'ʮ��إ��', '���ڶ�', 'ˮƿ��', '20191101', '20191130', 323, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191120', '2019', '10', '24', 'ʮ��إ��', '������', 'ˮƿ��', '20191101', '20191130', 324, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191121', '2019', '10', '25', 'ʮ��إ��', '������', 'ˮƿ��', '20191101', '20191130', 325, 21, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191122', '2019', '10', '26', 'ʮ��إ��', '������', 'ˮƿ��', '20191101', '20191130', 326, 22, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191123', '2019', '10', '27', 'ʮ��إ��', '������', 'ˮƿ��', '20191101', '20191130', 327, 23, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191124', '2019', '10', '28', 'ʮ��إ��', '������', 'ˮƿ��', '20191101', '20191130', 328, 24, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191125', '2019', '10', '29', 'ʮ��إ��', '����һ', 'ˮƿ��', '20191101', '20191130', 329, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191126', '2019', '11', '1 ', 'ʮһ�³�һ', '���ڶ�', 'ˮƿ��', '20191101', '20191130', 330, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191127', '2019', '11', '2 ', 'ʮһ�³���', '������', 'ˮƿ��', '20191101', '20191130', 331, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191128', '2019', '11', '3 ', 'ʮһ�³���', '������', 'ˮƿ��', '20191101', '20191130', 332, 28, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191129', '2019', '11', '4 ', 'ʮһ�³���', '������', 'ˮƿ��', '20191101', '20191130', 333, 29, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191130', '2019', '11', '5 ', 'ʮһ�³���', '������', 'ˮƿ��', '20191101', '20191130', 334, 30, '1', null, 'Y');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191201', '2019', '11', '6 ', 'ʮһ�³���', '������', 'ˮƿ��', '20191201', '20191231', 335, 1, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191202', '2019', '11', '7 ', 'ʮһ�³���', '����һ', 'ˮƿ��', '20191201', '20191231', 336, 2, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191203', '2019', '11', '8 ', 'ʮһ�³���', '���ڶ�', 'ˮƿ��', '20191201', '20191231', 337, 3, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191204', '2019', '11', '9 ', 'ʮһ�³���', '������', 'ˮƿ��', '20191201', '20191231', 338, 4, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191205', '2019', '11', '10', 'ʮһ�³�ʮ', '������', 'ˮƿ��', '20191201', '20191231', 339, 5, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191206', '2019', '11', '11', 'ʮһ��ʮһ', '������', 'ˮƿ��', '20191201', '20191231', 340, 6, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191207', '2019', '11', '12', 'ʮһ��ʮ��', '������', 'ˮƿ��', '20191201', '20191231', 341, 7, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191208', '2019', '11', '13', 'ʮһ��ʮ��', '������', 'ˮƿ��', '20191201', '20191231', 342, 8, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191209', '2019', '11', '14', 'ʮһ��ʮ��', '����һ', 'ˮƿ��', '20191201', '20191231', 343, 9, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191210', '2019', '11', '15', 'ʮһ��ʮ��', '���ڶ�', 'ˮƿ��', '20191201', '20191231', 344, 10, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191211', '2019', '11', '16', 'ʮһ��ʮ��', '������', 'ˮƿ��', '20191201', '20191231', 345, 11, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191212', '2019', '11', '17', 'ʮһ��ʮ��', '������', 'ˮƿ��', '20191201', '20191231', 346, 12, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191213', '2019', '11', '18', 'ʮһ��ʮ��', '������', 'ˮƿ��', '20191201', '20191231', 347, 13, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191214', '2019', '11', '19', 'ʮһ��ʮ��', '������', 'ˮƿ��', '20191201', '20191231', 348, 14, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191215', '2019', '11', '20', 'ʮһ�¶�ʮ', '������', 'ˮƿ��', '20191201', '20191231', 349, 15, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191216', '2019', '11', '21', 'ʮһ��إһ', '����һ', 'ˮƿ��', '20191201', '20191231', 350, 16, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191217', '2019', '11', '22', 'ʮһ��إ��', '���ڶ�', 'ˮƿ��', '20191201', '20191231', 351, 17, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191218', '2019', '11', '23', 'ʮһ��إ��', '������', 'ˮƿ��', '20191201', '20191231', 352, 18, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191219', '2019', '11', '24', 'ʮһ��إ��', '������', 'ˮƿ��', '20191201', '20191231', 353, 19, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191220', '2019', '11', '25', 'ʮһ��إ��', '������', 'ˮƿ��', '20191201', '20191231', 354, 20, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191221', '2019', '11', '26', 'ʮһ��إ��', '������', 'ˮƿ��', '20191201', '20191231', 355, 21, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191222', '2019', '11', '27', 'ʮһ��إ��', '������', 'Ħ����', '20191201', '20191231', 356, 22, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191223', '2019', '11', '28', 'ʮһ��إ��', '����һ', 'Ħ����', '20191201', '20191231', 357, 23, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191224', '2019', '11', '29', 'ʮһ��إ��', '���ڶ�', 'Ħ����', '20191201', '20191231', 358, 24, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191225', '2019', '11', '30', 'ʮһ����ʮ', '������', 'Ħ����', '20191201', '20191231', 359, 25, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191226', '2019', '12', '1 ', '���³�һ', '������', 'Ħ����', '20191201', '20191231', 360, 26, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191227', '2019', '12', '2 ', '���³���', '������', 'Ħ����', '20191201', '20191231', 361, 27, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191228', '2019', '12', '3 ', '���³���', '������', 'Ħ����', '20191201', '20191231', 362, 28, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191229', '2019', '12', '4 ', '���³���', '������', 'Ħ����', '20191201', '20191231', 363, 29, '1', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191230', '2019', '12', '5 ', '���³���', '����һ', 'Ħ����', '20191201', '20191231', 364, 30, '0', null, 'N');
insert into BF_DAY_INFO (solar_date, lunar_year, lunar_month, lunar_day, lunar_desc, week, constellation, month_begin, month_end, year_days, month_days, is_holiday_day, holiday_desc, is_month_end)
values ('20191231', '2019', '12', '6 ', '���³���', '���ڶ�', 'Ħ����', '20191201', '20191231', 365, 31, '0', null, 'Y');
commit;
prompt 1461 records loaded
prompt Loading BF_DOC...
insert into BF_DOC (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('20160707104419978326842788091349', '9bc187c0148d69b244f101762f116e69.jpg', 'jpg', 'MD5', '1f5972bf43183e6aad2e5b5e014abeee', 119720, '1', '1', 'webui/data/20160707/20160707104419978326842788091349.jpg', '20160707', '104419', 'dev', null, null, null);
insert into BF_DOC (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('20160707105024334119409912270442', 'JavaScriptȨ��ָ��(��6��Ӣ��).pdf', 'pdf', 'MD5', '0124b331be9cd4ddc09fc9575a172da1', 14129653, '2', '1', 'webui/data/20160707/20160707105024334119409912270442.pdf', '20160707', '105024', 'oulinhai', null, null, null);
insert into BF_DOC (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('20160707105309989339792159269881', '5ec18c776836344b073d77240d1eb5fe.jpg', 'jpg', 'MD5', '56a610323590751371a02af767bfc3c6', 385538, '1', '1', 'webui/data/20160707/20160707105309989339792159269881.jpg', '20160707', '105310', 'dev', null, null, null);
insert into BF_DOC (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('20160707105708301693977303229732', 'װ��ָ��.txt', 'txt', 'MD5', 'b1651245121f1e3ef0a883172ea0960e', 21, '1', '1', 'webui/data/20160707/20160707105708301693977303229732.txt', '20160707', '105708', 'dev', null, null, null);
insert into BF_DOC (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('20160714212002173047782962029238', '��ɫ����.png', 'png', 'MD5', 'e8ba8bcd17436823d15112425813aa0c', 123437, '1', '1', 'webui/data/20160714/20160714212002173047782962029238.png', '20160714', '212002', 'luowei', null, null, null);
insert into BF_DOC (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('20160921150912068622568945234465', 'beneform4j-reference.pdf', 'pdf', 'MD5', '044c3661e6c9b34ecd005a8237f1c0f4', 3566039, '1', '1', 'webui/data/20160921/20160921150912068622568945234465.pdf', '20160921', '150912', 'luowei', null, null, null);
commit;
prompt 6 records loaded
prompt Loading BF_DOC_MID...
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160705084534859901323988550195', '2.jpg', 'jpg', 'MD5', '758539428f4f06684061a588f784e18b', 3730, null, '1', 'c:/attached/temp/20160705/20160705084534859901323988550195.jpg', '20160705', '084534', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160705084535076433832887390152', '4.jpg', 'jpg', 'MD5', '59e5413d7a1edf60899669a2295fc35a', 3914, null, '1', 'c:/attached/temp/20160705/20160705084535076433832887390152.jpg', '20160705', '084535', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160705143527860139359214222655', 'FreeSSHDService.exe', 'exe', 'MD5', 'a25c33fb549d90018dbeec40bb07d543', 1513072, null, '1', 'c:/attached/temp/20160705/20160705143527860139359214222655.exe', '20160705', '143528', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20161011160406084614222566929699', '�½��ı��ĵ�.txt', 'txt', 'MD5', '4667ef3cdb8a532580b323f438e2bd18', 71, null, '1', 'c:/attached/temp/20161011/20161011160406084614222566929699.txt', '20161011', '160406', 'zhangjj');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20161018164745614854633001396006', 'framePlus.js', 'js', 'MD5', '321a3679da56681d2a40337d2ddda1d9', 19776, null, '1', 'c:/attached/temp/20161018/20161018164745614854633001396006.js', '20161018', '164745', 'zhangjj');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20161018164745245955983243456473', 'beneform4j.js', 'js', 'MD5', '69b94ce681cc177a8a2876986e56dcbe', 64126, null, '1', 'c:/attached/temp/20161018/20161018164745245955983243456473.js', '20161018', '164745', 'zhangjj');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20161018164745376113386936059490', 'FavoritesController.java', 'java', 'MD5', 'b98eac4d0435f3ddc324b5fe2985a3ad', 2518, null, '1', 'c:/attached/temp/20161018/20161018164745376113386936059490.java', '20161018', '164745', 'zhangjj');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20161018164745964146450217161866', 'IFavoritesService.java', 'java', 'MD5', '61ccd34be84ac27a707f423282fc05b5', 1256, null, '1', 'c:/attached/temp/20161018/20161018164745964146450217161866.java', '20161018', '164745', 'zhangjj');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20161018164746207389548731166772', 'mainframe.js', 'js', 'MD5', 'ffb1a76b31ac8b5f4c5009052781297a', 18221, null, '1', 'c:/attached/temp/20161018/20161018164746207389548731166772.js', '20161018', '164746', 'zhangjj');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20161018165022008555851189941485', '07.jpg', 'jpg', 'MD5', '9ed418ac5d5bd3261417f6d3e2171648', 108563, null, '1', 'c:/attached/temp/20161018/20161018165022008555851189941485.jpg', '20161018', '165022', 'zhangjj');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20161018165022563226394497414243', '08.jpg', 'jpg', 'MD5', 'a70c76b7254faa71625d395a1607d0cb', 2230, null, '1', 'c:/attached/temp/20161018/20161018165022563226394497414243.jpg', '20161018', '165022', 'zhangjj');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20161018165023041633658143464964', '09.jpg', 'jpg', 'MD5', 'fa555cea64fcf63815d83fb5c93a9194', 13527, null, '1', 'c:/attached/temp/20161018/20161018165023041633658143464964.jpg', '20161018', '165023', 'zhangjj');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20161018165023302854673655895849', '10.jpg', 'jpg', 'MD5', '9a36c090785768b5d1f66eb5edb87d3a', 10202, null, '1', 'c:/attached/temp/20161018/20161018165023302854673655895849.jpg', '20161018', '165023', 'zhangjj');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20161018165023584209302846125932', '11.jpg', 'jpg', 'MD5', 'f3d9d3e3ba99f154262b62ec8a75d0c0', 1185, null, '1', 'c:/attached/temp/20161018/20161018165023584209302846125932.jpg', '20161018', '165023', 'zhangjj');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20161018165024626073595462474781', '12.jpg', 'jpg', 'MD5', 'f505302d59443ea9ddcf29e55253535d', 1024, null, '1', 'c:/attached/temp/20161018/20161018165024626073595462474781.jpg', '20161018', '165024', 'zhangjj');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20161018165024889235342119702542', '111.jpg', 'jpg', 'MD5', '4d61175bf7d5acab5684a2686773d9de', 74209, null, '1', 'c:/attached/temp/20161018/20161018165024889235342119702542.jpg', '20161018', '165024', 'zhangjj');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160628174335903852339345622714', 'DTGadget64.dll', 'dll', 'MD5', 'ffdc6ec4a8c4724f759caf0fef7129a9', 368448, null, '1', 'c:/attached/temp/20160628/20160628174335903852339345622714.dll', '20160628', '174335', null);
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160628174451870444194418473404', 'DTGadget64.dll', 'dll', 'MD5', 'ffdc6ec4a8c4724f759caf0fef7129a9', 368448, null, '1', 'c:/attached/temp/20160628/20160628174451870444194418473404.dll', '20160628', '174451', null);
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160628174418878152880456545331', 'DTGadget64.dll', 'dll', 'MD5', 'ffdc6ec4a8c4724f759caf0fef7129a9', 368448, null, '1', 'c:/attached/temp/20160628/20160628174418878152880456545331.dll', '20160628', '174418', null);
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160629084735841105554183805788', 'DTGadget64.dll', 'dll', 'MD5', 'ffdc6ec4a8c4724f759caf0fef7129a9', 368448, null, '1', 'c:/attached/temp/20160629/20160629084735841105554183805788.dll', '20160629', '084735', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160629085139047263682016759519', 'DTCommonRes.dll', 'dll', 'MD5', 'aae25f773b496cab8e469e886a006547', 4860736, null, '1', 'c:/attached/temp/20160629/20160629085139047263682016759519.dll', '20160629', '085139', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160629085527342747705585801682', 'DTLite.exe', 'exe', 'MD5', 'dc34596bfcf0bd472aa1d48449d8a7df', 3672384, null, '1', 'c:/attached/temp/20160629/20160629085527342747705585801682.exe', '20160629', '085527', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160629085818854186442038657825', 'DTLite.exe', 'exe', 'MD5', 'dc34596bfcf0bd472aa1d48449d8a7df', 3672384, null, '1', 'c:/attached/temp/20160629/20160629085818854186442038657825.exe', '20160629', '085818', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160629090225513819935541974791', 'DTGadget64.dll', 'dll', 'MD5', 'ffdc6ec4a8c4724f759caf0fef7129a9', 368448, null, '1', 'c:/attached/temp/20160629/20160629090225513819935541974791.dll', '20160629', '090225', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160629114801183293284101211255', 'Chrysanthemum.jpg', 'jpg', 'MD5', '225a0b60ca7029336bf67abac3289101', 315560, null, '1', 'c:/attached/temp/20160629/20160629114801183293284101211255.jpg', '20160629', '114801', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160705094633806815212942102915', '38a7e342fc9b9250187a1a10b46d666e.jpg', 'jpg', 'MD5', '66117e20d8da75622b8ae9a5c325a03e', 146863, null, '1', 'c:/attached/temp/20160705/20160705094633806815212942102915.jpg', '20160705', '094633', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160705084535225860537064653670', '5.jpg', 'jpg', 'MD5', 'eeb8f2ea05f9c4e4144143012d00cfab', 3634, null, '1', 'c:/attached/temp/20160705/20160705084535225860537064653670.jpg', '20160705', '084535', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160705094358647137556271917202', 'wrapper.jar', 'jar', 'MD5', 'f6cab403bb44e7d156b195813247aeab', 83820, null, '1', 'c:/attached/temp/20160705/20160705094358647137556271917202.jar', '20160705', '094358', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160705094633647432121788025125', '5ec18c776836344b073d77240d1eb5fe.jpg', 'jpg', 'MD5', '56a610323590751371a02af767bfc3c6', 385538, null, '1', 'c:/attached/temp/20160705/20160705094633647432121788025125.jpg', '20160705', '094633', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160705094633704219145345517043', '9bc187c0148d69b244f101762f116e69.jpg', 'jpg', 'MD5', '1f5972bf43183e6aad2e5b5e014abeee', 119720, null, '1', 'c:/attached/temp/20160705/20160705094633704219145345517043.jpg', '20160705', '094633', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160715155132112066364940506310', 'SZIT2016-004664_for_pg.txt', 'txt', 'MD5', 'a950981a38954bbac459cc8b44d2aad2', 1155, null, '1', 'c:/attached/temp/20160715/20160715155132112066364940506310.txt', '20160715', '155132', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160811100904055314507463268246', 'Beneform4j����ƽ̨����.pptx', 'pptx', 'MD5', '03dbb24b3e6ed5bcae02cb2e48a50eea', 1145168, null, '1', 'c:/attached/temp/20160811/20160811100904055314507463268246.pptx', '20160811', '100904', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160705142644917672581715442755', '6.jpg', 'jpg', 'MD5', 'edb91e996d4fa4d37c109a8e0be38aeb', 3793, null, '1', 'c:/attached/temp/20160705/20160705142644917672581715442755.jpg', '20160705', '142644', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160715155137227380848162368827', '2016070503084166.xlsx', 'xlsx', 'MD5', '8644a3db2fbef359504c15186dee5680', 13775, null, '1', 'c:/attached/temp/20160715/20160715155137227380848162368827.xlsx', '20160715', '155137', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160811103100957143872310395928', 'Beneform4j����ƽ̨����.pptx', 'pptx', 'MD5', '03dbb24b3e6ed5bcae02cb2e48a50eea', 1145168, null, '1', 'c:/attached/temp/20160811/20160811103100957143872310395928.pptx', '20160811', '103101', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160811095119751653637566490546', 'params_table.sql', 'sql', 'MD5', '34d2fc6e98897830b2fa25f718b0d30b', 4365660, null, '1', 'c:/attached/temp/20160811/20160811095119751653637566490546.sql', '20160811', '095119', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160705084535222900070612274236', '3.jpg', 'jpg', 'MD5', '726acc9a4b8d7cfdc8520c2c89413a01', 3265, null, '1', 'c:/attached/temp/20160705/20160705084535222900070612274236.jpg', '20160705', '084535', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160811095556764637364001504152', 'CursorTypeHandler.java', 'java', 'MD5', 'c910007cd34d2a9219a3576e07a542dc', 2293, null, '1', 'c:/attached/temp/20160811/20160811095556764637364001504152.java', '20160811', '095556', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160811095458831058573493791915', 'markdown.chm', 'chm', 'MD5', '40e619fc0fb3d11704348c03b3e5f20f', 1953853, null, '1', 'c:/attached/temp/20160811/20160811095458831058573493791915.chm', '20160811', '095458', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160811095722752328332196958835', 'mybatis-config.xml', 'xml', 'MD5', 'bee6421fc746c803301e8cdb87aad81b', 1308, null, '1', 'c:/attached/temp/20160811/20160811095722752328332196958835.xml', '20160811', '095722', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160930094904471595414215039278', 'beneform4j.js', 'js', 'MD5', '69b94ce681cc177a8a2876986e56dcbe', 64126, null, '1', 'c:/attached/temp/20160930/20160930094904471595414215039278.js', '20160930', '094904', 'zhangjj');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160811101319810845866160327625', 'hibernate_validator_reference.pdf', 'pdf', 'MD5', 'f8d85803423c66f6dd31b995e20c0108', 1782171, null, '1', 'c:/attached/temp/20160811/20160811101319810845866160327625.pdf', '20160811', '101320', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160811101524896299723435314799', 'Beneform4j����ƽ̨����.pptx', 'pptx', 'MD5', '03dbb24b3e6ed5bcae02cb2e48a50eea', 1145168, null, '1', 'c:/attached/temp/20160811/20160811101524896299723435314799.pptx', '20160811', '101524', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160930094904500150293809439497', 'framePlus.js', 'js', 'MD5', '321a3679da56681d2a40337d2ddda1d9', 19776, null, '1', 'c:/attached/temp/20160930/20160930094904500150293809439497.js', '20160930', '094904', 'zhangjj');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160930094904791530202048159510', 'mainframe.js', 'js', 'MD5', 'ffb1a76b31ac8b5f4c5009052781297a', 18221, null, '1', 'c:/attached/temp/20160930/20160930094904791530202048159510.js', '20160930', '094904', 'zhangjj');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20161008142002154975242141134020', 'mainframe.js', 'js', 'MD5', 'ffb1a76b31ac8b5f4c5009052781297a', 18221, null, '1', 'c:/attached/temp/20161008/20161008142002154975242141134020.js', '20161008', '142002', 'zhangjj');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160629144825140653823413839643', 'common.log', 'log', 'MD5', '6dfb46d40617e4ea55ef460854e25b36', 35452742, null, '1', 'c:/attached/temp/20160629/20160629144825140653823413839643.log', '20160629', '144825', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160629142607442312385263156166', 'common-daily-rolling.log.20160530', '20160530', 'MD5', 'a8d4ecf19baf0b33d4a842601e0b4c0c', 753070, null, '1', 'c:/attached/temp/20160629/20160629142607442312385263156166.20160530', '20160629', '142607', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160629144324098560938208800521', 'common.log', 'log', 'MD5', '77e6c7afd55316a410034c49c2b92818', 35390468, null, '1', 'c:/attached/temp/20160629/20160629144324098560938208800521.log', '20160629', '144324', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160707090022311398129489278797', 'eclipse.exe', 'exe', 'MD5', '94554ed99c42fca7c802fdac7e7380c0', 326640, null, '1', 'c:/attached/temp/20160707/20160707090022311398129489278797.exe', '20160707', '090022', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160706095612135486759078976234', 'IMG_0063.jpg', 'jpg', 'MD5', '9f8e4c66ba1021bb8055ba0696043470', 176113, null, '1', 'c:/attached/temp/20160706/20160706095612135486759078976234.jpg', '20160706', '095612', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160811102032062941479482474392', 'Beneform4j����ƽ̨����.pptx', 'pptx', 'MD5', '03dbb24b3e6ed5bcae02cb2e48a50eea', 1145168, null, '1', 'c:/attached/temp/20160811/20160811102032062941479482474392.pptx', '20160811', '102032', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160811102232786646329214044314', 'Beneform4j����ƽ̨����.pptx', 'pptx', 'MD5', '03dbb24b3e6ed5bcae02cb2e48a50eea', 1145168, null, '1', 'c:/attached/temp/20160811/20160811102232786646329214044314.pptx', '20160811', '102232', 'dev');
insert into BF_DOC_MID (doc_id, doc_name, suffix, check_sum_type, check_sum, doc_size, doc_type, doc_state, store_path, inst_date, inst_time, inst_oper)
values ('20160930094904470248661833106825', 'FavoritesController.java', 'java', 'MD5', 'b98eac4d0435f3ddc324b5fe2985a3ad', 2518, null, '1', 'c:/attached/temp/20160930/20160930094904470248661833106825.java', '20160930', '094904', 'zhangjj');
commit;
prompt 54 records loaded
prompt Loading BF_JOB_CONTROLLER...
prompt Table is empty
prompt Loading BF_JOB_EXECUTE_LOG...
prompt Table is empty
prompt Loading BF_KEY_CFG...
insert into BF_KEY_CFG (module, public_empoent, private_empoent)
values ('10148845696774144230478006564809908236824102429967662086049549315010636543341360802673863553787030555626081327896706055419066864257496523090270219372126191', '65537', '6672776615865814347487637561646992476383578340590911382697942841201280630065009200187847829558570595821701241549749485843098540608677393249586910763926353');
commit;
prompt 1 records loaded
prompt Loading BF_LOG_LOGIN...
prompt Table is empty
prompt Loading BF_LOG_VISIT...
prompt Table is empty
prompt Loading BF_MEMO_DEF...
insert into BF_MEMO_DEF (id, user_id, memo_title, memo_content, memo_date, is_remind, memo_time)
values ('1', 'dev', '���Ա���1', '���Ա���1111111', '20160427', 0, '19:30');
insert into BF_MEMO_DEF (id, user_id, memo_title, memo_content, memo_date, is_remind, memo_time)
values ('2', 'dev', '���Ա���3���ı����Բ��Բ��Բ���', '���Ա���22222', '20160428', 0, '19:45');
insert into BF_MEMO_DEF (id, user_id, memo_title, memo_content, memo_date, is_remind, memo_time)
values ('3', 'dev', '���Ա���3', '���Ա���33333', '20160428', 0, '19:30');
insert into BF_MEMO_DEF (id, user_id, memo_title, memo_content, memo_date, is_remind, memo_time)
values ('4', 'dev', '123', '123', '20160501', 0, '19:45');
commit;
prompt 4 records loaded
prompt Loading BF_MEMO_LOG...
prompt Table is empty
prompt Loading BF_MENU...
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000168, 900000166, '���ز˵���', 'system/sysmanager/menulocale/list', '1', null, -1, '2', '1', null, null, 900000168, null, '20161125', '120724', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000169, 900000167, '�������ά����ѯ', 'system/sysmanager/taskrule/list', '1', 'bf4j-icon-node-15', -1, '2', '1', '1', null, 19, null, '20160820', '093112', 'assist              ', '20160820', '093818', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000170, 900000167, '�������ά��', null, '1', null, -1, '2', '1', '1', null, 20, null, '20160820', '093127', 'assist              ', '20160820', '094236', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000171, 900000170, '�����������', 'system/sysmanager/taskrule/insert', '1', null, 900000170, '2', '1', '0', null, 21, null, '20160820', '093250', 'assist              ', '20160820', '094304', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000172, 900000170, 'ɾ���������', 'system/sysmanager/taskrule/delete', '1', null, 900000170, '2', '1', '0', null, 22, null, '20160820', '093312', 'assist              ', '20160820', '094310', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000173, 900000170, '�޸��������', 'system/sysmanager/taskrule/update', '1', null, 900000170, '2', '1', '0', null, 0, null, '20160820', '093324', 'assist              ', '20160820', '094307', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000174, 900000170, '����������', 'system/sysmanager/taskrule/gotoAdd', '1', null, 900000170, '2', '1', '0', null, 11, null, '20160823', '091210', 'assist              ', '20160823', '091308', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000175, 900000170, '�������޸�', 'system/sysmanager/taskrule/gotoEdit', '1', null, 900000170, '2', '1', '0', null, 12, null, '20160823', '091220', 'assist              ', '20160823', '091318', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000176, 900000170, '��ѯ��ɫ�б�', 'system/sysmanager/taskrule/listRole', '1', null, 900000170, '2', '1', '0', null, 11, null, '20160823', '094351', 'assist              ', '20160823', '191833', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000177, 900000168, '�ύ���ʻ��˵�����', 'system/sysmanager/menulocale/save', '1', null, -1, '2', '1', null, null, 900000177, null, '20161125', '120754', 'assist              ', '20161125', '120823', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000178, 900000170, '��ѯ������', 'system/sysmanager/taskrule/listOrgTree', '1', null, 900000170, '2', '1', '0', null, 13, null, '20160823', '094453', 'assist              ', '20160823', '191804', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000179, 900000106, '����', 'system/sysmanager/cache/search', '0', 'bf4j-icon-node-16', -1, '2', '0', '1', null, 900000179, null, '20161125', '160937', 'assist              ', '20161125', '160956', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000180, 900000179, '��ѯ�����б�', 'system/sysmanager/cache/list', '0', null, -1, '2', '1', '1', null, 900000180, null, '20161125', '161018', 'assist              ', '20161125', '161415', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000181, 900000180, '�������', 'system/sysmanager/cache/clear', '0', null, -1, '2', '1', '1', null, 900000181, null, '20161125', '161118', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000182, 900000170, '��ѯ����ڵ�', 'system/sysmanager/taskrule/listTaskNode', '1', null, 900000170, '2', '1', '0', null, 14, null, '20160824', '160429', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000183, 900000180, '������л���', 'system/sysmanager/cache/clearAll', '0', null, -1, '2', '1', '1', null, 900000183, null, '20161125', '161133', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000184, 900000170, '��ѯ���û�', 'system/sysmanager/taskrule/listTaskUser', '1', null, 900000170, '2', '1', '0', null, 6, null, '20160826', '155548', 'assist              ', '20160826', '155610', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000185, 900000170, '�������û����', 'system/sysmanager/taskrule/gotoUserAdd', '1', null, 900000170, '2', '1', '0', null, 4, null, '20160909', '163418', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000186, 900000170, '������ѯ�û�', 'system/sysmanager/taskrule/searchTaskUser', '1', null, 900000170, '2', '1', '0', null, 5, null, '20160909', '163502', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000187, 900000010, '��������չʾ', 'portal/center/queryCenterShowkData', '1', null, -1, '1', '1', null, null, 6, '���еĹ���������鵽�˹����£���Ϊ�ӹ���', '20160921', '170019', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000188, 900000010, '��������������', 'portal/center/queryTaskNum', '1', null, -1, '1', '1', null, null, 6, '���еĹ���������鵽�˹����£���Ϊ�ӹ���', '20160922', '204349', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000189, 900000010, '�������������ϸȡ��', 'portal/center/findTaskParam', '1', null, -1, '1', '1', null, null, 6, '���еĹ���������鵽�˹����£���Ϊ�ӹ���', '20160923', '163622', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000190, 900000180, '�Ƴ�������Ŀ', 'system/sysmanager/cache/remove', '0', null, -1, '2', '1', '1', null, 900000190, null, '20161125', '161148', 'assist              ', '20161125', '161159', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000191, 900000162, '������ʷ�б�', 'system/sysmanager/quartz/history', '0', null, 900000162, '2', '1', '0', null, 2, null, '20161128', '195615', 'assist              ', '20161207', '171649', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000200, 900000001, '��ݲ˵�', null, '1', null, -1, '1', '1', null, null, 4, '��¼����ʹ��', '20160819', '182016', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000201, 900000200, '�����ղ�', 'common/favorites/insert', '1', null, -1, '1', '1', null, null, 4, '��¼����ʹ��', '20160819', '183050', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000202, 900000200, '��ݲ˵��޸�', 'common/favorites/update', '1', null, -1, '1', '1', null, null, 13, '��¼����ʹ��', '20160824', '160137', 'assist              ', '20160825', '115320', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000203, 900000200, '��ݲ˵�ɾ��', 'common/favorites/delete', '1', null, -1, '1', '1', null, null, 4, '��¼����ʹ��', '20160825', '115309', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000204, 900000200, '��ݲ˵������б�', 'common/favorites/listMenuTree', '1', null, -1, '1', '1', null, null, 5, '��¼����ʹ��', '20160825', '175709', 'assist              ', '20160830', '114740', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000205, 900000200, '��ݲ˵��б�', 'common/favorites/list', '1', null, -1, '1', '1', null, null, 12, '��¼����ʹ��', '20160824', '101309', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000206, 900000200, '���ÿ�ݲ˵�', 'common/favorites/search', '2', null, -1, '1', '1', null, null, 11, '��¼����ʹ��', '20160823', '171808', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000207, 900000200, '������������', 'common/favorites/saveSort', '1', null, -1, '1', '1', null, null, 4, '��¼����ʹ��', '20160905', '091700', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (4, 900000109, '��ȡδ����Ϣ��', 'news/checkMsgNum', '1', 'bf4j-icon-node-12', -1, '1', '1', null, null, 4, null, '20160909', '174327', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (5, 900000109, '�鿴δ����Ϣ', 'news/checkMsgDetail', '1', 'bf4j-icon-node-12', -1, '1', '1', null, null, 5, null, '20160912', '094843', 'assist              ', '20160913', '090034', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000001, 0, '��������', null, '1', null, -1, '1', '1', null, null, 0, '���еĹ���������鵽�˹����£���Ϊ�ӹ���', null, null, null, '20160513', '110541', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000002, 900000001, '֪ͨ����', 'common/viewNoteice', '0', null, null, '1', '1', null, null, 0, 'ֻ��Ҫ��¼�Ϳ��Է���', null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000003, 900000001, '��������', 'common/leaderViewData', '0', null, null, '1', '1', null, null, 1, 'ֻ��Ҫ��¼�Ϳ��Է���', null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000004, 900000001, 'kindeditor�ļ�����', 'kindEdit/fileManager', '0', null, null, '1', '1', null, null, 2, 'ֻ��Ҫ��¼�Ϳ��Է���', null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000005, 900000001, 'kindeditor�ļ��ϴ�', 'kindEdit/fileUpload', '0', null, null, '1', '1', null, null, 3, 'ֻ��Ҫ��¼�Ϳ��Է���', null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000006, 900000001, '֪ͨ����', 'common/listNoteiceData', '0', null, null, '1', '1', null, null, 4, 'ֻ��Ҫ��¼�Ϳ��Է���', null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000007, 900000001, '֪ͨ����', 'common/loadpage', '0', null, null, '1', '1', null, null, 5, 'ֻ��Ҫ��¼�Ϳ��Է���', null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000008, 900000001, '��������', 'common/leaderView', '0', null, null, '1', '1', null, null, 6, 'ֻ��Ҫ��¼�Ϳ��Է���', null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000009, 900000001, '����ѡ��Ԫ��', 'common/select', '1', null, 900000001, '1', '1', null, null, 7, '��Ϊ����ѡ��Ԫ�ض�̬�����ݵĹ�', '20160505', '142643', 'assist              ', '20160505', '142722', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000010, 900000001, '��ҳ', null, '1', null, null, '1', '1', null, null, 8, '���еĹ���������鵽�˹����£���Ϊ�ӹ���', '20160428', '155555', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000011, 900000010, '�����б�', 'portal/memo/getMemoList', '4', null, null, '1', '1', null, null, 0, null, null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000012, 900000010, '��ҳ', 'main', '1', null, null, '1', '1', null, null, 1, null, null, null, null, '20160428', '173533', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000013, 900000010, '��ʽ�л�', 'uiholder/changeTheme', '1', null, -1, '1', '1', null, null, 2, null, '20160516', '163246', 'assist              ', '20160516', '163330', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000014, 900000010, '������Ϣ��ȡ', 'getTopMessage', '4', null, null, '1', '1', null, null, 2, null, null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000015, 900000010, '�����˵�', null, '1', null, null, '1', '1', null, null, 3, '���еĹ���������鵽�˹����£���Ϊ�ӹ���', '20160428', '160055', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000016, 900000015, '���˵�', 'leftMenu', '4', null, null, '1', '1', null, null, 0, null, null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000017, 900000015, '�����˵�', 'topMenu', '1', null, null, '1', '1', null, null, 1, null, null, null, null, '20160503', '194721', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000018, 900000015, 'MINI�˵�', 'miniMenu', '4', null, null, '1', '1', null, null, 6, 'ֻ��Ҫ��¼�Ϳ��Է���', null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000019, 900000015, '�����˵�', 'shortCutMenu', '1', null, null, '1', '1', null, null, 44, '���еĹ���������鵽�˹����£���Ϊ�ӹ���', '20160503', '151929', 'assist              ', '20160503', '155845', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000020, 900000010, '�����л�', 'uiholder/changeLocale', '1', null, -1, '1', '1', null, null, 4, null, '20160516', '163348', 'assist              ', '20160516', '163354', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000021, 900000010, '�û�����', null, '0', 'bf4j-icon-node-11', -1, '1', '1', null, null, 5, null, null, null, null, '20160513', '110547', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000022, 900000021, '���������޸�', null, '0', 'bf4j-icon-mid-19', -1, '1', '1', null, null, 3, null, '20160503', '192326', 'assist              ', '20160513', '110412', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000023, 900000022, '�޸ĸ�������', 'portal/password/gotoUpdatePersonalPassword', '2', 'bf4j-icon-mid-19', -1, '1', '1', null, 'module', 0, null, '20160503', '192241', 'assist              ', '20160516', '164947', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000194, 900000040, '���ʻ�', null, '1', 'bf4j-icon-node-15', -1, '2', '0', null, null, 900000194, null, '20161219', '174451', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000195, 900000194, '�˵����ʻ�ָ��ά��', 'system/sysmanager/menulocale/view', '1', 'bf4j-icon-node-10', -1, '2', '0', null, null, 900000195, null, '20161219', '175301', 'assist              ', '20161226', '140708', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000197, 900000195, '���ز˵����ʻ�ָ��ά������', 'system/sysmanager/menulocale/guide', '1', null, -1, '2', '1', null, null, 900000197, null, '20161223', '175910', 'assist              ', '20161223', '180752', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000024, 900000022, 'ִ�и��������޸�', 'portal/password/updatePersonalPassword', '0', null, -1, '1', '1', null, null, 1, null, null, null, null, '20160513', '110422', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000025, 900000021, '�û���������', null, '0', 'bf4j-icon-mid-18', -1, '1', '1', null, null, 55, null, '20160503', '192432', 'assist              ', '20160513', '110426', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000026, 900000025, 'ִ���޸��û�����', 'portal/setting/updateUserSettings', '0', null, null, '1', '1', null, null, 0, null, null, null, null, '20160503', '194529', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000027, 900000025, 'ִ�в�ѯ�û������б�', 'portal/setting/queryUserSettings', '0', null, null, '1', '1', null, null, 1, null, null, null, null, '20160503', '194524', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000028, 900000025, '�û���������', 'portal/setting/gotoUserSettings', '2', 'bf4j-icon-mid-18', -1, '1', '1', null, 'tab', 56, null, '20160504', '112416', 'assist              ', '20160513', '110434', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000029, 900000025, '��ȡ��������ö���б�', 'portal/setting/getUserComboData', '0', 'bf4j-icon-node-11', -1, '1', '1', null, null, 57, null, '20160504', '191205', 'assist              ', '20160513', '110438', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000030, 900000025, 'ִ�в�ѯ�û������б�(MAP)', 'portal/setting/queryUserSettingsMap', '0', 'bf4j-icon-mid-18', -1, '1', '1', null, null, 89, null, '20160516', '141516', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000031, 900000001, 'UI����', null, '1', null, -1, '1', '1', null, null, 9, null, '20160510', '183452', 'assist              ', '20160513', '110455', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000032, 900000031, '��ȡ����������(������', 'uiholder/getComboDatas', '1', null, -1, '1', '1', '1', null, 70, null, '20160510', '183541', 'assist              ', '20160513', '110459', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000033, 900000031, '���ػ�����', null, '1', null, -1, '1', '1', '1', null, 71, null, '20160510', '183749', 'assist              ', '20160513', '105254', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000034, 900000033, 'ͬ������', 'uiholder/getOrganTree', '1', null, 900000033, '1', '1', '0', null, 72, null, '20160513', '105209', 'assist              ', '20160513', '110506', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000035, 900000033, '�첽����', 'uiholder/getOrganChildren', '1', null, 900000033, '1', '1', '0', null, 73, null, '20160513', '105243', 'assist              ', '20160513', '110510', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000036, 900000031, '�л�����', 'uiholder/changeLocale', '1', null, -1, '1', '1', '0', null, 88, null, '20160516', '111028', 'assist              ', '20160516', '111036', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000037, 900000001, '�ϴ�����', null, '1', null, -1, '1', '1', null, null, 113, '���еĹ���������鵽�˹����£���Ϊ�ӹ���', '20160521', '112547', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000038, 900000037, '�ļ�����', 'doc/download', '0', null, -1, '1', '1', null, null, 0, 'ֻ��Ҫ��¼�Ϳ��Է���', null, null, null, '20160521', '112722', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000039, 900000037, '�ļ��ϴ�', 'doc/upload', '1', null, -1, '1', '1', null, null, 114, '���еĹ���������鵽�˹����£���Ϊ�ӹ���', '20160521', '112716', 'assist              ', '20160521', '112728', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000040, 0, 'ϵͳ����', null, '1', 'bf4j-icon-node-15', -1, '2', '0', null, null, 1, null, null, null, null, '20161125', '120527', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000041, 900000040, '�û�����', null, '0', 'bf4j-icon-node-11', null, '2', '0', null, null, 0, null, null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000042, 900000041, '�û�����', 'system/sysmanager/user/search', '0', 'bf4j-icon-node-27', -1, '2', '0', null, null, 1, '����ѡ�񡢲�ѯ�б������Ի�����ͬʱ���������б�', null, null, null, '20160516', '203921', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000043, 900000042, '��ѯ�û��б�', 'system/sysmanager/user/list', '0', null, null, '2', '1', '1', null, 2, null, '20160503', '151012', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000044, 900000042, '�û�ά��', null, '0', null, null, '2', '1', '1', null, 12, null, '20160503', '151130', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000045, 900000044, '�����û�ʱ��ѯ��ɫ�б�', 'system/sysmanager/user/listRoleForAdd', '0', null, 900000044, '2', '1', '0', null, 29, '�����û�ʱ��ѯ����ά���Ľ�ɫ�б�', '20160503', '151442', 'assist              ', '20160503', '151657', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000046, 900000044, '�����û�', 'system/sysmanager/user/insert', '0', null, 900000044, '2', '1', '0', null, 33, null, '20160503', '151742', 'assist              ', '20160503', '151825', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000047, 900000044, '�޸��û�ʱ��ѯ��ɫ�б�', 'system/sysmanager/user/listRoleForEdit', '0', null, 900000044, '2', '1', '0', null, 38, null, '20160503', '151820', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000048, 900000044, '�޸��û�', 'system/sysmanager/user/update', '0', null, 900000044, '2', '1', '0', null, 39, null, '20160503', '152014', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000049, 900000044, 'ɾ���û�', 'system/sysmanager/user/delete', '0', null, 900000044, '2', '1', '0', null, 40, null, '20160503', '152047', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000050, 900000042, '�û�״̬ά��', null, '0', null, null, '2', '1', '1', null, 26, null, '20160503', '151310', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000051, 900000050, '�����û�����', 'system/sysmanager/user/resetPassword', '0', null, 900000050, '2', '1', '0', null, 41, null, '20160503', '152144', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000052, 900000050, '����/ͣ��', 'system/sysmanager/user/updateUserStatus', '0', null, 900000050, '2', '1', '0', null, 42, null, '20160503', '152215', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000053, 900000050, '����/����', 'system/sysmanager/user/updateLockFlag', '0', null, 900000050, '2', '1', '0', null, 43, null, '20160503', '152235', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000054, 900000050, 'ǿ������', 'system/sysmanager/user/offline', '0', null, 900000050, '2', '1', '0', null, 45, null, '20160503', '152255', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000055, 900000041, '�����û�', 'system/sysmanager/onlineuser/search', '0', 'bf4j-icon-node-17', -1, '2', '0', null, null, 2, null, null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000056, 900000055, '��ѯ�����û�', 'system/sysmanager/onlineuser/list', '0', null, 900000055, '2', '1', null, null, 110, null, '20160520', '175028', 'assist              ', '20160525', '193501', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000057, 900000055, 'ǿ������', 'system/sysmanager/onlineuser/offline', '0', null, 900000055, '2', '1', null, null, 112, null, '20160520', '175551', 'assist              ', '20160525', '191729', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000058, 900000040, '��ɫ����', null, '0', 'bf4j-icon-node-21', null, '2', '0', null, null, 1, null, null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000059, 900000058, '��ɫ����', 'system/sysmanager/role/search', '0', 'bf4j-icon-node-24', -1, '2', '0', null, null, 0, '�����Ի����а����д���ѡ���treegrid�������ͬʱ�����б�ѡ��tab��ǩ', null, null, null, '20160516', '204035', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000060, 900000059, '��ѯ��ɫ�б�', 'system/sysmanager/role/list', '0', 'bf4j-icon-node-24', null, '2', '1', '1', null, 46, null, '20160503', '152404', 'assist              ', '20160503', '153215', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000061, 900000059, '��ɫά��', null, '0', null, null, '2', '1', '1', null, 47, null, '20160503', '152419', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000062, 900000061, '����ʱ��ѯȨ����', 'system/sysmanager/role/listRolePermissionForAdd', '0', null, 900000061, '2', '1', '0', null, 0, null, '20160503', '152546', 'assist              ', '20160516', '091356', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000063, 900000061, '����������', 'system/sysmanager/role/gotoAdd', '0', null, 900000061, '2', '1', '0', null, 0, null, '20160516', '091322', 'assist              ', '20160516', '091341', 'assist              ');
commit;
prompt 100 records committed...
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000064, 900000061, '������ɫ', 'system/sysmanager/role/insert', '0', null, 900000061, '2', '1', '0', null, 1, null, '20160503', '152607', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000065, 900000061, '�޸�ʱ��ѯȨ����', 'system/sysmanager/role/listRolePermissionForEdit', '0', null, 900000061, '2', '1', '0', null, 2, null, '20160503', '152632', 'assist              ', '20160516', '091802', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000066, 900000061, '����ʱ��ѯ��ɫ(����)', 'system/sysmanager/role/listRoleAllotForAdd', '0', null, 900000061, '2', '1', '0', null, 2, null, '20160516', '091528', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000067, 900000061, '�޸Ľ�ɫ', 'system/sysmanager/role/update', '0', null, 900000061, '2', '1', '0', null, 3, null, '20160503', '152644', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000068, 900000061, 'ɾ����ɫ', 'system/sysmanager/role/delete', '0', null, 900000061, '2', '1', '0', null, 4, null, '20160503', '152702', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000069, 900000061, '�������޸�', 'system/sysmanager/role/gotoEdit', '0', null, 900000061, '2', '1', '0', null, 4, null, '20160516', '091750', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000070, 900000061, '�޸�ʱ��ѯ��ɫ(����)', 'system/sysmanager/role/listRoleAllotForEdit', '0', null, 900000061, '2', '1', '0', null, 5, null, '20160516', '091836', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000071, 900000058, '��ɫ(����)����', 'system/sysmanager/roleallot/search', '0', 'bf4j-icon-node-04', -1, '2', '1', '0', null, 1, null, '20160516', '091936', 'assist              ', '20160517', '144403', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000072, 900000071, '��ѯ��ɫ(����)�б�', 'system/sysmanager/roleallot/list', '0', null, -1, '2', '1', '0', null, 79, null, '20160516', '092037', 'assist              ', '20160517', '144412', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000073, 900000071, '��ɫ(����)ά��', null, '0', null, -1, '2', '1', '0', null, 80, null, '20160516', '092109', 'assist              ', '20160517', '144418', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000074, 900000073, '����������', 'system/sysmanager/roleallot/gotoAdd', '0', null, 900000073, '2', '1', '0', null, 81, null, '20160516', '092149', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000075, 900000073, '����ʱ��ѯȨ����', 'system/sysmanager/roleallot/listRolePermissionForAdd', '0', null, 900000073, '2', '1', '0', null, 82, null, '20160516', '092226', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000076, 900000073, '������ɫ(����)', 'system/sysmanager/roleallot/insert', '0', null, 900000073, '2', '1', '0', null, 83, null, '20160516', '092318', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000077, 900000073, '�������޸�', 'system/sysmanager/roleallot/gotoEdit', '0', null, 900000073, '2', '1', '0', null, 84, null, '20160516', '092340', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000078, 900000073, '�޸�ʱ��ѯȨ����', 'system/sysmanager/roleallot/listRolePermissionForEdit', '0', null, 900000073, '2', '1', '0', null, 85, null, '20160516', '092408', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000079, 900000073, '�޸Ľ�ɫ(����)', 'system/sysmanager/role/update', '0', null, 900000073, '2', '1', '0', null, 86, null, '20160516', '092427', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000080, 900000073, 'ɾ����ɫ(����)', 'system/sysmanager/roleallot/delete', '0', null, 900000073, '2', '1', '0', null, 87, null, '20160516', '092451', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000081, 900000058, '��ɫԼ������', 'system/sysmanager/rolelimit/search', '0', 'bf4j-icon-node-25', -1, '2', '0', null, null, 2, null, null, null, null, '20160519', '123013', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000082, 900000081, '��ɫԼ����ѯ', 'system/sysmanager/rolelimit/list', '0', null, -1, '2', '1', '1', null, 93, null, '20160518', '093910', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000083, 900000081, '��ɫԼ��ά��', null, '0', null, -1, '2', '1', '1', null, 94, null, '20160518', '093936', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000084, 900000083, '����', 'system/sysmanager/rolelimit/insert', '0', null, 900000083, '2', '1', '0', null, 0, null, '20160518', '094038', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000085, 900000083, '����ʱ��ѯ��ɫ�б�', 'system/sysmanager/rolelimit/listRoleForAdd', '0', null, 900000083, '2', '1', '0', null, 0, null, '20160523', '124026', 'assist              ', '20160523', '125636', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000086, 900000083, '�޸�', 'system/sysmanager/rolelimit/update', '0', null, 900000083, '2', '1', '0', null, 1, null, '20160518', '094116', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000087, 900000083, '�޸�ʱ��ѯ��ɫ�б�', 'system/sysmanager/rolelimit/listRoleForEdit', '0', null, 900000083, '2', '1', '0', null, 2, null, '20160523', '124126', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000088, 900000083, 'ɾ��', 'system/sysmanager/rolelimit/delete', '0', null, 900000083, '2', '1', '0', null, 3, null, '20160518', '094137', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000089, 900000040, '��������', null, '0', 'bf4j-icon-node-07', null, '2', '0', null, null, 2, null, null, null, null, '20160429', '085958', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000090, 900000089, '����ά��', null, '0', 'bf4j-icon-node-09', null, '2', '0', null, null, 0, null, null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000091, 900000090, '��ֵ����', 'systemmanage/param/single/gotoList', '1', 'bf4j-icon-node-34', null, '2', '0', null, null, 2, null, null, null, null, '20160505', '090141', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000092, 900000091, 'ƽ̨����ά���б�', 'systemmanage/param/single/findSystemParamList', '4', null, null, '2', '1', null, null, 0, null, null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000093, 900000091, '�޸�ƽ̨����', 'systemmanage/param/single/updateParamValue', '1', null, null, '1', '1', null, null, 1, null, null, null, null, '20160505', '111248', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000094, 900000091, '��ȡ������������', 'systemmanage/param/single/getSingleParamComboData', '1', null, null, '1', '1', null, null, 58, null, '20160505', '105637', 'assist              ', '20160505', '105659', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000095, 900000090, 'ö�ٲ���', 'system/sysmanager/param/enum/search', '0', 'bf4j-icon-node-10', -1, '2', '0', null, null, 3, null, null, null, null, '20160708', '103840', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000096, 900000089, '�ڼ���ά��', 'system/sysmanager/holiday/search', '0', 'bf4j-icon-node-20', null, '2', '0', null, null, 1, null, null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000097, 900000089, '��������', 'system/sysmanager/org/search', '0', 'bf4j-icon-node-06', -1, '2', '0', '1', null, 2, 'ҳ���������ά������', null, null, null, '20160516', '204104', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000098, 900000097, '������', null, '0', null, null, '2', '1', '1', null, 0, null, null, null, null, '20160509', '101040', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000099, 900000098, '�첽�����ӻ���', 'system/sysmanager/org/getChildren', '0', null, 900000098, '2', '1', '0', null, 0, null, null, null, null, '20160509', '101149', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000100, 900000098, '���һ���', 'system/sysmanager/org/find', '0', null, 900000098, '2', '1', '0', null, 0, null, null, null, null, '20160509', '101209', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000101, 900000097, '����ά��', null, '0', null, null, '2', '1', '1', null, 2, null, null, null, null, '20160509', '101221', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000102, 900000101, '����', 'system/sysmanager/org/insert', '0', null, 900000101, '2', '1', '0', null, 0, null, null, null, null, '20160509', '101259', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000103, 900000101, '�޸�', 'system/sysmanager/org/update', '0', null, 900000101, '2', '1', '0', null, 0, null, null, null, null, '20160509', '101315', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000104, 900000101, '�϶�', 'system/sysmanager/org/move', '0', null, 900000101, '2', '1', '0', null, 0, null, null, null, null, '20161014', '102422', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000105, 900000101, 'ɾ��', 'system/sysmanager/org/delete', '0', null, 900000101, '2', '1', '0', null, 0, null, null, null, null, '20160509', '101350', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000106, 900000040, 'ϵͳ���', null, '0', 'bf4j-icon-node-16', -1, '2', '0', null, null, 3, null, null, null, null, '20161125', '092007', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000107, 900000106, '��¼��־', 'system/sysmanager/syslog/login/search', '0', 'bf4j-icon-node-18', -1, '2', '0', null, null, 18, null, null, null, null, '20160705', '093820', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000108, 900000106, '������־', 'system/sysmanager/syslog/visit/search', '0', 'bf4j-icon-node-19', -1, '2', '0', null, null, 19, null, null, null, null, '20160705', '093833', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000109, 900000040, '��Ϣ����', 'news/listView', '1', 'bf4j-icon-node-12', null, '2', '0', null, null, 4, null, null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000110, 900000109, '��ȡ�б�����', 'news/listDatas', '1', null, 900000109, '2', '1', null, null, 13, null, null, null, null, '20160518', '144122', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000111, 900000109, '�鿴��Ϣ��ϸ', 'news/toDetail', '1', null, null, '2', '1', null, null, 13, null, null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000112, 900000109, '����������', 'news/toAdd', '1', null, -1, '2', '1', null, null, 13, null, null, null, null, '20161011', '174403', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000113, 900000109, '�����޸Ĳ���', 'news/edit', '1', null, 900000109, '2', '1', null, null, 13, null, null, null, null, '20160518', '144119', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000114, 900000109, '���޸�ҳ��', 'news/toEdit', '1', null, 900000109, '2', '1', null, null, 13, null, null, null, null, '20160518', '144107', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000115, 900000109, '������������', 'news/add', '1', null, 900000109, '2', '1', null, null, 13, null, null, null, null, '20160518', '144111', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000116, 900000109, '����ɾ������', 'news/delete', '1', null, 900000109, '2', '1', null, null, 13, null, null, null, null, '20160518', '144125', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000192, 900000162, '��ǰ״̬��ѯ', 'system/sysmanager/quartz/searchstatus', '0', 'bf4j-icon-node-16', 900000162, '2', '1', '0', null, 900000192, null, '20161207', '171746', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000193, 900000162, '��ʷִ�в�ѯ', 'system/sysmanager/quartz/searchhistory', '0', 'bf4j-icon-node-16', 900000162, '2', '1', '0', null, 900000193, null, '20161207', '171828', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000196, 900000195, '����˵����ʻ�ָ��ά������', 'system/sysmanager/menulocale/saveGuide', '1', null, -1, '2', '1', null, null, 900000196, null, '20161230', '114948', 'assist              ', '20161230', '115012', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000117, 900000040, '����ָ��', 'guide/listView', '1', 'bf4j-icon-node-03', null, '2', '0', null, null, 5, null, null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000118, 900000117, '���޸�ҳ��', 'guide/toEdit', '0', null, 900000117, '2', '1', null, null, 0, '1', null, null, null, '20160518', '144310', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000119, 900000117, '��ȡ�б�����', 'guide/list', '0', null, 900000117, '2', '1', null, null, 1, null, null, null, null, '20160518', '144313', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000120, 900000117, '�����޸Ĳ���', 'guide/edit', '0', null, 900000117, '2', '1', null, null, 2, null, null, null, null, '20160518', '144318', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000121, 900000040, '�ĵ�����', 'system/sysmanager/document/search', '1', 'bf4j-icon-node-22', -1, '2', '0', null, null, 6, '�ĵ��ϴ�������', null, null, null, '20160518', '145658', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000122, 900000121, '�б�ҳ���ȡ����', 'system/sysmanager/document/list', '1', null, 900000121, '2', '1', null, null, 0, '�ĵ��ϴ�������', '20160518', '144340', 'assist              ', '20160518', '154927', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000123, 900000121, '�����ĵ�', 'system/sysmanager/document/update', '1', null, 900000121, '2', '1', null, null, 0, null, '20160518', '145613', 'assist              ', '20160518', '155006', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000124, 900000121, '���������ĵ�', 'system/sysmanager/document/insert', '1', null, -1, '2', '1', null, null, 0, null, '20160518', '145919', 'assist              ', '20160518', '154959', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000125, 900000121, '�����ĵ�', 'system/sysmanager/document/lock', '1', null, 900000121, '2', '1', null, null, 107, null, '20160518', '145442', 'assist              ', '20160518', '155012', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000126, 900000121, 'ɾ���ĵ�', 'system/sysmanager/document/delete', '1', null, 900000121, '2', '1', null, null, 108, null, '20160518', '145511', 'assist              ', '20160518', '155016', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000127, 0, 'DEMO', null, '0', 'bf4j-icon-node-39', null, '2', '0', null, null, 2, null, null, null, null, '20160429', '101540', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000128, 900000127, '���ļ��ϴ�', 'preUpload', '1', 'bf4j-icon-node-20', null, '1', '0', null, null, 0, null, null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000129, 900000128, 'ִ���ϴ�', 'doUpload', '4', null, null, '1', '1', null, null, 0, null, null, null, null, null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000130, 900000127, '�ۺ�Ч��һ', 'demo/demo1/demo1', '1', 'bf4j-icon-node-33', -1, '2', '0', null, null, 1, null, null, null, null, '20161125', '151934', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000131, 900000127, '�ۺ�Ч����', 'demo/demo2/demo2', '1', 'bf4j-icon-node-32', -1, '2', '0', null, null, 2, null, null, null, null, '20161125', '152000', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000132, 900000127, '�ۺ�Ч����', 'demo/demo3/demo3', '1', 'bf4j-icon-node-30', -1, '2', '0', null, null, 3, null, null, null, null, '20161125', '152008', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000133, 900000127, 'ͼ����ʽӳ���', 'demo/iconconfig/iconconfig', '0', 'bf4j-icon-node-01', -1, '0', '0', null, null, 5, null, '20160509', '154620', 'assist              ', '20161125', '152018', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000134, 900000127, '�������DEMO', 'demo/combos/search', '0', 'bf4j-icon-node-41', -1, '1', '0', null, null, 6, null, '20160516', '183836', 'assist              ', '20160516', '192825', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000135, 900000127, '��½ģ��', null, '0', 'bf4j-icon-node-39', -1, '0', '0', null, null, 9, null, '20160624', '173919', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000136, 900000135, '��½ģ��1', 'demo/login1', '0', 'bf4j-icon-node-39', -1, '0', '0', null, null, 900000136, null, '20160624', '173938', 'assist              ', '20160627', '110752', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000137, 900000135, '��½ģ��2', 'demo/login2', '0', 'bf4j-icon-node-39', -1, '0', '0', null, null, 900000137, null, '20160624', '173948', 'assist              ', '20160627', '094932', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000138, 900000127, '����ʽ', 'demo/money/search', '0', 'bf4j-icon-node-39', -1, '0', '0', null, null, 8, '�����չʾ', '20160520', '084558', 'assist              ', '20160520', '110800', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000139, 900000138, '����ʽ', 'demo/money/list', '0', 'bf4j-icon-node-39', -1, '0', '1', null, null, 106, '�����չʾ', '20160520', '115723', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000140, 900000082, '��ɫԼ����ϸ', 'system/sysmanager/rolelimit/find', '0', null, 900000082, '2', '1', '0', null, 900000140, null, '20160524', '111056', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000141, 900000096, '��ѯ��ǰ�·�����', 'system/sysmanager/holiday/list', '0', null, -1, '2', '1', null, null, 900000141, null, '20160525', '191706', 'assist              ', '20160525', '193518', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000142, 900000096, '�޸Ľڼ�����Ϣ', 'system/sysmanager/holiday/update', '0', null, -1, '2', '1', null, null, 900000142, null, '20160525', '193553', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000143, 900000128, '����', 'save', '1', 'bf4j-icon-node-20', -1, '1', '1', '0', null, 900000143, null, '20160614', '114629', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000144, 900000128, '��ȡ���ϴ��ļ��б�', 'initList', '1', 'bf4j-icon-node-20', -1, '1', '1', null, null, 900000144, null, '20160621', '101308', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000145, 900000127, 'DEMO5', 'demo/demo5', '0', 'bf4j-icon-node-39', -1, '1', '0', null, null, 4, null, '20160624', '095453', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000146, 900000037, '�ļ�ɾ��', 'doc/delete', '1', null, -1, '1', '1', '0', null, 900000146, 'ɾ���ĵ�����', '20160628', '183821', 'assist              ', '20160628', '183843', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000147, 900000095, '��ѯ��������', 'system/sysmanager/param/enum/listEnumDef', '0', null, -1, '2', '1', null, null, 0, null, '20160708', '103257', 'assist              ', '20160708', '164854', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000148, 900000095, '������������', 'system/sysmanager/param/enum/addEnumDef', '0', null, -1, '2', '1', null, null, 1, null, '20160708', '164917', 'assist              ', '20160713', '201822', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000149, 900000107, '��ѯ��־', 'system/sysmanager/syslog/login/list', '0', null, -1, '2', '1', null, null, 900000149, null, '20160705', '112246', 'assist              ', '20160705', '112318', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000150, 900000108, '��ѯ��־', 'system/sysmanager/syslog/visit/list', '0', null, -1, '2', '1', null, null, 900000150, null, '20160705', '112304', 'assist              ', '20160705', '112331', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000151, 900000095, '�༭��������', 'system/sysmanager/param/enum/editEnumDef', '0', null, -1, '2', '1', null, null, 2, null, '20160708', '164950', 'assist              ', '20160713', '201831', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000152, 900000095, '�༭�б��Ͳ�����ϸ', 'system/sysmanager/param/enum/editListData', '0', null, -1, '2', '1', null, null, 4, null, '20160708', '165032', 'assist              ', '20160713', '201839', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000153, 900000095, 'ɾ��������', 'system/sysmanager/param/enum/deleteEnumDef', '0', null, -1, '2', '1', null, null, 7, null, '20160708', '165056', 'assist              ', '20160713', '201604', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000154, 900000095, '��ѯ�б��Ͳ���', 'system/sysmanager/param/enum/listEnumData', '0', null, -1, '2', '1', null, null, 3, null, '20160711', '114906', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000155, 900000095, '��ѯ���Ͳ���', 'system/sysmanager/param/enum/listEnumTree', '0', null, -1, '2', '1', null, null, 6, null, '20160711', '114938', 'assist              ', '20160713', '201549', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000156, 900000095, '�������ڵ�', 'system/sysmanager/param/enum/editTreeNode', '0', null, -1, '2', '1', null, null, 5, null, '20160711', '115036', 'assist              ', '20160713', '201716', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000157, 900000095, '�������ڵ�', 'system/sysmanager/param/enum/addTreeNode', '0', null, -1, '2', '1', null, null, 5, null, '20160713', '201220', 'assist              ', '20160713', '201432', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000158, 900000095, '�ƶ����ڵ�', 'system/sysmanager/param/enum/moveTreeNode', '0', null, -1, '2', '1', null, null, 6, null, '20160713', '201301', 'assist              ', '20160713', '201446', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000159, 900000095, '�������ڵ�', 'system/sysmanager/param/enum/findTreeNode', '0', null, -1, '2', '1', null, null, 5, null, '20160713', '201320', 'assist              ', '20160713', '201458', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000160, 900000095, 'ɾ�����ڵ�', 'system/sysmanager/param/enum/deleteTreeNode', '0', null, -1, '2', '1', null, null, 8, null, '20160713', '201334', 'assist              ', '20160713', '201511', 'assist              ');
commit;
prompt 200 records committed...
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000161, 900000040, '�ڵ��������', null, '1', 'bf4j-icon-node-66', -1, '2', '0', null, null, 11, null, '20160820', '091700', 'assist              ', '20161219', '175627', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000162, 900000106, '��ʱ����', 'system/sysmanager/quartz/search', '0', 'bf4j-icon-node-16', -1, '2', '0', '1', null, 900000162, null, '20161125', '092039', 'assist              ', null, null, null);
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000163, 900000162, '��ʱ������', 'system/sysmanager/quartz/tree', '0', 'bf4j-icon-node-16', 900000162, '2', '1', '0', null, 0, null, '20161125', '092138', 'assist              ', '20161207', '171627', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000164, 900000162, '��ʱ�����б�', 'system/sysmanager/quartz/list', '0', 'bf4j-icon-node-16', 900000162, '2', '1', '0', null, 1, null, '20161125', '092202', 'assist              ', '20161207', '171639', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000165, 900000162, '��������', 'system/sysmanager/quartz/combo', '0', 'bf4j-icon-node-16', 900000162, '2', '1', '0', null, 3, null, '20161125', '092252', 'assist              ', '20161128', '193058', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000166, 900000194, '�˵����ʻ�', 'system/sysmanager/menulocale/search', '1', 'bf4j-icon-node-41', -1, '2', '0', null, null, 0, null, '20161125', '120633', 'assist              ', '20161219', '175709', 'assist              ');
insert into BF_MENU (menu_id, parent_menu_id, menu_name, menu_url, display_area, display_icon, depend_menu_id, author_level, menu_flag, perm_tree_flag, target_page, seqno, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000167, 900000161, '�������ά��', 'system/sysmanager/taskrule/search', '1', 'bf4j-icon-node-15', -1, '2', '0', null, null, 17, null, '20160820', '092841', 'assist              ', '20160820', '093806', 'assist              ');
commit;
prompt 207 records loaded
prompt Loading BF_MENU_I18N...
insert into BF_MENU_I18N (menu_id, locale, menu_name, des)
values (900000040, 'zh_CN', 'ϵͳ����', null);
insert into BF_MENU_I18N (menu_id, locale, menu_name, des)
values (900000127, 'en_UK', 'DEMO', null);
insert into BF_MENU_I18N (menu_id, locale, menu_name, des)
values (900000127, 'en_US', 'DEMO', null);
insert into BF_MENU_I18N (menu_id, locale, menu_name, des)
values (900000127, 'zh_CN', 'ʾ��', null);
insert into BF_MENU_I18N (menu_id, locale, menu_name, des)
values (900000127, 'zh_TW', 'ʾ��', null);
commit;
prompt 5 records loaded
prompt Loading BF_NEWS...
prompt Table is empty
prompt Loading BF_NEWS_LOG...
insert into BF_NEWS_LOG (user_id, msg_id, oper_date, oper_time)
values ('oulinhai', '6871567536006662', '20161230', '104637');
insert into BF_NEWS_LOG (user_id, msg_id, oper_date, oper_time)
values ('zhangjj', '234', '20160918', '170256');
insert into BF_NEWS_LOG (user_id, msg_id, oper_date, oper_time)
values ('zhangjj', '123', '20160918', '170306');
insert into BF_NEWS_LOG (user_id, msg_id, oper_date, oper_time)
values ('zhangjj', '6871567536006662', '20160918', '170314');
insert into BF_NEWS_LOG (user_id, msg_id, oper_date, oper_time)
values ('luowei', '3456636446980335', '20160919', '093603');
insert into BF_NEWS_LOG (user_id, msg_id, oper_date, oper_time)
values ('dev', '2092780193701405', '20160919', '150654');
insert into BF_NEWS_LOG (user_id, msg_id, oper_date, oper_time)
values ('zhangjj', '4030774284706526', '20160927', '151847');
insert into BF_NEWS_LOG (user_id, msg_id, oper_date, oper_time)
values ('liuscc', '5543343200565772', '20160925', '115827');
commit;
prompt 8 records loaded
prompt Loading BF_ONLINE_USER...
insert into BF_ONLINE_USER (session_id, user_id, client_ip, server_ip, browser, os, login_date, login_time)
values ('2016121618232046005483', 'dev', '192.168.22.180', '192.168.22.180', 'Chrome 54.0', 'Windows ', '20161216', '182320');
insert into BF_ONLINE_USER (session_id, user_id, client_ip, server_ip, browser, os, login_date, login_time)
values ('2016091211455607781765', 'luowei', '22.145.27.49', '22.145.27.49', 'IE8', 'Windows ', '20160912', '114556');
insert into BF_ONLINE_USER (session_id, user_id, client_ip, server_ip, browser, os, login_date, login_time)
values ('2016091317345393292030', 'linjisong', '169.254.35.35', '169.254.35.35', 'FireFox 48.0', 'Windows ', '20160913', '173453');
insert into BF_ONLINE_USER (session_id, user_id, client_ip, server_ip, browser, os, login_date, login_time)
values ('2016091817455962029934', 'luowei', '22.145.27.49', '22.145.27.49', 'IE8', 'Windows ', '20160918', '174559');
insert into BF_ONLINE_USER (session_id, user_id, client_ip, server_ip, browser, os, login_date, login_time)
values ('2016092215173841813934', 'luowei', '22.145.27.49', '22.145.27.49', 'IE8', 'Windows ', '20160922', '151738');
insert into BF_ONLINE_USER (session_id, user_id, client_ip, server_ip, browser, os, login_date, login_time)
values ('2016101415562288868552', 'oulinhai', '22.145.31.102', '22.145.31.102', 'Chrome 33.0', 'Windows ', '20161014', '155622');
insert into BF_ONLINE_USER (session_id, user_id, client_ip, server_ip, browser, os, login_date, login_time)
values ('2016123015300840373095', 'dev', '169.254.110.71', '169.254.110.71', 'Chrome 47.0', 'Windows ', '20161230', '153008');
insert into BF_ONLINE_USER (session_id, user_id, client_ip, server_ip, browser, os, login_date, login_time)
values ('2016123015451517517322', 'oulinhai', '192.168.22.83', '192.168.22.83', 'Chrome 47.0', 'Windows ', '20161230', '154515');
insert into BF_ONLINE_USER (session_id, user_id, client_ip, server_ip, browser, os, login_date, login_time)
values ('2016123017215901907059', 'dev', '192.168.22.200', '192.168.22.200', 'Chrome 35.0', 'Windows ', '20161230', '172159');
commit;
prompt 9 records loaded
prompt Loading BF_ORG...
insert into BF_ORG (org_id, org_name, sup_org_id, org_level, org_type, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('0000', '���ڷ���', '-1', 1, '1', '����', null, null, null, null, null, null);
insert into BF_ORG (org_id, org_name, sup_org_id, org_level, org_type, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('1000', '���пƼ���', '0000', 2, '2', '���в���', null, null, null, null, null, null);
insert into BF_ORG (org_id, org_name, sup_org_id, org_level, org_type, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('1001', '���пƼ���������', '1000', 3, '4', '���в��ſ���', null, null, null, null, null, null);
insert into BF_ORG (org_id, org_name, sup_org_id, org_level, org_type, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('1002', '���пƼ���������', '1000', 3, '4', '���в��ſ���', null, null, null, '20160715', '154120', 'dev                 ');
insert into BF_ORG (org_id, org_name, sup_org_id, org_level, org_type, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('2000', '���вƻᲿ', '0000', 2, '2', '���в���', null, null, null, null, null, null);
insert into BF_ORG (org_id, org_name, sup_org_id, org_level, org_type, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('2001', '���вƻᲿ������', '2000', 3, '4', '���в��ſ���', null, null, null, null, null, null);
insert into BF_ORG (org_id, org_name, sup_org_id, org_level, org_type, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('2002', '���вƻᲿ������', '2000', 3, '4', '���в��ſ���', null, null, null, null, null, null);
insert into BF_ORG (org_id, org_name, sup_org_id, org_level, org_type, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('3000', '�޺�֧��', '0000', 2, '3', 'һ��֧��', null, null, null, null, null, null);
insert into BF_ORG (org_id, org_name, sup_org_id, org_level, org_type, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('3001', '�޺�֧�й�˾ҵ��', '3000', 3, '5', 'һ��֧�в���', null, null, null, null, null, null);
insert into BF_ORG (org_id, org_name, sup_org_id, org_level, org_type, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('3002', '�޺�֧�мƻ�����', '3000', 3, '5', 'һ��֧�в���', null, null, null, null, null, null);
insert into BF_ORG (org_id, org_name, sup_org_id, org_level, org_type, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('3003', '����֧��', '3000', 3, '6', '����', null, null, null, null, null, null);
insert into BF_ORG (org_id, org_name, sup_org_id, org_level, org_type, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('3004', '���֧��', '3000', 3, '6', '����', null, null, null, null, null, null);
insert into BF_ORG (org_id, org_name, sup_org_id, org_level, org_type, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('4000', '����֧��', '0000', 2, '3', 'һ��֧��', null, null, null, null, null, null);
insert into BF_ORG (org_id, org_name, sup_org_id, org_level, org_type, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('4001', '����֧�й�˾ҵ��', '4000', 3, '5', 'һ��֧�в���', null, null, null, null, null, null);
insert into BF_ORG (org_id, org_name, sup_org_id, org_level, org_type, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('4002', '����֧�мƻ�����', '4000', 3, '5', 'һ��֧�в���', null, null, null, null, null, null);
insert into BF_ORG (org_id, org_name, sup_org_id, org_level, org_type, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('4003', '������֧��', '4000', 3, '6', '����', null, null, null, null, null, null);
insert into BF_ORG (org_id, org_name, sup_org_id, org_level, org_type, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('4004', '������֧��', '4000', 3, '6', '����', null, null, null, null, null, null);
commit;
prompt 17 records loaded
prompt Loading BF_PARAM_CFG...
insert into BF_PARAM_CFG (param_code, param_value)
values ('AUTO_LOGOUT_CYCLE', '30');
insert into BF_PARAM_CFG (param_code, param_value)
values ('AUTO_LOGOUT_DELAY', '30');
insert into BF_PARAM_CFG (param_code, param_value)
values ('DEV_MODE', 'DEBUG');
insert into BF_PARAM_CFG (param_code, param_value)
values ('DOC_FILE_FTP_DIR', 'webui/data/');
insert into BF_PARAM_CFG (param_code, param_value)
values ('DOC_FILE_FTP_IP', '22.145.29.77');
insert into BF_PARAM_CFG (param_code, param_value)
values ('DOC_FILE_FTP_PASSWORD', 'passw0rd');
insert into BF_PARAM_CFG (param_code, param_value)
values ('DOC_FILE_FTP_PORT', '22');
insert into BF_PARAM_CFG (param_code, param_value)
values ('DOC_FILE_FTP_TYPE', '2');
insert into BF_PARAM_CFG (param_code, param_value)
values ('DOC_FILE_FTP_USER', 'root');
insert into BF_PARAM_CFG (param_code, param_value)
values ('DOC_FILE_PATH', 'c:/attached/sys/');
insert into BF_PARAM_CFG (param_code, param_value)
values ('DOC_FILE_PATH_TEMP', 'c:/attached/temp/');
insert into BF_PARAM_CFG (param_code, param_value)
values ('DOC_STORE_FLAG', '2');
insert into BF_PARAM_CFG (param_code, param_value)
values ('KINDEDIT_FILE_LIMIT_SIZE', '102400');
insert into BF_PARAM_CFG (param_code, param_value)
values ('KINDEDIT_FILE_PATH', 'c:/attached/');
insert into BF_PARAM_CFG (param_code, param_value)
values ('PERMISSION_MODE', 'ACTION');
insert into BF_PARAM_CFG (param_code, param_value)
values ('SESSION_CLEAR_CRON', ' ');
insert into BF_PARAM_CFG (param_code, param_value)
values ('USER_LOCKED_TIME', '15');
insert into BF_PARAM_CFG (param_code, param_value)
values ('USER_MAX_SESSION_NUM', '500');
insert into BF_PARAM_CFG (param_code, param_value)
values ('USER_ROLE_MODE', 'MULTI');
insert into BF_PARAM_CFG (param_code, param_value)
values ('USER_SESSION_TIMEOUT', '30');
insert into BF_PARAM_CFG (param_code, param_value)
values ('USER_TRY_LOGIN_NUM', '6');
commit;
prompt 21 records loaded
prompt Loading BF_PARAM_DEF...
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('AUTO_LOGOUT_CYCLE', 'ÿ�����ٷ���ִ������ʱ�Ự', 'PLAINTEXT', 'SYSTEM_CONFIG', 'LONG', '1', '30', 'VALUE_SCOPE', '[10,+��)', 10, 'ÿ�����ٷ���ִ������ʱ�Ự');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('AUTO_LOGOUT_DELAY', 'ϵͳ��������ٷ��ӿ�ʼִ������ʱ�Ự', 'PLAINTEXT', 'SYSTEM_CONFIG', 'LONG', '1', '30', 'VALUE_SCOPE', '[10,+��)', 9, 'ϵͳ��������ٷ��ӿ�ʼִ������ʱ�Ự');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('DEV_MODE', '��ǰ�����Ƿ�Ϊ����ģʽ', 'PLAINTEXT', 'SYSTEM_CONFIG', 'VARCHAR', '1', 'DEBUG', null, null, 13, 'debug��ʾ����/����ģʽ');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('DOC_FILE_FTP_DIR', 'FTPĿ¼', 'PLAINTEXT', 'DOCMANGER', 'VARCHAR', '1', 'webui/data/', null, null, 1, '���Ŀ¼');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('DOC_FILE_FTP_IP', 'IP��ַ', 'PLAINTEXT', 'DOCMANGER', 'VARCHAR', '1', '22.145.29.77', null, null, 1, 'ftp��ַ');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('DOC_FILE_FTP_PASSWORD', 'FTP����', 'PLAINTEXT', 'DOCMANGER', 'VARCHAR', '1', 'passw0rd', null, null, 1, '�û�����');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('DOC_FILE_FTP_PORT', 'FTP�˿�', 'PLAINTEXT', 'DOCMANGER', 'INTEGER', '1', '22', null, null, 1, '�˿�');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('DOC_FILE_FTP_TYPE', 'FTP����', 'PLAINTEXT', 'DOCMANGER', 'VARCHAR', '1', '2', null, null, 1, '1��FTPģʽ ��2��SFTPģʽ');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('DOC_FILE_FTP_USER', 'FTP�û�', 'PLAINTEXT', 'DOCMANGER', 'VARCHAR', '1', 'root', null, null, 1, 'ftp�û���');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('DOC_FILE_PATH', '�����ϴ�Ŀ¼', 'PLAINTEXT', 'DOCMANGER', 'VARCHAR', '1', 'c:/attached/sys/', null, null, 1, '�����ϴ���Ĭ�ϵĸ�Ŀ¼');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('DOC_FILE_PATH_TEMP', '���������õ���ʱĿ¼', 'PLAINTEXT', 'DOCMANGER', 'VARCHAR', '1', 'c:/attached/temp/', null, null, null, '�������ص���ʱĿ¼������ļ�ѹ��ʱ����Ҫ�ŵĸ�Ŀ¼');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('DOC_STORE_FLAG', '�ļ��洢�ķ�ʽ', 'PLAINTEXT', 'DOCMANGER', 'VARCHAR', '1', '2', null, null, 1, '1�����ش洢��2��Զ��FTP�洢');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('KINDEDIT_FILE_LIMIT_SIZE', '�ļ��ϴ������ƴ�С', 'PLAINTEXT', 'KINDEDIT', 'INTEGER', '1', '102400', null, null, 2, '���ļ����ļ��Ĵ�С���ƣ���λK�������˴�ֵ�����ϴ�ʧ��');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('KINDEDIT_FILE_PATH', '�ļ��ϴ�Ŀ¼', 'PLAINTEXT', 'KINDEDIT', 'VARCHAR', '1', 'c:/attached/', null, null, 1, '���ļ����ļ��淽·�������ϴ���ͼƬ��word�ļ���excel�ļ�');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('PERMISSION_MODE', 'Ȩ��У��ģʽ', 'PLAINTEXT', 'SYSTEM_CONFIG', 'VARCHAR', '1', 'ACTION', 'DICT', 'PERMISSION_MODE', 12, 'ACTION У�鵽������ť����  MENU ֻҪ���Կ����˵��Ϳ���');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('SESSION_CLEAR_CRON', 'ϵͳ����ʱ�Ự����', 'PLAINTEXT', 'SYSTEM_CONFIG', 'VARCHAR', '1', ' ', ' ', ' ', 11, 'ϵͳ����ʱ�ỰCRON���ʽ����');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('USER_LAYOUT', '����', 'PLAINTEXT', 'USER_OPTION', 'VARCHAR', '1', null, 'DICT', 'USER_LAYOUT', 3, null);
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('USER_LOCALE', '���Ե���', 'PLAINTEXT', 'USER_OPTION', 'VARCHAR', '1', 'zh_CN', 'DICT', 'USER_LOCALE', 1, null);
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('USER_LOCKED_TIME', '�û�����ʱ��', 'PLAINTEXT', 'SYSTEM_CONFIG', 'INTEGER', '1', '15', 'VALUE_SCOPE', '[3,+��)', 4, '�û�����ʱ�䣬��λΪ���ӣ���Ϊ0ʱ���ò����������ã���ҪUSER_TRY_LOGIN_NUM����ʱ����ֵ��СΪ3����');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('USER_MAX_SESSION_NUM', '���Ự��', 'PLAINTEXT', 'SYSTEM_CONFIG', 'INTEGER', '1', '500', null, null, 6, 'ͬһʱ��ͬһ���û���������Ự��');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('USER_ROLE_MODE', '�û���ɫģʽ', 'PLAINTEXT', 'SYSTEM_CONFIG', 'VARCHAR', '1', 'MULTI', 'DICT', 'USER_ROLE_MODE', 7, 'һ���û������ɫʱ���Ǻϲ���ɫ��¼���ǵ���ɫ��¼ MULTI ���ɫ�ϲ� SINGLE ����ɫ��¼');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('USER_SESSION_TIMEOUT', '�Ự��ʱʱ��', 'PLAINTEXT', 'SYSTEM_CONFIG', 'INTEGER', '1', '30', 'VALUE_SCOPE', '[10,+��)', 7, '�Ự����һ��ʱ��û�����������Զ��˳�');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('USER_THEME', '��ʽ', 'PLAINTEXT', 'USER_OPTION', 'VARCHAR', '1', 'boc-red', 'DICT', 'USER_THEME', 2, null);
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('USER_TRY_LOGIN_NUM', '�û���¼��ೢ�Դ���', 'PLAINTEXT', 'SYSTEM_CONFIG', 'INTEGER', '1', '5', null, null, 5, '�����û����Ե�¼�Ĵ�����0��ʾ������');
insert into BF_PARAM_DEF (param_code, param_name, store_type, param_group, data_type, editable, default_value, value_rule, value_rule_param, seqno, des)
values ('USER_DEFAULT_ROLE', '�û�Ĭ�Ͻ�ɫ', 'PLAINTEXT', 'USER_OPTION', 'VARCHAR', '1', '-1', 'DICT', null, 8, '�û���Ĭ�Ͻ�ɫ��ֻ��USER_ROLE_MODE=SINGLEʱ������');
commit;
prompt 25 records loaded
prompt Loading BF_PARAM_ENUM_DATA...
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('TEST1477713702476', '1', '1', '1', 1, '1');
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('TEST1479105730520', '1', '1', null, 0, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('TREE_STATUS', 'ALLSUCCESS', 'ȫ���ɹ�', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('TREE_STATUS', 'ALLEXCEPTION', 'ȫ��ʧ��', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('TREE_STATUS', 'UNSTART_REACHED', 'δ����(�ѹ�����ʱ��)  ', null, 4, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('TREE_STATUS', 'PARTSUCCESS', '���ֳɹ�', null, 3, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('TREE_STATUS', 'UNSTART_UNREACH', 'δ����(δ������ʱ��)', null, 5, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('AUTHOR_LEVEL', '0', '������Ȩ', null, 1, '�����˿ɷ���');
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('AUTHOR_LEVEL', '1', '��Ȩ��¼�û�', null, 1, 'ֻҪ��¼���ɷ���');
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('AUTHOR_LEVEL', '2', '��Ȩ��֤', null, 3, 'ֻ��ͨ����Ȩ��֤���ܷ���');
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('BF_DOC_STATUS', '0', '����', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('BF_DOC_STATUS', '1', '����', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('BF_DOC_TYPE', '1', 'ϵͳ�ĵ�', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('BF_DOC_TYPE', '2', '�û��ĵ�', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('BF_NEWS_EFFECTIVE_DAY', '1', '������Ч', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('BF_NEWS_EFFECTIVE_DAY', '2', '������Ч', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('BF_NEWS_EFFECTIVE_DAY', '3', '������Ч', null, 3, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('BF_NEWS_MSG_LEVEL', '1', '��', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('BF_NEWS_MSG_LEVEL', '2', '��', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('BF_NEWS_MSG_LEVEL', '3', '��', null, 3, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('BF_NEWS_MSG_TYPE', '1', '��Ϣ', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('BF_NEWS_MSG_TYPE', '2', '����', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('BF_NEWS_MSG_TYPE', '3', '�ź�', null, 3, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('BOOLEAN', '0', '��', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('BOOLEAN', '1', '��', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('CERT_TYPE', '1', '���֤', '1', 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('CERT_TYPE', '2', '����', '2', 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('CERT_TYPE', '3', '����֤', '3', 3, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('CERT_TYPE', '4', '����', '4', 4, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('DATA_TYPE', 'BOOLEAN', '��������', null, 5, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('DATA_TYPE', 'CHAR', '�����ı�', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('DATA_TYPE', 'INTEGER', '����', null, 3, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('DATA_TYPE', 'LONG', '������', null, 6, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('DATA_TYPE', 'NUMBER', '��������', null, 4, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('DATA_TYPE', 'VARCHAR', '�䳤�ı�', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('DISPLAY_AREA', '0', 'ͷ����ʾ', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('DISPLAY_AREA', '1', '�ಿ��ʾ', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('DISPLAY_AREA', '2', '������ʾ', null, 3, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('ENUM_PARAM_TYPE', 'LIST', '�б���ö��', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('ENUM_PARAM_TYPE', 'TREE', '����ö��', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('KINDEDIT_STORE_FLAG', '1', '���ش洢', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('KINDEDIT_STORE_FLAG', '2', 'Զ��FTP�洢', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('MENU_FLAG', '0', '�˵�', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('MENU_FLAG', '1', '������', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('PERMISSION_MODE', 'ACTION', 'ACTION', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('PERM_TYPE', 'MENU', '�����˵�', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('PERM_TYPE', 'MENU_A', 'Aģ��˵�', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('PERM_TYPE', 'MENU_B', 'Bģ��˵�', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('RULE_TYPE', 'DICT', '�����ֵ�', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('RULE_TYPE', 'LENGTH_SCOPE', '���ȷ�Χ', null, 3, '��[3,6]');
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('RULE_TYPE', 'PATTERN', '������ʽ', null, 4, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('RULE_TYPE', 'VALUE_SCOPE', 'ȡֵ��Χ', null, 2, '��[3,+��)');
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('RUNNING_STATUS', 'EXCEPTION', '�쳣', null, 3, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('RUNNING_STATUS', 'EXECUTING', '������', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('RUNNING_STATUS', 'SUCCESS', '�ɹ�', null, 4, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('RUNNING_STATUS', 'WAITING', '�ȴ�', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('STORE_TYPE', 'DECODE', '������ܴ洢', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('STORE_TYPE', 'ENCRYPT', '���������Ĵ洢', null, 3, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('STORE_TYPE', 'PLAINTEXT', '���Ĵ洢', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('USER_LAYOUT', 'DEFAULT', 'Ĭ��', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('USER_LOCALE', 'en_US', 'English', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('USER_LOCALE', 'zh_CN', '��������', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('USER_LOCALE', 'zh_TW', '��������', null, 3, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('USER_ROLE_MODE', 'MULTI', '���ɫģʽ', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('USER_ROLE_MODE', 'SINGLE', '����ɫģʽ', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('USER_THEME', 'abc-green', '��ɫ', null, 3, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('USER_THEME', 'boc-red', '��ɫ', null, 2, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('USER_THEME', 'default', 'Ĭ��', null, 1, null);
insert into BF_PARAM_ENUM_DATA (param_code, data_code, data_text, data_param, seqno, des)
values ('RULE_TYPE', 'TABLE_COLUMN', '���з�Χ', null, 5, '��BF_ROLE[ROLE_ID=1]');
commit;
prompt 69 records loaded
prompt Loading BF_PARAM_ENUM_DEF...
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('PARA_TEST', '���Բ���3', 'BENEFORM4J_SYSTEM_CONFIG', 'LIST', null, 3, '222');
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('TREE_STATUS', '������״̬', 'COMMON', 'LIST', null, 2, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('AUTHOR_LEVEL', '��Ȩ����', 'MENU', 'LIST', null, 12, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('BF_DOC_STATUS', '�ĵ�״̬', 'USER_OPTION', 'LIST', null, null, '�ĵ�״̬');
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('BF_DOC_TYPE', '�ĵ�����', 'USER_OPTION', 'LIST', null, 1, '�ĵ�����');
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('BF_NEWS_EFFECTIVE_DAY', '��Ч��־', 'USER_OPTION', 'LIST', null, 1, '��Ϣ��Ч�ı�־');
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('BF_NEWS_MSG_LEVEL', '��Ϣ����', 'USER_OPTION', 'LIST', null, 1, '��Ϣ����ļ���');
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('BF_NEWS_MSG_TYPE', '��Ϣ����', 'USER_OPTION', 'LIST', null, 1, '��Ϣ���������');
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('BOOLEAN', '�Ƿ�', 'COMMON', 'LIST', null, 10, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('CERT_TYPE', '֤������', 'COMMON', 'LIST', '1', 16, '����');
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('DATA_TYPE', '��������', 'COMMON', 'LIST', null, 4, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('DISPLAY_AREA', '��ʾ����', 'MENU', 'LIST', null, 11, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('ENUM_PARAM_GROUP', 'ö�ٲ������', 'COMMON', 'TREE', null, 8, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('ENUM_PARAM_TYPE', 'ö�ٲ�������', 'COMMON', 'LIST', null, 9, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('HOLIDAY_LIST', '�ڼ���', 'COMMON', 'LIST', null, 18, '���ڽڼ���ά��');
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('KINDEDIT_STORE_FLAG', '�ĵ���ŵ�λ��', 'KINDEDIT', 'LIST', null, null, '�ĵ���ŷ�ʽ');
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('MENU_FLAG', '�˵���־', 'MENU', 'LIST', null, 13, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('PARAM_GROUP', '�������', 'COMMON', 'TREE', null, 6, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('PERMISSION_MODE', 'Ȩ��У��ģʽ', 'SYSTEM_CONFIG', 'LIST', null, 15, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('PERM_TYPE', 'Ȩ������', 'SYSTEM_CONFIG', 'LIST', null, 17, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('RUNNING_STATUS', '����״̬', 'COMMON', 'LIST', null, 1, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('STORE_TYPE', '���ݴ洢��ʽ', 'COMMON', 'LIST', null, 5, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('TEST', '���Բ���', 'COMMON', 'LIST', null, 100, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('TEST1477713702476', '����', 'TEST', 'LIST', '0', 1, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('TEST1478175164856', '����', 'TEST', 'LIST', '0', 1, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('TEST1479105730520', '����', 'TEST', 'LIST', '0', 1, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('USER_LAYOUT', '����', 'USER_OPTION', 'LIST', null, 2, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('USER_LOCALE', '����', 'USER_OPTION', 'LIST', null, 3, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('USER_ROLE_MODE', '�û���ɫģʽ', 'SYSTEM_CONFIG', 'LIST', null, 14, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('USER_THEME', '��ʽ', 'USER_OPTION', 'LIST', null, 1, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('VALUE_RULE', 'ȡֵ����', 'COMMON', 'LIST', null, 7, null);
insert into BF_PARAM_ENUM_DEF (param_code, param_name, param_group, param_attr, editable, seqno, des)
values ('TEST1479108701472', '����', 'TEST', 'LIST', null, 100, null);
commit;
prompt 32 records loaded
prompt Loading BF_PARAM_TREE_DATA...
insert into BF_PARAM_TREE_DATA (param_code, data_code, data_text, data_icon, parent_data_code, data_param, seqno, des)
values ('ENUM_PARAM_GROUP', 'COMMON', '����', null, null, null, 1, null);
insert into BF_PARAM_TREE_DATA (param_code, data_code, data_text, data_icon, parent_data_code, data_param, seqno, des)
values ('ENUM_PARAM_GROUP', 'USER_OPTION', '�û�����', null, null, null, 2, null);
insert into BF_PARAM_TREE_DATA (param_code, data_code, data_text, data_icon, parent_data_code, data_param, seqno, des)
values ('PARAM_GROUP', 'APP_PARAM', 'Ӧ�ü�����', null, 'PARAM_ROOT', null, 3, null);
insert into BF_PARAM_TREE_DATA (param_code, data_code, data_text, data_icon, parent_data_code, data_param, seqno, des)
values ('PARAM_GROUP', 'APP_PARAM1', 'Ӧ�ü�������1', null, 'APP_PARAM', null, 1, null);
insert into BF_PARAM_TREE_DATA (param_code, data_code, data_text, data_icon, parent_data_code, data_param, seqno, des)
values ('PARAM_GROUP', 'APP_PARAM1-1', 'Ӧ�ü�������1-1', null, 'APP_PARAM1', null, 0, null);
insert into BF_PARAM_TREE_DATA (param_code, data_code, data_text, data_icon, parent_data_code, data_param, seqno, des)
values ('PARAM_GROUP', 'APP_PARAM2', 'Ӧ�ü�������2', null, 'APP_PARAM1', null, 0, null);
insert into BF_PARAM_TREE_DATA (param_code, data_code, data_text, data_icon, parent_data_code, data_param, seqno, des)
values ('PARAM_GROUP', 'APP_SYSTEM_CONFIG', 'Ӧ��ϵͳ����', null, 'SYSTEM_CONFIG', null, 1, null);
insert into BF_PARAM_TREE_DATA (param_code, data_code, data_text, data_icon, parent_data_code, data_param, seqno, des)
values ('PARAM_GROUP', 'APP_USER_OPTION', 'Ӧ���û�����', null, 'USER_OPTION', null, 1, null);
insert into BF_PARAM_TREE_DATA (param_code, data_code, data_text, data_icon, parent_data_code, data_param, seqno, des)
values ('PARAM_GROUP', 'BENEFORM4J_SYSTEM_CONFIG', 'ƽ̨ϵͳ����', null, 'SYSTEM_CONFIG', null, 1, null);
insert into BF_PARAM_TREE_DATA (param_code, data_code, data_text, data_icon, parent_data_code, data_param, seqno, des)
values ('PARAM_GROUP', 'BENEFORM4J_USER_OPTION', 'ƽ̨�û�����', null, 'USER_OPTION', null, 1, null);
insert into BF_PARAM_TREE_DATA (param_code, data_code, data_text, data_icon, parent_data_code, data_param, seqno, des)
values ('PARAM_GROUP', 'DOCMANGER', 'ϵͳ�ĵ�����', null, 'SYSTEM_CONFIG', null, 4, null);
insert into BF_PARAM_TREE_DATA (param_code, data_code, data_text, data_icon, parent_data_code, data_param, seqno, des)
values ('PARAM_GROUP', 'KINDEDIT', 'KINDEDIT���ò���', null, 'SYSTEM_CONFIG', null, 3, null);
insert into BF_PARAM_TREE_DATA (param_code, data_code, data_text, data_icon, parent_data_code, data_param, seqno, des)
values ('PARAM_GROUP', 'PARAM_ROOT', '����', null, null, null, 1, null);
insert into BF_PARAM_TREE_DATA (param_code, data_code, data_text, data_icon, parent_data_code, data_param, seqno, des)
values ('PARAM_GROUP', 'SYSTEM_CONFIG', 'ϵͳ����', null, 'PARAM_ROOT', null, 1, null);
insert into BF_PARAM_TREE_DATA (param_code, data_code, data_text, data_icon, parent_data_code, data_param, seqno, des)
values ('PARAM_GROUP', 'TEST', '����', null, 'APP_PARAM', null, 100, null);
insert into BF_PARAM_TREE_DATA (param_code, data_code, data_text, data_icon, parent_data_code, data_param, seqno, des)
values ('PARAM_GROUP', 'USER_OPTION', '�û�����', null, 'PARAM_ROOT', null, 2, null);
commit;
prompt 16 records loaded
prompt Loading BF_PERM...
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000194, 'MENU', '900000194', '20161219', '174451', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000195, 'MENU', '900000195', '20161219', '175301', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000197, 'MENU', '900000197', '20161223', '175910', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000196, 'MENU', '900000196', '20161230', '114948', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (4, 'MENU', '4', '20160909', '174327', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (5, 'MENU', '5', '20160912', '094843', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000001, 'MENU', '900000001', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000002, 'MENU', '900000002', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000003, 'MENU', '900000003', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000004, 'MENU', '900000004', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000005, 'MENU', '900000005', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000006, 'MENU', '900000006', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000007, 'MENU', '900000007', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000008, 'MENU', '900000008', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000009, 'MENU', '900000009', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000010, 'MENU', '900000010', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000011, 'MENU', '900000011', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000012, 'MENU', '900000012', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000013, 'MENU', '900000013', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000014, 'MENU', '900000014', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000015, 'MENU', '900000015', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000016, 'MENU', '900000016', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000017, 'MENU', '900000017', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000018, 'MENU', '900000018', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000019, 'MENU', '900000019', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000020, 'MENU', '900000020', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000021, 'MENU', '900000021', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000022, 'MENU', '900000022', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000023, 'MENU', '900000023', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000024, 'MENU', '900000024', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000025, 'MENU', '900000025', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000026, 'MENU', '900000026', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000027, 'MENU', '900000027', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000028, 'MENU', '900000028', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000029, 'MENU', '900000029', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000030, 'MENU', '900000030', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000031, 'MENU', '900000031', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000032, 'MENU', '900000032', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000033, 'MENU', '900000033', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000034, 'MENU', '900000034', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000035, 'MENU', '900000035', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000036, 'MENU', '900000036', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000037, 'MENU', '900000037', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000038, 'MENU', '900000038', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000039, 'MENU', '900000039', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000040, 'MENU', '900000040', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000041, 'MENU', '900000041', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000042, 'MENU', '900000042', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000043, 'MENU', '900000043', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000044, 'MENU', '900000044', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000045, 'MENU', '900000045', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000046, 'MENU', '900000046', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000047, 'MENU', '900000047', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000048, 'MENU', '900000048', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000049, 'MENU', '900000049', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000050, 'MENU', '900000050', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000051, 'MENU', '900000051', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000052, 'MENU', '900000052', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000053, 'MENU', '900000053', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000054, 'MENU', '900000054', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000055, 'MENU', '900000055', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000056, 'MENU', '900000056', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000057, 'MENU', '900000057', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000058, 'MENU', '900000058', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000059, 'MENU', '900000059', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000060, 'MENU', '900000060', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000061, 'MENU', '900000061', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000062, 'MENU', '900000062', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000063, 'MENU', '900000063', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000064, 'MENU', '900000064', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000065, 'MENU', '900000065', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000066, 'MENU', '900000066', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000067, 'MENU', '900000067', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000068, 'MENU', '900000068', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000069, 'MENU', '900000069', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000070, 'MENU', '900000070', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000071, 'MENU', '900000071', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000072, 'MENU', '900000072', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000073, 'MENU', '900000073', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000074, 'MENU', '900000074', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000075, 'MENU', '900000075', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000076, 'MENU', '900000076', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000077, 'MENU', '900000077', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000078, 'MENU', '900000078', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000079, 'MENU', '900000079', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000080, 'MENU', '900000080', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000081, 'MENU', '900000081', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000082, 'MENU', '900000082', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000083, 'MENU', '900000083', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000084, 'MENU', '900000084', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000085, 'MENU', '900000085', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000086, 'MENU', '900000086', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000087, 'MENU', '900000087', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000088, 'MENU', '900000088', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000089, 'MENU', '900000089', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000090, 'MENU', '900000090', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000091, 'MENU', '900000091', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000092, 'MENU', '900000092', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000093, 'MENU', '900000093', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000094, 'MENU', '900000094', null, null, null, null, null, null);
commit;
prompt 100 records committed...
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000095, 'MENU', '900000095', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000096, 'MENU', '900000096', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000097, 'MENU', '900000097', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000098, 'MENU', '900000098', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000099, 'MENU', '900000099', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000100, 'MENU', '900000100', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000101, 'MENU', '900000101', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000102, 'MENU', '900000102', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000103, 'MENU', '900000103', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000104, 'MENU', '900000104', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000105, 'MENU', '900000105', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000106, 'MENU', '900000106', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000107, 'MENU', '900000107', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000108, 'MENU', '900000108', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000109, 'MENU', '900000109', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000110, 'MENU', '900000110', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000111, 'MENU', '900000111', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000112, 'MENU', '900000112', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000113, 'MENU', '900000113', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000114, 'MENU', '900000114', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000115, 'MENU', '900000115', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000116, 'MENU', '900000116', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000117, 'MENU', '900000117', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000118, 'MENU', '900000118', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000119, 'MENU', '900000119', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000120, 'MENU', '900000120', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000121, 'MENU', '900000121', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000122, 'MENU', '900000122', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000123, 'MENU', '900000123', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000124, 'MENU', '900000124', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000125, 'MENU', '900000125', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000126, 'MENU', '900000126', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000127, 'MENU', '900000127', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000128, 'MENU', '900000128', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000129, 'MENU', '900000129', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000130, 'MENU', '900000130', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000131, 'MENU', '900000131', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000132, 'MENU', '900000132', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000133, 'MENU', '900000133', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000134, 'MENU', '900000134', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000135, 'MENU', '900000135', '20160624', '173919', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000136, 'MENU', '900000136', '20160624', '173938', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000137, 'MENU', '900000137', '20160624', '173948', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000138, 'MENU', '900000138', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000139, 'MENU', '900000139', null, null, null, null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000140, 'MENU', '900000140', '20160524', '111056', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000141, 'MENU', '900000141', '20160525', '191706', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000142, 'MENU', '900000142', '20160525', '193553', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000143, 'MENU', '900000143', '20160614', '114629', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000144, 'MENU', '900000144', '20160621', '101308', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000145, 'MENU', '900000145', '20160624', '095453', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000146, 'MENU', '900000146', '20160628', '183821', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000147, 'MENU', '900000147', '20160708', '103257', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000148, 'MENU', '900000148', '20160708', '164917', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000149, 'MENU', '900000149', '20160705', '112246', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000150, 'MENU', '900000150', '20160705', '112304', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000151, 'MENU', '900000151', '20160708', '164950', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000152, 'MENU', '900000152', '20160708', '165032', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000153, 'MENU', '900000153', '20160708', '165056', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000154, 'MENU', '900000154', '20160711', '114906', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000155, 'MENU', '900000155', '20160711', '114938', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000156, 'MENU', '900000156', '20160711', '115036', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000157, 'MENU', '900000157', '20160713', '201220', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000158, 'MENU', '900000158', '20160713', '201301', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000159, 'MENU', '900000159', '20160713', '201320', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000160, 'MENU', '900000160', '20160713', '201334', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000161, 'MENU', '900000161', '20160820', '091700', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000162, 'MENU', '900000162', '20161125', '092039', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000163, 'MENU', '900000163', '20161125', '092138', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000164, 'MENU', '900000164', '20161125', '092202', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000165, 'MENU', '900000165', '20161125', '092252', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000166, 'MENU', '900000166', '20161125', '120633', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000167, 'MENU', '900000167', '20160820', '092841', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000168, 'MENU', '900000168', '20161125', '120724', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000169, 'MENU', '900000169', '20160820', '093112', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000170, 'MENU', '900000170', '20160820', '093127', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000171, 'MENU', '900000171', '20160820', '093250', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000172, 'MENU', '900000172', '20160820', '093312', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000173, 'MENU', '900000173', '20160820', '093324', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000174, 'MENU', '900000174', '20160823', '091210', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000175, 'MENU', '900000175', '20160823', '091220', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000176, 'MENU', '900000176', '20160823', '094351', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000177, 'MENU', '900000177', '20161125', '120754', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000178, 'MENU', '900000178', '20160823', '094453', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000179, 'MENU', '900000179', '20161125', '160937', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000180, 'MENU', '900000180', '20161125', '161018', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000181, 'MENU', '900000181', '20161125', '161118', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000182, 'MENU', '900000182', '20160824', '160429', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000183, 'MENU', '900000183', '20161125', '161133', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000184, 'MENU', '900000184', '20160826', '155548', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000185, 'MENU', '900000185', '20160909', '163418', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000186, 'MENU', '900000186', '20160909', '163502', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000187, 'MENU', '900000187', '20160921', '170019', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000188, 'MENU', '900000188', '20160922', '204349', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000189, 'MENU', '900000189', '20160923', '163622', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000190, 'MENU', '900000190', '20161125', '161148', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000191, 'MENU', '900000191', '20161128', '195615', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000200, 'MENU', '900000200', '20160819', '182016', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000201, 'MENU', '900000201', '20160819', '183050', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000202, 'MENU', '900000202', '20160824', '160137', 'assist', null, null, null);
commit;
prompt 200 records committed...
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000203, 'MENU', '900000203', '20160825', '115309', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000204, 'MENU', '900000204', '20160825', '175709', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000205, 'MENU', '900000205', '20160824', '101309', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000206, 'MENU', '900000206', '20160823', '171808', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000207, 'MENU', '900000207', '20160905', '091700', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000192, 'MENU', '900000192', '20161207', '171746', 'assist', null, null, null);
insert into BF_PERM (perm_id, perm_type, perm_type_key, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (900000193, 'MENU', '900000193', '20161207', '171828', 'assist', null, null, null);
commit;
prompt 207 records loaded
prompt Loading BF_ROLE...
insert into BF_ROLE (role_id, role_name, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, '����Ա', '��������Ա', null, null, null, '20161230', '115149', 'dev                 ');
insert into BF_ROLE (role_id, role_name, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (2, 'ϵͳ����Ա', 'ϵͳ����Ա', null, null, null, '20161125', '104152', 'linjisong           ');
insert into BF_ROLE (role_id, role_name, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (3, '���й���Ա', '' || chr(9) || '' || chr(9) || '' || chr(9) || '' || chr(9) || '                ', null, null, null, '20161125', '104157', 'linjisong           ');
insert into BF_ROLE (role_id, role_name, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (4, '�����û�', '' || chr(9) || '' || chr(9) || '' || chr(9) || '' || chr(9) || '                ', null, null, null, '20161125', '104202', 'linjisong           ');
insert into BF_ROLE (role_id, role_name, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (5, '֧�й���Ա', '' || chr(9) || '' || chr(9) || '' || chr(9) || '' || chr(9) || '                ', null, null, null, '20161125', '104208', 'linjisong           ');
insert into BF_ROLE (role_id, role_name, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (6, '֧���û�', '' || chr(9) || '' || chr(9) || '' || chr(9) || '' || chr(9) || '                ', null, null, null, '20161125', '104212', 'linjisong           ');
insert into BF_ROLE (role_id, role_name, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (99, '�����ɫ99', '����99', null, null, null, '20161125', '104220', 'linjisong           ');
commit;
prompt 7 records loaded
prompt Loading BF_ROLE_ALLOT...
insert into BF_ROLE_ALLOT (role_allot_id, role_allot_name, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, '����Ա(����)', null, null, null, null, null, null, null);
insert into BF_ROLE_ALLOT (role_allot_id, role_allot_name, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (2, 'ϵͳ����Ա(����)', null, null, null, null, null, null, null);
insert into BF_ROLE_ALLOT (role_allot_id, role_allot_name, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (3, '���й���Ա(����)', null, null, null, null, null, null, null);
insert into BF_ROLE_ALLOT (role_allot_id, role_allot_name, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (4, '�����û�(����)', null, null, null, null, null, null, null);
insert into BF_ROLE_ALLOT (role_allot_id, role_allot_name, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (5, '֧�й���Ա(����)', null, null, null, null, null, null, null);
insert into BF_ROLE_ALLOT (role_allot_id, role_allot_name, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (6, '֧���û�(����)', null, null, null, null, null, null, null);
insert into BF_ROLE_ALLOT (role_allot_id, role_allot_name, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (7, '�����û�(����)', null, null, null, null, null, null, null);
insert into BF_ROLE_ALLOT (role_allot_id, role_allot_name, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, '���Խ�ɫ(����)', null, null, null, null, null, null, null);
commit;
prompt 8 records loaded
prompt Loading BF_ROLE_ALLOT_PERM...
prompt Table is empty
prompt Loading BF_ROLE_LIMIT...
insert into BF_ROLE_LIMIT (limit_no, role_id, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('20160405489997106217477708465720', 2, null, '20161027', '155904', null, null, null, 'dev                 ');
insert into BF_ROLE_LIMIT (limit_no, role_id, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('20160405489997106217477708465720', 3, null, '20161027', '155904', null, null, null, 'dev                 ');
insert into BF_ROLE_LIMIT (limit_no, role_id, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('20160405489997106217477708465720', 4, null, '20161027', '155904', null, null, null, 'dev                 ');
insert into BF_ROLE_LIMIT (limit_no, role_id, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('20160523125751153873121898029652', 1, null, '20160523', '125751', 'dev                 ', null, null, null);
insert into BF_ROLE_LIMIT (limit_no, role_id, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('20160523125751153873121898029652', 2, null, '20160523', '125751', 'dev                 ', null, null, null);
insert into BF_ROLE_LIMIT (limit_no, role_id, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('20160523125825920498367363011473', 1, null, '20160523', '125825', 'dev                 ', null, null, null);
insert into BF_ROLE_LIMIT (limit_no, role_id, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('20160523125825920498367363011473', 3, null, '20160523', '125825', 'dev                 ', null, null, null);
insert into BF_ROLE_LIMIT (limit_no, role_id, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('20160523125825920498367363011473', 5, null, '20160523', '125825', 'dev                 ', null, null, null);
insert into BF_ROLE_LIMIT (limit_no, role_id, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('20160523125825920498367363011473', 6, null, '20160523', '125825', 'dev                 ', null, null, null);
insert into BF_ROLE_LIMIT (limit_no, role_id, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('20160523191348389190463595768498', 1, null, '20160523', '191348', 'dev                 ', null, null, null);
insert into BF_ROLE_LIMIT (limit_no, role_id, des, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('20160523191348389190463595768498', 5, null, '20160523', '191348', 'dev                 ', null, null, null);
commit;
prompt 11 records loaded
prompt Loading BF_ROLE_PERM...
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000040, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000041, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000042, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000043, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000044, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000050, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000055, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000056, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000057, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000058, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000059, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000060, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000061, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000081, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000082, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000083, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000089, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000090, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000091, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000092, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000095, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000147, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000148, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000151, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000154, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000152, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000159, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000156, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000157, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000155, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000158, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000153, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000160, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000096, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000141, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000142, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000097, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000098, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000101, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000106, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000107, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000149, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000108, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000150, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000162, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000179, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000180, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000181, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000183, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000190, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000109, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000111, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000112, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000114, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000110, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000116, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000115, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000113, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000117, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000118, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000119, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000120, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000121, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000124, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000123, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000122, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000125, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000126, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000161, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000167, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000169, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000170, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000194, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000166, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000168, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000177, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000195, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000196, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000197, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000127, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000130, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000131, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 900000132, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000040, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000041, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000042, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000043, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000044, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000050, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000055, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000056, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000057, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000058, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000059, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000060, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000061, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000081, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000082, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000083, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000089, null, null, null, null, null, null, null);
commit;
prompt 100 records committed...
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000090, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000091, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000092, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000095, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000096, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000097, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000098, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000101, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000121, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000122, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000123, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000124, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000125, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000126, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000141, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000142, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000147, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000148, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000151, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000152, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000153, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000154, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000155, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000156, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000157, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000158, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000159, null, null, null, null, null, null, null);
insert into BF_ROLE_PERM (role_id, perm_id, perm_attr, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 900000160, null, null, null, null, null, null, null);
commit;
prompt 128 records loaded
prompt Loading BF_ROLE_ROLE_ALLOT...
insert into BF_ROLE_ROLE_ALLOT (role_id, role_allot_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (1, 1, null, null, null, null, null, null);
insert into BF_ROLE_ROLE_ALLOT (role_id, role_allot_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (2, 2, null, null, null, null, null, null);
insert into BF_ROLE_ROLE_ALLOT (role_id, role_allot_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (3, 3, null, null, null, null, null, null);
insert into BF_ROLE_ROLE_ALLOT (role_id, role_allot_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (4, 4, null, null, null, null, null, null);
insert into BF_ROLE_ROLE_ALLOT (role_id, role_allot_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (5, 5, null, null, null, null, null, null);
insert into BF_ROLE_ROLE_ALLOT (role_id, role_allot_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (6, 6, null, null, null, null, null, null);
insert into BF_ROLE_ROLE_ALLOT (role_id, role_allot_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values (8, 8, null, null, null, null, null, null);
commit;
prompt 7 records loaded
prompt Loading BF_SHORT_MENU...
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('2', null, 'dev', 900000028, '���˲�������', null, 4, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160825150356834746', null, 'zhangjj', 900000107, '��¼��־', 'bf4j-icon-mid-09', 5, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160829111623029963', null, 'zhangjj', 900000055, '�����û�', 'bf4j-icon-mid-03', 9, '999');
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160829111700076434', null, 'zhangjj', 900000130, '�ۺ�Ч��123456790', 'bf4j-icon-mid-09', 3, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160831105720562495', null, 'zhangjj', 900000109, '��Ϣ����', 'bf4j-icon-mid-09', 2, '000333');
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160831170348049221', null, 'zhangjj', 900000132, '�ۺ�Ч��3', 'bf4j-icon-mid-02', 8, '55554444');
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160831170618091820', null, 'zhangjj', 900000131, '�ۺ�Ч��2', 'bf4j-icon-mid-06', 10, '69874321');
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160831173803968575', null, 'zhangjj', 900000133, 'ͼ����ʽӳ���', 'bf4j-icon-mid-13', 6, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160831180648219634', null, 'zhangjj', 900000097, '��������9', 'bf4j-icon-mid-01', 11, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160902092104323257', null, 'zhangjj', 900000121, '�ĵ�����', 'bf4j-icon-mid-09', 1, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160905115226188418', null, 'luowei', 900000109, '��Ϣ����', 'bf4j-icon-mid-03', 2, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160905115305485667', null, 'luowei', 900000117, '����ָ��', 'bf4j-icon-mid-02', 1, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160905115318308172', null, 'luowei', 900000121, '�ĵ�����', 'bf4j-icon-mid-09', 4, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160905143525662836', null, 'zhangjj', 900000206, '��ݲ˵�����', 'bf4j-icon-mid-16', 12, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160906100523983972', null, 'zhangjj', 900000042, '�û�����', 'bf4j-icon-mid-18', 7, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160907175105717135', null, 'zhangjj', 900000059, '��ɫ����', 'bf4j-icon-mid-09', 13, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160908093737793603', null, 'luowei', 900000097, '��������', 'bf4j-icon-mid-06', 5, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160910160701752054', null, 'liuscc', 900000167, '�������ά��', 'bf4j-icon-mid-09', 1, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160910161644422154', null, 'luowei', 900000091, '��ֵ����', 'bf4j-icon-mid-06', 6, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160910161702200804', null, 'luowei', 900000055, '�����û�', 'bf4j-icon-mid-02', 7, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160910161728740439', null, 'luowei', 900000059, '��ɫ����', 'bf4j-icon-mid-18', 8, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160912115403862266', null, 'zhangjj', 900000117, '����ָ��', 'bf4j-icon-mid-09', 4, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160912115900182305', null, 'luowei', 900000081, '��ɫԼ������', 'bf4j-icon-mid-05', 10, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('20160924144010473547', null, 'liuscc', 900000042, '�û�����', 'bf4j-icon-mid-05', 2, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('3', null, 'luowei', 900000028, '���˲�������', 'bf4j-icon-mid-03', 9, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('4', null, 'luowei', 900000023, '�޸ĸ�������', 'bf4j-icon-mid-06', 3, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('5', null, 'oulinhai', 900000028, '���˲�������', null, null, null);
insert into BF_SHORT_MENU (key_id, parent_id, user_id, menu_id, short_menu_name, display_icon, seqno, des)
values ('6', null, 'oulinhai', 900000023, '�޸ĸ�������', null, null, null);
commit;
prompt 28 records loaded
prompt Loading BF_TASK_DETAIL...
prompt Table is empty
prompt Loading BF_TASK_LIMIT_ORGS...
insert into BF_TASK_LIMIT_ORGS (key_id, org_id)
values ('1', '1');
insert into BF_TASK_LIMIT_ORGS (key_id, org_id)
values ('20160926114359053787141883283540', '17847');
insert into BF_TASK_LIMIT_ORGS (key_id, org_id)
values ('20160926161615589100264594221860', '17847');
insert into BF_TASK_LIMIT_ORGS (key_id, org_id)
values ('20160925115038436351537925433242', '17847');
insert into BF_TASK_LIMIT_ORGS (key_id, org_id)
values ('20160925115113803282495839794525', '17847');
commit;
prompt 5 records loaded
prompt Loading BF_TASK_LIMIT_ROLES...
insert into BF_TASK_LIMIT_ROLES (key_id, role_id)
values ('20160926161152452864954861332246', '1');
insert into BF_TASK_LIMIT_ROLES (key_id, role_id)
values ('20160926161152452864954861332246', '2');
insert into BF_TASK_LIMIT_ROLES (key_id, role_id)
values ('20160926161607574464972883358507', '1');
insert into BF_TASK_LIMIT_ROLES (key_id, role_id)
values ('20160926161607574464972883358507', '2');
insert into BF_TASK_LIMIT_ROLES (key_id, role_id)
values ('20160926161615589100264594221860', '1');
insert into BF_TASK_LIMIT_ROLES (key_id, role_id)
values ('20160926114359053787141883283540', '3');
insert into BF_TASK_LIMIT_ROLES (key_id, role_id)
values ('20160926114359053787141883283540', '2');
insert into BF_TASK_LIMIT_ROLES (key_id, role_id)
values ('20160925115038436351537925433242', '1');
insert into BF_TASK_LIMIT_ROLES (key_id, role_id)
values ('20160925115038436351537925433242', '2');
insert into BF_TASK_LIMIT_ROLES (key_id, role_id)
values ('20160925115113803282495839794525', '1');
insert into BF_TASK_LIMIT_ROLES (key_id, role_id)
values ('20160925115113803282495839794525', '2');
commit;
prompt 11 records loaded
prompt Loading BF_TASK_LIMIT_USERS...
insert into BF_TASK_LIMIT_USERS (key_id, user_id)
values ('20160926161152452864954861332246', 'liuscc');
insert into BF_TASK_LIMIT_USERS (key_id, user_id)
values ('20160926161152452864954861332246', 'luowei');
insert into BF_TASK_LIMIT_USERS (key_id, user_id)
values ('20160926161607574464972883358507', 'liuscc');
insert into BF_TASK_LIMIT_USERS (key_id, user_id)
values ('20160926161607574464972883358507', 'luowei');
insert into BF_TASK_LIMIT_USERS (key_id, user_id)
values ('20160926161615589100264594221860', 'liuscc');
insert into BF_TASK_LIMIT_USERS (key_id, user_id)
values ('20160926114359053787141883283540', 'liuscc');
insert into BF_TASK_LIMIT_USERS (key_id, user_id)
values ('20160926114359053787141883283540', 'luowei');
insert into BF_TASK_LIMIT_USERS (key_id, user_id)
values ('20160925115038436351537925433242', 'liuscc');
insert into BF_TASK_LIMIT_USERS (key_id, user_id)
values ('20160925115038436351537925433242', 'luowei');
insert into BF_TASK_LIMIT_USERS (key_id, user_id)
values ('20160925115113803282495839794525', 'liuscc');
commit;
prompt 10 records loaded
prompt Loading BF_TASK_LOG...
insert into BF_TASK_LOG (log_id, key_id, task_id, task_date, task_time, deal_oper, deal_date, deal_time)
values ('20160924214042649251780113148027', '20160924170833505262978553899000', '20160824002', '20160924', '170833', 'liuscc', '20160924', '214042');
insert into BF_TASK_LOG (log_id, key_id, task_id, task_date, task_time, deal_oper, deal_date, deal_time)
values ('20160924214908739431221794570252', '20160924172203050078073921536634', '20160824002', '20160924', '172202', 'liuscc', '20160924', '214908');
insert into BF_TASK_LOG (log_id, key_id, task_id, task_date, task_time, deal_oper, deal_date, deal_time)
values ('20160925114532724678981281149584', '20160924214049262099999222089898', '20160824003', '20160924', '214042', 'liuscc', '20160925', '114532');
insert into BF_TASK_LOG (log_id, key_id, task_id, task_date, task_time, deal_oper, deal_date, deal_time)
values ('20160925114559006088743898112073', '20160924214909893770852380946161', '20160824003', '20160924', '214908', 'liuscc', '20160925', '114559');
insert into BF_TASK_LOG (log_id, key_id, task_id, task_date, task_time, deal_oper, deal_date, deal_time)
values ('20160925114658357461643651862834', '20160924171557221658256811342765', '20160824002', '20160924', '171557', 'liuscc', '20160925', '114658');
insert into BF_TASK_LOG (log_id, key_id, task_id, task_date, task_time, deal_oper, deal_date, deal_time)
values ('20160925114704900033289649418368', '20160924171155256676762611688604', '20160824002', '20160924', '171155', 'liuscc', '20160925', '114704');
insert into BF_TASK_LOG (log_id, key_id, task_id, task_date, task_time, deal_oper, deal_date, deal_time)
values ('20160925115113784220093926694740', '20160925115038436351537925433242', '20160824002', '20160925', '115038', 'liuscc', '20160925', '115113');
insert into BF_TASK_LOG (log_id, key_id, task_id, task_date, task_time, deal_oper, deal_date, deal_time)
values ('20160925115653703310298426450174', '20160925115113803282495839794525', '20160824003', '20160925', '115113', 'liuscc', '20160925', '115653');
insert into BF_TASK_LOG (log_id, key_id, task_id, task_date, task_time, deal_oper, deal_date, deal_time)
values ('20160926161615499657105159804553', '20160926161607574464972883358507', '20160824002', '20160926', '161607', 'liuscc', '20160926', '161615');
insert into BF_TASK_LOG (log_id, key_id, task_id, task_date, task_time, deal_oper, deal_date, deal_time)
values ('20160926161940570384348713331778', '20160926161615589100264594221860', '20160824003', '20160926', '161615', 'liuscc', '20160926', '161940');
commit;
prompt 10 records loaded
prompt Loading BF_TASK_OWNER...
prompt Table is empty
prompt Loading BF_TASK_PARAM...
insert into BF_TASK_PARAM (task_id, task_name, menu_id, menu_name, rule_id, detail_menu_id)
values ('20160824001', '�����û�', 900000043, '�����û�', '20161230103440362238742336127230', '900000046');
insert into BF_TASK_PARAM (task_id, task_name, menu_id, menu_name, rule_id, detail_menu_id)
values ('20160824002', '�޸��û�', 900000043, '�޸��û�', '20160924102101441432953776975318', '900000048');
insert into BF_TASK_PARAM (task_id, task_name, menu_id, menu_name, rule_id, detail_menu_id)
values ('20160824003', 'ɾ���û�', 900000042, 'ɾ���û�', '20160924111338259384056882881363', '900000049');
commit;
prompt 3 records loaded
prompt Loading BF_TASK_REGEX...
insert into BF_TASK_REGEX (rule_id, task_id, oper_flag, role_flag, org_flag, detail_flag, detail_menu_id, inst_oper, inst_date, inst_time, modi_oper, modi_date, modi_time)
values ('20161230103440362238742336127230', '20160824001', '0', '0', '0', '1', '900000046', 'dev', '20161230', '103440', null, null, null);
insert into BF_TASK_REGEX (rule_id, task_id, oper_flag, role_flag, org_flag, detail_flag, detail_menu_id, inst_oper, inst_date, inst_time, modi_oper, modi_date, modi_time)
values ('20160924102101441432953776975318', '20160824002', '1', '1', '0', '1', '900000048', 'liuscc', '20160924', '102101', 'liuscc', '20160926', '115328');
insert into BF_TASK_REGEX (rule_id, task_id, oper_flag, role_flag, org_flag, detail_flag, detail_menu_id, inst_oper, inst_date, inst_time, modi_oper, modi_date, modi_time)
values ('20160924111338259384056882881363', '20160824003', '1', '1', '1', '0', '900000042', 'liuscc', '20160924', '111338', 'liuscc', '20160926', '113111');
commit;
prompt 3 records loaded
prompt Loading BF_TASK_REGEX_SUB_INFO...
insert into BF_TASK_REGEX_SUB_INFO (rule_id, limit_flag, limit_key_id)
values ('20160924102101441432953776975318', 'U', 'liuscc');
insert into BF_TASK_REGEX_SUB_INFO (rule_id, limit_flag, limit_key_id)
values ('20160924102101441432953776975318', 'U', 'luowei');
insert into BF_TASK_REGEX_SUB_INFO (rule_id, limit_flag, limit_key_id)
values ('20160924102101441432953776975318', 'R', '1');
insert into BF_TASK_REGEX_SUB_INFO (rule_id, limit_flag, limit_key_id)
values ('20160924102101441432953776975318', 'R', '2');
insert into BF_TASK_REGEX_SUB_INFO (rule_id, limit_flag, limit_key_id)
values ('20160924111338259384056882881363', 'R', '6');
commit;
prompt 5 records loaded
prompt Loading BF_USER...
insert into BF_USER (user_id, user_name, nick_name, user_pwd, user_status, org_id, cert_type, cert_no, mobile_phone, telephone, email, limit_ip, online_session_num, lock_flag, lock_date, lock_time, login_num, last_login_ip, last_login_date, last_login_time, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper, modi_pwd_date, modi_pwd_time)
values ('test1001', 'Oracle����', '����', '580fd56c36b6dba94e9858b3aeeb2fe9', '1', '1001', '1', null, '1311111111', '3289052365', 'test@139.com', null, 0, '0', null, null, 0, null, null, null, '20161230', '104751', 'oulinhai', '20161230', '104827', 'oulinhai', null, null);
insert into BF_USER (user_id, user_name, nick_name, user_pwd, user_status, org_id, cert_type, cert_no, mobile_phone, telephone, email, limit_ip, online_session_num, lock_flag, lock_date, lock_time, login_num, last_login_ip, last_login_date, last_login_time, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper, modi_pwd_date, modi_pwd_time)
values ('342424', '������', null, '72a37c352d3742086d859844c1546f95', '1', '1234', '1', null, null, null, null, null, 0, '0', null, null, 0, null, null, null, '20160513', '180359', 'dev', null, null, null, null, null);
insert into BF_USER (user_id, user_name, nick_name, user_pwd, user_status, org_id, cert_type, cert_no, mobile_phone, telephone, email, limit_ip, online_session_num, lock_flag, lock_date, lock_time, login_num, last_login_ip, last_login_date, last_login_time, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper, modi_pwd_date, modi_pwd_time)
values ('admin', '����Ա', null, '21a2a476ca0ce8584b3d06854c8769c4', '1', 'A0000', '3', '362330201602201578', '13589701213', '0755-82503294', 'admin-beneform4j@formssi.com', null, 0, '0', null, null, 2, '22.145.31.103', '20160411', '172507', null, null, null, null, null, null, null, null);
insert into BF_USER (user_id, user_name, nick_name, user_pwd, user_status, org_id, cert_type, cert_no, mobile_phone, telephone, email, limit_ip, online_session_num, lock_flag, lock_date, lock_time, login_num, last_login_ip, last_login_date, last_login_time, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper, modi_pwd_date, modi_pwd_time)
values ('dev', '������', null, '025b8c4b087136d0c8e8a5d93dd5f6a5', '1', 'A0000', '1', '362330201602201152', '13589703456', '0755-82503292', 'dev-beneform4j@formssi.com', null, 152, '0', null, null, 0, '192.168.22.200', '20161230', '172159', null, null, null, null, null, null, '20161230', '152027');
insert into BF_USER (user_id, user_name, nick_name, user_pwd, user_status, org_id, cert_type, cert_no, mobile_phone, telephone, email, limit_ip, online_session_num, lock_flag, lock_date, lock_time, login_num, last_login_ip, last_login_date, last_login_time, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper, modi_pwd_date, modi_pwd_time)
values ('kingdom', 'kingdom', 'kingdom', '0c31ca1ddec254158ca9d43462431b95', '0', '123', '1', null, null, null, null, null, 0, '0', null, null, 0, '22.145.31.103', '20160614', '155141', '20160519', '202924', 'dev', null, null, null, null, null);
insert into BF_USER (user_id, user_name, nick_name, user_pwd, user_status, org_id, cert_type, cert_no, mobile_phone, telephone, email, limit_ip, online_session_num, lock_flag, lock_date, lock_time, login_num, last_login_ip, last_login_date, last_login_time, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper, modi_pwd_date, modi_pwd_time)
values ('linjisong', '�ּ���', '˫Ŀ', '353eb283212fb174a7a7b29baaa74437', '1', '1001', '1', null, null, null, null, null, 9, '0', null, null, 0, '169.254.223.194', '20161125', '104133', '20160913', '164830', 'dev', null, null, null, null, null);
insert into BF_USER (user_id, user_name, nick_name, user_pwd, user_status, org_id, cert_type, cert_no, mobile_phone, telephone, email, limit_ip, online_session_num, lock_flag, lock_date, lock_time, login_num, last_login_ip, last_login_date, last_login_time, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper, modi_pwd_date, modi_pwd_time)
values ('liuscc', '������', '��', '64280047ac4e9d885b74254ebd07f404', '1', '17847', '1', null, null, null, null, null, 153, '0', null, null, 0, '22.145.27.36', '20160927', '150316', '20160818', '143847', 'lscongz', '20160818', '143956', 'lscongz', null, null);
insert into BF_USER (user_id, user_name, nick_name, user_pwd, user_status, org_id, cert_type, cert_no, mobile_phone, telephone, email, limit_ip, online_session_num, lock_flag, lock_date, lock_time, login_num, last_login_ip, last_login_date, last_login_time, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper, modi_pwd_date, modi_pwd_time)
values ('luowei', '����', null, '0f33a73426cd4ef6a417b3d004224db3', '1', '17847', '1', null, null, null, null, null, 43, '0', null, null, 0, '22.145.31.29', '20161024', '092457', '20160519', '142013', 'dev', null, null, null, null, null);
insert into BF_USER (user_id, user_name, nick_name, user_pwd, user_status, org_id, cert_type, cert_no, mobile_phone, telephone, email, limit_ip, online_session_num, lock_flag, lock_date, lock_time, login_num, last_login_ip, last_login_date, last_login_time, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper, modi_pwd_date, modi_pwd_time)
values ('oulinhai', 'ŷ�ֺ�', '��', 'b5a4f964b6b40bc030dc526a95038dff', '1', '1001', '1', null, '13203221213', '0755-22331399', 'admin03@forms.com', null, 20, '0', null, null, 0, '192.168.22.83', '20161230', '154515', '20160706', '142612', 'dev', '20160706', '142835', 'dev', null, null);
insert into BF_USER (user_id, user_name, nick_name, user_pwd, user_status, org_id, cert_type, cert_no, mobile_phone, telephone, email, limit_ip, online_session_num, lock_flag, lock_date, lock_time, login_num, last_login_ip, last_login_date, last_login_time, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper, modi_pwd_date, modi_pwd_time)
values ('test', '����Ա', null, '6a42684f2be600e858f8ebb0a0111449', '1', 'A0000', '2', '362330201602201345', '13589704647', '0755-82503293', 'test-beneform4j@formssi.com', null, 2, '0', null, null, 0, '169.254.87.46', '20160920', '185101', null, null, null, null, null, null, null, null);
insert into BF_USER (user_id, user_name, nick_name, user_pwd, user_status, org_id, cert_type, cert_no, mobile_phone, telephone, email, limit_ip, online_session_num, lock_flag, lock_date, lock_time, login_num, last_login_ip, last_login_date, last_login_time, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper, modi_pwd_date, modi_pwd_time)
values ('xuguanping', '���ƽ', null, 'a1852001cb9554a597640ca30c900319', '1', '17836', '1', null, null, null, null, null, 6, '0', null, null, 0, '22.145.27.44', '20161017', '083442', '20160927', '111914', 'oulinhai', '20160927', '111951', 'oulinhai', null, null);
insert into BF_USER (user_id, user_name, nick_name, user_pwd, user_status, org_id, cert_type, cert_no, mobile_phone, telephone, email, limit_ip, online_session_num, lock_flag, lock_date, lock_time, login_num, last_login_ip, last_login_date, last_login_time, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper, modi_pwd_date, modi_pwd_time)
values ('zhangjj', '�żѿ�', '�ѿ�', '745faf1c5d5ecef293159c619fb53131', '1', '1001', '1', null, null, null, null, null, 72, '0', null, null, 0, '22.145.27.121', '20161024', '170833', '20160818', '102258', 'dev', '20161014', '112957', 'dev', '20161013', '113727');
commit;
prompt 12 records loaded
prompt Loading BF_USER_CFG...
insert into BF_USER_CFG (user_id, param_code, param_value)
values ('dev', 'USER_DEFAULT_ROLE', '1');
insert into BF_USER_CFG (user_id, param_code, param_value)
values ('dev', 'USER_LAYOUT', 'DEFAULT');
insert into BF_USER_CFG (user_id, param_code, param_value)
values ('dev', 'USER_LOCALE', 'zh_CN');
insert into BF_USER_CFG (user_id, param_code, param_value)
values ('dev', 'USER_THEME', 'boc-red');
insert into BF_USER_CFG (user_id, param_code, param_value)
values ('kingdom', 'USER_LOCALE', 'en_US');
insert into BF_USER_CFG (user_id, param_code, param_value)
values ('liuscc', 'USER_LOCALE', 'zh_CN');
insert into BF_USER_CFG (user_id, param_code, param_value)
values ('luowei', 'USER_DEFAULT_ROLE', '-1');
insert into BF_USER_CFG (user_id, param_code, param_value)
values ('luowei', 'USER_LAYOUT', 'DEFAULT');
insert into BF_USER_CFG (user_id, param_code, param_value)
values ('luowei', 'USER_LOCALE', 'zh_CN');
insert into BF_USER_CFG (user_id, param_code, param_value)
values ('luowei', 'USER_THEME', 'boc-red');
insert into BF_USER_CFG (user_id, param_code, param_value)
values ('oulinhai', 'USER_LOCALE', 'zh_CN');
insert into BF_USER_CFG (user_id, param_code, param_value)
values ('xuguanping', 'USER_LOCALE', 'zh_CN');
commit;
prompt 12 records loaded
prompt Loading BF_USER_ROLE...
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('test1001', 5, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('test1001', 6, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('342424', 1, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('342424', 2, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('admin', 1, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('admin', 2, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('dev', 1, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('dev', 2, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('dev', 3, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('dev', 4, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('dev', 5, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('dev', 6, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('dev', 8, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('kingdom', 1, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('kingdom', 2, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('kingdom', 3, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('kingdom', 4, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('kingdom', 5, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('kingdom', 6, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('kingdom', 8, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('linjisong', 1, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('liuscc', 1, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('luowei', 1, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('luowei', 2, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('luowei', 3, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('luowei', 4, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('luowei', 5, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('luowei', 6, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('luowei', 8, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('oulinhai', 1, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('test', 8, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('xuguanping', 1, null, null, null, null, null, null);
insert into BF_USER_ROLE (user_id, role_id, inst_date, inst_time, inst_oper, modi_date, modi_time, modi_oper)
values ('zhangjj', 1, null, null, null, null, null, null);
commit;
prompt 33 records loaded
set feedback on
set define on
prompt Done.
