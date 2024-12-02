package com.bankaccount.eventStoreDB.DTO;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class EventDTO {

    private UUID id;
    private String aggregateId;
    private String eventType;
    private int eventVersion;
    private Object eventData;
    private Instant createdAt;
    private Long version;

}
