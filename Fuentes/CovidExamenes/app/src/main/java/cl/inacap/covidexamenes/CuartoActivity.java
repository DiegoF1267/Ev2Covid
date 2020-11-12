package cl.inacap.covidexamenes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

public class CuartoActivity extends AppCompatActivity {

     Switch switchE;
     Switch switchO;
    private TextView fecha;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuarto);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.fecha = findViewById(R.id.idTextViewfecha);

    }

    public void abrirCalendario(View view) {
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(CuartoActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fechas= dayOfMonth + "/" + month + "/" + year;
                fecha.setText(fechas);
            }
        }, anio, mes, dia);
        dpd.show();
    }

    public void onclick (View view){
        if (view.getId()==R.id.idSwitch){


            }
        }



    public void onclick2 (View view){
        if (view.getId()==R.id.idSwitch){

        }
    }

}