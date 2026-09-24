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