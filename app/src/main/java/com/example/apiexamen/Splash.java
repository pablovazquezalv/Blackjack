package com.example.apiexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Splash extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
         findViewById(R.id.b2).setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
    CountDownTimer tiempo= new CountDownTimer(5000, 1000)
    {

        public void onTick(long milisUntilFinished)
        {
            TextView tiempo=findViewById(R.id.b2);
            tiempo.setText(""+milisUntilFinished /1000);
        }

        public void onFinish()
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }.start();
}