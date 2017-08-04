package com.prognepal.MeroKharcha;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ishan on 2/17/2017.
 */

public class add_trans extends AppCompatActivity implements OnItemSelectedListener{
    DatabaseHelper myDb;
    private Button button;
    private Button del;
    private Button assign;
    private EditText dele;
    private EditText editText;

    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myDb = new DatabaseHelper(this);




        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_trans);






        editText = (EditText)findViewById(R.id.editText2);



        // List<String> spinnerArray =  new ArrayList<String>();
       /* spinnerArray.add("Grocery");
       spinnerArray.add("Transportation");
       spinnerArray.add("Rent");
       spinnerArray.add("Electricity Bill");
       saveArray(spinnerArray,"SpinnerArrayData",getApplicationContext());
*/
      final List<String> spinnerArray = loadArray("SpinnerArrayData",this);
      final  ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);

        Button addcat  = (Button)findViewById(R.id.button11);



        View.OnClickListener addcate = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText cat = (EditText)findViewById(R.id.editText10);
                String cate = cat.getText().toString();


              //adapter.insert(cate);
                spinnerArray.add(cate);

                Toast.makeText(add_trans.this,cate + " Has been added as a new category ",Toast.LENGTH_LONG).show();
                saveArray( spinnerArray,"SpinnerArrayData",getApplicationContext());


            }
        };

        addcat.setOnClickListener(addcate);

        sItems.setOnItemSelectedListener(this);




    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {










        final String selected= parent.getItemAtPosition(position).toString();

        del = (Button)findViewById(R.id.button10);
        dele = (EditText)findViewById(R.id.editText9) ;

        assign = (Button)findViewById(R.id.button12);

        button = (Button)findViewById(R.id.button7);
        View.OnClickListener handle = new View.OnClickListener(){

            @Override
            public void onClick(View v) {



                Calendar check = Calendar.getInstance();
                int thisMonth = check.get(Calendar.MONTH);

                SharedPreferences sharedPrefe = getSharedPreferences("Budget", Context.MODE_PRIVATE);
                int tot = sharedPrefe.getInt("tot",0);
                int xy = tot + Integer.parseInt(editText.getText().toString());

                SharedPreferences.Editor editrema =  sharedPrefe.edit();

                editrema.putInt("tot", xy);
                editrema.apply();









                Cursor res = myDb.showKharcha();

                String chk= position+""+thisMonth;
                int chks= Integer.parseInt(chk);
                if(res.getCount()==0)
                //for the first entry to database
                {

                    int num = Integer.parseInt(editText.getText().toString());

                    boolean ifAdded = myDb.insertData(chk,selected,num,thisMonth,0);

                    if(ifAdded == true) {

                        Toast.makeText(add_trans.this, "Kharcha added to " + selected + " successfully", Toast.LENGTH_LONG).show();

                    }

                }
                else

                    {

        //if there is already data in database whose type and date matches
    while(res.moveToNext())
    {


        if(res.getInt(0)==chks)
        {int num = Integer.parseInt(editText.getText().toString());

            num = num + res.getInt(2);
            boolean ifAdded = myDb.updateData(res.getString(0),selected,num,thisMonth,res.getInt(4));
            if(ifAdded == true)
                Toast.makeText(add_trans.this,"Kharcha on "+selected+" updated successfully",Toast.LENGTH_LONG).show();

        }
        else

    //if there is data in database but date is different

            {int num = Integer.parseInt(editText.getText().toString());

            boolean ifAdded = myDb.insertData(chk,selected,num,thisMonth,res.getInt(4));
            if(ifAdded == true) {
                Toast.makeText(add_trans.this, "Kharcha added to " + selected + " successfully", Toast.LENGTH_LONG).show();
                //Toast.makeText(add_trans.this,String.valueOf(month),Toast.LENGTH_LONG).show();

            }
        }
    }
}

            }
        };

        View.OnClickListener delete = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Cursor dels = myDb.showKharcha();


                Calendar cal = Calendar.getInstance();
                int thisMonth = cal.get(Calendar.MONTH);

                String chk= position+""+thisMonth;
                int chks= Integer.parseInt(chk);
int c=0;


                while(dels.moveToNext())
                {
                    if(dels.getInt(0)==chks)
                    {int num = Integer.parseInt(dele.getText().toString());

                     int   numero =dels.getInt(2)-num;
                        boolean ifAdded = myDb.updateData(dels.getString(0),selected,numero,thisMonth,dels.getInt(4));
                        if(ifAdded == true)
                            Toast.makeText(add_trans.this,"Kharcha of "+num+" deleted from "+selected+" successfully",Toast.LENGTH_LONG).show();
                        //else
                        //Toast.makeText(add_trans.this,"Kharcha couldn't be updated on "+selected,Toast.LENGTH_LONG).show();
                    c=c+1;
                    }
                }
if(c==0)
    Toast.makeText(add_trans.this,"Category not found ",Toast.LENGTH_LONG).show();





            }
        };


        View.OnClickListener assignb = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
EditText budgt = (EditText)findViewById(R.id.editText6);
                int num = Integer.parseInt(budgt.getText().toString());
                Calendar cal = Calendar.getInstance();
                int thisMonth = cal.get(Calendar.MONTH);
                Cursor ass = myDb.showKharcha();

                String chk= position+""+thisMonth;
                int chks= Integer.parseInt(chk);


                while(ass.moveToNext()) {
                if(ass.getInt(0)==chks) {
                    boolean ifAdded = myDb.updateData(ass.getString(0),selected, ass.getInt(1), thisMonth, num);
                    if (ifAdded == true)
                        Toast.makeText(add_trans.this, "Budget on " + selected + " added successfully", Toast.LENGTH_LONG).show();
                }

                }

            }
        };



                button.setOnClickListener(handle);
                del.setOnClickListener(delete);
                assign.setOnClickListener(assignb);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    public boolean saveArray(List<String> array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.size());
        for(int i=0;i<array.size();i++)
            editor.putString(arrayName + "_" + i, array.get(i));
        return editor.commit();
    }


    public List<String> loadArray(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        List<String> array = new ArrayList<String> ();
        for(int i=0;i<size;i++)
            array.add(prefs.getString(arrayName + "_" + i, null));
        return array;
    }

}



