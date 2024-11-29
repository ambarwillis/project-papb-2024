package com.pam.deertoapp.JadwalSection;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_table")
public class ItemModel {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String date;
    private String imageUrl;

    public ItemModel(){
    }

    public ItemModel(String name, String date, String imageUrl) {
        this.name = name;
        this.date = date;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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