CREATE SEQUENCE professionals_sqs;

CREATE TABLE if not exists Professionals
(
  professional_id       BIGINT PRIMARY KEY DEFAULT NEXTVAL('professionals_sqs'),
  professional_discount INT CHECK (professional_discount BETWEEN 0 AND 100)  default 0                                                               NOT NULL,
  branch_office_id      INT REFERENCES branch_offices (branch_office_id) ON DELETE CASCADE ON UPDATE CASCADE                                         NOT NULL
);
