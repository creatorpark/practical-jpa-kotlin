CREATE TABLE team
(
    id   BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'SEQ_ID',
    name VARCHAR(100) NOT NULL COMMENT '이름',
    PRIMARY KEY (id)
) COMMENT = '팀';

CREATE TABLE player
(
    id             BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'SEQ_ID',
    team_id        BIGINT       NULL COMMENT 'TEAM_ID(FK)',
    name           VARCHAR(100) NOT NULL COMMENT '이름',
    uniform_number BIGINT       NOT NULL COMMENT '유니폼',
    PRIMARY KEY (id),
    CONSTRAINT fk_player_team_id FOREIGN KEY (team_id) REFERENCES team (id)
) COMMENT = '선수';
