package cl.inacap.covidexamenes.dao;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.covidexamenes.dto.Pacientes;

public class PacienteDAOLista implements PacientesDAO {
    private List<Pacientes> pacientes = new ArrayList<>();
    private static PacienteDAOLista instancia;

    private PacienteDAOLista() {
        Pacientes p = new Pacientes();
        p.setNombre("diegolimon");
        p.setApellido("Fernandez");
        p.setArea("otro");
        p.setFecha("20/05/2020");
        p.setPresion(80);
        p.setRut("20321269-0");
        p.setsCovid("si");
        p.setTemperatura(39);
        p.setTos("no");
        pacientes.add(p);
        p = new Pacientes();
        p.setNombre("pene");
        p.setApellido("pene");
        p.setArea("otro");
        p.setFecha("20/05/2020");
        p.setPresion(80);
        p.setRut("212123123-0");
        p.setsCovid("si");
        p.setTemperatura(39);
        p.setTos("no");
        pacientes.add(p);
    }

    public static PacienteDAOLista getInstance() {
        if (instancia == null) {
            instancia = new PacienteDAOLista();
        }
        return instancia;
    }

    @Override
    public List<Pacientes> getAll() {
        return pacientes;
    }

    @Override
    public Pacientes save(Pacientes p) {
        pacientes.add(p);
        return p;
    }
}
