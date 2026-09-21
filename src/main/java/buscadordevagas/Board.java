package buscadordevagas;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter @AllArgsConstructor
public class Board {

    private final long ID;
    private final String boardName;
    private List<Column> columnList;

}
