use museu3;

ALTER TABLE ESTILO MODIFY COLUMN INICIO INT;
ALTER TABLE ESTILO MODIFY COLUMN FIM INT;
ALTER TABLE ARTISTA MODIFY COLUMN DATA_NASC INT;
ALTER TABLE ARTISTA MODIFY COLUMN DATA_FALESC INT;
ALTER TABLE OBRA MODIFY COLUMN DATA INT;
