package com.bankaccount.eventStoreDB.mapper;

import com.bankaccount.eventStoreDB.DTO.EventDTO;
import com.bankaccount.eventStoreDB.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    EventDTO eventEntityToEventDTO(EventEntity eventEntity) throws Exception;
}