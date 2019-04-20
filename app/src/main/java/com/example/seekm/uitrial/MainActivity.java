package com.example.seekm.uitrial;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Handler;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    ImageView logo,map,back,eclipse,forward;
    TextView text,getStarted;
    private static final String TAG = "GoogleActivity";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            printKeyHash();

            nextActivity();





    }

    private void printKeyHash() {
        try{

            PackageInfo  Info = getPackageManager().getPackageInfo("com.example.seekm.uitrial", PackageManager.GET_SIGNATURES);

            for(Signature Signature:Info.signatures){


                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(Signature.toByteArray());
                Log.e("KEYHASH",Base64.encodeToString(messageDigest.digest(),Base64.DEFAULT));
                


            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    void nextActivity(){
        //Activity delay function
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {









                if (FirebaseAuth.getInstance().getCurrentUser() != null) {

                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                final   String User_uid1 = currentFirebaseUser.getUid();


                DocumentReference docRef = db.collection("Tutors").document(User_uid1);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {



                                finishAffinity();
                                startActivity(new Intent(MainActivity.this,Drawer.class));




                            } else {



                                finishAffinity();
                                startActivity(new Intent(MainActivity.this,NextActivity.class));

                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });




                } else {

                    Intent i = new Intent(MainActivity.this, MobileV.class);
                    startActivity(i);
                    finish();
                }
            }
        }, 3000);
    }

}
