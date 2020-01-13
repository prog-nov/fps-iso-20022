use test;

drop table if exists `user1`;
create table `user1`(
`id` int primary key auto_increment,
`name` varchar(50) default null,
`sex` int(20) not null default 0
)engine = MYISAM default charset = utf8;


drop table if exists `user2`; 
create table `user2`(
`id` int primary key auto_increment,
`name` varchar(50) default null,
`sex` int(20) not null default 0
)engine = MYISAM default charset = utf8;

drop table if exists `alluser1`;
create table `alluser1`(
`id` int primary key auto_increment,
`name` varchar(50) default null,
`sex` int(20) not null default 0
)engine = merge union=(user1,user2) insert_method = last default charset=utf8;

insert into alluser1(name,sex) values('1',3);

MERGE INTO test T1
USING (SELECT OWNER , OBJECT_NAME , MAX(ID) ID FROM T GROUP BY OWNER, OBJECT_NAME) T
ON (T.OWNER = T1.OWNER AND T.OBJECT_NAME = T1.TABLE_NAME)
WHEN MATCHED THEN UPDATE SET T1.ID = T.ID
WHEN NOT MATCHED THEN INSERT VALUES (T.ID, T.OWNER, T.OBJECT_NAME);

