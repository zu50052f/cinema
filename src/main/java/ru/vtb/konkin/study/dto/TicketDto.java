package ru.vtb.konkin.study.dto;

public class TicketDto {
    String premiereId;
    int amountOfSeats;

    public TicketDto(String premiereId, int amountOfSeats) {
        this.premiereId = premiereId;
        this.amountOfSeats = amountOfSeats;
    }

    public TicketDto() {}

    public String getPremiereId() {
        return premiereId;
    }

    public void setPremiereId(String premiereId) {
        this.premiereId = premiereId;
    }

    public int getAmountOfSeats() {
        return amountOfSeats;
    }

    public void setAmountOfSeats(int amountOfSeats) {
        this.amountOfSeats = amountOfSeats;
    }

    @Override
    public String toString() {
        return "TicketDto{" +
                "premiereId='" + premiereId + '\'' +
                ", amountOfSeats=" + amountOfSeats +
                '}';
    }
}
