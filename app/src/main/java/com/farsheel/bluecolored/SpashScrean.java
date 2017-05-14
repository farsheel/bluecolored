package com.farsheel.bluecolored;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;



public class SpashScrean extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_spash_screan);

        final Intent in=new Intent(this,MainActivity.class);

        Handler ha=new Handler();
        ha.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(in);
                finish();
            }
        },3000);
    }


}
