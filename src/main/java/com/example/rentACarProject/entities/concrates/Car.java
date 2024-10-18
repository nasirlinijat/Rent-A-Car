package com.example.rentACarProject.entities.concrates;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "plate", unique = true)
    private String plate;

    @Column(name = "modelYear")
    private int modelYear;

    @Column(name = "state")
    private int state;

    @Column(name = "dailyPrice")
    private double dailyPrice;

    @Column(name = "imagePath")
    private String imagePath;

    @OneToMany(mappedBy = "car")
    @Lazy
    private List<RentHistory> rentHistories;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

}
