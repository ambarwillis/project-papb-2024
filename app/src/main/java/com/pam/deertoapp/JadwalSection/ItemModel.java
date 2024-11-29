package com.pam.deertoapp.JadwalSection;

public class ItemModel {
    String name;
    String date;
    String imageUrl;

    public ItemModel(String name, String date, String imageUrl) {
        this.name = name;
        this.date = date;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

