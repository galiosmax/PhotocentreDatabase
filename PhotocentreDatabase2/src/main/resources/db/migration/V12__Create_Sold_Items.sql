CREATE SEQUENCE sold_items_sqs;

create table if not exists Sold_Items
(
  sold_item_id     int primary key DEFAULT NEXTVAL('sold_items_sqs'),
  sold_item_name   varchar(256)                                                                         not null,
  sold_item_cost   numeric(10, 2) check (sold_item_cost >= 0),
  sold_item_date   date default current_date                                                            not null,
  branch_office_id int references branch_offices (branch_office_id) on delete cascade on UPDATE cascade not null
);
