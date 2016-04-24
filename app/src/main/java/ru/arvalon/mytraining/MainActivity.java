package ru.arvalon.mytraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mytrainers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mytrainers=(Button)findViewById(R.id.mytrainers);

        mytrainers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMyTrainersActivity();
            }
        });
    }

    public void startMyTrainersActivity(){
        Log.d("norm","Button mytrainers is pressed");
        Intent equipmentsintent=new Intent(this,Equipments.class);
        startActivity(equipmentsintent);
    }
}
