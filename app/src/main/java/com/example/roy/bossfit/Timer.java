package com.example.roy.bossfit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.StringTokenizer;

/**
 * Created by jerom.rajan on 06.03.2018.
 */

public class Timer extends AppCompatActivity
{
    public int time= 90;
    public int restTime = 30;
    public int workTime = 90;
    public int set = 3;
    TextView workInterval;
    TextView restInterval;
    TextView sets;
    Button btnIncreaseWork;
    Button btnDecreaseWork;
    Button  btnSetsUp;
    Button btnSetsDown;
    Button btnIncreaseRest;
    Button btnDecreaseRest;

    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        btnIncreaseWork= (Button) findViewById(R.id.btnWorkIntervalUp);
        btnDecreaseWork= (Button) findViewById(R.id.btnWorkIntervalDown);
        btnIncreaseRest= (Button) findViewById(R.id.btnRestIntervalUp);
        btnDecreaseRest= (Button) findViewById(R.id.btnRestIntervalDown);
        btnSetsUp= (Button) findViewById(R.id.btnSetsUp);
        btnSetsDown= (Button) findViewById(R.id.btnSetsDown);

        workInterval = (TextView)findViewById(R.id.workInterval);
        restInterval = (TextView)findViewById(R.id.restInterval);
        sets = (TextView)findViewById(R.id.sets);

        btnIncreaseWork.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                workTime += 10;
                getNewTime(workTime,workInterval);
            }
        });

        btnDecreaseWork.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                workTime -= 10;
                getNewTime(workTime,workInterval);
            }
        });

        btnIncreaseRest.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                restTime += 10;
                getNewTime(restTime,restInterval);
            }
        });

        btnDecreaseRest.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                restTime -= 10;
                getNewTime(restTime,restInterval);
            }
        });

        btnSetsUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                set += 1;
                String myDisplay = set+"";
                sets.setText(myDisplay);
            }
        });

        btnSetsDown.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                set -= 1;
                String myDisplay = set+"";
                sets.setText(myDisplay);
            }
        });
    }

    public void workInterval(View view)
    {
        Intent i= new Intent(this,IntervalWork.class);
        i.putExtra("sets",set);
        i.putExtra("workTime", workTime);
        i.putExtra("restTime", restTime);
        startActivity(i);
    }


    public void getNewTime(int time, TextView view)
    {
        int minutes = (int)Math.floor(time/60);
        int seconds = time % 60;
        String myDisplay = minutes + " : " + seconds;
        view.setText(myDisplay);
    }


    Handler handler = new Handler();
    int delay = 1000; //milliseconds

    public void intervalStart()
    {
        handler.postDelayed(new Runnable()
        {
            public void run()
            {

                handler.postDelayed(this, delay);
            }
        }, delay);
    }

}
