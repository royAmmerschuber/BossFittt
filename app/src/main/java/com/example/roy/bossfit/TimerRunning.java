package com.example.roy.bossfit;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jerom.rajan on 07.03.2018.
 */



public class TimerRunning extends AppCompatActivity
{
    /**
     * states of the timer also the titles for the states
     */
    private final static String STATE_START ="Get Ready";
    private final static String STATE_WORK="Work";
    private final static String STATE_REST="Rest";
    private final static String STATE_FINISHED="Finished";
    /**
     * curren state
     */
    private String state =STATE_START;
    /**
     * Textviews for displaying information
     */
    private TextView txtText;
    private TextView txtTimer;
    private TextView txtSet;
    /**
     * background to set bgColor
     */
    private ConstraintLayout bg;
    /**
     * counter for counting
     */
    private CountDownTimer counter;
    /**
     * if element is paused
     */
    private boolean paused=false;
    /**
     * work,restime and amount of sets
     */
    private int workTime = 0;
    private int restTime = 0;
    private int sets = 0;
    /**
     * how many sets are done
     */
    private int finishedSets=0;
    /**
     * the current time of displayed timer
     */
    private int currentTime=10;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_running);

        bg=findViewById(R.id.background);
        txtSet=findViewById(R.id.lblSets);
        txtTimer=findViewById(R.id.lblTimer);
        txtText =findViewById(R.id.txtText);

        sets = getIntent().getExtras().getInt("sets");
        restTime = getIntent().getExtras().getInt("restTime");
        workTime = getIntent().getExtras().getInt("workTime");

        txtSet.setText(Integer.toString(sets));
        txtText.setText(state);
        bg.setBackgroundColor(getColor(R.color.timerBlue));
        counter=new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {
                countdown();
            }

            @Override
            public void onFinish() {
                Tfinish();
            }
        };
        counter.start();
    }

    /**
     * updates every second
     */
    public void countdown(){
        currentTime-=1;
        txtTimer.setText(getTime(currentTime));
    }

    /**
     * closes app
     */
    @Override
    public void onBackPressed() {
        finish();
    }

    /**
     * ends the timer
     */
    public void Tfinish(){

        switch(state){
            case STATE_REST:
            case STATE_START:{
                state=STATE_WORK;
                bg.setBackgroundColor(getColor(R.color.timerGreen));
                currentTime=workTime;
                txtText.setText(state);
            }break;
            case STATE_WORK:{
                finishedSets++;
                txtSet.setText(Integer.toString(sets-finishedSets));
                if(finishedSets>= sets){
                    state=STATE_FINISHED;
                    txtText.setText(state);
                    bg.setBackgroundColor(getColor(R.color.timerBlue));
                    txtSet.setText("0");
                    txtTimer.setText("0:00");
                    //TODO
                    return;
                }else{
                    state=STATE_REST;
                    txtText.setText(state);
                    currentTime=restTime;
                    bg.setBackgroundColor(getColor(R.color.timerRed));
                }

            }break;

        }
        counter=new CountDownTimer(currentTime*1000,1000) {
                    @Override
                    public void onTick(long l) {
                        countdown();
                    }

                    @Override
                    public void onFinish() {
                        Tfinish();
                    }
                };
        counter.start();

    }

    /**
     * pause button pauses timer
     * @param view
     */
    public void onPause(View view){
        FloatingActionButton b=findViewById(R.id.timerPause);

        if(paused) {
            switch (state) {
                case STATE_REST:{
                    bg.setBackgroundColor(getColor(R.color.timerRed));

                }
                break;
                case STATE_WORK: {
                    bg.setBackgroundColor(getColor(R.color.timerGreen));
                }break;
            }
            counter=new CountDownTimer(currentTime*1000,1000) {
                @Override
                public void onTick(long l) {
                    countdown();
                }

                @Override
                public void onFinish() {
                    Tfinish();
                }
            }.start();
            b.setImageDrawable(getDrawable(android.R.drawable.ic_media_pause));
        }else{
            bg.setBackgroundColor(getColor(R.color.timerBlue));
            counter.cancel();
            b.setImageDrawable(getDrawable(android.R.drawable.ic_media_play));
        }
        paused=!paused;
    }

    /**
     * generates timeString out of seconds
     * @param seconds
     * @return
     */
    private String getTime(int seconds){
        float s=seconds;
        double m=Math.floor(s/60);
        s-=m*60;
        return Math.round(m)+":"+Math.round(s);
    }
}
