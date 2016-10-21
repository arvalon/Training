package ru.arvalon.mytraining.GymDbActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import ru.arvalon.mytraining.GymDbActivities.AddEquipmentActivities.AddEquipmentActivity;
import ru.arvalon.mytraining.GymDbActivities.AddExerciseActivities.AddExerciseActivity;
import ru.arvalon.mytraining.GymDbActivities.AvaliableEquipmentsActivities.EquipmentsActivity;
import ru.arvalon.mytraining.GymDbActivities.AvaliableExercisesActivities.AvaliableExercicesActivities;
import ru.arvalon.mytraining.MainActivity;
import ru.arvalon.mytraining.R;

/**
 * Created by arvalon on 23.04.2016.
 */
public class GymDB extends AppCompatActivity {

    private Button equipments;
    private Button exercises;
    private Button addequipment;
    private Button addExercise;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gymdb);

        equipments=(Button)findViewById(R.id.equipments);
        exercises=(Button)findViewById(R.id.exercices);
        addequipment=(Button)findViewById(R.id.addEquipments);
        addExercise=(Button)findViewById(R.id.addExercises);

        equipments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEquipmentsActivity();
            }
        });

        exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startExercicesActivity();
            }
        });

        addequipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddEquipmentActivity();
            }
        });

        addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddExerciseActivity();
            }
        });



    }

    private void startAddExerciseActivity() {
        startActivity(new Intent(this,AddExerciseActivity.class));
    }

    private void startAddEquipmentActivity() { startActivity(new Intent(this,AddEquipmentActivity.class));
    }

    private void startEquipmentsActivity() {
        Intent i=new Intent(this,EquipmentsActivity.class);
        startActivity(i);

    }

    private void startExercicesActivity() {
        startActivity(new Intent(this,AvaliableExercicesActivities.class));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.equipment_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.back){
            Intent mainActivity=new Intent(this,MainActivity.class);
            startActivity(mainActivity);
        }
        return super.onOptionsItemSelected(item);
    }

}