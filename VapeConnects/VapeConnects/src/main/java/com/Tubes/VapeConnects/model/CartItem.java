package com.Tubes.VapeConnects.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "produk_id", nullable = false)
    private Produk produk;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
    private int quantity;
    public CartItem(Produk produk, int quantity) {
        this.produk = produk;
        this.quantity = quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = this.quantity+1;
    }
    public java.math.BigDecimal getSubTotal() {
        return produk.getPrice().multiply(java.math.BigDecimal.valueOf(quantity));
    }
    // Getter & Setter
}
