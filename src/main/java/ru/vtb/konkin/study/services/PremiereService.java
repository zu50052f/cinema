package ru.vtb.konkin.study.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.konkin.study.mappers.PremiereMapper;
import ru.vtb.konkin.study.models.Premiere;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class PremiereService {
    @Autowired
    PremiereEntityService premiereEntityService;

    public void addPremiere(Premiere premiere) {
        premiereEntityService.saveOrUpdate(PremiereMapper.modelToEntity(premiere));
    }

    public Premiere getPremiereById(String id) {
        return PremiereMapper.entityToModel(premiereEntityService.getPremiereEntityById(id));
    }

    public Map<String, Premiere> getPremieres() {
        HashMap<String, Premiere> premieres = new HashMap<>();
        premiereEntityService.getAllPremiereEntity().forEach(premiereEntity -> {
            Premiere premiere = PremiereMapper.entityToModel(premiereEntity);
            premieres.put(premiere.getId(), premiere);
        });
        return premieres;
    }

    public void listPremieres() {
        Map<String, Premiere> premieres = getPremieres();
        log.info(">>  Attention! Look to the list of available premieres below");
        premieres.forEach((k, v) -> log.info(">>    K: {}, V {}.", k, v.getName()));
    }

    public void bookSomeSeats(String premiereId, int amountOfSeats) {
        Premiere premiere = getPremiereById(premiereId);
        log.info(">>  Ready to book {} seats to premiere {}.", amountOfSeats, premiere.getName());

        if (premiere.getAvailableSeats() < amountOfSeats) {
            log.error(">>    Can't complete your request! There is just {} seats available!",  premiere.getAvailableSeats());
        } else {
            premiereEntityService.updateAvailableSeatsById(premiereId, premiere.getAvailableSeats() - amountOfSeats);
            log.info(">>    Success book of {} seats on premiere {}.", amountOfSeats, premiere.getName());
        }
    }

    public void changeAgeCategory(String premiereId, int ageCategory) {
        if (ageCategory < 0) {
            log.error(">>  Can not change age category! Age category must be positive integer number!");
            return;
        }

        Premiere premiere = getPremiereById(premiereId);
        log.info(">>  Ready to change age category to {} for premiere {}.", ageCategory, premiere.getName());

        if (premiere.getAgeCategory() == ageCategory) {
            log.warn(">>    Age category for premiere {} is {} yet.", premiere.getName(), premiere.getAgeCategory());
        } else {
            premiereEntityService.updateAgeCategoryById(premiereId, ageCategory);
            log.info(">>    Success update of age category to {} for premiere {}.", ageCategory, premiere.getName());
        }
    }

    public void removePremiere(String premiereId) {
        log.info(">>  Ready to remove premiere with id {}.", premiereId);

        premiereEntityService.deleteById(premiereId);

        log.info(">>  Premiere with id {} has been successfully removed.", premiereId);
    }

    public void printPremiereInfo(String premiereId) {
        Premiere premiere = getPremiereById(premiereId);
        if (premiere != null) {
            log.info(">>  Details of premiere with id {} are below:\n" +
                    ">>    Name: {}\n" +
                    ">>    Description: {}\n" +
                    ">>    Age category: {}\n" +
                    ">>    Available seats: {}", premiereId, premiere.getName(), premiere.getDescription(), premiere.getAgeCategory(), premiere.getAvailableSeats());
        } else {
            log.error("There is no premiere with id {}!", premiereId);
        }
    }

    public void showTransactionsInAction() {
        log.info("\n>>  Start interrupted transaction demo!\n>>    Trying to delete");
        listPremieres();
        try {
            premiereEntityService.stepByStepDeleteToShowTransactionalAnnotationAtWork("1");
        } catch (IllegalArgumentException illegalArgumentException) {
            log.info("\n>>    Exception on delete caught!");
        }
        listPremieres();

        log.info("\n>>  Now delete and throw without transactional annotation");
        try {
            premiereEntityService.stepByStepDeleteToShowTransactionalAnnotationAtWork("1", true);
        } catch (IllegalArgumentException illegalArgumentException) {
            log.info("\n>>    Exception on delete caught!");
        }
        listPremieres();

        log.info("\n>>  The end.");
    }
}
