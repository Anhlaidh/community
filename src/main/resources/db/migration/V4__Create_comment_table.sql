create table comment
(
	id Bigint auto_increment primary key ,
	parent_id BIGINT not null,
	type int not null,
	commentator int not null,
	gmt_create BIGINT not null,
	gmt_modified bigint not null,
	like_count BIGINT default 0 not null,
);

