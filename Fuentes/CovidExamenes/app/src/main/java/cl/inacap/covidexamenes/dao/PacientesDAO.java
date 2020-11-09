package cl.inacap.covidexamenes.dao;

import java.util.List;

import cl.inacap.covidexamenes.dto.Pacientes;

public interface PacientesDAO {
    List<Pacientes>getAll();
    Pacientes save(Pacientes p);

}
