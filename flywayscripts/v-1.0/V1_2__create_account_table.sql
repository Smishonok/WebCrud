create table if not exists accounts(
    id bigint primary key,
    user_id bigint not null,
    login varchar(50) not null,
    password varchar(200) not null,
    status varchar(50),

    unique (user_id,login)
)

