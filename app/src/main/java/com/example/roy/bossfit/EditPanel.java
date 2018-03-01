package com.example.roy.bossfit;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.roy.bossfit.Database.AppDatabase;
import com.example.roy.bossfit.Database.DBDAO;
import com.example.roy.bossfit.Database.Exercise;
import com.example.roy.bossfit.Database.Plan;

import java.util.ArrayList;
import java.util.List;

public class EditPanel extends AppCompatActivity {
    public int imageId=1;
    List<ConstraintLayout> exercises;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_panel);
        exercises=new ArrayList<>();

    }
    public void addPressed(View view){

        LinearLayout list=(LinearLayout) findViewById(R.id.exerciseList);
        LayoutInflater inf=LayoutInflater.from(this);
        ConstraintLayout con=(ConstraintLayout) inf.inflate(R.layout.edit_exercise, null, false);

        list.addView(con);
        exercises.add(con);

    }
    public void editPlanImg(View view){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,imageId);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

        }
    }

    public void savePressed(View view){
        AppDatabase db=AppDatabase.getAppDatabase(this);
        DBDAO dao=db.userDao();
        Plan p=new Plan();
        p.setName(((EditText)findViewById(R.id.txtPlName)).getText().toString());
        p.setProfileFK(1);
        long pid= dao.insertPlan();

        for (ConstraintLayout cl:exercises) {
            String name=((EditText)cl.getChildAt(0)).getText().toString();
            int sets=Integer.parseInt(((EditText)cl.getChildAt(1)).getText().toString());
            int reps=Integer.parseInt(((EditText)cl.getChildAt(2)).getText().toString());
            float weight=Float.parseFloat(((EditText)cl.getChildAt(3)).getText().toString());
            Exercise e= new Exercise();
            e.setName(name);
            e.setSets(sets);
            e.setRepetitions(reps);
            e.setWeight(weight);
            e.setPlanFK(pid);
            dao.insertExercise(e);
        }

    }
    public void removePressed(View view){
        exercises.remove(view.getParent());
        ((ViewManager)view.getParent().getParent()).removeView((View)view.getParent());
    }

}
