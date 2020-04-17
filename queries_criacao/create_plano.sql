CREATE TABLE plano(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(256) NOT NULL,
    valor FLOAT NOT NULL,
    duracao INT NOT NULL
);

INSERT INTO plano VALUES
(1,'Plano FIT Anual', 69.90, 12),
(2,'Plano FIT Semestral', 99.90, 6),
(3,'Plano FIT Trimestral', 129.90, 3),
(4,'Plano ACQUAFIT Anual', 169.90, 12),
(5,'Plano ACQUAFIT Semestral', 199.90, 6),
(6,'Plano ACQUAFIT Trimestral', 229.90, 3)
