package com.example.moviebookingapp;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.util.List;

@Entity(tableName = "booking_table" )
public class BookingModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    String date;
    String shows;
    String movie;
    String price;

    public BookingModel(String date, String shows, String movie, String price) {
        this.date = date;
        this.shows = shows;
        this.movie = movie;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShows() {
        return shows;
    }

    public void setShows(String shows) {
        this.shows = shows;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
