# Host: 127.0.0.1  (Version 5.5.62)
# Date: 2020-02-02 14:52:39
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "student"
#

CREATE TABLE `student` (
  `num` varchar(10) NOT NULL,
  `name` varchar(32) NOT NULL,
  `class` varchar(15) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `nation` varchar(10) NOT NULL,
  `telephone` varchar(15) NOT NULL,
  `idNum` varchar(20) NOT NULL,
  `birthday` date NOT NULL,
  `degreeOfEducation` varchar(10) NOT NULL DEFAULT '高中',
  `paliticsStatus` varchar(10) NOT NULL,
  `stageOfDevelopment` varchar(20) NOT NULL,
  `admissionTime` date NOT NULL,
  `dormitory` varchar(20) NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "student"
#

