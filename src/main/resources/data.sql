
INSERT INTO person (id, name, cpf, cep, street, number, district, city, state) VALUES 
    (1, 'José Cardoso Fernandes', '591.550.660-70', '77679-495', 'Rua da Paz', '2461', 'São José', 'Londrina', 'PR'),
    (2, 'Maitê Bárbara Nunes', '691.797.528-10', '77023-484', 'Quadra 602 Sul Avenida NS 2', '760A', 'Plano Diretor Sul', 'Palmas', 'TO');

INSERT INTO contract (number, register_date, expiration_date, person_id) VALUES 
    (1, '2021-06-03', '2022-10-30', 2),
    (2, '2021-01-03', '2021-11-02', 1),
    (3, '2021-08-31', '2021-09-29', 2),
    (4, '2021-08-31', '2021-09-15', 2),
    (5, '2021-08-31', '2021-09-07', 1),
    (6, '2021-08-17', now(), 1);