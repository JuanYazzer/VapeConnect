package com.Tubes.VapeConnects.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`order`") // karena "order" adalah reserved word di SQL
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double total;

    private String paymentMethod;

    private LocalDateTime orderDate;

    private String status;

    private int rating;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
