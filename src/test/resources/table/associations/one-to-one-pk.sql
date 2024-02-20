CREATE TABLE _user
(
    id   BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'SEQ_ID',
    name VARCHAR(100) NOT NULL COMMENT '이름',
    PRIMARY KEY (id)
) COMMENT = '사용자';

CREATE TABLE address
(
    user_id BIGINT       NOT NULL COMMENT 'USER_ID',
    street  VARCHAR(100) NOT NULL COMMENT '거리',
    city    VARCHAR(100) NOT NULL COMMENT '도시',
    PRIMARY KEY (user_id),
    CONSTRAINT fk_address_user_id FOREIGN KEY (user_id) REFERENCES _user (id)
) COMMENT = '주소';
