-- addrbook 테이블 생성
CREATE TABLE addrbook(
    num       NUMBER PRIMARY KEY,
    username  VARCHAR2(20) NOT NULL,
    tel       VARCHAR2(20), 
    email     VARCHAR2(30) NOT NULL,
    gender    VARCHAR2(6),
    regDate   TIMESTAMP DEFAULT SYSTIMESTAMP
);

CREATE SEQUENCE seq_num NOCACHE;

INSERT INTO addrbook(num, username, tel, email, gender)
VALUES (seq_num.NEXTVAL, '김기용', '010-1234-5678', 'test@gmail.com', '남');

COMMIT;