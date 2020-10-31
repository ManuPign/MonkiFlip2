package com.example.inbox3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView texto1 = findViewById(R.id.textView1);
        TextView texto2 = findViewById(R.id.textView2);
        TextView texto3 = findViewById(R.id.textView3);
        ImageView imagenabout = findViewById(R.id.imageViewabout);
        Button botonatras = findViewById(R.id.botonatrasabout);

        botonatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Picasso.get().load("https://www.bostonherald.com/wp-content/uploads/2020/05/GettyImages-824860820.jpg").into(imagenabout);

    }
}
