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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gymdb);

        findViewById(R.id.equipments).setOnClickListener(v -> startEquipmentsActivity());
        findViewById(R.id.exercices).setOnClickListener(v -> startExercicesActivity());
        findViewById(R.id.addEquipments).setOnClickListener(v -> startAddEquipmentActivity());
        findViewById(R.id.addExercises).setOnClickListener(v -> startAddExerciseActivity());

    }

    private void startAddExerciseActivity() {
        startActivity(new Intent(this,AddExerciseActivity.class));
    }

    private void startAddEquipmentActivity() {
        startActivity(new Intent(this,AddEquipmentActivity.class));
    }

    private void startEquipmentsActivity() {
        startActivity(new Intent(this,EquipmentsActivity.class));
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
            startActivity(new Intent(this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}