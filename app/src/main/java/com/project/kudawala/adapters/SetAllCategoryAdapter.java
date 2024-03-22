package com.project.kudawala.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.project.kudawala.R;
import com.project.kudawala.reponses.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

public class SetAllCategoryAdapter extends BaseAdapter {

    Context context;

    LayoutInflater inflater;

    List<CategoryResponse> categoryList;

    Button btn_sell_scrap;

    ArrayList<Integer> subCategoryList;

    ArrayList<String> selectedCategory;

    public SetAllCategoryAdapter(Context context, List<CategoryResponse> categoryList,Button btn_sell_scrap){
        this.context = context;
        this.categoryList = categoryList;
        inflater = (LayoutInflater.from(context));
        this.btn_sell_scrap = btn_sell_scrap;
        this.subCategoryList = new ArrayList<>();
        this.selectedCategory = new ArrayList<>();
    }

    public ArrayList<Integer> getData(){
        return this.subCategoryList;
    }

    public ArrayList<String> getSelectedCategory(){return this.selectedCategory;}

    @Override
    public int getCount() {
        return this.categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.main_categories_layout, null);
        TextView categoryName = convertView.findViewById(R.id.main_category_Scrap_title);
        ImageView imageView = convertView.findViewById(R.id.main_category_grid_layout_imageview);
        categoryName.setText(categoryList.get(position).getCategoryName().toUpperCase());
        imageView.setImageBitmap(CategoryResponse.convertToBitMap(categoryList.get(position).getCategoryImage()));
        CheckBox selectedCheckBox = convertView.findViewById(R.id.main_category_Scrap_checkbox);

        selectedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    selectedCategory.add(categoryList.get(position).getCategoryId());
                } else {
                    selectedCategory.remove(categoryList.get(position).getCategoryId());
                }

                if(selectedCategory.isEmpty()){
                    btn_sell_scrap.setEnabled(false);
                    btn_sell_scrap.setBackground(context.getResources().getDrawable(R.drawable.btn_disable_bg));
                } else {
                    btn_sell_scrap.setEnabled(true);
                    btn_sell_scrap.setBackground(context.getResources().getDrawable(R.drawable.profile_btn_bg));
                }
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedCheckBox != null && btn_sell_scrap != null){
                    if (!selectedCheckBox.isChecked()) {
                        selectedCheckBox.setChecked(true);
                    } else {
                        selectedCheckBox.setChecked(false);
                    }
                }
            }
        });

        return convertView;
    }


}
