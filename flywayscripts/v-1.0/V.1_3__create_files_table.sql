create table if not exists files(
    id bigint primary key,
    name varchar(50) not null,
    user_id bigint not null ,
    file_status enum('ACTIVE','BANNED','DELETED'),
    file_path varchar(500) not null,

    unique (file_path),
    unique (user_id,file_path)
)