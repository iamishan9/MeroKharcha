package com.prognepal.MeroKharcha;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;



import java.util.Random;

/**
 * Created by Ishan,Kushas,Dalee on 2/13/2017.
 */

public class menu extends AppCompatActivity {

    DatabaseHelper myDb;
    Button view;
    Button change;

EditText budget;
    TextView show;
    ImageButton chart;
    Button overs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_menu);

        myDb = new DatabaseHelper(this);
        view = (Button) findViewById(R.id.button8);
        change = (Button) findViewById(R.id.button5);
        chart = (ImageButton) findViewById(R.id.imageButton2);
overs =  (Button) findViewById(R.id.button9);
        show = (TextView) findViewById(R.id.textView3);
        SharedPreferences sharedPref = getSharedPreferences("Budget", Context.MODE_PRIVATE);
        int paisa = sharedPref.getInt("paisa",0);
         show.setText(String.valueOf(paisa));


        viewAll();
    }

    public void viewAll() {




        change.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {




                        //inflate ->fill
                        View view = LayoutInflater.from(menu.this).inflate(R.layout.change_budget,null);
                        budget = (EditText) view.findViewById(R.id.editText11);

AlertDialog.Builder builder = new AlertDialog.Builder(menu.this);

                         builder.setMessage("Change Budget")
                                .setView(view)
                                .setPositiveButton("Done",new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog,int which){
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

                        SharedPreferences mas = getSharedPreferences("Budget", Context.MODE_PRIVATE);
                        float comp = mas.getInt("paisa",0);

                        float comp1 = 0.12f * comp;

                        Random r = new Random();
                        int i1 = r.nextInt(3 - 0);

                        int x=0;

                        StringBuffer buf = new StringBuffer();
                        while(ove.moveToNext()){
                            if(ove.getInt(0)!=3 && ove.getFloat(3)>comp1)
                            {
                                if(i1==1) {
                                    buf.append("I think you should look into why you're spending this much on  " + ove.getString(1) + " \n ");
                                x++;
                                }
                                else{
                                    buf.append("You're spending a bit on   " + ove.getString(1) + " \n ");
                                    x++;
                                }
                            }

                            else if(ove.getInt(0)!=3 && ove.getFloat(3)>(2*comp1))
                            {if(i1==1) {
                                buf.append("You're spending way too much on  " + ove.getString(1) + " \n ");
                                x++;
                            }
                                else {
                                buf.append("You shouldn't really spend so much on  " + ove.getString(1) + " \n ");
                                x++;
                                }
                            }
                            else if(ove.getInt(0)==3 && ove.getFloat(3)>comp1) {
                                buf.append("Good thing you're investing on your education \n ");
                                x++;
                            }
                            else {
                             if(x==0)
                                 buf.append("Don't worry, your'e fine with your spendings. \n ");
                            }

                        }
                        showMessage("Overspending Check",buf.toString());


                    }


                });




        chart.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), piechart.class);
                        startActivity(i);

                    }
                }
        );




//yolo


        view.setOnClickListener(
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
                           // buffer.append(" ID : " + show.getString(0) + " \n ");
                            buffer.append(" Kharcha Type : " + show.getString(1) + " \n ");
                           buffer.append("Previous Month's Kharcha: Rs. "+ show.getString(2)+ "\n");
                            buffer.append("Current Month's Kharcha : Rs. " +  show.getString(3) + " \n\n ");
                        }

                        showMessage("Kharcha",buffer.toString());

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




}
