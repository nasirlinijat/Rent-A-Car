package com.example.rentACarProject.entities.concrates;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RentHistory")
public class RentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "rentalStartDate")
    private LocalDate rentalStartDate;

    @Column(name = "rentalEndDate")
    private LocalDate rentalEndDate;

    @Column(name = "totalCost")
    private BigDecimal totalCost;

    @Column(name = "status")
    private String status;

    @Column(name = "pickupLocation")
    private String pickupLocation;

    @Column(name = "returnLocation")
    private String returnLocation;

    @ManyToOne
    @JoinColumn(name = "car_id")
    @Lazy
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
