package buscadordevagas;

import lombok.Getter;

import java.util.List;

@Getter
public class Board {

    private final long ID;
    private final String boardName;
    private List<Column> columnList;

    public Board(long ID, String boardName) {
        this.ID = ID;
        this.boardName = boardName;
    }
}
