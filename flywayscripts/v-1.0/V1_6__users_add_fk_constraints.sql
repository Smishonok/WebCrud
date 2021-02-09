alter table users
    add constraint account_id_fk foreign key (account_id) references accounts (id) on update cascade