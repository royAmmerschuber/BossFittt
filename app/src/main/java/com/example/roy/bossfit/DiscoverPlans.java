package com.example.roy.bossfit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.example.roy.bossfit.Database.AppDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jerom.rajan on 02.03.2018.
 */

    public class DiscoverPlans extends AppCompatActivity
    {
        private SectionsPageAdapter mSectionsPageAdapter;
        private ExpandableListView listView;
        private ExpandableListAdapter listAdapter;
        private List<String> listDataHeader;
        private HashMap<String,List<String>> listHash;

        protected void onCreate(Bundle savedInstanceState)
        {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_discover_plans);

            listView = (ExpandableListView)findViewById(R.id.lvExp);
            initData();
            listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
            listView.setAdapter(listAdapter);

        }

        private void initData()
        {

            listDataHeader = new ArrayList<>();
            listHash = new HashMap<>();



            listDataHeader.add("EDMTDev");
            listDataHeader.add("Android");
            listDataHeader.add("Xamarin");
            listDataHeader.add("UWP");

            List<String> edmtDev = new ArrayList<>();
            edmtDev.add("This is Expandable ListView");

            List<String> androidStudio = new ArrayList<>();
            androidStudio.add("Expandable ListView");
            androidStudio.add("Google Map");
            androidStudio.add("Chat Application");
            androidStudio.add("Firebase ");

            List<String> xamarin = new ArrayList<>();
            xamarin.add("Xamarin Expandable ListView");
            xamarin.add("Xamarin Google Map");
            xamarin.add("Xamarin Chat Application");
            xamarin.add("Xamarin Firebase ");

            List<String> uwp = new ArrayList<>();
            uwp.add("UWP Expandable ListView");
            uwp.add("UWP Google Map");
            uwp.add("UWP Chat Application");
            uwp.add("UWP Firebase ");

            listHash.put(listDataHeader.get(0),edmtDev);
            listHash.put(listDataHeader.get(1),androidStudio);
            listHash.put(listDataHeader.get(2),xamarin);
            listHash.put(listDataHeader.get(3),uwp);

        }

    }


