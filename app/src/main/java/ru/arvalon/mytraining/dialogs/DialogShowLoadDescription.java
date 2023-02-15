package ru.arvalon.mytraining.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;

import ru.arvalon.mytraining.R;

/**
 * Created by arvalon on 21.07.2016.
 */
public class DialogShowLoadDescription extends DialogFragment {

    /*public interface DialogHost{
        void DialogShowLoadDescriptionPositive();
    }*/

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext())
                .setMessage(R.string.loadMessageHelp)
                .setTitle(R.string.loadMessageHelpTitle)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                /*try{
                                    ((DialogHost)getContext()).DialogShowLoadDescriptionPositive();
                                }catch (ClassCastException ex){}*/
                                dialog.dismiss();
                            }
                        }
                );
        return builder.create();
    }
}
