# Host: 127.0.0.1  (Version 5.5.62)
# Date: 2020-03-09 23:15:42
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "party_branch"
#

CREATE TABLE `party_branch` (
  `branch_name` varchar(20) NOT NULL DEFAULT '',
  `class_num` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`branch_name`,`class_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "party_branch"
#

INSERT INTO `party_branch` VALUES ('学生第三党支部','1815493');

#
# Structure for table "student_class"
#

CREATE TABLE `student_class` (
  `class_num` varchar(10) NOT NULL,
  `party_members` tinyint(4) DEFAULT NULL,
  `class_members` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`class_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "student_class"
#

INSERT INTO `student_class` VALUES ('1815493',0,49);

#
# Structure for table "student"
#

CREATE TABLE `student` (
  `num` varchar(10) NOT NULL,
  `class_num` varchar(10) NOT NULL DEFAULT '',
  `name` varchar(10) NOT NULL DEFAULT '',
  `sex` varchar(10) NOT NULL,
  `nation` varchar(10) NOT NULL,
  `native` varchar(10) NOT NULL,
  `telephone` varchar(15) NOT NULL,
  `identity_card` varchar(20) NOT NULL,
  `birth` date NOT NULL,
  `degree_of_education` varchar(10) NOT NULL,
  `palitics_status` varchar(10) NOT NULL,
  `stage_of_development` varchar(20) NOT NULL,
  `admission_time` date NOT NULL,
  `dormitory` varchar(10) NOT NULL,
  PRIMARY KEY (`num`),
  KEY `FK_Reference_3` (`class_num`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`class_num`) REFERENCES `student_class` (`class_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "student"
#

INSERT INTO `student` VALUES ('181549315','1815493','古洪铨','男','汉族','广东茂名','18899891161','440921200008030458','2000-08-03','高中','团员','入党申请人','2018-09-03','17#B616');

#
# Structure for table "activist"
#

CREATE TABLE `activist` (
  `num` varchar(10) NOT NULL,
  `activist_occupation` varchar(50) DEFAULT NULL,
  `identifying_activist` date DEFAULT NULL,
  `directing_score` decimal(4,2) DEFAULT NULL,
  `composite_ranking` tinyint(4) DEFAULT NULL,
  `agree` tinyint(4) DEFAULT NULL,
  `disagree` tinyint(4) DEFAULT NULL,
  `abstain` tinyint(4) DEFAULT NULL,
  `thinking_report` tinyint(4) DEFAULT NULL,
  `need_num` tinyint(4) DEFAULT NULL,
  `attend_num` tinyint(4) DEFAULT NULL,
  `superintendent1` varchar(10) DEFAULT NULL,
  `superintendent2` varchar(10) DEFAULT NULL,
  `cultivate_contacts` varchar(10) DEFAULT NULL,
  `which_volume` tinyint(4) DEFAULT NULL,
  `inspect_time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`num`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`num`) REFERENCES `student` (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "activist"
#


#
# Structure for table "applicant"
#

CREATE TABLE `applicant` (
  `num` varchar(10) NOT NULL,
  `time_of_application` date NOT NULL,
  `speaker` varchar(10) NOT NULL,
  `talk_time` date NOT NULL,
  `applicant_occupation` varchar(50) NOT NULL DEFAULT '',
  `is_adult` tinyint(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`num`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`num`) REFERENCES `student` (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "applicant"
#


#
# Structure for table "developer"
#

CREATE TABLE `developer` (
  `num` varchar(10) NOT NULL,
  `identifying_developer` date NOT NULL,
  `profession` varchar(20) NOT NULL DEFAULT '',
  `dad_name` varchar(10) DEFAULT NULL,
  `dad_identity` varchar(20) DEFAULT NULL,
  `dad_status` varchar(10) DEFAULT NULL,
  `mom_name` varchar(10) DEFAULT NULL,
  `mom_identity` varchar(20) DEFAULT NULL,
  `mom_status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`num`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`num`) REFERENCES `student` (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "developer"
#


#
# Structure for table "inspector"
#

CREATE TABLE `inspector` (
  `num` varchar(10) NOT NULL,
  `party_score` decimal(4,2) DEFAULT NULL,
  `training_time` date DEFAULT NULL,
  `experience` varchar(150) DEFAULT NULL,
  `advantage` varchar(100) DEFAULT NULL,
  `disadvantage` varchar(100) DEFAULT NULL,
  `award` varchar(50) DEFAULT NULL,
  `competitive_score` decimal(4,2) DEFAULT NULL,
  `inspector_occupation` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`num`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`num`) REFERENCES `student` (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "inspector"
#


#
# Structure for table "mark"
#

CREATE TABLE `mark` (
  `num` varchar(10) NOT NULL,
  `discipline_inspection` varchar(100) DEFAULT NULL,
  `composite_score` decimal(4,2) DEFAULT NULL,
  `composite_ranking` tinyint(3) DEFAULT '0',
  `academic_score` decimal(4,2) DEFAULT NULL,
  `academic_ranking` tinyint(3) DEFAULT '0',
  `semester` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`num`,`semester`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`num`) REFERENCES `student` (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "mark"
#


#
# Structure for table "party_member"
#

CREATE TABLE `party_member` (
  `num` varchar(10) NOT NULL,
  `application_num` varchar(20) DEFAULT NULL,
  `application_time` date DEFAULT NULL,
  `enter_time` date DEFAULT NULL,
  `positive_time` date DEFAULT NULL,
  `is_official` tinyint(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`num`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`num`) REFERENCES `student` (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "party_member"
#


#
# Structure for table "user"
#

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

