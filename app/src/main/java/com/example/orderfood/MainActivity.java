package com.example.orderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.orderfood.Fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                SharedPreferences userPref = getApplicationContext().getSharedPreferences("user",Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = userPref.edit();
//                editor.clear();
//                editor.apply();
//                boolean isLogged = userPref.getBoolean("Logged",false);
//                if(isLogged){
//                    startActivity(new Intent(MainActivity.this,AuthActivity.class));
//                    finish();
//                }
//                else {
//                    startActivity(new Intent(MainActivity.this,AuthActivity.class));
//                    finish();
//                }
//                isFirstTime();
                startActivity(new Intent(MainActivity.this, AuthActivity.class));

            }
        }, 3000);
    }




//    private void isFirstTime() {
//        SharedPreferences preferences = getApplication().getSharedPreferences("onBoard", Context.MODE_PRIVATE);
//        boolean isFristTime = preferences.getBoolean("isFirstTime",true);
//        if(isFristTime){
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putBoolean("isFirstTime",false);
//            editor.apply();
//
////            start Onboard activity
//            startActivity(new Intent(MainActivity.this,OnboardActivity.class));
//            finish();
//        }
//        else{
//            //start Auth Activity
//            startActivity(new Intent(MainActivity.this,AuthActivity.class));
//            finish();
//        }
//    }
}
