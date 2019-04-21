package com.example.seekm.uitrial;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class MobileV extends AppCompatActivity implements
        View.OnClickListener {

    public TextView connectSocial;
    public EditText phone_number;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21)
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));

        //locking orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_mobile_verification);

        phone_number = findViewById(R.id.number);
        phone_number.setOnClickListener(this);
        phone_number.setShowSoftInputOnFocus(false);

        connectSocial = findViewById(R.id.connectSocial);
        connectSocial.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.number:
                startActivity(new Intent(MobileV.this, MobileVerification2.class));

                break;
            case R.id.connectSocial:
                startActivity(new Intent(MobileV.this, SocialVerification.class));
                break;
        }
    }
}