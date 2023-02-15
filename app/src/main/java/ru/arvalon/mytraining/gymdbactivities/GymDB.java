package ru.arvalon.mytraining.gymdbactivities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import ru.arvalon.mytraining.gymdbactivities.addequipmentactivities.AddEquipmentActivity;
import ru.arvalon.mytraining.gymdbactivities.addexerciseactivities.AddExerciseActivity;
import ru.arvalon.mytraining.gymdbactivities.avaliableequipmentsactivities.EquipmentsActivity;
import ru.arvalon.mytraining.gymdbactivities.avaliableexercisesactivities.AvaliableExercicesActivities;
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