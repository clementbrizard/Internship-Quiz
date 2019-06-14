CREATE DATABASE IF NOT EXISTS `db`;
USE `db`;

create table if not exists answer
(
    answer_id int auto_increment
        primary key,
    title     varchar(200)         not null,
    isActive  tinyint(1) default 1 not null
);

create table if not exists form
(
    form_id  int auto_increment
        primary key,
    title    varchar(200)         not null,
    isActive tinyint(1) default 1 not null
);

create table if not exists role
(
    id   int auto_increment
        primary key,
    name varchar(45) null
)
    charset = utf8;

create table if not exists subject
(
    subject_id int auto_increment
        primary key,
    title      varchar(200) not null
);

create table if not exists form_subject
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

create table if not exists question
(
    question_id int auto_increment
        primary key,
    subject_id  int                  null,
    title       varchar(200)         not null,
    isActive    tinyint(1) default 1 not null,
    constraint Question_Subject_id_fk
        foreign key (subject_id) references subject (subject_id)
);

create table if not exists answer_question
(
    answer_question_id int auto_increment
        primary key,
    question_id        int                  not null,
    answer_id          int                  not null,
    position           int        default 1 not null,
    isValid            tinyint(1) default 0 not null,
    constraint AnswerQuestion_Answer_id_fk
        foreign key (answer_id) references answer (answer_id),
    constraint AnswerQuestion_Question_id_fk
        foreign key (question_id) references question (question_id)
);

create table if not exists form_question
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

create table if not exists subject_form
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

create table if not exists subject_question
(
    question_id int not null,
    subject_id  int not null,
    primary key (question_id, subject_id),
    constraint QuestionSubject_Question_id_fk
        foreign key (question_id) references question (question_id),
    constraint QuestionSubject_Subject_id_fk
        foreign key (subject_id) references subject (subject_id)
);

create table if not exists user
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

create table if not exists track
(
    track_id int auto_increment
        primary key,
    form_id  int           not null,
    user_id  int           not null,
    score    int default 0 not null,
    duration varchar(30)   null,
    date     varchar(30)   null,
    constraint Track_Form_id_fk
        foreign key (form_id) references form (form_id),
    constraint Track_User_id_fk
        foreign key (user_id) references user (id)
);

create table if not exists track_question
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

create table if not exists user_role
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


