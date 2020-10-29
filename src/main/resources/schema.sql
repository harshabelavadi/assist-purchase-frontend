DROP TABLE IF EXISTS TBL_Books;

CREATE TABLE TBL_Books (
  isbn1 INT AUTO_INCREMENT  PRIMARY KEY,
  author VARCHAR(250) NOT NULL,
  title VARCHAR(250) NOT NULL,
  publisher VARCHAR(250) DEFAULT NULL
);
--insert into Product values (1,'X3',TRUE,'light',10,'bedside',TRUE,TRUE);