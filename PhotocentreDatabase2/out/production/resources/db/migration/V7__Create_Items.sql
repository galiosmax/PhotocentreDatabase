CREATE SEQUENCE items_sqs;

CREATE TABLE if not exists Items
(
  item_id          BIGINT PRIMARY KEY DEFAULT NEXTVAL('items_sqs'),
  item_name        VARCHAR(256)                                                                         NOT NULL,
  item_cost        NUMERIC(10, 2)                                                                       NOT NULL,
  item_date        DATE                                                                                 NOT NULL,
  item_type        VARCHAR(256)                                                                         NOT NULL,
  branch_office_id INT REFERENCES branch_offices (branch_office_id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
  supply_id        int references supplies (supply_id) on delete cascade on update cascade
);