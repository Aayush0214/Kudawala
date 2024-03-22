package com.project.kudawala;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.kudawala.reponses.SubCategoryResponse;

import java.util.ArrayList;
import java.util.List;

public class price_list_parent_adapter extends RecyclerView.Adapter<price_list_parent_adapter.ViewHolder> {

    List<price_list_parent_model_class> priceListParentModelClassList;
    Context context;
    ArrayList<SubCategoryResponse> selectedScrapTypes;
    public price_list_parent_adapter(List<price_list_parent_model_class> priceListParentModelClassList, Context context) {
        this.priceListParentModelClassList = priceListParentModelClassList;
        this.context = context;
        this.selectedScrapTypes = new ArrayList<>();
    }

    public ArrayList<SubCategoryResponse> getSelectedSubCategoryData(){
        return selectedScrapTypes;
    }

    @NonNull
    @Override
    public price_list_parent_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.price_list_parent_recyclerview, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull price_list_parent_adapter.ViewHolder holder, int position) {
        holder.categoryTitle.setText(priceListParentModelClassList.get(position).categoryTitle);
        price_list_child_adapter childAdapter;
        childAdapter = new price_list_child_adapter(priceListParentModelClassList.get(position).priceListChildModelClassList, context,selectedScrapTypes);
        holder.recyclerView_child.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView_child.setAdapter(childAdapter);
        childAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return priceListParentModelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView_child;
        TextView categoryTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recyclerView_child = itemView.findViewById(R.id.item_price_recycler);
            categoryTitle = itemView.findViewById(R.id.Category_title);
        }
    }
}
