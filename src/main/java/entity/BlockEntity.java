package entity;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class BlockEntity {

    private Long id;
    private String block_reason;
    private String unblock_reason;
    private OffsetDateTime block_date;
    private OffsetDateTime unblock_date;

}
