package com.example.ecommerce.entities;

import jakarta.persistence.*;

import java.util.List;

public class FormCat {

    private String name;

    public FormCat(String name) {
        this.name = name;
    }

    public FormCat() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
