CREATE TABLE equipamento(
    id SERIAL PRIMARY KEY,
    id_sala INT NOT NULL REFERENCES sala(id) ON DELETE RESTRICT,
    nome varchar(50) NOt NULL,
    condicao VARCHAR(30) NOT NULL,
    tipo_exercicio VARCHAR(30) NOT NULL,
    data_compra DATE NOT NULL,
    data_manutencao DATE
);

INSERT INTO equipamento VALUES
(1,10,'Mesa Flexora','disponível','Inferior','1972-03-17','2019-03-12'),
(2,7,'Kit Halter Emborrachado','Indisponível','Superior','1972-03-11','2019-03-12'),
(3,10,'Mesa Flexora','Indisponível','Inferior','1999-05-04','2019-03-12'),
(4,7,'Cadeira Extensora','disponível','Inferior','2012-06-30','1973-12-15'),
(5,5,'Crossover','disponível','Superior','1985-11-17','2004-02-27'),
(6,10,'Crossover','disponível','Superior','2000-07-13','1978-11-23'),
(7,5,'Mesa Flexora.Puxador Costas','Inferior','rerum','2007-02-10','2010-06-14'),
(8,10,'Banco Supino Reto','Indisponível','Superior','2018-08-25','2004-01-25'),
(9,7,'Cadeira Extensora','disponível','Inferior','1981-12-18','1996-11-09'),
(10,7,'Cadeira Extensora','Indisponível','Infeiror','1983-11-14','2015-11-20'),
(11,7,'Puxador Costas','dis','Superior','1993-08-28','1999-07-03'),
(12,7,'Puxador Costas','disponível','Superior','2017-02-12','1971-08-25'),
(13,10,'Cadeira Extensora','Indisponível','Inferior','2018-01-29','2019-03-12'),
(14,5,'Crossover','Em manutenção','Superior','2016-11-16','2019-03-12'),
(15,10,'Crossover','Em manutenção','Superior','1975-07-22','2019-03-12'),
(16,5,'Kit Halter Emborrachado','Superior','fugit','1985-05-03','1988-01-17'),
(17,7,'Esteira Natural Premium','Aerobico','eum','1980-03-11','2019-03-12'),
(18,5,'Cadeira Extensora','Indisponível','Inferior','1992-03-20','2019-03-12');