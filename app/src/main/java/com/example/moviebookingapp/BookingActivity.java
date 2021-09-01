package com.example.moviebookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookingActivity extends AppCompatActivity {

    CheckBox c1, c2, c3, c4;
    TextView title, desc,choosedate,totalPrice;
    MaterialButton bookShowBtn;
    private BookingViewModel bookingViewModel;
    private Calendar myCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        bookingViewModel = new ViewModelProvider(this).get(BookingViewModel.class);


        MovieItem movieItem = (MovieItem) getIntent().getParcelableExtra("object");
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);
        title = findViewById(R.id.tv1);
        totalPrice=findViewById(R.id.totlaprice);
        choosedate = findViewById(R.id.date_picker);
        desc = findViewById(R.id.tv2);
        bookShowBtn = findViewById(R.id.book_btn);



      myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };



        title.setText(movieItem.getTitle());
        desc.setText(movieItem.getDescription());
        c1.setText(String.valueOf(movieItem.getPrice1()));
        c2.setText(String.valueOf(movieItem.getPrice2()));
        c3.setText(String.valueOf(movieItem.getPrice3()));
        c4.setText(String.valueOf(movieItem.getPrice4()));
        choosedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(BookingActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        bookShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String show1 = null, show2 = null, show3 = null, show4 = null;
                if (c1.isChecked()) {
                    show1 = c1.getText().toString();
                }
                if (c2.isChecked()) {
                    show2 = c2.getText().toString();
                }
                if (c3.isChecked()) {
                    show3 = c3.getText().toString();
                }
                if (c4.isChecked()) {
                    show4 = c4.getText().toString();
                }

                if (show1 != null || show2 != null || show3 != null || show4 != null || !TextUtils.isEmpty(choosedate.getText())) {
                   // book here
                    BookingModel bookingModel=new BookingModel(choosedate.getText().toString(),
                            show1 + " "+show2 + " "+ show3 + " "+show4,title.getText().toString(),
                           totalPrice.getText().toString() );
                    bookingViewModel.insert(bookingModel);
                    Intent intent=new Intent(BookingActivity.this,BookingDetails.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(BookingActivity.this, "Plsease select show first", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        choosedate.setText(sdf.format(myCalendar.getTime()));
    }
}