CREATE TABLE Account (
  account_number BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  account_type   VARCHAR(45) DEFAULT NULL,
  person_name    VARCHAR(50) DEFAULT NULL
);

INSERT INTO Account (account_type, person_name) VALUES ('checking', 'Вася Пупкин');