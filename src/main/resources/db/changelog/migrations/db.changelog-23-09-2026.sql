--liquibase formatted sql
--changeset matheus:23-09-2026
--comment: board table create

CREATE TABLE IF NOT EXISTS board (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL
    ) ENGINE=InnoDB;

--rollback DROP TABLE board;