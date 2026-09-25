package entity;

import entity.ColumnType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnEntity {
    private Long id;
    private String name;
    private int order;
    private ColumnType columnType;
    private Long boardId;
}