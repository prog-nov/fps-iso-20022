-- Create table
create table BF_FUNCTION_GUIDE
(
  menu_id       INTEGER not null,
  menu_name     VARCHAR2(64) not null,
  guide_title   VARCHAR2(128),
  guide_content CLOB not null,
  inst_date     VARCHAR2(8) not null,
  inst_time     VARCHAR2(6) not null,
  inst_oper     VARCHAR2(20) not null,
  modi_date     VARCHAR2(8),
  modi_time     VARCHAR2(6),
  modi_oper     VARCHAR2(20)
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

 -- Create table
create table BF_FUNCTION_GUIDE_I18N
(
  menu_id       INTEGER not null,
  locale        VARCHAR2(64) not null,
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
