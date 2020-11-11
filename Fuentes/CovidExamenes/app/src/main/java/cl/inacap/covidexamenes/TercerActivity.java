package cl.inacap.covidexamenes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TercerActivity extends AppCompatActivity {

    private Button segundoBtn;
    private Button cuartoBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercer);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));


        segundoBtn= findViewById(R.id.segundo_btn);
        segundoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TercerActivity.this,SegundoActivity.class);
                startActivity(i);
            }
        });
        cuartoBtn = findViewById(R.id.cuarto_btn);
        cuartoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TercerActivity.this,CuartoActivity.class);
                startActivity(i);
            }
        });

    }
}