package ru.arvalon.mytraining.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import ru.arvalon.mytraining.R;

/**
 * Created by arvalon on 15.06.2016.
 */
public class DialogAddEquipmentEnd extends DialogFragment {

    public interface DialogHost{
        void DialogAddEquipmentEndPositive();
        void DialogAddEquipmentEndNegative();
        void DialogAddEquipmentEndNeutral();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext())
                .setMessage(R.string.dialog_addequipmentadd)
                .setTitle(R.string.equipment_added)
                .setPositiveButton(R.string.choose_exercises,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try{
                                    ((DialogHost)getContext()).DialogAddEquipmentEndPositive();
                                }catch (ClassCastException ex){}
                                dialog.dismiss();
                            }
                        }).setNegativeButton(R.string.add_more_equipment,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try{
                                    ((DialogHost)getContext()).DialogAddEquipmentEndNegative();
                                }catch (ClassCastException ex){}
                                dialog.dismiss();
                            }
                        }).setNeutralButton(R.string.main_menu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try{
                            ((DialogHost)getContext()).DialogAddEquipmentEndNeutral();
                        }catch (ClassCastException ex){}
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}
