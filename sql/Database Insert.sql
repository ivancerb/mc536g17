use museu3;

insert into MUSEU (NOME, CONTATO) values ('Museu de Artes Visuais', 'Lucas Araujo');

insert into CURADOR (NOME, CONTATO) values ('Claudia Bauzer Medeiros', 'Sem contato');
insert into CURADOR (NOME, CONTATO) values ('Lucas Oliveira','+55 19 985-548-123');

insert into ESTILO (INICIO, FIM, NOME, DESCRICAO) values ('1950','0','Contemporâneo','');

insert into EXPOSICAO (ID_CURADOR, ID_MUSEU, NOME, DESCRICAO, DATA_INICIO, DATA_FIM, STATUS_CD) values ('1','1','Artes Visuais da Unicamp','Exposição de artes da Unicamp','2015-06-09','9999-12-31','A');
-- STATUS_CD = 'A' -> Andamento
-- STATUS_CD = 'F' -> Finalizado

insert into LOCAL (ZIP_CODE,ID_MUSEU,ENDERECO,CIDADE,ESTADO,PAIS) values ('13083-970','1','CIDade Universitaria Zeferino Vaz','Campinas','SP','Brasil');
