package ru.vtb.konkin.study.mappers;

import ru.vtb.konkin.study.entities.PremiereEntity;
import ru.vtb.konkin.study.models.Premiere;

public class PremiereMapper {
    public static PremiereEntity modelToEntity(Premiere premiere) {
        return new PremiereEntity(premiere.getId(), premiere.getName(), premiere.getDescription(), premiere.getAgeCategory(), premiere.getAvailableSeats());
    }

    public static Premiere entityToModel(PremiereEntity premiereEntity) {
        return new Premiere(premiereEntity.getId(), premiereEntity.getName(), premiereEntity.getDescription(), premiereEntity.getAgeCategory(), premiereEntity.getAvailableSeats());
    }
}
