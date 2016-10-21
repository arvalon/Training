package ru.arvalon.mytraining.GymDbActivities.AddExerciseActivities;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.arvalon.mytraining.Model.Muscle;
import ru.arvalon.mytraining.Model.MuscleGroup;
import ru.arvalon.mytraining.R;
import ru.arvalon.mytraining.dialogs.DialogShowLoadDescription;

/**
 * Created by arvalon on 16.07.2016.
 * Только для копирования прмера кода
 */
public class AddMusclesToExerciseAdapter extends BaseExpandableListAdapter {

    Context ctx;
    FragmentManager fragmentManager;
    private final ArrayList<MuscleGroup> keyList;
    Map<MuscleGroup,List<Muscle>> musclesByGroups=new HashMap<>();

    public AddMusclesToExerciseAdapter(Context context, FragmentManager fm, Map<MuscleGroup,List<Muscle>> inputMap){
        ctx=context;
        fragmentManager=fm;
        musclesByGroups=inputMap;
        keyList = new ArrayList<MuscleGroup>(musclesByGroups.keySet());
    }

    // ok
    @Override
    public int getGroupCount() {
        return musclesByGroups.size();
    }


    /*
    @Override
    public int getChildrenCount(int groupPosition) {
        Log.d("happy","getChildrenCount, groupPosition: "+groupPosition);
        return musclesByGroups.get(new MuscleGroup(groupPosition,null,1,null,null)).size();
    }
    */

    @Override
    public int getChildrenCount(int groupPosition) {
        //Log.d("happy","getChildrenCount, groupPosition: "+groupPosition);
        return musclesByGroups.get(keyList.get(groupPosition)).size();
    }

    //ok
    @Override
    public Object getGroup(int groupPosition) {
        return musclesByGroups.get(new MuscleGroup(groupPosition,null,1,null,null));
    }

    /*
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO: 21.07.2016 error
        return musclesByGroups.get(new MuscleGroup(groupPosition,null,1,null,null)).get(childPosition);
    }
    */

    //ok
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO: 21.07.2016 error
        return musclesByGroups.get(keyList.get(groupPosition)).get(childPosition);
    }

    //ok
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //ok
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition*100+ childPosition;
    }

    //ok
    @Override
    public boolean hasStableIds() {
        return false;
    }

    //ok
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.muscles_groups_list, null);
        }
        //String groupName=keyList.get(groupPosition).getName();

        ((TextView)convertView.findViewById(R.id.muscle_name)).setText(keyList.get(groupPosition).getName());

        if (keyList.get(groupPosition).getImg()!=null){
            ((ImageView)convertView.findViewById(R.id.muscle_groups_image))
                    .setImageBitmap(BitmapFactory
                            .decodeByteArray(keyList.get(groupPosition)
                                    .getImg(),0,keyList.get(groupPosition).getImg().length));

        }else ((ImageView)convertView.findViewById(R.id.muscle_groups_image))
                .setImageResource(R.drawable.no);

        return convertView;
    }

    //ok
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //Log.d("happy","childPosition: "+String.valueOf(childPosition));
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.ctx
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.muscles_list, null);
        }

        ((TextView)convertView.findViewById(R.id.muscle_name)).setText(((Muscle)getChild(groupPosition,childPosition)).getMuscle_name());

        if (((Muscle)getChild(groupPosition,childPosition)).getImage()!=null){
            ((ImageView)convertView.findViewById(R.id.muscle_image))
                    .setImageBitmap(BitmapFactory
                            .decodeByteArray(((Muscle)getChild(groupPosition,childPosition))
                                    .getImage(),0,((Muscle)getChild(groupPosition,childPosition))
                                    .getImage().length));

        }else ((ImageView)convertView.findViewById(R.id.muscle_image))
                .setImageResource(R.drawable.no);


        ImageView loadInfo=(ImageView)convertView.findViewById(R.id.loadInfo);
        loadInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("happy","VOPROS");
                // TODO: 26.07.2016 dialog invocation
                DialogShowLoadDescription dialog = new DialogShowLoadDescription();
                //dialog.show(getSupportFragmentManager(), "FILE_DELETE");
                dialog.show(fragmentManager, "FILE_DELETE");
            }
        });

        CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.muscle_checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO: 28.07.2016 http://startandroid.ru/ru/uroki/vse-uroki-spiskom/113-urok-54-kastomizatsija-spiska-sozdaem-svoj-adapter.html
                ((Muscle) getChild(groupPosition,childPosition)).setChecked(isChecked);

                Log.d("happy","TAG: "+(Integer) buttonView.getTag());
                //Integer tag= (Integer) buttonView.getTag();

                //((Muscle) getChild(groupPosition,childPosition)).setChecked(isChecked);

                Log.d("happy","onCheckedChanged, ID: "+((Muscle) getChild(groupPosition,childPosition)).getID());
                //Log.d("happy","onCheckedChanged, getChildId: "+getChildId(groupPosition,childPosition));
            }
        });

        checkBox.setTag(((Muscle) getChild(groupPosition,childPosition)).getID());
        //checkBox.setTag(getChildId(groupPosition,childPosition));
        Log.d("happy","checkBox.setTag: "+((Muscle) getChild(groupPosition,childPosition)).getID());
        checkBox.setChecked(((Muscle) getChild(groupPosition,childPosition)).getChecked());
        Log.d("happy","checkBox.setChecked: "+((Muscle) getChild(groupPosition,childPosition)).getID());
        return convertView;
    }

    List<Muscle> getCheckedMuscles(){
        List<Muscle> checkedMuscles=new ArrayList<>();
        //todo пройтись по всей HashMap и выдать отмеченные мышцы.
        // http://stackoverflow.com/questions/33301708/how-to-get-checkbox-state-in-expandable-listview-in-android
        //for (Muscle m:musclesByGroups.)
        return null;
    }

    //ok
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
