package com.example.fragmentos;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String votos1 = getIntent().getStringExtra("votos1");
        String votos2 = getIntent().getStringExtra("votos2");

        TextView votos_1= (TextView)findViewById(R.id.txtVotos1);
        TextView votos_2= (TextView)findViewById(R.id.txtVotos2);
        votos_1.setText(votos1);
        votos_2.setText(votos2);

        Button btnRegresar = (Button)findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
