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

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MovieItem> movieItems = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieItem currentMovieItem = movieItems.get(position);

        byte[] decodedString = Base64.decode(currentMovieItem.getImage(), Base64.DEFAULT);

        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.imageView.setImageBitmap(decodedByte);
        holder.title.setText(currentMovieItem.getTitle());
        holder.desc.setText(currentMovieItem.getDescription());
        holder.price1.setText(String.valueOf(currentMovieItem.getPrice1()));
        holder.price2.setText(String.valueOf(currentMovieItem.getPrice2()));
        holder.price3.setText(String.valueOf(currentMovieItem.getPrice3()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BookingActivity.class);
                intent.putExtra("object", (Parcelable) currentMovieItem);
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }

    public void setNotes(List<MovieItem> movieItems) {
        this.movieItems = movieItems;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc, price1, price2, price3;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            title = itemView.findViewById(R.id.tv1);
            desc = itemView.findViewById(R.id.tv2);
            price1 = itemView.findViewById(R.id.tp1);
            price2 = itemView.findViewById(R.id.tp2);
            price3 = itemView.findViewById(R.id.tp3);
        }
    }
}
