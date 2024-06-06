--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

-- Started on 2024-05-12 19:54:39

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2 (class 3079 OID 33501)
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- TOC entry 4826 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 33451)
-- Name: company; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.company (
    id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    name character varying(255),
    description character varying(255),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.company OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 33459)
-- Name: evento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.evento (
    id uuid NOT NULL,
    name character varying(255),
    description text,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    starts_in timestamp without time zone,
    end_in timestamp without time zone,
    payed_event boolean,
    value_event numeric,
    company_id uuid,
    CONSTRAINT evento_check CHECK (((payed_event = false) OR ((payed_event = true) AND (value_event IS NOT NULL))))
);


ALTER TABLE public.evento OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 33486)
-- Name: participants; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.participants (
    id uuid NOT NULL,
    event_id uuid,
    user_id uuid,
    adm_of_event boolean
);


ALTER TABLE public.participants OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 33473)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    name character varying(255),
    company_id uuid,
    phone character varying(255),
    email character varying(255),
    is_adm boolean,
    password character varying(255),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 4817 (class 0 OID 33451)
-- Dependencies: 216
-- Data for Name: company; Type: TABLE DATA; Schema: public; Owner: postgres
--

--
-- TOC entry 4663 (class 2606 OID 33458)
-- Name: company company_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company
    ADD CONSTRAINT company_pkey PRIMARY KEY (id);


--
-- TOC entry 4665 (class 2606 OID 33467)
-- Name: evento evento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evento
    ADD CONSTRAINT evento_pkey PRIMARY KEY (id);


--
-- TOC entry 4669 (class 2606 OID 33490)
-- Name: participants participants_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.participants
    ADD CONSTRAINT participants_pkey PRIMARY KEY (id);


--
-- TOC entry 4667 (class 2606 OID 33480)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 4670 (class 2606 OID 33468)
-- Name: evento evento_company_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evento
    ADD CONSTRAINT evento_company_id_fkey FOREIGN KEY (company_id) REFERENCES public.company(id);


--
-- TOC entry 4672 (class 2606 OID 33491)
-- Name: participants participants_event_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.participants
    ADD CONSTRAINT participants_event_id_fkey FOREIGN KEY (event_id) REFERENCES public.evento(id);


--
-- TOC entry 4673 (class 2606 OID 33496)
-- Name: participants participants_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.participants
    ADD CONSTRAINT participants_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.usuario(id);


--
-- TOC entry 4671 (class 2606 OID 33481)
-- Name: usuario usuario_company_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_company_id_fkey FOREIGN KEY (company_id) REFERENCES public.company(id);


-- Adiciona a extensão uuid-ossp
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Adiciona uma nova coluna com um UUID gerado automaticamente como valor padrão
ALTER TABLE participants ADD COLUMN new_id uuid DEFAULT uuid_generate_v4();

-- Copia os dados da coluna id antiga para a nova coluna
UPDATE participants SET new_id = id;

-- Remove a coluna id antiga
ALTER TABLE participants DROP COLUMN id;

-- Renomeia a nova coluna para id
ALTER TABLE participants RENAME COLUMN new_id TO id;

-- Completed on 2024-05-12 19:54:41

--
-- PostgreSQL database dump complete
--

