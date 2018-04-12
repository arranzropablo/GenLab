create table answers
(
	id int auto_increment
		primary key,
	pregunta_id int not null,
	texto varchar(100) not null,
	correcta tinyint(1) null
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
	link varchar(400) null
)
engine=InnoDB
;

create index book_section_idx
	on book (sectionid)
;

create table problem
(
	id int auto_increment
		primary key,
	sectionid int not null,
	nombre varchar(50) null,
	contenido varchar(5000) null
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
	texto varchar(100) not null
)
engine=InnoDB
;

create index `question-test_idx`
	on questions (test_id)
;

alter table answers
	add constraint answers_questions_id_fk
		foreign key (pregunta_id) references questions (id)
			on update cascade on delete cascade
;

create table sections
(
	id int not null
		primary key,
	nombre varchar(50) null,
	priority int null
)
engine=InnoDB
;

alter table book
	add constraint book_section
		foreign key (sectionid) references sections (id)
			on update cascade on delete cascade
;

alter table problem
	add constraint problems_sections_id_fk
		foreign key (sectionid) references sections (id)
			on update cascade on delete cascade
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

alter table questions
	add constraint `question-test`
		foreign key (test_id) references tests (id)
			on update cascade on delete cascade
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
	role varchar(100) default 'USER' null,
	feedback varchar(25000) null
)
engine=InnoDB
;

--INSERTS SECTIONS

INSERT INTO genlab.sections (id, nombre, priority) VALUES (0, 'twoloci', 1);
INSERT INTO genlab.sections (id, nombre, priority) VALUES (1, 'onelocus', 2);
INSERT INTO genlab.sections (id, nombre, priority) VALUES (2, 'linkage', 3);
INSERT INTO genlab.sections (id, nombre, priority) VALUES (3, 'epistasia', 4);
INSERT INTO genlab.sections (id, nombre, priority) VALUES (4, 'polyhybrid', 5);
INSERT INTO genlab.users (email, password, role, feedback) VALUES ('ruben', '$2a$10$R8mTPv2.JJilxtv6h7/Jj.VqVRufigy4jIPQMX4cLSEACjXF8UOnG', 'ADMIN', null);