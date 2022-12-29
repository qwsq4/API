CREATE TABLE car (
    id bigserial PRIMARY KEY ,
    brand VARCHAR(255),
    model VARCHAR(255),
    cost NUMERIC
);
CREATE TABLE person (
    id bigserial PRIMARY KEY,
    name VARCHAR(255),
    age smallserial,
    has_driver_license BOOLEAN,
    car_id bigint,

    FOREIGN KEY (car_id) REFERENCES car;
);