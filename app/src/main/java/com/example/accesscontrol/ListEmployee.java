package com.example.accesscontrol;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.accesscontrol.estructural.Usuario;
import com.example.accesscontrol.servicios.Servicio;

import java.util.ArrayList;

public class ListEmployee extends AppCompatActivity {

    private ListView lvListaEmpleados;
    private ArrayList x;
    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employee);

        Intent intent = getIntent();
        user = (Usuario) intent.getSerializableExtra("MSGEMPLEADO");

        lvListaEmpleados = (ListView) findViewById(R.id.lvListaEmpleados);
        //Servicio servicio = new Servicio();
        //                                                          clase actual, estilo del adapter, datos para llenar
         x = MainActivity.ser.getListaDeNombres();

         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, x);
        //Asociando el adaptador al list view
        lvListaEmpleados.setAdapter(adapter);

        lvListaEmpleados.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {


            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListEmployee.this);
                builder.setTitle("Alerta");

                builder.setMessage("¿Desea editar el empleado?").setPositiveButton("Si", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        irAEmpleados(adapterView.getItemAtPosition(i).toString());
                    }
                }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dialog.cancel();
                    }
                }).setCancelable(false).show();

                //Toast.makeText(getApplicationContext(), "Pos : " + i + " Valor : " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void irAEmpleados(String empleado)
    {
        Usuario usuario = null;

        for(int i = 0; i<x.size(); i++)
        {
            String y = (String) x.get(i);

            ArrayList list = MainActivity.ser.getListUsuarios();

            for(int j = 0; j < list.size(); j++)
            {
                Usuario usr = (Usuario) list.get(j);
                int documento = usr.getDocumento();
                String strDocumento = String.valueOf(documento);
                String strNombre = usr.getNombre();

                if(y.contains(strDocumento) && y.contains(strNombre))
                {
                    usuario = MainActivity.ser.enviarObjDocumento(documento);
                }
            }
        }
        Intent intent = new Intent(this, EditEmployee.class);
        intent.putExtra("MSJEMPLEADO2", usuario);
        startActivity(intent);
    }

    public void onBackPressed()
    {
        x.clear();
        finish();
    }


}
