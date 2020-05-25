create table task (
task_id bigint NOT NULL AUTO_INCREMENT primary key,
description varchar(255) not null,
priority int,
active boolean not null);