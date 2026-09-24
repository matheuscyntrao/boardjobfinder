package buscadordevagas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    private Long id;
    private String title;
    private Integer order;
    private String description;
    private OffsetDateTime creationDate;
    private Boolean isBlocked;
}