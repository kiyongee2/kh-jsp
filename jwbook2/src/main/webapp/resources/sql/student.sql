-- oracle db와 연동
CREATE TABLE student(
    sid       NUMBER PRIMARY KEY,
    username VARCHAR2(20) NOT NULL,
    univ     VARCHAR2(40),
    birth    VARCHAR2(20),
    email    VARCHAR2(40) NOT NULL UNIQUE
);

CREATE SEQUENCE seq_sid NOCACHE;

INSERT INTO student (sid, username, univ, birth, email)
VALUES (seq_sid.NEXTVAL, '우영우', '한강대학교', '1999-10-01', 'hanriver@seoul.com');

