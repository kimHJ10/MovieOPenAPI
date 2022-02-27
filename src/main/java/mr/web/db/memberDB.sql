-- memberDB Table
create table memberDB (
	num int primary key auto_increment,
	id varchar(20) not null,
	unique key(id),
	pw varchar(20) not null,
	name varchar(30) not null,
	email varchar(50) not null
);

-- 검색
select * from memberDB;