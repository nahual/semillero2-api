create user semillero@'%' identified by 'semillero';
create database semillero CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
grant all privileges on semillero.* to semillero@'%';
flush privileges;
