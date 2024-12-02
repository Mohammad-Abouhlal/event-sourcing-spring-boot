package com.bankaccount.eventStoreDB.service;

import com.bankaccount.eventStoreDB.DTO.EventDTO;
import com.bankaccount.eventStoreDB.entity.EventEntity;
import com.bankaccount.eventStoreDB.mapper.EventMapper;
import com.bankaccount.eventStoreDB.repository.EventRepositoryCustom;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Data
public class EventStoreServiceImpl implements EventStoreService {

    private static String FQ_NAME = "com.bankaccount.account.event.";

    private final EventRepositoryCustom eventRepository;

    private final ObjectMapper objectMapper;

    private final EventMapper eventMapper;

    @Override
    public EventEntity addNewEvent(EventEntity eventEntity) {
        return eventRepository.saveNewEvent(eventEntity);
    }

    @Override
    public EventDTO getEventById(UUID id) {
        EventEntity eventEntity = eventRepository.findEventById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        EventDTO eventDTO = null;
        try {
            Class<?> clazzEventType = Class.forName( FQ_NAME +
                    eventEntity.getEventType());

            Object eventData = eventEntity.getEventDataAs(clazzEventType);

            eventDTO = eventMapper.eventEntityToEventDTO(eventEntity);
            eventDTO.setEventData(eventData);
        } catch (Exception e) {
            throw new RuntimeException("Could not retrieve Event: " + eventEntity.getId() + e);
        }

        return eventDTO;
    }

    @Override
    public List<EventEntity> getAllEventsByDateRange(LocalDate startDate, LocalDate endDate, int pageSize) {
        PageRequest pageRequest = PageRequest.of(0, pageSize);
        return eventRepository.findAllByDateRange(startDate, endDate, pageRequest);
    }
}
