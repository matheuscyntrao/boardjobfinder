package DAO;

import entity.BlockEntity;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class BlockDAO {

    private final Connection connection;

    public BlockEntity insert(final BlockEntity entity) throws SQLException {
        var sql = "INSERT INTO `block` (block_reason, card_id) VALUES (?, ?)";
        try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getBlockReason());
            statement.setLong(2, entity.getCardId());
            statement.executeUpdate();

            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getLong(1));
                }
            }
        }
        return entity;
    }

    public void unblock(final Long cardId, final String unblockReason) throws SQLException {
        var sql = "UPDATE `block` SET unblock_reason = ?, unblock_date = CURRENT_TIMESTAMP WHERE card_id = ? AND unblock_date IS NULL";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setString(1, unblockReason);
            statement.setLong(2, cardId);
            statement.executeUpdate();
        }
    }

    public Optional<BlockEntity> findActiveByCardId(final Long cardId) throws SQLException {
        var sql = "SELECT id, block_reason, unblock_reason, block_date, unblock_date, card_id FROM `block` WHERE card_id = ? AND unblock_date IS NULL";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, cardId);
            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    var entity = new BlockEntity();
                    entity.setId(resultSet.getLong("id"));
                    entity.setBlockReason(resultSet.getString("block_reason"));
                    entity.setUnblockReason(resultSet.getString("unblock_reason"));

                    var blockTimestamp = resultSet.getTimestamp("block_date");
                    if (blockTimestamp != null) {
                        entity.setBlockDate(blockTimestamp.toInstant().atOffset(ZoneOffset.UTC));
                    }

                    var unblockTimestamp = resultSet.getTimestamp("unblock_date");
                    if (unblockTimestamp != null) {
                        entity.setUnblockDate(unblockTimestamp.toInstant().atOffset(ZoneOffset.UTC));
                    }

                    entity.setCardId(resultSet.getLong("card_id"));
                    return Optional.of(entity);
                }
            }
        }
        return Optional.empty();
    }

    public boolean isCardBlocked(final Long cardId) throws SQLException {
        var sql = "SELECT 1 FROM `block` WHERE card_id = ? AND unblock_date IS NULL";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, cardId);
            try (var resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}