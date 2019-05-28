CREATE SEQUENCE amateurs_sqs;

CREATE TABLE if not exists Amateurs
(
  amateur_id         BIGINT PRIMARY KEY DEFAULT NEXTVAL('amateurs_sqs'),
  amateur_expirience NUMERIC(10, 2) NOT NULL
);