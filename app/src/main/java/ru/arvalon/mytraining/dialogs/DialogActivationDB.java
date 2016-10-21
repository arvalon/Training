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
public class DialogActivationDB extends DialogFragment {

    public interface DialogHost{
        void DialogActivationDBpositive();
        void DialogActivationDBnegative();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext())
                .setMessage(R.string.dialogActivationDBMessage)
                .setTitle(R.string.dialogActivationDBTitle)
                .setPositiveButton(R.string.dialogActivationDBPositiveButton,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try{
                                    ((DialogHost)getContext()).DialogActivationDBpositive();
                                }catch (ClassCastException ex){}
                                dialog.dismiss();
                            }
                        }).setNegativeButton(R.string.dialogActivationDBNegativeButton,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try{
                                    ((DialogHost)getContext()).DialogActivationDBnegative();
                                }catch (ClassCastException ex){}
                                dialog.dismiss();
                            }
                        });
        return builder.create();
    }
}
