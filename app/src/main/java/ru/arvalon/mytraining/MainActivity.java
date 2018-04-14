package ru.arvalon.mytraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ru.arvalon.mytraining.gymdbactivities.avaliableequipmentsactivities.EquipmentsActivity;
import ru.arvalon.mytraining.gymdbactivities.GymDB;
import ru.arvalon.mytraining.myprogrammactivities.MyProgrammActivity;
import ru.arvalon.mytraining.workoutactivities.WorkoutActivity;
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
        startActivity(new Intent(this,MyProgrammActivity.class));
    }

    private void workout() {
        Log.d(App.TAG,"Button WorkoutActivity is pressed");
        startActivity(new Intent(this,WorkoutActivity.class));
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
        startActivity(new Intent(this,EquipmentsActivity.class));
    }

    @Override
    public void DialogActivationDBnegative() {
        startActivity(new Intent(this,GymDB.class));
    }
}