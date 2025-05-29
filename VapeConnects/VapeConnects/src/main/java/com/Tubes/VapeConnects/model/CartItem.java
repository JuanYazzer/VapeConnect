// package com.Tubes.VapeConnects.model;

// import jakarta.persistence.*;

// @Entity
// public class CartItem {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private int id;

//     @ManyToOne
//     @JoinColumn(name = "produk_id", nullable = false)
//     private Produk produk;

//     private int quantity;

//     public CartItem() {}

//     public CartItem(Produk produk, int quantity) {
//         this.produk = produk;
//         this.quantity = quantity;
//     }
//         public void setProduk(Produk produk) {
//         this.produk = produk;
//     }

//     public Produk getProduk() {
//         return produk;
//     }
//     public java.math.BigDecimal getSubTotal() {
//         return produk.getPrice().multiply(java.math.BigDecimal.valueOf(quantity));
//     }
//         public void setQuantity(int quantity) {
//         this.quantity = quantity;
//     }

//     public int getQuantity() {
//         return this.quantity;
//     }
//     // Getter & Setter
// }
