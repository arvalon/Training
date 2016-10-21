package ru.arvalon.mytraining.GymDbActivities.AddExerciseActivities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import ru.arvalon.mytraining.GymDbActivities.Description;
import ru.arvalon.mytraining.R;

public class AddExerciseActivity extends AppCompatActivity {

    private static final int TAKE_PHOTO = 1;
    private static final int TAKE_DESCRIPTION=2;
    private static final int TAKE_EQUIPMENTS=3;
    private static final int TAKE_MUSCLES=4;
    private static final String EXERCISE_IMAGE = "EXERCISE_IMAGE";
    private static final String DESCRIPTION="DESCRIPTION";

    private Button addEquipment;


    private ArrayList<Integer> PickedEquipmentsID=new ArrayList<>();
    private static final String PICKED_EQUIPMENTS_ID="PICKED_EQUIPMENTS_ID";
    private Button addPhoto;
    private Bitmap bp;
    private ImageView equipmentImage;
    private Button addDescription;
    private String description;
    private Button addMuscles;


    @Override
    protected void onResume() {
        super.onResume();
        if (description!=null && description.length()!=0){
            addDescription.setText(R.string.editDescription);
        }else addDescription.setText(R.string.addDescription);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        equipmentImage=(ImageView)findViewById(R.id.equipment_image);

        addEquipment=(Button)findViewById(R.id.add_equipment);
        addEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addequipment();
            }
        });

        addPhoto=(Button)findViewById(R.id.addPhoto);
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPhoto();
            }
        });

        addDescription=(Button)findViewById(R.id.add_description);

        addDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("happy","onClick");
                addDescription();
            }
        });

        addMuscles =(Button)findViewById(R.id.add_muscles);
        addMuscles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMuscles();
            }
        });
    }

    private void addMuscles() {
        Intent i = new Intent(this,GetMuscles2Exercise.class);
        startActivityForResult(i,TAKE_MUSCLES);
    }

    private void addPhoto() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, TAKE_PHOTO);
    }

    private void addDescription() {
        Intent i=new Intent(this,Description.class);
        if (description!=null){
            i.putExtra(DESCRIPTION,description.toString());
            Log.d("happy","Put to EXTRAS: "+description.toString());
        }
        startActivityForResult(i,TAKE_DESCRIPTION);
    }

    private void addequipment() {
        Intent i =new Intent(this,GetEquipments2Exercise.class);
        if (PickedEquipmentsID.size()!=0){
            i.putIntegerArrayListExtra(PICKED_EQUIPMENTS_ID,PickedEquipmentsID);
        }
        startActivityForResult(i,TAKE_EQUIPMENTS);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_EQUIPMENTS) {
            Log.d("happy","requestCode");
            if (resultCode == RESULT_OK) {
                Log.d("happy","resultCode - ПОЛУЧИЛИ МАССИВ ID EQUIPMENTS");
                if (data.getIntegerArrayListExtra("CHECKED_EQUIPMENTS_RESULT")!=null){
                    PickedEquipmentsID.clear();
                    for(Integer i:data.getIntegerArrayListExtra("CHECKED_EQUIPMENTS_RESULT")){
                        PickedEquipmentsID.add(i);
                        Log.d("happy",i.toString());
                    }
                    Log.d("happy","СФОРМИРОВАЛИ КОЛЛЕКЦИЮ ИНТОВ");
                    for (Integer i : PickedEquipmentsID){
                        Log.d("happy",i.toString());
                    }
                }else{
                    Log.d("happy","НЕ выбрано ни одного тренажёра");
                    PickedEquipmentsID.clear();
                }

            }
        }else if(requestCode==TAKE_PHOTO){
            if (resultCode == RESULT_OK) {
                bp = (Bitmap) data.getExtras().get("data");
                equipmentImage.setImageBitmap(bp);
            }
        }else if (requestCode==TAKE_DESCRIPTION){
            if (resultCode==RESULT_OK){
                if (data.getExtras().getString("description")!=null)
                    this.description=data.getExtras().getString("description");
            }
        }
    }

    // ТУТ ДОЛЖНВ БЫТЬ OnSAVE и ONRESUME для PickedEquipmentsID

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("happy","AddExerciseActivity - ТУТ СОХРАНЯЕМ");
        if (PickedEquipmentsID.size()!=0){
            outState.putIntegerArrayList(PICKED_EQUIPMENTS_ID,PickedEquipmentsID);
        }
        outState.putParcelable(EXERCISE_IMAGE,bp);
        outState.putString(DESCRIPTION,description);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("happy","AddExerciseActivity - ТУТ ВОССТАНАВЛИВАЕМ");
        if (savedInstanceState!=null){
            if (savedInstanceState.getIntegerArrayList(PICKED_EQUIPMENTS_ID)!=null){
                for (Integer i:savedInstanceState.getIntegerArrayList(PICKED_EQUIPMENTS_ID)){
                    PickedEquipmentsID.add(i);
                }
            }
        }
        equipmentImage.setImageBitmap((Bitmap)savedInstanceState.getParcelable(EXERCISE_IMAGE));
        bp=(Bitmap)savedInstanceState.getParcelable(EXERCISE_IMAGE);
        description=savedInstanceState.getString(DESCRIPTION);
    }
}
