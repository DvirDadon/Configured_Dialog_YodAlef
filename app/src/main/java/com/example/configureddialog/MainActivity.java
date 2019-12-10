package com.example.configureddialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * @author Dvir Dadon
 * @since 10/12/2019
 */

public class MainActivity extends AppCompatActivity {
    LinearLayout ll;
    AlertDialog.Builder adb;
    final String[] colors = {"Red","Green","Blue"};
    int color[];
    String st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll =findViewById(R.id.ll);
    }


    /**
     * @since 10/12/2019
     * This method let the user pick one RGB color and change the linear layout to this color
     */

    public void SingleColor(View view) {
        adb = new AlertDialog.Builder(this);
        color = new int[]{0,0,0};
        adb.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                color[which]=255;
                ll.setBackgroundColor(Color.rgb(color[0],color[1],color[2]));
            }
        });
        adb.setTitle("Choose one color");
        adb.setCancelable(false);
        AlertDialog ad  = adb.create();
        ad.show();
    }

    /**
     * @since 10/12/2019
     * This method let the user pick one or more RGB color and the linear layout to this color
     */

    public void Two_Colors(View view) {
        color = new int[]{0,0,0};
        adb = new AlertDialog.Builder(this);
        adb.setTitle("Combine Colors to change background");
        adb.setMultiChoiceItems(colors, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            color[which]=255;
            }
        });
        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ll.setBackgroundColor(Color.rgb(color[0],color[1],color[2]));
            }
        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        adb.setCancelable(false);
        AlertDialog ad = adb.create();
        ad.show();
    }


    /**
     * @since 10/12/2019
     * This method change the Linear layout color to white
     */

    public void Reset(View view) {
        ll.setBackgroundColor(Color.WHITE);
    }

    /**
     * @since 10/12/2019
     * This method let the user choose whatever text he want to write and it will show in toast
     */

    public void Text(View view) {
        adb = new AlertDialog.Builder(this);
        adb.setTitle("Enter text to Toast");
        final EditText et = new EditText(this);
        et.setHint("Enter here text");
        adb.setView(et);
        adb.setPositiveButton("Show toast", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                st = et.getText().toString();
                Toast.makeText(MainActivity.this,st, Toast.LENGTH_SHORT).show();
            }
        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        adb.setCancelable(false);
        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * @since 10/12/2019
     * This method creates general Option menu
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    /**
     *
     * @since 10/12/2019
     * @return The method moving you to the credits activity
     */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent si = new Intent(this,Credits.class);
        startActivity(si);
        return true;
    }
}
