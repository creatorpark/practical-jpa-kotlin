CREATE TABLE team_uni
(
    id   BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'SEQ_ID',
    name VARCHAR(100) NOT NULL COMMENT '이름',
    PRIMARY KEY (id)
) COMMENT = '팀';

CREATE TABLE player_uni
(
    id             BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'SEQ_ID',
    team_uni_id    BIGINT       NOT NULL COMMENT 'TEAM_UNI_ID(FK)',
    name           VARCHAR(100) NOT NULL COMMENT '이름',
    uniform_number BIGINT       NOT NULL COMMENT '유니폼',
    PRIMARY KEY (id),
    CONSTRAINT fk_player_uni_team_id FOREIGN KEY (team_uni_id) REFERENCES team_uni (id)
) COMMENT = '선수';
