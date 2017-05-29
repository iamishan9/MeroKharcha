package com.example.ishan.merokharcha;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.logging.Logger;

import static android.support.constraint.R.id.parent;
import static java.util.logging.Logger.global;

/**
 * Created by Ishan on 2/17/2017.
 */

public class add_trans extends AppCompatActivity implements OnItemSelectedListener{
    private Button button;
    private EditText editText;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    private int f,t,e,c,a,m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_trans);

        editText = (EditText)findViewById(R.id.editText2);
        button = (Button)findViewById(R.id.button7);
        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height= dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.9));

        spinner = (Spinner)findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.expenditure_type,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

      //  SharedPreferences sharedPref = getSharedPreferences("addtrans", Context.MODE_PRIVATE);
        //final int num = sharedPref.getInt("adts", "");






        final int poss = spinner.getSelectedItemPosition();

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(editText.getText().toString());

                if(poss == 0)
                    f=f+num;
                else if(poss ==1)
                    t=t+num;
                else if(poss==2)
                    e=e+num;
                else if(poss==3)
                    c=c+num;
                else if(poss==4)
                    a=a+num;
                else
                    m=m+num;
//
                Toast.makeText(add_trans.this,String.valueOf(m),Toast.LENGTH_LONG).show();
    }
        });



        spinner.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       // Toast.makeText(this,String.valueOf(position),Toast.LENGTH_LONG).show();

        //String selected= parent.getItemAtPosition(position).toString();
        //Toast.makeText(this,selected,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    }



