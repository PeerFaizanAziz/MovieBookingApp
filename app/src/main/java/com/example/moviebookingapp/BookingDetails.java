package com.example.moviebookingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class BookingDetails extends AppCompatActivity {
    TextView tv1, tv2, tv3, tv4;
    MaterialButton buttononline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        Intent intent = getIntent();
        MovieItem movieItem = (MovieItem) intent.getParcelableExtra("object");

buttononline=findViewById(R.id.payonline);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);

        String s1, s2, s3, s4;
        s1 = intent.getStringExtra("s1");
        s2 = intent.getStringExtra("s2");
        s3 = intent.getStringExtra("s3");
        s4 = intent.getStringExtra("s4");
        buttononline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(BookingDetails.this);
                builder1.setTitle("Paying Online");
                builder1.setMessage("Please wait..");
                builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    }
                });

                builder1.show();

            }
        });
        if (s1!=null){
            tv1.setVisibility(View.VISIBLE);
            tv1.setText(movieItem.getTitle() +" will be booked for Rs : "+s1+ " time. ");
        }
        if (s2!=null){
            tv2.setVisibility(View.VISIBLE);
            tv2.setText(movieItem.getTitle()+" will be booked for Rs : "+s2+ " time. ");
        }

        if (s3!=null){
            tv3.setVisibility(View.VISIBLE);
            tv3.setText(movieItem.getTitle()+" will be booked for Rs : "+s3+ " time. ");
        }

        if (s4!=null){
            tv4.setVisibility(View.VISIBLE);
            tv4.setText(movieItem.getTitle()+" will be booked for Rs : "+s4+ " time. ");
        }



    }
}