package com.muffincrunchy.klavitur_shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import klavitur_shop.R;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Item> mItemData;
    private OnItemClickListener mClickListener;

    interface OnItemClickListener {
        void onImgClicked(int position);
        void onItemClicked(int position);
    }

    public RecycleViewAdapter(Context mContext, ArrayList<Item> mItemData, OnItemClickListener mClickListener) {
        this.mContext = mContext;
        this.mItemData = mItemData;
        this.mClickListener = mClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false), mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item currentItem = mItemData.get(position);
        holder.bindTo(currentItem);
        Glide.with(mContext)
                .load(currentItem.getImg())
                .into(holder.mItemImg);
    }

    @Override
    public int getItemCount() {
        return mItemData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mItemImg;
        private TextView mItemName, mItemPrice, mItemDesc;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener mClickListener) {
            super(itemView);

            mItemImg = itemView.findViewById(R.id.item_img);
            mItemName = itemView.findViewById(R.id.item_name);
            mItemPrice = itemView.findViewById(R.id.item_price);
            mItemDesc = itemView.findViewById(R.id.item_desc);

            itemView.setOnClickListener(view -> {
                if (mClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mClickListener.onItemClicked(position);
                    }
                }
            });

            mItemImg.setOnClickListener(view -> {
                if (mClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mClickListener.onImgClicked(position);
                    }
                }
            });
        }

        void bindTo(Item currentItem) {
            mItemName.setText(currentItem.getName());
            mItemPrice.setText(currentItem.getPrice());
        }
    }
}
