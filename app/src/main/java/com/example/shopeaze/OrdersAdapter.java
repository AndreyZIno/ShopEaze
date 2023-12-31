package com.example.shopeaze;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {

    private List<Order> orderList;

    public OrdersAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        String displayText = "Order " + order.getOrderNumber() + " - Status: " + order.getStatus();
        holder.status.setText(displayText);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order clickedOrder = orderList.get(position);
                OrderDetailDialog dialog = new OrderDetailDialog(clickedOrder);
                dialog.show(((FragmentActivity) v.getContext()).getSupportFragmentManager(), "OrderDetailDialog");
            }
        });

    }


    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView status;

        public OrderViewHolder(View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.status_text);
        }
    }
}