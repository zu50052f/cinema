package ru.vtb.konkin.study.services;

import org.springframework.stereotype.Service;
import ru.vtb.konkin.study.models.Premiere;
import ru.vtb.konkin.study.models.Ticket;

@Service
public class TicketService {

    private final PremiereService premiereService;

    public TicketService(PremiereService premiereService) {
        this.premiereService = premiereService;
    }

    public Ticket buy(String premiereId, int amountOfSeats) {
        Premiere premiere = premiereService.getPremiereById(premiereId);
        if (premiere == null) {
            throw new IllegalArgumentException("No premiere with id " + premiereId + " exists");
        }

        premiereService.bookSomeSeats(premiereId, amountOfSeats);
        return new Ticket(premiereId, amountOfSeats);
    }
}
