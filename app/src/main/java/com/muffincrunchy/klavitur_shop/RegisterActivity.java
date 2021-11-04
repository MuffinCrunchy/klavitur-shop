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
import android.widget.TextView;

import klavitur_shop.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtNama, txtUser, txtPass;
    private SharedPreferences mPreferences;
    private static final String mSharedAcc = "AccSys";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtNama = findViewById(R.id.et_nama_reg);
        txtUser = findViewById(R.id.et_user_reg);
        txtPass = findViewById(R.id.et_pass_reg);

        TextView toLog = findViewById(R.id.to_log);
        toLog.setOnClickListener(v -> toLogin());
    }

    private void toLogin() {
        Intent iLog = new Intent(this, LoginActivity.class);
        startActivity(iLog);
    }

    public void submitRegister(View view) {
        mPreferences = getSharedPreferences(mSharedAcc, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("myName", txtNama.getText().toString());
        editor.putString("myUser", txtUser.getText().toString());
        editor.putString("myPass", txtPass.getText().toString());
        editor.apply();
        confirm();
        txtNama.setText("");
        txtUser.setText("");
        txtPass.setText("");
    }

    public void resetRegister(View view) {
        txtNama.setText("");
        txtUser.setText("");
        txtPass.setText("");
    }

    private void confirm() {
        AlertDialog.Builder psn = new AlertDialog.Builder(this)
                .setTitle("Registrasi Berhasil")
                .setMessage("Silahkan isi kembali untuk konfirmasi")
                .setIcon(R.drawable.ic_alert_fail)
                .setNeutralButton("OK", (dialog, which) -> toLogin());

        psn.show();
    }
}