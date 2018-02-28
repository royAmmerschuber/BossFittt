package com.example.roy.bossfit;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class EditPanel extends AppCompatActivity {

    List<ConstraintLayout> exercises;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_panel);
        exercises=new ArrayList<>();

    }

    public void addPressed(View view){
        LinearLayout list=(LinearLayout) findViewById(R.id.exerciseList);

        ConstraintLayout box=new ConstraintLayout(this);
        ConstraintLayout.LayoutParams p=new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);
        box.setLayoutParams(p);
        ConstraintSet c= new ConstraintSet();
        c.clone(box);

        EditText txtName=new EditText(this);
        p=new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        p.setMargins(0,8,0,8);
        txtName.setLayoutParams(p);
        c.connect(txtName.getId(),ConstraintSet.BOTTOM, box.getId(),ConstraintSet.BOTTOM);
        c.connect(txtName.getId(),ConstraintSet.TOP,box.getId(),ConstraintSet.TOP);
        txtName.setEms(10);
        txtName.setText("exercise");
        box.addView(txtName);

        Button btn=new Button(this);
        p=new ConstraintLayout.LayoutParams(32,32);
        p.setMargins(0,8,8,8);
        p.setMarginEnd(8);
        btn.setLayoutParams(p);
        c.connect(btn.getId(),ConstraintSet.BOTTOM,box.getId(),ConstraintSet.BOTTOM);
        c.connect(btn.getId(),ConstraintSet.END,box.getId(),ConstraintSet.END);
        c.connect(btn.getId(),ConstraintSet.TOP,box.getId(),ConstraintSet.TOP);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removePressed(view);
            }
        });
        btn.setBackground(getDrawable(android.R.drawable.ic_delete));
        EditText txtTime=new EditText(this);
        p=new ConstraintLayout.LayoutParams(
                0,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        p.setMargins(8,8,8,8);
        p.setMarginEnd(8);
        p.setMarginStart(8);
        txtTime.setLayoutParams(p);
        c.connect(txtTime.getId(),ConstraintSet.BOTTOM,box.getId(),ConstraintSet.BOTTOM);
        c.connect(txtTime.getId(),ConstraintSet.END,btn.getId(),ConstraintSet.START);
        box.setConstraintSet(c);
        list.addView(box);
        exercises.add(box);
        /*
        * <android.support.constraint.ConstraintLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content">
                    <Button
                        android:id="@+id/button2"
                        android:layout_width="32dp"
                        android:layout_height="32dp"
                        android:layout_marginBottom="8dp"
                        android:layout_marginEnd="8dp"
                        android:layout_marginTop="8dp"
                        android:background="@android:drawable/ic_delete"
                        android:onClick="removePressed"
                        app:layout_constraintBottom_toBottomOf="parent"
                        app:layout_constraintEnd_toEndOf="parent"
                        app:layout_constraintTop_toTopOf="parent" />

                    <EditText
                        android:id="@+id/editText5"
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_marginBottom="8dp"
                        android:layout_marginEnd="8dp"
                        android:layout_marginStart="8dp"
                        android:layout_marginTop="8dp"
                        android:ems="10"
                        android:inputType="time"
                        android:text="1:00"
                        app:layout_constraintBottom_toBottomOf="parent"
                        app:layout_constraintEnd_toStartOf="@+id/button2"
                        app:layout_constraintStart_toEndOf="@+id/editText2"
                        app:layout_constraintTop_toTopOf="parent" />

                </android.support.constraint.ConstraintLayout>
        * */
    }
    public void savePressed(View view){

    }
    public void removePressed(View view){

    }
}
