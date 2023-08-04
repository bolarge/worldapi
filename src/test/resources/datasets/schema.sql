create table if not exists country
(
    id              int auto_increment
    location        varchar(25)   null,
    name            varchar(25)   null,
    population      varchar(25)   null,
    country_capital varchar(25)   null,
    iso_code2       varchar(25)   null,
    iso_code3       varchar(25)   null,
    primary key(id)
);
