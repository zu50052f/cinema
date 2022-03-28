package ru.vtb.konkin.study.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vtb.konkin.study.entities.PremiereEntity;

public interface PremiereEntityRepository extends JpaRepository<PremiereEntity, String> {
}
