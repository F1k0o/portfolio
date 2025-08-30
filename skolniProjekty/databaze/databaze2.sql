/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     10.05.2024 12:54:23                          */
/*==============================================================*/


/*==============================================================*/
/* Table: "autor"                                               */
/*==============================================================*/
create table autor  (
   "id_autor2"           INTEGER                         not null
      constraint CKC_ID_AUTOR_AUTOR check ("id_autor" >= 0),
   "jmeno_autora"       VARCHAR2(20)                    not null,
   "prijmeni_autor"     VARCHAR2(20)                    not null,
   "popis_autora"       CLOB,
   constraint PK_AUTOR primary key ("id_autor")
);
/*==============================================================*/
/* Table: "kniha"                                               */
/*==============================================================*/
create table "kniha"  (
   "isbn"               CHAR(17)                        not null,
   "ico"                CHAR(8)                         not null,
   "nazev_knihy"        VARCHAR2(20)                    not null,
   "cena"               NUMBER(8,2)                     not null
      constraint CKC_CENA_KNIHA check ("cena" >= 0),
   "popis_knihy"        CLOB,
   "pocet_stran"        VARCHAR2(4)                    
      constraint CKC_POCET_STRAN_KNIHA check ("pocet_stran" is null or ("pocet_stran" >= '1')),
   constraint PK_KNIHA primary key ("isbn")
);

/*==============================================================*/
/* Index: "vydalo_FK"                                           */
/*==============================================================*/
create index "vydalo_FK" on "kniha" (
   "ico" ASC
);

/*==============================================================*/
/* Table: "nakladatelstvi"                                      */
/*==============================================================*/
create table "nakladatelstvi"  (
   "ico"                NUMBER(8)                         not null,
   "nazev"              VARCHAR2(20)                    not null,
   "adresa_nakladatelstvi" VARCHAR2(30)                    not null,
   "email_nakladatelstvi" VARCHAR2(50)                    not null,
   "telefon_nakladatelstvi" CHAR(13),
   constraint PK_NAKLADATELSTVI primary key ("ico")
);
/*==============================================================*/
/* Table: "napsal"                                              */
/*==============================================================*/
create table "napsal"  (
   "isbn"               CHAR(17)                        not null,
   "id_autor"           INTEGER                         not null,
   constraint PK_NAPSAL primary key ("isbn", "id_autor")
);

/*==============================================================*/
/* Index: "napsal_FK"                                           */
/*==============================================================*/
create index "napsal_FK" on "napsal" (
   "isbn" ASC
);

/*==============================================================*/
/* Index: "napsal2_FK"                                          */
/*==============================================================*/
create index "napsal2_FK" on "napsal" (
   "id_autor" ASC
);

/*==============================================================*/
/* Table: "objednavka"                                          */
/*==============================================================*/
create table "objednavka"  (
   "id_objednavky"      INTEGER                         not null
      constraint CKC_ID_OBJEDNAVKY_OBJEDNAV check ("id_objednavky" >= 0),
   "id_zakaznika"       INTEGER                         not null,
   "datum_vytvoreni"    DATE                            not null,
   "stav_objednavky"    VARCHAR2(20)                    not null
      constraint CKC_STAV_OBJEDNAVKY_OBJEDNAV check ("stav_objednavky" in ('vytvoreno','zpracovano','vyzvednuto')),
   "datum_zpracovani"   DATE                           
      constraint CKC_DATUM_ZPRACOVANI_OBJEDNAV check ("datum_zpracovani" is null or ("datum_zpracovani" >= TO_DATE('2000-01-01', 'YYYY-MM-DD'))),
   "datum_vyzvednuti"   DATE                           
      constraint CKC_DATUM_VYZVEDNUTI_OBJEDNAV check ("datum_vyzvednuti" is null or ("datum_vyzvednuti" >= TO_DATE('2000-01-01', 'YYYY-MM-DD'))),
   constraint PK_OBJEDNAVKA primary key ("id_objednavky")
);

/*==============================================================*/
/* Index: "vytvoreno_FK"                                        */
/*==============================================================*/
create index "vytvoreno_FK" on "objednavka" (
   "id_zakaznika" ASC
);

/*==============================================================*/
/* Table: "patri_do"                                            */
/*==============================================================*/
create table "patri_do"  (
   "isbn"               CHAR(17)                        not null,
   "id_zanr"            INTEGER                         not null,
   constraint PK_PATRI_DO primary key ("isbn", "id_zanr")
);

/*==============================================================*/
/* Index: "patri_do_FK"                                         */
/*==============================================================*/
create index "patri_do_FK" on "patri_do" (
   "isbn" ASC
);

/*==============================================================*/
/* Index: "patri_do2_FK"                                        */
/*==============================================================*/
create index "patri_do2_FK" on "patri_do" (
   "id_zanr" ASC
);

