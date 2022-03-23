package com.example.contactapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avatarfirst.avatargenlib.AvatarConstants;
import com.avatarfirst.avatargenlib.AvatarGenerator;

import java.io.InputStream;
import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<Contact> contacts;
    private Activity activity;

    public ContactAdapter(ArrayList<Contact> contacts, Activity activity) {
        this.contacts = contacts;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(contacts.get(position).getName());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(view.getContext(), ContactDetailActivity.class);
                intent.putExtra("contactID", contacts.get(position).getId());
                activity.startActivityForResult(intent, 234);
            }
        });

        if (contacts.get(position).getAvatar() != null) {
            holder.ivAvatar.setImageURI(Uri.parse(contacts.get(position).getAvatar()));
        } else {
            holder.ivAvatar.setImageDrawable(
                    AvatarGenerator.Companion.avatarImage(
                            holder.ivAvatar.getContext(),
                            200,
                            AvatarConstants.Companion.getCIRCLE(),
                            holder.tvName.getText().toString().charAt(0)+""
                    ));
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvName;
        public ImageView ivAvatar;
        private ItemClickListener itemClickListener;

        public ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_name);
            ivAvatar = view.findViewById(R.id.iv_avatar);

            view.setOnClickListener(this);
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
