package ru.arvalon.mytraining.GymDbActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.arvalon.mytraining.R;

public class Description extends AppCompatActivity {

    private Button descriptionAdd;
    private EditText description;
    private Button descriptionErase;
    private final String DESCRIPTION="description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        description=(EditText)findViewById(R.id.description);

        Intent intent = getIntent();
        if(intent.hasExtra("DESCRIPTION")){
            description.setText(intent.getExtras().getString("DESCRIPTION"));
        }
        // TODO: 04.07.2016 Залочить эту кнопку если поле пустое
        descriptionAdd=(Button)findViewById(R.id.description_add);
        descriptionAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (description.getText().toString()!=null){
                    Log.d("happy","ЭТОГО НЕ МОЖЕТ БЫТЬ");
                    intent.putExtra("description", description.getText().toString());
                }
                setResult(RESULT_OK, intent);
                Log.d("happy","Description is put to Intent: "+description.getText().toString());
                finish();
            }
        });

        descriptionErase=(Button)findViewById(R.id.description_erase);
        descriptionErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //intent.putExtra("description", description.getText().toString());
                setResult(RESULT_CANCELED, intent);
                Log.d("happy","RESULT_CANCELED");
                finish();
            }
        });
    }

}
