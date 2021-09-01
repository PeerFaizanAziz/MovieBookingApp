package com.example.moviebookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MovieViewModel movieViewModel;
    private EditText titleEt, descEt, p1Et, p2Et, p3Et, p4Et;
    private Button addMovieBtn, showBookingsbtn;
    private ImageView imgView;
    public static final int RESULT_LOAD_IMAGE = 100;
    String encodedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleEt = findViewById(R.id.title);
        descEt = findViewById(R.id.description);
        p1Et = findViewById(R.id.price1);
        p2Et = findViewById(R.id.price2);
        p3Et = findViewById(R.id.price3);
        p4Et = findViewById(R.id.price4);
        addMovieBtn = findViewById(R.id.add_movie_btn);
        imgView = findViewById(R.id.imv_view);
        showBookingsbtn = findViewById(R.id.show_bookings);

        String role = getIntent().getStringExtra("role");
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        MovieAdapter movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        if (role != null && role.equals("Admin")) {
            titleEt.setVisibility(View.VISIBLE);
            descEt.setVisibility(View.VISIBLE);
            p1Et.setVisibility(View.VISIBLE);
            p2Et.setVisibility(View.VISIBLE);
            p3Et.setVisibility(View.VISIBLE);
            p4Et.setVisibility(View.VISIBLE);
            addMovieBtn.setVisibility(View.VISIBLE);
            imgView.setVisibility(View.VISIBLE);
            showBookingsbtn.setVisibility(View.VISIBLE);
        } else if (role != null && role.equals("User")) {
            recyclerView.setVisibility(View.VISIBLE);
            movieViewModel.allNotes.observe(this, new Observer<List<MovieItem>>() {
                @Override
                public void onChanged(List<MovieItem> movieItems) {

                    movieAdapter.setNotes(movieItems);
                }
            });
        }

        showBookingsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowBookings.class);
                startActivity(intent);
                finish();
            }
        });
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        addMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((TextUtils.isEmpty(titleEt.getText()) && TextUtils.isEmpty(descEt.getText()) &&
                        TextUtils.isEmpty(p1Et.getText()) && TextUtils.isEmpty(p2Et.getText()) &&
                        TextUtils.isEmpty(p3Et.getText()) && TextUtils.isEmpty(p4Et.getText()) &&
                        encodedImage == null)) {
                    Toast.makeText(MainActivity.this, "Please Fill All the fields", Toast.LENGTH_SHORT).show();
                } else {
                    MovieItem movieItem = new MovieItem(titleEt.getText().toString(), descEt.getText().toString()
                            , p1Et.getText().toString(), p2Et.getText().toString(), p3Et.getText().toString()
                            , p4Et.getText().toString(), encodedImage);
                    Log.d("TAG", "onClick: "+ movieItem.getImage());
                    movieViewModel.insert(movieItem);
                    Toast.makeText(MainActivity.this, "Movie Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            imgView.setImageURI(selectedImage);

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
            Log.d("TAG", "onActivityResult: "+encodedImage);


        }
    }
}