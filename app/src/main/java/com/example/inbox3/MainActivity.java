package com.example.inbox3;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;



import com.example.inbox3.models.Mensaje;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView casillaCorreo;
    private AdaptadorMensaje adaptador;
    private List<Mensaje> listaMensaje;
    private Context ctx;
    private SqliteHelper sqliteHelper;
    private SQLiteDatabase BASE1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.ctx = getApplicationContext();
        casillaCorreo = findViewById(R.id.casillaCorreo);
        Button botondelog = findViewById(R.id.button);
        Button nuevopost = findViewById(R.id.button2);
        ImageView monkeflip = findViewById(R.id.about);
        Button actualizar = findViewById(R.id.button3);
        Picasso.get().load("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/27dbbda7-320b-488b-b8cb-6d993296f095/dd8pi7n-536567bc-376b-4fe8-9747-21d2a26e0f9f.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOiIsImlzcyI6InVybjphcHA6Iiwib2JqIjpbW3sicGF0aCI6IlwvZlwvMjdkYmJkYTctMzIwYi00ODhiLWI4Y2ItNmQ5OTMyOTZmMDk1XC9kZDhwaTduLTUzNjU2N2JjLTM3NmItNGZlOC05NzQ3LTIxZDJhMjZlMGY5Zi5wbmcifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6ZmlsZS5kb3dubG9hZCJdfQ.W9xZHquYBuyVllogXu3-G9ZC8umh2CkOJSb_IfFymBg").into(monkeflip);

        botondelog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nuevopost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NuevoprodActivity.class);
                startActivity(i);
            }
        });


        monkeflip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(i);
            }
        });


        listaMensaje = new ArrayList<>();



        cargarSqlite();



        adaptador = new AdaptadorMensaje(listaMensaje);
        casillaCorreo.setAdapter(adaptador);

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarSqlite();
            }
        });

        casillaCorreo.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Mensaje mensaje = adaptador.getItem(i);
                Intent intent = new Intent(MainActivity.this, Detalle.class);
                intent.putExtra("remitente", mensaje.getRemitente());
                intent.putExtra("asunto", mensaje.getAsunto());
                intent.putExtra("contenido", mensaje.getContenido());
                intent.putExtra("correo", mensaje.getCategoria());
                intent.putExtra("imagen", mensaje.getEnlaceImagen());
                startActivity(intent);

            }
            });



        }

        private void cargarSqlite(){
            sqliteHelper = new SqliteHelper(this.ctx);
            BASE1 = sqliteHelper.getReadableDatabase();
            listaMensaje.clear();
            @SuppressLint("Recycle") Cursor cursor = BASE1.rawQuery("SELECT * FROM Publicaciones",null);


                //iteramos todos los registros del cursor y llenamos array con registros

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Mensaje item = new Mensaje();
                        //recorremos hasta llegar al ultimo registro
                        item.setId(cursor.getInt(cursor.getColumnIndex("id")));
                        item.setRemitente(cursor.getString(cursor.getColumnIndex("remitente")));
                        item.setCategoria(cursor.getString(cursor.getColumnIndex("categoria")));
                        item.setAsunto(cursor.getString(cursor.getColumnIndex("asunto")));
                        item.setContenido(cursor.getString(cursor.getColumnIndex("contenido")));
                        item.setEnlaceImagen(cursor.getString(cursor.getColumnIndex("enlaceImagen")));

                        listaMensaje.add(item);
                    } while (cursor.moveToNext());
                }
            }





                    // corremos a siguiente posición del curso


            // cerramos conexion SQLite
            BASE1.close();

        }



        private void cargarHardcode(){
            listaMensaje.add(new Mensaje(1, "Mani por kilo", "Alimentos", "$150", "Mani de huerta libre de fertilizantes quimicos y pesticidas. Mani salado 20 pesos extra por kilo. Envio a todo el pais", "https://http2.mlstatic.com/D_NQ_NP_877277-MLA31116283067_062019-O.jpg"));
            listaMensaje.add(new Mensaje(2, "Mancuernas 5, 10, 15kg", "Ejercicio", "$500-1000", "Mancuernas de metal macizo en presentaciones de 5 ($500), 10 ($800) y 15kg ($1000). El precio es unitario. No hacemos envios debido al peso del producto. Retiro por nuestra casa en Parque Patricios", "https://mirfitness.com.ar/wp-content/uploads/4524.jpg"));
            listaMensaje.add(new Mensaje(3, "Intel i5 7400 3.0GHz", "Computacion", "$10000", "I5 7400 nunca overclock ni altas temperaturas. Seis meses de uso con un Coolermaster Hyper 212. Hago envios a todo el pais y acepto Monkeypago con recargo", "https://http2.mlstatic.com/D_NQ_NP_679982-MLA31035552470_062019-O.jpg"));
            listaMensaje.add(new Mensaje(4, "Terreno en Toay", "Inmuebles", "A discutir", "Terreno lindante a avda. 13 de caballeria. 30 metros de frente por 60 de fondo. Cubierto por servicio de electricidad, gas y cloaca. Calle recientemente asfaltada. Zona en desarrollo. Escucho ofertas de otros inmuebles o auto 0km", "https://fotos.inmobusqueda.com/720/299161/299161_720_5a29b5.jpg"));
            listaMensaje.add(new Mensaje(5, "Bicicleta fija Olmo", "Ejercicio", "$15000", "Bicicleta fija OLMO con medidos de kilometros, calorias y velocidad y ajustador de intensidad. Un año y medio de uso. No hago envios, se retira por Constitucion", "https://www.hfitness.com.ar/img/articulos/cod_754_2.jpg?img=directorio"));
            listaMensaje.add(new Mensaje(6, "Departamento costero Mar de Ajo", "Inmuebles", "USD300000", "Inmobiliara Albucer vende departamento con vista al mar en Mar de Ajó. Dos dormitorios, dos baños, cocina, comedor, living y espacioso balcon. 60m cuadrados. El inmueble se ubica en el quinto piso del edificio Costa China", "https://deplanosycasas.com/wp-content/uploads/2016/01/Plano-de-departamento-de-65-m2-1280x720.jpg"));
            listaMensaje.add(new Mensaje(7, "Alfajores Jorgito chocolate, merengue, fruta x Caja", "Alimentos", "$550", "Cajas alfajores jorgito de cualquier variedad. Se pueden combinar cajas. Promocion 10 cajas x $5000. Envios a todo el pais", "https://k31.kn3.net/taringa/3/3/4/2/1/6/4/mlabs/4CF.jpg?3388"));
            listaMensaje.add(new Mensaje(8, "Kit bandas elasticas de resistencia", "Ejercicio", "$1200", "Bandas de resistencia 25 kg de fuerza. El kit incluye banda larga para pierna, banda circular y banda en bucle para brazos. Ideal para ejercicios en casa. Envios a todo el pais y garantia de dos meses", "https://cdni.onedayonly.co.za/catalog/product/r/e/resistance-band-set-11-piece-3_1.jpg?auto=compress&bg=fff&fit=fill&h=800&w=800"));
            listaMensaje.add(new Mensaje (9, "NVIDIA Gforce GTX1080ti Founders Edition", "Computacion", "$60000", "Tres meses de uso. Se limpio y se hizo cambio de pasta termica luego de ser retirada de la computadora. Retiro por caballito o envios a todo el pais por via cargo", "https://i.ytimg.com/vi/riggkNqTlWU/maxresdefault.jpg" ));
            listaMensaje.add(new Mensaje(10,"Renault Twingo modelo 02","Vehiculos", "$175000", "Sin revisar, pero funciona sin problemas. El precio queda sujeto a la revision de un mecanico imparcial. Completo de papeles, tercer dueño, sin problemas de chapa o pintura. 23000 kilometros", "https://upload.wikimedia.org/wikipedia/commons/f/fb/Renault_Twingo.jpg"));
            listaMensaje.add(new Mensaje(11,"Armonica Hohner special 20 marine band ","Instrumentos", "$3500", "Armonica gama media en escala DO. Incluye estuche, manual de uso y un codigo para lecciones online gratis en la pagina del fabricante. Ideal para regalos. Envios a todo el pais. Somos NOTAS musica. Encontranos en nuestro local de la Boca", "https://http2.mlstatic.com/D_NQ_NP_695110-MLA31068572987_062019-O.jpg"));
            listaMensaje.add(new Mensaje(12,"Unidad solida ADATA 120gb","Computacion", "$2800", "Unidad solida ADATA 120GB. COLOR NEGRO, FACTOR 2.5MM. PARA PC O NOTEBOOK. INTERFAZ SATA 3", "https://www.precio-calidad.com.ar/wp-content/uploads/2018/11/692104-MLA26472573470_122017-F-4-800x800.jpg"));
            listaMensaje.add(new Mensaje(13,"Seasonic M12II 520W","Computacion", "$22000", "FUENTE PC 520W 80 PLUS BRONZE SEASONIC, MARCA LIDER EN CALIDAD. Completamente modular. Cuatro conectores PCI-E, ocho conectores SATA. Tamanio 120mm", "https://wancomputers.com.ar/img/p/928-5928-thickbox.jpg"));
            listaMensaje.add(new Mensaje(14,"Flauta dulce para niños","Instrumentos", "$500", "Flauta dulce educativa con funda para que los chicos den su primer paso en la musica! Material plastico, sin piezas pequeñas. Envios a todo el pais. Somos NOTAS musica. Encontranos en nuestro local de la Boca", "https://www.librerialanena.com.ar/image/cache/catalog/Productos/Escolar/otros/Flauta-Dulce-Comun-1000x1000.jpg"));
            listaMensaje.add(new Mensaje(15,"VW New Beetle modelo 2005","Vehiculos", "USD6000", "Concesionaria el chancho vende Volkswagen new beetle 2005 un solo dueño, 13000 kilometros, revision y service al dia en perfecto estado de chapa y pintura. Se puede pasar a ver a nuestro showroom de Flores", "https://clasicdn.paraguay.com/pictures/2014/10/09/556697/1433482L.jpg"));
            listaMensaje.add(new Mensaje(16,"Alfombra yoga enrollable","Ejercicio", "$2199", "Colchoneta Mat para Yoga, pilates, gimnasia, abdominales o una salida al aire libre. Tamaño 1.8m x 0.6m", "https://http2.mlstatic.com/D_NQ_NP_854455-MLA42163942350_062020-O.webp"));
            listaMensaje.add(new Mensaje(17,"Corolla AE86 modelo 85","Vehiculos", "USD10000", "AE86 modelo 85 sprinter trueno. Service y mecanico recien hecho, capa de pintura original restaurada, chapa y pintura en perfecto estado. 50500 kilómetros hasta principios de 2020. Se puede pasar a ver por Palermo, ofertar para establecer contacto", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Toyota_SPRINTER_TRUENO_3door_%28AE86%29_front.JPG/1200px-Toyota_SPRINTER_TRUENO_3door_%28AE86%29_front.JPG"));
            listaMensaje.add(new Mensaje(18,"Bajo SX Jazz para zurdos + funda + accesorios","Instrumentos", "$25000", "INCLUYE: SX JAZZ BASS, FUNDA, CABLE, CORREA, AFINADOR /n CUERPO BASSWOOD, TRASTES NICKEL, MICROFONO SINGLE COIL, CONTROLES 2V", "https://http2.mlstatic.com/D_817586-MLA42860203281_072020-O.jpg"));
        }

    }

