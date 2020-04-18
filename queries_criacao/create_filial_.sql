DROP TABLE filial  ;
CREATE TABLE filial(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE ,
    email VARCHAR(256) NOT NULL UNIQUE,
    telefone_fixo VARCHAR(15) NOT NULL,
    id_endereco INT NOT NULL REFERENCES endereco(id) ON DELETE RESTRICT
);

INSERT INTO filial VALUES
(1,'Sede','sede@acad.com', '(11)4432-5453', 1),
(2, 'Filial Anália Franco','filia_analia@acad.com', '(11)6453-7354', 2),
(3, 'Filial Belém','filial_belem@acad.com', '(11)8523-3486', 3)

