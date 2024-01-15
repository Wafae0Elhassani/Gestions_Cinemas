package com.cinema.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Data  @AllArgsConstructor @NoArgsConstructor @ToString
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientName;
    private double price;
    //@Column(unique = true)
    private int paymentCode;
    private boolean reserved;
    @ManyToOne
    private Place place;
    @ManyToOne
    private Projection projection;
}
