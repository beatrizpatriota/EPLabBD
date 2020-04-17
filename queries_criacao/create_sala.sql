CREATE TABLE sala(
    id SERIAL PRIMARY KEY,
    id_filial INT NOT NULL references filial(id) ON DELETE CASCADE,
    nome VARCHAR(50) NOT NULL,
    capacidade INT NOT NULL
);

INSERT INTO sala VALUES
(1, 1, 'Tatame', 30),
(2, 1, 'Piscina', 20),
(3, 1, 'Sala de Spinning', 15),
(4, 1, 'Sala Espelhada', 40),
(5, 1, 'Espaço Musculação', 50),
(6, 2, 'Sala Espelhada', 30),
(7, 2, 'Espaço Musculação', 35),
(8, 3, 'Tatame', 20),
(9, 3, 'Piscina', 30),
(10, 3, 'Espaço Musculação', 40)