/*==============================================================*/
/* Table: "zakaznik"                                            */
/*==============================================================*/
create table "zakaznik"  (
   "id_zakaznika"       INTEGER                         not null
      constraint CKC_ID_ZAKAZNIKA_ZAKAZNIK check ("id_zakaznika" >= 0),
   "jmeno_zakaznika"    VARCHAR2(20)                    not null,
   "prijmeni_zakaznika" VARCHAR2(20)                    not null,
   "email_zakaznika"    VARCHAR2(50)                    not null,
   "fakturacni_adresa"  VARCHAR2(50)                    not null,
   "telefon_zakaznika"  CHAR(13),
   constraint PK_ZAKAZNIK primary key ("id_zakaznika")
);

/*==============================================================*/
/* Table: "zanr"                                                */
/*==============================================================*/
create table "zanr"  (
   "id_zanr"            INTEGER                         not null
      constraint CKC_ID_ZANR_ZANR check ("id_zanr" >= 0),
   "nazev_zanru"        VARCHAR2(20)                    not null,
   constraint PK_ZANR primary key ("id_zanr")
);

/*==============================================================*/
/* Table: "zvoleny_titul"                                       */
/*==============================================================*/
create table "zvoleny_titul"  (
   "isbn"               CHAR(17)                        not null,
   "id_objednavky"      INTEGER                         not null,
   constraint PK_ZVOLENY_TITUL primary key ("isbn", "id_objednavky")
);

/*==============================================================*/
/* Index: "zvoleny_titul_FK"                                    */
/*==============================================================*/
create index "zvoleny_titul_FK" on "zvoleny_titul" (
   "isbn" ASC
);

/*==============================================================*/
/* Index: "zvoleny_titul2_FK"                                   */
/*==============================================================*/
create index "zvoleny_titul2_FK" on "zvoleny_titul" (
   "id_objednavky" ASC
);

alter table "kniha"
   add constraint FK_KNIHA_VYDALO_NAKLADAT foreign key ("ico")
      references "nakladatelstvi" ("ico");

alter table "napsal"
   add constraint FK_NAPSAL_NAPSAL_KNIHA foreign key ("isbn")
      references "kniha" ("isbn")
      on delete cascade;

alter table "napsal"
   add constraint FK_NAPSAL_NAPSAL2_AUTOR foreign key ("id_autor")
      references "autor" ("id_autor");

alter table "objednavka"
   add constraint FK_OBJEDNAV_VYTVORENO_ZAKAZNIK foreign key ("id_zakaznika")
      references "zakaznik" ("id_zakaznika");

alter table "patri_do"
   add constraint FK_PATRI_DO_PATRI_DO_KNIHA foreign key ("isbn")
      references "kniha" ("isbn")
      on delete cascade;

alter table "patri_do"
   add constraint FK_PATRI_DO_PATRI_DO2_ZANR foreign key ("id_zanr")
      references "zanr" ("id_zanr");

alter table "zvoleny_titul"
   add constraint FK_ZVOLENY__ZVOLENY_T_KNIHA foreign key ("isbn")
      references "kniha" ("isbn")
      on delete cascade;

alter table "zvoleny_titul"
   add constraint FK_ZVOLENY__ZVOLENY_T_OBJEDNAV foreign key ("id_objednavky")
      references "objednavka" ("id_objednavky")
      on delete cascade;
      

    ----------------------------------------
insert into "autor" ("id_autor", "jmeno_autora", "prijmeni_autor", "popis_autora") values (2, 'Pepa', 'Vomacko', 'Nemecky autor povalecneho obdobi.');

insert into "autor" ("id_autor", "jmeno_autora", "prijmeni_autor", "popis_autora") values (1, 'Petr', 'Nevecerel', 'Autor, ktery ma rad plnene knedliky.');

insert into "autor" ("id_autor", "jmeno_autora", "prijmeni_autor", "popis_autora") values (0, 'Franta', 'Vorel', 'Autor, ktery ma tezke deprese.');


insert into "zanr" ("id_zanr", "nazev_zanru") values (2, 'Povdka');

insert into "zanr" ("id_zanr", "nazev_zanru") values (0, 'Novela');

insert into "zanr" ("id_zanr", "nazev_zanru") values (1, 'Roman');


insert into "nakladatelstvi" ("ico", "nazev", "adresa_nakladatelstvi", "email_nakladatelstvi", "telefon_nakladatelstvi") values ('12345678', 'Nakladatelstvi1', 'Studentska 19 16000,Praha 6', 'nakladatelstvi1@vse.cz', '+420123456789');

insert into "nakladatelstvi" ("ico", "nazev", "adresa_nakladatelstvi", "email_nakladatelstvi", "telefon_nakladatelstvi") values ('87654321', 'Nakladatelstvi2', 'Koliste 1,60200 Brno', 'nakladatelstvi2@vse.cz', '+420987654321');

insert into "nakladatelstvi" ("ico", "nazev", "adresa_nakladatelstvi", "email_nakladatelstvi", "telefon_nakladatelstvi") values ('12348765', 'Nakladatelstvi3', 'Bartunkova 7,14900 Praha 11', 'nakladatelstvi3@vse.cz', '+420123789456');


