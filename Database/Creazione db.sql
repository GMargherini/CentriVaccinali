CREATE TABLE centri_vaccinali(
    nome VARCHAR(50) NOT NULL,
    comune VARCHAR(35) NOT NULL,
    indirizzo VARCHAR(50) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    totale_segnalazioni NUMERIC,
    media_generale NUMERIC,
    PRIMARY KEY(nome,comune)
);

CREATE TABLE vaccinati(
    ID_vaccinazione INTEGER PRIMARY KEY CHECK (ID_vaccinazione>=0 AND ID_vaccinazione<56535),
    codice_fiscale CHAR(16) UNIQUE NOT NULL CHECK(codice_fiscale ~ '[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]'),
    nome_cognome VARCHAR(40),
    nome VARCHAR(50) NOT NULL,
    comune VARCHAR(35) NOT NULL,
    data_vaccinazione DATE DEFAULT CURRENT_DATE,
    tipo_vaccino VARCHAR(20),
    FOREIGN KEY(nome,comune) REFERENCES centri_vaccinali
);

CREATE TABLE cittadini_registrati(
    ID_vaccinazione INTEGER UNIQUE NOT NULL REFERENCES vaccinati,
    user_ID VARCHAR(30) PRIMARY KEY,
    password VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL CHECK(email ~* '^[-\w.]+@[A-Z_.]+?[A-Z]{2,4}$')
);

CREATE TABLE eventi_avversi(
    sintomo VARCHAR(30) NOT NULL,
    ID_vaccinazione INTEGER NOT NULL REFERENCES vaccinati,
    severita INTEGER CHECK(severita>=1 AND severita <=5),
    note VARCHAR(256),
    nome VARCHAR(50) NOT NULL,
    comune VARCHAR(35) NOT NULL,
    PRIMARY KEY(sintomo,ID_vaccinazione),
    FOREIGN KEY(nome,comune) REFERENCES centri_vaccinali
);

CREATE TABLE aggregazioni_eventi(
    sintomo VARCHAR(30) NOT NULL REFERENCES eventi_avversi,
    nome VARCHAR(50) NOT NULL,
    comune VARCHAR(35) NOT NULL,
    numero_segnalazioni INTEGER,
    media_severita NUMERIC,
    PRIMARY KEY(sintomo,nome,comune),
    FOREIGN KEY(nome,comune) REFERENCES centri_vaccinali
);
