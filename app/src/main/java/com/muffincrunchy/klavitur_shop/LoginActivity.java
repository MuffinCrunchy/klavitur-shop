package com.muffincrunchy.klavitur_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import klavitur_shop.R;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUser, txtPass;
    private SharedPreferences mPreferences;
    private static final String mSharedAcc = "AccSys";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        setContentView(R.layout.activity_login);

        txtUser = findViewById(R.id.et_user_log);
        txtPass = findViewById(R.id.et_pass_log);

        TextView toReg = findViewById(R.id.to_reg);
        toReg.setOnClickListener(v -> toRegister());
    }

    private void toRegister() {
        Intent iReg = new Intent(this, RegisterActivity.class);
        startActivity(iReg);
    }

    public void submitLogin(View view) {
        mPreferences = getSharedPreferences(mSharedAcc, Activity.MODE_PRIVATE);
        if (txtUser.getText().toString().equals(mPreferences.getString("myUser", null)) && txtPass.getText().toString().equals(mPreferences.getString("myPass", null))) {
            Intent iMain = new Intent(this, MainActivity.class);
            startActivity(iMain);
            Toast.makeText(getApplicationContext(), "Selamat Datang, " + mPreferences.getString("myName", null), Toast.LENGTH_SHORT).show();
        } else {
            alert();
        }

        txtUser.setText("");
        txtPass.setText("");
    }

    public void resetLogin(View view) {
        txtUser.setText("");
        txtPass.setText("");
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void alert() {

        AlertDialog.Builder psn = new AlertDialog.Builder(this)
                .setTitle("Login Gagal")
                .setMessage("Username atau Password salah. \nSilahkan registrasi jika belum memiliki akun.")
                .setIcon(R.drawable.ic_alert_fail)
                .setNeutralButton("OK", (dialog, which) -> dialog.cancel());
        psn.show();
    }
}