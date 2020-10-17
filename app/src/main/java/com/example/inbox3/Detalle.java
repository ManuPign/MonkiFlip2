package com.example.inbox3;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;

public class Detalle extends AppCompatActivity {

    private TextView remitente, asunto, contenido, correo;

    private Button botonAtras;

    Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);


        TextView remitente = findViewById(R.id.remitente);
        TextView asunto = findViewById(R.id.asunto);
        TextView contenido = findViewById(R.id.contenido);
        TextView correo = findViewById(R.id.correo);
        Button botonAtras = findViewById(R.id.botonAtras);
        ImageView avatar2 = findViewById(R.id.avatar2);



        Bundle extras = getIntent().getExtras();






            String remitenteA = extras.getString("remitente");
            String asuntoA = extras.getString("asunto");
            String contenidoA = extras.getString("contenido");
            String correoA = extras.getString("correo");
            String imagen = extras.getString("imagen");




        remitente.setText("Producto: " + remitenteA);
        asunto.setText("Precio: " + asuntoA);
        remitente.setTypeface(boldTypeface);
        asunto.setTypeface(boldTypeface);
        remitente.setTextSize(getResources().getDimension(R.dimen.titlesize));
        asunto.setTextSize(getResources().getDimension(R.dimen.titlesize));

        contenido.setText(contenidoA);
        contenido.setTextSize(getResources().getDimension(R.dimen.contentsize));
        correo.setText(correoA);
        correo.setTextSize(getResources().getDimension(R.dimen.contentsize));
        Picasso.get().load(imagen).into(avatar2);



        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
