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

INSERT INTO dependente VALUES
(1,1,'Guido','Veum','35019525098','2006-01-25','Masculino'),
(1,2,'Saul','Bartell','67021093299','2009-11-06','Masculino'),
(1,3,'Malcolm','Hoeger','77399817913','2003-12-31','Masculino'),
(1,4,'Keon','Littel','77163656686','2001-05-27','Masculino'),
(1,5,'Estevan','Fahey','55486822429','2019-12-02','Masculino'),
(1,6,'Kayley','Olson','57080383421','2002-01-28','Masculino'),
(1,8,'Antone','Murray','36084756731','2005-03-21','Masculino'),
(1,9,'Jairo','Rowe','10071286636','2016-12-13','Masculino'),
(1,'10','Mikel','Kassulke','73202997289','2015-07-31','Masculino'),
(2,3,'Valentina','Lubowitz','58772179293','2010-10-08','Feminino'),
(2,4,'Millie','Corwin','95729682975','2018-09-01','Feminino'),
(2,5,'Janis','Grant','84033408898','2019-12-29','Feminino'),
(2,6,'Edwina','Corkery','88073345936','2019-07-25','Feminino'),
(2,7,'Pascale','Feil','89917056563','2003-07-01','Feminino'),
(2,8,'Dortha','Turner','44094683337','2007-07-10','Feminino');