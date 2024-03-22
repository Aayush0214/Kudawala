package com.project.kudawala;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.project.kudawala.reponses.AndroidOrdersResponse;

import java.util.List;

public class OrderDetailsSettingAdapter extends RecyclerView.Adapter<OrderDetailsSettingAdapter.View_holder>{

   List<AndroidOrdersResponse> ordersResponses;

    public OrderDetailsSettingAdapter(List<AndroidOrdersResponse> ordersResponses) {
        this.ordersResponses = ordersResponses;
    }

    @NonNull
    @Override
    public OrderDetailsSettingAdapter.View_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View list_details = layoutInflater.inflate(R.layout.user_order_details_cardview_design,null);
        View_holder viewHolder = new View_holder(list_details);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsSettingAdapter.View_holder holder, @SuppressLint("RecyclerView") int position) {
        holder.orderedDate.setText(ordersResponses.get(position).getOrderDate());
        holder.orderedItems.setText(ordersResponses.get(position).getOrderItems().split(",")[0]);

        holder.orderDetailCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),UserOrderDescription.class);

                intent.putExtra("OrderedDate",ordersResponses.get(position).getPickupDate());
                intent.putExtra("OrderedItems",ordersResponses.get(position).getOrderItems());
                intent.putExtra("orderAddress",ordersResponses.get(position).getOrderAddress());
                intent.putExtra("orderMobile",ordersResponses.get(position).getMobileNumber());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ordersResponses.size();
    }

    public class View_holder extends RecyclerView.ViewHolder {

        TextView orderedDate;
        TextView orderedItems;
        CardView orderDetailCardView;
        public View_holder(@NonNull View itemView) {
            super(itemView);
            orderedDate = itemView.findViewById(R.id.item_ordered_date);
            orderedItems = itemView.findViewById(R.id.user_ordered_items);
            orderDetailCardView = itemView.findViewById(R.id.order_details_cardView);
        }
    }
}
