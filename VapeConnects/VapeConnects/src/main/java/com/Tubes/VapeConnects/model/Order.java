package com.Tubes.VapeConnects.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> items = new ArrayList<>();


    private double total;

    private String paymentMethod;

    private String status; // PENDING, PAID, FAILED, EXPIRED

    private LocalDateTime orderDate;

       // --- MIDTRANS FIELDS ---
    private String snapToken;
    private String midtransTransactionId;

    // --- CUSTOMER SNAPSHOT ---
    private String shippingName;
    private String shippingAddress;
    private String shippingPhone;
    private String shippingEmail;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Integer rating = 0;

}
