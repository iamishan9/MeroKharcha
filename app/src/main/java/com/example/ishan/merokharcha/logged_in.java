package com.example.ishan.merokharcha;

/**
 * Created by Ishan on 2/13/2017.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class logged_in extends AppCompatActivity {

    Button button3;
    Button buttonCalc;
    Button button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logged_in);


        button3 =(Button)findViewById(R.id.button3);
        button4 =(Button)findViewById(R.id.button4);
        buttonCalc=(Button)findViewById(R.id.buttonCalc);
        button3.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View v) {
                                           Intent i = new Intent(getApplicationContext(), add_trans.class);
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
