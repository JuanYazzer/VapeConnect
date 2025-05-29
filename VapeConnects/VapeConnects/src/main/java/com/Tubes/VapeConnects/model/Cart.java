// package com.Tubes.VapeConnects.model;

// import jakarta.persistence.*;
// import java.util.ArrayList;
// import java.util.List;

// @Entity
// public class Cart {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private int id;

//     @OneToMany(cascade = CascadeType.ALL)
//     private List<CartItem> items = new ArrayList<>();

//     public void addItem(CartItem item) {
//         this.items.add(item);
//     }

//     public double calculateTotal() {
//         return items.stream()
//             .map(CartItem::getSubTotal)
//             .mapToDouble(java.math.BigDecimal::doubleValue)
//             .sum();
//     }

//     // Getter & Setter
// }
