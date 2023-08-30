create database garmdb;
create user garm with password 'garm$$123';
grant all privileges on database garmdb to garm;
\c garmdb;
create schema garm;
alter schema garm owner to garm;

