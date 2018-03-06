package com.example.roy.bossfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.roy.bossfit.Database.AppDatabase;
import com.example.roy.bossfit.Database.DBDAO;
import com.example.roy.bossfit.Database.Exercise;
import com.example.roy.bossfit.Database.Plan;

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
        private List<Plan> listDataHeader;

        @Override
        protected void onRestart() {
            super.onRestart();

        }

        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_discover_plans);

            listView = (ExpandableListView)findViewById(R.id.lvExp);
            initData();
            listAdapter = new ExpandableListAdapter(this,listDataHeader);
            listView.setAdapter(listAdapter);

        }

        private void initData()
        {

            DBDAO dao=AppDatabase.getAppDatabase(this).DBDao();
            listDataHeader=dao.getPlans(1);
        }

        public void addClicked(View view){
            startActivity(new Intent(this,EditPanel.class));
        }

    }


