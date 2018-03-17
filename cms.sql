--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.6
-- Dumped by pg_dump version 9.6.6

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: address; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE address (
    id bigint NOT NULL,
    lastmodifiedtime bigint,
    version integer,
    apprtment_name character varying(255),
    area character varying(255),
    city character varying(255),
    country character varying(255),
    land_line1 character varying(255),
    land_line2 character varying(255),
    land_line3 character varying(255),
    pincode character varying(255),
    street_name character varying(255),
    patient_id character varying(255)
);


ALTER TABLE address OWNER TO cms;

--
-- Name: appointment; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE appointment (
    id bigint NOT NULL,
    lastmodifiedtime bigint,
    version integer,
    "time" timestamp without time zone NOT NULL,
    doctor_id bigint,
    patient_id character varying(255)
);


ALTER TABLE appointment OWNER TO cms;

--
-- Name: category; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE category (
    id bigint NOT NULL,
    lastmodifiedtime bigint,
    version integer,
    name character varying(255) NOT NULL
);


ALTER TABLE category OWNER TO cms;

--
-- Name: doctor; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE doctor (
    id bigint NOT NULL,
    lastmodifiedtime bigint,
    version integer,
    additional_qualification character varying(255),
    category_id character varying(255),
    color_code character varying(255),
    email character varying(255),
    fees real NOT NULL,
    mobile1 character varying(10) NOT NULL,
    mobile_number2 character varying(10),
    mobile_number3 character varying(10),
    name character varying(255) NOT NULL,
    qualification character varying(255),
    title integer
);


ALTER TABLE doctor OWNER TO cms;

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
-- Name: label; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE label (
    id bigint NOT NULL,
    lastmodifiedtime bigint,
    version integer,
    labelname character varying(255)
);


ALTER TABLE label OWNER TO cms;

--
-- Name: medication; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE medication (
    id bigint NOT NULL,
    lastmodifiedtime bigint,
    version integer,
    name character varying(255) NOT NULL,
    type integer NOT NULL
);


ALTER TABLE medication OWNER TO cms;

--
-- Name: patient; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE patient (
    id character varying(255) NOT NULL,
    lastmodifiedtime bigint,
    version integer,
    occupation character varying(255),
    age integer NOT NULL,
    allergies character varying(255),
    birthday_wish boolean NOT NULL,
    blood_group integer,
    dental_history character varying(255),
    dob date,
    email character varying(255),
    gender integer,
    guardian_name character varying(255),
    label character varying(255),
    medical_alert character varying(255),
    medical_history character varying(255),
    mobile_number1 character varying(10) NOT NULL,
    mobile_number2 character varying(10),
    mobile_number3 character varying(10),
    name character varying(255) NOT NULL,
    need_welcome_message boolean NOT NULL,
    profile_id character varying(255),
    remainder integer,
    title integer,
    patient_id bigint
);


ALTER TABLE patient OWNER TO cms;

--
-- Name: patient_label; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE patient_label (
    patient_id character varying(255) NOT NULL,
    label_id bigint NOT NULL
);


ALTER TABLE patient_label OWNER TO cms;

--
-- Name: prescription; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE prescription (
    id bigint NOT NULL,
    lastmodifiedtime bigint,
    version integer,
    date date NOT NULL,
    doctor_id bigint,
    patient_id character varying(255)
);


ALTER TABLE prescription OWNER TO cms;

