CREATE SEQUENCE suppliers_sqs;

CREATE TABLE if not exists Suppliers
(
  supplier_id             BIGINT PRIMARY KEY DEFAULT NEXTVAL('suppliers_sqs'),
  supplier_name           VARCHAR(256) NOT NULL,
  supplier_specialization VARCHAR(256) NOT NULL
);