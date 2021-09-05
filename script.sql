create table course
(
    course_id      varchar(20) null,
    course_name    varchar(50) null,
    course_teacher varchar(50) null
);

create table hw12_user
(
    id       int         null,
    password varchar(20) null
);

create table select_course
(
    select_id  varchar(20) null,
    student_id varchar(20) null,
    course_id  varchar(20) null
);

create table student
(
    student_id       varchar(20) null,
    student_password varchar(20) null
);

create table user
(
    u_id   varchar(20)             not null,
    u_pass varchar(20) default '0' not null
);

create table userlog
(
    id            int auto_increment
        primary key,
    operator      varchar(5)  null,
    operateType   varchar(20) null,
    operateDate   datetime    null,
    operateResult varchar(4)  null
)
    charset = utf8;

create table users
(
    id        bigint auto_increment
        primary key,
    userName  varchar(32) null,
    passWord  varchar(32) null comment '
',
    user_sex  varchar(32) null,
    nick_name varchar(32) null
)
    charset = utf8;


