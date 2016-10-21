package ru.arvalon.mytraining;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ru.arvalon.mytraining.GymDbActivities.AvaliableEquipmentsActivities.EquipmentsActivity;
import ru.arvalon.mytraining.GymDbActivities.GymDB;
import ru.arvalon.mytraining.MyProgrammActivities.MyProgrammActivity;
import ru.arvalon.mytraining.WorkoutActivities.WorkoutActivity;
import ru.arvalon.mytraining.db.DatabaseAccess;
import ru.arvalon.mytraining.dialogs.DialogActivationDB;

public class MainActivity extends AppCompatActivity implements DialogActivationDB.DialogHost {

    private Button mytrainers;
    private Button myprogramm;
    private Button workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        super.closeContextMenu();

        mytrainers=(Button)findViewById(R.id.mytrainers);
        workout=(Button)findViewById(R.id.workout);
        myprogramm=(Button)findViewById(R.id.myprogramm);

        mytrainers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Dialog
                startMyTrainersActivity();

            }
        });
        myprogramm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMyProgrammActivity();
            }
        });
        workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workout();
            }
        });
    }


    private void startMyProgrammActivity() {
        Log.d("norm","Button MyProgrammActivity is pressed");
        Intent myprogrammintent=new Intent(this,MyProgrammActivity.class);
        startActivity(myprogrammintent);
    }

    private void workout() {
        Log.d("norm","Button WorkoutActivity is pressed");
        Intent workoutintent=new Intent(this,WorkoutActivity.class);
        startActivity(workoutintent);
    }


    public void startMyTrainersActivity(){
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(this);
        databaseAccess.open();
        int activationDB=databaseAccess.getActivationDB(); //почему читается 0???
        databaseAccess.close();
        if (activationDB!=1){
            DialogActivationDB dialog = new DialogActivationDB();
            dialog.show(getSupportFragmentManager(),"DialogActivationDB");
        }else DialogActivationDBnegative();
    }

    @Override
    public void DialogActivationDBpositive() {
        Intent equipmentsintent=new Intent(this,EquipmentsActivity.class);
        startActivity(equipmentsintent);
    }

    @Override
    public void DialogActivationDBnegative() {
        Intent equipmentsintent=new Intent(this,GymDB.class);
        startActivity(equipmentsintent);
    }
}