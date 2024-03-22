package com.project.kudawala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.kudawala.reponses.ApiResponse;
import com.project.kudawala.reponses.SubCategoryResponse;
import com.project.kudawala.request.CreateOrderRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Order_confirmation extends AppCompatActivity {

    GridView selectedScrapItemsView;
    TextView cancel_btn_layer3, change_pickup_date_btn;
    Button pickup_confirmation_btn;
    String confirmedPickUpAddress;
    String confirmedPhoneNumber;

    LoadingAnimationDialog loadingAnimation;

    FirebaseUser firebaseUser;

    APIInterface apiInterface;

    ArrayList<SubCategoryResponse> selectedSubCategoryResponses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);
        apiInterface = RetrofitInstance.getRetrofit().create(APIInterface.class);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        loadingAnimation = new LoadingAnimationDialog(this);
        selectedSubCategoryResponses = (ArrayList<SubCategoryResponse>) getIntent().getSerializableExtra("selectedSubCategoryData");

        //cancel button
        cancel_btn_layer3 = findViewById(R.id.cancel_btn_layer3);
        cancel_btn_layer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Order_confirmation.this, dashboard_user.class));
                finish();
            }
        });

        //change date button
        change_pickup_date_btn = findViewById(R.id.date_change_btn_layer3);
        change_pickup_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Order_confirmation.this, Date_selection.class));
            }
        });

        //getting selected date from intent passed from dateSelection Activity
        String selectedDate = getIntent().getStringExtra("selectDate");
        TextView dateSelectedTextView = findViewById(R.id.selected_Pickup_Date_TextView);
        dateSelectedTextView.setText("On "+selectedDate);

        //Storing PickUp address
        EditText pickUpAddress = findViewById(R.id.editText_PostalAddress);
        EditText phoneNumber = findViewById(R.id.editText_Mobile_Number);

        //pickup confirmation button
        pickup_confirmation_btn = findViewById(R.id.pickup_confirmation_btn);
        pickup_confirmation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmedPickUpAddress = pickUpAddress.getText().toString();
                confirmedPhoneNumber = phoneNumber.getText().toString();
                if (!confirmedPickUpAddress.isEmpty()){
                    if (!confirmedPhoneNumber.isEmpty()){
                        ArrayList<String> orderItems = new ArrayList<>();
                        for(SubCategoryResponse subCategoryResponse: selectedSubCategoryResponses){
                            orderItems.add(subCategoryResponse.getSubCategoryId());
                        }

                        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
                        createOrderRequest.setOrderedItems(orderItems);
                        createOrderRequest.setOrderAddress(confirmedPickUpAddress);
                        createOrderRequest.setMobileNumber(confirmedPhoneNumber);
                        createOrderRequest.setEmail(firebaseUser.getEmail());
                        createOrderRequest.setUserId(firebaseUser.getUid());
                        createOrderRequest.setPickupDate(selectedDate);

                        Call<ApiResponse> createOrderCall = apiInterface.createOrderInterface(createOrderRequest);

                        createOrderCall.enqueue(new Callback<ApiResponse>() {
                            @Override
                            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                                loadingAnimation.show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        loadingAnimation.cancel();
                                        Intent intent = new Intent(Order_confirmation.this, order_confirmed.class);
                                        intent.putExtra("selectDate",selectedDate);
                                        intent.putExtra("confirmedAddress", confirmedPickUpAddress);
                                        intent.putExtra("orderItems",selectedSubCategoryResponses);
                                        intent.putExtra("confirmedPhoneNumber",confirmedPhoneNumber);
                                        startActivity(intent);
                                    }
                                },5000);
                            }

                            @Override
                            public void onFailure(Call<ApiResponse> call, Throwable t) {
                                throw new RuntimeException("Unable to create order at this moment");
                            }
                        });
                    }else {
                        Toast.makeText(Order_confirmation.this, "Please enter phone number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Order_confirmation.this, "Please enter pickup address", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //GridView to show selected scrap items
        selectedScrapItemsView = findViewById(R.id.selected_scrap_items_list);
        //Temporary data source to implement gridview

        //creating instance of selected scrap adapter class
        Selected_scrap_adapter selectedScrapAdapter = new Selected_scrap_adapter(getApplicationContext(), selectedSubCategoryResponses);
        selectedScrapItemsView.setAdapter(selectedScrapAdapter);
    }
}