create sequence professionals_sqs;

create table if not exists Professionals
(
  professional_id       bigint primary key default nextval('professionals_sqs'),
  professional_discount int check (professional_discount between 0 and 100)  default 0                                                               not null,
  branch_office_id      int references branch_offices (branch_office_id) on delete cascade on update cascade                                         not null
);
