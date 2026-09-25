package service;

import DAO.CardDAO;
import entity.CardEntity;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CardService {

    private final Connection connection;

    public CardEntity create(final CardEntity entity) throws SQLException {
        var dao = new CardDAO(connection);
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
        var dao = new CardDAO(connection);
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

    public Optional<CardEntity> findById(final Long id) throws SQLException {
        var dao = new CardDAO(connection);
        return dao.findById(id);
    }

    public List<CardEntity> findByColumnId(final Long columnId) throws SQLException {
        var dao = new CardDAO(connection);
        return dao.findByColumnId(columnId);
    }
}