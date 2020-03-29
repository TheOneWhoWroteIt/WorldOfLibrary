
create database worldoflibrary;

create schema worldoflibrary;


/*block create tables for users*/

create table worldoflibrary.reader(
  id bigserial primary key,
  version bigserial not null,
  first_name character varying(150) not null,
  last_name character varying(150) not null,
  birthday date not null,
  email character varying(150) not null unique,
  phone_number character varying (100) not null unique,
  login character varying (200) not null unique,
  password character varying(2000) not null,
  city character varying(100) not null,
  street character varying(100) not null,
  home_number integer not null,
  room_number integer not null,
  black_list boolean not null,
  role_id bigserial not null

);

create table worldoflibrary.employee(
  id bigserial primary key,
  version bigserial not null,
  first_name character varying(150) not null,
  last_name character varying(150) not null,
  birthday date not null,
  email character varying(150) not null unique,
  phone_number character varying (100) not null unique,
  login character varying (200) not null unique,
  password character varying(2000) not null,
  city character varying(100) not null,
  street character varying(100) not null,
  home_number integer not null,
  room_number integer not null,
  position character varying(200) not null ,
  start_work date not null,
  end_work date,
  photo_path character varying(255) default '../img/app_img/no foto.jpg' not null,
  role_id bigserial not null
);

create table worldoflibrary.role(
  id bigserial primary key,
  version bigserial not null,
  name character varying(50) not null unique
);


/*block create tables for books*/


create table worldoflibrary.author(
  id bigserial primary key,
  version bigserial not null,
  first_name character varying(100) not null,
  last_name character varying(100) not null,
  author_description text not null

);

create table worldoflibrary.book(
  id bigserial primary key,
  version bigserial not null,
  title character varying(255) not null,
  isbn character varying(255) not null unique,
  year integer not null,
  book_description text not null,
  image_path character varying(255) default '../img/app_img/cover_book.jpg' not null,
  page_count character varying(20) not null,
  genre character varying(200) not null,
  published_name character varying(200) not null,
  print_edition_count integer not null


);

CREATE TABLE worldoflibrary.book_author (
  book_id bigserial,
  author_id bigserial,
  PRIMARY KEY (author_id, book_id)

);


/*block create tables for lists*/

create table worldoflibrary.order_reader_list(
  id bigserial primary key,
  version bigserial not null,
  start_order date not null,
  valid_date_order date not null,
  end_order date,
  order_expired boolean not null,
  request_from_reader_id bigserial not null,
  foreign key (request_from_reader_id) references worldoflibrary.request_from_reader_list (id)

);

create table worldoflibrary.request_from_reader_list(
  id bigserial primary key,
  version bigserial not null,
  start_request date not null,
  valid_date_request date not null,
  reader_id bigserial not null,
  foreign key (reader_id) references worldoflibrary.reader(id)
  );

CREATE TABLE worldoflibrary.request_books (
  book_id bigserial,
  request_id bigserial,
  PRIMARY KEY (request_id, book_id)

);


/*insert into*/

/*insert role*/

insert into worldoflibrary.role(version, name)
values (0, 'READER');

insert into worldoflibrary.role(version, name)
values (0, 'LIBRA');

insert into worldoflibrary.role(version, name)
values (0, 'ADMIN');



