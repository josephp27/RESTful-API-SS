create table contact
(
   id integer not null,
   name varchar(255) not null,
   email varchar(255) not null,
   number_work varchar(15) not null,
   number_personal varchar(15) not null,
   company varchar(255) not null,
   primary key(id)
);

--create table users
--(
--   id integer not null,
--   name varchar(255) not null,
--   company varchar(255) not null,
--   profile_image varchar(255) not null,
--   email varchar(255) not null,
--   birthday DATE() not null,
--   phone_work varchar(15) not null,
--   phone_personal varchar(15) not null,
--   address varchar(255) not null,
--   primary key(id)
--);

--name, company, profile image, email,
--  birthdate, phone number (work, personal) and address.