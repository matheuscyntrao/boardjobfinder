package entity;

import buscadordevagas.ColumnType;
import lombok.Data;

@Data
public class BoardColumnEntity {

    private Long id;
    private String name;
    private Integer order;
    private ColumnType columnType;

}
