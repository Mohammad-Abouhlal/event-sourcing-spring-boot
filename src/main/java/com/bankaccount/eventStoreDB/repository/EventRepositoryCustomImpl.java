package com.bankaccount.eventStoreDB.repository;

import com.bankaccount.eventStoreDB.entity.EventEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EventRepositoryCustomImpl implements EventRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public EventEntity saveNewEvent(EventEntity eventEntity) {
        Optional<EventEntity> existingEvent = entityManager.createQuery(
                        "SELECT e FROM EventEntity e WHERE e.id = :id AND e.eventVersion = :eventVersion",
                        EventEntity.class
                )
                .setParameter("id", eventEntity.getId())
                .setParameter("eventVersion", eventEntity.getEventVersion())
                .getResultStream()
                .findFirst();

        if (existingEvent.isPresent()) {
            throw new IllegalStateException("Event with the given version already exists and cannot be updated.");
        }
        entityManager.persist(eventEntity);
        return eventEntity;
    }

    @Override
    public Optional<EventEntity> findEventById(UUID id) {
        EventEntity eventEntity = entityManager.find(EventEntity.class, id);
        return Optional.of(eventEntity);
    }

    @Override
    public List<EventEntity> findAllByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        // Custom query logic for finding events by date range
        String query = "SELECT e FROM EventEntity e WHERE e.eventDate BETWEEN :startDate AND :endDate";
        return entityManager.createQuery(query, EventEntity.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    public <T> T getEventById(UUID id, Class<T> clazz) throws Exception {
        EventEntity eventEntity = findEventById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return eventEntity.getEventDataAs(clazz);
    }


}
