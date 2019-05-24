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


create table if not exists Answer
(
    id int auto_increment
        primary key,
    title varchar(200) not null,
    isActive tinyint(1) default 1 not null
);

create table if not exists Subject
(
    id int auto_increment
        primary key,
    title varchar(200) not null
);

create table if not exists Form
(
    id int auto_increment
        primary key,
    idSubject int not null,
    title varchar(200) not null,
    isActive tinyint(1) default 1 not null,
    constraint Form_Subject_id_fk
        foreign key (idSubject) references Subject (id)
);

create table if not exists Question
(
    id int auto_increment
        primary key,
    idSubject int not null,
    title varchar(200) null,
    isActive tinyint(1) default 1 not null,
    constraint Question_Subject_id_fk
        foreign key (idSubject) references Subject (id)
);

create table if not exists AnswerQuestion
(
    idQuestion int not null,
    idAnswer int not null,
    position int default 1 not null,
    isValid tinyint(1) default 0 not null,
    primary key (idQuestion, idAnswer),
    constraint AnswerQuestion_Answer_id_fk
        foreign key (idAnswer) references Answer (id),
    constraint AnswerQuestion_Question_id_fk
        foreign key (idQuestion) references Question (id)
);

create table if not exists QuestionForm
(
    idForm int not null,
    idQuestion int not null,
    position int default 1 not null,
    primary key (idForm, idQuestion),
    constraint QuestionForm_Form_id_fk
        foreign key (idForm) references Form (id),
    constraint QuestionForm_Question_id_fk
        foreign key (idQuestion) references Question (id)
);

create table if not exists QuestionSubject
(
    idQuestion int not null,
    idSubject int not null,
    primary key (idQuestion, idSubject),
    constraint QuestionSubject_Question_id_fk
        foreign key (idQuestion) references Question (id),
    constraint QuestionSubject_Subject_id_fk
        foreign key (idSubject) references Subject (id)
);

create table if not exists Track
(
    id int auto_increment
        primary key,
    idForm int not null,
    idUser int not null,
    score int default 0 not null,
    duration timestamp,
    constraint Track_Form_id_fk
        foreign key (idForm) references Form (id),
    constraint Track_User_id_fk
        foreign key (idUser) references User (id)
);

create table if not exists Choice
(
    idTrack int not null,
    idQuestion int not null,
    choicePosition int default 1 not null,
    primary key (idTrack, idQuestion),
    constraint Choice_Question_id_fk
        foreign key (idQuestion) references Question (id),
    constraint Choice_Track_id_fk
        foreign key (idTrack) references Track (id)
);
