CREATE SEQUENCE orders_sqs;

CREATE TABLE if not exists Orders
(
  order_id               BIGINT PRIMARY KEY DEFAULT NEXTVAL('orders_sqs'),
  order_urgency          VARCHAR(256)                                                                         NOT NULL,
  order_amount_of_photos INT                                                                                  NOT NULL,
  order_discount         INT,
  order_cost             NUMERIC(10, 2)                                                                       NOT NULL,
  order_date             DATE                                                                                 NOT NULL,
  order_completion_date  DATE,
  order_type             VARCHAR(256)                                                                         NOT NULL,
  branch_office_id       INT REFERENCES branch_offices (branch_office_id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL
);