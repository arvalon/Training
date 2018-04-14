package ru.arvalon.mytraining.gymdbactivities.addexerciseactivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.List;
import java.util.Map;

import ru.arvalon.mytraining.model.Muscle;
import ru.arvalon.mytraining.model.MuscleGroup;
import ru.arvalon.mytraining.R;
import ru.arvalon.mytraining.db.DatabaseAccess;

public class GetMuscles2Exercise extends AppCompatActivity {

    private ExpandableListView expListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_muscles2_exercise);

        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<MuscleGroup> muscleGroupList=databaseAccess.getMuscleGroups();
        databaseAccess.close();

        databaseAccess.open();
        List<Muscle> musclesList=databaseAccess.getMuscles();
        databaseAccess.close();

        databaseAccess.open();
        Map<MuscleGroup,List<Muscle>> musclesByGroups=databaseAccess.getMusclesByGroups(muscleGroupList,musclesList);
        databaseAccess.close();
        AddMusclesToExerciseAdapter adapter=new AddMusclesToExerciseAdapter(this,getSupportFragmentManager(),musclesByGroups);
        expListView=(ExpandableListView)findViewById(R.id.muscles_list);
        expListView.setAdapter(adapter);


    }
}
