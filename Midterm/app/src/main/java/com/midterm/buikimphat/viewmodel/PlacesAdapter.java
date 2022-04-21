package com.midterm.buikimphat.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.buikimphat.DetailsActivity;
import com.midterm.buikimphat.R;
import com.midterm.buikimphat.model.Place;

import java.util.ArrayList;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {

    private ArrayList<Place> places;
    private Activity activity;

    public PlacesAdapter(ArrayList<Place> places, Activity activity) {
        this.places = places;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(places.get(position).getTitle());
        holder.tvDesc.setText(places.get(position).getDesc());
        holder.tvTime.setText(places.get(position).getTimeStamp());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(activity.getApplicationContext(), DetailsActivity.class);
                intent.putExtra("title", places.get(position).getTitle());
                intent.putExtra("desc", places.get(position).getDesc());
                intent.putExtra("timeStamp", places.get(position).getTimeStamp());
                intent.putExtra("lat", places.get(position).getLat());
                intent.putExtra("lng", places.get(position).getLng());
                intent.putExtra("addr", places.get(position).getAddr());
                intent.putExtra("e", places.get(position).getE());
                intent.putExtra("zip", places.get(position).getZip());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvTitle;
        public TextView tvDesc;
        public TextView tvTime;
        private ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvTime = itemView.findViewById(R.id.tv_time);

            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }
    }
}
