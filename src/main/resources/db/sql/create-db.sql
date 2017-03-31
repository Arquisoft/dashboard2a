CREATE TABLE Tuser(
id BIGINT PRIMARY KEY,
login VARCHAR(255) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
viewCategories BOOLEAN NOT NULL,
viewComments BOOLEAN NOT NULL,
viewSuggestions BOOLEAN NOT NULL
);

CREATE TABLE category(
id BIGINT PRIMARY KEY,
name VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE citizen(
id BIGINT PRIMARY KEY,
password VARCHAR(255) NOT NULL,
userName VARCHAR(255) NOT NULL UNIQUE,
dni VARCHAR(255) NOT NULL UNIQUE,
name VARCHAR(255),
surname VARCHAR(255),
bornDate DATE,
email VARCHAR(255),
postAddress VARCHAR(255),
nationality VARCHAR(255)

);

CREATE TABLE suggestion(
id BIGINT PRIMARY KEY,
code VARCHAR(255) NOT NULL UNIQUE,
title VARCHAR(255) NOT NULL,
description VARCHAR(255),
minVotes INTEGER,
id_citizen BIGINT NOT NULL,
id_category BIGINT NOT NULL,
CONSTRAINT fk_suggestion_citizen FOREIGN KEY (id_citizen) REFERENCES citizen(id),
CONSTRAINT fk_suggestion_category FOREIGN KEY (id_category) REFERENCES category(id)
);

CREATE TABLE comment(
id BIGINT PRIMARY KEY,
code VARCHAR(255) NOT NULL UNIQUE,
description VARCHAR(255) NOT NULL,
id_citizen BIGINT NOT NULL,
id_suggestion BIGINT NOT NULL,
CONSTRAINT fk_commnet_citizen FOREIGN KEY (id_citizen) REFERENCES citizen(id),
CONSTRAINT fk_commnet_suggestion FOREIGN KEY (id_suggestion) REFERENCES suggestion(id),
);

CREATE TABLE voteSuggestion(
id_citizen BIGINT,
id_suggestion BIGINT,
vote VARCHAR(255) NOT NULL,
CONSTRAINT pk_voteSuggestion PRIMARY KEY (id_citizen, id_suggestion),
CONSTRAINT fk_voteSuggestion_citizen FOREIGN KEY (id_citizen) REFERENCES citizen(id),
CONSTRAINT fk_voteSuggestion_suggestion FOREIGN KEY (id_suggestion) REFERENCES suggestion(id)
);

CREATE TABLE voteComment(
id_citizen BIGINT,
id_comment BIGINT,
vote VARCHAR(255) NOT NULL,
CONSTRAINT pk_voteComment PRIMARY KEY (id_citizen, id_comment),
CONSTRAINT fk_voteComment_citizen FOREIGN KEY (id_citizen) REFERENCES citizen(id),
CONSTRAINT fk_voteComment_comment FOREIGN KEY (id_comment) REFERENCES comment(id)
);
