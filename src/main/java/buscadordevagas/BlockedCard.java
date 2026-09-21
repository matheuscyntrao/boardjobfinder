package buscadordevagas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
public class BlockedCard extends Card {

    private final long ID;
    private String blockReason;
    private String unblockReason;
    private OffsetDateTime blockDate;
    private OffsetDateTime unblockDate;

}
