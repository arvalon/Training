package ru.arvalon.mytraining.gymdbactivities.addequipmentactivities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import ru.arvalon.mytraining.gymdbactivities.Description;
import ru.arvalon.mytraining.gymdbactivities.addexerciseactivities.AddExerciseActivity;
import ru.arvalon.mytraining.MainActivity;
import ru.arvalon.mytraining.R;
import ru.arvalon.mytraining.converters.ImageConverter;
import ru.arvalon.mytraining.db.DatabaseAccess;
import ru.arvalon.mytraining.dialogs.DialogAddEquipmentEnd;


/**
 * Created by arvalon on 02.07.2016.
 */
public class AddEquipmentActivity extends AppCompatActivity implements DialogAddEquipmentEnd.DialogHost {

    private static final int TAKE_PHOTO = 1;
    private static final int TAKE_DESCRIPTION=2;
    private static final String EQUIPMENT_IMAGE = "EQUIPMENT_IMAGE";
    private static final String DESCRIPTION="DESCRIPTION";

    private int counterWeight;
    private int measure;

    private Button addPhoto;
    private ImageView equipmentImage;
    private Bitmap bp;
    private EditText inputEquipmentsName;
    private Button addDescription;
    private String description;
    private Button addEquipment;
    private Switch counterWeightSwitch;
    private Switch measureSwitch;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("happy","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addequipment);

        addPhoto=(Button)findViewById(R.id.addPhoto);
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("happy","addPhotoButton");
                addPhoto();

            }
        });

        inputEquipmentsName=(EditText)findViewById(R.id.inputEquipmentsName);
        equipmentImage=(ImageView)findViewById(R.id.equipment_image);

        addDescription=(Button)findViewById(R.id.add_description);

        addDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDescription();
            }
        });

        counterWeightSwitch=(Switch)findViewById(R.id.counterweight_switch);
        counterWeightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    counterWeight=1;
                }else {
                    counterWeight=0;
                }
            }
        });

        measureSwitch=(Switch)findViewById(R.id.unit_of_measure_switch);
        measureSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    measure=1;
                    Log.d("happy","Pfounds is added");
                }else {
                    measure=0;
                }
            }
        });

        addEquipment=(Button)findViewById(R.id.add_equipment);
        addEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equipmentAdd();
            }
        });

    }

    private void equipmentAdd() {
        if (inputEquipmentsName.getText().length()!=0){
            byte[] bOutPutArray=ImageConverter.convertImage(bp);
            DatabaseAccess databaseAccess=DatabaseAccess.getInstance(this);
            databaseAccess.open();
            databaseAccess.addEquipment(inputEquipmentsName.getText().toString(),
                    description,
                    counterWeight,
                    measure,
                    bOutPutArray);
            databaseAccess.close();
            DialogAddEquipmentEnd dialog=new DialogAddEquipmentEnd();
            dialog.show(getSupportFragmentManager(),"ЗАЧЕМ ЭТА СТРОКА");
        }else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    R.string.noname_equipment, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //if (description!=null ){
            if (description!=null && description.length()!=0){
            Log.d("happy","String description is not null: ");
            addDescription.setText(R.string.editDescription);
        }else addDescription.setText(R.string.addDescription);
    }

    private void addDescription() {
        Intent i=new Intent(this,Description.class);
        if (description!=null){
            i.putExtra(DESCRIPTION,description.toString());
            Log.d("happy","Put to EXTRAS: "+description.toString());
        }
        startActivityForResult(i,TAKE_DESCRIPTION);
    }

    private void addPhoto() {
        Log.d("happy","addPhoto");
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, TAKE_PHOTO);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("happy","onActivityResult");
        if (requestCode == TAKE_PHOTO) {
            Log.d("happy","requestCode");
            if (resultCode == RESULT_OK) {
                Log.d("happy","resultCode");
                //Возможен NullPointerException в последующие 2 строчки
                //На SmartBoos SE2 не проходить проверка и последующего if
                bp = (Bitmap) data.getExtras().get("data");
                equipmentImage.setImageBitmap(bp);

                if (data != null && data.getData() != null) {
                    Log.d("happy","Photo is getted");
                    //bp = (Bitmap) data.getExtras().get("data");
                    //equipmentImage.setImageBitmap(bp);
                }
            }
        }else
        if (requestCode == TAKE_DESCRIPTION) {
            Log.d("happy","1");
            if (resultCode == RESULT_OK) {
                Log.d("happy","Description is getted: "+data.getExtras().getString("description"));
                if (data.getExtras().getString("description")!=null)
                this.description=data.getExtras().getString("description");
                //addDescription.setText("ОТРЕДАЧИТЬ");
            }
            else{
                Log.d("happy","NE NORM!");
                this.description=null;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("happy","ONSAVEINSTANTSTATE");
        outState.putParcelable(EQUIPMENT_IMAGE,bp);
        outState.putString(DESCRIPTION,description);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("happy","ONRESTOREINSTANTSTATE");
        equipmentImage.setImageBitmap((Bitmap)savedInstanceState.getParcelable(EQUIPMENT_IMAGE));
        bp=(Bitmap)savedInstanceState.getParcelable(EQUIPMENT_IMAGE);
        description=savedInstanceState.getString(DESCRIPTION);
    }

    // Neutral - MainMenu
    // Negative - Add another equipment
    // Positive - Add Exercises activity for this equipments

    @Override
    public void DialogAddEquipmentEndPositive() {
        Toast.makeText(getApplicationContext(), "Positive", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,AddExerciseActivity.class));
    }

    @Override
    public void DialogAddEquipmentEndNegative() {
        Toast.makeText(getApplicationContext(), "Negative", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,AddEquipmentActivity.class));
    }

    @Override
    public void DialogAddEquipmentEndNeutral() {
        Toast.makeText(getApplicationContext(), "Neutral", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,MainActivity.class));
    }
}
