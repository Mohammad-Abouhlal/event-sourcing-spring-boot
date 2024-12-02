package com.bankaccount.eventStoreDB.repository;

import org.springframework.data.domain.Pageable;
import com.bankaccount.eventStoreDB.entity.EventEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepositoryCustom {

    EventEntity saveNewEvent(EventEntity eventEntity);

    Optional<EventEntity> findEventById(UUID id);

    List<EventEntity> findAllByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
