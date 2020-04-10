package com.example.accesscontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.accesscontrol.estructural.Usuario;

public class Login extends AppCompatActivity {

    private ImageView imgCandado;
    private TextView txtClicHere;
    private EditText txtUsuario;
    private EditText txtContra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Disable landscape mode, dont forget import library
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        imgCandado = (ImageView) findViewById(R.id.imgCandado);
        imgCandado.setImageResource(R.drawable.candado);

        txtUsuario = (EditText) findViewById(R.id.txtUser);
        txtContra = (EditText) findViewById(R.id.txtPassword);

        //underline txtClicHere
        TextView textView = (TextView) findViewById(R.id.txtClicHere);
        SpannableString content = new SpannableString("Clic Here");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);
    }

    public void botonLogin(View view) {

        String usuario, contra;
        Usuario x = null;

        usuario = txtUsuario.getText().toString();
        contra = txtContra.getText().toString();

        if(MainActivity.ser.login(usuario,contra) == true)
        {
            Intent login = new Intent(this, Dashboard.class);
            startActivity(login);

            x = MainActivity.ser.enviarObj(usuario, contra);

            Intent intent = new Intent(this, Dashboard.class);

            intent.putExtra("MSGUSUARIO", x);

            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Incorrect credentials", Toast.LENGTH_SHORT).show();
        }

    }


    public void botonClicHere(View view) {
        Intent registro = new Intent(this, Register.class);
        startActivity(registro);
    }
}
