--update dati aggregati
UPDATE aggregazioni_eventi ae
SET numero_segnalazioni=(SELECT COUNT(*)
		FROM centri_vaccinali cv, eventi_avversi ea
		WHERE ea.nome=cv.nome AND ea.comune=cv.comune),
	media_severita=(SELECT AVG(severita)
		FROM eventi_avversi ea, centri_vaccinali cv
		WHERE ea.nome=cv.nome AND ea.comune=cv.comune AND ae.sintomo=ea.sintomo)

--update dati aggregati centri
update centri_vaccinali cv
set totale_segnalazioni=(select sum(numero_segnalazioni)
			from aggregazioni_eventi
			where nome=cv.nome and comune=cv.comune),
	media_generale=(select sum(media_severita * numero_segnalazioni)/sum(numero_segnalazioni)
			from aggregazioni_eventi
			where nome=cv.nome and comune=cv.comune
			group by sintomo)
