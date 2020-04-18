DROP TABLE IF EXISTS endereco CASCADE ;
CREATE TABLE endereco(
    id SERIAL PRIMARY KEY,
    logradouro VARCHAR(100) NOT NULL,
    numero INT NOT NULL,
    complemento VARCHAR(100),
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(100) NOT NULL,
    cep VARCHAR(100) NOt NULL
);

DROP TABLE IF EXISTS plano CASCADE ;
CREATE TABLE plano(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(256) NOT NULL,
    valor FLOAT NOT NULL,
    duracao INT NOT NULL
);

DROP TABLE IF EXISTS filial CASCADE ;
CREATE TABLE filial(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE ,
    email VARCHAR(256) NOT NULL UNIQUE,
    telefone_fixo VARCHAR(15) NOT NULL,
    id_endereco INT NOT NULL REFERENCES endereco(id) ON DELETE RESTRICT
);

DROP TABLE IF EXISTS sala CASCADE ;
CREATE TABLE sala(
    id SERIAL PRIMARY KEY,
    id_filial INT NOT NULL references filial(id) ON DELETE CASCADE,
    nome VARCHAR(50) NOT NULL,
    capacidade INT NOT NULL
);


DROP TABLE IF EXISTS equipamento CASCADE ;
CREATE TABLE equipamento(
    id SERIAL PRIMARY KEY,
    id_sala INT NOT NULL REFERENCES sala(id) ON DELETE RESTRICT,
    nome varchar(50) NOt NULL,
    condicao VARCHAR(30) NOT NULL,
    tipo_exercicio VARCHAR(30) NOT NULL,
    data_compra DATE NOT NULL,
    data_manutencao DATE
);


DROP TABLE IF EXISTS funcionario CASCADE ;
CREATE TABLE funcionario(
    id SERIAL PRIMARY KEY,
    id_filial INT NOT NULL REFERENCES filial(id),
    tipo VARCHAR(50) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    cpf CHAR(11)  NOT NULL UNIQUE ,
    data_nascimento DATE NOT NULL,
    email VARCHAR(256) NOt NULL UNIQUE,
    sexo VARCHAR(10) NOT NULL,
    telefone_fixo VARCHAR(15) NOT NULL,
    celular VARCHAR(15) NOT NULL,
    id_endereco INT NOT NULL REFERENCES endereco(id) ON DELETE RESTRICT
);

DROP TABLE IF EXISTS dependente CASCADE ;
CREATE TABLE dependente(
    id INT NOT NULL,
    id_funcionario INT NOT NULL REFERENCES funcionario(id) ON DELETE CASCADE,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    sexo VARCHAR(10) NOT NULL,
    PRIMARY KEY (id, id_funcionario)
);

DROP TABLE IF EXISTS cliente CASCADE ;
CREATE TABLE cliente(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    cpf CHAR(11)  NOT NULL UNIQUE ,
    data_nascimento DATE NOT NULL,
    email VARCHAR(256) NOt NULL UNIQUE,
    sexo VARCHAR(10) NOT NULL,
    telefone_fixo VARCHAR(15) NOT NULL,
    celular VARCHAR(15) NOT NULL,
    id_endereco INT NOT NULL REFERENCES endereco(id) ON DELETE RESTRICT
);

DROP TABLE IF EXISTS matricula CASCADE ;
CREATE TABLE matricula(
    id SERIAL PRIMARY KEY,
    id_cliente INT NOT NULL UNIQUE REFERENCES cliente(id) ON DELETE RESTRICT,
    id_personal INT NOT NULL REFERENCES funcionario(id) ON DELETE RESTRICT,
    id_plano INT NOT NULL REFERENCES plano(id) ON DELETE RESTRICT,
    data_matricula DATE NOT NULL
);

DROP TABLE IF EXISTS aula CASCADE ;
CREATE TABLE aula(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    tipo varchar(50) NOT NULL
);

DROP TABLE IF EXISTS oferecimento CASCADE ;
CREATE TABLE oferecimento(
    id SERIAL PRIMARY KEY,
    id_professor INT NOT NULL REFERENCES funcionario(id) ON DELETE RESTRICT,
    id_aula INT NOT NULL REFERENCES aula(id) ON DELETE RESTRICT,
    id_sala INT NOT NULL REFERENCES sala(id) ON DELETE RESTRICT,
    horario TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS rel_matricula_oferecimento CASCADE ;
CREATE TABLE rel_matricula_oferecimento(
    id_matricula    INT     NOT NULL references matricula (id) ON DELETE CASCADE,
    id_oferecimento INT     NOT NULL references oferecimento (id) ON DELETE CASCADE,
    presenca        boolean NOT NULL
);