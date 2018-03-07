package com.example.roy.bossfit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.roy.bossfit.Database.AppDatabase;
import com.example.roy.bossfit.Database.DBDAO;
import com.example.roy.bossfit.Database.Plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jerom.rajan on 02.03.2018.
 */

    public class DiscoverPlans extends AppCompatActivity
    {
        private ExpandableListView listView;
        private ExpandableListAdapter listAdapter;
        private List<Plan> listDataHeader;

        @Override
        protected void onRestart() {
            super.onRestart();
            initData();
        }

        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_discover_plans);

            listView = (ExpandableListView)findViewById(R.id.lvExp);
            listAdapter = new ExpandableListAdapter(this);
            initData();
            listView.setAdapter(listAdapter);

        }

        private void initData()
        {
            DBDAO dao=AppDatabase.getAppDatabase(this).DBDao();
            listDataHeader=dao.getPlans(1);
            listAdapter.setData(listDataHeader);
        }

        public void addClicked(View view){
            startActivity(new Intent(this,EditPanel.class));
        }

        public void removeClicked(View view){
            final int i=(int)((View)view.getParent()).getTag();
            Log.i("remove",i+"");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure you want to delete this nice plan?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    AppDatabase.getAppDatabase(null).DBDao().deletePlan(i);

                    dialog.dismiss();
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Do nothing
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
            initData();
        }

        public void editClicked(View view){
            int i=(int)((View)view.getParent()).getTag();
            Log.i("edit",i+"");
            startActivity(new Intent(this,EditPanel.class).putExtra("edit", i));

        }

    }


