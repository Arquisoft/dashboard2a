INSERT INTO User values(1, 'alcalde', '1234', true, true, true);
INSERT INTO User values(2, 'concejal1', '1234', false, true, true);
INSERT INTO User values(3, 'concejal2', '1234', true, false, true);
INSERT INTO User values(4, 'concejal3', '1234', true, true, false);
INSERT INTO User values(5, 'concejal4', '1234', false, false, false);

INSERT INTO category values(1, 'categoria1');
INSERT INTO category values(2, 'categoria2');
INSERT INTO category values(3, 'categoria3');
INSERT INTO category values(4, 'categoria4');
INSERT INTO category values(5, 'categoria5');

INSERT INTO citizen values(1, '1234', 'user1', '1234567890', 'user1', 'Surname1', '1994-05-01', 'user1@gmail.com', 'Oviedo', 'Español');
INSERT INTO citizen values(2, '1234', 'user2', '1234567891', 'user2', 'Surname2', '1990-06-02', 'user2@gmail.com', 'Santander', 'Español');
INSERT INTO citizen values(3, '1234', 'user3', '1234567892', 'user3', 'Surname3', '1984-07-03', 'user3@gmail.com', 'Bilbao', 'Español');
INSERT INTO citizen values(4, '1234', 'user4', '1234567893', 'user4', 'Surname4', '1954-08-04', 'user4@gmail.com', 'Barakaldo', 'Español');
INSERT INTO citizen values(5, '1234', 'user5', '1234567894', 'user5', 'Surname5', '1995-09-05', 'user5@gmail.com', 'Gijón', 'Español');

INSERT INTO suggestion values(1, 'S001', 'Sugerencia 1', 'Esta es la sugerencia 1', 1, 1, 1);
INSERT INTO suggestion values(2, 'S002', 'Sugerencia 2', 'Esta es la sugerencia 2', 10, 1, 2);
INSERT INTO suggestion values(3, 'S003', 'Sugerencia 3', 'Esta es la sugerencia 3', 11, 2, 3);
INSERT INTO suggestion values(4, 'S004', 'Sugerencia 4', 'Esta es la sugerencia 4', 20, 2, 4);
INSERT INTO suggestion values(5, 'S005', 'Sugerencia 5', 'Esta es la sugerencia 5', 3, 2, 5);

INSERT INTO comment values(1, 'C001', 'Este es el comentario 1', 3, 1);
INSERT INTO comment values(2, 'C002', 'Este es el comentario 2', 2, 1);
INSERT INTO comment values(3, 'C003', 'Este es el comentario 3', 4, 2);
INSERT INTO comment values(4, 'C004', 'Este es el comentario 4', 5, 3);
INSERT INTO comment values(5, 'C005', 'Este es el comentario 5', 5, 3);
INSERT INTO comment values(6, 'C006', 'Este es el comentario 6', 3, 3);
INSERT INTO comment values(7, 'C007', 'Este es el comentario 7', 1, 4);
INSERT INTO comment values(8, 'C008', 'Este es el comentario 8', 1, 5);
INSERT INTO comment values(9, 'C009', 'Este es el comentario 9', 3, 4);
INSERT INTO comment values(10, 'C0010', 'Este es el comentario 10', 4, 5);


INSERT INTO voteSuggestion values(1, 1, 'POSITIVE');
INSERT INTO voteSuggestion values(2, 1, 'POSITIVE');
INSERT INTO voteSuggestion values(3, 1, 'POSITIVE');
INSERT INTO voteSuggestion values(1, 2, 'POSITIVE');
INSERT INTO voteSuggestion values(2, 2, 'POSITIVE');
INSERT INTO voteSuggestion values(3, 2, 'POSITIVE');
INSERT INTO voteSuggestion values(4, 2, 'POSITIVE');
INSERT INTO voteSuggestion values(1, 4, 'POSITIVE');
INSERT INTO voteSuggestion values(1, 5, 'NEGATIVE');
INSERT INTO voteSuggestion values(2, 5, 'NEGATIVE');
INSERT INTO voteSuggestion values(3, 5, 'NEGATIVE');
INSERT INTO voteSuggestion values(4, 5, 'NEGATIVE');
INSERT INTO voteSuggestion values(3, 4, 'NEGATIVE');
INSERT INTO voteSuggestion values(4, 4, 'NEGATIVE');
INSERT INTO voteSuggestion values(5, 4, 'NEGATIVE');


INSERT INTO voteComment values(1, 1, 'POSITIVE');
INSERT INTO voteComment values(2, 1, 'POSITIVE');
INSERT INTO voteComment values(3, 1, 'POSITIVE');
INSERT INTO voteComment values(1, 2, 'POSITIVE');
INSERT INTO voteComment values(2, 2, 'POSITIVE');
INSERT INTO voteComment values(3, 2, 'POSITIVE');
INSERT INTO voteComment values(4, 2, 'POSITIVE');
INSERT INTO voteComment values(1, 4, 'POSITIVE');
INSERT INTO voteComment values(1, 5, 'NEGATIVE');
INSERT INTO voteComment values(2, 5, 'NEGATIVE');
INSERT INTO voteComment values(3, 5, 'NEGATIVE');
INSERT INTO voteComment values(4, 5, 'NEGATIVE');
INSERT INTO voteComment values(3, 4, 'NEGATIVE');
INSERT INTO voteComment values(4, 4, 'NEGATIVE');
INSERT INTO voteComment values(5, 4, 'NEGATIVE');
