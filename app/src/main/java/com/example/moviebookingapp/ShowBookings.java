package com.example.moviebookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class ShowBookings extends AppCompatActivity {
RecyclerView recyclerView;
BookingViewModel bookingViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bookings);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        BookingAdapter bookingAdapter = new BookingAdapter();
        recyclerView.setAdapter(bookingAdapter);
        bookingViewModel = new ViewModelProvider(this).get(BookingViewModel.class);
        bookingViewModel.allMovies.observe(this, new Observer<List<BookingModel>>() {
            @Override
            public void onChanged(List<BookingModel> notes) {
                bookingAdapter.setNotes(notes);
            }
        });

    }
}