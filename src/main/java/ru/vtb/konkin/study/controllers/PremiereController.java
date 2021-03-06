package ru.vtb.konkin.study.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vtb.konkin.study.dto.PremiereDto;
import ru.vtb.konkin.study.dto.TicketDto;
import ru.vtb.konkin.study.mappers.PremiereMapper;
import ru.vtb.konkin.study.mappers.TicketMapper;
import ru.vtb.konkin.study.services.PremiereService;
import ru.vtb.konkin.study.services.TicketService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/premiere")
public class PremiereController {

    private static final Logger log = LoggerFactory.getLogger(PremiereController.class);

    private final PremiereService premiereService;
    private final TicketService ticketService;

    public PremiereController(PremiereService premiereEntityService, TicketService ticketService) {
        this.premiereService = premiereEntityService;
        this.ticketService = ticketService;
    }

    @GetMapping("/info/all")
    public List<PremiereDto> getAllPremiereEntity() {
        return premiereService
                .getPremieres()
                .values()
                .stream()
                .map(PremiereMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/info/{id}")
    public PremiereDto getPremiereEntityById(@PathVariable("id") String id){
        return PremiereMapper.modelToDto(premiereService.getPremiereById(id));

    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public PremiereDto createPremiere(@RequestBody PremiereDto premiereDto) {
        premiereService.addPremiere(PremiereMapper.dtoToModel(premiereDto));
        return PremiereMapper.modelToDto(premiereService.getPremiereById(premiereDto.getId()));
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PremiereDto updatePremiere(@PathVariable("id") String id, @RequestBody PremiereDto premiereDto) {
        if (!Objects.equals(id, premiereDto.getId())) {
            throw new IllegalArgumentException("Hey! Id's do not match!");
        }

        premiereService.update(PremiereMapper.dtoToModel(premiereDto));
        return PremiereMapper.modelToDto(premiereService.getPremiereById(premiereDto.getId()));
    }

    @PostMapping("/buy")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TicketDto buyTicket(@RequestBody TicketDto ticketDto) {
        log.info("\n>> {}", ticketDto);
        return TicketMapper.modelToDto(ticketService.buy(ticketDto.getPremiereId(), ticketDto.getAmountOfSeats()));
    }
}