create table empresa (
	id serial not null
		constraint empresa_pk
			primary key,
	cnpj varchar(255) not null,
	data_atualizacao timestamp not null,
	data_criacao timestamp not null,
	razao_social varchar(255) not null
);

create table funcionario (
	id serial not null
		constraint funcionario_pk
			primary key,
	cpf varchar(255) not null,
	data_atualizacao timestamp not null,
	data_criacao timestamp not null,
	email varchar(255) not null,
	nome varchar(255) not null,
	perfil varchar(255) not null,
	qtd_horas_almoco float,
	qtd_horas_trabalho_dia float,
	senha varchar(255) not null,
	valor_hora decimal(19,2),
	empresa_id bigint
		constraint func_empresa_id_fk
			references empresa (id)
);

create table lancamento (
	id serial not null
		constraint lancamento_pk
			primary key,
	data timestamp not null,
	data_atualizacao timestamp not null,
	data_criacao timestamp not null,
	descricao varchar(255),
	localizacao varchar(255),
	tipo varchar(255) not null,
	funcionario_id bigint
		constraint lanc_func_id_fk
			references funcionario (id)
);

create index lanc_id_index on lancamento (id);

create index func_id_index on funcionario (id);

