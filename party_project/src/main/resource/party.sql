/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/2/26 23:10:14                           */
/*==============================================================*/


drop table if exists activist;

drop table if exists applicant;

drop table if exists class;

drop table if exists developer;

drop table if exists inspector;

drop table if exists mark;

drop table if exists party_member;

drop table if exists student;

/*==============================================================*/
/* Table: activist                                              */
/*==============================================================*/
create table activist
(
   num                  varchar(10) not null,
   activist_occupation  varchar(10),
   identifying_activist date not null,
   directing_score      decimal(4,2),
   composite_ranking    tinyint,
   agree                tinyint,
   disagree             tinyint,
   abstain              tinyint,
   thinking_report      tinyint,
   need_num             tinyint,
   attend_num           tinyint,
   superintendent1      varchar(10),
   superintendent2      varchar(10),
   cultivate_contacts   varchar(10),
   which_volume         tinyint,
   inspect_time         varchar(10),
   primary key (num)
);

/*==============================================================*/
/* Table: applicant                                             */
/*==============================================================*/
create table applicant
(
   num                  varchar(10) not null,
   time_of_application  date not null,
   speaker              varchar(10) not null,
   talk_time            date not null,
   applicant_occupation varchar(20) not null,
   is_adult             tinyint unsigned not null,
   primary key (num)
);

/*==============================================================*/
/* Table: class                                                 */
/*==============================================================*/
create table class
(
   class_num            varchar(10) not null,
   party_members        tinyint,
   class_members        tinyint,
   primary key (class_num)
);

/*==============================================================*/
/* Table: developer                                             */
/*==============================================================*/
create table developer
(
   num                  varchar(10) not null,
   identifying_developer date not null,
   profession           varchar(10) not null,
   dad_name             varchar(10),
   dad_identity         varchar(20),
   dad_status           varchar(10),
   mom_name             varchar(10),
   mom_identity         varchar(20),
   mom_status           varchar(10),
   primary key (num)
);

/*==============================================================*/
/* Table: inspector                                             */
/*==============================================================*/
create table inspector
(
   num                  varchar(10) not null,
   party_score          decimal(3,1),
   training_time        date,
   experience           varchar(10),
   advantage            varchar(10),
   disadvantage         varchar(10),
   award                varchar(10),
   competitive_score    decimal(3,1),
   inspector_occupation varchar(20),
   primary key (num)
);

/*==============================================================*/
/* Table: mark                                                  */
/*==============================================================*/
create table mark
(
   num                  varchar(10) not null,
   discipline_inspection varchar(10),
   composite_score      decimal(4,2) not null,
   composite_ranking    tinyint not null,
   academic_score       decimal(4,2) not null,
   academic_ranking     tinyint not null,
   semester             varchar(10) not null,
   primary key (num, semester)
);

/*==============================================================*/
/* Table: party_member                                          */
/*==============================================================*/
create table party_member
(
   num                  varchar(10) not null,
   application_num      varchar(10),
   application_time     date,
   enter_time           date,
   positive_time        date,
   is_official          tinyint not null,
   primary key (num)
);

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   num                  varchar(10) not null,
   class                varchar(10) not null,
   name                 varchar(32) not null,
   sex                  varchar(10) not null,
   nation               varchar(10) not null,
   native               varchar(10) not null,
   telephone            varchar(15) not null,
   identity_card        varchar(20) not null,
   birth                date not null,
   degree_of_education  varchar(10) not null,
   palitics_status      varchar(10) not null,
   stage_of_development varchar(20) not null,
   admission_time       date not null,
   dormitory            varchar(10) not null,
   primary key (num)
);

alter table activist add constraint FK_Reference_2 foreign key (num)
      references student (num) on delete restrict on update restrict;

alter table applicant add constraint FK_Reference_1 foreign key (num)
      references student (num) on delete restrict on update restrict;

alter table developer add constraint FK_Reference_6 foreign key (num)
      references student (num) on delete restrict on update restrict;

alter table inspector add constraint FK_Reference_4 foreign key (num)
      references student (num) on delete restrict on update restrict;

alter table mark add constraint FK_Reference_5 foreign key (num)
      references student (num) on delete restrict on update restrict;

alter table party_member add constraint FK_Reference_7 foreign key (num)
      references student (num) on delete restrict on update restrict;

alter table student add constraint FK_Reference_3 foreign key (class)
      references class (class_num) on delete restrict on update restrict;

