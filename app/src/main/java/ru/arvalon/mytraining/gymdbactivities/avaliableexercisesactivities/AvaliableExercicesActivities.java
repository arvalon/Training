package ru.arvalon.mytraining.gymdbactivities.avaliableexercisesactivities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import ru.arvalon.mytraining.R;
import ru.arvalon.mytraining.db.DatabaseAccess;

/**
 * Created by arvalon on 15.06.2016.
 */
public class AvaliableExercicesActivities extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercices);

        listView=(ListView)findViewById(R.id.listViewExercises);
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> exercices=databaseAccess.getExercises();
        databaseAccess.close();

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,exercices);
        this.listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.equipment_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.back){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
