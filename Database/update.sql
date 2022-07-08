--update dati aggregati
UPDATE aggregazioni_eventi ae
SET sintomo=(SELECT sintomo
  FROM eventi_avversi
  GROUP BY sintomo),
numero_segnalazioni=(SELECT COUNT(*)
	FROM centri_vaccinali cv, eventi_avversi ea
	WHERE ea.nome=cv.nome AND ea.comune=cv.comune),
media_severita=(SELECT AVG(severita)
	FROM eventi_avversi ea, centri_vaccinali cv
	WHERE ea.nome=cv.nome AND ea.comune=cv.comune AND ae.sintomo=ea.sintomo)
