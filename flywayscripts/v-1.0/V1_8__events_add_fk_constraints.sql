alter table events add constraint event_user_id_fk foreign key (user_id) references users (id) on delete cascade;
alter table events add constraint event_file_id foreign key (file_id) references files (id) on delete cascade;