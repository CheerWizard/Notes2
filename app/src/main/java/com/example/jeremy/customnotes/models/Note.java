package com.example.jeremy.customnotes.models;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "description_column")
    private String description;
    @ColumnInfo(name = "category_column")
    private String category;
    @ColumnInfo(name = "date_column")
    private Date date;

    public Note(String description, String category , Date date) {
        this.description = description;
        this.category = category;
        this.date = date;
    }

    public Note(int id , String description, String category , Date date) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.date = date;
    }

    public Note() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
