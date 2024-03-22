package com.project.kudawala;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserOrderDescription extends AppCompatActivity {

    TextView userOrderedDate, userOrderedItems,userOrderedAddress,userOrderMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_order_description);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userOrderedDate = findViewById(R.id.userOrder_confirmed_pickup_date);
        userOrderedItems = findViewById(R.id.userOrder_confirmed_scrapItems);
        userOrderedAddress = findViewById(R.id.confirmed_Pickup_Address);
        userOrderMobile = findViewById(R.id.confirmed_mobile_number);

        userOrderedDate.setText(getIntent().getStringExtra("OrderedDate"));
        userOrderedItems.setText(getIntent().getStringExtra("OrderedItems"));
        userOrderedAddress.setText(getIntent().getStringExtra("orderAddress"));
        userOrderMobile.setText(getIntent().getStringExtra("orderMobile"));
    }
}