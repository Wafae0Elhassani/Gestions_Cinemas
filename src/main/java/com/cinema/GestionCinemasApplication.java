package com.cinema;

import com.cinema.Services.ICinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionCinemasApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GestionCinemasApplication.class, args);
    }

    @Autowired
    private ICinemaService iCinemaService;
    @Override
    public void run(String... args) throws Exception {
        iCinemaService.initvilles();
        iCinemaService.initCinemas();
        iCinemaService.initSalles();
        iCinemaService.initPlaces();
        iCinemaService.initSceances();
        iCinemaService.initCategories();
        iCinemaService.initFilms();
        iCinemaService.initProjections();
        iCinemaService.initTickets();
    }
}
