INSERT INTO users (id, name, last_name, username, password) VALUES (1, 'jelena', 'stojkovic', 'jelena', 'jelena');
INSERT INTO users (id, name, last_name, username, password) VALUES (2, 'admin', 'admin', 'admin', 'admin');


INSERT INTO recept (recept_id, vreme, priprema, sastojci, tezina,naziv) VALUES (1,30,'namazati testo kecapom,staviti salamu,sir,sampinjone,i origano.Neka se pece na 200 stepeni.','kecap,salama,sir,origano,sampinjoni,testo.','srednje','pica');
INSERT INTO recept (recept_id, vreme, priprema, sastojci, tezina,naziv) VALUES (2,55,'U tiganj sipati ulje,izmutiti jaja, sipati u tiganj i mesati dok ne bude gotovo.','3 jajeta','lako','omlet');
INSERT INTO recept (recept_id, vreme, priprema, sastojci, tezina,naziv) VALUES (3,34,'staviti kore, premazati besamel sosom,staviti kore,staviti meso,staviti kore,staviti pesto sos. ispeci','kore,pesto sos,junece meso,sir,besamel sos.','tesko','lazanje');

INSERT INTO komentar (komentar_id,komentator,tekst,recept_recept_id)VALUES(1,'jelena','Odlicno',1);
INSERT INTO komentar (komentar_id,komentator,tekst,recept_recept_id)VALUES(2,'marija','Super stvarno je vrh ovaj recept',1);
INSERT INTO komentar (komentar_id,komentator,tekst,recept_recept_id)VALUES(3,'pavle','Ne svidja mi se kako spremate, ja znam bolji nacin',2);
INSERT INTO komentar (komentar_id,komentator,tekst,recept_recept_id)VALUES(4,'milos','Savrsenstvo bez mane',2);

