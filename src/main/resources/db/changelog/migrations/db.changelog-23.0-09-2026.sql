--liquibase formatted sql
--changeset matheus:23-09-2026-1
--comment: create board and column tables

CREATE TABLE IF NOT EXISTS board (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     `name` VARCHAR(255) NOT NULL
    ) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `column` (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        `name` VARCHAR(255) NOT NULL,
    `order` INT NOT NULL,
    column_type VARCHAR(20) NOT NULL,
    board_id BIGINT NOT NULL,
    CONSTRAINT board_column_fk FOREIGN KEY (board_id) REFERENCES board(id) ON DELETE CASCADE,
    CONSTRAINT unique_board_id_order UNIQUE KEY (board_id, `order`)
    ) ENGINE=InnoDB;

--rollback DROP TABLE `column`;
--rollback DROP TABLE board;

--liquibase formatted sql
--changeset matheus:23.2-09-2026-2
--comment: create card and block tables

CREATE TABLE IF NOT EXISTS `card` (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      title VARCHAR(255) NOT NULL,
    `order` INT NOT NULL,
    description VARCHAR(255) NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    board_column_id BIGINT NOT NULL,
    CONSTRAINT board_column_card_fk FOREIGN KEY (board_column_id) REFERENCES `column`(id) ON DELETE CASCADE
    ) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `block` (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       block_reason VARCHAR(255) NOT NULL,
    unblock_reason VARCHAR(255) NULL,
    block_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    unblock_date TIMESTAMP NULL,
    card_id BIGINT NOT NULL,
    CONSTRAINT card_block_fk FOREIGN KEY (card_id) REFERENCES `card`(id) ON DELETE CASCADE
    ) ENGINE=InnoDB;

--rollback DROP TABLE `block`;
--rollback DROP TABLE `card`;