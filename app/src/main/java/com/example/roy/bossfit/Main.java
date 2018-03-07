package com.example.roy.bossfit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;

public class Main extends AppCompatActivity
{
    private static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
    }

    public void openProfile(View view){
        Intent i= new Intent(this,ProfilePanel.class);
        startActivity(i);
    }

    public void discoverPlans(View view){
        Intent i= new Intent(this,DiscoverPlans.class);
        startActivity(i);
    }

    public void openMap(View view){
        Uri intentUri=Uri.parse("geo:0,0?q=gym");
        Intent mapIntent=new Intent(Intent.ACTION_VIEW,intentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void timer(View view){
        Intent i= new Intent(this,TimerControl.class);
        startActivity(i);
    }






}
