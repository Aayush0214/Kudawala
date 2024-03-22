package com.project.kudawala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.project.kudawala.reponses.SubCategoryResponse;

import java.util.ArrayList;

public class order_confirmed extends AppCompatActivity {

    Button sell_more_btn;

    ArrayList<SubCategoryResponse> subCategoryResponses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmed);

        sell_more_btn = findViewById(R.id.sell_more_btn);
        sell_more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(order_confirmed.this, dashboard_user.class));
                finish();
            }
        });

        subCategoryResponses = (ArrayList<SubCategoryResponse>) getIntent().getSerializableExtra("orderItems");

        TextView orderConfirmedDate = findViewById(R.id.confirmed_pickup_date);
        orderConfirmedDate.setText(getIntent().getStringExtra("selectDate"));

        TextView orderConfirmedAddress = findViewById(R.id.confirmed_Pickup_Address);
        orderConfirmedAddress.setText(getIntent().getStringExtra("confirmedAddress"));

        TextView confirmedMobileNumber = findViewById(R.id.confirmed_mobile_number);
        confirmedMobileNumber.setText(getIntent().getStringExtra("confirmedPhoneNumber"));

        ArrayList<String> selectedOrderItems = new ArrayList<>();

        for(SubCategoryResponse subCategoryResponse:subCategoryResponses){
            selectedOrderItems.add(subCategoryResponse.getSubCategoryName());
        }

        String selectedOrderItemString = String.join(", ",selectedOrderItems);

        TextView orderItems = findViewById(R.id.confirmed_scrapItems);
        orderItems.setText(selectedOrderItemString);
    }
}