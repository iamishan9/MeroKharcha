package com.example.ishan.merokharcha;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.*;

/**
 * Created by Ishan on 2/17/2017.
 */

public class ChangePass extends AppCompatActivity {

    EditText editText3;
    EditText editText4;
    EditText editText5;
    Button button6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepass);

        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        button6=(Button)findViewById(R.id.button6);
    //changing the password

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getSharedPreferences("changePass", Context.MODE_PRIVATE);
                String pass = sharedPref.getString("password", "");

                if (editText3.getText().toString().equals(pass)) {
                    if (editText4.getText().toString().equals(editText5.getText().toString())) {
                        SharedPreferences.Editor editor = sharedPref.edit();

                        editor.putString("password", editText5.getText().toString());
                        editor.apply();
                        Toast.makeText(ChangePass.this, "Done",Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ChangePass.this, "The Passwords do not match", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(ChangePass.this, "Incorrect Old Password", Toast.LENGTH_LONG).show();
                }
            }

        });


    }
    }