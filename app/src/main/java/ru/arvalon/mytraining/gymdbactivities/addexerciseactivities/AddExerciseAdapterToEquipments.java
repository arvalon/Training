package ru.arvalon.mytraining.gymdbactivities.addexerciseactivities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import ru.arvalon.mytraining.model.Equipment;
import ru.arvalon.mytraining.R;

/**
 * Created by arvalon on 07.07.2016.
 */
public class AddExerciseAdapterToEquipments extends BaseAdapter {

    Context ctx;
    LayoutInflater layoutInflater;
    List<Equipment> equipments;
    private ArrayList<Integer> checkedEquipments=new ArrayList<>();

    AddExerciseAdapterToEquipments(Context ctx,List<Equipment> inputEquipments,ArrayList<Integer> checkedEquipments){
        this.ctx=ctx;
        equipments=inputEquipments;
        this.checkedEquipments=checkedEquipments;
        layoutInflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return equipments.size();
    }

    @Override
    public Object getItem(int position) {
        return equipments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    Equipment getEquipment(int position){
        return ((Equipment)getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.equipment_list, parent, false);
        }

        Equipment equipment=getEquipment(position);

        ((TextView)view.findViewById(R.id.equipment_name)).setText(equipment.getName());
        if (equipment.getImage()!=null){
            ((ImageView)view.findViewById(R.id.equipment_image)).setImageBitmap(BitmapFactory.decodeByteArray(equipment.getImage(),0,equipment.getImage().length));
        }else ((ImageView)view.findViewById(R.id.equipment_image)).setImageResource(R.drawable.no);


        CheckBox checkBox=(CheckBox)view.findViewById(R.id.equipment_checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Toast.makeText(ctx, "Neutral", Toast.LENGTH_SHORT).show();
                getEquipment((Integer) buttonView.getTag()).setChecked(isChecked);
            }
        });
        checkBox.setTag(position);

        if (checkedEquipments.size()!=0){
            for (Integer i:checkedEquipments){
                if (i==equipment.getId()){
                    equipment.setChecked(true);
                    //Log.d("happy","АДАПТЕР. Отметили № "+equipment.getId());
                }
            }
        }

        checkBox.setChecked(equipment.isChecked());

        return view;
    }

    List<Equipment> getCheckedEquipments(){
        List<Equipment> checkedEquipments=new ArrayList<Equipment>();
        for(Equipment e: equipments){
            if (e.isChecked()) checkedEquipments.add(e);
        }
        return checkedEquipments;
    }
}
