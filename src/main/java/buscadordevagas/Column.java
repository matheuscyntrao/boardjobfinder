package buscadordevagas;

import lombok.Getter;

import java.util.List;

@Getter
public class Column {

    private final Long id;
    private final String name;
    private ColumnType columnType;
    private Long order;
    private List<Card> cardList;

    public Column(Long id, String name, ColumnType type, Long order) {
        this.id = id;
        this.name = name;
        this.columnType = type;
        this.order = order;
    }

}
