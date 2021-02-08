create table if not exists accounts(
    id bigint primary key,
    user_id bigint not null,
    login varchar(50) not null,
    passwordToken varchar(200) not null,
    status enum('CONNECTED','DISCONNECTED'),

    unique (user_id,login)
)

