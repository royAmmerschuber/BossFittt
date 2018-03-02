package com.example.roy.bossfit;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import io.reactivex.annotations.Nullable;

/**
 * Created by jerom.rajan on 01.03.2018.
 */

public class Tab2Profile extends android.support.v4.app.Fragment
{
    private static final String TAG = "Tab2Profile";

    private Button btnTEST;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.tab2_profile_panel,container,false);
        btnTEST = (Button) view.findViewById(R.id.btnTEST2);

        btnTEST.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 2",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
