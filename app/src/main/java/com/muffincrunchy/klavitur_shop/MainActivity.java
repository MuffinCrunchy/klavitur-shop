package com.muffincrunchy.klavitur_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import klavitur_shop.R;

public class MainActivity extends AppCompatActivity implements RecycleViewAdapter.OnItemClickListener {

    private int btnCount;
    private ArrayList<Item> mItemData;
    private RecyclerView mRecyclerView;
    private RecycleViewAdapter mAdapter;
    private LinearLayout totalPriceLay;
    private TextView totalPriceTxt;
    private int price;
    private SharedPreferences mPreferences;
    private static final String mSharedPcs = "PcsSys";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        totalPriceTxt = findViewById(R.id.total_price);
        totalPriceLay = findViewById(R.id.purchase_layout);
        totalPriceLay.setOnClickListener(view -> { toPurchase(); });
        mPreferences = getSharedPreferences(mSharedPcs, Activity.MODE_PRIVATE);
        totalPriceTxt.setText(mPreferences.getString("myPrice", null));

        mRecyclerView = findViewById(R.id.rv_item);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        mItemData = new ArrayList<>();

        mAdapter = new RecycleViewAdapter(this, mItemData, this);
        mRecyclerView.setAdapter(mAdapter);

        initializeData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String phoneNumber = "081809003056";
        switch (item.getItemId()) {
            case R.id.action_sms:
                Intent sendMsg = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber));
                sendMsg.putExtra("sms_body", "Can I ask something?");
                startActivity(sendMsg);
                return true;
            case R.id.action_call:
                Intent dialPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                startActivity(dialPhone);
                return true;
            case R.id.action_map:
                String loc = "Transmart Tegal";
                Intent openLoc = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + loc));
                startActivity(openLoc);
                return true;
            case R.id.action_user:
                Intent update = new Intent(this, UpdateActivity.class);
                startActivity(update);
                return true;
            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeData() {
        String[] itemName = getResources().getStringArray(R.array.item_name);
        String[] itemPrice = getResources().getStringArray(R.array.item_price);
        String[] itemDesc = getResources().getStringArray(R.array.item_desc);
        TypedArray itemImg = getResources().obtainTypedArray(R.array.item_img);

        mItemData.clear();

        for (int i=0; i<itemName.length; i++) {
            mItemData.add(new Item(itemName[i], itemPrice[i], itemDesc[i], itemImg.getResourceId(i, 0)));
        }

        itemImg.recycle();

        mAdapter.notifyDataSetChanged();
    }

    private void toPurchase() {
        Intent purchase = new Intent(this, PurchaseActivity.class);
        startActivity(purchase);
    }

    @Override
    public void onBackPressed() {
        if (btnCount >=1) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            System.exit(0);
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        btnCount++;
    }


    @Override
    public void onImgClicked(int position) {
        price = Integer.parseInt(mItemData.get(position).getPrice()) + Integer.parseInt(totalPriceTxt.getText().toString());
        totalPriceTxt.setText(String.valueOf(price));

        mPreferences = getSharedPreferences(mSharedPcs, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("myPrice", totalPriceTxt.getText().toString());
        editor.apply();
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("name", mItemData.get(position).getName());
        intent.putExtra("price", mItemData.get(position).getPrice());
        intent.putExtra("desc", mItemData.get(position).getDesc());
        intent.putExtra("img", mItemData.get(position).getImg());
        startActivity(intent);

    }
}