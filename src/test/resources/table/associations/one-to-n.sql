CREATE TABLE _order
(
    id   BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'SEQ_ID',
    name VARCHAR(100) NOT NULL COMMENT '이름',
    PRIMARY KEY (id)
) COMMENT = '주문';

CREATE TABLE order_item
(
    id       BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'SEQ_ID',
    order_id BIGINT       NULL COMMENT 'ORDER_ID(FK)',
    name     VARCHAR(100) NOT NULL COMMENT '이름',
    count    BIGINT       NOT NULL COMMENT 'COUNT',
    price    BIGINT       NOT NULL COMMENT 'PRICE',
    PRIMARY KEY (id),
    CONSTRAINT fk_order_item_order_id FOREIGN KEY (order_id) REFERENCES _order (id)
) COMMENT = '주문 아이템';
