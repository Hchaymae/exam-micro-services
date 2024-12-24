create table if not exists category (
    id serial primary key,
    name varchar(255) not null,
    description varchar(255) not null
);

create table if not exists product (
    id serial primary key,
    name varchar(255) not null,
    description varchar(255) not null,
    available_quantity double precision not null,
    price numeric(38,2),
    category_id integer not null
        constraint product_category_id_fk references category
);

create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;
