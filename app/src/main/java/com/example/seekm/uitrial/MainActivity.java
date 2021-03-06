package com.example.seekm.uitrial;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;
import android.widget.VideoView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "MAINACTIVITY";
    VideoView videoView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } catch (Error err) {
            Log.d(TAG, "onCreate: Error");
        }
        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();

        try {
            videoView = findViewById(R.id.videoView);
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);
            videoView.setVideoURI(video);

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    if (isFinishing())
                        return;

                    Intent intent = new Intent(MainActivity.this, OnBoarding_1.class);
                    startActivity(intent);
                    printKeyHash();
                    finish();
                }
            });
            videoView.start();
        } catch (Error err) {
            Log.d(TAG, "onCreateInMainActivity: " + err.getMessage());
            Intent intent = new Intent(MainActivity.this, OnBoarding_1.class);
            startActivity(intent);
            printKeyHash();
        }
    }

    private void printKeyHash() {
        try {

            PackageInfo Info = getPackageManager().getPackageInfo("com.example.seekm.uitrial", PackageManager.GET_SIGNATURES);

            for (Signature Signature : Info.signatures) {

                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(Signature.toByteArray());
                Log.e("KEYHASH", Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
