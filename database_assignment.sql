CREATE DATABASE sparkx_assignment;

use sparkx_assignment;

CREATE TABLE owner (
    owner_id int ,
    name varchar(30),
    surname varchar(20),
    street_address varchar(50),
    city varchar(30),
    state varchar(20),
    state_full varchar(20),
    zip_code int,
    PRIMARY KEY (owner_id)
);

CREATE TABLE pet (
    pet_id varchar(10) ,
    name varchar(45),
    kind varchar(50),
    gender varchar(20),
    age int,
    owner_id int,

    PRIMARY KEY (pet_id),
    FOREIGN KEY (owner_id) REFERENCES owner (owner_id)
);

CREATE TABLE procedure_detail (
    procedure_type varchar(50) NOT NULL,
    procedure_subcode int NOT NULL,
    description varchar(100),
    price decimal,
    PRIMARY KEY (procedure_type, procedure_subcode)
);

CREATE TABLE procedure_history (
    pet_id varchar(10),
    date date,
    procedure_type varchar(50),
    procedure_subcode int,

    FOREIGN KEY (pet_id) REFERENCES pet (pet_id)
    FOREIGN KEY (procedure_type, procedure_subcode) REFERENCES procedure_detail (procedure_type,procedure_subcode),    
);

-- Query 01
SELECT procedure_type 
FROM procedure_detail
WHERE price > 150;

-- Query 02
SELECT name, surname 
FROM owner
WHERE owner_id IN (SELECT owner_id
                FROM pet
                WHERE kind = 'parrot');

-- Query 03
SELECT zip_code, COUNT(owner_id)
FROM owner
GROUP BY zip_code;

-- Query 04
SELECT pet_id, name
FROM pet
WHERE pet_id IN (SELECT pet_id
                FROM procedure_history
                WHERE MONTH(date) = 2 AND YEAR(date) = 2016);

-- Query 05
SELECT SUM(price)
FROM procedure_detail
INNER JOIN procedure_history 
ON procedure_detail.procedure_type = procedure_history.procedure_type 
    AND procedure_detail.procedure_subcode = procedure_history.procedure_subcode
WHERE procedure_history.pet_id IN(SELECT pet_id
                                FROM pet
                                INNER JOIN owner
                                ON pet.owner_id = owner.owner_id
                                WHERE owner.zip_code = 49503 AND  MONTH(date) = 3);