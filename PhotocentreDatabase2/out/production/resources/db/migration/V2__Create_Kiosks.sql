CREATE SEQUENCE kiosks_sqs;

CREATE TABLE Kiosks
(
  kiosk_id                BIGINT PRIMARY KEY DEFAULT NEXTVAL('kiosks_sqs'),
  kiosk_address           VARCHAR(256)                                                                         NOT NULL,
  kiosk_amount_of_workers INT                                                                                  NOT NULL,
  branch_office_id        INT REFERENCES branch_offices (branch_office_id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL
);