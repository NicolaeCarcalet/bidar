create table skills(skill_id bigint auto_increment primary key, skill varchar(255), user_id bigint);
create table interests(interest_id bigint auto_increment primary key, interest varchar(255), user_id bigint);
create table profile(user_id bigint primary key, first_name varchar(255), last_name varchar(255), country varchar(255), age int);

CREATE SEQUENCE SKILLS_SEQUENCE START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE INTEREST_SEQUENCE START WITH 1 INCREMENT BY 1;
--CREATE SEQUENCE PROFILE_SEQUENCE START WITH 1 INCREMENT BY 1;
