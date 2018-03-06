package com.example.roy.bossfit;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.roy.bossfit.Database.AppDatabase;
import com.example.roy.bossfit.Database.DBDAO;
import com.example.roy.bossfit.Database.Exercise;
import com.example.roy.bossfit.Database.Plan;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EditPanel extends AppCompatActivity {
    private static final int REQ_PL_IMG = 1;
    private static final int REQ_EX_IMG = 2;

    private String path;
    List<ConstraintLayout> exercises;
    List<String> exPaths;
    Integer id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_panel);
        exercises=new ArrayList<>();
        exPaths=new ArrayList<>();
        if(getIntent().getIntExtra("edit",-10)!=-10){
            id=getIntent().getIntExtra("edut",-10);
            DBDAO db=AppDatabase.getAppDatabase(this).DBDao();
            Plan p=db.getPlan(id);
            path=p.getImage();
            ((EditText)findViewById(R.id.txtPlName)).setText(p.getName());
            ((EditText)findViewById(R.id.txtDescr)).setText(p.getDescription());
            if(!Objects.equals(path, "") &&path!=null){
                ((ImageView)findViewById(R.id.imgPlan)).setImageURI(Uri.parse(path));
            }
            List<Exercise> exercises=db.getExercises(id);
            LinearLayout list=findViewById(R.id.exerciseList);
            for (Exercise e:exercises) {
                LayoutInflater inf=LayoutInflater.from(this);
                ConstraintLayout con=(ConstraintLayout) inf.inflate(R.layout.edit_exercise, null, false);
                ((EditText)con.findViewById(R.id.txtExName)).setText(e.getName());
                ((EditText)con.findViewById(R.id.txtExReps)).setText(e.getRepetitions());
                ((EditText)con.findViewById(R.id.txtExSets)).setText(e.getSets());
                ((EditText)con.findViewById(R.id.txtExWeights)).setText(Float.toString(e.getWeight()));
                String expath=e.getImage();
                if(!Objects.equals(expath, "")&&expath!=null) {
                    ((ImageView) con.findViewById(R.id.imgEx)).setImageURI(Uri.parse(expath));
                }else{
                    expath="";
                }
                list.addView(con);
                this.exercises.add(con);
                exPaths.add(expath);
            }

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_PL_IMG) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                path = uri.toString();
                ((ImageView) findViewById(R.id.imgPlan)).setImageURI(uri);
            }
        }else{
            if(resultCode==RESULT_OK){
                Uri uri=data.getData();
                exPaths.set(requestCode-REQ_EX_IMG,uri.toString());
                loadExImages();
            }
        }
    }

    public void editPlanImg(View view){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,REQ_PL_IMG);

    }

    public void addPressed(View view){

        LinearLayout list= findViewById(R.id.exerciseList);
        LayoutInflater inf=LayoutInflater.from(this);
        ConstraintLayout con=(ConstraintLayout) inf.inflate(R.layout.edit_exercise, null, false);

        list.addView(con);
        exercises.add(con);
        exPaths.add("");

    }
    public void savePressed(View view){
        AppDatabase db = AppDatabase.getAppDatabase(this);
        DBDAO dao = db.DBDao();
        if(id==null) {
            Plan p = new Plan();
            p.setName(((EditText) findViewById(R.id.txtPlName)).getText().toString());
            p.setDescription(((EditText) findViewById(R.id.txtDescr)).getText().toString());
            p.setImage(path);
            p.setUserFK(1);
            long pid = dao.insertPlan(p)[0];

            for (int i = 0; i < exercises.size(); i++) {
                String name = ((EditText) exercises.get(i).getChildAt(0)).getText().toString();
                int sets = Integer.parseInt(((EditText) exercises.get(i).getChildAt(1)).getText().toString());
                int reps = Integer.parseInt(((EditText) exercises.get(i).getChildAt(2)).getText().toString());
                float weight = Float.parseFloat(((EditText) exercises.get(i).getChildAt(3)).getText().toString());
                Exercise e = new Exercise();
                e.setName(name);
                e.setSets(sets);
                e.setRepetitions(reps);
                e.setWeight(weight);
                e.setPlanFK(pid);
                e.setImage(exPaths.get(i));
                dao.insertExercise(e);

            }
        }else{
            Plan p=dao.getPlan(id);
            p.setImage(path);
            p.setName(((EditText)findViewById(R.id.txtPlName)).getText().toString());
            p.setDescription(((EditText)findViewById(R.id.txtDescr)).getText().toString());
            dao.updatePlans(p);
            dao.deleteExercises(p.getId());
            for (int i = 0; i < exercises.size(); i++) {
                String name = ((EditText) exercises.get(i).getChildAt(0)).getText().toString();
                int sets = Integer.parseInt(((EditText) exercises.get(i).getChildAt(1)).getText().toString());
                int reps = Integer.parseInt(((EditText) exercises.get(i).getChildAt(2)).getText().toString());
                float weight = Float.parseFloat(((EditText) exercises.get(i).getChildAt(3)).getText().toString());
                Exercise e = new Exercise();
                e.setName(name);
                e.setSets(sets);
                e.setRepetitions(reps);
                e.setWeight(weight);
                e.setPlanFK(id);
                e.setImage(exPaths.get(i));
                dao.insertExercise(e);
            }
        }
        finish();
    }



    public void loadExImages(){
        for(int i=0;i<exercises.size();i++){
            ConstraintLayout cl=exercises.get(i);
            String path=exPaths.get(i);
            ((ImageView)cl.getChildAt(4)).setImageURI(Uri.parse(path));
        }
    }

    public void removePressed(View view){
        exPaths.remove(exercises.indexOf(view.getParent()));
        exercises.remove(view.getParent());
        ((ViewManager)view.getParent().getParent()).removeView((View)view.getParent());
    }

    public void editExImage(View view){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,REQ_EX_IMG+exercises.indexOf(view.getParent()));
    }
}
