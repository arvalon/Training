package ru.arvalon.mytraining.converters;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.arvalon.mytraining.Model.Equipment;
import ru.arvalon.mytraining.db.DatabaseAccess;

/**
 * Created by arvalon on 08.07.2016.
 */
public class EquipmentsConverter {

    public static ArrayList<Integer> equipmentsToId(List<Equipment> equipments){
        ArrayList<Integer> arrayList=new ArrayList<Integer>();
        for (Equipment e:equipments) arrayList.add(e.getId());
        return arrayList;
    }

    public static List<Equipment> IdToEquipments(ArrayList<Integer> IDs, Context ctx){
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(ctx);
        databaseAccess.open();
        List<Equipment> equipments=databaseAccess.getFullEquipmentForFragment();
        databaseAccess.close();
        List<Equipment> totalEquipments=new ArrayList<Equipment>();
        for (Integer i:IDs){
            for (Equipment e:equipments){
                if (i==e.getId()){
                    totalEquipments.add(e);
                    Log.d("happy","КОНВЕРТЕР: Добавили в коллекцию тренажёр с ID: "+e.getId());
                }
            }
        }
        return totalEquipments;
    }
}
