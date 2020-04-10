package com.example.accesscontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

public class EditEmployee extends AppCompatActivity {

    private ImageView imgEditRostro;
    private Spinner spinnerGeneroEdit;
    private static ArrayList<String> lista = new ArrayList<String>();
    String textoSpinner;
    private EditText txtEditUser;
    private EditText txtEditPassword;
    private EditText txtEditNewPassword;
    private EditText txtEditFirstName;
    private EditText txtEditLastName;
    private EditText txtEditDocumentNumber;
    private EditText txtEditAge;
    private EditText txtEditPositionInCompany;
    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);
        //Disable landscape mode, dont forget import library
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        imgEditRostro = (ImageView) findViewById(R.id.imgEditRostro);
        imgEditRostro.setImageResource(R.drawable.rostro);

        txtEditUser = (EditText) findViewById(R.id.txtEditUser);
        txtEditPassword = (EditText) findViewById(R.id.txtEditPassword);
        txtEditNewPassword = (EditText) findViewById(R.id.txtEditNewPassword);
        txtEditFirstName = (EditText) findViewById(R.id.txtEditFirstName);
        txtEditLastName = (EditText) findViewById(R.id.txtEditLastName);
        txtEditDocumentNumber = (EditText) findViewById(R.id.txtEditDocumentNumber);
        txtEditAge = (EditText) findViewById(R.id.txtEditAge);
        txtEditPositionInCompany = (EditText) findViewById(R.id.txtEditPositionInCompany);

        Intent intent = getIntent();
        user = (Usuario) intent.getSerializableExtra("MSJEMPLEADO2");

        txtEditDocumentNumber.setText(String.valueOf(user.getDocumento()));
        txtEditDocumentNumber.setEnabled(false);

        txtEditUser.setText(user.getUsuario());
        txtEditUser.setEnabled(false);
        txtEditFirstName.setText(user.getNombre());
        txtEditLastName.setText(user.getApellido());
        txtEditAge.setText(String.valueOf(user.getEdad()));
        txtEditPositionInCompany.setText(user.getPosCompany());

        //Codigo spinner
        spinnerGeneroEdit = (Spinner) findViewById(R.id.spinnerGeneroEdit);
        if(lista.size()<2)
        {
            lista.add("Male");
            lista.add("Female");
        }
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinnerlist, lista);
        spinnerGeneroEdit.setAdapter(adapter);
        //Escuchar spinner
        spinnerGeneroEdit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            textoSpinner = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
        );
    }

    public void botonEdit(View view)
    {
        String usuario,password, newPassword, nombre, apellido,posCompany, genero, strEdad, strDocumento;
        int edad, documento;

        try
        {
            usuario = txtEditUser.getText().toString();
            password = txtEditPassword.getText().toString();
            newPassword = txtEditNewPassword.getText().toString();
            nombre = txtEditFirstName.getText().toString();
            apellido = txtEditLastName.getText().toString();
            posCompany = txtEditPositionInCompany.getText().toString();
            genero = textoSpinner;
            strEdad = txtEditAge.getText().toString();
            strDocumento = txtEditDocumentNumber.getText().toString();

            edad = Integer.parseInt(strEdad);
            documento = Integer.parseInt(strDocumento);

            if (!usuario.equals("") && !password.equals("") && !nombre.equals("") && !apellido.equals("") && !posCompany.equals("")
                    && !genero.equals("") && edad > 0 && documento > 0) {
                Usuario newUsr = new Usuario(usuario, password, nombre, apellido, posCompany, genero, edad, documento);

                if (MainActivity.ser.validacion(newUsr) == true)
                {
                    if(MainActivity.ser.login(usuario, password))
                    {
                        MainActivity.ser.modificarUsuario(usuario, newPassword, nombre, apellido, posCompany, genero, edad, documento);
                        Toast.makeText(this, "successful modification", Toast.LENGTH_SHORT).show();
                        Intent dashboard = new Intent(this, Dashboard.class);
                        startActivity(dashboard);

                    }
                    else
                    {
                        Toast.makeText(this, "Incorrect credentials", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(this, "The user doesn't exists", Toast.LENGTH_SHORT).show();
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

