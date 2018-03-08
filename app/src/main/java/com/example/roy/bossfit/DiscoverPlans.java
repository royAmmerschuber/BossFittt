package com.example.roy.bossfit;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.roy.bossfit.Database.AppDatabase;
import com.example.roy.bossfit.Database.DBDAO;
import com.example.roy.bossfit.Database.Plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jerom.rajan on 02.03.2018.
 */

/**
 * List of Plans
 */
public class DiscoverPlans extends AppCompatActivity
{
    /**
     * the listview to fill with elements
     */
    private ExpandableListView listView;
    /**
     * the adapter for filling the listview
     */
    private ExpandableListAdapter listAdapter;
    /**
     * data to fill the listview
     */
    private List<Plan> listDataHeader;

    /**
     * reloads the list on restart
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
    }

    /**
     * sets list up and fills it with data
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_plans);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }
            listView = (ExpandableListView)findViewById(R.id.lvExp);
        listAdapter = new ExpandableListAdapter(this);
        initData();
        listView.setAdapter(listAdapter);

    }

    /**
     * checks if permission is granted
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    finish();
                }
                return;
            }
        }
    }

    /**
     * loads the data
     */
    private void initData(){
        DBDAO dao=AppDatabase.getAppDatabase(this).DBDao();
        listDataHeader=dao.getPlans(1);
        listAdapter.setData(listDataHeader);
    }

    /**
     * opens the add activity
     * @param view
     */
    public void addClicked(View view){
        startActivity(new Intent(this,EditPanel.class));
    }

    /**
     * deletes element of specified view
     * @param view
     */
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

    /**
     * opens edit activity for plan of specified view
     * @param view
     */
    public void editClicked(View view){
        int i=(int)((View)view.getParent()).getTag();
        Log.i("edit",i+"");
        startActivity(new Intent(this,EditPanel.class).putExtra("edit", i));

    }

}


