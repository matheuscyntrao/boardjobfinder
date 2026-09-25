package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockEntity {
    private Long id;
    private String blockReason;
    private String unblockReason;
    private OffsetDateTime blockDate;
    private OffsetDateTime unblockDate;
    private Long cardId;
}