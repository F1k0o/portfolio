insert into "autor" ("id_autor", "jmeno_autora", "prijmeni_autor", "popis_autora") values (2, 'Pepa', 'Vomacko', 'Nemecky autor povalecneho obdobi.');
insert into "autor" ("id_autor", "jmeno_autora", "prijmeni_autor", "popis_autora") values (1, 'Petr', 'Nevecerel', 'Autor, ktery ma rad plnene knedliky.');
insert into "autor" ("id_autor", "jmeno_autora", "prijmeni_autor", "popis_autora") values (0, 'Franta', 'Vorel', 'Autor, ktery ma tezke deprese.');


insert into "zanr" ("id_zanr", "nazev_zanru") values (2, 'Povdka');
insert into "zanr" ("id_zanr", "nazev_zanru") values (0, 'Novela');
insert into "zanr" ("id_zanr", "nazev_zanru") values (1, 'Roman');


insert into "nakladatelstvi" ("ico", "nazev", "adresa_nakladatelstvi", "email_nakladatelstvi", "telefon_nakladatelstvi") values ('12345678', 'Nakladatelstvi1', 'Studentska 1903/14a, Dejvice, 16000 Praha 6', 'nakladatelstvi1@vse.cz', '+420123456789');
insert into "nakladatelstvi" ("ico", "nazev", "adresa_nakladatelstvi", "email_nakladatelstvi", "telefon_nakladatelstvi") values ('87654321', 'Nakladatelstvi2', 'Koliste v.ev. 1, Brno-mesto, 60200 Brno', 'nakladatelstvi2@vse.cz', '+420987654321');
insert into "nakladatelstvi" ("ico", "nazev", "adresa_nakladatelstvi", "email_nakladatelstvi", "telefon_nakladatelstvi") values ('12348765', 'Nakladatelstvi3', 'Bartunkova 717/3
Praha 11-Chodov
149 00 Praha 415', 'nakladatelstvi3@vse.cz', '+420123789456');


insert into "zakaznik" ("id_zakaznika", "jmeno_zakaznika", "prijmeni_zakaznika", "email_zakaznika", "fakturacni_adresa", "telefon_zakaznika") values (2, 'Andrej', 'Sycak', 'andy@vse.cz', 'Plzenska 1903/14a, Smichov, 15000 Praha 5', '+420498231123');
insert into "zakaznik" ("id_zakaznika", "jmeno_zakaznika", "prijmeni_zakaznika", "email_zakaznika", "fakturacni_adresa", "telefon_zakaznika") values (1, 'Arnold', 'Cerny', 'arno@vse.cz', 'Ulice 194/13, Stodulky, 15000 Praha 5', '+420774789654');
insert into "zakaznik" ("id_zakaznika", "jmeno_zakaznika", "prijmeni_zakaznika", "email_zakaznika", "fakturacni_adresa", "telefon_zakaznika") values (0, 'Spytihnev', 'Prvni', 'spyty@vse.cz', 'Na prikope 16, Stare mesto, 11000 Praha 1', '+420789654321');


insert into "objednavka" ("id_objednavky", "id_zakaznika", "datum_vytvoreni", "datum_zpracovani", "datum_vyzvednuti", "stav_objednavky") values (0, 1,DATE  '2005-01-01',DATE  '2005-01-02',DATE  '2005-01-28', 'vyzvednuto');
insert into "objednavka" ("id_objednavky", "id_zakaznika", "datum_vytvoreni", "datum_zpracovani", "datum_vyzvednuti", "stav_objednavky") values (2, 1,DATE '2006-05-29',DATE '2006-05-29',DATE '2006-05-30', 'vyzvednuto');
insert into "objednavka" ("id_objednavky", "id_zakaznika", "datum_vytvoreni", "datum_zpracovani", "datum_vyzvednuti", "stav_objednavky") values (1, 1,DATE '2020-10-06',DATE '2020-10-07',DATE '2020-10-08', 'vyzvednuto');


insert into "kniha" ("isbn", "ico", "nazev_knihy", "cena", "popis_knihy", "pocet_stran") values ('978-80-247-2279-5', '12348765', 'Kniha1', 400, 'Krasna kniha.', '300');
insert into "kniha" ("isbn", "ico", "nazev_knihy", "cena", "popis_knihy", "pocet_stran") values ('954-80-247-1234-1', '87654321', 'Kniha2', 500, 'O neco horsi kniha.', '128');
insert into "kniha" ("isbn", "ico", "nazev_knihy", "cena", "popis_knihy", "pocet_stran") values ('654-80-645-2279-3', '12348765', 'Kniha2', 300, 'Spatna kniha.', '	');

insert into "napsal" ("isbn", "id_autor") values ('978-80-247-2279-5', 2);
insert into "napsal" ("isbn", "id_autor") values ('954-80-247-1234-1', 1);
insert into "napsal" ("isbn", "id_autor") values ('654-80-645-2279-3', 0);


insert into "zvoleny_titul" ("isbn", "id_objednavky") values ('978-80-247-2279-5', 0);
insert into "zvoleny_titul" ("isbn", "id_objednavky") values ('954-80-247-1234-1', 2);
insert into "zvoleny_titul" ("isbn", "id_objednavky") values ('654-80-645-2279-3', 1);


insert into "patri_do" ("isbn", "id_zanr") values ('978-80-247-2279-5', 2);
insert into "patri_do" ("isbn", "id_zanr") values ('954-80-247-1234-1', 0);
insert into "patri_do" ("isbn", "id_zanr") values ('654-80-645-2279-3', 1);

