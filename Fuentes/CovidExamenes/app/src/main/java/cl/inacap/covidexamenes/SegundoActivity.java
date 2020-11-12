package cl.inacap.covidexamenes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cl.inacap.covidexamenes.adapters.PacientesArrayAdapter;
import cl.inacap.covidexamenes.dao.PacientesDAO;
import cl.inacap.covidexamenes.dao.PacientesDAOSQLite;
import cl.inacap.covidexamenes.dto.Pacientes;

public class SegundoActivity extends AppCompatActivity {
    private ListView pacientesLv;
    private List<Pacientes> pacientes;
    private PacientesArrayAdapter adaptador;
    private FloatingActionButton fab;
    private PacientesDAO pacientesDAO = new PacientesDAOSQLite(this);
    private Button cuartoBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);
        this.setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        this.pacientesLv=findViewById(R.id.pacientes_lv);
        this.fab=findViewById(R.id.fab);
        pacientes = pacientesDAO.getAll();
        pacientesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SegundoActivity.this, VerPacientesActivity.class);
                Pacientes pacActual = pacientes.get(i);
                intent.putExtra("paciente", pacActual);
                startActivity(intent);
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SegundoActivity.this, CuartoActivity.class);
                startActivity(i);
            }
        });

    }

    protected void onResume() {
        super.onResume();
        pacientes = pacientesDAO.getAll();
        if(pacientes!=null) {
            adaptador = new PacientesArrayAdapter(this, R.layout.pacientes_list, pacientes);
            pacientesLv = findViewById(R.id.pacientes_lv);
            pacientesLv.setAdapter(adaptador);
        }



    }


}