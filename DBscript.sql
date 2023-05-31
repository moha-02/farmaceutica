
CREATE DATABASE farmacia;
use farmacia;
create or replace table doctor
(
    mail     varchar(50)  not null
        primary key,
    pass     varchar(100) null,
    name     varchar(100) null,
    last_log date         null,
    session  int          null
);

create or replace table medicine
(
    id   bigint unsigned auto_increment
        primary key,
    name varchar(255) null,
    tmax float        null,
    tmin float        null
);

create or replace table patient
(
    mail varchar(50)  not null
        primary key,
    name varchar(100) null
);

create or replace table xip
(
    id          int             not null
        primary key,
    doctor_mail varchar(255)    null,
    id_medicine bigint unsigned null,
    id_patient  varchar(50)     null,
    date        date            null,
    constraint doctor_mail
        foreign key (doctor_mail) references doctor (mail),
    constraint id_medicine
        foreign key (id_medicine) references medicine (id),
    constraint id_patient
        foreign key (id_patient) references patient (mail)
);

