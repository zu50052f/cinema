package ru.vtb.konkin.study.entities;

import javax.persistence.*;

/**
 * Premiere entity, matches premiere table
 */
@Entity
@Table(name = "premiere")
public class PremiereEntity {

    @Id
    @Column
    private String id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "age_category")
    private int ageCategory;

    @Column(name = "available_seats")
    private int availableSeats;

    public PremiereEntity(String id, String name, String description, int ageCategory, int availableSeats) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ageCategory = ageCategory;
        this.availableSeats = availableSeats;
    }

    public PremiereEntity() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(int ageCategory) {
        this.ageCategory = ageCategory;
    }
}
