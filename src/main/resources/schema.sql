drop table if exists premiere;
create table premiere (
    id varchar2(100) auto_increment primary key,
    name varchar2(100) not null,
    description varchar2(1000) not null,
    age_category int not null,
    available_seats int not null
);

insert into premiere(name, description, age_category, available_seats)
values('James Bond', 'The most known secret agent', 10, 100);