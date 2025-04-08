create database sistema_usuarios;

use sistema_usuarios;

create table usuarios (
	id int,
    nome varchar(100) not null,
	cpf char(11) not null,
    idade int not null,
	constraint pk_usuario_id primary key (id),
    constraint un_usuario_cpf unique (cpf)
);

select * from usuarios;