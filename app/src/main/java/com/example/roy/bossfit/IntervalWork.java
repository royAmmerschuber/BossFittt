package com.example.roy.bossfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jerom.rajan on 07.03.2018.
 */



public class IntervalWork extends AppCompatActivity
{
    TextView workInterval;
    TextView sets;
    public int restTime = 0;
    public int workTime = 0;
    public int set = 0;

    protected void onCreate(Bundle savedInstanceState)
    {
        set = getIntent().getExtras().getInt("sets");
        restTime = getIntent().getExtras().getInt("restTime");
        workTime = getIntent().getExtras().getInt("workTime");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_interval);

    }

    public void restInterval(View view){
        Intent i= new Intent(this,IntervalRest.class);
        startActivity(i);
    }
}
