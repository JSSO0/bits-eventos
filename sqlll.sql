INSERT INTO Usuario ( name,  phone, email, is_adm, password) VALUES ( 'Joely', '88981522648', 'sjoely131@gmail.com', false, '1234');
SELECT * from Usuario;
INSERT INTO Usuario ( name,  phone, email, is_adm, password) VALUES ( 'Joely', '88981522648', 'sjoely131@gmail.com', false, '1234');

INSERT INTO Company (name, description) VALUES ('Empresa da Yulia', 'Yulia empresa');
SELECT * FROM Company;

INSERT INTO Evento ( name, description, created_at, starts_in, end_in, payed_event, company_id) VALUES ( 'Evento do Matheus', 'evento', '2024-05-13 00:00:00.000000', '2024-05-13 00:00:00.000000', '2024-05-13 00:00:00.000000', false, 'cae9070b-4837-4155-8180-d773c44e6dd2');
SELECT * FROM Evento;

INSERT INTO Participants ( event_id, user_id, adm_of_event) VALUES ( '7da86fa5-30b3-4850-bb98-1cfaaa1986bc', '20bc0758-8e77-44a7-9395-b049b0f7f8f9', false);
SELECT * FROM Participants;