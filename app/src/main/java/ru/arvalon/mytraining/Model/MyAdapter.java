package ru.arvalon.mytraining.Model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arvalon on 16.07.2016.
 */
public class MyAdapter extends BaseExpandableListAdapter {

    Context ctx;
    Map<MuscleGroup,List<Muscle>> musclesByGroups=new HashMap<>();

    public MyAdapter(Context context, Map<MuscleGroup,List<Muscle>> inputMap){
        ctx=context;
        musclesByGroups=inputMap;
    }

    @Override
    public int getGroupCount() {return musclesByGroups.size();}

    @Override
    public int getChildrenCount(int groupPosition) {return 0;}

    @Override
    public Object getGroup(int groupPosition) {return null;}

    @Override
    public Object getChild(int groupPosition, int childPosition) {return null;}

    @Override
    public long getGroupId(int groupPosition) {return 0;}

    @Override
    public long getChildId(int groupPosition, int childPosition) {return 0;}

    @Override
    public boolean hasStableIds() {return false;}

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {return false;}
}
