package com.example.ishan.merokharcha;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Ishan on 2/17/2017.
 */

public class add_trans extends AppCompatActivity implements OnItemSelectedListener{
    DatabaseHelper myDb;
    private Button button;
    private EditText editText;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_trans);

       myDb = new DatabaseHelper(this);

        editText = (EditText)findViewById(R.id.editText2);
       // button = (Button)findViewById(R.id.button7);
        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height= dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.9));

        spinner = (Spinner)findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.expenditure_type,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
     //  Toast.makeText(this,String.valueOf(position),Toast.LENGTH_LONG).show();

        final String selected= parent.getItemAtPosition(position).toString();

        button = (Button)findViewById(R.id.button7);
        View.OnClickListener handle = new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Cursor res = myDb.showKharcha();
if(res.getCount()==0)
{int num = Integer.parseInt(editText.getText().toString());

    boolean ifAdded = myDb.insertData(String.valueOf(position),selected,num);
    if(ifAdded == true)
        Toast.makeText(add_trans.this,"Kharcha added to "+selected+" successfully",Toast.LENGTH_LONG).show();
  //  else
      // Toast.makeText(add_trans.this,"Kharcha couldn't be added to "+selected,Toast.LENGTH_LONG).show();

}
else
{
    while(res.moveToNext())
    {
        if(res.getInt(0)==position)
        {int num = Integer.parseInt(editText.getText().toString());

            num = num + res.getInt(2);
            boolean ifAdded = myDb.updateData(String.valueOf(position),selected,num);
            if(ifAdded == true)
                Toast.makeText(add_trans.this,"Kharcha on "+selected+" updated successfully",Toast.LENGTH_LONG).show();
            //else
                //Toast.makeText(add_trans.this,"Kharcha couldn't be updated on "+selected,Toast.LENGTH_LONG).show();
        }
        else
        {int num = Integer.parseInt(editText.getText().toString());

            boolean ifAdded = myDb.insertData(String.valueOf(position),selected,num);
            if(ifAdded == true)
                Toast.makeText(add_trans.this,"Kharcha added to "+selected+" successfully",Toast.LENGTH_LONG).show();
            //else
                //Toast.makeText(add_trans.this,"Kharcha couldn't be added to "+selected,Toast.LENGTH_LONG).show();
        }
    }
}

            }
        };

        button.setOnClickListener(handle);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    }



