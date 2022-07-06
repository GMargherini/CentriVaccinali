-- ricerca per nome parametrizzata
SELECT nome, comune
FROM centri_vaccinali
WHERE nome LIKE('%?%')

-- ricerca per comune e tipologia parametrizzata
SELECT nome, comune
FROM centri_vaccinali
WHERE comune=? AND tipo=?

--selezione centro
SELECT *
FROM centri_vaccinali
WHERE nome=? AND comune=?
