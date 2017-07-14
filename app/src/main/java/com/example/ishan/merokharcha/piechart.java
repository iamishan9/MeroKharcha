package com.example.ishan.merokharcha;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.github.mikephil.charting.data.PieDataSet.*;

/**
 * Created by Ishan on 7/14/2017.
 */

public class piechart extends AppCompatActivity {

    DatabaseHelper myDb;
DatabaseHelper data;


private static String TAG="PieChart";



    PieChart pieChart;

@Override
    protected void onCreate(Bundle savedInstanceState) {





    super.onCreate(savedInstanceState);
    setContentView(R.layout.piechart);






    pieChart = (PieChart) findViewById(R.id.idPieChart);
    Log.d(TAG, "onCreate: starting to create chart");

    data = new DatabaseHelper(this);
    Cursor dat = data.showKharcha();

   final String[] xDa = new String[dat.getCount()];
    final float[] yDa = new float[dat.getCount()];
    int i = 0;
    while (dat.moveToNext()){
        String kname = dat.getString(1);
        Float kh= dat.getFloat(3);
        float kharcha = kh.floatValue();
        xDa[i]=kname;
        yDa[i]=kharcha;
        i++;


    }







    //pieChart.setDescription("Kharcha of the month ");
    pieChart.setRotationEnabled(true);
    //pieChart.setUsePercentValues(true);
    //pieChart.setHoleColor(Color.WHITE);
    pieChart.setCenterTextColor(Color.BLACK);
    pieChart.setHoleRadius(25f);
    pieChart.setTransparentCircleAlpha(0);
    pieChart.setCenterText("Kharcha");
    pieChart.setCenterTextSize(10);
    //pieChart.setDrawEntryLabels(true);
    //pieChart.setEntryLabelTextSize(20);

    addDataSet();



}

    private void addDataSet() {

        Log.d(TAG,"addDataSet started");

        myDb = new DatabaseHelper(this);
        Cursor pie = myDb.showKharcha();

        String[] xData = new String[pie.getCount()];
        float[] yData = new float[pie.getCount()];
        int i = 0;
        float total = 0;
        while (pie.moveToNext()){
            String kname = pie.getString(1);
            Float kh= pie.getFloat(3);
            float kharcha = kh.floatValue();
            total = total + kharcha ;
            xData[i]=kname;
            yData[i]=kharcha;
            i++;


        }





        ArrayList<PieEntry> pieEntry= new ArrayList<>();
        //ArrayList<String> xEntry = new ArrayList<>();

        for( i =0;i < yData.length;i++){
           //yData[i] = yData[i]*100/total;
            pieEntry.add(new PieEntry(yData[i],xData[i]));
        }


//create the data set
        PieDataSet pieDataSet = new PieDataSet(pieEntry,"KHARCHA");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
       // colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);


        //add legend to the chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);


        //create pieData object
        PieData pieData =  new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();


    }


}
