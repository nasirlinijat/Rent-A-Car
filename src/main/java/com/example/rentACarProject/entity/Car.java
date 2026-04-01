package com.example.rentACarProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"model", "rentHistories"})
@EqualsAndHashCode(exclude = {"model", "rentHistories"})
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plate", nullable = false, unique = true, length = 20)
    private String plate;

    @Column(name = "model_year", nullable = false)
    private int modelYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private CarState state;

    @Column(name = "daily_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal dailyPrice;

    @Column(name = "image_path")
    private String imagePath;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RentHistory> rentHistories;

    @ManyToOne(optional = false)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;
}
