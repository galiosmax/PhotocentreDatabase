create sequence customers_sqs;

create table if not exists Customers
(
  customer_id       bigint primary key default nextval('customers_sqs'),
  customer_name     varchar(256)                                    not null,
  customer_discount int check (customer_discount between 0 and 100) not null,
  professional_id   int references professionals (professional_id) on delete cascade on update cascade,
  amateur_id        int references amateurs (amateur_id) on delete cascade on update cascade
);