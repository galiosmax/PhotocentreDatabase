CREATE SEQUENCE orders_sqs;

CREATE TABLE if not exists Orders
(
  order_id              BIGINT PRIMARY KEY DEFAULT NEXTVAL('orders_sqs'),
  order_urgency         VARCHAR(256)                                                                                                                NOT NULL,
  order_cost            NUMERIC(10, 2) check (order_cost >= 0)                                                                                      NOT NULL,
  order_date            DATE default current_date                                                                                                   NOT NULL,
  order_completion_date DATE,
  order_type            VARCHAR(256)                                                                                                                NOT NULL,
  branch_office_id      INT REFERENCES branch_offices (branch_office_id) ON DELETE CASCADE ON UPDATE CASCADE                                        NOT NULL,
  kiosk_id              int references kiosks (kiosk_id) on delete cascade ON UPDATE CASCADE,
  customer_id           int references customers (customer_id) on delete cascade ON UPDATE CASCADE                                                  not null
);