create table category
(
    id               bigint unsigned auto_increment
        primary key,
    title            varchar(127)                          not null,
    slug             varchar(127)                          not null,
    meta_description varchar(127)                          not null,
    meta_keyword     varchar(127)                          not null,
    created_at       timestamp default current_timestamp() not null on update current_timestamp(),
    updated_at       timestamp                             null,
    deleted_at       timestamp                             null,
    constraint slug
        unique (slug)
);

create table photo
(
    id         bigint unsigned auto_increment
        primary key,
    path       varchar(128)                          not null,
    created_at timestamp default current_timestamp() not null on update current_timestamp(),
    updated_at timestamp                             null,
    deleted_at timestamp                             null
);

create table user
(
    id                 bigint unsigned auto_increment
        primary key,
    name               varchar(31)                                  not null,
    email              varchar(127)                                 not null,
    emiail_verified_at timestamp                                    null,
    password           varchar(60)                                  null,
    status             enum ('activate', 'deactivate', 'block', '') null,
    photo_id           bigint unsigned                              not null,
    remember_token     varchar(100)                                 null,
    create_at          timestamp default '0000-00-00 00:00:00'      not null,
    updated_at         timestamp                                    null,
    deleted_at         timestamp                                    null,
    constraint email
        unique (email),
    constraint photo_id
        unique (photo_id)
);

create table post
(
    id           bigint unsigned auto_increment
        primary key,
    title        varchar(255)                    not null,
    slug         varchar(255)                    not null,
    description  varchar(255)                    not null,
    meta_keyword varchar(255)                    not null,
    status       enum ('activate', 'deActivate') not null,
    user_id      bigint unsigned                 null,
    photo_id     bigint unsigned                 null,
    category_id  bigint unsigned                 null,
    created_at   timestamp                       null,
    updated_at   timestamp                       null,
    deleted_at   timestamp                       null,
    constraint post_category_id_fk
        foreign key (category_id) references category (id),
    constraint post_photo_id_fk
        foreign key (photo_id) references photo (id),
    constraint post_user_id_fk
        foreign key (user_id) references user (id)
);

