package com.project.kudawala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.kudawala.reponses.CategoryResponse;
import com.project.kudawala.request.GetCategoriesByIdsRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Select_scrap_item extends AppCompatActivity {

    Button button;
    RecyclerView recyclerView;
    ImageView back_btn_layer1;
    ArrayList<price_list_parent_model_class> parentModelClassArrayList;

    ArrayList<String> selectedCategories;

    price_list_parent_adapter parentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_scrap_item);
        APIInterface apiInterface = RetrofitInstance.getRetrofit().create(APIInterface.class);

        button = findViewById(R.id.scrap_select_button);
        //initializing lists of parent and child recycler view
        recyclerView = findViewById(R.id.parent_recycler_view);


        parentModelClassArrayList = new ArrayList<>();
        selectedCategories = new ArrayList<>();
        selectedCategories = (ArrayList<String>) getIntent().getSerializableExtra("subcategories");

        GetCategoriesByIdsRequest getCategoriesByIdsRequest = new GetCategoriesByIdsRequest();
        getCategoriesByIdsRequest.setCategoryIds(selectedCategories);

        Call<List<CategoryResponse>> fetchSelectedCategoryCall = apiInterface.getCategoriesById(getCategoriesByIdsRequest);

        fetchSelectedCategoryCall.enqueue(new Callback<List<CategoryResponse>>() {
            @Override
            public void onResponse(Call<List<CategoryResponse>> call, Response<List<CategoryResponse>> response) {
                if(response.body() != null)
                    for(CategoryResponse categoryResponse:response.body()){
                        parentModelClassArrayList.add(new price_list_parent_model_class(categoryResponse.getCategoryName(),categoryResponse.getSubCategoryResponse()));
                    }
                parentAdapter = new price_list_parent_adapter(parentModelClassArrayList,Select_scrap_item.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(parentAdapter);
                parentAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<CategoryResponse>> call, Throwable t) {
                throw new RuntimeException("Unable to fetch categories now");
            }
        });

        //selected subcategory button functionality
        button.setOnClickListener(v -> {
            if(!parentAdapter.getSelectedSubCategoryData().isEmpty()){
                Intent intent = new Intent(Select_scrap_item.this, Date_selection.class);
                intent.putExtra("selectedSubCategoryData",parentAdapter.getSelectedSubCategoryData());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please select at least one category", Toast.LENGTH_SHORT).show();
            }

        });

    }
}