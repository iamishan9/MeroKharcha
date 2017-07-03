package com.example.ishan.merokharcha;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.system.StructPollfd;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Ishan on 2/13/2017.
 */

public class menu extends AppCompatActivity {

    DatabaseHelper myDb;
    Button view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_menu);

        myDb = new DatabaseHelper(this);
        view = (Button) findViewById(R.id.button8);
        viewAll();
    }

    public void viewAll() {
        view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor show = myDb.showKharcha();
                        Toast.makeText(menu.this,String.valueOf(show.getCount()),Toast.LENGTH_LONG).show();
                        if (show.getCount() == 0) {
                            showMessage("Error","No kharcha found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();

                       while (show.moveToNext()) {
                            buffer.append(" ID : " + show.getString(0) + " \n ");
                            buffer.append(" KharchaType : " + show.getString(1) + " \n ");
                            buffer.append(" Kharcha : " +  show.getString(2) + " \n\n ");
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
