package com.example.accesscontrol;

import android.app.ActionBar;
import android.content.Context;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TableDynamic {

    private TableLayout tableLayout;
    private Context context;
    private String[] header;
    private ArrayList<String[]> data;
    private TableRow tableRow;
    private TextView txtcell;
    private int indexC;
    private int indexR;
    private boolean multiColor=false;
    int firstColor,secondColor;

    public TableDynamic(TableLayout tableLayout, Context context)
    {
        this.tableLayout=tableLayout;
        this.context=context;
    }
    public void addHeader(String[] header)
    {
        this.header=header;
        createHeader();
    }
    public void addData(ArrayList<String[]> data)
    {
        this.data=data;
        createDataTable();
    }
    private void newRow(){
        tableRow=new TableRow(context);
    }
    private  void newCell(){
        txtcell=new TextView(context);
        //setGravity es para la posicion del texto dentro del textview
        txtcell.setGravity(Gravity.CENTER);
        txtcell.setTextSize(25);
    }
    private void createHeader(){
        indexC=0;
        newRow();
        while (indexC<header.length){
            newCell();
            txtcell.setText(header[indexC++]);
            tableRow.addView(txtcell,newTableRowParams());
        }
        tableLayout.addView(tableRow);
    }
    private void createDataTable(){
        String info;
        for(indexR=0;indexR<=header.length;indexR++)
        {
            newRow();
            for(indexC=0;indexC<header.length;indexC++)
            {
                newCell();
                String[] row=data.get(indexR);
                info=(indexC<row.length)?row[indexC]:"";
                txtcell.setText(info);
                tableRow.addView(txtcell,newTableRowParams());
            }
            tableLayout.addView(tableRow);
        }
    }
    public void addItems(String[]item){
        String info;
        data.add(item);
        indexC=0;
        newRow();
        while(indexC<header.length){
            newCell();
            info=(indexC<item.length)?item[indexC++]:"";
            txtcell.setText(info);
            tableRow.addView(txtcell,newTableRowParams());
        }
        tableLayout.addView(tableRow,data.size()-1);
    }
    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams params= new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight=1;
        return  params;
    }
}
