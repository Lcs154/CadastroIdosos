CREATE DATABASE elder_admin;

USE elder_admin;

CREATE TABLE elder(
	id INT AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  birthday VARCHAR(20) NOT NULL,
  age INT NOT NULL,
  sala VARCHAR(50),
  email VARCHAR(70) NOT NULL,
  numRes VARCHAR(20) NOT NULL,
	doencas VARCHAR(150),
    
    CONSTRAINT elder_pk PRIMARY KEY(id)
);
