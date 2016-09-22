package com.example.jinbailiang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import com.example.jinbailiang.demos_jinbai.R;

public class WellComeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_WellCome;
    private Handler handler = new Handler() {

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_well_come);


        initViews();
        setListeners();
//        toMainActivity();
    }

    private void toMainActivity() {
                 handler.postDelayed(new Runnable() {

             @Override
             public void run() {
                 Intent intent = new Intent(WellComeActivity.this, MainActivity.class);
                 startActivity(intent);
                 WellComeActivity.this.finish();
            }
        }, 3000);


       /* Runnable toMainActivityRunnable = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(i <4){
                    i++;

                }
            }
        };
        MyApplication.getThreadPoolExecutor().execute(toMainActivityRunnable);*/
    }

    private void setListeners() {
        tv_WellCome.setOnClickListener(this);
    }

    private void initViews() {
        tv_WellCome = (TextView)findViewById(R.id.tv_WellCome);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_WellCome:
                startActivity(new Intent(WellComeActivity.this,MainActivity.class));
                WellComeActivity.this.finish();
                break;
        }
    }
}
