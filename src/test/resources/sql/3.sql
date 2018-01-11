CREATE TABLE Instance (
  id       INT PRIMARY KEY AUTO_INCREMENT,
  model_id INT NOT NULL,
  FOREIGN KEY (model_id) REFERENCES Gun (id)
);

INSERT INTO Instance (model_id) VALUES (1);
INSERT INTO Instance (model_id) VALUES (1);
INSERT INTO Instance (model_id) VALUES (1);
INSERT INTO Instance (model_id) VALUES (1);
INSERT INTO Instance (model_id) VALUES (1);
INSERT INTO Instance (model_id) VALUES (1);
INSERT INTO Instance (model_id) VALUES (2);
INSERT INTO Instance (model_id) VALUES (2);
INSERT INTO Instance (model_id) VALUES (2);
INSERT INTO Instance (model_id) VALUES (2);
INSERT INTO Instance (model_id) VALUES (3);
INSERT INTO Instance (model_id) VALUES (3);
INSERT INTO Instance (model_id) VALUES (4);
INSERT INTO Instance (model_id) VALUES (4);
INSERT INTO Instance (model_id) VALUES (4);
INSERT INTO Instance (model_id) VALUES (4);
INSERT INTO Instance (model_id) VALUES (4);
INSERT INTO Instance (model_id) VALUES (4);
INSERT INTO Instance (model_id) VALUES (4);
INSERT INTO Instance (model_id) VALUES (4);
INSERT INTO Instance (model_id) VALUES (5);
INSERT INTO Instance (model_id) VALUES (5);
INSERT INTO Instance (model_id) VALUES (5);
INSERT INTO Instance (model_id) VALUES (5);
INSERT INTO Instance (model_id) VALUES (5);