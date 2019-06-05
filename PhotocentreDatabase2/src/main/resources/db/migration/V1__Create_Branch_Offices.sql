CREATE SEQUENCE branch_office_sqs;

CREATE TABLE Branch_Offices
(
  branch_office_id                BIGINT PRIMARY KEY DEFAULT NEXTVAL('branch_office_sqs'),
  branch_office_address           VARCHAR(256)                                            NOT NULL,
  branch_office_amount_of_workers INT CHECK (branch_office_amount_of_workers >= 0)        NOT NULL

);