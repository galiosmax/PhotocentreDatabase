CREATE SEQUENCE photocentre_sqs;

CREATE TABLE Photocentre
(
    photocentre_id      BIGINT PRIMARY KEY DEFAULT NEXTVAL('photocentre_sqs'),
    photocentre_name    VARCHAR(256) NOT NULL
)