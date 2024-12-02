package com.bankaccount.eventStoreDB.service;

import com.bankaccount.eventStoreDB.DTO.EventDTO;
import com.bankaccount.eventStoreDB.entity.EventEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface EventStoreService {

    EventEntity addNewEvent(EventEntity eventEntity);

    EventDTO getEventById(UUID id);

    List<EventEntity> getAllEventsByDateRange(LocalDate startDate, LocalDate endDate, int pageSize);
}
