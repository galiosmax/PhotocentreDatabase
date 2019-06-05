CREATE SEQUENCE films_sqs;

create table if not exists Films
(
  film_id      int primary key DEFAULT NEXTVAL('films_sqs'),
  film_name    varchar(256) not null,
  sold_item_id int references sold_items (sold_item_id) on delete cascade on update cascade,
  order_id     int references orders (order_id) on delete cascade on update cascade
);