alter table account
    add constraint user_id_fk foreign key (user_id) references users (id) on delete cascade;


