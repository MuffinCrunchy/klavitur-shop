package com.muffincrunchy.klavitur_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import klavitur_shop.R;

public class UpdateActivity extends AppCompatActivity {

    private EditText txtUser, txtPass;
    private SharedPreferences mPreferences;
    private static final String mSharedAcc = "AccSys";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        txtUser = findViewById(R.id.et_user_up);
        txtPass = findViewById(R.id.et_pass_up);
    }

    public void submitUpdate(View view) {
        mPreferences = getSharedPreferences(mSharedAcc, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("myUser", txtUser.getText().toString());
        editor.putString("myPass", txtPass.getText().toString());
        editor.apply();
        confirm();
        txtUser.setText("");
        txtPass.setText("");
    }

    public void resetUpdate(View view) {
        txtUser.setText("");
        txtPass.setText("");
    }

    private void confirm() {
        AlertDialog.Builder psn = new AlertDialog.Builder(this)
                .setTitle("Update Berhasil")
                .setMessage("Data anda berhasil diperbarui")
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