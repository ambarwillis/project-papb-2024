package com.pam.deertoapp;

public class ItemModel {
    String name;
    String date;
    int Image;

    public ItemModel(String name, String date, int image) {
        this.name = name;
        this.date = date;
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
