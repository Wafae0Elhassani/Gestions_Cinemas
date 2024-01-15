package com.cinema.Services;

import com.cinema.Entities.*;
import com.cinema.Repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaServiceImpl implements ICinemaService{

    @Autowired
    private VilleRepository villeRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private SalleRepository salleRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ProjectionRepository projectionRepository;

    @Autowired
    private TicketRepository ticketRepository;


    @Override
    public void initvilles() {
        Stream.of("Casablanca","Rabat","Marrakech","Tanger").forEach((villeName)->{
            Ville ville = new Ville();
            ville.setName(villeName);
            villeRepository.save(ville);
        });
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach((villeName)->{
            Stream.of("Mega Rama","IMax","Movies","Show").forEach((cinemaName)->{
                Cinema cinema = new Cinema();
                cinema.setName(cinemaName);
                cinema.setVille(villeName);
                cinema.setNbSalles((int) (3+Math.random()*7));
                cinemaRepository.save(cinema);
            });
        });
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach((cinema)->{
            for(int i= 0; i<cinema.getNbSalles();i++){
                Salle salle = new Salle();
                salle.setName("Salle "+i);
                salle.setCinema(cinema);
                salle.setNbPlace((int) (20+Math.random()*10));
                salleRepository.save(salle);
            }
        });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach((salle)->{
            Place place = new Place();
            for(int i=0; i<salle.getNbPlace();i++){
                place.setNumber(i+1);
                place.setSalle(salle);
                placeRepository.save(place);
            }
        });
    }

    @Override
    public void initSceances() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("12:00","15:00","17:00","19:00","21:00").forEach((hour)->{
            Seance seance = new Seance();
            try {
                seance.setBeginTime(dateFormat.parse(hour));
                seanceRepository.save(seance);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void initCategories() {
        Stream.of("Action","Fiction","Drama","Romance").forEach((categoryName)->{
            Category category = new Category();
            category.setName(categoryName);
            categoryRepository.save(category);
        });
    }

    @Override
    public void initFilms() {
        double[] durees = new double[] {1,1.5,2,2.5,3,3.5};
        List<Category> categoryList = categoryRepository.findAll();
        Stream.of("Mission Impossible","The Proposal","The hating game","Truth or dare").forEach((movie)->{
            Film film = new Film();
            film.setTitle(movie);
            film.setDuration(durees[new Random().nextInt(durees.length)]);
            film.setPhoto(movie.replaceAll(" ",""));
            film.setCategory(categoryList.get(new Random().nextInt(categoryList.size())));
            filmRepository.save(film);
        });
    }

    @Override
    public void initProjections() {
        double[] prices = new double[] {30,60,50,46,100};
        Random random = new Random();
        villeRepository.findAll().forEach(ville->{
            ville.getCinemas().forEach(cinema->{
                cinema.getSalles().forEach(salle->{
                    filmRepository.findAll().forEach(film->{
                            seanceRepository.findAll().forEach(seance->{
                                Projection projection = new Projection();
                                projection.setDateProjection(new Date());
                                projection.setFilm(film);
                                projection.setPrice(prices[random.nextInt(prices.length)]);
                                projection.setSalle(salle);
                                projection.setSeance(seance);
                                projectionRepository.save(projection);
                            });
                    });
                });
            });
        });

    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach((projection)->{
            projection.getSalle().getPlaces().forEach((place)->{
                Ticket ticket = new Ticket();
                ticket.setPlace(place);
                ticket.setPrice(projection.getPrice());
                ticket.setReserved(false);
                ticket.setPaymentCode((int) (Math.random()*1000));
                ticketRepository.save(ticket);
            });
        });
    }
}
