package com.example.dogapp.viewmodel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.dogapp.R;
import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DogsAdapter extends RecyclerView.Adapter<DogsAdapter.ViewHolder> {

    // This object helps you save/restore the open/close state of each view
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    private ArrayList<DogBreed> dogBreeds;

    public DogsAdapter(ArrayList<DogBreed> dogBreeds) {
        this.dogBreeds = dogBreeds;
    }

    @NonNull
    @Override
    public DogsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_breed_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogsAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(dogBreeds.get(position).getName());
        Picasso.get().load(dogBreeds.get(position).getUrl())
                .resize(300, 255)
                .centerCrop().into(holder.ivThumbnail);
        String origin = dogBreeds.get(position).getOrigin();
        String lifeSpan = dogBreeds.get(position).getLifeSpan();
        String description = "";
        if (lifeSpan != null) description += lifeSpan;
        if (origin != null){
            if (lifeSpan != null) description += ", " + origin;
            else description += description += origin;
        }
        holder.tvDescription.setText(description);

        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(dogBreeds.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return dogBreeds.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivThumbnail;
        public TextView tvName;
        public TextView tvDescription;
        public SwipeRevealLayout swipeRevealLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.iv_thumbnail);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDescription = itemView.findViewById(R.id.tv_description);
            swipeRevealLayout = itemView.findViewById(R.id.swipe_layout);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            DogBreed dogBreed = dogBreeds.get(getAdapterPosition());
            Bundle bundle = new Bundle();
            bundle.putSerializable("dogBreed", dogBreed);
            Navigation.findNavController(itemView).navigate(R.id.detailsFragment, bundle);
        }
    }
}
