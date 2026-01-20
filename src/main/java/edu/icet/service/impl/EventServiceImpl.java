package edu.icet.service.impl;

import edu.icet.model.dto.EventDTO;
import edu.icet.model.entity.EventEntity;
import edu.icet.repository.EventRepository;
import edu.icet.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveEvent(EventDTO eventDto) {

        EventEntity eventEntity = modelMapper.map(eventDto, EventEntity.class);


        eventRepository.save(eventEntity);
    }
}