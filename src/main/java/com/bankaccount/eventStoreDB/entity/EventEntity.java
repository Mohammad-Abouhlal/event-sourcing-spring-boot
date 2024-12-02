package com.bankaccount.eventStoreDB.entity;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Builder
@Table(name = "evententity")
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    /*
    * The Aggregate ID is a key concept in Domain-Driven Design (DDD) and event sourcing.
    * It serves as a unique identifier for a group of related domain objects,
    * called an aggregate, which are treated as a single unit for the purposes of data changes and business logic
    */
    private String aggregateId;
    private String eventType;
    private int eventVersion;

    /*
     * It is content of Event like what was changed.
     */
    //@JdbcTypeCode(SqlTypes.JSON)
    private String eventData;
    @CreationTimestamp
    private Instant createdAt;

    @Version
    private Long version;

    // Utility method to deserialize eventData into the appropriate event object
    public <T> T getEventDataAs(Class<T> clazz) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(eventData, clazz);
    }
}
