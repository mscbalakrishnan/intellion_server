--
-- Name: appointment; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE appointment (
    id bigint NOT NULL,
    "time" timestamp without time zone NOT NULL,
    doctor_id bigint,
    patient_id bigint
);


ALTER TABLE appointment OWNER TO cms;

--
-- Name: appointment_sequence; Type: SEQUENCE; Schema: public; Owner: cms
--

CREATE SEQUENCE appointment_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE appointment_sequence OWNER TO cms;

--
-- Name: category; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE category (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE category OWNER TO cms;

--
-- Name: category_sequence; Type: SEQUENCE; Schema: public; Owner: cms
--

CREATE SEQUENCE category_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE category_sequence OWNER TO cms;

--
-- Name: doctor; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE doctor (
    id bigint NOT NULL,
    email character varying(255),
    fees real NOT NULL,
    mobile character varying(255),
    name character varying(255) NOT NULL,
    qualification character varying(255),
    title integer
);


ALTER TABLE doctor OWNER TO cms;

--
-- Name: doctor_category; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE doctor_category (
    category_id bigint NOT NULL,
    doctor_id bigint NOT NULL
);


ALTER TABLE doctor_category OWNER TO cms;

--
-- Name: doctor_sequence; Type: SEQUENCE; Schema: public; Owner: cms
--

CREATE SEQUENCE doctor_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE doctor_sequence OWNER TO cms;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: cms
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO cms;

--
-- Name: medication; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE medication (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    type integer NOT NULL
);


ALTER TABLE medication OWNER TO cms;

--
-- Name: medication_sequence; Type: SEQUENCE; Schema: public; Owner: cms
--

CREATE SEQUENCE medication_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE medication_sequence OWNER TO cms;

--
-- Name: patient; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE patient (
    id bigint NOT NULL,
    occupation character varying(255),
    address1 character varying(255),
    address2 character varying(255),
    age integer NOT NULL,
    allergies character varying(255),
    birthday_wish boolean NOT NULL,
    blood_group integer,
    city character varying(255),
    dob date,
    email character varying(255),
    gender integer,
    label character varying(255),
    landline character varying(255),
    medical_alert character varying(255),
    medical_history character varying(255),
    mobile character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    need_welcome_message boolean NOT NULL,
    pincode character varying(255),
    profile_id character varying(255),
    remainder integer,
    title integer
);


ALTER TABLE patient OWNER TO cms;

--
-- Name: patient_sequence; Type: SEQUENCE; Schema: public; Owner: cms
--

CREATE SEQUENCE patient_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE patient_sequence OWNER TO cms;

--
-- Name: prescription; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE prescription (
    id bigint NOT NULL,
    date date NOT NULL,
    doctor_id bigint,
    patient_id bigint
);


ALTER TABLE prescription OWNER TO cms;

--
-- Name: prescription_entry; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE prescription_entry (
    id bigint NOT NULL,
    before_food_morning boolean NOT NULL,
    before_food_night boolean NOT NULL,
    before_food_noon boolean NOT NULL,
    morning real NOT NULL,
    night real NOT NULL,
    no_of_days real NOT NULL,
    noon real NOT NULL,
    notes character varying(255),
    unit_morning integer,
    unit_night integer,
    unit_noon integer,
    medication_id bigint,
    prescription_id bigint
);


ALTER TABLE prescription_entry OWNER TO cms;

--
-- Name: prescription_sequence; Type: SEQUENCE; Schema: public; Owner: cms
--

CREATE SEQUENCE prescription_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE prescription_sequence OWNER TO cms;

--
-- Name: prescriptionentry_sequence; Type: SEQUENCE; Schema: public; Owner: cms
--

CREATE SEQUENCE prescriptionentry_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE prescriptionentry_sequence OWNER TO cms;

--
-- Name: role; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE role (
    role_id integer NOT NULL,
    role character varying(255)
);


ALTER TABLE role OWNER TO cms;

--
-- Name: settings; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE settings (
    id bigint NOT NULL,
    category character varying(255),
    type character varying(255)
);


ALTER TABLE settings OWNER TO cms;

--
-- Name: settings_params; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE settings_params (
    id bigint NOT NULL,
    param_name character varying(255) NOT NULL,
    param_value character varying(255) NOT NULL,
    settings_id bigint
);


ALTER TABLE settings_params OWNER TO cms;

--
-- Name: user_role; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE user_role (
    user_id integer NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE user_role OWNER TO cms;

--
-- Name: users; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE users (
    user_id integer NOT NULL,
    active boolean,
    email character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255) NOT NULL
);


ALTER TABLE users OWNER TO cms;

--
-- Name: appointment_sequence; Type: SEQUENCE SET; Schema: public; Owner: cms
--

SELECT pg_catalog.setval('appointment_sequence', 1, false);


--
-- Name: category_sequence; Type: SEQUENCE SET; Schema: public; Owner: cms
--

SELECT pg_catalog.setval('category_sequence', 1, false);


--
-- Name: doctor_sequence; Type: SEQUENCE SET; Schema: public; Owner: cms
--

SELECT pg_catalog.setval('doctor_sequence', 1, false);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: cms
--

SELECT pg_catalog.setval('hibernate_sequence', 1, false);


--
-- Name: medication_sequence; Type: SEQUENCE SET; Schema: public; Owner: cms
--

SELECT pg_catalog.setval('medication_sequence', 1, false);


--
-- Name: patient_sequence; Type: SEQUENCE SET; Schema: public; Owner: cms
--

SELECT pg_catalog.setval('patient_sequence', 1, false);


--
-- Name: prescription_sequence; Type: SEQUENCE SET; Schema: public; Owner: cms
--

SELECT pg_catalog.setval('prescription_sequence', 1, false);


--
-- Name: prescriptionentry_sequence; Type: SEQUENCE SET; Schema: public; Owner: cms
--

SELECT pg_catalog.setval('prescriptionentry_sequence', 1, false);


--
-- Name: appointment appointment_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY appointment
    ADD CONSTRAINT appointment_pkey PRIMARY KEY (id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: doctor_category doctor_category_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY doctor_category
    ADD CONSTRAINT doctor_category_pkey PRIMARY KEY (category_id, doctor_id);


--
-- Name: doctor doctor_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY doctor
    ADD CONSTRAINT doctor_pkey PRIMARY KEY (id);


--
-- Name: medication medication_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY medication
    ADD CONSTRAINT medication_pkey PRIMARY KEY (id);


--
-- Name: patient patient_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY patient
    ADD CONSTRAINT patient_pkey PRIMARY KEY (id);


--
-- Name: prescription_entry prescription_entry_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY prescription_entry
    ADD CONSTRAINT prescription_entry_pkey PRIMARY KEY (id);


--
-- Name: prescription prescription_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT prescription_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (role_id);


--
-- Name: settings_params settings_params_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY settings_params
    ADD CONSTRAINT settings_params_pkey PRIMARY KEY (id);


--
-- Name: settings settings_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY settings
    ADD CONSTRAINT settings_pkey PRIMARY KEY (id);


--
-- Name: doctor uk_2i9eqm6hy3ephpt9ep0xfjsfq; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY doctor
    ADD CONSTRAINT uk_2i9eqm6hy3ephpt9ep0xfjsfq UNIQUE (name);


--
-- Name: patient uk_ov1mjrglxi907dck4u1sixtqc; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY patient
    ADD CONSTRAINT uk_ov1mjrglxi907dck4u1sixtqc UNIQUE (name);


--
-- Name: user_role user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: prescription fk1ppr8greedyrey8nchpr0v4dn; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fk1ppr8greedyrey8nchpr0v4dn FOREIGN KEY (doctor_id) REFERENCES doctor(id);


--
-- Name: doctor_category fk24vsg13pcqv4f8oemft35buf0; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY doctor_category
    ADD CONSTRAINT fk24vsg13pcqv4f8oemft35buf0 FOREIGN KEY (category_id) REFERENCES category(id);


--
-- Name: doctor_category fk3qokrjwt4mfgywr0tkbwgo8ro; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY doctor_category
    ADD CONSTRAINT fk3qokrjwt4mfgywr0tkbwgo8ro FOREIGN KEY (doctor_id) REFERENCES doctor(id);


--
-- Name: appointment fk4apif2ewfyf14077ichee8g06; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY appointment
    ADD CONSTRAINT fk4apif2ewfyf14077ichee8g06 FOREIGN KEY (patient_id) REFERENCES patient(id);


--
-- Name: prescription_entry fk7397qlsisqb8ubc7addaxoks8; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY prescription_entry
    ADD CONSTRAINT fk7397qlsisqb8ubc7addaxoks8 FOREIGN KEY (prescription_id) REFERENCES prescription(id);


--
-- Name: settings_params fk9hs1opr9rsxac7o47tqs5p6c6; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY settings_params
    ADD CONSTRAINT fk9hs1opr9rsxac7o47tqs5p6c6 FOREIGN KEY (settings_id) REFERENCES settings(id);


--
-- Name: user_role fka68196081fvovjhkek5m97n3y; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY user_role
    ADD CONSTRAINT fka68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES role(role_id);


--
-- Name: prescription_entry fkh60t2u6ffd3ucpk0sy1ke2i0m; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY prescription_entry
    ADD CONSTRAINT fkh60t2u6ffd3ucpk0sy1ke2i0m FOREIGN KEY (medication_id) REFERENCES medication(id);


--
-- Name: user_role fkj345gk1bovqvfame88rcx7yyx; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY user_role
    ADD CONSTRAINT fkj345gk1bovqvfame88rcx7yyx FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- Name: appointment fkoeb98n82eph1dx43v3y2bcmsl; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY appointment
    ADD CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl FOREIGN KEY (doctor_id) REFERENCES doctor(id);


--
-- Name: prescription fkqrlh184tfvdi95erwl65p4xj3; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fkqrlh184tfvdi95erwl65p4xj3 FOREIGN KEY (patient_id) REFERENCES patient(id);


--
-- PostgreSQL database dump complete
--

