package cl.inacap.covidexamenes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import cl.inacap.covidexamenes.adapters.PacientesArrayAdapter;
import cl.inacap.covidexamenes.dao.PacientesDAO;
import cl.inacap.covidexamenes.dto.Pacientes;

public class MainActivity extends AppCompatActivity {
    private ListView pacientesLv;
    private List<Pacientes> pacientes;
    private PacientesArrayAdapter adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        adaptador = new PacientesArrayAdapter(this,R.layout.pacientes_list, pacientes);
        pacientesLv = findViewById(R.id.pacientes_lv);
        pacientesLv.setAdapter(adaptador);
    }
}