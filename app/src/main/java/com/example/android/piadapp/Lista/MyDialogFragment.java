package com.example.android.piadapp.Lista;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;


public class MyDialogFragment extends DialogFragment {

    public MyDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle b = getArguments();


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Risultato")
                .setMessage(b.getString("Message"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getDialog().dismiss();
                    }
                });



        return builder.create();
    }
}
