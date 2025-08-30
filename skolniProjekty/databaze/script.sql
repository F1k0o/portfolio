/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     10.05.2024 12:54:23                          */
/*==============================================================*/


alter table kniha
   drop constraint FK_KNIHA_VYDALO_NAKLADAT;

alter table napsal
   drop constraint FK_NAPSAL_NAPSAL_KNIHA;

alter table napsal
   drop constraint FK_NAPSAL_NAPSAL2_AUTOR;

alter table objednavka
   drop constraint FK_OBJEDNAV_VYTVORENO_ZAKAZNIK;

alter table patri_do
   drop constraint FK_PATRI_DO_PATRI_DO_KNIHA;

alter table patri_do
   drop constraint FK_PATRI_DO_PATRI_DO2_ZANR;

alter table zvoleny_titul
   drop constraint FK_ZVOLENY__ZVOLENY_T_KNIHA;

alter table zvoleny_titul
   drop constraint FK_ZVOLENY__ZVOLENY_T_OBJEDNAV;

drop index "vydalo_FK";

drop index "napsal2_FK";

drop index "napsal_FK";

drop index "vytvoreno_FK";

drop index "patri_do2_FK";

drop index "patri_do_FK";

drop index "zvoleny_titul2_FK";

drop index "zvoleny_titul_FK";

drop table autor cascade constraints;

drop table kniha cascade constraints;

drop table nakladatelstvi cascade constraints;

drop table napsal cascade constraints;

drop table objednavka cascade constraints;

drop table patri_do cascade constraints;

drop table zakaznik cascade constraints;

drop table zanr cascade constraints;

drop table zvoleny_titul cascade constraints;

/*==============================================================*/
/* Table: autor                                               */
/*==============================================================*/
create table autor  (
   "id_autor"           INTEGER                         not null
      constraint CKC_ID_AUTOR_AUTOR check ("id_autor" >= 0),
   "jmeno_autora"       VARCHAR2(20)                    not null,
   "prijmeni_autor"     VARCHAR2(20)                    not null,
   "popis_autora"       CLOB,
   constraint PK_AUTOR primary key ("id_autor")
);

