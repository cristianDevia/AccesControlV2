package com.example.accesscontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.accesscontrol.estructural.Usuario;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    private Spinner spinnerGenero;
    private static ArrayList <String> lista = new ArrayList<String>();
    private EditText txtUsuario;
    private EditText txtPassword;
    private EditText txtNombre;
    private EditText txtApeliido;
    private EditText txtPosCompany;
    private EditText txtGenero;
    private EditText txtEdad;
    private EditText txtDocumento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Disable landscape mode, dont forget import library
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Codigo spinner
        spinnerGenero = (Spinner) findViewById(R.id.spinnerGenero);

        txtUsuario = (EditText) findViewById(R.id.txtUser);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtNombre = (EditText) findViewById(R.id.txtFirstName);
        txtApeliido = (EditText) findViewById(R.id.txtLastNamecodigo);
        txtPosCompany = (EditText) findViewById(R.id.txtPositionInCompany);

        txtEdad = (EditText)findViewById(R.id.txtAge);
        txtDocumento = (EditText)findViewById(R.id.txtDocumentNumber);

        if(lista.size()<2)
        {
            lista.add("Male");
            lista.add("Female");
        }
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinnerlist, lista);
        spinnerGenero.setAdapter(adapter);
        //Escuchar spinner
        spinnerGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String mensaje = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }
        );
    }

    public void botonRegistrar(View view){

        String usuario,password, nombre, apellido,posCompany, genero, strEdad, strDocumento;
        int edad, documento;

        try {
            usuario = txtUsuario.getText().toString();
            password = txtPassword.getText().toString();
            nombre = txtNombre.getText().toString();
            apellido = txtApeliido.getText().toString();
            posCompany = txtPosCompany.getText().toString();
            genero = (String) spinnerGenero.toString();
            strEdad = txtEdad.getText().toString();
            strDocumento = txtDocumento.getText().toString();

            edad = Integer.parseInt(strEdad);
            documento = Integer.parseInt(strDocumento);

            if (!usuario.equals("") && !password.equals("") && !nombre.equals("") && !apellido.equals("") && !posCompany.equals("")
                    && !genero.equals("") && edad > 0 && documento > 0) {
                Usuario newUsr = new Usuario(usuario, password, nombre, apellido, posCompany, genero, edad, documento);

                if (MainActivity.ser.validacion(newUsr) == true)
                {
                    Toast.makeText(this, "The user already exists", Toast.LENGTH_SHORT).show();
                }
                else
                    {

                    MainActivity.ser.registrarUsuario(newUsr);
                    Toast.makeText(this, "Successful registration", Toast.LENGTH_SHORT).show();
                    Intent registrar = new Intent(this, Login.class);
                    startActivity(registrar);
                }
            } else {
                Toast.makeText(this, "Fields cannot be empty or have null values", Toast.LENGTH_SHORT).show();

            }

        }
        catch (Exception e)
        {
            Toast.makeText(this, "Fields cannot be empty or have null values", Toast.LENGTH_SHORT).show();

        }

    }

}
