package com.example.projetogerenfacil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class NextActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next); // Certifique-se de criar o layout para esta atividade

        Button botaoVoltar = findViewById(R.id.btnVoltar_Next);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                Intent voltarMain = new Intent(NextActivity.this, MainActivity.class);
                startActivity(voltarMain);
          }
        });
    }
}
