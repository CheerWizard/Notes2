package com.example.jeremy.customnotes.business_logic.data;

import com.example.jeremy.customnotes.constants.SQLiteConstants;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = SQLiteConstants.Tables.NOTE_TABLE)
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "description_column")
    private String description;
    @ColumnInfo(name = "category_column")
    private String category;
    @ColumnInfo(name = "date_column")
    private String date;

    public Note(String description, String category , Date date) {
        this.description = description;
        this.category = category;
        this.date = date.toString();
    }

    public Note(int id , String description, String category , Date date) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.date = date.toString();
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

    public void setDate(Date date) {
        this.date = date.toString();
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
