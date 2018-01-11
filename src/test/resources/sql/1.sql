SET MODE POSTGRESQL;

CREATE TABLE IF NOT EXISTS Person (
  id         INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255),
  permission BOOLEAN         DEFAULT FALSE,
  dob        DATE,
  email      VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  address    VARCHAR(255),
  telephone  VARCHAR(15)
);

INSERT INTO Person (first_name, last_name, permission, dob, email, password, address, telephone)
VALUES ('Jose', 'Eglesias', TRUE, '1980-06-15', 'Jose_Eglesias@mail.es', 'qwerty', 'Franco squere, 5/1, 10',
        '+38007654321');

INSERT INTO Person (first_name, last_name, permission, dob, email, password, address, telephone)
VALUES ('John', 'Eglesias', TRUE, '1980-06-15', 'John_Eglesias@mail.es', 'qwerty', 'Franco squere, 5/1, 10',
        '+38007654321');

INSERT INTO Person (first_name, last_name, dob, email, password, address, telephone)
VALUES ('Pit', 'Eglesias', '1980-06-15', 'Pit_Eglesias@mail.es', 'qwerty', 'Franco squere, 5/1, 10',
        '+38007654321');

INSERT INTO Person (first_name, last_name, permission, dob, email, password, address, telephone)
VALUES ('Aisha', 'Eglesias', TRUE, '1980-06-15', 'Aisha_Eglesias@mail.es', 'qwerty', 'Franco squere, 5/1, 10',
        '+38007654321');