insert into "zakaznik" ("id_zakaznika", "jmeno_zakaznika", "prijmeni_zakaznika", "email_zakaznika", "fakturacni_adresa", "telefon_zakaznika") values (2, 'Andrej', 'Sycak', 'andy@vse.cz', 'Plzenska 1903/14a, Smichov, 15000 Praha 5', '+420498231123');

insert into "zakaznik" ("id_zakaznika", "jmeno_zakaznika", "prijmeni_zakaznika", "email_zakaznika", "fakturacni_adresa", "telefon_zakaznika") values (1, 'Arnold', 'Cerny', 'arno@vse.cz', 'Ulice 194/13, Stodulky, 15000 Praha 5', '+420774789654');

insert into "zakaznik" ("id_zakaznika", "jmeno_zakaznika", "prijmeni_zakaznika", "email_zakaznika", "fakturacni_adresa", "telefon_zakaznika") values (0, 'Spytihnev', 'Prvni', 'spyty@vse.cz', 'Na prikope 16, Stare mesto, 11000 Praha 1', '+420789654321');




insert into "objednavka" ("id_objednavky", "id_zakaznika", "datum_vytvoreni", "datum_zpracovani", "datum_vyzvednuti", "stav_objednavky") values (0, 1,DATE  '2005-01-01',DATE  '2005-01-02',DATE  '2005-01-28', 'vyzvednuto');

insert into "objednavka" ("id_objednavky", "id_zakaznika", "datum_vytvoreni", "datum_zpracovani", "datum_vyzvednuti", "stav_objednavky") values (2, 1,DATE '2006-05-29',DATE '2006-05-29',DATE '2006-05-30', 'vyzvednuto');

insert into "objednavka" ("id_objednavky", "id_zakaznika", "datum_vytvoreni", "datum_zpracovani", "datum_vyzvednuti", "stav_objednavky") values (1, 1,DATE '2020-10-06',DATE '2020-10-07',DATE '2020-10-08', 'vyzvednuto');



insert into "kniha" ("isbn", "ico", "nazev_knihy", "cena", "popis_knihy", "pocet_stran") values ('978-80-247-2279-5', '12348765', 'Kniha1', 400, 'Krasna kniha.', '300');

insert into "kniha" ("isbn", "ico", "nazev_knihy", "cena", "popis_knihy", "pocet_stran") values ('954-80-247-1234-1', '87654321', 'Kniha2', 500, 'O neco horsi kniha.', '128');

insert into "kniha" ("isbn", "ico", "nazev_knihy", "cena", "popis_knihy", "pocet_stran") values ('654-80-645-2279-3', '12348765', 'Kniha2', 300, 'Spatna kniha.', '250');


insert into "napsal" ("isbn", "id_autor") values ('978-80-247-2279-5', 2);

insert into "napsal" ("isbn", "id_autor") values ('954-80-247-1234-1', 1);

insert into "napsal" ("isbn", "id_autor") values ('654-80-645-2279-3', 0);


insert into "zvoleny_titul" ("isbn", "id_objednavky") values ('978-80-247-2279-5', 0);

insert into "zvoleny_titul" ("isbn", "id_objednavky") values ('954-80-247-1234-1', 2);

insert into "zvoleny_titul" ("isbn", "id_objednavky") values ('654-80-645-2279-3', 1);


insert into "patri_do" ("isbn", "id_zanr") values ('978-80-247-2279-5', 2);

insert into "patri_do" ("isbn", "id_zanr") values ('954-80-247-1234-1', 0);

insert into "patri_do" ("isbn", "id_zanr") values ('654-80-645-2279-3', 1);


-- Pøístupová práva pro uživatele STUDENT
GRANT SELECT ON autor TO STUDENT;
GRANT SELECT ON kniha TO STUDENT;
GRANT SELECT ON nakladatelstvi TO STUDENT;
GRANT SELECT ON napsal TO STUDENT;
GRANT SELECT ON objednavka TO STUDENT;
GRANT SELECT ON patri_do TO STUDENT;
GRANT SELECT ON zakaznik TO STUDENT;
GRANT SELECT ON zanr TO STUDENT;
GRANT SELECT ON zvoleny_titul TO STUDENT;

-- Pøístupová práva pro uživatele IT218
GRANT SELECT, INSERT, UPDATE, DELETE ON autor TO IT218;
GRANT SELECT, INSERT, UPDATE, DELETE ON kniha TO IT218;
GRANT SELECT, INSERT, UPDATE, DELETE ON nakladatelstvi TO IT218;
GRANT SELECT, INSERT, UPDATE, DELETE ON napsal TO IT218;
GRANT SELECT, INSERT, UPDATE, DELETE ON objednavka TO IT218;
GRANT SELECT, INSERT, UPDATE, DELETE ON patri_do TO IT218;
GRANT SELECT, INSERT, UPDATE, DELETE ON zakaznik TO IT218;
GRANT SELECT, INSERT, UPDATE, DELETE ON zanr TO IT218;
GRANT SELECT, INSERT, UPDATE, DELETE ON zvoleny_titul TO IT218;
