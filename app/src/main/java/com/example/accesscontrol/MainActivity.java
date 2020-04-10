package com.example.accesscontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.accesscontrol.servicios.Servicio;

public class MainActivity extends AppCompatActivity {

    private ImageView logoUnibague;
    public static Servicio ser = new Servicio();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Disable landscape mode, dont forget import library
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        logoUnibague = (ImageView) findViewById(R.id.imgLogo);
        logoUnibague.setImageResource(R.drawable.logo_unibague);
    }

    public void botonContinuar(View view){
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }
}
