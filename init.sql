CREATE TABLE IF NOT EXISTS public.tb_contratante
(
    cpf character varying(14) COLLATE pg_catalog."default" NOT NULL,
    nome character varying(150) COLLATE pg_catalog."default" NOT NULL,
    telefone character varying(15) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tb_contratante_pkey PRIMARY KEY (cpf)
);

CREATE TABLE IF NOT EXISTS public.tb_endereco
(
    id character varying(32) COLLATE pg_catalog."default" NOT NULL,
    cep character varying(9) COLLATE pg_catalog."default" NOT NULL,
    bairro character varying(100) COLLATE pg_catalog."default" NOT NULL,
    rua character varying(100) COLLATE pg_catalog."default" NOT NULL,
    numero integer NOT NULL,
    CONSTRAINT tb_endereco_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.tb_pecas
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
    CONSTRAINT tb_pecas_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.tb_contrato
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
    CONSTRAINT tb_contrato_pkey PRIMARY KEY (id),
    CONSTRAINT tb_contrato_cpf_contratante_fkey FOREIGN KEY (cpf_contratante)
        REFERENCES public.tb_contratante (cpf) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT tb_contrato_id_endereco_fkey FOREIGN KEY (id_endereco)
        REFERENCES public.tb_endereco (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT tb_contrato_id_pecas_fkey FOREIGN KEY (id_pecas)
        REFERENCES public.tb_pecas (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.tb_estoque_andaime
(
    tamanho integer NOT NULL,
    quantidade integer NOT NULL
);

CREATE TABLE IF NOT EXISTS public.tb_estoque_escora
(
    tamanho integer NOT NULL,
    quantidade integer NOT NULL
);

CREATE TABLE IF NOT EXISTS public.tb_estoque_plataforma
(
    tamanho integer NOT NULL,
    quantidade integer NOT NULL
);

CREATE TABLE IF NOT EXISTS public.tb_estoque_roldana
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
		UPDATE tb_estoque_andaime SET quantidade = quantidade - NEW.qtd_andaime
		WHERE tamanho = NEW.tam_andaime;
		UPDATE tb_estoque_escora SET quantidade = quantidade - NEW.qtd_escora
		WHERE tamanho = NEW.tam_escora;
		UPDATE tb_estoque_plataforma SET quantidade = quantidade - NEW.qtd_plataforma
		WHERE tamanho = NEW.tam_plataforma;
		UPDATE tb_estoque_roldana SET quantidade = quantidade - NEW.qtd_roldana;
		RETURN NEW;
	ELSEIF(TG_OP = 'UPDATE') THEN
		UPDATE tb_estoque_andaime SET quantidade = quantidade + NEW.qtd_andaime
		WHERE tamanho = NEW.tam_andaime;
		UPDATE tb_estoque_escora SET quantidade = quantidade + NEW.qtd_escora
		WHERE tamanho = NEW.tam_escora;
		UPDATE tb_estoque_plataforma SET quantidade = quantidade + NEW.qtd_plataforma
		WHERE tamanho = NEW.tam_plataforma;
		UPDATE tb_estoque_roldana SET quantidade = quantidade + NEW.qtd_roldana;
		RETURN NEW;
	ELSEIF(TG_OP = 'DELETE') THEN
		UPDATE tb_estoque_andaime SET quantidade = quantidade + OLD.qtd_andaime
		WHERE tamanho = OLD.tam_andaime;
		UPDATE tb_estoque_escora SET quantidade = quantidade + OLD.qtd_escora
		WHERE tamanho = OLD.tam_escora;
		UPDATE tb_estoque_plataforma SET quantidade = quantidade + OLD.qtd_plataforma
		WHERE tamanho = OLD.tam_plataforma;
		UPDATE tb_estoque_roldana SET quantidade = quantidade + OLD.qtd_roldana;
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
		UPDATE tb_estoque_andaime SET quantidade = quantidade + (select qtd_andaime from tb_pecas where id = NEW.id_pecas)
		WHERE tamanho = (SELECT tam_andaime FROM tb_pecas WHERE id = NEW.id_pecas);
		UPDATE tb_estoque_escora SET quantidade = quantidade + (select qtd_escora from tb_pecas where id = NEW.id_pecas)
		WHERE tamanho = (SELECT tam_escora FROM tb_pecas WHERE id = NEW.id_pecas);
		UPDATE tb_estoque_plataforma SET quantidade = quantidade + (select qtd_plataforma from tb_pecas where id = NEW.id_pecas)
		WHERE tamanho = (SELECT tam_plataforma FROM tb_pecas WHERE id = NEW.id_pecas);
		UPDATE tb_estoque_roldana SET quantidade = quantidade + (select qtd_roldana from tb_pecas where id = NEW.id_pecas);
		RETURN NEW;
END;
$BODY$;

CREATE OR REPLACE TRIGGER t_atualiza_estoque
    AFTER INSERT OR DELETE OR UPDATE
    ON public.tb_pecas
    FOR EACH ROW
    EXECUTE FUNCTION public.atualiza_estoque();

CREATE OR REPLACE TRIGGER t_atualiza_estoque_contrato_vencido
     AFTER UPDATE
     ON public.tb_contrato
     FOR EACH ROW
     EXECUTE FUNCTION public.atualiza_estoque_contrato_vencido();

INSERT INTO tb_estoque_andaime VALUES (1, 500), (2, 650), (3, 550);
INSERT INTO tb_estoque_escora VALUES (1, 500), (2, 650), (3, 550);
INSERT INTO tb_estoque_plataforma VALUES (1, 300), (2, 250), (3, 280);
INSERT INTO tb_estoque_roldana VALUES(600);