--Query 1: Alunos perto de qual filial
SELECT jpesado.id_funcionario,
       nome,
       id_endereco,
       jpesado.bairro_filial,
       n_clientes
from (
         (SELECT bairro_cliente, COUNT(*) as n_clientes
          from (SELECT c.id   as cliente_id,
                       e.id   as endereco_id,
                       bairro as bairro_cliente
                from cliente c
                         join endereco e on c.id_endereco = e.id) as cE
          GROUP BY cE.bairro_cliente) as cE
             join
             (SELECT f.id   as id_funcionario,
                     e.id   as endereco_id,
                     bairro as bairro_filial,
                     nome,
                     id_endereco
              from ((SELECT * from filial) f
                       join (SELECT * from endereco) e on f.id_endereco = e.id)) as pE
             on pE.bairro_filial LIKE cE.bairro_cliente
         ) as jpesado
order by bairro_filial;


--Query 2: Plano vencido - Selecionar a data da matricula do cliente e comparar com o plano ativo para ele

EXPLAIN
SELECT *
from (Select c.id      as id_cliente,
             c.nome,
             sobrenome,
             m.id      as id_matricula,
             id_plano,
             m.data_matricula,
             p.duracao as duracao
      from cliente c
               join matricula m on c.id = m.id_cliente
               join plano p on p.id = m.id_plano
      where DATE(m.data_matricula + ((INTERVAL '1 month') * duracao)) < current_date) as f
order by id_cliente;

--Query 3: Quais tipos de aula o professor oferece

SELECT of.nome_professor      as nome_professor,
       of.sobrenome_professor as sobrenome_professor,
       nome                   as nome_aula,
       tipo
from aula
         join (
    SELECT id_aula,
           nome      as nome_professor,
           sobrenome as sobrenome_professor
    from funcionario f
             join oferecimento o
                  on f.id = o.id_professor
    where tipo LIKE 'Professor') of on of.id_aula = id;

--Query 4: Quais aulas o aluno está matriculado

SELECT relAll.nome as nome_cliente,
       sobrenome,
       presenca,
       id_professor,
       horario as horario_da_aula,
       aula.nome as nome_aula,
       tipo
from (SELECT *
      from rel_matricula_oferecimento rel
               join oferecimento ON rel.id_oferecimento = oferecimento.id
               join (
          SELECT m.id, nome, sobrenome
          from matricula m
                   join (SELECT * from cliente) as c
                        on m.id_cliente = c.id
      ) as mat on id_matricula = mat.id) as relAll
         join aula on relAll.id_aula = aula.id
order by nome_cliente;