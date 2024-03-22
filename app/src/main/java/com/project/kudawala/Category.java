package com.project.kudawala;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.project.kudawala.reponses.ApiResponse;
import com.project.kudawala.reponses.CategoryResponse;
import com.project.kudawala.request.CreateCategoryRequest;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Category extends AppCompatActivity {
    Button add_img_button,submit_ctg_button;
    Bitmap bitmap;
    EditText categoryText;
    ImageView categoryImg;

    APIInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //initializing variables
        add_img_button = findViewById(R.id.category_add_image_button);
        categoryImg = findViewById(R.id.display_category_img);
        submit_ctg_button = findViewById(R.id.category_add_button);
        categoryText = findViewById(R.id.Category_name);
        apiInterface = RetrofitInstance.getRetrofit().create(APIInterface.class);


        //This is for adding selected image into image view
        ActivityResultLauncher<Intent> setImageViewLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent img_data = result.getData();
                            Uri uri = img_data.getData();
                            try {
                                //here the selected image is uploaded into imageView
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                categoryImg.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        //add image button functionality
        add_img_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                setImageViewLauncher.launch(intent);
            }
        });

        submit_ctg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    String tempCategoryName = categoryText.getText().toString();
                    if(!tempCategoryName.equals("")) {
                        System.out.println(categoryText.getText().toString());
                        if (bitmap != null) {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                            byte[] bytes = byteArrayOutputStream.toByteArray();
                            final String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);
                            CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest();
                            createCategoryRequest.setCategoryName(tempCategoryName);
                            createCategoryRequest.setCategoryImage(base64Image.replace("\n", ""));
                            Call<ApiResponse> createCategoryCall = apiInterface.createCategoryInterface(createCategoryRequest);
                            createCategoryCall.enqueue(new Callback<ApiResponse>() {
                                @Override
                                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                                    if(response.body() != null){
                                        Log.d("Create Category Success",response.body().getMessage());
                                        startActivity(new Intent(getApplicationContext(), Sub_category.class));
                                    } else {
                                        Log.d("Create Category error", String.valueOf(response.errorBody()));
                                    }
                                }

                                @Override
                                public void onFailure(Call<ApiResponse> call, Throwable t) {
                                    Log.e("Create Category Faliure",t.toString());
                                }
                            });
                        }else{
                            Toast.makeText(getApplicationContext(), "Please select image!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Please add category name!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception exception){
                    throw new RuntimeException(exception.getMessage());
                }
            }
        });
    }
}