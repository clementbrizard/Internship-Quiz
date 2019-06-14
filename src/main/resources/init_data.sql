INSERT INTO db.subject (subject_id, title)
VALUES (1, 'Java');
INSERT INTO db.subject (subject_id, title)
VALUES (2, 'C++');



INSERT INTO db.form (form_id, title, isActive)
VALUES (1, 'Test', 1);


INSERT INTO db.role (id, name)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO db.role (id, name)
VALUES (2, 'ROLE_USER');
INSERT INTO db.role (id, name)
VALUES (3, 'ROLE_USER');



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


INSERT INTO db.user_role (user_id, role_id)
VALUES (5, 1);
INSERT INTO db.user_role (user_id, role_id)
VALUES (5, 2);

INSERT INTO db.answer (answer_id,title,isActive) VALUES (NULL, 'Oui', '1');
INSERT INTO db.answer (answer_id,title,isActive) VALUES (NULL, 'Non', '1');
INSERT INTO db.answer (answer_id,title,isActive) VALUES (NULL, 'POO', '1');
INSERT INTO db.answer (answer_id,title,isActive) VALUES (NULL, 'PO', '1');
INSERT INTO db.answer (answer_id,title,isActive) VALUES (NULL, 'Réponse Invalide', '0');

INSERT INTO db.question (question_id,subject_id,title,isActive) VALUES (NULL, '2', 'Le C++ est une langage de :', '1');
INSERT INTO db.question (question_id,subject_id,title,isActive) VALUES (NULL, '1', 'JEE est un framework Java', '1');
INSERT INTO db.question (question_id,subject_id,title,isActive) VALUES (NULL, '2', 'Le C++ utilise des pointeurs', '1');
INSERT INTO db.question (question_id,subject_id,title,isActive) VALUES (NULL, '1', 'Rails est un framework Java', '1');
INSERT INTO db.question (question_id,subject_id,title,isActive) VALUES (NULL, '1', 'Question Invalide', '0');

INSERT INTO db.answer_question (answer_question_id,question_id,answer_id,position,isValid) VALUES
(1, 4, 7, 1, 1),
(2, 4, 8, 1, 0),
(3, 3, 9, 1, 1),
(4, 3, 10, 1, 0),
(5, 5, 7, 1, 1),
(6, 5, 8, 1, 0),
(7, 6, 7, 1, 0),
(8, 6, 8, 1, 1);

INSERT INTO db.form (form_id,title,isActive) VALUES
(1, 'Test', 1),
(2, 'Quizz Java', 1),
(3, 'Quizz C++', 1),
(4, 'Quizz Invalide', 0);


INSERT INTO db.form_question (form_question_id,form_id,question_id,position) VALUES
(1, 3, 3, 1),
(2, 3, 5, 2),
(3, 4, 7, 1),
(4, 2, 4, 1),
(5, 2, 6, 2),
(6, 2, 7, 3);

INSERT INTO db.form_subject (form_id,subject_id) VALUES
(2, 1),
(3, 2);

INSERT INTO db.subject_form (subject_id, form_id) VALUES
(1, 2),
(2, 3);


INSERT INTO db.subject_question (question_id,subject_id) VALUES
(4, 1),
(6, 1),
(3, 2),
(5, 2);




