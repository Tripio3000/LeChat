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