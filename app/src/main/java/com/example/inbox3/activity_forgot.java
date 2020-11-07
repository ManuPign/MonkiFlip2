package com.example.inbox3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
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

public class activity_forgot extends AppCompatActivity {

    private Context ctx;
    private SqliteHelper SqliteHelper;
    private SQLiteDatabase BASE1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        this.ctx = getApplicationContext();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ImageView imagenForgot = findViewById(R.id.imagenForgot);
        TextView textoForgot = findViewById(R.id.textoForgot);
        final EditText idForgot = findViewById(R.id.idForgot);
        Button botonForgot = findViewById(R.id.botonForgot);
        Button atrasForgot = findViewById(R.id.atrasForgot);

        Picasso.get().load("https://i.pinimg.com/564x/81/67/1b/81671b2b7e6d3da41b70fec53f79c04b.jpg").into(imagenForgot);

        atrasForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        botonForgot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    String idRecuperado = idForgot.getText().toString();

                SqliteHelper = new SqliteHelper(ctx);
                BASE1 = SqliteHelper.getReadableDatabase();
                String[] argumentos = {idRecuperado};
                Cursor cursor = BASE1.rawQuery("SELECT * FROM Usuarios WHERE id =?", argumentos);

                if (cursor != null){
                    cursor.moveToFirst();
                    String a = cursor.getString(cursor.getColumnIndex("usuario"));
                    String b = cursor.getString(cursor.getColumnIndex("contrasenia"));
                    String c = "Usuario: " + a + " Contrasenia: " + b;
                    Toast.makeText(ctx, c , Toast.LENGTH_LONG).show();
                    BASE1.close();
                } else {
                    Toast.makeText(ctx, "ID inexistente", Toast.LENGTH_LONG).show();
                }

                // Do something in response to button click
            }
        });

    }
}
