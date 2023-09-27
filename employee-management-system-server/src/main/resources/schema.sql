drop schema if exists `demo_test`;
create database `demo_test`;
use `demo_test`;

drop table if exists `employee_user_table`;
create table `employee_user_table`
(
    emp_id     varchar(36) PRIMARY KEY,
    email   varchar(20) not null unique,
    password varchar(60) not null ,
    role ENUM('ADMIN','MANAGER','SYSTEM_ADMIN','USER')
);


drop table if exists `employee_table`;
create table `employee_table`
(
    emp_id     varchar(36) PRIMARY KEY,
    first_name varchar(20) not null,
    last_name  varchar(20) not null,
    birth_date date    not null,
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


drop table if exists `branch_table`;
create table `branch_table`(
   branch_id varchar(36) primary key ,
   branch_name varchar(25) not null ,
   manager_id varchar(36) unique ,
   manager_start_date datetime,
   CONSTRAINT fk_customers FOREIGN KEY (manager_id) references employee_table(emp_id) on delete set null
);

alter table employee_table add column `super_id` varchar(36);
alter table employee_table add column `branch_id` varchar(36);

alter table employee_table ADD  FOREIGN KEY (super_id) references employee_table(emp_id) on delete set null ;
alter table employee_table ADD FOREIGN KEY (branch_id) references branch_table(branch_id) on DELETE set null ;

drop table if exists branch_suipplier;
create table `branch supplier`
(
    supplier_name varchar(20) ,
    supply_type varchar(20) not null unique ,
    branch_id varchar(36),
    PRIMARY KEY (supplier_name,branch_id),
    FOREIGN KEY(branch_id) references branch_table(branch_id) on delete cascade
);

drop table if exists `client_table`;
create table `client_table`(
   client_id varchar(36) PRIMARY KEY ,

   client_name varchar(20) not null ,

   branch_id varchar(36) not null,

   FOREIGN KEY (branch_id) references branch_table(branch_id) on delete cascade
);

drop table if exists `works_with`;
create table `works_with`
(
    client_id varchar(36),
    emp_id varchar(36),
    PRIMARY KEY (emp_id,client_id),
    total_sales double,
    FOREIGN KEY (emp_id) references employee_table(emp_id) ,
    FOREIGN KEY (client_id) references  client_table(client_id)
);