CREATE TABLE increment_id_entity
(
    id   BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'SEQ_ID',
    name VARCHAR(100) NOT NULL COMMENT 'CountryCode',
    PRIMARY KEY (id)
) COMMENT = '자동증가 엔티티';

CREATE TABLE ts_id_entity
(
    id   BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'SEQ_ID',
    name VARCHAR(100) NOT NULL COMMENT 'CountryCode',
    PRIMARY KEY (id)
) COMMENT = 'TSID 엔티티';
