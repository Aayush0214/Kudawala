package com.project.kudawala.request;

import com.google.gson.annotations.SerializedName;

public class CreateCategoryRequest {

    @SerializedName("categoryName")
    String categoryName;

    @SerializedName("categoryImage")
    String categoryImage;

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
}
