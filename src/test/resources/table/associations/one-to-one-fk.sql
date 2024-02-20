CREATE TABLE user_fk
(
    id   BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'SEQ_ID',
    name VARCHAR(100) NOT NULL COMMENT '이름',
    PRIMARY KEY (id)
) COMMENT = '사용자';

CREATE TABLE address_fk
(
    id      BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'SEQ_ID',
    user_id BIGINT       NOT NULL COMMENT 'USER_ID(FK)',
    street  VARCHAR(100) NOT NULL COMMENT '거리',
    city    VARCHAR(100) NOT NULL COMMENT '도시',
    PRIMARY KEY (id),
    CONSTRAINT fk_address_fk_user_id FOREIGN KEY (user_id) REFERENCES user_fk (id)
) COMMENT = '주소';
