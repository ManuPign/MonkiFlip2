package com.example.inbox3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

public class LoginActivity extends AppCompatActivity {


    public static android.content.SharedPreferences SharedPreferences = null;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor = sharedPreferences.edit();



        String correorecordado = sharedPreferences.getString("correo","");
        String contraseniarecordada = sharedPreferences.getString("contrasenia","");









        ImageView logo = findViewById(R.id.logo);

            final EditText correo = findViewById(R.id.correo);
            final EditText contrasenia = findViewById(R.id.contrasenia);
            final CheckBox recordar = findViewById(R.id.recordar);
            final Button login = findViewById(R.id.login);
            correo.setText(correorecordado);
            contrasenia.setText(contraseniarecordada);


        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                String correoinsertado = correo.getText().toString();
                String contraseniainsertada = contrasenia.getText().toString();
                if (correoinsertado.equals("cuchifo@gmail.com") && contraseniainsertada.equals("asrambuti1")){
                    Toast toast2 = Toast.makeText(getApplicationContext(), "Bienvenido!", Toast.LENGTH_SHORT);
                    toast2.show();



                    if (recordar.isChecked()){

                        editor.putString("correo", correoinsertado);
                        editor.putString("contrasenia", contraseniainsertada);
                        editor.apply();
                    } else if (!recordar.isChecked()){
                        editor.clear();
                        editor.apply();
                    }

                    Intent i = new Intent (LoginActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Correo o contrasenia incorrectos", Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }
        });




        Picasso.get().load("https://i.imgur.com/c3sBK45.png").into(logo);


    }
}
