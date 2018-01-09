create table answers
(
	id int auto_increment
		primary key,
	pregunta_id int not null,
	texto varchar(100) not null,
	correcta tinyint(1) null,
	constraint answers_questions_id_fk
		foreign key (pregunta_id) references questions (id)
			on update cascade on delete cascade
)
engine=InnoDB
;

create index answers_questions_id_fk
	on answers (pregunta_id)
;

create table book
(
	id int auto_increment
		primary key,
	sectionid int not null,
	name varchar(250) not null,
	author varchar(200) null,
	editorial varchar(200) null,
	isbn varchar(150) null,
	link varchar(400) null,
	constraint book_section
		foreign key (sectionid) references sections (id)
			on update cascade on delete cascade
)
engine=InnoDB
;

create index book_section_idx
	on book (sectionid)
;

create table calculationtool
(
	id int not null
		primary key,
	sectionid int not null,
	nombre varchar(100) null,
	constraint calculationtool_sections_id_fk
		foreign key (sectionid) references sections (id)
			on update cascade on delete cascade
)
engine=InnoDB
;

create index calculationtool_sections_id_fk
	on calculationtool (sectionid)
;

create table problem
(
	id int auto_increment
		primary key,
	sectionid int not null,
	nombre varchar(50) null,
	contenido varchar(5000) null,
	constraint problems_sections_id_fk
		foreign key (sectionid) references sections (id)
			on update cascade on delete cascade
)
engine=InnoDB
;

create index problems_sections_id_fk
	on problem (sectionid)
;

create table questions
(
	id int auto_increment
		primary key,
	test_id int not null,
	texto varchar(100) not null,
	constraint `question-test`
		foreign key (test_id) references tests (id)
			on update cascade on delete cascade
)
engine=InnoDB
;

create index `question-test_idx`
	on questions (test_id)
;

create table sections
(
	id int not null
		primary key,
	nombre varchar(50) null
)
engine=InnoDB
;

create table tests
(
	id int auto_increment
		primary key,
	sectionid int not null,
	titulo varchar(100) null,
	constraint idfbk_2
		foreign key (sectionid) references sections (id)
			on update cascade on delete cascade
)
engine=InnoDB
;

create index idfbk_2_idx
	on tests (sectionid)
;

create table theory
(
	id int auto_increment
		primary key,
	sectionid int not null,
	titulo varchar(100) null,
	contenido varchar(65000) null,
	constraint idfbk_1
		foreign key (sectionid) references sections (id)
			on update cascade on delete cascade
)
engine=InnoDB
;

create index idfbk_1_idx
	on theory (sectionid)
;

create table users
(
	email varchar(100) not null
		primary key,
	password varchar(100) not null,
	role varchar(100) default 'role_student' null
)
engine=InnoDB
;