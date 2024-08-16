package com.redhat.samples.bff;

public class Book {
    private String isbn;

    private String title;

    private String description;

    private int price;

    private double ratings;

    public Book() {
    }

    public Book(String isbn, String title, String description, int price, double ratings) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.price = price;
        this.ratings = ratings;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public double getRatings() {
        return ratings;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

}
