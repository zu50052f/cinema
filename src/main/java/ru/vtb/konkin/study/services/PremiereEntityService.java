package ru.vtb.konkin.study.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public void saveOrUpdate(PremiereEntity premiereEntity) {
        premiereEntityRepository.save(premiereEntity);
    }

    @Transactional
    public void deleteById(String id) {
        premiereEntityRepository.deleteById(id);
    }

    @Transactional
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

    @Transactional
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


    @Transactional
    public void stepByStepDeleteToShowTransactionalAnnotationAtWork(String id) {
        premiereEntityRepository.deleteById(id);
        throw new IllegalArgumentException("Age category can't be greater than 100!");
    }

    public void stepByStepDeleteToShowTransactionalAnnotationAtWork(String id, boolean nontransactional) {
        premiereEntityRepository.deleteById(id);
        throw new IllegalArgumentException("Age category can't be greater than 100!");
    }
}
