package service;

import DAO.ColumnDAO;
import entity.ColumnEntity;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ColumnService {

    private final Connection connection;

    public ColumnEntity create(final ColumnEntity entity) throws SQLException {
        var dao = new ColumnDAO(connection);
        try {
            var createdEntity = dao.insert(entity);
            connection.commit();
            return createdEntity;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    public boolean delete(final Long id) throws SQLException {
        var dao = new ColumnDAO(connection);
        try {
            if (!dao.exists(id)) {
                return false;
            }
            dao.delete(id);
            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    public Optional<ColumnEntity> findById(final Long id) throws SQLException {
        var dao = new ColumnDAO(connection);
        return dao.findById(id);
    }

    public List<ColumnEntity> findByBoardId(final Long boardId) throws SQLException {
        var dao = new ColumnDAO(connection);
        return dao.findByBoardId(boardId);
    }
}