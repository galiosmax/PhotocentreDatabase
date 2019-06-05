CREATE SEQUENCE photos_sqs;

CREATE TABLE if not exists Photos
(
  photo_id         BIGINT PRIMARY KEY DEFAULT NEXTVAL('photos_sqs'),
  photo_paper_type VARCHAR(256)                                                       NOT NULL,
  photo_format     VARCHAR(256)                                                       NOT NULL,
  film_id          int references films (film_id) on delete cascade on update cascade not null
);