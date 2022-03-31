package ru.vtb.konkin.study.models;

public class Ticket {
    String premiereId;
    int amountOfSeats;

    public Ticket(String premiereId, int amountOfSeats) {
        this.premiereId = premiereId;
        this.amountOfSeats = amountOfSeats;
    }

    public Ticket() {}

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
        return "Ticket{" +
                "premiereId='" + premiereId + '\'' +
                ", amountOfSeats=" + amountOfSeats +
                '}';
    }
}
