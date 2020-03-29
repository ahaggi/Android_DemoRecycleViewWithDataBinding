package com.example.demorecycleviewwithdatabinding.model;

public class Cat {
    String id;
    String image;

    public Cat() {
    }

    public Cat(String id, String image) {
        this.id = id;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
