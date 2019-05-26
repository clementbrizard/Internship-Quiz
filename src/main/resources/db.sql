CREATE DATABASE  IF NOT EXISTS `db`;
USE `db`;
--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
INSERT INTO `role` VALUES (0,'ROLE_USER');
INSERT INTO `role` VALUES (1,'ROLE_ADMIN');
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) UNIQUE NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `valid` BOOLEAN DEFAULT 1,
  `firstName` varchar(255) NOT NULL,
  `secondName` varchar(255) NOT NULL,
  `mail` varchar(255) UNIQUE NOT NULL,
  `company` varchar(255) NOT NULL,
  `phone` varchar(255),
  `creationdate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_role_roleid_idx` (`role_id`),
  CONSTRAINT `fk_user_role_roleid` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table if not exists answer
(
    answer_id int auto_increment
        primary key,
    title varchar(200) not null,
    isActive tinyint(1) default 1 not null
);

create table if not exists subject
(
    subject_id int auto_increment
        primary key,
    title varchar(200) not null
);

create table if not exists form
(
    form_id int auto_increment
        primary key,
    subject_id int not null,
    title varchar(200) not null,
    isActive tinyint(1) default 1 not null,
    constraint Form_Subject_id_fk
        foreign key (subject_id) references subject (subject_id)
);

create table if not exists question
(
    question_id int auto_increment
        primary key,
    subject_id int not null,
    title varchar(200) null,
    isActive tinyint(1) default 1 not null,
    constraint Question_Subject_id_fk
        foreign key (subject_id) references subject (subject_id)
);

create table if not exists answer_question
(
    answer_question_id int auto_increment
        primary key,
    question_id int not null,
    answer_id int not null,
    position int default 1 not null,
    valid tinyint(1) default 0 not null,
    constraint AnswerQuestion_Answer_id_fk
        foreign key (answer_id) references answer (answer_id),
    constraint AnswerQuestion_Question_id_fk
        foreign key (question_id) references question (question_id)
);

create table if not exists form_question
(
    form_question_id int auto_increment
        primary key,
    form_id int not null,
    question_id int not null,
    position int default 1 not null,
    constraint QuestionForm_Form_id_fk
        foreign key (form_id) references form (form_id),
    constraint QuestionForm_Question_id_fk
        foreign key (question_id) references question (question_id)
);

create table if not exists subject_question
(
    question_id int not null,
    subject_id int not null,
    primary key (question_id, subject_id),
    constraint QuestionSubject_Question_id_fk
        foreign key (question_id) references question (question_id),
    constraint QuestionSubject_Subject_id_fk
        foreign key (subject_id) references subject (subject_id)
);

create table if not exists track
(
    track_id int auto_increment
        primary key,
    form_id int not null,
    user_id int not null,
    score int default 0 not null,
    duration timestamp,
    constraint Track_Form_id_fk
        foreign key (form_id) references form (form_id),
    constraint Track_User_id_fk
        foreign key (user_id) references user (id)
);

create table if not exists track_question
(
    track_question_id int auto_increment
        primary key,
    track_id int not null,
    question_id int not null,
    choicePosition int default 1 not null,
    constraint Choice_Question_id_fk
        foreign key (question_id) references question (question_id),
    constraint Choice_Track_id_fk
        foreign key (track_id) references track (track_id)
);
