package com.example.roy.bossfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

//            listDataHeader.add("Oberkörper");
//            listDataHeader.add("Unterkörper");
//
//            List<String> oberKörper = new ArrayList<>();
//            oberKörper.add("biceps");
//            oberKörper.add("rücken");
//            oberKörper.add("bauch");
//
//            List<String> unterKörper = new ArrayList<>();
//            unterKörper.add("beine");
//
//            listHash.put(listDataHeader.get(0),oberKörper);
//            listHash.put(listDataHeader.get(1),unterKörper);

            DBDAO dao=AppDatabase.getAppDatabase(this).DBDao();
            List<Plan> plans=dao.getPlans(1);
            for (Plan p:plans)
            {
                int x = 0;
                List<String> exerciceList = new ArrayList<>();
                List<Exercise> exercises = dao.getExercises(p.getId());
                for(Exercise e:exercises)
                {

                    exerciceList.add(e.getName());
                }
                listDataHeader.add(p.getName());
                listHash.put(listDataHeader.get(x),exerciceList);
                x++;
            }

            Exercise e= new Exercise();
            e.setName("Biceps Curls");
            e.setPlanFK(1);
            e.setRepetitions(5);
            e.setSets(10);
            e.setWeight(20);
            dao.insertExercise(e);


        }

        public void addClicked(View view){
            startActivity(new Intent(this,EditPanel.class));
        }

    }


