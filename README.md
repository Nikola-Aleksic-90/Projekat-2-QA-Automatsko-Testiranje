Projekat-2-QA-Automatsko-testiranje

Samostalni projekat

Testira se sajt https://sandbox.2checkout.com/sandbox .
Za rad sa elementima koji se nalaze na web stranici koristi se Selenium, a za pisanje
testova JUnit. Primenjen je Page Object Model.

Potrebno je da se svi korišćeni lokatori čuvaju u jednom tekstualnom fajlu, tako da
ukoliko se neki (na primer xpath) promeni čitav kod nije potrebno kompajlirati.
Odredjeni su sledeći zahtevi:

➔ Testirati da li je moguće ulogovati se ukoliko je preskočen korak registracije
korisnika (da li se može prijaviti koristeći podatke koji nikada nisu sačuvani u bazi
korisnika). MANUELNO TESTIRANO

➔ Testirati da li radi forma za registraciju unosom podataka za jednog korisnika.

➔ Detaljno proveriti da li je moguće registrovati se bez unosa svih polja. MANUELNO TESTIRANO

➔ Testirati logovanje korisnika ukoliko to nije urađeno prethodnim koracima.

➔ Testirati dodavanje 5 proizvoda - potrebno je popuniti samo osnovne podatke.
(Potrebne podatke učitati iz xlsx ili xls fajla, kreirati proizvode i proveriti da li je
njihovo kreiranje uspešno).

➔ Povećati cenu kreiranog proizvoda za 200 i proveriti uspešnost izmena. USPESNOST JE PROVERENA MANUELNO
