create table category
(
    id               bigint unsigned auto_increment
        primary key,
    title            varchar(127)                          not null,
    slug             varchar(127)                          not null,
    meta_description text                                  not null,
    meta_keyword     text                                  not null,
    created_at       timestamp default current_timestamp() not null,
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
    created_at timestamp default current_timestamp() not null,
    updated_at timestamp                             null,
    deleted_at timestamp                             null
);

create table role
(
    id         bigint unsigned auto_increment
        primary key,
    name       varchar(60)                           not null,
    created_at timestamp default current_timestamp() not null,
    updated_at timestamp                             null,
    deleted_at timestamp                             null
);

create table user
(
    id                bigint unsigned auto_increment
        primary key,
    name              varchar(60)                                                          not null,
    email             varchar(127)                                                         not null,
    email_verified_at timestamp                                                            null,
    password          varchar(60)                                                          null,
    status            enum ('ACTIVATE', 'DEACTIVATE', 'BLOCK') default 'DEACTIVATE'        null,
    photo_id          bigint unsigned                                                      null,
    remember_token    varchar(100)                                                         null,
    created_at        timestamp                                default current_timestamp() not null,
    updated_at        timestamp                                                            null,
    deleted_at        timestamp                                                            null,
    constraint email
        unique (email),
    constraint photo_id
        unique (photo_id),
    constraint user_photo_id_fk
        foreign key (photo_id) references photo (id)
);

create table post
(
    id               bigint unsigned auto_increment
        primary key,
    title            varchar(255)                          not null,
    slug             varchar(255)                          not null,
    description      text                                  not null,
    meta_description text                                  not null,
    meta_keyword     text                                  not null,
    status           enum ('ACTIVATE', 'DEACTIVATE')       not null,
    user_id          bigint unsigned                       null,
    photo_id         bigint unsigned                       null,
    category_id      bigint unsigned                       null,
    created_at       timestamp default current_timestamp() not null on update current_timestamp(),
    updated_at       timestamp                             null,
    deleted_at       timestamp                             null,
    constraint post_category_id_fk
        foreign key (category_id) references category (id),
    constraint post_photo_id_fk
        foreign key (photo_id) references photo (id),
    constraint post_user_id_fk
        foreign key (user_id) references user (id)
);

create table comment
(
    id          bigint unsigned auto_increment
        primary key,
    post_id     bigint unsigned                       not null,
    parent_id   bigint unsigned                       not null,
    description text                                  not null,
    status      enum ('APPROVED', 'REJECTED')         not null,
    created_at  timestamp default current_timestamp() not null on update current_timestamp(),
    updated_at  timestamp                             null,
    deleted_at  timestamp                             null,
    constraint comment_comment_id_fk
        foreign key (parent_id) references comment (id),
    constraint comment_post_id_fk
        foreign key (post_id) references post (id)
);

create table role_user
(
    id      bigint unsigned auto_increment
        primary key,
    role_id bigint unsigned not null,
    user_id bigint unsigned not null,
    constraint role_user_user_id_role_id_uindex
        unique (user_id, role_id),
    constraint role_user_role_id_fk
        foreign key (role_id) references role (id),
    constraint role_user_user_id_fk
        foreign key (user_id) references user (id)
);

