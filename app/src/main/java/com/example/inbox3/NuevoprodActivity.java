package com.example.inbox3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class NuevoprodActivity extends AppCompatActivity {

    private Context ctx;
    private SqliteHelper sqliteHelper;
    private SQLiteDatabase BASE1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevoprod);
        this.ctx = getApplicationContext();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        final EditText nuevoprodnombre = findViewById(R.id.nuevoprodnombre);
        final EditText nuevoprodprecio = findViewById(R.id.nuevoprodprecio);
        final EditText nuevoproddescripcion = findViewById(R.id.nuevoproddescripcion);
        final Spinner categoria = findViewById(R.id.nuevoprodcategoria);
        final EditText nuevoprodenlace = findViewById(R.id.nuevoprodenlace);
        ImageView nuevoprodimagen = findViewById(R.id.nuevoprodimagen);
        TextView nuevoprod = findViewById(R.id.nuevoprod);
        Button nuevoprodatras = findViewById(R.id.nuevoprodatras);
        Button nuevoprodboton = findViewById(R.id.nuevoprodboton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categorias, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categoria.setAdapter(adapter);

        nuevoprodatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nuevoprodboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String nombre = nuevoprodnombre.getText().toString();
                String precio = nuevoprodprecio.getText().toString();
                String spinner = categoria.getSelectedItem().toString();
                String enlaceimagen = nuevoprodenlace.getText().toString();
                String nuevoprodescripcion = nuevoproddescripcion.getText().toString();

                nuevoProducto(nombre, spinner, precio, nuevoprodescripcion, enlaceimagen);
            }
        });



        Picasso.get().load("https://i.ytimg.com/vi/TanVUgneNo8/hqdefault.jpg").into(nuevoprodimagen);



    }

    public void nuevoProducto(String nombreproducto, String categoriaproducto, String precioproducto, String descripcionproducto, String enlaceimagen){
        sqliteHelper = new SqliteHelper(ctx);
        BASE1 = sqliteHelper.getWritableDatabase();
        ContentValues producto = new ContentValues();
        producto.put("remitente", nombreproducto);
        producto.put("asunto", precioproducto);
        producto.put("contenido", descripcionproducto);
        producto.put("enlaceImagen", enlaceimagen);
        producto.put("categoria", categoriaproducto);

        BASE1.insert("Publicaciones",null, producto);
        Toast toast1 = Toast.makeText(ctx, "Publicacion cargada. Refresque la pagina.", Toast.LENGTH_SHORT);
        toast1.show();



        BASE1.close();
        finish();



    }



}
