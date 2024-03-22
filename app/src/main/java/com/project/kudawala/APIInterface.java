package com.project.kudawala;

import com.project.kudawala.reponses.AndroidOrdersResponse;
import com.project.kudawala.reponses.ApiResponse;
import com.project.kudawala.reponses.CategoryResponse;
import com.project.kudawala.request.CreateCategoryRequest;
import com.project.kudawala.request.CreateOrderRequest;
import com.project.kudawala.request.GetCategoriesByIdsRequest;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @POST("category/create-category")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ApiResponse> createCategoryInterface(@Body CreateCategoryRequest createCategoryRequest);

    @GET("category/get-all-category")
    Call<List<CategoryResponse>> getAllCategoryInterface();

    @POST("category/get-categories-by-ids")
    Call<List<CategoryResponse>> getCategoriesById(@Body GetCategoriesByIdsRequest getCategoriesByIdsRequest);

    @POST("android/create-order")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<ApiResponse> createOrderInterface(@Body CreateOrderRequest createOrderRequest);

    @GET("android/get-order-by-userId/{userId}")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<List<AndroidOrdersResponse>> getAllOrdersByUsers(@Path(value = "userId",encoded = true) String userId);
}
