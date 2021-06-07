package umn.ac.id.uts_32927;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;


public class SettingDialog extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Setting");
        builder.setMessage("What Do You Want To Do ?");
        builder.setNegativeButton("Profile", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                profile();
            }
        });
        builder.setPositiveButton("Log Out !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                login();
            }
        });
        return builder.create();
    }
    public void profile(){
        Intent intent = new Intent(getContext(),Profile.class);
        startActivity(intent);
    }
    public void login(){
        Intent intent = new Intent(getContext(),Login.class);
        startActivity(intent);
    }
}
