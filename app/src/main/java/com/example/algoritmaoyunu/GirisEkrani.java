package com.example.algoritmaoyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GirisEkrani extends AppCompatActivity {

    private Button buttonBasla;
    private Button buttonNasilOynanir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_ekrani);

        buttonNasilOynanir = findViewById(R.id.buttonNasilOynanir);
        buttonBasla = findViewById(R.id.buttonBasla);

        buttonBasla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(GirisEkrani.this, MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        buttonNasilOynanir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GirisEkrani.this, ActivityNasilOynanir.class));

            }
        });


    }
}