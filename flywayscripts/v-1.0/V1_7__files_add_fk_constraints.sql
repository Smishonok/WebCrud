alter table files
    add constraint files_user_id_fk foreign key (user_id) references users (id) on DELETE cascade