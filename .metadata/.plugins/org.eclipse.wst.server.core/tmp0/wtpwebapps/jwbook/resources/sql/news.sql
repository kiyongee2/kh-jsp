CREATE TABLE news(
	aid     INT PRIMARY KEY AUTO_INCREMENT,
	title   VARCHAR NOT NULL,
	img     VARCHAR NOT NULL,
	date    TIMESTAMP,
	content VARCHAR NOT NULL
);

INSERT INTO news (title, img, date, content)
VALUES ('사회뉴스', 'tatu.jpg', CURRENT_TIMESTAMP(), '타투한 사람이 늘어가고 있다.');