CREATE DATABASE pocket_monster_db;

USE pocket_monster_db;


CREATE TABLE trainer (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(10) NOT NULL
);

CREATE TABLE monster (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(10) NOT NULL,
trainer_id INT,
FOREIGN KEY (trainer_id) REFERENCES trainer(id)
);

INSERT INTO trainer (name) VALUES
('Magenta'),
('Cyan');

INSERT INTO monster (name) VALUES
('Goufer'),
('Maremot');

INSERT INTO monster (name, trainer_id) VALUES
('Foeline', 1),
('Dougless', 2);