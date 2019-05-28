CREATE SEQUENCE customers_sqs;

CREATE TABLE if not exists Customers
(
  customer_id     BIGINT PRIMARY KEY DEFAULT NEXTVAL('customers_sqs'),
  customer_name   varchar(256) not null,
  professional_id int references professionals (professional_id) on delete cascade on update cascade,
  amateur_id      int references amateurs (amateur_id) on delete cascade on update cascade
);