package com.example.roy.bossfit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by jerom.rajan on 02.03.2018.
 */

public class ProfilePanel extends AppCompatActivity
{
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_panel);

        activityProfilPanel();
    }

    protected  void activityProfilPanel()
    {
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        ImageView userImage = (ImageView) findViewById(R.id.userImg);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.foto_platzhalter);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        userImage.setImageDrawable(roundedBitmapDrawable);
    }

    private void setupViewPager(ViewPager viewPager)
    {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Profile(), "My Plans");
        adapter.addFragment(new Tab2Profile(), "My Profile");
        adapter.addFragment(new Tab3Profile(), "Edit Profile");
        viewPager.setAdapter(adapter);
    }

}