--
-- Name: prescription_entry; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE prescription_entry (
    id bigint NOT NULL,
    lastmodifiedtime bigint,
    version integer,
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
-- Name: role; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE role (
    id bigint NOT NULL,
    lastmodifiedtime bigint,
    version integer,
    role character varying(255)
);


ALTER TABLE role OWNER TO cms;

--
-- Name: settings; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE settings (
    id bigint NOT NULL,
    lastmodifiedtime bigint,
    version integer,
    category character varying(255)
);


ALTER TABLE settings OWNER TO cms;

--
-- Name: settings_params; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE settings_params (
    id bigint NOT NULL,
    lastmodifiedtime bigint,
    version integer,
    param_name character varying(255) NOT NULL,
    param_value character varying(255) NOT NULL,
    settings_id bigint
);


ALTER TABLE settings_params OWNER TO cms;

--
-- Name: smsdetails; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE smsdetails (
    id bigint NOT NULL,
    lastmodifiedtime bigint,
    version integer,
    contact_list character varying(255),
    date bigint NOT NULL,
    detail character varying(255),
    failure_cause character varying(255),
    name character varying(255),
    retry_count integer NOT NULL,
    status character varying(255),
    status_code integer NOT NULL
);


ALTER TABLE smsdetails OWNER TO cms;

--
-- Name: user_role; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE user_role (
    user_id character varying(45) NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE user_role OWNER TO cms;

--
-- Name: users; Type: TABLE; Schema: public; Owner: cms
--

CREATE TABLE users (
    name character varying(45) NOT NULL,
    lastmodifiedtime bigint,
    version integer,
    active boolean,
    password character varying(255) NOT NULL
);


ALTER TABLE users OWNER TO cms;

--
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: cms
--

COPY address (id, lastmodifiedtime, version, apprtment_name, area, city, country, land_line1, land_line2, land_line3, pincode, street_name, patient_id) FROM stdin;
30	1519268994754	0	33, Abc Apts	Velachery	Chennai	India	\N	\N	\N	\N	\N	00020218
\.


--
-- Data for Name: appointment; Type: TABLE DATA; Schema: public; Owner: cms
--

COPY appointment (id, lastmodifiedtime, version, "time", doctor_id, patient_id) FROM stdin;
40	1519268994933	0	2018-02-22 08:39:54.926	11	00010218
42	1519268994970	0	2018-02-24 08:39:54.969	11	00010218
190	1519629360591	1	2018-03-08 05:00:00	11	00010218
230	1519748629223	0	2018-03-06 05:00:00	140	00010218
261	1519838779582	0	2018-03-02 13:00:00	12	00030218
262	1519838857152	0	2018-03-02 20:00:00	250	00040218
263	1519838892245	0	2018-03-09 04:00:00	140	00010218
264	1519838923245	0	2018-03-09 13:00:00	12	00030218
267	1519839068101	0	2018-03-02 04:00:00	251	00010218
268	1519839099673	0	2018-03-21 08:00:00	251	00010218
272	1519839439346	0	2018-03-29 18:00:00	12	00030218
273	1519839463505	0	2018-03-19 15:00:00	251	00040218
274	1519839488346	0	2018-03-19 12:00:00	12	00030218
271	1519839876028	1	2018-03-29 21:00:00	251	00040218
275	1519840063325	1	2018-03-29 22:00:00	140	00020218
300	1519879651222	1	2018-03-01 03:00:00	250	00060218
301	1519879683938	0	2018-03-01 03:00:00	140	00040218
269	1519879909978	3	2018-03-21 19:00:00	140	00040218
303	1519880007996	0	2018-03-01 04:00:00	251	00030218
304	1519880344101	0	2018-03-14 14:00:00	330	00010218
305	1519880435892	0	2018-03-14 15:00:00	140	00030218
306	1519880487151	0	2018-03-14 17:00:00	12	00040218
460	1519913100501	1	2018-03-23 16:00:00	250	00010218
730	1519999768782	0	2018-03-23 17:00:00	140	00050218
731	1519999813926	0	2018-03-23 17:00:00	10	00020218
732	1520001337858	0	2018-03-30 04:00:00	330	00050218
734	1520001386648	0	2018-03-17 04:00:00	330	00060218
736	1520001414773	0	2018-03-24 05:00:00	330	00060218
738	1520001490608	0	2018-03-03 05:00:00	140	00030218
1462	1520605592791	0	2018-03-09 02:00:00	251	00060218
1463	1520605825628	0	2018-03-09 20:00:00	330	00020218
265	1520606013750	1	2018-03-09 04:00:00	10	00010318
1464	1520606053840	0	2018-03-09 21:00:00	10	00010218
1465	1520606373766	0	2018-03-09 13:00:00	251	00050218
737	1520784370456	1	2018-03-15 05:00:00	250	00010218
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: cms
--

COPY category (id, lastmodifiedtime, version, name) FROM stdin;
20	1519268994659	0	dentist
21	1519268994675	0	root canal specialist
430	1519912860194	0	wisdom teeth
1420	1520601483463	0	custom spl
1421	1520601499488	0	new
218	1520778958649	0	cat
\.


--
-- Data for Name: doctor; Type: TABLE DATA; Schema: public; Owner: cms
--

COPY doctor (id, lastmodifiedtime, version, additional_qualification, category_id, color_code, email, fees, mobile1, mobile_number2, mobile_number3, name, qualification, title) FROM stdin;
10	1519268994350	0	\N	\N	\N	kumara@guru.com	0	9876543321	\N	\N	Kumaraguru	\N	6
11	1519268994686	0	\N	\N	\N	abc@xyz.com	0	9171415876	\N	\N	Kumara	\N	5
140	1519838654210	2	Surgeon, Consultant	20	#cc0066	srini@balajidental.com	450	9171415876	\N	\N	T.Srinivasan	M.D.S	5
12	1519838702758	1	Surgeon Consultant	20	#3399ff	xyz@abc.com	500	9971415876	\N	\N	Guru	M.D.S	5
251	1519839010182	0	Surgeon Consultant	20	#ff6600	abc@abc.com	500	7418529630	\N	\N	Arun	B.D.S	5
330	1519880404004	2	Consultant	20	#FF0000	k@gmail.com	250	9701752863	\N	\N	Kavita	BDS	5
250	1519912870628	1	Surgeon Consultant	430	#009933	abc@abc.com	1500	7896541230	\N	\N	Muruganatham	M.D.S	5
1430	1520601556768	1	surgeon	20		senthilvasanth_84@yahoo.com	500	1234567890	\N	\N	sherwin1	mds	5
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: cms
--

SELECT pg_catalog.setval('hibernate_sequence', 284, true);


--
-- Data for Name: label; Type: TABLE DATA; Schema: public; Owner: cms
--

COPY label (id, lastmodifiedtime, version, labelname) FROM stdin;
61	1519840499400	1	Promo
180	1519840515409	2	Main Branch
60	1519840543453	2	UpperClass
1470	1520602255438	0	elite
225	1520779314977	0	austr
284	1520876674252	0	sydney
\.


--
-- Data for Name: medication; Type: TABLE DATA; Schema: public; Owner: cms
--

COPY medication (id, lastmodifiedtime, version, name, type) FROM stdin;
50	1519268995328	0	Crosin	0
51	1519268995340	0	benadryl	1
170	1519629142032	0	benadryl12121	1
370	1519880938941	0	p250	1
450	1519912988678	0	p250	1
1450	1520601794281	0	wther	1
198	1520674825335	0	bala	1
221	1520779218006	0	swer	1
\.


--
-- Data for Name: patient; Type: TABLE DATA; Schema: public; Owner: cms
--

COPY patient (id, lastmodifiedtime, version, occupation, age, allergies, birthday_wish, blood_group, dental_history, dob, email, gender, guardian_name, label, medical_alert, medical_history, mobile_number1, mobile_number2, mobile_number3, name, need_welcome_message, profile_id, remainder, title, patient_id) FROM stdin;
00030218	1519838519657	0		0		f	0		1997-02-04	abc@abc.com	0	\N				9632587410	\N	\N	Raviraja	f		\N	0	\N
00040218	1519838827327	0		0		f	0		2005-02-08		0	\N				8521479630	\N	\N	karthk	f		\N	0	\N
00050218	1519840391542	0		0		f	0		1997-02-05	abc@abc.com	0	\N				7415698230	\N	\N	Hariharan	f		\N	0	\N
00060218	1519840431859	0		0		f	0		1987-01-13	abc@abc.com	0	\N				9654781230	\N	\N	Swethamenon	f		\N	0	\N
00020218	1519629116681	1	\N	29	\N	f	2	\N	1989-01-15	\N	0	\N	\N	\N	\N	9600194696	\N	\N	pandian Babu	t	00020218	\N	0	\N
00020318	1520601751410	0		0		f	0		2003-03-03		0	\N				9600194696	\N	\N	jose	t		\N	0	\N
00010318	1520876674267	1		0		f	0		2008-03-04		0	\N				1478523690	\N	\N	jay	f		\N	0	\N
00010218	1520876674525	1	\N	0	\N	f	2	\N	1989-01-15	\N	0	\N	\N	\N	\N	9600194696	\N	\N	Murali Babu	f	\N	\N	0	\N
\.


--
-- Data for Name: patient_label; Type: TABLE DATA; Schema: public; Owner: cms
--

COPY patient_label (patient_id, label_id) FROM stdin;
00010318	284
00010218	284
\.


--
-- Data for Name: prescription; Type: TABLE DATA; Schema: public; Owner: cms
--

COPY prescription (id, lastmodifiedtime, version, date, doctor_id, patient_id) FROM stdin;
194	1520674497602	0	2018-03-10	10	00050218
199	1520674834418	0	2018-03-10	10	00050218
202	1520675698087	0	2018-03-10	140	00050218
204	1520675826769	0	2018-03-10	10	00050218
206	1520675943088	0	2018-03-10	140	00050218
211	1520676305019	0	2018-03-10	251	00010318
222	1520779234160	0	2018-03-11	11	00050218
232	1520788190204	0	2018-03-11	250	00050218
\.


--
-- Data for Name: prescription_entry; Type: TABLE DATA; Schema: public; Owner: cms
--

COPY prescription_entry (id, lastmodifiedtime, version, before_food_morning, before_food_night, before_food_noon, morning, night, no_of_days, noon, notes, unit_morning, unit_night, unit_noon, medication_id, prescription_id) FROM stdin;
203	1520675698133	0	f	f	f	0	0	0	0	fever	0	0	0	50	202
205	1520675826777	0	f	f	f	0	0	0	0	fever	0	0	0	50	204
207	1520675943088	0	f	f	f	0	0	0	0		0	0	0	50	206
210	1520676239433	0	f	f	f	0	0	0	0		2	2	2	198	199
212	1520676305019	0	f	f	f	0	0	0	0		0	0	0	50	211
223	1520779234176	0	f	f	f	0	0	0	0		2	2	2	221	222
224	1520779234196	0	f	f	f	0	0	0	0		0	0	0	50	222
229	1520788088135	0	f	f	f	0	0	0	0	qwer1234	0	0	0	50	194
234	1520788224764	0	f	f	f	0	0	0	0	aa1	0	0	0	50	232
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: cms
--

COPY role (id, lastmodifiedtime, version, role) FROM stdin;
130	1519269421453	0	ADMIN
131	1519269421577	0	USER
\.


--
-- Data for Name: settings; Type: TABLE DATA; Schema: public; Owner: cms
--

COPY settings (id, lastmodifiedtime, version, category) FROM stdin;
271	1520873202368	0	sms
283	1520873202587	0	clinic
\.


--
-- Data for Name: settings_params; Type: TABLE DATA; Schema: public; Owner: cms
--

COPY settings_params (id, lastmodifiedtime, version, param_name, param_value, settings_id) FROM stdin;
261	1520873202196	0	sms_global_switch	false	271
262	1520873202259	0	username	success	271
263	1520873202274	0	password	123456	271
264	1520873202274	0	sender	SPPURT	271
265	1520873202290	0	url	http://bhashsms.com/api/sendmsg.php	271
266	1520873202306	0	type	normal	271
267	1520873202321	0	priority	ndnd	271
268	1520873202321	0	sms_birthday_alarm	false	271
269	1520873202337	0	sms_periodic_reminder	false	271
270	1520873202352	0	sms_periodic_reminder_Days	3	271
272	1520873202431	0	clinicName	Sree Balaji Dental Clinic	283
273	1520873202431	0	addressLine1	No 14, 11th Street	283
274	1520873202493	0	addressLine2	Balaji Nagar	283
275	1520873202509	0	mobile	+91 99221 99221	283
276	1520873202509	0	area	Adambakkam	283
277	1520873202524	0	city	Chennai	283
278	1520873202524	0	state	Tamil Nadu	283
279	1520873202540	0	pincode	600088	283
280	1520873202540	0	email	drbalajiclinc@gmail.com	283
281	1520873202556	0	landline	+91 44 43559921	283
282	1520873202571	0	website	www.drbalajiclinc.com	283
\.


--
-- Data for Name: smsdetails; Type: TABLE DATA; Schema: public; Owner: cms
--

COPY smsdetails (id, lastmodifiedtime, version, contact_list, date, detail, failure_cause, name, retry_count, status, status_code) FROM stdin;
205	1520667893981	5	9600194696	1519629369220	Dear \r\nMurali Babu, \r\nYour appointment \r\nat Sree Balaji Dental Clinic, on Feb 23, 2018 8:39 AM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::00010218	0	FAILURE	0
240	1520667894295	5	9600194696	1519752408815	Hello Murali Babu, Sree Balaji Dental Clinic informing about the appointment reminder on 2018-03-06.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
280	1520667894613	5	9171415876	1519839226125	Dear \r\nDr T.Srinivasan, \r\nYour appointment \r\nwith pandian Babu \r\nat Sree Balaji Dental Clinic, on Mar 21, 2018 12:00 PM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::140	0	FAILURE	0
281	1520667894934	5	9600194696	1519839226169	Dear \r\npandian Babu, \r\nYour appointment \r\nat Sree Balaji Dental Clinic, on Mar 21, 2018 12:00 PM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::00020218	0	FAILURE	0
290	1520667895258	5	9600194696	1519879485454	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
291	1520667895574	5	9600194696	1519879500082	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
292	1520667895895	5	9600194696	1519879545087	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
293	1520667896214	5	9600194696	1519879560126	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
294	1520667896542	5	9600194696	1519879605077	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
295	1520667896870	5	9600194696	1519879620074	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
296	1520667897208	5	9600194696	1519879665083	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
297	1520667897501	5	9600194696	1519879680073	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
298	1520667897857	5	9600194696	1519879725064	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
299	1520667898097	5	9600194696	1519879740076	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
310	1520667898349	5	9600194696	1519879785057	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
311	1520667898622	5	9600194696	1519879800067	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
312	1520667898933	5	9600194696	1519879845085	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
313	1520667899166	5	9600194696	1519879860075	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
314	1520667899417	5	9600194696	1519879905078	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
315	1520667899737	5	9600194696	1519879920049	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
316	1520667899993	5	9600194696	1519879965067	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
317	1520667900382	5	9600194696	1519879980040	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
318	1520667900696	5	9600194696	1519880025061	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
319	1520667901010	5	9600194696	1519880040129	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
320	1520667901337	5	9600194696	1519880085075	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
321	1520667901655	5	9600194696	1519880100078	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
322	1520667901976	5	9600194696	1519880145074	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
323	1520667902294	5	9600194696	1519880160069	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
202	1520667902615	5	9171415876	1519629360673	Dear \r\nDr Kumara,\r\nYour appointment \r\nwith Murali Babu \r\nat Sree Balaji Dental Clinic, rescheduled on Mar 8, 2018 5:00 AM.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT RESCHEDULE::11	0	FAILURE	0
203	1520667902877	5	9600194696	1519629360693	Dear \r\nMurali Babu,\r\nYour appointment \r\nat Sree Balaji Dental Clinic, rescheduled on Mar 8, 2018 5:00 AM.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT RESCHEDULE::00010218	0	FAILURE	0
204	1520667893667	5	9171415876	1519629369167	Dear \r\nDr Kumara, \r\nYour appointment \r\nwith Murali Babu \r\nat Sree Balaji Dental Clinic, on Feb 23, 2018 8:39 AM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::11	0	FAILURE	0
329	1520667903576	5	9600194696	1519880340071	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
340	1520667903915	5	9600194696	1519880385067	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
341	1520667904197	5	9600194696	1519880400049	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
342	1520667904542	5	9600194696	1519880445045	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
343	1520667904858	5	9600194696	1519880460044	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
344	1520667905176	5	9600194696	1519880505053	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
345	1520667905496	5	9600194696	1519880520067	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
346	1520667905816	5	9600194696	1519880565082	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
347	1520667906134	5	9600194696	1519880580067	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
348	1520667906454	5	9600194696	1519880625068	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
349	1520667906776	5	9600194696	1519880640067	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
350	1520667907095	5	9600194696	1519880685067	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
351	1520667907417	5	9600194696	1519880700070	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
352	1520667907741	5	9600194696	1519880745055	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
353	1520667908060	5	9600194696	1519880760067	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
354	1520667908309	5	9600194696	1519880805059	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
355	1520667908704	5	9600194696	1519880820047	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
356	1520667909383	5	9600194696	1519880865111	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
358	1520667909616	5	9600194696	1519880878751	Dear \r\nMurali Babu, \r\nYou have an appointment \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 7, 2018 5:00 AM.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT::00010218	0	FAILURE	0
359	1520667909976	5	9600194696	1519880880106	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
360	1520667910323	5	7896541230	1519880899387	Dear \r\nDr Muruganatham, \r\nYour appointment \r\nwith Murali Babu \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 8, 2018 5:00 AM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::250	0	FAILURE	0
361	1520667910557	5	9600194696	1519880899403	Dear \r\nMurali Babu, \r\nYour appointment \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 8, 2018 5:00 AM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::00010218	0	FAILURE	0
362	1520667910798	5	9600194696	1519880925054	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
363	1520667911358	5	9600194696	1519880940125	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
364	1520667911608	5	9600194696	1519880985066	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
365	1520667911906	5	9600194696	1519881000116	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
366	1520667912215	5	9600194696	1519881045088	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
367	1520667912533	5	9600194696	1519881060048	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
325	1520667912854	5	9600194696	1519880220086	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
326	1520667913176	5	9600194696	1519880265065	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
327	1520667913494	5	9600194696	1519880280044	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
328	1520667903115	5	9600194696	1519880325107	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
403	1520667914134	5	9600194696	1519881240070	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
404	1520667914457	5	9600194696	1519881285058	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
405	1520667914776	5	9600194696	1519881300064	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
406	1520667915079	5	9600194696	1519881345047	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
407	1520667915422	5	9600194696	1519881360052	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
408	1520667915732	5	9600194696	1519881405046	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
409	1520667916056	5	9600194696	1519881420062	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
410	1520667916375	5	9600194696	1519881465062	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
420	1520667916694	5	9600194696	1519912606290	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
421	1520667917023	5	9600194696	1519912620156	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
422	1520667917336	5	9600194696	1519912665077	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
424	1520667917657	5	9600194696	1519912725070	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
425	1520667917974	5	9600194696	1519912740069	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
426	1520667918296	5	9600194696	1519912785071	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
427	1520667918621	5	9600194696	1519912800065	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
428	1520667918948	5	9600194696	1519912845065	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
429	1520667919256	5	9600194696	1519912860089	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
440	1520667919573	5	9600194696	1519912905106	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
441	1520667919893	5	9600194696	1519912920070	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
442	1520667920216	5	9600194696	1519912965061	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
443	1520667920534	5	9600194696	1519912980067	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
444	1520667920853	5	9600194696	1519913025071	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
445	1520667921172	5	9600194696	1519913040061	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
446	1520667921494	5	7896541230	1519913078371	Dear \r\nDr Muruganatham, \r\nYou have an appointment \r\nwith Murali Babu \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 22, 2018 4:00 PM.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT::250	0	FAILURE	0
447	1520667921843	5	9600194696	1519913078393	Dear \r\nMurali Babu, \r\nYou have an appointment \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 22, 2018 4:00 PM.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT::00010218	0	FAILURE	0
448	1520667922135	5	9600194696	1519913085053	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
449	1520667922456	5	9600194696	1519913100072	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
470	1520667922712	5	9600194696	1519913145060	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
471	1520667922958	5	9600194696	1519913160082	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
369	1520667923445	5	9600194696	1519881120116	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
400	1520667923735	5	9600194696	1519881165100	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
401	1520667923975	5	9600194696	1519881180058	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
402	1520667913814	5	9600194696	1519881225051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
477	1520667924697	5	9600194696	1519913280061	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
478	1520667924930	5	9600194696	1519913325048	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
479	1520667925156	5	9600194696	1519913340054	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
480	1520667925991	5	9600194696	1519913385056	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
481	1520667926302	5	9600194696	1519913400087	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
482	1520667926617	5	9600194696	1519913445049	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
483	1520667926934	5	9600194696	1519913460055	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
484	1520667927262	5	9600194696	1519913505056	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
485	1520667927574	5	9600194696	1519913520056	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
486	1520667927897	5	9600194696	1519913565053	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
487	1520667928215	5	9600194696	1519913580062	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
488	1520667928533	5	9600194696	1519913625101	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
489	1520667928859	5	9600194696	1519913640071	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
490	1520667929181	5	9600194696	1519913685051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
491	1520667929506	5	9600194696	1519913700068	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
492	1520667929826	5	9600194696	1519913745172	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
493	1520667930132	5	9600194696	1519913760064	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
494	1520667930454	5	9600194696	1519913805054	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
495	1520667930786	5	9600194696	1519913820056	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
496	1520667931096	5	9600194696	1519913865053	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
497	1520667931413	5	9600194696	1519913880064	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
498	1520667931742	5	9600194696	1519913925062	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
499	1520667932053	5	9600194696	1519913940057	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
500	1520667932375	5	9600194696	1519913985052	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
501	1520667932708	5	9600194696	1519914000060	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
503	1520667933022	5	9600194696	1519914060054	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
504	1520667933340	5	9600194696	1519914105110	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
505	1520667933653	5	9600194696	1519914120075	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
506	1520667933976	5	9600194696	1519914165051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
473	1520667934293	5	9600194696	1519913179756	Dear \r\nMurali Babu, \r\nYour appointment \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 2, 2018 5:00 AM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::00010218	0	FAILURE	0
474	1520667934613	5	9600194696	1519913205066	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
475	1520667934934	5	9600194696	1519913220054	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
476	1520667924373	5	9600194696	1519913265064	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
512	1520667935575	5	9600194696	1519914345043	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
513	1520667935899	5	9600194696	1519914360071	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
514	1520667936213	5	9600194696	1519914405050	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
515	1520667936541	5	9600194696	1519914420056	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
516	1520667936867	5	9600194696	1519914465059	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
517	1520667937175	5	9600194696	1519914480054	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
518	1520667937494	5	9600194696	1519914525063	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
519	1520667937814	5	9600194696	1519914540051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
520	1520667938229	5	9600194696	1519914585055	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
521	1520667938466	5	9600194696	1519914600129	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
522	1520667938774	5	9600194696	1519914645051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
523	1520667939094	5	9600194696	1519914660056	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
524	1520667939413	5	9600194696	1519914705049	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
525	1520667939749	5	9600194696	1519914720057	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
526	1520667940066	5	9600194696	1519914765073	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
527	1520667940382	5	9600194696	1519914780077	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
528	1520667940693	5	9600194696	1519914825054	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
529	1520667941017	5	9600194696	1519914840051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
530	1520667941336	5	9600194696	1519914885055	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
531	1520667941653	5	9600194696	1519914900062	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
532	1520667941973	5	9600194696	1519914945097	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
533	1520667942295	5	9600194696	1519914960058	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
534	1520667942616	5	9600194696	1519915005083	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
535	1520667942944	5	9600194696	1519915020047	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
536	1520667943260	5	9600194696	1519915065051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
537	1520667943576	5	9600194696	1519915080049	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
538	1520667943894	5	9600194696	1519915125049	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
539	1520667944216	5	9600194696	1519915140052	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
540	1520667944533	5	9600194696	1519915185073	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
541	1520667944772	5	9600194696	1519915200071	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
508	1520667945173	5	9600194696	1519914225065	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
509	1520667945495	5	9600194696	1519914240050	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
510	1520667945824	5	9600194696	1519914285091	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
511	1520667935259	5	9600194696	1519914300053	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
547	1520667946459	5	9600194696	1519915380052	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
548	1520667946779	5	9600194696	1519915425057	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
549	1520667947112	5	9600194696	1519915440049	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
550	1520667947416	5	9600194696	1519915485071	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
551	1520667947733	5	9600194696	1519915500072	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
552	1520667948053	5	9600194696	1519915545052	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
553	1520667948375	5	9600194696	1519915560057	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
554	1520667948696	5	9600194696	1519915605052	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
555	1520667949013	5	9600194696	1519915620060	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
556	1520667949332	5	9600194696	1519915665086	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
557	1520667949653	5	9600194696	1519915680049	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
558	1520667949988	5	9600194696	1519915725053	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
559	1520667950316	5	9600194696	1519915740053	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
560	1520667950620	5	9600194696	1519915785054	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
561	1520667950933	5	9600194696	1519915800052	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
562	1520667951255	5	9600194696	1519915845072	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
563	1520667951575	5	9600194696	1519915860058	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
564	1520667951893	5	9600194696	1519915905067	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
565	1520667952213	5	9600194696	1519915920077	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
566	1520667952533	5	9600194696	1519915965051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
567	1520667952857	5	9600194696	1519915980053	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
568	1520667953176	5	9600194696	1519916025051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
569	1520667953499	5	9600194696	1519916040128	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
570	1520667953821	5	9600194696	1519916085094	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
571	1520667954141	5	9600194696	1519916100071	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
572	1520667954461	5	9600194696	1519916145053	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
573	1520667954773	5	9600194696	1519916160057	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
574	1520667955094	5	9600194696	1519916205063	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
575	1520667955415	5	9600194696	1519916220050	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
576	1520667955736	5	9600194696	1519916265083	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
543	1520667956056	5	9600194696	1519915260059	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
544	1520667956373	5	9600194696	1519915305059	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
545	1520667956693	5	9600194696	1519915320064	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
546	1520667946133	5	9600194696	1519915365055	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
582	1520667957345	5	9600194696	1519916445068	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
583	1520667957661	5	9600194696	1519916460052	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
584	1520667958297	5	9600194696	1519916505073	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
585	1520667958614	5	9600194696	1519916520060	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
586	1520667958935	5	9600194696	1519916565063	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
587	1520667959255	5	9600194696	1519916580073	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
588	1520667959472	5	9600194696	1519916625053	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
589	1520667959900	5	9600194696	1519916640051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
590	1520667960221	5	9600194696	1519916685052	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
591	1520667960546	5	9600194696	1519916700051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
592	1520667960868	5	9600194696	1519916745049	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
593	1520667961182	5	9600194696	1519916760049	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
594	1520667961501	5	9600194696	1519916805052	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
595	1520667961814	5	9600194696	1519916820074	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
596	1520667962134	5	9600194696	1519916865052	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
597	1520667962452	5	9600194696	1519916880051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
598	1520667962774	5	9600194696	1519916925079	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
599	1520667963096	5	9600194696	1519916940075	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
600	1520667963415	5	9600194696	1519916985058	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
601	1520667963735	5	9600194696	1519917000049	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
602	1520667964054	5	9600194696	1519917045055	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
603	1520667964380	5	9600194696	1519917060051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
604	1520667964700	5	9600194696	1519917105050	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
605	1520667965023	5	9600194696	1519917120060	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
606	1520667965341	5	9600194696	1519917165085	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
607	1520667965568	5	9600194696	1519917180074	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
608	1520667965801	5	9600194696	1519917225051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
609	1520667966330	5	9600194696	1519917240052	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
610	1520667966632	5	9600194696	1519917285053	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
611	1520667966860	5	9600194696	1519917300050	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
578	1520667967254	5	9600194696	1519916325075	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
579	1520667967473	5	9600194696	1519916340049	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
580	1520667967681	5	9600194696	1519916385049	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
581	1520667957020	5	9600194696	1519916400067	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
617	1520667968173	5	9600194696	1519917480104	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
618	1520667968410	5	9600194696	1519917525054	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
619	1520667968648	5	9600194696	1519917540050	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
620	1520667968880	5	9600194696	1519917585072	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
621	1520667969174	5	9600194696	1519917600051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
622	1520667969479	5	9600194696	1519917645067	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
623	1520667969692	5	9600194696	1519917660070	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
624	1520667970143	5	9600194696	1519917705060	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
625	1520667970464	5	9600194696	1519917720051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
626	1520667970774	5	9600194696	1519917765052	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
627	1520667971093	5	9600194696	1519917780061	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
628	1520667971425	5	9600194696	1519917825059	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
629	1520667971750	5	9600194696	1519917840045	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
630	1520667972053	5	9600194696	1519917885086	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
631	1520667972373	5	9600194696	1519917900055	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
632	1520667972693	5	9600194696	1519917945055	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
633	1520667973015	5	9600194696	1519917960092	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
634	1520667973236	5	9600194696	1519918005057	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
635	1520667973654	5	9600194696	1519918020050	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
636	1520667973973	5	9600194696	1519918065051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
637	1520667974293	5	9600194696	1519918080062	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
638	1520667974615	5	9600194696	1519918125048	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
639	1520667974940	5	9600194696	1519918140075	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
640	1520667975253	5	9600194696	1519918185051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
641	1520667975574	5	9600194696	1519918200054	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
642	1520667975895	5	9600194696	1519918245057	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
643	1520667976215	5	9600194696	1519918260059	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
644	1520667976533	5	9600194696	1519918305056	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
645	1520667977173	5	9600194696	1519918320056	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
646	1520667977500	5	9600194696	1519918365057	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
613	1520667977743	5	9600194696	1519917360048	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
614	1520667978139	5	9600194696	1519917405053	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
615	1520667978460	5	9600194696	1519917420073	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
616	1520667967925	5	9600194696	1519917465101	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
652	1520667979097	5	9600194696	1519918545075	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
653	1520667979414	5	9600194696	1519918560054	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
654	1520667979732	5	9600194696	1519918605053	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
655	1520667980054	5	9600194696	1519918620081	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
656	1520667980373	5	9600194696	1519918665051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
657	1520667980699	5	9600194696	1519918680056	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
658	1520667981026	5	9600194696	1519918725090	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
659	1520667981342	5	9600194696	1519918740048	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
660	1520667981662	5	9600194696	1519918785047	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
661	1520667981986	5	9600194696	1519918800050	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
662	1520667982296	5	9600194696	1519918845048	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
663	1520667982611	5	9600194696	1519918860057	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
664	1520667982933	5	9600194696	1519918905047	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
665	1520667983253	5	9600194696	1519918920049	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
666	1520667983574	5	9600194696	1519918965052	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
667	1520667983895	5	9600194696	1519918980070	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
668	1520667984214	5	9600194696	1519919025081	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
669	1520667984540	5	9600194696	1519919040074	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
670	1520667985191	5	9600194696	1519919085051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
671	1520667985517	5	9600194696	1519919100055	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
672	1520667985816	5	9600194696	1519919145050	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
673	1520667986134	5	9600194696	1519919160056	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
674	1520667986453	5	9600194696	1519919205082	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
675	1520667986775	5	9600194696	1519919220044	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
676	1520667987098	5	9600194696	1519919265051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
677	1520667987413	5	9600194696	1519919280050	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
678	1520667987740	5	9600194696	1519919325054	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
679	1520667988046	5	9600194696	1519919340055	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
680	1520667988375	5	9600194696	1519919385066	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
681	1520667988609	5	9600194696	1519919400051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
648	1520667988850	5	9600194696	1519918425055	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
649	1520667989335	5	9600194696	1519918440054	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
650	1520667989585	5	9600194696	1519918485050	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
651	1520667978772	5	9600194696	1519918500056	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
687	1520667990602	5	9600194696	1519919580051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
688	1520667990930	5	9600194696	1519919625085	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
689	1520667991253	5	9600194696	1519919640058	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
690	1520667991566	5	9600194696	1519919685052	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
691	1520667991894	5	9600194696	1519919700095	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
692	1520667992207	5	9600194696	1519919745049	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
693	1520667992429	5	9600194696	1519919760050	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
694	1520667992851	5	9600194696	1519919805051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
695	1520667993070	5	9600194696	1519919820051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
696	1520667993288	5	9600194696	1519919865056	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
697	1520667993511	5	9600194696	1519919880064	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
698	1520667993776	5	9600194696	1519919925053	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
699	1520667994026	5	9600194696	1519919940052	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
700	1520667994276	5	9600194696	1519929017912	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
701	1520667994646	5	9632587410	1519929018355	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
702	1520667994865	5	8521479630	1519929018446	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
710	1520667995099	5	9600194696	1519999622429	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
711	1520667995334	5	9632587410	1519999622734	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
712	1520667995552	5	8521479630	1519999622852	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
713	1520667995791	5	9600194696	1519999665092	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
714	1520667996009	5	9632587410	1519999665216	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
715	1520667996228	5	8521479630	1519999665364	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
716	1520667996562	5	9600194696	1519999680095	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
717	1520667996784	5	9632587410	1519999680186	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
718	1520667997010	5	8521479630	1519999680273	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
719	1520667997228	5	9600194696	1519999725111	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
720	1520667997463	5	9632587410	1519999725181	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
721	1520667997682	5	8521479630	1519999725242	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
722	1520667997932	5	9600194696	1519999740153	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
723	1520667998158	5	9632587410	1519999740221	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
683	1520667998393	5	9600194696	1519919460055	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
684	1520667998612	5	9600194696	1519919505051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
685	1520667998893	5	9600194696	1519919520051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
686	1520667989985	5	9600194696	1519919565050	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
729	1520667999395	5	9632587410	1519999800268	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
740	1520667999645	5	8521479630	1519999800328	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
741	1520667999863	5	9600194696	1519999845161	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
742	1520668000210	5	9632587410	1519999845235	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
743	1520668000491	5	8521479630	1519999845293	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
745	1520668000710	5	8521479630	1519999857210	Dear \r\nkarthk, \r\nYour appointment \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 23, 2018 6:00 AM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::00040218	0	FAILURE	0
746	1520668001069	5	9600194696	1519999860131	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
747	1520668001309	5	9632587410	1519999860412	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
748	1520668001544	5	8521479630	1519999860492	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
749	1520668001778	5	9600194696	1519999905079	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
750	1520668001997	5	9632587410	1519999905142	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
751	1520668002216	5	8521479630	1519999905206	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
752	1520668002467	5	9600194696	1519999920139	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
753	1520668002685	5	9632587410	1519999920199	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
754	1520668002904	5	8521479630	1519999920266	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
755	1520668003154	5	9600194696	1519999965112	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
756	1520668003373	5	9632587410	1519999965211	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
757	1520668003730	5	8521479630	1519999965287	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
758	1520668003940	5	9600194696	1519999980066	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
759	1520668004175	5	9632587410	1519999980119	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
760	1520668004659	5	8521479630	1519999980166	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
761	1520668004893	5	9600194696	1520000025150	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
762	1520668005331	5	9632587410	1520000025203	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
763	1520668005549	5	8521479630	1520000025286	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
764	1520668005784	5	9600194696	1520000040070	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
765	1520668006015	5	9632587410	1520000040123	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
766	1520668006280	5	8521479630	1520000040179	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
767	1520668006608	5	9600194696	1520000085087	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
768	1520668006921	5	9632587410	1520000085156	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
725	1520668007150	5	9600194696	1519999785086	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
726	1520668007571	5	9632587410	1519999785147	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
727	1520668007884	5	8521479630	1519999785223	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
728	1520667999143	5	9600194696	1519999800202	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
774	1520668008522	5	9632587410	1520000145168	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
775	1520668008850	5	8521479630	1520000145248	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
776	1520668009069	5	9600194696	1520000160149	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
777	1520668009490	5	9632587410	1520000160207	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
778	1520668009818	5	8521479630	1520000160254	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
779	1520668010131	5	9600194696	1520000205074	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
780	1520668010459	5	9632587410	1520000205130	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
781	1520668010680	5	8521479630	1520000205223	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
782	1520668011087	5	9600194696	1520000220128	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
783	1520668011399	5	9632587410	1520000220208	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
784	1520668011730	5	8521479630	1520000220291	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
785	1520668012048	5	9600194696	1520000265177	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
786	1520668012360	5	9632587410	1520000265298	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
787	1520668012579	5	8521479630	1520000265386	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
788	1520668013010	5	9600194696	1520000280094	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
789	1520668013338	5	9632587410	1520000280153	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
790	1520668013745	5	8521479630	1520000280218	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
791	1520668013979	5	9600194696	1520000325099	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
792	1520668014291	5	9632587410	1520000325160	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
793	1520668014572	5	8521479630	1520000325232	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
794	1520668014931	5	9600194696	1520000340062	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
795	1520668015248	5	9632587410	1520000340126	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
796	1520668015570	5	8521479630	1520000340173	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
797	1520668015882	5	9600194696	1520000385072	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
798	1520668016117	5	9632587410	1520000385134	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
799	1520668016335	5	8521479630	1520000385253	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
800	1520668016850	5	9600194696	1520000400073	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
801	1520668017172	5	9632587410	1520000400126	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
802	1520668017484	5	8521479630	1520000400173	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
803	1520668017870	5	9600194696	1520000445071	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
770	1520668018135	5	9600194696	1520000100102	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
771	1520668018448	5	9632587410	1520000100232	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
772	1520668018776	5	8521479630	1520000100303	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
773	1520668008209	5	9600194696	1520000145090	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
815	1520668022304	5	9600194696	1520000565158	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
819	1520668022601	5	9632587410	1520000580199	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
820	1520668022930	5	8521479630	1520000580280	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
821	1520668023247	5	9600194696	1520000625100	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
822	1520668023575	5	9632587410	1520000625169	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
811	1520668023887	5	8521479630	1520000505254	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
823	1520668024210	5	8521479630	1520000625239	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
824	1520668024524	5	9600194696	1520000640108	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
825	1520668024852	5	9632587410	1520000640221	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
826	1520668025164	5	8521479630	1520000640319	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
809	1520668025490	5	9600194696	1520000505137	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
816	1520668026115	5	9632587410	1520000565276	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
817	1520668026459	5	8521479630	1520000565368	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
818	1520668026770	5	9600194696	1520000580104	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
804	1520668019376	5	9632587410	1520000445120	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
805	1520668019719	5	8521479630	1520000445184	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
806	1520668019938	5	9600194696	1520000460115	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
807	1520668020656	5	9632587410	1520000460174	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
808	1520668020875	5	8521479630	1520000460240	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
810	1520668021093	5	9632587410	1520000505195	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
812	1520668021328	5	9600194696	1520000520075	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
813	1520668021640	5	9632587410	1520000520123	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
814	1520668021970	5	8521479630	1520000520172	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
832	1520668027411	5	8521479630	1520000700247	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
833	1520668027723	5	9600194696	1520000745182	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
834	1520668027944	5	9632587410	1520000745252	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
835	1520668028366	5	8521479630	1520000745330	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
836	1520668028679	5	9600194696	1520000760100	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
837	1520668029010	5	9632587410	1520000760170	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
838	1520668029228	5	8521479630	1520000760236	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
839	1520668029494	5	9600194696	1520000805120	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
840	1520668029713	5	9632587410	1520000805197	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
841	1520668029916	5	8521479630	1520000805260	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
842	1520668030148	5	9600194696	1520000820148	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
843	1520668030351	5	9632587410	1520000820232	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
844	1520668030586	5	8521479630	1520000820378	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
845	1520668030820	5	9600194696	1520000865132	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
846	1520668031252	5	9632587410	1520000865276	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
847	1520668031565	5	8521479630	1520000865350	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
848	1520668031893	5	9600194696	1520000880079	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
849	1520668032221	5	9632587410	1520000880142	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
850	1520668032530	5	8521479630	1520000880220	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
851	1520668032749	5	9600194696	1520000925167	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
852	1520668032967	5	9632587410	1520000925239	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
853	1520668033171	5	8521479630	1520000925323	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
854	1520668033483	5	9600194696	1520000940152	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
855	1520668033717	5	9632587410	1520000940272	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
856	1520668033925	5	8521479630	1520000940380	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
857	1520668034472	5	9600194696	1520000985197	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
858	1520668034708	5	9632587410	1520000985301	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
859	1520668035413	5	8521479630	1520000985381	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
860	1520668040956	5	9600194696	1520001000195	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
861	1520668041269	5	9632587410	1520001000267	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
828	1520668041503	5	9632587410	1520000685229	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
829	1520668041800	5	8521479630	1520000685304	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
830	1520668042024	5	9600194696	1520000700083	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
831	1520668027004	5	9632587410	1520000700147	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
867	1520668042774	5	9632587410	1520001060164	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
868	1520668043089	5	8521479630	1520001060211	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
869	1520668043417	5	9600194696	1520001105124	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
870	1520668043730	5	9632587410	1520001105196	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
871	1520668043933	5	8521479630	1520001105266	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
872	1520668044165	5	9600194696	1520001120085	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
873	1520668044368	5	9632587410	1520001120147	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
874	1520668044681	5	8521479630	1520001120241	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
875	1520668044915	5	9600194696	1520001165109	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
876	1520668045329	5	9632587410	1520001165203	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
877	1520668045642	5	8521479630	1520001165328	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
878	1520668045861	5	9600194696	1520001180076	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
879	1520668046079	5	9632587410	1520001180155	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
880	1520668046610	5	8521479630	1520001180329	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
881	1520668046829	5	9600194696	1520001225093	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
882	1520668047048	5	9632587410	1520001225156	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
883	1520668047314	5	8521479630	1520001225218	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
884	1520668047648	5	9600194696	1520001240120	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
885	1520668047898	5	9632587410	1520001240198	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
886	1520668048116	5	8521479630	1520001240305	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
887	1520668048351	5	9600194696	1520001285125	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
888	1520668048850	5	9632587410	1520001285188	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
889	1520668049069	5	8521479630	1520001285250	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
890	1520668049288	5	9600194696	1520001300112	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
891	1520668049492	5	9632587410	1520001300175	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
892	1520668049710	5	8521479630	1520001300253	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
893	1520668050129	5	9600194696	1520001345103	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
894	1520668050442	5	9632587410	1520001345181	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
895	1520668050770	5	8521479630	1520001345274	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
896	1520668051082	5	9600194696	1520001360113	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
863	1520668051305	5	9600194696	1520001045095	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
864	1520668051727	5	9632587410	1520001045220	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
865	1520668052055	5	8521479630	1520001045298	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
866	1520668042446	5	9600194696	1520001060102	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
902	1520668052682	5	9600194696	1520001420098	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
903	1520668052900	5	9632587410	1520001420144	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
904	1520668053291	5	8521479630	1520001420207	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
905	1520668053649	5	9600194696	1520001465077	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
906	1520668053977	5	9632587410	1520001465155	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
907	1520668054290	5	8521479630	1520001465280	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
908	1520668054601	5	9600194696	1520001480115	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
909	1520668054915	5	9632587410	1520001480206	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
910	1520668055243	5	8521479630	1520001480318	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
912	1520668055639	5	9654781230	1520001513528	Dear \r\nSwethamenon, \r\nYour appointment \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 17, 2018 4:00 AM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::00060218	0	FAILURE	0
913	1520668055889	5	9600194696	1520001525119	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
914	1520668056209	5	9632587410	1520001525202	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
915	1520668056428	5	8521479630	1520001525278	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
916	1520668056850	5	9701752863	1520001527938	Dear \r\nDr Kavita, \r\nYour appointment \r\nwith Hariharan \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 30, 2018 4:00 AM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::330	0	FAILURE	0
917	1520668057162	5	7415698230	1520001527967	Dear \r\nHariharan, \r\nYour appointment \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 30, 2018 4:00 AM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::00050218	0	FAILURE	0
918	1520668057489	5	9600194696	1520001540110	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
919	1520668057708	5	9632587410	1520001540189	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
920	1520668058132	5	8521479630	1520001540251	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
921	1520668058450	5	9600194696	1520001585112	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
922	1520668058734	5	9632587410	1520001585186	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
923	1520668059093	5	8521479630	1520001585262	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
924	1520668059421	5	9600194696	1520001600096	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
925	1520668059737	5	9632587410	1520001600162	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
926	1520668060050	5	8521479630	1520001600250	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
927	1520668060253	5	9600194696	1520001645124	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
928	1520668060680	5	9632587410	1520001645186	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
929	1520668061009	5	8521479630	1520001645241	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
930	1520668061228	5	9600194696	1520001660115	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
898	1520668061493	5	8521479630	1520001360259	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
899	1520668062075	5	9600194696	1520001405120	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
900	1520668062294	5	9632587410	1520001405182	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
901	1520668052369	5	8521479630	1520001405245	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
936	1520668062762	5	9600194696	1520001720092	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
937	1520668063250	5	9632587410	1520001720155	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
938	1520668063578	5	8521479630	1520001720233	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
939	1520668063816	5	9600194696	1520001765094	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
940	1520668064035	5	9632587410	1520001765219	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
941	1520668064256	5	8521479630	1520001765281	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
942	1520668064530	5	9600194696	1520001780087	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
943	1520668064738	5	9632587410	1520001780181	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
944	1520668065114	5	8521479630	1520001780228	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
945	1520668065320	5	9600194696	1520001825117	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
946	1520668065544	5	9632587410	1520001825164	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
947	1520668065752	5	8521479630	1520001825226	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
948	1520668065966	5	9600194696	1520001840107	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
949	1520668066194	5	9632587410	1520001840170	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
950	1520668066459	5	8521479630	1520001840258	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
951	1520668067129	5	9600194696	1520001885100	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
952	1520668067412	5	9632587410	1520001885163	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
953	1520668067641	5	8521479630	1520001885241	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
954	1520668067864	5	9600194696	1520001900121	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
955	1520668068336	5	9632587410	1520001900179	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
956	1520668068564	5	8521479630	1520001900241	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
957	1520668069004	5	9600194696	1520001945094	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
958	1520668069237	5	9632587410	1520001945172	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
959	1520668069448	5	8521479630	1520001945250	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
960	1520668069665	5	9600194696	1520001960138	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
961	1520668069886	5	9632587410	1520001960205	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
962	1520668070289	5	8521479630	1520001960286	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
963	1520668070503	5	9600194696	1520002005101	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
964	1520668070959	5	9632587410	1520002005168	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
965	1520668071188	5	8521479630	1520002005322	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
932	1520668071574	5	8521479630	1520001660254	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
933	1520668071855	5	9600194696	1520001705105	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
934	1520668072210	5	9632587410	1520001705152	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
935	1520668062512	5	8521479630	1520001705232	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
971	1520668072858	5	8521479630	1520002065275	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
972	1520668073107	5	9600194696	1520002080121	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
973	1520668073489	5	9632587410	1520002080183	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
974	1520668073809	5	8521479630	1520002080253	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
975	1520668074137	5	9600194696	1520002125095	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
976	1520668074481	5	9632587410	1520002125176	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
977	1520668074778	5	8521479630	1520002125247	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
978	1520668075082	5	9600194696	1520002140159	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
979	1520668075413	5	9632587410	1520002140222	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
980	1520668075736	5	8521479630	1520002140293	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
981	1520668076051	5	9600194696	1520002185147	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
982	1520668076285	5	9632587410	1520002185216	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
983	1520668076521	5	8521479630	1520002185281	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
984	1520668076761	5	9600194696	1520002200103	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
985	1520668077007	5	9632587410	1520002200167	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
986	1520668077242	5	8521479630	1520002200239	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
987	1520668077483	5	9600194696	1520002245092	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
988	1520668077719	5	9632587410	1520002245165	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
989	1520668077954	5	8521479630	1520002245243	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
990	1520668078189	5	9600194696	1520002260088	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
991	1520668078424	5	9632587410	1520002260151	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
992	1520668078663	5	8521479630	1520002260234	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
993	1520668078894	5	9600194696	1520002305091	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
994	1520668079248	5	9632587410	1520002305154	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
995	1520668079784	5	8521479630	1520002305221	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
996	1520668080026	5	9600194696	1520002320114	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
997	1520668080264	5	9632587410	1520002320193	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
998	1520668080521	5	8521479630	1520002320248	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
999	1520668080851	5	9600194696	1520002365087	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1000	1520668081169	5	9632587410	1520002365203	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
967	1520668081513	5	9632587410	1520002020155	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
968	1520668081816	5	8521479630	1520002020228	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
969	1520668082146	5	9600194696	1520002065101	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
970	1520668072537	5	9632587410	1520002065210	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1006	1520668082809	5	9632587410	1520002425204	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1007	1520668083097	5	8521479630	1520002425337	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1008	1520668083416	5	9600194696	1520002440126	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1009	1520668083734	5	9632587410	1520002440205	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1010	1520668084052	5	8521479630	1520002440295	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1011	1520668084369	5	9600194696	1520002485098	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1012	1520668084690	5	9632587410	1520002485164	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1013	1520668085015	5	8521479630	1520002485230	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1014	1520668085343	5	9600194696	1520002500085	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1015	1520668085640	5	9632587410	1520002500144	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1016	1520668085968	5	8521479630	1520002500201	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1017	1520668086297	5	9600194696	1520002545135	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1018	1520668086609	5	9632587410	1520002545213	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1019	1520668086930	5	8521479630	1520002545297	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1020	1520668087249	5	9600194696	1520002560147	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1021	1520668087577	5	9632587410	1520002560214	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1022	1520668087890	5	8521479630	1520002560281	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1023	1520668088202	5	9600194696	1520002605102	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1024	1520668088441	5	9632587410	1520002605167	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1025	1520668088847	5	8521479630	1520002605229	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1026	1520668089097	5	9600194696	1520002620099	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1027	1520668089563	5	9632587410	1520002620179	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1028	1520668089829	5	8521479630	1520002620332	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1029	1520668090126	5	9600194696	1520002665090	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1050	1520668090438	5	9632587410	1520002665205	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1051	1520668090673	5	8521479630	1520002665289	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1052	1520668090913	5	9600194696	1520002680121	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1053	1520668091148	5	9632587410	1520002680225	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1054	1520668091366	5	8521479630	1520002680297	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1055	1520668091601	5	9600194696	1520002725173	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1002	1520668092050	5	9600194696	1520002380094	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1003	1520668092362	5	9632587410	1520002380170	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1004	1520668092690	5	8521479630	1520002380232	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1005	1520668082472	5	9600194696	1520002425096	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1061	1520668093640	5	9600194696	1520002785096	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1062	1520668093890	5	9632587410	1520002785160	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1063	1520668094125	5	8521479630	1520002785227	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1064	1520668094609	5	9600194696	1520002800115	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1065	1520668094950	5	9632587410	1520002800195	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1066	1520668095262	5	8521479630	1520002800265	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1067	1520668095559	5	9600194696	1520002845094	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1068	1520668095890	5	9632587410	1520002845181	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1069	1520668096218	5	8521479630	1520002845246	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1070	1520668096547	5	9600194696	1520002860145	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1071	1520668096900	5	9632587410	1520002860209	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1072	1520668097166	5	8521479630	1520002860266	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1073	1520668097479	5	9600194696	1520002905092	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1074	1520668097807	5	9632587410	1520002905153	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1075	1520668098129	5	8521479630	1520002905220	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1076	1520668098442	5	9600194696	1520002920085	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1077	1520668098770	5	9632587410	1520002920171	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1078	1520668099085	5	8521479630	1520002920234	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1079	1520668099409	5	9600194696	1520002965090	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1080	1520668099722	5	9632587410	1520002965219	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1081	1520668100050	5	8521479630	1520002965302	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1082	1520668100378	5	9600194696	1520002980091	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1083	1520668100689	5	9632587410	1520002980158	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1084	1520668101017	5	8521479630	1520002980220	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1085	1520668101283	5	9600194696	1520003025087	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1086	1520668101642	5	9632587410	1520003025154	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1087	1520668102289	5	8521479630	1520003025219	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1088	1520668102601	5	9600194696	1520003040111	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1089	1520668102929	5	9632587410	1520003040192	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1090	1520668103242	5	8521479630	1520003040289	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1057	1520668103569	5	8521479630	1520002725332	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1058	1520668103898	5	9600194696	1520002740088	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1059	1520668104216	5	9632587410	1520002740196	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1060	1520668093312	5	8521479630	1520002740298	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1096	1520668104833	5	8521479630	1520003100248	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1097	1520668105161	5	9600194696	1520003145070	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1098	1520668105490	5	9632587410	1520003145134	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1099	1520668105802	5	8521479630	1520003145180	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1100	1520668106128	5	9600194696	1520003160070	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1101	1520668106441	5	9632587410	1520003160151	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1102	1520668106769	5	8521479630	1520003160207	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1103	1520668107097	5	9600194696	1520003205066	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1104	1520668107425	5	9632587410	1520003205107	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1105	1520668107738	5	8521479630	1520003205157	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1106	1520668108050	5	9600194696	1520003220063	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1107	1520668108361	5	9632587410	1520003220110	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1108	1520668108597	5	8521479630	1520003220157	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1109	1520668108941	5	9600194696	1520003265095	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1110	1520668109175	5	9632587410	1520003265173	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1111	1520668109394	5	8521479630	1520003265220	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1112	1520668109633	5	9600194696	1520003280089	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1113	1520668110164	5	9632587410	1520003280136	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1114	1520668110492	5	8521479630	1520003280198	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1115	1520668110929	5	9600194696	1520003325063	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1116	1520668111241	5	9632587410	1520003325110	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1117	1520668111569	5	8521479630	1520003325172	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1118	1520668111897	5	9600194696	1520003340099	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1119	1520668112208	5	9632587410	1520003340146	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1120	1520668112520	5	8521479630	1520003340193	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1121	1520668112911	5	9600194696	1520003385077	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1122	1520668113259	5	9632587410	1520003385124	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1123	1520668113777	5	8521479630	1520003385171	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1124	1520668114027	5	9600194696	1520003400088	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1125	1520668114449	5	9632587410	1520003400135	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1092	1520668114777	5	9632587410	1520003085161	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1093	1520668115089	5	8521479630	1520003085232	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1094	1520668115402	5	9600194696	1520003100116	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1095	1520668104529	5	9632587410	1520003100179	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1131	1520668116041	5	9632587410	1520003460147	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1132	1520668116369	5	8521479630	1520003460179	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1133	1520668116604	5	9600194696	1520003505080	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1134	1520668117009	5	9632587410	1520003505111	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1135	1520668117331	5	8521479630	1520003505205	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1136	1520668117648	5	9600194696	1520003520082	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1137	1520668117992	5	9632587410	1520003520129	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1138	1520668118288	5	8521479630	1520003520160	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1139	1520668118499	5	9600194696	1520003565092	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1140	1520668118921	5	9632587410	1520003565139	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1141	1520668119249	5	8521479630	1520003565217	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1142	1520668119570	5	9600194696	1520003580075	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1143	1520668119882	5	9632587410	1520003580121	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1144	1520668120210	5	8521479630	1520003580168	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1145	1520668120523	5	9600194696	1520003625079	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1146	1520668121149	5	9632587410	1520003625126	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1147	1520668121477	5	8521479630	1520003625173	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1148	1520668121805	5	9600194696	1520003640144	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1149	1520668122102	5	9632587410	1520003640222	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1150	1520668122448	5	8521479630	1520003640301	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1151	1520668122667	5	9600194696	1520003685087	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1152	1520668123089	5	9632587410	1520003685134	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1153	1520668123401	5	8521479630	1520003685181	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1154	1520668124097	5	9600194696	1520003700077	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1155	1520668124362	5	9632587410	1520003700155	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1156	1520668124690	5	8521479630	1520003700211	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1157	1520668125003	5	9600194696	1520003745062	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1158	1520668125329	5	9632587410	1520003745156	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1159	1520668125641	5	8521479630	1520003745187	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1160	1520668125970	5	9600194696	1520003760113	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1127	1520668126282	5	9600194696	1520003445062	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1128	1520668126612	5	9632587410	1520003445109	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1129	1520668126924	5	8521479630	1520003445187	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1130	1520668115729	5	9600194696	1520003460085	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1166	1520668127518	5	9600194696	1520003820081	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1167	1520668127889	5	9632587410	1520003820138	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1168	1520668128202	5	8521479630	1520003820169	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1169	1520668128530	5	9600194696	1520003865121	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1170	1520668128845	5	9632587410	1520003865166	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1171	1520668129069	5	8521479630	1520003865213	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1172	1520668129491	5	9600194696	1520003880108	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1173	1520668129725	5	9632587410	1520003880155	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1174	1520668130142	5	8521479630	1520003880233	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1175	1520668130459	5	9600194696	1520003925086	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1176	1520668130771	5	9632587410	1520003925117	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1177	1520668131091	5	8521479630	1520003925164	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1178	1520668131410	5	9600194696	1520003940083	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1179	1520668131680	5	9632587410	1520003940114	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1180	1520668132057	5	8521479630	1520003940192	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1181	1520668132372	5	9600194696	1520003985129	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1182	1520668132692	5	9632587410	1520003985191	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1183	1520668133008	5	8521479630	1520003985238	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1184	1520668133334	5	9600194696	1520004000090	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1185	1520668133641	5	9632587410	1520004000153	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1186	1520668133975	5	8521479630	1520004000215	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1187	1520668134204	5	9600194696	1520004045085	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1188	1520668134619	5	9632587410	1520004045132	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1189	1520668134864	5	8521479630	1520004045179	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1190	1520668135618	5	9600194696	1520004060073	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1191	1520668135943	5	9632587410	1520004060178	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1192	1520668136212	5	8521479630	1520004060215	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1193	1520668136536	5	9600194696	1520004105089	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1194	1520668136849	5	9632587410	1520004105135	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1195	1520668137094	5	8521479630	1520004105200	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1162	1520668137517	5	8521479630	1520003760238	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1163	1520668137812	5	9600194696	1520003805056	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1164	1520668138136	5	9632587410	1520003805118	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1165	1520668127252	5	8521479630	1520003805149	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1201	1520668138767	5	8521479630	1520004165167	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1202	1520668139078	5	9600194696	1520004180111	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1203	1520668139408	5	9632587410	1520004180142	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1204	1520668139736	5	8521479630	1520004180189	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1205	1520668140068	5	9600194696	1520004225060	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1206	1520668140365	5	9632587410	1520004225107	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1207	1520668140688	5	8521479630	1520004225200	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1208	1520668140985	5	9600194696	1520004240054	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1209	1520668141329	5	9632587410	1520004240133	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1210	1520668141646	5	8521479630	1520004240179	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1211	1520668141968	5	9600194696	1520004285094	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1212	1520668142292	5	9632587410	1520004285141	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1213	1520668142539	5	8521479630	1520004285172	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1214	1520668142868	5	9600194696	1520004300064	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1215	1520668143109	5	9632587410	1520004300110	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1216	1520668143369	5	8521479630	1520004300235	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1217	1520668143889	5	9600194696	1520004345084	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1218	1520668144161	5	9632587410	1520004345131	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1219	1520668144523	5	8521479630	1520004345178	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1220	1520668144849	5	9600194696	1520004360064	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1221	1520668145083	5	9632587410	1520004360111	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1222	1520668145490	5	8521479630	1520004360158	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1223	1520668145809	5	9600194696	1520004405065	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1224	1520668146142	5	9632587410	1520004405112	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1225	1520668146451	5	8521479630	1520004405174	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1226	1520668146770	5	9600194696	1520004420082	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1227	1520668147093	5	9632587410	1520004420129	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1228	1520668147418	5	8521479630	1520004420176	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1229	1520668147735	5	9600194696	1520004465093	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1230	1520668148059	5	9632587410	1520004465187	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1197	1520668148392	5	9632587410	1520004120163	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1198	1520668148684	5	8521479630	1520004120233	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1199	1520668149011	5	9600194696	1520004165073	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1200	1520668138454	5	9632587410	1520004165120	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1236	1520668149648	5	9632587410	1520004525176	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1237	1520668149977	5	8521479630	1520004525223	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1238	1520668150293	5	9600194696	1520004540080	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1239	1520668150608	5	9632587410	1520004540112	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1240	1520668150930	5	8521479630	1520004540158	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1241	1520668151250	5	9600194696	1520004585075	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1242	1520668151594	5	9632587410	1520004585138	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1243	1520668151891	5	8521479630	1520004585185	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1244	1520668152208	5	9600194696	1520004600079	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1245	1520668152546	5	9632587410	1520004600126	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1246	1520668152858	5	8521479630	1520004600173	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1247	1520668153171	5	9600194696	1520004645074	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1248	1520668153403	5	9632587410	1520004645134	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1249	1520668153684	5	8521479630	1520004645173	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1250	1520668153934	5	9600194696	1520004660062	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1251	1520668154439	5	9632587410	1520004660126	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1252	1520668154673	5	8521479630	1520004660174	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1253	1520668155082	5	9600194696	1520004705087	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1254	1520668155332	5	9632587410	1520004705134	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1255	1520668155573	5	8521479630	1520004705179	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1256	1520668156167	5	9600194696	1520004720082	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1257	1520668156417	5	9632587410	1520004720126	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1258	1520668156688	5	8521479630	1520004720187	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1259	1520668156922	5	9600194696	1520004765067	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1260	1520668157328	5	9632587410	1520004765173	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1261	1520668157641	5	8521479630	1520004765218	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1262	1520668157970	5	9600194696	1520004780115	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1263	1520668158283	5	9632587410	1520004780155	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1264	1520668158611	5	8521479630	1520004780198	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1265	1520668158923	5	9600194696	1520004825075	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1232	1520668159249	5	9600194696	1520004480080	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1233	1520668159842	5	9632587410	1520004480111	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1234	1520668160217	5	8521479630	1520004480158	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1235	1520668149333	5	9600194696	1520004525129	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1271	1520668160856	5	9600194696	1520004885066	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1272	1520668161169	5	9632587410	1520004885130	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1273	1520668161497	5	8521479630	1520004885185	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1274	1520668161818	5	9600194696	1520004900060	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1275	1520668168399	5	9632587410	1520004900103	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1276	1520668168852	5	8521479630	1520004900157	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1277	1520668169165	5	9600194696	1520004945062	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1278	1520668169401	5	9632587410	1520004945106	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1279	1520668169687	5	8521479630	1520004945190	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1280	1520668169937	5	9600194696	1520004960069	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1281	1520668170519	5	9632587410	1520004960127	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1282	1520668170773	5	8521479630	1520004960186	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1283	1520668171085	5	9600194696	1520005005060	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1284	1520668171413	5	9632587410	1520005005105	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1285	1520668171731	5	8521479630	1520005005151	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1286	1520668172059	5	9600194696	1520005020069	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1287	1520668172371	5	9632587410	1520005020132	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1288	1520668172684	5	8521479630	1520005020174	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1289	1520668173010	5	9600194696	1520005065069	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1290	1520668173323	5	9632587410	1520005065166	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1291	1520668173651	5	8521479630	1520005065212	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1292	1520668173964	5	9600194696	1520005080069	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1293	1520668174287	5	9632587410	1520005080126	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1294	1520668174600	5	8521479630	1520005080173	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1295	1520668174928	5	9600194696	1520005125076	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1296	1520668175308	5	9632587410	1520005125118	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1297	1520668175573	5	8521479630	1520005125159	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1298	1520668175889	5	9600194696	1520005140081	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1299	1520668176217	5	9632587410	1520005140171	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1300	1520668176470	5	8521479630	1520005140227	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1267	1520668176845	5	8521479630	1520004825199	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1268	1520668177173	5	9600194696	1520004840069	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1269	1520668177489	5	9632587410	1520004840120	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1270	1520668160528	5	8521479630	1520004840170	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1306	1520668178021	5	8521479630	1520005200175	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1307	1520668178427	5	9600194696	1520005245109	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1308	1520668178770	5	9632587410	1520005245234	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1309	1520668178989	5	8521479630	1520005245313	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1310	1520668179239	5	9600194696	1520005260090	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1311	1520668179723	5	9632587410	1520005260149	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1312	1520668180048	5	8521479630	1520005260237	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1313	1520668180361	5	9600194696	1520005305099	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1314	1520668180698	5	9632587410	1520005305146	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1315	1520668180919	5	8521479630	1520005305177	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1316	1520668181328	5	9600194696	1520005320056	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1317	1520668181656	5	9632587410	1520005320118	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1318	1520668181968	5	8521479630	1520005320181	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1319	1520668182281	5	9600194696	1520005365068	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1320	1520668182613	5	9632587410	1520005365130	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1321	1520668182925	5	8521479630	1520005365177	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1322	1520668183253	5	9600194696	1520005380072	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1323	1520668183566	5	9632587410	1520005380119	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1324	1520668183798	5	8521479630	1520005380166	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1325	1520668184205	5	9600194696	1520005425066	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1326	1520668184533	5	9632587410	1520005425129	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1327	1520668184751	5	8521479630	1520005425176	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1328	1520668185167	5	9600194696	1520005440059	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1329	1520668185480	5	9632587410	1520005440117	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1330	1520668185808	5	8521479630	1520005440164	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1331	1520668186136	5	9600194696	1520005485084	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1332	1520668186448	5	9632587410	1520005485141	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1333	1520668186776	5	8521479630	1520005485175	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1334	1520668187088	5	9600194696	1520005500071	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1335	1520668187416	5	9632587410	1520005500134	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1302	1520668187728	5	9632587410	1520005185115	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1303	1520668188041	5	8521479630	1520005185165	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1304	1520668188369	5	9600194696	1520005200068	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1305	1520668177802	5	9632587410	1520005200114	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1341	1520668189007	5	9632587410	1520005560133	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1342	1520668189241	5	8521479630	1520005560178	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1343	1520668189648	5	9600194696	1520005605082	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1344	1520668189960	5	9632587410	1520005605136	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1345	1520668190303	5	8521479630	1520005605183	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1346	1520668190616	5	9600194696	1520005620075	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1347	1520668190928	5	9632587410	1520005620104	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1348	1520668191256	5	8521479630	1520005620189	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1349	1520668191568	5	9600194696	1520005665067	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1350	1520668191897	5	9632587410	1520005665109	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1351	1520668192209	5	8521479630	1520005665160	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1352	1520668192522	5	9600194696	1520005680104	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1353	1520668192847	5	9632587410	1520005680213	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1354	1520668193175	5	8521479630	1520005680260	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1355	1520668193488	5	9600194696	1520005725079	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1356	1520668193800	5	9632587410	1520005725126	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1357	1520668194127	5	8521479630	1520005725188	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1358	1520668194440	5	9600194696	1520005740080	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1359	1520668194768	5	9632587410	1520005740118	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1360	1520668195080	5	8521479630	1520005740180	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1361	1520668195408	5	9600194696	1520005785057	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1362	1520668195729	5	9632587410	1520005785120	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1363	1520668196057	5	8521479630	1520005785169	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1364	1520668196369	5	9600194696	1520005800099	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1365	1520668196688	5	9632587410	1520005800209	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1366	1520668197000	5	8521479630	1520005800256	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1367	1520668197328	5	9600194696	1520005845111	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1368	1520668197641	5	9632587410	1520005845143	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1369	1520668197967	5	8521479630	1520005845190	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1370	1520668198279	5	9600194696	1520005860105	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1337	1520668198607	5	9600194696	1520005545082	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1338	1520668198920	5	9632587410	1520005545129	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1339	1520668199157	5	8521479630	1520005545239	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1340	1520668188619	5	9600194696	1520005560073	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1376	1520668199599	5	9600194696	1520005920070	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1377	1520668199833	5	9632587410	1520005920116	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1378	1520668200527	5	8521479630	1520005920148	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1379	1520668200856	5	9600194696	1520005965099	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1380	1520668201090	5	9632587410	1520005965161	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1381	1520668201312	5	8521479630	1520005965230	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1382	1520668201925	5	9600194696	1520005980101	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1383	1520668202175	5	9632587410	1520005980133	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1384	1520668202394	5	8521479630	1520005980195	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1385	1520668202769	5	9600194696	1520006025074	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1386	1520668202994	5	9632587410	1520006025136	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1387	1520668203400	5	8521479630	1520006025168	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1388	1520668203665	5	9600194696	1520006040113	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1389	1520668203915	5	9632587410	1520006040176	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1390	1520668204367	5	8521479630	1520006040223	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1391	1520668204586	5	9600194696	1520006085078	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1392	1520668204836	5	9632587410	1520006085125	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1393	1520668205320	5	8521479630	1520006085171	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1394	1520668205555	5	9600194696	1520006100071	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1395	1520668205789	5	9632587410	1520006100118	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1396	1520668206281	5	8521479630	1520006100165	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1397	1520668206602	5	9600194696	1520006145078	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1398	1520668206930	5	9632587410	1520006145124	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1399	1520668207295	5	8521479630	1520006145171	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1400	1520668207513	5	9600194696	1520006160086	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1401	1520668207887	5	9632587410	1520006160133	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1402	1520668208215	5	8521479630	1520006160196	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1403	1520668208418	5	9600194696	1520006205082	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1404	1520668208668	5	9632587410	1520006205114	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1405	1520668209167	5	8521479630	1520006205176	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1372	1520668209401	5	8521479630	1520005860217	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1373	1520668209667	5	9600194696	1520005905172	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1374	1520668210135	5	9632587410	1520005905219	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1375	1520668199380	5	8521479630	1520005905324	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1411	1520668210583	5	8521479630	1520006265175	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1412	1520668210833	5	9600194696	1520006280075	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1413	1520668211067	5	9632587410	1520006280135	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1414	1520668211408	5	8521479630	1520006280157	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1441	1520668211720	5	1234567890	1520602011953	Dear \r\nDr sherwin1, \r\nYou have an appointment \r\nwith Murali Babu \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 22, 2018 12:00 PM.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT::1430	0	FAILURE	0
1442	1520668212048	5	9600194696	1520602012021	Dear \r\nMurali Babu, \r\nYou have an appointment \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 22, 2018 12:00 PM.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT::00010218	0	FAILURE	0
1443	1520668212366	5	1234567890	1520602149355	Dear \r\nDr sherwin1, \r\nYour appointment \r\nwith Murali Babu \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 23, 2018 12:00 PM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::1430	0	FAILURE	0
1444	1520668212687	5	9600194696	1520602149383	Dear \r\nMurali Babu, \r\nYour appointment \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 23, 2018 12:00 PM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::00010218	0	FAILURE	0
1445	1520668212952	5	1234567890	1520605883328	Dear \r\nDr sherwin1, \r\nYour appointment \r\nwith Raviraja \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 9, 2018 8:00 PM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::1430	0	FAILURE	0
1446	1520668213321	5	9632587410	1520605883406	Dear \r\nRaviraja, \r\nYour appointment \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 9, 2018 8:00 PM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::00030218	0	FAILURE	0
200	1520668213649	5	9171415876	1519629340481	Dear \r\nDr Kumara, \r\nYou have an appointment \r\nwith Murali Babu \r\nat Sree Balaji Dental Clinic, on Feb 28, 2018 5:00 AM.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT::11	0	FAILURE	0
1408	1520668213967	5	8521479630	1520006220166	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
324	1520668214280	5	9600194696	1519880205051	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
357	1520668214608	5	7896541230	1519880878720	Dear \r\nDr Muruganatham, \r\nYou have an appointment \r\nwith Murali Babu \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 7, 2018 5:00 AM.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT::250	0	FAILURE	0
368	1520668214921	5	9600194696	1519881105109	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
423	1520668215248	5	9600194696	1519912680116	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
472	1520668215561	5	9171415876	1519913179739	Dear \r\nDr T.Srinivasan, \r\nYour appointment \r\nwith Murali Babu \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 2, 2018 5:00 AM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::140	0	FAILURE	0
502	1520668215889	5	9600194696	1519914045084	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
507	1520668216139	5	9600194696	1519914180046	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
542	1520668216357	5	9600194696	1519915245057	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
577	1520668216857	5	9600194696	1519916280053	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
612	1520668217107	5	9600194696	1519917345046	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
647	1520668217487	5	9600194696	1519918380099	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
682	1520668217737	5	9600194696	1519919445052	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-08.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
724	1520668218143	5	8521479630	1519999740272	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
744	1520668218362	5	7418529630	1519999857182	Dear \r\nDr Arun, \r\nYour appointment \r\nwith karthk \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 23, 2018 6:00 AM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::251	0	FAILURE	0
769	1520668219135	5	8521479630	1520000085222	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1407	1520668219416	5	9632587410	1520006220135	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1409	1520668219635	5	9600194696	1520006265066	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1410	1520668210364	5	9632587410	1520006265112	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
862	1520668222920	5	8521479630	1520001000335	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1231	1520668223248	5	8521479630	1520004465218	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1266	1520668223561	5	9632587410	1520004825123	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1301	1520668223887	5	9600194696	1520005185070	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1336	1520668224199	5	8521479630	1520005500181	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1371	1520668224527	5	9632587410	1520005860160	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1406	1520668224840	5	9600194696	1520006220091	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1440	1520668225151	5	9600194696	1520601752042	Welcome jose. Your profile has been successfully registered at JACK AND JILL CHILDREN CLINIC. We wish you a speedy recovery. For any assistance, kindly contact - +91 99221 99221	\N	REGISTRATION::00020318	0	FAILURE	0
220	1520779310692	5	9600194696	1520779165120	Dear \r\nMurali Babu, \r\nYour appointment \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 17, 2018 10:00 AM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::00010218	0	FAILURE	0
1161	1520668225722	5	9632587410	1520003760160	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
201	1520668225956	5	9600194696	1519629340549	Dear \r\nMurali Babu, \r\nYou have an appointment \r\nat Sree Balaji Dental Clinic, on Feb 28, 2018 5:00 AM.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT::00010218	0	FAILURE	0
148	1520668260687	5	9654781230	1520667277921	Hello Swethamenon, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-17.	\N	APPOINTMENT REMINDER::00060218	0	FAILURE	0
217	1520681570687	5	9654781230	1520681400339	Hello Swethamenon, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-17.	\N	APPOINTMENT REMINDER::00060218	0	FAILURE	0
191	1520674370808	5	9654781230	1520674200094	Hello Swethamenon, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-17.	\N	APPOINTMENT REMINDER::00060218	0	FAILURE	0
164	1520670720762	5	9654781230	1520670600051	Hello Swethamenon, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-17.	\N	APPOINTMENT REMINDER::00060218	0	FAILURE	0
1196	1520668219854	5	9600194696	1520004120092	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
827	1520668220073	5	9600194696	1520000685147	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
897	1520668220302	5	9632587410	1520001360177	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
911	1520668220552	5	9701752863	1520001513517	Dear \r\nDr Kavita, \r\nYour appointment \r\nwith Swethamenon \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 17, 2018 4:00 AM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::330	0	FAILURE	0
931	1520668221005	5	9632587410	1520001660203	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
966	1520668221326	5	9600194696	1520002020091	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1001	1520668221645	5	8521479630	1520002365264	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
1056	1520668221879	5	9632587410	1520002725269	Hello Raviraja, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00030218	0	FAILURE	0
1091	1520668222285	5	9600194696	1520003085092	Hello Murali Babu, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00010218	0	FAILURE	0
1126	1520668222608	5	8521479630	1520003400166	Hello karthk, JACK AND JILL CHILDREN CLINIC informing about the appointment reminder on 2018-03-09.	\N	APPOINTMENT REMINDER::00040218	0	FAILURE	0
219	1520779310480	5	7418529630	1520779165056	Dear \r\nDr Arun, \r\nYour appointment \r\nwith Murali Babu \r\nat JACK AND JILL CHILDREN CLINIC, on Mar 17, 2018 10:00 AM has been cancelled.\r\nFor any assistance, kindly contact - +91 99221 99221	\N	APPOINTMENT CANCEL::251	0	FAILURE	0
\.


--
-- Data for Name: user_role; Type: TABLE DATA; Schema: public; Owner: cms
--

COPY user_role (user_id, role_id) FROM stdin;
admin	130
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: cms
--

COPY users (name, lastmodifiedtime, version, active, password) FROM stdin;
admin	1519269421621	1	t	$2a$10$4Cie7lbDuKm9NUOsaBp2JO/ODEnIidV6wFCnBeu0EvALTMI1PrR.i
\.


--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


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
-- Name: doctor doctor_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY doctor
    ADD CONSTRAINT doctor_pkey PRIMARY KEY (id);


--
-- Name: label label_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY label
    ADD CONSTRAINT label_pkey PRIMARY KEY (id);


--
-- Name: medication medication_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY medication
    ADD CONSTRAINT medication_pkey PRIMARY KEY (id);


--
-- Name: patient_label patient_label_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY patient_label
    ADD CONSTRAINT patient_label_pkey PRIMARY KEY (patient_id, label_id);


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
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


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
-- Name: smsdetails smsdetails_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY smsdetails
    ADD CONSTRAINT smsdetails_pkey PRIMARY KEY (id);


--
-- Name: label uk28tv3ficg8megpsjtw80vu8mv; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY label
    ADD CONSTRAINT uk28tv3ficg8megpsjtw80vu8mv UNIQUE (labelname);


--
-- Name: doctor uk2i9eqm6hy3ephpt9ep0xfjsfq; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY doctor
    ADD CONSTRAINT uk2i9eqm6hy3ephpt9ep0xfjsfq UNIQUE (name);


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
-- Name: settings uka6opgvuojoarl6ls72vqyvbwi; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY settings
    ADD CONSTRAINT uka6opgvuojoarl6ls72vqyvbwi UNIQUE (category);


--
-- Name: patient ukov1mjrglxi907dck4u1sixtqc; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY patient
    ADD CONSTRAINT ukov1mjrglxi907dck4u1sixtqc UNIQUE (name);


--
-- Name: user_role user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (name);


--
-- Name: prescription fk1ppr8greedyrey8nchpr0v4dn; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fk1ppr8greedyrey8nchpr0v4dn FOREIGN KEY (doctor_id) REFERENCES doctor(id);


--
-- Name: patient fk2st294pbohde6tjvwxip5r01j; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY patient
    ADD CONSTRAINT fk2st294pbohde6tjvwxip5r01j FOREIGN KEY (patient_id) REFERENCES label(id);


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
-- Name: patient_label fka6205yw6pv7u6n5x94cpndwj3; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY patient_label
    ADD CONSTRAINT fka6205yw6pv7u6n5x94cpndwj3 FOREIGN KEY (label_id) REFERENCES label(id);


--
-- Name: user_role fka68196081fvovjhkek5m97n3y; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY user_role
    ADD CONSTRAINT fka68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES role(id);


--
-- Name: prescription_entry fkh60t2u6ffd3ucpk0sy1ke2i0m; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY prescription_entry
    ADD CONSTRAINT fkh60t2u6ffd3ucpk0sy1ke2i0m FOREIGN KEY (medication_id) REFERENCES medication(id);


--
-- Name: user_role fkj345gk1bovqvfame88rcx7yyx; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY user_role
    ADD CONSTRAINT fkj345gk1bovqvfame88rcx7yyx FOREIGN KEY (user_id) REFERENCES users(name);


--
-- Name: address fkm6svejh11etrox7dkwuqw05cr; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY address
    ADD CONSTRAINT fkm6svejh11etrox7dkwuqw05cr FOREIGN KEY (patient_id) REFERENCES patient(id);


--
-- Name: patient_label fkmx36rmasuqq1o5xmji9dsvohd; Type: FK CONSTRAINT; Schema: public; Owner: cms
--

ALTER TABLE ONLY patient_label
    ADD CONSTRAINT fkmx36rmasuqq1o5xmji9dsvohd FOREIGN KEY (patient_id) REFERENCES patient(id);


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

