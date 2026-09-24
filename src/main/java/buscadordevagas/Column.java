package buscadordevagas;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter @AllArgsConstructor
public class Column {

    private final Long id;
    private final String name;
    private ColumnType columnType;
    private Integer order;
    private List<Card> cardList;

}
