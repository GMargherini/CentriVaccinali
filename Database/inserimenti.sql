-- registraCentroVaccinale()
INSERT INTO centri_vaccinali (nome, comune, indirizzo, tipo)
VALUES (?,?,?,?);

-- registraVaccinato()
INSERT INTO vaccinati (ID_vaccinazione, codice_fiscale, nome_cognome, nome, comune, data_vaccinazione, tipo_vaccino)
VALUES (?,?,?,?,?,?);

-- registraCittadino()
INSERT INTO cittadini_registrati (ID_vaccinazione, user_ID, password, email)
VALUES (?,?,?,?);

-- inserisciEventiAvversi()
INSERT INTO eventi_avversi (sintomo, severita, note, nome, comune)
VALUES (?,?,?,?,?);

-- inserimento eventi aggregati
INSERT INTO aggregazioni_eventi(sintomo, nome, comune)
select distinct sintomo, nome, comune
from eventi_avversi
