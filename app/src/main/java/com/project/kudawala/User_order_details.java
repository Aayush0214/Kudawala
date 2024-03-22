package com.project.kudawala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.kudawala.reponses.AndroidOrdersResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_order_details extends AppCompatActivity {

    RecyclerView userOrderDetailsView;

    APIInterface apiInterface;

    FirebaseUser firebaseUser;

    List<AndroidOrdersResponse> ordersResponses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order_details);
        apiInterface = RetrofitInstance.getRetrofit().create(APIInterface.class);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        ordersResponses = new ArrayList<>();

        Call<List<AndroidOrdersResponse>> getOrdersByUserId = apiInterface.getAllOrdersByUsers(firebaseUser.getUid().toString());
        getOrdersByUserId.enqueue(new Callback<List<AndroidOrdersResponse>>() {
            @Override
            public void onResponse(Call<List<AndroidOrdersResponse>> call, Response<List<AndroidOrdersResponse>> response) {
                if(response.body() != null){
                    ordersResponses = response.body();
                    userOrderDetailsView = findViewById(R.id.user_order_details_recyclerView);
                    OrderDetailsSettingAdapter orderDetailsSettingAdapter = new OrderDetailsSettingAdapter(ordersResponses);
                    userOrderDetailsView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    userOrderDetailsView.setAdapter(orderDetailsSettingAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<AndroidOrdersResponse>> call, Throwable t) {
                throw new RuntimeException(t.getCause().toString());
            }
        });

        OrderDetailsDataModel[] orderDetailsData = new OrderDetailsDataModel[]{
                new OrderDetailsDataModel("15-03-2024", "ScrapItems"),
                new OrderDetailsDataModel("14-08-2024", "ScrapItems2")
        };

        //recycler view initialization

    };
}