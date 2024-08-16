package com.redhat.samples.bff;

public class Ratings {

    private String isbn;

    private double ratings;

    public Ratings() {}

    public Ratings(String isbn, double ratings) {
        this.isbn = isbn;
        this.ratings = ratings;
    }

    public String getIsbn() {
        return isbn;
    }

    public double getRatings() {
        return ratings;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }
    
}
