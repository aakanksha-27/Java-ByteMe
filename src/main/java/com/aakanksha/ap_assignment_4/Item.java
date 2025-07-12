package com.aakanksha.ap_assignment_4;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Comparable<Item>, Serializable {

    String name;
    Integer price;
    String category;
    boolean available;
    ArrayList<String> reviews;

    public Item(String name, Integer price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = true;
        reviews = new ArrayList<>();
    }

    public Item(String name, Integer price, String category, boolean available) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = available;
        reviews = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return this.price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public ArrayList<String> getReviews() {
        return this.reviews;
    }
    public void setReviews(ArrayList<String> reviews) {
        this.reviews = reviews;
    }

    public String toString(){
        return (this.getName() + " Rs." + this.getPrice() + " " + (this.isAvailable()? "Available" : "Not Available"));
    }

    public int compareTo(Item item) {
        return this.price.compareTo(item.price);
    }
}
