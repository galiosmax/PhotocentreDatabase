create sequence amateurs_sqs;

create table if not exists Amateurs
(
  amateur_id         bigint primary key default nextval('amateurs_sqs'),
  amateur_experience int check (amateur_experience between 0 and 100) default 0 not null
);