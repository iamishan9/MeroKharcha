package com.prognepal.MeroKharcha;

/**
 * Created by Ishan on 2/13/2017.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;




public class logged_in extends AppCompatActivity {

    Button button3;
    Button buttonCalc;
    Button button4;
    TextView remain;
    TextView totkh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logged_in);






        remain = (TextView) findViewById(R.id.textView12);
        totkh = (TextView) findViewById(R.id.textView13);

        SharedPreferences sharedPref = getSharedPreferences("Budget", Context.MODE_PRIVATE);
        int  bud= sharedPref.getInt("paisa",0);
        int rema = sharedPref.getInt("rema",0);
        int tot = sharedPref.getInt("tot",0);

        rema = bud-tot;
       SharedPreferences.Editor editrema = sharedPref.edit();

        editrema.putInt("rema", rema);
        editrema.apply();

        remain.setText("Rs. "+String.valueOf(rema));
        totkh.setText("Rs. "+String.valueOf(tot));






        button3 =(Button)findViewById(R.id.button3);
        button4 =(Button)findViewById(R.id.button4);
        buttonCalc=(Button)findViewById(R.id.buttonCalc);
        button3.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View v) {
                                        /*   Calendar calendar = Calendar.getInstance();


                                           int thisMonth = calendar.get(Calendar.MONTH);


                                           SharedPreferences sharedPref = getSharedPreferences("Date", Context.MODE_PRIVATE);
                                           int month = sharedPref.getInt("month",0);
                                          // int month = Integer.parseInt(sharedPref.getString("month", "0"));
                                           if(month==0) {
                                               //int month = Integer.parseInt(sharedPref.getString("month", "0"));


                                               SharedPreferences.Editor editor = sharedPref.edit();

                                               editor.putInt("month", thisMonth);
                                               editor.apply();

                                           }
                                          */ Intent i = new Intent(getApplicationContext(), add_trans.class);
                                           startActivity(i);
                                       }
                                   });

        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), menu.class);
                startActivity(i);
            }
        });

                buttonCalc.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent i= new Intent(getApplicationContext(), calculator.class);
                        startActivity(i);


            }








    });
}}
