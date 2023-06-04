create schema if not exists generate;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table if not exists generate.message
(
    id       uuid default uuid_generate_v1() not null,
    user_id  bigint                          not null,
    message  text                            not null,
    batch_size bigint                        not null,
    num_steps bigint                         not null,
    seed      bigint                         not null,
    hash     varchar(255),
    progress varchar(255),
    primary key (id)
    );

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