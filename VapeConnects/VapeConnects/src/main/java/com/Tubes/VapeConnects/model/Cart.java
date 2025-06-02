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

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();
    
    @OneToOne
    @JoinColumn(name = "user_id") // nama kolom di tabel Cart yang menyimpan id user
    private User user;


    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
    public void addItem(CartItem item) {
        item.setCart(this); // pastikan bidirectional konsisten
        this.items.add(item);
    }

    public double calculateTotal() {
        return items.stream()
            .map(CartItem::getSubTotal)
            .mapToDouble(java.math.BigDecimal::doubleValue)
            .sum();
    }

    
}
