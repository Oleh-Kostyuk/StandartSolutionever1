package com.example.standartsolutionever;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.standartsolutionever.entites.Order;

import java.util.List;

/**
}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Order> orders;



    public MyItemRecyclerViewAdapter(List<Order> orders) {
        this.orders = orders;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_my_custom_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.order= orders.get(position);
        holder.mProvider.setText(orders.get(position).getProvider());
        holder.mCarrier.setText(orders.get(position).getCarrier());
        holder.mKindOfRwm.setText(orders.get(position).getKindRwm());
        holder.mTypeofRwm.setText(orders.get(position).getTypeRwm());
        holder.mQuantity.setText(orders.get(position).getQuantity().toString());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Order order;
        public final View mView;
        public final TextView mProvider;
        public final TextView mCarrier;
        public final TextView mKindOfRwm;
        public final TextView mTypeofRwm;
        public final TextView mQuantity;

       ;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mProvider =  view.findViewById(R.id.provider);
            mKindOfRwm =  view.findViewById(R.id.kindRwm);
            mTypeofRwm =  view.findViewById(R.id.typeRwm);
            mCarrier = view.findViewById(R.id.carrier);
            mQuantity=view.findViewById(R.id.quantity);;
        }

        @Override
        public String toString() {
            return "ViewHolder{" +
                    "order=" + order +
                    ", mView=" + mView +
                    ", mProvider=" + mProvider +
                    ", mCarrier=" + mCarrier +
                    ", mKindOfRwm=" + mKindOfRwm +
                    ", mTypeofRwm=" + mTypeofRwm +
                    ", mQuantity=" + mQuantity +
                    '}';
        }
    }
}