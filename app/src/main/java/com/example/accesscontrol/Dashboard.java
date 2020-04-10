package com.example.accesscontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.accesscontrol.estructural.Usuario;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    private ImageView imgRostro;
    private EditText txtbuscar;
    private TextView txtNombre;
    private TextView txtCargo;
    private TextView txtNumeroDocumento;
    private ArrayList<Usuario> listUsuarios = new ArrayList<Usuario>();
    private Usuario usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //Disable landscape mode, dont forget import library
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        imgRostro = (ImageView) findViewById(R.id.imgRostro);
        imgRostro.setImageResource(R.drawable.rostro);

        txtbuscar = (EditText) findViewById(R.id.txtBuscar);
        txtNombre = (TextView) findViewById(R.id.txtName);
        txtCargo = (TextView) findViewById(R.id.txtNombre);
        txtNumeroDocumento = (TextView) findViewById(R.id.txtNumeroDocumento);

        Intent intent = getIntent();

        String nombre, posCompany, documento;

        usr = (Usuario) intent.getSerializableExtra("MSGUSUARIO");

        //nombre = String.valueOf(user.getNombre());
        //nombre = user.getNombre();
        //posCompany = String.valueOf(user.getPosCompany());
        //posCompany = user.getPosCompany();
        //documento = String.valueOf(user.getDocumento());

        //txtNombre.setText(nombre);
        //txtCargo.setText(posCompany);
        //txtNumeroDocumento.setText(documento);
    }

    public void btnCalcularEmpleados(View view)
    {
        int numEmpleados;
        numEmpleados = MainActivity.ser.numEmpleados();
        Toast.makeText(this, "El número de empleados son: " + numEmpleados, Toast.LENGTH_SHORT).show();

    }

    public void btnEliminarEmpleado(View view)
    {
        try
        {
            String strdocumentNumber = txtbuscar.getText().toString();
            int documentNumber = Integer.parseInt(strdocumentNumber);
            if(MainActivity.ser.eliminarUsuario(documentNumber))
            {
                MainActivity.ser.eliminarUsuario(documentNumber);
                Toast.makeText(this, "User deleted with id: " + documentNumber, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Error, the user doesn't exist", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Fields cannot be empty or have null values", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnEditarEmpleado(View view){
        try
        {
            String strdocumentNumber = txtbuscar.getText().toString();
            int documentNumber = Integer.parseInt(strdocumentNumber);
            if(MainActivity.ser.buscarUsuarioPorDocumentNumber(documentNumber))
            {
                Intent editarEmpleado = new Intent(this, EditEmployee.class);
                startActivity(editarEmpleado);
            }
            else
            {
                Toast.makeText(this, "Error, the user doesn't exist", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Fields cannot be empty or have null values", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnListarEmpleados(View view)
    {
        try
        {
            Intent listarEmpleado = new Intent(this, ListEmployee.class);
            startActivity(listarEmpleado);

            Intent intent = new Intent(this, ListEmployee.class);

            intent.putExtra("MSGEMPLEADO", usr);

            startActivity(intent);


            /*if(MainActivity.ser.numEmpleados() > 0)
            {
                Intent listarEmpleado = new Intent(this, ListEmployee.class);
                startActivity(listarEmpleado);
            }
            else
            {
                Toast.makeText(this, "There are no employees", Toast.LENGTH_SHORT).show();
            }*/
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}
