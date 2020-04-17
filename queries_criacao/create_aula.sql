CREATE TABLE aula(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    tipo varchar(50) NOT NULL
);

INSERT INTO aula VALUES
(1, 'Karate', 'Luta'),
(2, 'Hidroginasticas', 'Aquática'),
(3, 'Spinning', 'Aerobico'),
(4, 'Step', 'Aerobico')