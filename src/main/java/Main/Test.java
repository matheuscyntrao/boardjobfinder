package Main;

import configuration.ConnectionConfig;
import entity.BlockEntity;
import entity.BoardEntity;
import entity.CardEntity;
import entity.ColumnEntity;
import entity.ColumnType;
import migration.MigrationStrategy;
import service.BlockService;
import service.BoardService;
import service.CardService;
import service.ColumnService;

import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {
        try (var connection = ConnectionConfig.getConnection()) {
            new MigrationStrategy(connection).executeMigration();

            var boardService = new BoardService(connection);
            var columnService = new ColumnService(connection);
            var cardService = new CardService(connection);
            var blockService = new BlockService(connection);

            var board = new BoardEntity(null, "Board de Desenvolvimento");
            board = boardService.create(board);

            var columnBacklog = new ColumnEntity(null, "Backlog", 1, ColumnType.BACKLOG, board.getId());
            var columnDoing = new ColumnEntity(null, "Em Progresso", 2, ColumnType.DOING, board.getId());

            columnBacklog = columnService.create(columnBacklog);
            columnDoing = columnService.create(columnDoing);

            var card = new CardEntity(null, "Implementar DAOs", 1, "Criar DAOs e Services", null, columnBacklog.getId());
            card = cardService.create(card);

            var block = new BlockEntity(null, "Aguardando aprovação de PR", null, null, null, card.getId());
            block = blockService.blockCard(block);

            blockService.findActiveBlock(card.getId());

            blockService.unblockCard(card.getId(), "PR Aprovado");

            //boardService.delete(board.getId());

            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


