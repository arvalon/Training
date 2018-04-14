package ru.arvalon.mytraining.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ru.arvalon.mytraining.model.Equipment;
import ru.arvalon.mytraining.model.Muscle;
import ru.arvalon.mytraining.model.MuscleGroup;

/**
 * Created by arvalon on 07.06.2016.
 */
public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;


    //muscle_groups table

    private static final String MUSCLE_GROUPS_TABLE = "muscle_groups";

    private static final String MUSCLE_GROUPS_ID="_id";
    private static String MUSCLE_GROUPS_GROUP_NAME="group_name";
    private static final String MUSCLE_GROUPS_POWER="power";
    private static final String MUSCLE_GROUPS_IMG="img";
    private static final String MUSCLE_GROUPS_DESCRIPTION="description";

    //equipment table
    private static final String EQUIPMENTS_TABLE="equipments";

    private static final String EQUIPMENTS_ID="_id";
    private static String EQUIPMENTS_NAME="equipment_name";
    private static final String EQUIPMENTS_IMG="exercise_img";
    private static final String EQUIPMENTS_DESCRIPTION="description";
    private static final String EQUIPMENTS_COUNTERWEIGHT="counterweight";
    private static final String EQUIPMENTS_MEASURE="measure";
    private static final String EQUIPMENTS_DATE_BANNED="date_banned";
    private static final String EQUIPMENTS_AVALIABLE="avaliable";

    //Muscles tables

    private static final String MUSCLES_TABLE="muscles";

    private static final String MUSCLE_ID="_id";
    private static final String MUSCLE_GROUP="groups";
    private static String MUSCLE_NAME="muscle_name";
    private static final String MUSCLE_IMG="muscle_img";
    private static final String MUSCLE_DESCRIPTION="description";

    //Localization
    private static final String RU="ru";


    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }


    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }


    public void open() {
        this.database = openHelper.getWritableDatabase();
    }


    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    private String GetLocalization(String inputColumn){
        if (Locale.getDefault().getLanguage().equals(RU)) return inputColumn+"_"+RU;
        return inputColumn;
    }

    private String SetLocalizationRU(String inputColumn){return inputColumn+"_"+RU;}


    public List<String> getExercises() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT exercise_runame FROM exercises", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getEquipments() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT equipment_runame FROM equipments", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<Equipment> getFullEquipmentForFragment(){
        Cursor cursor=database.query(EQUIPMENTS_TABLE,
                new String[]{EQUIPMENTS_ID,
                        GetLocalization(EQUIPMENTS_NAME),
                EQUIPMENTS_IMG,
                EQUIPMENTS_AVALIABLE,
                EQUIPMENTS_MEASURE},
                null,null,null,null,null);
        List<Equipment> equipmentsList=new ArrayList<>();
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                equipmentsList.add(new Equipment(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getBlob(2),
                        cursor.getInt(3),
                        cursor.getInt(4)));
                cursor.moveToNext();
            }
        } else return null;
        return equipmentsList;
    }

    public int getActivationDB(){
        Cursor cursor=database.rawQuery("SELECT value FROM sys WHERE key='activationdb'",null);
        cursor.moveToFirst();
        return Integer.parseInt(cursor.getString(0));
    }
    public void setActivationDB(){
        database.execSQL("UPDATE sys SET value = 1 WHERE key = 'activationdb'");
    }

    public void ChangeEqupmentsAvaliable(int id,int param){
        database.execSQL("UPDATE [equipments] SET [avaliable]="+param+" where [_id]="+id);
    }

    public void ChangeEqupmentsMeasure(int id, int param) {
        database.execSQL("UPDATE [equipments] SET [measure]="+param+" where [_id]="+id);
    }

    public void addEquipment(String equipment_name,String description,int counterweight, int measure,byte[] exercise_img) {
        ContentValues cv=new ContentValues();
        cv.put(EQUIPMENTS_NAME,equipment_name);
        cv.put(SetLocalizationRU(EQUIPMENTS_NAME),equipment_name);
        cv.put("description",description);
        cv.put("counterweight",counterweight);
        cv.put("measure",measure);
        cv.put("exercise_img",exercise_img);
        database.insert("equipments",null,cv);
    }

    public List<MuscleGroup> getMuscleGroups() {
        Cursor cursor=database.query(MUSCLE_GROUPS_TABLE,
                new String[]{MUSCLE_GROUPS_ID,
                        GetLocalization(MUSCLE_GROUPS_GROUP_NAME),
                        MUSCLE_GROUPS_POWER,
                        MUSCLE_GROUPS_IMG,
                        MUSCLE_GROUPS_DESCRIPTION},
                null, null, null, null, null);
        List<MuscleGroup> muscleGroupsList=new ArrayList<>();
        Log.d("happy","cursor.getCount(): "+String.valueOf(cursor.getCount())+" muscle groups");
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                muscleGroupsList.add(new MuscleGroup(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getBlob(3),
                        cursor.getString(4)));
                cursor.moveToNext();
            }
        }else return null;
        return muscleGroupsList;
    }

    public List<Muscle> getMuscles(){
        Cursor cursor=database.query(MUSCLES_TABLE,
                new String[]{MUSCLE_ID,
                        MUSCLE_GROUP,
                        GetLocalization(MUSCLE_NAME),
                        MUSCLE_IMG,
                        MUSCLE_DESCRIPTION},
                null,null,null,null,null);
        List<Muscle>musclesList=new ArrayList<>();
        Log.d("happy","cursor.getCount(): "+String.valueOf(cursor.getCount())+" muscles");
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                musclesList.add(new Muscle(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getBlob(3),
                        cursor.getString(4)
                ));
                cursor.moveToNext();
            }
        }else return null;
        return musclesList;
    }

    public Map<MuscleGroup,List<Muscle>> getMusclesByGroups(List<MuscleGroup> muscleGroups,List<Muscle> muscles){
        Map<MuscleGroup,List<Muscle>> musclesByGroups=new HashMap<>();
        for (MuscleGroup mg: muscleGroups) {
            List<Muscle> muscleTemp=new ArrayList<>();
            for (Muscle m: muscles){
                if (m.getGroup()==mg.getId()){
                    muscleTemp.add(m);
                    //Log.d("happy","Положили "+m.getMuscle_name()+" к "+mg.getName());
                }
            }
            musclesByGroups.put(mg,muscleTemp);
        }
        return musclesByGroups;
    }
}
