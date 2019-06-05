CREATE SEQUENCE amateurs_sqs;

CREATE TABLE if not exists Amateurs
(
  amateur_id         BIGINT PRIMARY KEY DEFAULT NEXTVAL('amateurs_sqs'),
  amateur_expirience INT CHECK (amateur_expirience BETWEEN 0 AND 100) default 0 NOT NULL
);