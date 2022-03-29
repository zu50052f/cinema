drop table if exists premiere;
create table premiere (
    id varchar2(100) auto_increment primary key,
    name varchar2(100) not null,
    description varchar2(1000) not null,
    age_category int not null ,
    available_seats int not null
);