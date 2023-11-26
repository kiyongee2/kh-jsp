-- 게시판 테이블
CREATE TABLE board(
    bno        NUMBER PRIMARY KEY,
    title      VARCHAR2(100) NOT NULL,
    content    CLOB NOT NULL,
    createdate TIMESTAMP DEFAULT SYSTIMESTAMP,
    modifydate TIMESTAMP,
    hit        NUMBER DEFAULT 0,
    filename   VARCHAR2(50), 
    id         VARCHAR2(20) NOT NULL,
    FOREIGN KEY(id) REFERENCES member(id) ON DELETE CASCADE 
);

CREATE SEQUENCE seq_bno NOCACHE;

INSERT INTO board (bno, title, content, filename, id)
VALUES (seq_bno.NEXTVAL, '글제목', '글내용입니다.', 'bird.jpg', 'khit');

COMMIT;

SELECT * FROM board ORDER BY bno DESC;