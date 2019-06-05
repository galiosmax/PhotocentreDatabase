CREATE SEQUENCE services_sqs;

CREATE TABLE if not exists Services
(
  service_id       BIGINT PRIMARY KEY DEFAULT NEXTVAL('services_sqs'),
  service_name     VARCHAR(256)                                                                             NOT NULL,
  service_cost     NUMERIC(10, 2) check (service_cost >= 0)                                                 NOT NULL,
  branch_office_id INT REFERENCES branch_offices (branch_office_id) ON DELETE CASCADE ON UPDATE CASCADE     NOT NULL
);