package com.example.ishan.merokharcha;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private Button button2;


    //variable to hold the string aka password
    private String pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText = (EditText)findViewById(R.id.editText);


        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                SharedPreferences sharedPref = getSharedPreferences("changePass", Context.MODE_PRIVATE);
                String pass = sharedPref.getString("password", "");

                if (editText.getText().toString().equals(pass)) {

                    Intent i = new Intent(getApplicationContext(), logged_in.class);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ChangePass.class);
                startActivity(i);
            }
        });

        }}


