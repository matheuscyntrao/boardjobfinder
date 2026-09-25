package DAO;

import entity.CardEntity;
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
public class CardDAO {

    private final Connection connection;

    public CardEntity insert(final CardEntity entity) throws SQLException {
        var sql = "INSERT INTO `card` (title, `order`, description, board_column_id) VALUES (?, ?, ?, ?)";
        try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getTitle());
            statement.setInt(2, entity.getOrder());
            statement.setString(3, entity.getDescription());
            statement.setLong(4, entity.getBoardColumnId());
            statement.executeUpdate();

            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getLong(1));
                }
            }
        }
        return entity;
    }

    public void delete(final Long id) throws SQLException {
        var sql = "DELETE FROM `card` WHERE id = ?";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    public Optional<CardEntity> findById(final Long id) throws SQLException {
        var sql = "SELECT id, title, `order`, description, creation_date, board_column_id FROM `card` WHERE id = ?";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    var entity = new CardEntity();
                    entity.setId(resultSet.getLong("id"));
                    entity.setTitle(resultSet.getString("title"));
                    entity.setOrder(resultSet.getInt("order"));
                    entity.setDescription(resultSet.getString("description"));

                    var timestamp = resultSet.getTimestamp("creation_date");
                    if (timestamp != null) {
                        entity.setCreationDate(timestamp.toInstant().atOffset(ZoneOffset.UTC));
                    }

                    entity.setBoardColumnId(resultSet.getLong("board_column_id"));
                    return Optional.of(entity);
                }
            }
        }
        return Optional.empty();
    }

    public List<CardEntity> findByColumnId(final Long columnId) throws SQLException {
        var sql = "SELECT id, title, `order`, description, creation_date, board_column_id FROM `card` WHERE board_column_id = ? ORDER BY `order` ASC";
        var cards = new ArrayList<CardEntity>();
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, columnId);
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var entity = new CardEntity();
                    entity.setId(resultSet.getLong("id"));
                    entity.setTitle(resultSet.getString("title"));
                    entity.setOrder(resultSet.getInt("order"));
                    entity.setDescription(resultSet.getString("description"));

                    var timestamp = resultSet.getTimestamp("creation_date");
                    if (timestamp != null) {
                        entity.setCreationDate(timestamp.toInstant().atOffset(ZoneOffset.UTC));
                    }

                    entity.setBoardColumnId(resultSet.getLong("board_column_id"));
                    cards.add(entity);
                }
            }
        }
        return cards;
    }

    public boolean exists(final Long id) throws SQLException {
        var sql = "SELECT 1 FROM `card` WHERE id = ?";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (var resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}