/*==============================================================*/
/* Table: kniha                                               */
/*==============================================================*/
create table kniha  (
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
create index "vydalo_FK" on kniha (
   "ico" ASC
);

/*==============================================================*/
/* Table: nakladatelstvi                                      */
/*==============================================================*/
create table nakladatelstvi  (
   "ico"                NUMBER(8)                         not null,
   "nazev"              VARCHAR2(20)                    not null,
   "adresa_nakladatelstvi" VARCHAR2(30)                    not null,
   "email_nakladatelstvi" VARCHAR2(50)                    not null,
   "telefon_nakladatelstvi" CHAR(13),
   constraint PK_NAKLADATELSTVI primary key ("ico")
);

/*==============================================================*/
/* Table: napsal                                              */
/*==============================================================*/
create table napsal  (
   "isbn"               CHAR(17)                        not null,
   "id_autor"           INTEGER                         not null,
   constraint PK_NAPSAL primary key ("isbn", "id_autor")
);

/*==============================================================*/
/* Index: "napsal_FK"                                           */
/*==============================================================*/
create index "napsal_FK" on napsal (
   "isbn" ASC
);

/*==============================================================*/
/* Index: "napsal2_FK"                                          */
/*==============================================================*/
create index "napsal2_FK" on napsal (
   "id_autor" ASC
);

/*==============================================================*/
/* Table: objednavka                                          */
/*==============================================================*/
create table objednavka  (
   "id_objednavky"      INTEGER                         not null
      constraint CKC_ID_OBJEDNAVKY_OBJEDNAV check ("id_objednavky" >= 0),
   "id_zakaznika"       INTEGER                         not null,
   "datum_vytvoreni"    DATE                            
	constraint CKC_DATUM_ZPRACOVANI_OBJEDNAV check ("datum_vytvoreni" is null or ("datum_vytvoreni" >= TO_DATE('2000-01-01', 'YYYY-MM-DD'))),
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
create index "vytvoreno_FK" on objednavka (
   "id_zakaznika" ASC
);

/*==============================================================*/
/* Table: patri_do                                            */
/*==============================================================*/
create table patri_do  (
   "isbn"               CHAR(17)                        not null,
   "id_zanr"            INTEGER                         not null,
   constraint PK_PATRI_DO primary key ("isbn", "id_zanr")
);

/*==============================================================*/
/* Index: "patri_do_FK"                                         */
/*==============================================================*/
create index "patri_do_FK" on patri_do (
   "isbn" ASC
);

/*==============================================================*/
/* Index: "patri_do2_FK"                                        */
/*==============================================================*/
create index "patri_do2_FK" on patri_do (
   "id_zanr" ASC
);

/*==============================================================*/
/* Table: zakaznik                                            */
/*==============================================================*/
create table zakaznik  (
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
/* Table: zanr                                                */
/*==============================================================*/
create table zanr  (
   "id_zanr"            INTEGER                         not null
      constraint CKC_ID_ZANR_ZANR check ("id_zanr" >= 0),
   "nazev_zanru"        VARCHAR2(20)                    not null,
   constraint PK_ZANR primary key ("id_zanr")
);

/*==============================================================*/
/* Table: zvoleny_titul                                       */
/*==============================================================*/
create table zvoleny_titul  (
   "isbn"               CHAR(17)                        not null,
   "id_objednavky"      INTEGER                         not null,
   constraint PK_ZVOLENY_TITUL primary key ("isbn", "id_objednavky")
);

/*==============================================================*/
/* Index: "zvoleny_titul_FK"                                    */
/*==============================================================*/
create index "zvoleny_titul_FK" on zvoleny_titul (
   "isbn" ASC
);

/*==============================================================*/
/* Index: "zvoleny_titul2_FK"                                   */
/*==============================================================*/
create index "zvoleny_titul2_FK" on zvoleny_titul (
   "id_objednavky" ASC
);

alter table kniha
   add constraint FK_KNIHA_VYDALO_NAKLADAT foreign key ("ico")
      references nakladatelstvi ("ico");


alter table napsal
   add constraint FK_NAPSAL_NAPSAL_KNIHA foreign key ("isbn")
      references kniha ("isbn")
      on delete cascade;

alter table napsal
   add constraint FK_NAPSAL_NAPSAL2_AUTOR foreign key ("id_autor")
      references autor ("id_autor");

alter table objednavka
   add constraint FK_OBJEDNAV_VYTVORENO_ZAKAZNIK foreign key ("id_zakaznika")
      references zakaznik ("id_zakaznika");

alter table patri_do
   add constraint FK_PATRI_DO_PATRI_DO_KNIHA foreign key ("isbn")
      references kniha ("isbn")
      on delete cascade;

alter table patri_do
   add constraint FK_PATRI_DO_PATRI_DO2_ZANR foreign key ("id_zanr")
      references zanr ("id_zanr");

alter table zvoleny_titul
   add constraint FK_ZVOLENY__ZVOLENY_T_KNIHA foreign key ("isbn")
      references kniha ("isbn")
      on delete cascade;

alter table zvoleny_titul
   add constraint FK_ZVOLENY__ZVOLENY_T_OBJEDNAV foreign key ("id_objednavky")
      references objednavka ("id_objednavky")
      on delete cascade;

-- Přístupová práva pro uživatele STUDENT
GRANT SELECT ON autor TO STUDENT;
GRANT SELECT ON kniha TO STUDENT;
GRANT SELECT ON nakladatelstvi TO STUDENT;
GRANT SELECT ON napsal TO STUDENT;
GRANT SELECT ON objednavka TO STUDENT;
GRANT SELECT ON patri_do TO STUDENT;
GRANT SELECT ON zakaznik TO STUDENT;
GRANT SELECT ON zanr TO STUDENT;
GRANT SELECT ON zvoleny_titul TO STUDENT;

-- Přístupová práva pro uživatele IT218
GRANT SELECT, INSERT, UPDATE, DELETE ON autor TO IT218;
GRANT SELECT, INSERT, UPDATE, DELETE ON kniha TO IT218;
GRANT SELECT, INSERT, UPDATE, DELETE ON nakladatelstvi TO IT218;
GRANT SELECT, INSERT, UPDATE, DELETE ON napsal TO IT218;
GRANT SELECT, INSERT, UPDATE, DELETE ON objednavka TO IT218;
GRANT SELECT, INSERT, UPDATE, DELETE ON patri_do TO IT218;
GRANT SELECT, INSERT, UPDATE, DELETE ON zakaznik TO IT218;
GRANT SELECT, INSERT, UPDATE, DELETE ON zanr TO IT218;
GRANT SELECT, INSERT, UPDATE, DELETE ON zvoleny_titul TO IT218;
