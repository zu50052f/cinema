package ru.vtb.konkin.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.vtb.konkin.study.models.Premiere;
import ru.vtb.konkin.study.services.PremiereService;

@SpringBootApplication
@Slf4j
public class CinemaApplication {
    @Autowired
    PremiereService premiereService;

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class);
    }

    @Bean
    public void demo() {
        log.info("\n\n\n\n###################\n>>  Demo Start\n###################\n\n");

        // 1) Добавлять новые премьеры (с указанием названия, подробного описания, возрастной категории и количество доступных мест)
        premiereService.addPremiere(new Premiere("1", "PREMIERE_1", "DESCRIPTION", 10, 100));
        premiereService.addPremiere(new Premiere("2", "PREMIERE_2", "DESCRIPTION", 10, 100));
        premiereService.listPremieres();

        // 2) Изменять показатели премьеры
        premiereService.bookSomeSeats("1", 60);
        premiereService.bookSomeSeats("1", 60);
        premiereService.changeAgeCategory("1", 5);
        premiereService.changeAgeCategory("1", -1);
        premiereService.changeAgeCategory("1", 101);

        // 3) Удалять мероприятие
        premiereService.removePremiere("2");

        // 4) Просматривать список всех мероприятий и какого-то конкретного (с подробным описанием)
        premiereService.listPremieres();
        premiereService.printPremiereInfo("1");

        // 5) Демонтраиця транзакционности
        premiereService.showTransactionsInAction();

        // 6)
        premiereService.addPremiere(new Premiere("1", "PREMIERE_1", "DESCRIPTION", 10, 100));
        premiereService.addPremiere(new Premiere("2", "PREMIERE_2", "DESCRIPTION", 10, 100));

        log.info("\n\n###################\n>>  Demo End\n###################\n\n\n\n");
    }
}
