create table accounts
(
    id         int auto_increment primary key,
    account_id char(36)                    not null,
    user_id    bigint                      not null,
    balance    decimal(12, 2) default 0.00 null,
    constraint account_id
        unique (account_id),
    constraint id
        unique (id),
    constraint user_id
        unique (user_id)
);