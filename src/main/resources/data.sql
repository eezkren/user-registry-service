DROP TABLE IF EXISTS registrations;

CREATE TABLE registrations
(
    pk_id        UUID         DEFAULT RANDOM_UUID() PRIMARY KEY,
    first_name   VARCHAR(250) NOT NULL,
    last_name    VARCHAR(250) NOT NULL,
    email        VARCHAR(250) DEFAULT NULL,
    phone_number VARCHAR(250) DEFAULT NULL,
    country_code VARCHAR(250) DEFAULT NULL
);