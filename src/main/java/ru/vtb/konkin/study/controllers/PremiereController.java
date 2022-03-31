package ru.vtb.konkin.study.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vtb.konkin.study.dto.PremiereDto;
import ru.vtb.konkin.study.mappers.PremiereMapper;
import ru.vtb.konkin.study.services.PremiereService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class PremiereController {

    private static final Logger log = LoggerFactory.getLogger(PremiereController.class);

    private final PremiereService premiereService;

    public PremiereController(PremiereService premiereEntityService) {
        this.premiereService = premiereEntityService;
    }

    @GetMapping("/premieres")
    public List<PremiereDto> getAllPremiereEntity() {
        return premiereService
                .getPremieres()
                .values()
                .stream()
                .map(PremiereMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/premiere/{id}")
    public PremiereDto getPremiereEntityById(@PathVariable("id") String id){
        return PremiereMapper.modelToDto(premiereService.getPremiereById(id));

    }

    @PostMapping("/premiere")
    @ResponseStatus(HttpStatus.CREATED)
    public PremiereDto createPremiere(@RequestBody PremiereDto premiereDto) {
        premiereService.addPremiere(PremiereMapper.dtoToModel(premiereDto));
        return PremiereMapper.modelToDto(premiereService.getPremiereById(premiereDto.getId()));
    }

    @PutMapping("/premiere/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PremiereDto updatePremiere(@PathVariable("id") String id, @RequestBody PremiereDto premiereDto) {
        if (!Objects.equals(id, premiereDto.getId())) {
            throw new IllegalArgumentException("Hey! Id's do not match!");
        }

        premiereService.update(PremiereMapper.dtoToModel(premiereDto));
        return PremiereMapper.modelToDto(premiereService.getPremiereById(premiereDto.getId()));
    }
}