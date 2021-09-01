create database dbdualcomp;
use dbdualcomp;
create table carrinho (
	id int primary key auto_increment,
    produto varchar(100) not null,
    quantidade int not null,
    valor decimal(10,2) not null
);

select * from carrinho;

select * from carrinho order by produto;

insert into carrinho (produto, quantidade, valor)
values ('hd',10,200);

insert into carrinho (produto, quantidade, valor)
values ('memoria',5,100);

insert into carrinho (produto, quantidade, valor)
values ('monitor',3,500);
