
-- CREATE SEQUENCE person_seq INCREMENT 1 START WITH 3;
CREATE SEQUENCE person_seq START WITH 3 INCREMENT BY 1;

CREATE TABLE person (
    id BIGINT DEFAULT nextval('person_seq') PRIMARY KEY,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    name VARCHAR NOT NULL,
    cep VARCHAR(9) NOT NULL,
    street VARCHAR NOT NULL,
    number VARCHAR(10) NOT NULL,
    additional VARCHAR(150),
    district VARCHAR(150) NOT NULL,
    city VARCHAR(150) NOT NULL,
    state VARCHAR(2) NOT NULL,
    country VARCHAR(150) NOT NULL DEFAULT 'Brasil'
);

CREATE TABLE contract (
    number BIGINT PRIMARY KEY CHECK (number > 0),
    register_date DATE NOT NULL CHECK (register_date <= NOW()),
    expiration_date DATE NOT NULL CHECK (expiration_date >= NOW()),
    person_id BIGINT NOT NULL,
    FOREIGN KEY (person_id) REFERENCES person(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

