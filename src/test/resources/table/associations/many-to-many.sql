CREATE TABLE post
(
    id       BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'SEQ_ID',
    title    VARCHAR(100) NOT NULL COMMENT '제목',
    contents VARCHAR(100) NOT NULL COMMENT '내용',
    PRIMARY KEY (id)
) COMMENT = '글';


CREATE TABLE tag
(
    id   BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'SEQ_ID',
    name VARCHAR(100) NOT NULL COMMENT '이름',
    PRIMARY KEY (id)
) COMMENT = '태그';

CREATE TABLE post_tag
(
    post_id BIGINT NOT NULL COMMENT 'SEQ_ID',
    tag_id  BIGINT NOT NULL COMMENT 'SEQ_ID',
    PRIMARY KEY (post_id, tag_id),
    CONSTRAINT fk_post_tag_id1 FOREIGN KEY (post_id) REFERENCES post (id),
    CONSTRAINT fk_post_tag_id2 FOREIGN KEY (tag_id) REFERENCES tag (id)
) COMMENT = '포스트 태그 상세';


