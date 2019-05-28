CREATE SEQUENCE supplies_sqs;

CREATE TABLE if not exists Supplies
(
  supply_id   BIGINT PRIMARY KEY DEFAULT NEXTVAL('supplies_sqs'),
  supply_cost NUMERIC(10, 2)                                                             NOT NULL,
  supplier_id int references suppliers (supplier_id) on delete cascade on update cascade not null
);