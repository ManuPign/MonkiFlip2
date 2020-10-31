package com.example.inbox3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    private Context ctx;
    private SqliteHelper sqliteHelper;
    private SQLiteDatabase BASE1;


    public static android.content.SharedPreferences SharedPreferences = null;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        this.ctx = getApplicationContext();
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
            final Button registrarse = findViewById(R.id.registrarse);
            correo.setText(correorecordado);
            contrasenia.setText(contraseniarecordada);

        registrarse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                // Do something in response to button click
            }
        });




        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                String correoinsertado = correo.getText().toString();
                String contraseniainsertada = contrasenia.getText().toString();
                if (recordar.isChecked()){

                    editor.putString("correo", correoinsertado);
                    editor.putString("contrasenia", contraseniainsertada);
                    editor.apply();
                } else if (!recordar.isChecked()){
                    editor.clear();
                    editor.apply();
                }

                String password = autenticar(correoinsertado, contraseniainsertada);

                switch(password){
                    case "true":
                        Toast toast2 = Toast.makeText(getApplicationContext(), "Bienvenido!", Toast.LENGTH_SHORT);
                        toast2.show();
                        Intent i = new Intent (LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case "inactivo":
                        Toast toast3 = Toast.makeText(getApplicationContext(), "Usuario inactivo", Toast.LENGTH_SHORT);
                        toast3.show();
                        break;
                    case "inexistente":
                        Toast toast4 = Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT);
                        toast4.show();
                        break;
                    default:
                        Toast toast5 = Toast.makeText(getApplicationContext(), "Error de sistema", Toast.LENGTH_SHORT);
                        toast5.show();

                }

               /* if (password){
                    Toast toast2 = Toast.makeText(getApplicationContext(), "Bienvenido!", Toast.LENGTH_SHORT);
                    toast2.show();
                    Intent i = new Intent (LoginActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Correo o contrasenia incorrectos", Toast.LENGTH_SHORT);
                    toast1.show();
                }*/



               /* if (correoinsertado.equals("cuchifo") && contraseniainsertada.equals("asrambuti1")){
                    Toast toast2 = Toast.makeText(getApplicationContext(), "Bienvenido!", Toast.LENGTH_SHORT);
                    toast2.show();





                    Intent i = new Intent (LoginActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Correo o contrasenia incorrectos", Toast.LENGTH_SHORT);
                    toast1.show();
                } */
            }


        })



        ;




        Picasso.get().load("https://i.imgur.com/c3sBK45.png").into(logo);


    }

    private String autenticar(String usuario, String contrasenia){
        sqliteHelper = new SqliteHelper(this.ctx);
        BASE1 = sqliteHelper.getReadableDatabase();

        Cursor cursor = BASE1.rawQuery("SELECT * FROM Usuarios",null);

        if(cursor.moveToFirst()) {
            //iteramos todos los registros del cursor y llenamos array con registros



            while (cursor.isAfterLast() == false) {

                String a = cursor.getString(cursor.getColumnIndex("usuario"));
                String b = cursor.getString(cursor.getColumnIndex("contrasenia"));
                if(usuario.equals(a) && contrasenia.equals(b)){
                    if (cursor.getInt(cursor.getColumnIndex("activo")) == 1){
                        return "true";
                    } else return "inactivo";

                }
                // corremos a siguiente posición del curso
                cursor.moveToNext();
            }


        }else{
            Toast.makeText(ctx, "No hay registros", Toast.LENGTH_SHORT).show();

        }

        // cerramos conexion SQLite
        BASE1.close();
        return "inexistente";

    }


}
