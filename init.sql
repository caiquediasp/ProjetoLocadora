CREATE TABLE IF NOT EXISTS public.contratante
(
    cpf character varying(14) COLLATE pg_catalog."default" NOT NULL,
    nome character varying(150) COLLATE pg_catalog."default" NOT NULL,
    telefone character varying(15) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT contratante_pkey PRIMARY KEY (cpf)
);

CREATE TABLE IF NOT EXISTS public.endereco
(
    id character varying(32) COLLATE pg_catalog."default" NOT NULL,
    cep character varying(9) COLLATE pg_catalog."default" NOT NULL,
    bairro character varying(100) COLLATE pg_catalog."default" NOT NULL,
    rua character varying(100) COLLATE pg_catalog."default" NOT NULL,
    numero integer NOT NULL,
    CONSTRAINT endereco_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.pecas
(
    id character varying(32) COLLATE pg_catalog."default" NOT NULL,
    qtd_andaime integer NOT NULL,
    tam_andaime integer NOT NULL,
    valor_andaime numeric(10,2) NOT NULL,
    qtd_escora integer NOT NULL,
    tam_escora integer NOT NULL,
    valor_escora numeric(10,2) NOT NULL,
    qtd_plataforma integer NOT NULL,
    tam_plataforma integer NOT NULL,
    valor_plataforma numeric(10,2) NOT NULL,
    qtd_roldana integer NOT NULL,
    valor_roldana numeric(10,2) NOT NULL,
    CONSTRAINT pecas_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.contrato
(
    id character varying(32) COLLATE pg_catalog."default" NOT NULL,
    cpf_contratante character varying(14) COLLATE pg_catalog."default" NOT NULL,
    id_endereco character varying(32) COLLATE pg_catalog."default" NOT NULL,
    id_pecas character varying(32) COLLATE pg_catalog."default" NOT NULL,
    data_locacao date NOT NULL,
    data_devolucao date NOT NULL,
    forma_pagamento character varying(35) COLLATE pg_catalog."default" NOT NULL,
    valor_total numeric(10,2) NOT NULL,
    status character varying(10) COLLATE pg_catalog."default",
    CONSTRAINT contrato_pkey PRIMARY KEY (id),
    CONSTRAINT contrato_cpf_contratante_fkey FOREIGN KEY (cpf_contratante)
        REFERENCES public.contratante (cpf) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT contrato_id_endereco_fkey FOREIGN KEY (id_endereco)
        REFERENCES public.endereco (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT contrato_id_pecas_fkey FOREIGN KEY (id_pecas)
        REFERENCES public.pecas (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.estoqueandaime
(
    tamanho integer NOT NULL,
    quantidade integer NOT NULL
);

CREATE TABLE IF NOT EXISTS public.estoqueescora
(
    tamanho integer NOT NULL,
    quantidade integer NOT NULL
);

CREATE TABLE IF NOT EXISTS public.estoqueplataforma
(
    tamanho integer NOT NULL,
    quantidade integer NOT NULL
);

CREATE TABLE IF NOT EXISTS public.estoqueroldana
(
    quantidade integer NOT NULL
);

CREATE OR REPLACE FUNCTION public.atualiza_estoque()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
	IF(TG_OP = 'INSERT') THEN
		UPDATE estoqueandaime SET quantidade = quantidade - NEW.qtd_andaime
		WHERE tamanho = NEW.tam_andaime;
		UPDATE estoqueescora SET quantidade = quantidade - NEW.qtd_escora
		WHERE tamanho = NEW.tam_escora;
		UPDATE estoqueplataforma SET quantidade = quantidade - NEW.qtd_plataforma
		WHERE tamanho = NEW.tam_plataforma;
		UPDATE estoqueroldana SET quantidade = quantidade - NEW.qtd_roldana;
		RETURN NEW;
	ELSEIF(TG_OP = 'UPDATE') THEN
		UPDATE estoqueandaime SET quantidade = quantidade + NEW.qtd_andaime
		WHERE tamanho = NEW.tam_andaime;
		UPDATE estoqueescora SET quantidade = quantidade + NEW.qtd_escora
		WHERE tamanho = NEW.tam_escora;
		UPDATE estoqueplataforma SET quantidade = quantidade + NEW.qtd_plataforma
		WHERE tamanho = NEW.tam_plataforma;
		UPDATE estoqueroldana SET quantidade = quantidade + NEW.qtd_roldana;
		RETURN NEW;
	ELSEIF(TG_OP = 'DELETE') THEN
		UPDATE estoqueandaime SET quantidade = quantidade + OLD.qtd_andaime
		WHERE tamanho = OLD.tam_andaime;
		UPDATE estoqueescora SET quantidade = quantidade + OLD.qtd_escora
		WHERE tamanho = OLD.tam_escora;
		UPDATE estoqueplataforma SET quantidade = quantidade + OLD.qtd_plataforma
		WHERE tamanho = OLD.tam_plataforma;
		UPDATE estoqueroldana SET quantidade = quantidade + OLD.qtd_roldana;
		RETURN OLD;
	END IF;
RETURN NULL;
END;
$BODY$;

CREATE OR REPLACE FUNCTION public.atualiza_estoque_contrato_vencido()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
		UPDATE estoqueandaime SET quantidade = quantidade + (select qtd_andaime from pecas where id = NEW.id_pecas)
		WHERE tamanho = (SELECT tam_andaime FROM pecas WHERE id = NEW.id_pecas);
		UPDATE estoqueescora SET quantidade = quantidade + (select qtd_escora from pecas where id = NEW.id_pecas)
		WHERE tamanho = (SELECT tam_escora FROM pecas WHERE id = NEW.id_pecas);
		UPDATE estoqueplataforma SET quantidade = quantidade + (select qtd_plataforma from pecas where id = NEW.id_pecas)
		WHERE tamanho = (SELECT tam_plataforma FROM pecas WHERE id = NEW.id_pecas);
		UPDATE estoqueroldana SET quantidade = quantidade + (select qtd_roldana from pecas where id = NEW.id_pecas);
		RETURN NEW;
END;
$BODY$;

CREATE OR REPLACE TRIGGER t_atualiza_estoque
    AFTER INSERT OR DELETE OR UPDATE
    ON public.pecas
    FOR EACH ROW
    EXECUTE FUNCTION public.atualiza_estoque();

CREATE OR REPLACE TRIGGER t_atualiza_estoque_contrato_vencido
     AFTER UPDATE
     ON public.contrato
     FOR EACH ROW
     EXECUTE FUNCTION public.atualiza_estoque_contrato_vencido();

INSERT INTO estoqueandaime VALUES (1, 500), (2, 650), (3, 550);
INSERT INTO estoqueescora VALUES (1, 500), (2, 650), (3, 550);
INSERT INTO estoqueplataforma VALUES (1, 300), (2, 250), (3, 280);
INSERT INTO estoqueroldana VALUES(600);