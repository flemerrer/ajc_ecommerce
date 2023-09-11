package com.example.ecommerce.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    List<Boardgame> boardgameList;

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Boardgame> getBoardgameList() {
        return boardgameList;
    }

    public void setBoardgameList(List<Boardgame> boardgameList) {
        this.boardgameList = boardgameList;
    }
}
