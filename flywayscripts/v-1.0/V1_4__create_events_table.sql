create table if not exists events(
    id bigint primary key,
    date_time bigint not null,
    file_id bigint not null,
    user_id bigint not null,
    event_type varchar(50),

    unique (date_time,user_id),
    unique (date_time,file_id)
)