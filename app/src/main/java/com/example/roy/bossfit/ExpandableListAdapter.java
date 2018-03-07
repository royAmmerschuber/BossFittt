package com.example.roy.bossfit;

import android.arch.persistence.room.Database;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.roy.bossfit.Database.AppDatabase;
import com.example.roy.bossfit.Database.DBDAO;
import com.example.roy.bossfit.Database.Exercise;
import com.example.roy.bossfit.Database.Plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by jerom.rajan on 02.03.2018.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Plan> listDataHeader;
    private HashMap<Plan,List<Plan>> listHashMap;

    public ExpandableListAdapter(Context context, List<Plan> listDataHeader) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        listHashMap=new HashMap<>();
        for (Plan p:listDataHeader) {
            List<Plan> l=new ArrayList<>();
            l.add(p);
            listHashMap.put(p,l);
        }
    }
    public ExpandableListAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listDataHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listHashMap.get(listDataHeader.get(i)).get(i1); // i = Group Item , i1 = ChildItem
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle = ((Plan)getGroup(i)).getName();
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_group,null);
        }
        TextView lblListHeader = (TextView)view.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final Plan p = (Plan)getChild(i,i1);
        LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null)
        {
            view = inflater.inflate(R.layout.list_item,null);
        }
        TextView txtDescription=view.findViewById(R.id.txtDescr);
        ImageView imgPlan=view.findViewById(R.id.imgPlan);
        LinearLayout lstExercises=view.findViewById(R.id.lstExercises);

        txtDescription.setText(p.getDescription());
        if(!Objects.equals(p.getImage(), "")&&p.getImage()!=null) {
            imgPlan.setImageURI(Uri.parse(p.getImage()));
        }
        view.setTag(p.getId());

        DBDAO dao=AppDatabase.getAppDatabase(context).DBDao();
        List<Exercise> exercises= dao.getExercises(p.getId());
        lstExercises.removeAllViews();
        for(Exercise e:exercises){

            View c=inflater.inflate(R.layout.plan_list_exercise,lstExercises,true);
            TextView txtExName=c.findViewById(R.id.txtName);
            TextView txtSets=c.findViewById(R.id.txtSets);
            TextView txtRepetitions=c.findViewById(R.id.txtRepetitions);
            TextView txtWeight=c.findViewById(R.id.txtWeight);
            ImageView imgEx=c.findViewById(R.id.imgExercise);
            txtExName.setText(e.getName());
            txtSets.setText(Integer.toString(e.getSets()));
            txtRepetitions.setText(Integer.toString(e.getRepetitions()));
            txtWeight.setText(Float.toString(e.getWeight()));
            if(!Objects.equals(e.getImage(), "")&&e.getImage()!=null) {
                imgEx.setImageURI(Uri.parse(e.getImage()));

            }
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public void setData(List<Plan> listDataHeader,HashMap<Plan,List<Plan>> listHashMap) {
        this.listDataHeader = listDataHeader;
        this.listHashMap=listHashMap;
        notifyDataSetChanged();
    }

}
