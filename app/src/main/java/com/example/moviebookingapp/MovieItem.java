package com.example.moviebookingapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class MovieItem implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private String image;
    private String price1;
    private String price2;
    private String price3;
    private String price4;


    public MovieItem(String title, String description, String price1, String price2, String price3, String price4, String image ) {
        this.title = title;
        this.description = description;
        this.price1 = price1;
        this.price2 = price2;
        this.price3 = price3;
        this.price4 = price4;
        this.image=image;

    }

    protected MovieItem(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        price1 = in.readString();
        price2 = in.readString();
        price3 = in.readString();
        price4 = in.readString();
        image=in.readString();
    }

    public static final Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel in) {
            return new MovieItem(in);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice1() {
        return price1;
    }

    public String getPrice2() {
        return price2;
    }

    public String getPrice3() {
        return price3;
    }

    public String getPrice4() {
        return price4;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(price1);
        parcel.writeString(price2);
        parcel.writeString(price3);
        parcel.writeString(price4);
    }
}
