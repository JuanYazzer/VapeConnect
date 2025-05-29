package com.Tubes.VapeConnects.model;

public class Rating {
    private int id;
    private int productId;
    private int userId;
    private int ratingValue;

    public Rating(int id, int productId, int userId, int ratingValue) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.ratingValue = ratingValue;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getProductId() {
        return productId;
    }
    public int getUserId() {
        return userId;
    }
    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }
    public int getRatingValue() {
        return ratingValue;
    }
    public void viewRating() {
        System.out.println("Rating ID: " + id);
        System.out.println("Product ID: " + productId);
        System.out.println("User ID: " + userId);
        System.out.println("Rating Value: " + ratingValue);
    }
}
