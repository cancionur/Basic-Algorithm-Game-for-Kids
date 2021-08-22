package com.example.algoritmaoyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityNasilOynanir extends AppCompatActivity {

    private Button buttonMenuDon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasil_oynanir);

        buttonMenuDon = findViewById(R.id.buttonMenuDon);

        buttonMenuDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityNasilOynanir.this,GirisEkrani.class));
                finish();
            }
        });

    }
}