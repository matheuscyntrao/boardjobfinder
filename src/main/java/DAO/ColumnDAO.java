package DAO;

import entity.ColumnType;
import entity.ColumnEntity;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ColumnDAO {

    private final Connection connection;

    public ColumnEntity insert(final ColumnEntity entity) throws SQLException {
        var sql = "INSERT INTO `column` (name, `order`, column_type, board_id) VALUES (?, ?, ?, ?)";
        try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getOrder());
            statement.setString(3, entity.getColumnType().name());
            statement.setLong(4, entity.getBoardId());
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
        var sql = "DELETE FROM `column` WHERE id = ?";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    public Optional<ColumnEntity> findById(final Long id) throws SQLException {
        var sql = "SELECT id, name, `order`, column_type, board_id FROM `column` WHERE id = ?";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    var entity = new ColumnEntity();
                    entity.setId(resultSet.getLong("id"));
                    entity.setName(resultSet.getString("name"));
                    entity.setOrder(resultSet.getInt("order"));
                    entity.setColumnType(ColumnType.valueOf(resultSet.getString("column_type")));
                    entity.setBoardId(resultSet.getLong("board_id"));
                    return Optional.of(entity);
                }
            }
        }
        return Optional.empty();
    }

    public List<ColumnEntity> findByBoardId(final Long boardId) throws SQLException {
        var sql = "SELECT id, name, `order`, column_type, board_id FROM `column` WHERE board_id = ? ORDER BY `order` ASC";
        var columns = new ArrayList<ColumnEntity>();
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, boardId);
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var entity = new ColumnEntity();
                    entity.setId(resultSet.getLong("id"));
                    entity.setName(resultSet.getString("name"));
                    entity.setOrder(resultSet.getInt("order"));
                    entity.setColumnType(ColumnType.valueOf(resultSet.getString("column_type")));
                    entity.setBoardId(resultSet.getLong("board_id"));
                    columns.add(entity);
                }
            }
        }
        return columns;
    }

    public boolean exists(final Long id) throws SQLException {
        var sql = "SELECT 1 FROM `column` WHERE id = ?";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (var resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}