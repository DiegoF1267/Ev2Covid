package cl.inacap.covidexamenes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cl.inacap.covidexamenes.dto.Pacientes;

public class VerPacientesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView nombreTxt;
    private TextView apellidoTxt;
    private TextView rutTxt;
    private TextView fechaTxt;
    private TextView areaTxt;
    private TextView covidTxt;
    private TextView temperaturaDou;
    private TextView tosTxt;
    private TextView presionInt;



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pacientes);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.toolbar=findViewById(R.id.toolbar);
        this.nombreTxt=findViewById(R.id.nombrePaTxt);
        this.apellidoTxt=findViewById(R.id.apellidoPaTxt);
        this.temperaturaDou=findViewById(R.id.temperaturaPaDou);
        this.presionInt=findViewById(R.id.presionPaint);
        this.rutTxt=findViewById(R.id.rutPaTxt);
        this.fechaTxt=findViewById(R.id.fechaPaTxt);
        this.areaTxt=findViewById(R.id.areaPaTxt);
        this.covidTxt=findViewById(R.id.covidPaTxt);
        this.tosTxt=findViewById(R.id.tosPaTxt);





        this.setSupportActionBar(this.toolbar);
        if(getIntent() !=null){
            Pacientes pacientes =(Pacientes) getIntent().getSerializableExtra("paciente");
            this.nombreTxt.setText(pacientes.getNombre());
            this.apellidoTxt.setText(pacientes.getApellido());
            this.temperaturaDou.setText(pacientes.getTemperatura()+" ° ");
            this.presionInt.setText(pacientes.getPresion()+"");
            this.rutTxt.setText(pacientes.getRut());
            this.fechaTxt.setText(pacientes.getFecha());
            this.areaTxt.setText(pacientes.getArea());
            this.covidTxt.setText(pacientes.getsCovid());
            this.tosTxt.setText(pacientes.getTos());
        }
    }
}