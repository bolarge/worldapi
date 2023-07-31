create table if not exists country
(
    id              int auto_increment
        primary key,
    date_created    datetime(6)    null,
    date_updated    datetime(6)    null,
    location        varchar(255)   null,
    name            varchar(255)   null,
    population      varchar(255)   null,
    size            varchar(255)   null,
    country_capital varchar(255)   null,
    the_states      varbinary(255) null
);
