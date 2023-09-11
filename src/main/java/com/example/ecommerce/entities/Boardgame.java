package com.example.ecommerce.entities;

import jakarta.persistence.*;

@Entity
@Table(name="BOARDGAMES")
public class Boardgame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="GAME_ID")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    String name;
    String publisher;
    float score;
    //stocker en centimes
    int price;
    String description;

    public Boardgame(String name, String publisher, float score, int price, String description) {
        this.name = name;
        this.publisher = publisher;
        this.score = score;
        this.price = price;
        this.description = description;
    }

    public Boardgame() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        if (category != null) {
            return category.getName();
        } else {
            return "";
        }
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public float getPrice() {
        return price/100f;
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

    @Override
    public String toString() {
        return "Boardgame{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", score=" + score +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
