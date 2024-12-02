package com.bankaccount.eventStoreDB.controller;

import com.bankaccount.eventStoreDB.DTO.EventDTO;
import com.bankaccount.eventStoreDB.entity.EventEntity;
import com.bankaccount.eventStoreDB.service.EventStoreService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
@Data
public class EventController {

    private final EventStoreService eventService;


    @GetMapping("/{id}")
    public EventDTO getEvent(@PathVariable UUID id) {
        return eventService.getEventById(id);
    }

    @PostMapping
    public EventEntity addEvent(@RequestBody EventEntity eventEntity) {
        System.out.println("Received event: " + eventEntity);
        return eventService.addNewEvent(eventEntity);
    }

    @GetMapping("/date-range")
    public List<EventEntity> getEventsByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam int pageSize
    ) {
        return eventService.getAllEventsByDateRange(startDate, endDate, pageSize);
    }
}