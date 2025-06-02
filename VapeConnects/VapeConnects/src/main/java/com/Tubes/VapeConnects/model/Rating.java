package com.Tubes.VapeConnects.model;

public class Rating {
    private int id;
    private int productId;
    private int userId;
    private int ratingValue;

    // ✅ Tambahkan Constructor Kosong
    public Rating() {}

    // Constructor dengan parameter
    public Rating(int id, int productId, int userId, int ratingValue) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.ratingValue = ratingValue;
    }

    // Getter dan Setter lengkap
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; } // ✅ Tambah
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; } // ✅ Tambah
    public int getRatingValue() { return ratingValue; }
    public void setRatingValue(int ratingValue) { this.ratingValue = ratingValue; }

    public void viewRating() {
        System.out.println("Rating ID: " + id);
        System.out.println("Product ID: " + productId);
        System.out.println("User ID: " + userId);
        System.out.println("Rating Value: " + ratingValue);
    }
}
