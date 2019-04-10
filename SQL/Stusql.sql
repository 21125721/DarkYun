//学生信息管理系统
drop table stu;

create table stu(`stucode` int auto_increment comment '学号' primary key,
				`name` varchar(20) not null comment '姓名',
				`sex` int not null comment '性别@1:男/0:女',
				`age` tinyint(3) not null comment '年龄',
				`phoneid` varchar(11) not null comment '手机号')character set utf8 collate utf8_general_ci comment="学生表";

select * from stu;
select * from stu limit 1,2;

desc stu;
alter table stu modify column `phoneid` varchar(11) not null;
show tables;


insert into stu(`name`,`sex`,`age`,`phoneid`) values('Rose',0,18,'13487564924');


CREATE PROCEDURE SelectAll() 
BEGIN
	select * from stu;
END
;















