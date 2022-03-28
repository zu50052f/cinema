package ru.vtb.konkin.study;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vtb.konkin.study.entities.PremiereEntity;
import ru.vtb.konkin.study.services.PremiereEntityService;

@SpringBootTest
class PremiereEntityServiceTests {

    @Autowired
    PremiereEntityService premiereEntityService;

    final String JAMES_BOND_ID = "JAMES_BOND_ID";
    final String JAMES_BOND = "JAMES_BOND";
    final int DEFAULT_AVAILABLE_SEATS = 100;
    final PremiereEntity premiereEntity = new PremiereEntity(JAMES_BOND_ID, JAMES_BOND, "The most known secret agent", 10, DEFAULT_AVAILABLE_SEATS);

    @Test
    void saveNewPremiere() {
        premiereEntityService.saveOrUpdate(premiereEntity);
    }

    @Test
    void saveAndGetPremiere() {
        premiereEntityService.saveOrUpdate(premiereEntity);
        final String premiereName = premiereEntityService.getPremiereEntityById(JAMES_BOND_ID).getName();
        assert premiereName.equals(JAMES_BOND) : "Name does not match!";
    }

    @Test
    void deletePremiere() {
        premiereEntityService.saveOrUpdate(premiereEntity);
        premiereEntityService.deleteById(premiereEntity.getId());
        final PremiereEntity premiereEntity = premiereEntityService.getPremiereEntityById(JAMES_BOND_ID);
        assert premiereEntity == null : "Premiere exists but should not!";
    }

    @Test
    void updateAvailableSeats() {
        final int diff = 20;
        PremiereEntity premiereEntity = new PremiereEntity(JAMES_BOND_ID, JAMES_BOND, "The most known secret agent", 10, 100);
        premiereEntityService.saveOrUpdate(premiereEntity);
        assert premiereEntityService.getPremiereEntityById(JAMES_BOND_ID).getAvailableSeats() == DEFAULT_AVAILABLE_SEATS : "Wrong initial available_seats" ;
        premiereEntityService.updateAvailableSeatsById(JAMES_BOND_ID, premiereEntity.getAvailableSeats() - diff);
        assert premiereEntityService.getPremiereEntityById(JAMES_BOND_ID).getAvailableSeats() == DEFAULT_AVAILABLE_SEATS - diff: "Wrong updated available_seats" ;
    }
}
