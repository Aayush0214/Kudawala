package com.project.kudawala;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.kudawala.reponses.SubCategoryResponse;

import java.util.ArrayList;

public class Selected_scrap_adapter extends BaseAdapter {
    Context context;

    LayoutInflater inflater;   //to get view/layout which we have created to display the data

    ArrayList<SubCategoryResponse> subCategoryResponses;
    public Selected_scrap_adapter(Context context,ArrayList<SubCategoryResponse> subCategoryResponses){
        this.context = context;
        this.subCategoryResponses = subCategoryResponses;
        inflater = (LayoutInflater.from(context));
    }
    @Override
    public int getCount() {
        return subCategoryResponses.size();
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
        convertView = inflater.inflate(R.layout.selected_scrap_layout, null); //this is for finding layout designed for displaying the data
        TextView itemsName = convertView.findViewById(R.id.items_Name);             // this is for finding layout's component where data will be shown
        TextView itemsPrice = convertView.findViewById(R.id.items_Rate);             // this is for finding layout's component where data will be shown

        //now initializing data according to position
        itemsName.setText(this.subCategoryResponses.get(position).getSubCategoryName());
        itemsPrice.append(this.subCategoryResponses.get(position).getPrice().toString());

        return convertView;
    }
}
