package com.example.inbox3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class RegisterActivity extends AppCompatActivity {


    private SqliteHelper sqliteHelper;
    private SQLiteDatabase BASE1;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.ctx = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ImageView imagenregister = findViewById(R.id.imagenregister);
        final EditText usuarioregister = findViewById(R.id.usuarioregister);
        final EditText contraseniaregister = findViewById(R.id.contraseniaregister);
        Button forgotRegister = findViewById(R.id.forgotRegister);
        Button atrasregister = findViewById(R.id.atrasregister);
        Button botonregistrarse = findViewById(R.id.botonregistrarse);
        TextView bienvenida1 = findViewById(R.id.bienvenida1);
        TextView bienvenida2 = findViewById(R.id.bienvenida2);







        atrasregister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                // Do something in response to button click
            }
        });

        forgotRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, activity_forgot.class);
                startActivity(i);

                // Do something in response to button click
            }
        });

        botonregistrarse.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                String usuarioins = usuarioregister.getText().toString();
                String contraseniains = contraseniaregister.getText().toString();
                registrarUsuario(usuarioins, contraseniains);

            }
        });

        Picasso.get().load("https://i.kym-cdn.com/entries/icons/medium/000/034/479/cover6.jpg").into(imagenregister);


    }

    private void registrarUsuario(String nombre, String contrasenia){
        sqliteHelper = new SqliteHelper(this.ctx);
        BASE1 = sqliteHelper.getWritableDatabase();

        ContentValues credenciales = new ContentValues();
        credenciales.put("usuario", nombre);
        credenciales.put("contrasenia", contrasenia);

        BASE1.insert("Usuarios",null,credenciales);
        Toast toast1 = Toast.makeText(getApplicationContext(), "Usuario registrado con exito", Toast.LENGTH_SHORT);
        toast1.show();

        BASE1.close();
        finish();
    }


}
