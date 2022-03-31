package ru.vtb.konkin.study.mappers;

import ru.vtb.konkin.study.dto.TicketDto;
import ru.vtb.konkin.study.models.Ticket;

public class TicketMapper {
    private TicketMapper() {}

    public static TicketDto modelToDto(Ticket ticket) {
        return new TicketDto(ticket.getPremiereId(), ticket.getAmountOfSeats());
    }

    public static Ticket dtoToModel(TicketDto ticketDto) {
        return new Ticket(ticketDto.getPremiereId(), ticketDto.getAmountOfSeats());
    }
}
