package com.muffincrunchy.klavitur_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import klavitur_shop.R;

public class DetailActivity extends AppCompatActivity {

    private ImageView mItemImgDetail;
    private TextView mItemNameDetail, mItemPriceDetail, mItemDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mItemImgDetail = findViewById(R.id.item_img_detail);
        mItemNameDetail = findViewById(R.id.item_name_detail);
        mItemPriceDetail = findViewById(R.id.item_price_detail);
        mItemDesc = findViewById(R.id.item_desc);

        String name = getIntent().getStringExtra("name");
        mItemNameDetail.setText(name);
        String price = getIntent().getStringExtra("price");
        mItemPriceDetail.setText(price);
        String desc = getIntent().getStringExtra("desc");
        mItemDesc.setText(desc);
        Glide.with(this)
                .load(getIntent().getIntExtra("img", 0))
                .into(mItemImgDetail);

    }
}