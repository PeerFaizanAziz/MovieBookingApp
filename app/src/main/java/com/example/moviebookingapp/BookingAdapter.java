package com.example.moviebookingapp;



import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    private List<BookingModel> notes = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booking_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookingModel currentNote = notes.get(position);


        holder.movie.setText(currentNote.getMovie());
        holder.price.setText(currentNote.getPrice());
        holder.date.setText(String.valueOf(currentNote.getDate()));
        holder.show.setText(String.valueOf(currentNote.getShows()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<BookingModel> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView movie, price, date, show;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movie=itemView.findViewById(R.id.movie);
            price=itemView.findViewById(R.id.price);
            date=itemView.findViewById(R.id.date);
            show=itemView.findViewById(R.id.shows);

        }
    }
}

