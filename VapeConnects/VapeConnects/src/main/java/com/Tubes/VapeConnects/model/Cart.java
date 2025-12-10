package com.Tubes.VapeConnects.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "cart") // bukan pemilik relasi
    private Customer customer;


    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CartItem> items = new ArrayList<>();


    public void addItem(CartItem item) {
        items.add(item);
        item.setCart(this); // penting: set balik relasinya
    }


    public double calculateTotal() {
        return items.stream()
            .map(CartItem::getSubTotal)
            .mapToDouble(java.math.BigDecimal::doubleValue)
            .sum();
    }
}
