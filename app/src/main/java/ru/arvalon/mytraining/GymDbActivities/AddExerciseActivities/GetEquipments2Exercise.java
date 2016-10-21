package ru.arvalon.mytraining.GymDbActivities.AddExerciseActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ru.arvalon.mytraining.Model.Equipment;
import ru.arvalon.mytraining.R;
import ru.arvalon.mytraining.converters.EquipmentsConverter;
import ru.arvalon.mytraining.db.DatabaseAccess;

public class GetEquipments2Exercise extends AppCompatActivity {

    List<Equipment> equipments=new ArrayList<Equipment>();

    private ArrayList<Integer> PickedEquipmentsID=new ArrayList<>();

    AddExerciseAdapterToEquipments adapter;

    private static final String CHECKED_EQUIPMENTS="CHECKED_EQUIPMENTS";
    private static final String CHECKED_EQUIPMENTS_RESULT="CHECKED_EQUIPMENTS_RESULT";

    private Button getEquipments;

    @Override
    protected void onResume() {
        super.onResume();

        //ТЕПЕРЬ ОН СТАЛ ТУТ



        //ЁБАНЫЙ КОД ЗАКОНЧИЛСЯ
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_equipments2_exercise);


        Intent intent = getIntent();
        if(intent.hasExtra("PICKED_EQUIPMENTS_ID")){
            PickedEquipmentsID=intent.getIntegerArrayListExtra("PICKED_EQUIPMENTS_ID");
            Log.d("happy","ПОЛУЧИЛИ СПИСОК ОТМЕЧЕННЫХ ID");
            for(Integer i : PickedEquipmentsID){
                Log.d("happy",i.toString());
            }
            //equipments=EquipmentsConverter.IdToEquipments(PickedEquipmentsID,this);
        }



        getEquipments=(Button)findViewById(R.id.get_equipments);
        getEquipments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(adapter.getCheckedEquipments().size()!=0){

                    intent.putIntegerArrayListExtra(CHECKED_EQUIPMENTS_RESULT, EquipmentsConverter.equipmentsToId(adapter.getCheckedEquipments()));
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        fillEquipments();
        adapter=new AddExerciseAdapterToEquipments(this,equipments,PickedEquipmentsID);

        ListView listView=(ListView)findViewById(R.id.equipments_list);
        listView.setAdapter(adapter);


    }

    private void fillEquipments() {
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(this);
        databaseAccess.open();
        equipments=databaseAccess.getFullEquipmentForFragment();
        databaseAccess.close();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putParcelableArrayList(CHECKED_EQUIPMENTS, adapter.getCheckedEquipments());
        if (adapter.getCheckedEquipments().size()!=0){
            Log.d("happy","onSaveInstanceState - ВЫТАЩИЛИ ИЗ АДАПТЕРА:");
            //ArrayList<Integer> checkedList=new ArrayList<>(); //убрать
            PickedEquipmentsID.clear();
            for(Equipment e:adapter.getCheckedEquipments()){
                Log.d("happy",e.getName()+",сохранили в  PickedEquipmentsID цыфорку: "+e.getId());
                //checkedList.add(e.getId()); //убрать
                PickedEquipmentsID.add(e.getId());
            }
            //outState.putIntegerArrayList(CHECKED_EQUIPMENTS,checkedList);//убрать
            outState.putIntegerArrayList(CHECKED_EQUIPMENTS,PickedEquipmentsID);
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //equipments=(ArrayList)savedInstanceState.getParcelableArrayList(CHECKED_EQUIPMENTS);
        Log.d("happy","ВЫТАЩИЛИ ИЗ onRestoreInstanceState:");
        //for(Equipment e:savedInstanceState.getParcelableArrayList(CHECKED_EQUIPMENTS)) Log.d("happy",e.getRuname());
        /*
        if (savedInstanceState!=null){
            if(savedInstanceState.getIntegerArrayList(CHECKED_EQUIPMENTS)!=null){
                for (Integer i:savedInstanceState.getIntegerArrayList(CHECKED_EQUIPMENTS)){
                    //Log.d("happy",i.toString());

                    for(Equipment e: equipments){
                        if(e.getId()==i) e.setChecked(true);
                        PickedEquipmentsID.add(i);
                    }
                }
            }

        }
        */
    }
}
