package com.prognepal.MeroKharcha;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.Random;

/**
 * Created by Ishan on 2/13/2017.
 */

public class menu extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper myDb;

    Button change;

EditText budget;
    TextView show;

    Button overs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_menu);


        myDb = new DatabaseHelper(this);

        change = (Button) findViewById(R.id.button5);

overs =  (Button) findViewById(R.id.button9);
        show = (TextView) findViewById(R.id.textView3);
        SharedPreferences sharedPref = getSharedPreferences("Budget", Context.MODE_PRIVATE);
        int paisa = sharedPref.getInt("paisa",0);
         show.setText(String.valueOf(paisa));

        Spinner spinner = (Spinner)findViewById(R.id.spinner2);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.expenditure_month, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

spinner.setOnItemSelectedListener(this);

        viewAll();

    }

    public void viewAll() {


        change.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        //inflate ->fill
                        View view = LayoutInflater.from(menu.this).inflate(R.layout.change_budget, null);
                        budget = (EditText) view.findViewById(R.id.editText11);

                        AlertDialog.Builder builder = new AlertDialog.Builder(menu.this);

                        builder.setMessage("Change Budget")
                                .setView(view)
                                .setPositiveButton("Done", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        SharedPreferences sharedPref = getSharedPreferences("Budget", Context.MODE_PRIVATE);
                                        //int paisa = sharedPref.getInt("paisa",0);
                                        int bud = Integer.parseInt(budget.getText().toString());

                                        SharedPreferences.Editor editor = sharedPref.edit();

                                        editor.putInt("paisa", bud);
                                        editor.apply();
                                        show.setText(String.valueOf(bud));


                                    }


                                })
                                .setNegativeButton("Cancel", null)
                                .setCancelable(false);
                        AlertDialog alert = builder.create();
                        alert.show();

                    }
                }
        );

        overs.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor ove = myDb.showKharcha();


                        if (ove.getCount() == 0) {
                            showMessage("Error", "No kharcha found");
                            return;
                        }

                        android.icu.util.Calendar check = android.icu.util.Calendar.getInstance();
                        int thisMonth = check.get(android.icu.util.Calendar.MONTH);

                        StringBuffer buf = new StringBuffer();
                        StringBuffer buff = new StringBuffer();

                        buf.append("You're overspending on : \n");
                        buff.append("You're doing okay on : \n");
                        while (ove.moveToNext()) {
                            // buffer.append(" ID : " + show.getString(0) + " \n ");
                            if (ove.getInt(3) == thisMonth) {
                                if(ove.getInt(2) > ove.getInt(4)) {
                                    int dif = ove.getInt(2) - ove.getInt(4);
                                    buf.append(ove.getString(1) + " by Rs." + dif + "\n");
                                }
                                else
                                {
                                   buff.append(ove.getString(1) +", ");
                                }

                            }

                        }
buf.append("\n\n"+buff);
                        showMessage("Overspending Check ", buf.toString());


                    }
                }
        );
    }














    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();




    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {

        final String selected= parent.getItemAtPosition(position).toString();


        ImageButton chart = (ImageButton) findViewById(R.id.imageButton2);
        Button   show = (Button) findViewById(R.id.button8);
        show.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor show = myDb.showKharcha();

                        if (show.getCount() == 0) {
                            showMessage("Error","No kharcha found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();

                        while (show.moveToNext()) {

                            if(show.getInt(3)==position) {
                                buffer.append(" Kharcha Type : " + show.getString(1) + " \n ");
                                buffer.append(" Kharcha: Rs. " + show.getString(2) + "\n\n");
                            }
                        }

                        showMessage("Kharcha for the month of "+selected ,buffer.toString());

                    }
                }
        );










        chart.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), piechart.class);
                        i.putExtra("pos", position);
                        startActivity(i);

                    }
                }
        );



    }

@Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}
