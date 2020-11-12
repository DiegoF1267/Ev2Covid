package cl.inacap.covidexamenes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cl.inacap.covidexamenes.dao.PacientesDAO;
import cl.inacap.covidexamenes.dao.PacientesDAOSQLite;
import cl.inacap.covidexamenes.dto.Pacientes;

public class CuartoActivity extends AppCompatActivity {

    Spinner opciones;
    Switch switchE;
    Switch switchO;
    private EditText rutTxt;
    private EditText nombreTxt;
    private EditText apellidoTxt;
    private TextView fechaTxt;
    private Calendar fechaCalendario = Calendar.getInstance();
    private Spinner areaSpn;
    private Switch covidSwt;
    private EditText temperaturaTxt;
    private Switch tosSwt;
    private Toolbar toolbar;
    private EditText presionTxt;
    private Button agregarBtn;
    private PacientesDAO pacDAO = new PacientesDAOSQLite(this);

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
        this.fechaTxt = findViewById(R.id.idDatePicker);
        this.rutTxt = findViewById(R.id.rut);
        this.nombreTxt = findViewById(R.id.nombre);
        this.apellidoTxt = findViewById(R.id.apellido);
        this.areaSpn = findViewById(R.id.idSpinner);
        this.tosSwt = findViewById(R.id.idSwitch2);
        this.covidSwt = findViewById(R.id.idSwitch);
        this.temperaturaTxt = findViewById(R.id.idTemp);
        this.presionTxt = findViewById(R.id.idPresion);
        this.agregarBtn = findViewById(R.id.agregar_btn);

        this.agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> errores = new ArrayList<>();
                String rutStr = rutTxt.getText().toString().trim();
                String nombreStr = nombreTxt.getText().toString().trim();
                String apellidoStr = apellidoTxt.getText().toString().trim();
                String fechaStr = "";
                String areaTrabajoStr = areaSpn.getSelectedItem().toString();
                String temperaturaStr = temperaturaTxt.getText().toString().trim();
                String sintomasCovidStr = "";
                String presionArterialStr = presionTxt.getText().toString().trim();

                    Pacientes p = new Pacientes();
                    p.setRut(rutStr);
                    p.setNombre(nombreStr);
                    p.setApellido(apellidoStr);
                    p.setFecha(fechaStr);
                    p.setArea(areaTrabajoStr);
                    p.setsCovid(sintomasCovidStr);
                    //p.setTemperatura("");
                    p.setTos("");
                    //p.setPresion("");
                    pacDAO.save(p);
                    startActivity(new Intent(CuartoActivity.this, SegundoActivity.class));

            }
        });

        opciones = (Spinner) findViewById(R.id.idSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);


    }

    public void abrirCalendario(View view) {
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(CuartoActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fechas = dayOfMonth + "/" + month + "/" + year;
                fechaTxt.setText(fechas);
            }
        }, anio, mes, dia);
        dpd.show();
    }

    public void onclick(View view) {
        if (view.getId() == R.id.idSwitch) {


        }
    }


    public void onclick2(View view) {
        if (view.getId() == R.id.idSwitch) {

        }
    }

}