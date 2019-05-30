CREATE DATABASE IF NOT EXISTS `db`;
USE `db`;

create table answer
(
    answer_id int auto_increment
        primary key,
    title     varchar(200)         not null,
    isActive  tinyint(1) default 1 not null
);

create table subject
(
    subject_id int auto_increment
        primary key,
    title      varchar(200) not null
);

INSERT INTO db.subject (subject_id, title)
VALUES (1, 'Java');
INSERT INTO db.subject (subject_id, title)
VALUES (2, 'C++');

create table question
(
    question_id int auto_increment
        primary key,
    subject_id  int                  null,
    title       varchar(200)         not null,
    isActive    tinyint(1) default 1 not null,
    constraint Question_Subject_id_fk
        foreign key (subject_id) references subject (subject_id)
);


create table answer_question
(
    answer_question_id int auto_increment
        primary key,
    question_id        int                  not null,
    answer_id          int                  not null,
    position           int        default 1 not null,
    isValid              tinyint(1) default 0 not null,
    constraint AnswerQuestion_Answer_id_fk
        foreign key (answer_id) references answer (answer_id),
    constraint AnswerQuestion_Question_id_fk
        foreign key (question_id) references question (question_id)
);


create table form
(
    form_id  int auto_increment
        primary key,
    title    varchar(200)         not null,
    isActive tinyint(1) default 1 not null
);

INSERT INTO db.form (form_id, title, isActive)
VALUES (1, 'Test', 1);
create table form_question
(
    form_question_id int auto_increment
        primary key,
    form_id          int           not null,
    question_id      int           not null,
    position         int default 1 not null,
    constraint QuestionForm_Form_id_fk
        foreign key (form_id) references form (form_id),
    constraint QuestionForm_Question_id_fk
        foreign key (question_id) references question (question_id)
);


create table form_subject
(
    form_id    int not null,
    subject_id int not null,
    primary key (form_id, subject_id),
    constraint fk_form_subject_form_id
        foreign key (form_id) references form (form_id)
            on update cascade on delete cascade,
    constraint fk_form_subject_subject_id
        foreign key (subject_id) references subject (subject_id)
            on update cascade on delete cascade
);

create table role
(
    id   int auto_increment
        primary key,
    name varchar(45) null
)
    charset = utf8;

INSERT INTO db.role (id, name)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO db.role (id, name)
VALUES (2, 'ROLE_USER');
INSERT INTO db.role (id, name)
VALUES (3, 'ROLE_USER');

create table subject_form
(
    subject_id int not null,
    form_id    int not null,
    primary key (subject_id, form_id),
    constraint fk_user_role_formid
        foreign key (form_id) references form (form_id)
            on update cascade on delete cascade,
    constraint fk_user_role_subjectid
        foreign key (subject_id) references subject (subject_id)
            on update cascade on delete cascade
)
    charset = utf8;

create index fk_user_subject_subjectid_idx
    on subject_form (subject_id);


create table subject_question
(
    question_id int not null,
    subject_id  int not null,
    primary key (question_id, subject_id),
    constraint QuestionSubject_Question_id_fk
        foreign key (question_id) references question (question_id),
    constraint QuestionSubject_Subject_id_fk
        foreign key (subject_id) references subject (subject_id)
);

create table user
(
    id           int auto_increment
        primary key,
    username     varchar(255)                         not null,
    password     varchar(255)                         null,
    valid        tinyint(1) default 1                 null,
    firstName    varchar(255)                         not null,
    secondName   varchar(255)                         not null,
    mail         varchar(255)                         not null,
    company      varchar(255)                         not null,
    phone        varchar(255)                         null,
    creationdate timestamp  default CURRENT_TIMESTAMP not null,
    constraint mail
        unique (mail),
    constraint username
        unique (username)
)
    charset = utf8;

INSERT INTO db.user (id, username, password, valid, firstName, secondName, mail, company, phone, creationdate)
VALUES (5, 'luc_brizard', '$2a$11$/4nviwT0cVUIDNJgeFmE5eb5mINUGtGBKTOelLzuJKi5wQcXQrKEC', 1, 'luc', 'brizard',
        'luc@gmail.com', 'fsa', '0987654321', '2019-05-26 12:17:50');
INSERT INTO db.user (id, username, password, valid, firstName, secondName, mail, company, phone, creationdate)
VALUES (13, 'cbrizard', '$2a$11$wif42eGZWdbqnej7h1ERveZa91j6Q3t8x2QpkAiaEfHaPAzooPn/u', 1, 'clementine', 'Brizard',
        'clementbrizard53@gmail.com', 'UTC', '0770976800', '2019-05-26 17:58:41');
INSERT INTO db.user (id, username, password, valid, firstName, secondName, mail, company, phone, creationdate)
VALUES (14, 'anne_laure_briz', '$2a$11$JvU6gs7KM0c3GoJxgdfXd.GxXdf/PjSXdYj.yDy2RCadADT6ibXlq', 1, 'anne-laure',
        'Brizard', 'alb@gmail.com', 'PSL', '0987654322', '2019-05-26 18:09:32');
INSERT INTO db.user (id, username, password, valid, firstName, secondName, mail, company, phone, creationdate)
VALUES (15, 'jean-brzard', '$2a$11$D1sFpoV5c5/7Cx81VKASzuG4paerokbR43yaphpgTXEqoa9GOj7hm', 1, 'jean', 'Brizard',
        'jean@gmail.com', 'Immac', '0987654321', '2019-05-26 18:15:38');
INSERT INTO db.user (id, username, password, valid, firstName, secondName, mail, company, phone, creationdate)
VALUES (16, 'marie_briz', '$2a$11$X24H5Ssq/4tR.7wrfPnzEe4hTuSdwzRGG12KFmBGjve.Fdhg02YlS', 1, 'marie', 'brizard',
        'marie@gmail.com', 'FSA', '0608315289', '2019-05-27 02:11:49');
INSERT INTO db.user (id, username, password, valid, firstName, secondName, mail, company, phone, creationdate)
VALUES (17, 'ninon_lize', '$2a$11$OB7vtSX8CvgvjlrnmGCU7eAmc2Tfax9uhPrD.JoWPKZ5/38n7X0nS', 1, 'ninon', 'lize',
        'ninon@gmail.com', 'UTC', '0987654321', '2019-05-27 02:15:00');

create table track
(
    track_id int auto_increment
        primary key,
    form_id  int                                 not null,
    user_id  int                                 not null,
    score    int       default 0                 not null,
    duration timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint Track_Form_id_fk
        foreign key (form_id) references form (form_id),
    constraint Track_User_id_fk
        foreign key (user_id) references user (id)
);


create table track_question
(
    track_question_id int auto_increment
        primary key,
    track_id          int           not null,
    question_id       int           not null,
    choicePosition    int default 1 not null,
    constraint Choice_Question_id_fk
        foreign key (question_id) references question (question_id),
    constraint Choice_Track_id_fk
        foreign key (track_id) references track (track_id)
);

create table user_role
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id),
    constraint fk_user_role_roleid
        foreign key (role_id) references role (id)
            on update cascade on delete cascade,
    constraint fk_user_role_userid
        foreign key (user_id) references user (id)
            on update cascade on delete cascade
)
    charset = utf8;

create index fk_user_role_roleid_idx
    on user_role (role_id);

INSERT INTO db.user_role (user_id, role_id)
VALUES (5, 1);
INSERT INTO db.user_role (user_id, role_id)
VALUES (5, 2);