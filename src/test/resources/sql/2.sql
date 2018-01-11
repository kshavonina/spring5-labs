CREATE TABLE Gun (
  id      INT PRIMARY KEY AUTO_INCREMENT,
  name    VARCHAR(255) NOT NULL,
  caliber DOUBLE       NOT NULL
);

INSERT INTO Gun (name, caliber) VALUES ('Kolt', 11.52);
INSERT INTO Gun (name, caliber) VALUES ('Beretta', 6.35);
INSERT INTO Gun (name, caliber) VALUES ('Glock', 9.0);
INSERT INTO Gun (name, caliber) VALUES ('AKM-47', 7.62);
INSERT INTO Gun (name, caliber) VALUES ('AK-74', 5.45);