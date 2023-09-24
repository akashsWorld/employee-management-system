drop schema if exists `demo_test`;
create database `demo_test`;
use `demo_test`;

drop table if exists `employee_user_table`;
create table `employee_user_table`
(
    emp_id     varchar(36) PRIMARY KEY,
    email   varchar(20) not null unique,
    password varchar(60) not null ,
    role ENUM('ADMIN','MANAGER')
);


drop table if exists `employee_table`;
create table `employee_table`
(
    emp_id     varchar(36) PRIMARY KEY,
    first_name varchar(20) not null,
    last_name  varchar(20) not null,
    birth_date datetime    not null,
    gender        ENUM ('MALE','FEMALE'),
    salary     double      not null,

    FOREIGN KEY (emp_id) references employee_user_table(emp_id) on delete cascade
);

drop table if exists `token_table`;
create table `token_table`
(
    emp_id     varchar(36) PRIMARY KEY,
    token varchar(500) not null,
    created_at datetime ,
    FOREIGN KEY (emp_id) references employee_user_table(emp_id) on delete cascade
);

-- TODO: Design whole schema