package ru.arvalon.mytraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ru.arvalon.mytraining.GymDbActivities.AvaliableEquipmentsActivities.EquipmentsActivity;
import ru.arvalon.mytraining.GymDbActivities.GymDB;
import ru.arvalon.mytraining.MyProgrammActivities.MyProgrammActivity;
import ru.arvalon.mytraining.WorkoutActivities.WorkoutActivity;
import ru.arvalon.mytraining.db.DatabaseAccess;
import ru.arvalon.mytraining.dialogs.DialogActivationDB;

public class MainActivity extends AppCompatActivity implements DialogActivationDB.DialogHost {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.mytrainers).setOnClickListener(v->{startMyTrainersActivity();});
        findViewById(R.id.workout).setOnClickListener(v->{startMyProgrammActivity();});
        findViewById(R.id.myprogramm).setOnClickListener(v->{workout();});
    }


    private void startMyProgrammActivity() {
        Log.d(App.TAG,"Button MyProgrammActivity is pressed");
        Intent myprogrammintent=new Intent(this,MyProgrammActivity.class);
        startActivity(myprogrammintent);
    }

    private void workout() {
        Log.d(App.TAG,"Button WorkoutActivity is pressed");
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