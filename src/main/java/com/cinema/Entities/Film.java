package com.cinema.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data   @AllArgsConstructor @NoArgsConstructor @ToString
public class Film implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String director;
    private Date releaseDate;
    private double duration;
    private String photo;
    @OneToMany(mappedBy = "film")
    private Collection<Projection> projections;
    @ManyToOne
    private Category category;
}
