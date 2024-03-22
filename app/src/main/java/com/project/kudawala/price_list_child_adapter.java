package com.project.kudawala;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.kudawala.reponses.SubCategoryResponse;

import java.util.ArrayList;
import java.util.List;

public class price_list_child_adapter extends RecyclerView.Adapter<price_list_child_adapter.ViewHolder> {

    List<SubCategoryResponse> priceListChildModelClassList;
    Context context;

    ArrayList<SubCategoryResponse> selectedSubCategory;



    public price_list_child_adapter(List<SubCategoryResponse> priceListChildModelClassList, Context context, ArrayList<SubCategoryResponse> selectedScrapTypes) {
        this.priceListChildModelClassList = priceListChildModelClassList;
        this.context = context;
        this.selectedSubCategory = selectedScrapTypes;
    }

    @NonNull
    @Override
    public price_list_child_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.price_list_child_recyclerview, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull price_list_child_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.subcategory_title.setText(priceListChildModelClassList.get(position).getSubCategoryName());
        holder.subcategory_price.append(priceListChildModelClassList.get(position).getPrice().toString() + "/Kg");

        holder.subCategoryCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    selectedSubCategory.add(priceListChildModelClassList.get(position));
                } else {
                    selectedSubCategory.remove(position);
                }
            }
        });
        //holder.subcategory_price.setText();
    }

    @Override
    public int getItemCount() {
        return priceListChildModelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView subcategory_title, subcategory_price;
        CheckBox subCategoryCheckbox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subcategory_title = itemView.findViewById(R.id.subcategory_title);
            subcategory_price = itemView.findViewById(R.id.subcategory_price);
            subCategoryCheckbox = itemView.findViewById(R.id.subcategory_checkbox);
        }
    }
}
