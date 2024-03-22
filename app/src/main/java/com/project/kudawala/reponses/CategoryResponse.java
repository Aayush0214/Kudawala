package com.project.kudawala.reponses;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.google.gson.annotations.SerializedName;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.zip.Inflater;

public class CategoryResponse implements Serializable {

    @SerializedName("categoryId")
    private String categoryId;

    @SerializedName("categoryName")
    private String categoryName;

    @SerializedName("categoryImage")
    private String categoryImage;

    @SerializedName("subCategories")
    private List<SubCategoryResponse> subCategoryResponse;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public List<SubCategoryResponse> getSubCategoryResponse() {
        return subCategoryResponse;
    }

    public void setSubCategoryResponse(List<SubCategoryResponse> subCategoryResponse) {
        this.subCategoryResponse = subCategoryResponse;
    }

    public static Bitmap convertToBitMap(String base64Str) throws IllegalArgumentException {
        byte[] decodedBytes = Base64.decode(base64Str.substring(base64Str.indexOf(",")  + 1), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }


}
