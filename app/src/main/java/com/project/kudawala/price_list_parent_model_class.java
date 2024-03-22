package com.project.kudawala;

import com.project.kudawala.reponses.SubCategoryResponse;

import java.util.List;

public class price_list_parent_model_class {
    String categoryTitle;
    List<SubCategoryResponse> priceListChildModelClassList;

    public price_list_parent_model_class(String categoryTitle, List<SubCategoryResponse> priceListChildModelClassList){
        this.categoryTitle = categoryTitle;
        this.priceListChildModelClassList = priceListChildModelClassList;
    }
}
