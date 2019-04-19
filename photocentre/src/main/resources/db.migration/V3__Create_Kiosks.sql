CREATE SEQUENCE kiosks_sqs;

CREATE TABLE Kiosks
(
    kiosk_id                BIGINT PRIMARY KEY DEFAULT NEXTVAL('kiosks_sqs'),
    kiosk_address           VARCHAR(256) NOT NULL,
    kiosk_amount_of_workers INT NOT NULL
)