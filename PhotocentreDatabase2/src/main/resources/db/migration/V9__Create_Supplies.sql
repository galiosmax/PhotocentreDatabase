CREATE SEQUENCE supplies_sqs;

CREATE TABLE if not exists Supplies
(
  supply_id              BIGINT PRIMARY KEY DEFAULT NEXTVAL('supplies_sqs'),
  supply_cost            NUMERIC(10, 2) check (supply_cost >= 0)                                                         NOT NULL,
  supply_date            date default current_date                                                                       not null,
  supply_completion_date date,
  supplier_id            int references suppliers (supplier_id) on delete cascade on update cascade                      not null
);