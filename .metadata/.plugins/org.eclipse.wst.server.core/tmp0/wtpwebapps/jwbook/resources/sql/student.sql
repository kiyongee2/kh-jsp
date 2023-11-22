-- h2와 연동
CREATE TABLE student(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR2(20),
    univ     VARCHAR2(40),
    birth    DATE,
    email    VARCHAR2(40)
);

INSERT INTO student (username, univ, birth, email)
VALUES ('우영우', '한강대학교', '1999-10-01', 'hanriver@univ.com');