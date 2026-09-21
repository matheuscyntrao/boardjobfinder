package buscadordevagas;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
public class Card {

    private long ID;
    private String title;
    private String description;
    private OffsetDateTime creationDate;
    private boolean isBlocked;

    public Card() {
        // Lombok default constructor
    }

}
