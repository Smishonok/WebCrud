create table if not exists users
(
    id bigint auto_increment primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    birthday bigint not null,
    account_id bigint not null,

    unique (first_name,last_name,birthday)
)

