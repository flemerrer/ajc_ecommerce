package com.example.ecommerce.entities;

import static java.lang.Float.valueOf;

public class FormGame {

    String name;
    String publisher;
    float score;
    //stocker en centimes
    int price;
    String description;

    public FormGame(String name, String publisher, float score, int price, String description) {
        this.name = name;
        this.score = score;
        this.price = price;
        this.description = description;
        this.publisher = publisher;
    }

    public FormGame() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
