package ru.vtb.konkin.study.models;

public class Premiere {

    private String id;
    private String name;
    private String description;
    private int ageCategory;
    private int availableSeats;

    public Premiere() {}

    public Premiere(String id, String name, String description, int ageCategory, int availableSeats) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ageCategory = ageCategory;
        this.availableSeats = availableSeats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(int ageCategory) {
        this.ageCategory = ageCategory;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
