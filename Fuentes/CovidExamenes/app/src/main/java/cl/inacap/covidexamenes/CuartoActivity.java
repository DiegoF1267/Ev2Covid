package cl.inacap.covidexamenes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.inacap.covidexamenes.dao.PacientesDAO;
import cl.inacap.covidexamenes.dao.PacientesDAOSQLite;
import cl.inacap.covidexamenes.dto.Pacientes;

public class CuartoActivity extends AppCompatActivity {

    Spinner opciones;
    Switch switchE;
    Switch switchO;

    private Button fechaTxt;
    private EditText rutPacienteTxt;
    private EditText nombreTxt;
    private EditText apellidoTxt;
    private Calendar calendario = Calendar.getInstance();
    private Spinner spinnerAreaTrabajo;
    private Switch switchSintomasCovid;
    private EditText temperaturaTxt;
    private Switch switchPresentaTos;
    private Toolbar toolbar;
    private EditText presionArterialTxt;
    private Button agregarPacienteBtn;
    private PacientesDAO pDAO = new PacientesDAOSQLite(this);

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuarto);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.fechaTxt = findViewById(R.id.fechaid);
        this.rutPacienteTxt = findViewById(R.id.rut);
        this.nombreTxt = findViewById(R.id.nombre);
        this.apellidoTxt = findViewById(R.id.apellido);
        this.spinnerAreaTrabajo = findViewById(R.id.idSpinner);
        this.switchPresentaTos = findViewById(R.id.idSwitch2);
        this.switchSintomasCovid = findViewById(R.id.idSwitch);
        this.temperaturaTxt = findViewById(R.id.idTemp);
        this.presionArterialTxt = findViewById(R.id.idPresion);
        this.agregarPacienteBtn = findViewById(R.id.agregar_btn);

        opciones = (Spinner) findViewById(R.id.idSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);


        this.agregarPacienteBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                List<String> errores = new ArrayList<>();
                String rutStr = rutPacienteTxt.getText().toString().trim();
                String nombreStr = nombreTxt.getText().toString().trim();
                String apellidoStr = apellidoTxt.getText().toString().trim();
                String fechaStr = "";
                String areaTrabajoStr = spinnerAreaTrabajo.getSelectedItem().toString();
                String temperaturaStr = temperaturaTxt.getText().toString().trim();
                String sintomasCovidStr = "";
                String presionArterialStr = presionArterialTxt.getText().toString().trim();
                if (switchSintomasCovid.isChecked()) {
                    sintomasCovidStr = "Sí";
                } else {
                    sintomasCovidStr = "No";
                }
                double temperaturaDou = 0;
                if (temperaturaStr.isEmpty()) {
                    errores.add("Debe Ingresar Una Temperatura");
                } else {
                    temperaturaDou = Double.parseDouble(temperaturaStr);
                    if (temperaturaDou < 20.1) {
                        errores.add("La teperatura debe ser mayor que 20°");
                    }
                }
                String presentaTosStr = "";
                if (switchPresentaTos.isChecked()) {
                    presentaTosStr = "Sí";
                } else {
                    presentaTosStr = "No";
                }
                int presionArterial = 0;
                if (presionArterialStr.isEmpty()) {
                    errores.add("Debe Ingresar La Presion Arterial");
                } else {
                    presionArterial = Integer.parseInt(presionArterialStr);
                }

                if (verificarRut(rutStr) == false) {
                    errores.add("El Rut No Es Valido");
                }
                if (nombreStr.isEmpty()) {
                    errores.add("Debe Ingresar Un Nombre");
                }
                if (apellidoStr.isEmpty()) {
                    errores.add("Debe Ingresar Un Apellido");
                }


                if (errores.isEmpty()) {
                    Pacientes p = new Pacientes();
                    p.setRut(rutStr);
                    p.setNombre(nombreStr);
                    p.setApellido(apellidoStr);
                    p.setFecha(fechaStr);
                    p.setArea(areaTrabajoStr);
                    p.setsCovid(sintomasCovidStr);
                    p.setTemperatura(temperaturaDou);
                    p.setTos(presentaTosStr);
                    p.setPresion(presionArterial);
                    pDAO.save(p);
                    startActivity(new Intent(CuartoActivity.this, SegundoActivity.class));
                } else {

                }

            }




            public boolean verificarRut(String rut){
                boolean esValido = true;
                if (rut.length() > 8 && rut.length() < 11){
                    try {
                        String [] rutSeparado = rut.split("-");
                        if (rutSeparado.length == 2){
                            int dv=0;
                            try{
                                dv = Integer.parseInt(rutSeparado[1]);
                            }catch (Exception ex){
                                dv=20;
                            }


                            if ((dv > -1 && dv<10) || rutSeparado[1].equalsIgnoreCase("k")){
                                String hayPuntos [] = rutSeparado[0].split("\\.");
                                if (hayPuntos.length==1){
                                    esValido=true;
                                    Integer.parseInt(rutSeparado[0]);
                                }else{
                                    esValido = false;
                                }
                            }else {
                                esValido = false;
                            }
                        }else{
                            esValido = false;
                        }
                    }catch (Exception ex){
                        esValido = false;
                    }

                }else{
                    esValido = false;
                }
                return esValido;
            }


            private void mostrarErrores(List<String> errores){
                //1. Generar una cadena de texto con los errores
                String mensaje="";
                for(String e:errores){
                    mensaje+= "-" + e + "\n";
                }
                //2. Mostrar un mensaje de alerta
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(CuartoActivity.this);
                alertBuilder
                        .setTitle("Error De Validacion")
                        .setMessage(mensaje)
                        .setPositiveButton("Aceptar",null)
                        .create()
                        .show();
            }





        });

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


    public void Onclick(View view) {
        if (view.getId() == R.id.idSwitch) {


        }
    }

    public void onclick2 (View view){
        if (view.getId() == R.id.idSwitch) {

        }


    }
}



