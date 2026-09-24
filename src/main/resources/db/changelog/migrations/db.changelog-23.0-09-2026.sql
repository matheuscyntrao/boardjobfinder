--liquibase formatted sql
--changeset matheus:23-09-2026-1
--comment: create board and column tables

CREATE TABLE IF NOT EXISTS board (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL
    ) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `column` (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
    `order` INT NOT NULL,
    column_type VARCHAR(20) NOT NULL,
    board_id BIGINT NOT NULL,
    CONSTRAINT board_column_fk FOREIGN KEY (board_id) REFERENCES board(id) ON DELETE CASCADE,
    CONSTRAINT unique_board_id_order UNIQUE KEY (board_id, `order`)
    ) ENGINE=InnoDB;

--rollback DROP TABLE `column`;
--rollback DROP TABLE board;