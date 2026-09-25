package service;

import DAO.BlockDAO;
import entity.BlockEntity;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
public class BlockService {

    private final Connection connection;

    public BlockEntity blockCard(final BlockEntity entity) throws SQLException {
        var dao = new BlockDAO(connection);
        try {
            if (dao.isCardBlocked(entity.getCardId())) {
                throw new IllegalStateException("O card já se encontra bloqueado.");
            }
            var createdEntity = dao.insert(entity);
            connection.commit();
            return createdEntity;
        } catch (SQLException | IllegalStateException e) {
            connection.rollback();
            throw e;
        }
    }

    public boolean unblockCard(final Long cardId, final String unblockReason) throws SQLException {
        var dao = new BlockDAO(connection);
        try {
            if (!dao.isCardBlocked(cardId)) {
                return false;
            }
            dao.unblock(cardId, unblockReason);
            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    public Optional<BlockEntity> findActiveBlock(final Long cardId) throws SQLException {
        var dao = new BlockDAO(connection);
        return dao.findActiveByCardId(cardId);
    }
}