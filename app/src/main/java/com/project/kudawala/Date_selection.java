package com.project.kudawala;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.kudawala.reponses.SubCategoryResponse;

import java.util.ArrayList;

public class Date_selection extends AppCompatActivity {

    Button dateSelectbtn;
    TextView cancel_btn_layer2;
    DatePicker selectPickUpDate;

    String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_selection);

        //cancel button
        cancel_btn_layer2 = findViewById(R.id.cancel_btn_layer2);
        cancel_btn_layer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Date_selection.this, dashboard_user.class));
                finish();
            }
        });

        //date picker initialization
        selectPickUpDate = findViewById(R.id.select_pickup_date);
        String day = String.valueOf(selectPickUpDate.getDayOfMonth());
        String month = String.valueOf(selectPickUpDate.getMonth() + 1);
        String year = String.valueOf(selectPickUpDate.getYear());
        selectedDate = day + "/" + month + "/" + year;

        // Set an OnDateChangedListener to handle date changes
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectPickUpDate.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    Toast.makeText(Date_selection.this, "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
                }
            });
        }


        //date selection button
        dateSelectbtn = findViewById(R.id.date_select_button);
        dateSelectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<SubCategoryResponse> subCategoryResponses = (ArrayList<SubCategoryResponse>)getIntent().getSerializableExtra("selectedSubCategoryData");
                String pickUpDate = selectedDate;
                Intent intent = new Intent(Date_selection.this, Order_confirmation.class);
                intent.putExtra("selectDate", pickUpDate);
                intent.putExtra("selectedSubCategoryData",subCategoryResponses);
                startActivity(intent);
            }
        });

    }
}