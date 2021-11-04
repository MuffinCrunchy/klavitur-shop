package com.muffincrunchy.klavitur_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import klavitur_shop.R;

public class PurchaseActivity extends AppCompatActivity {

    private TextView totalPrice;
    private SharedPreferences mPreferences;
    private static final String mSharedPcs = "PcsSys";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        totalPrice = findViewById(R.id.total_price_detail);

        mPreferences = getSharedPreferences(mSharedPcs, Activity.MODE_PRIVATE);
        totalPrice.setText(mPreferences.getString("myPrice", null));
    }

    public void onPurchase(View view) {
        mPreferences = getSharedPreferences(mSharedPcs, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("myPrice", "0");
        editor.apply();

        confirm();
    }

    public void onCancel(View view) {
        mPreferences = getSharedPreferences(mSharedPcs, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("myPrice", "0");
        editor.apply();

        backToMain();
    }

    private void confirm() {
        AlertDialog.Builder psn = new AlertDialog.Builder(this)
                .setTitle("Pembayaran Berhasil")
                .setMessage("Terima kasih telah berbelanja di toko kami")
                .setIcon(R.drawable.ic_alert_fail)
                .setNeutralButton("OK", (dialog, which) -> {
                    backToMain();
                    finish();
                });

        psn.show();
    }

    private void backToMain() {
        Intent iMain = new Intent(this, MainActivity.class);
        startActivity(iMain);
    }
}