create table if not exists generate.picture
(
    id           uuid default uuid_generate_v1() not null,
    message_id   uuid                            not null,
    content_type varchar(255),
    name         text,
    file_path    varchar(255),
    primary key (id),
    foreign key (message_id) references generate.message (id)
    );