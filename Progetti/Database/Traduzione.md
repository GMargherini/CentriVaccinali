vaccinati (<ins>ID vaccinazione</ins>, codice fiscale, nome, nome<sup>centri_vaccinali</sup>, comune<sup>centri_vaccinali</sup>, data vaccinazione, tipo vaccino)\
cittadini_registrati (<ins>ID vaccinazione</ins><sup>vaccinati</sup>, user ID, password, e-mail)\
eventi_avversi (<ins>sintomo</ins>, <ins>ID vaccinazione</ins><sup>cittadini_registrati</sup>, severità, note, nome<sup>centri_vaccinali</sup>, comune<sup>centri_vaccinali</sup>)\
centri_vaccinali (<ins>nome, comune</ins>, indirizzo, tipo, totale segnalazioni, media generale)\
aggregazioni_eventi (<ins>sintomo</ins><sup>eventi_avversi</sup>, <ins>nome</ins><sup>centri_vaccinali</sup>, <ins>comune</ins><sup>centri_vaccinali</sup>, numero segnalazioni, media severità)
