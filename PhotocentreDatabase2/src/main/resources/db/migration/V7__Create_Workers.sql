CREATE SEQUENCE workers_sqs;

CREATE TABLE if not exists Workers
(
  worker_id           BIGINT PRIMARY KEY DEFAULT NEXTVAL('workers_sqs'),
  worker_name         VARCHAR(256) NOT NULL,
  worker_area_of_work VARCHAR(256) NOT NULL,
  worker_position     VARCHAR(256) NOT NULL
);