package com.example.nijandhanl.alertboxsample;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btn_show_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_show_dialog = findViewById(R.id.btn_show_dialog);
        btn_show_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    private void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_input_dialog,null,false);
        builder.setView(view);
        builder.setCancelable(false);
        final EditText et_input = view.findViewById(R.id.et_input);
        builder.setTitle("Enter Notes");
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                hideKeyboard(MainActivity.this,et_input);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                hideKeyboard(MainActivity.this,et_input);
            }
        });

        AlertDialog ad = builder.create();
        ad.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showKeyboard(MainActivity.this,et_input);
                    }
                },100);
            }
        });
        ad.show();
    }

    public static void hideKeyboard(Context context, View view){

        try {
            if (view != null&&context!=null) {
                InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception ignored) {

        }
    }

    public static void showKeyboard(Context context, View view) {
        InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.toggleSoftInputFromWindow(view.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
    }
}
