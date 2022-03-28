package ru.vtb.konkin.study.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.konkin.study.entities.PremiereEntity;
import ru.vtb.konkin.study.repositories.PremiereEntityRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PremiereEntityService {
    @Autowired
    PremiereEntityRepository premiereEntityRepository;

    public List<PremiereEntity> getAllPremiereEntity() {
        return new ArrayList<>(premiereEntityRepository.findAll());
    }

    public PremiereEntity getPremiereEntityById(String id) {
        Optional<PremiereEntity> premiereEntity = premiereEntityRepository.findById(id);
        return premiereEntity.orElse(null);
    }

    public void saveOrUpdate(PremiereEntity premiereEntity) {
        premiereEntityRepository.save(premiereEntity);
    }

    public void deleteById(String id) {
        premiereEntityRepository.deleteById(id);
    }

    public void updateAvailableSeatsById(String id, int availableSeats) {
        Optional<PremiereEntity> optionalPremiereEntity = premiereEntityRepository.findById(id);
        if (optionalPremiereEntity.isPresent()) {
            PremiereEntity premiereEntity = optionalPremiereEntity.get();
            premiereEntity.setAvailableSeats(availableSeats);
            premiereEntityRepository.save(premiereEntity);
        } else {
            throw new EntityNotFoundException("There is no premiere with id " + id);
        }
    }

    public void updateAgeCategoryById(String id, int ageCategory) {
        Optional<PremiereEntity> optionalPremiereEntity = premiereEntityRepository.findById(id);
        if (optionalPremiereEntity.isPresent()) {
            PremiereEntity premiereEntity = optionalPremiereEntity.get();
            premiereEntity.setAgeCategory(ageCategory);
            premiereEntityRepository.save(premiereEntity);
        } else {
            throw new EntityNotFoundException("There is no premiere with id " + id);
        }
    }
}
