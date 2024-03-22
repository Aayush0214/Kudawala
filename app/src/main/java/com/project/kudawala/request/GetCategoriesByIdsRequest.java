package com.project.kudawala.request;


import java.util.List;


public class GetCategoriesByIdsRequest {
    List<String> categoryIds;

    public List<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
