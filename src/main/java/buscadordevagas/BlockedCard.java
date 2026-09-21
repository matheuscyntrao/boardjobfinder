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

    public BlockedCard(long ID, String title, String description,
                       OffsetDateTime creationDate, boolean isBlocked,
                       long id, String blockReason, String unblockReason,
                       OffsetDateTime blockDate, OffsetDateTime unblockDate ) {
        super(ID, title, description, creationDate, isBlocked);
        this.ID = id;
        this.blockDate = blockDate;
        this.unblockDate = unblockDate;
        this.blockReason = blockReason;
        this.unblockReason = unblockReason;
    }

}
