package cl.inacap.covidexamenes.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.covidexamenes.dto.Pacientes;
import cl.inacap.covidexamenes.helpers.PacientesSQLiteHelpers;

public class PacientesDAOSQLite implements PacientesDAO {

    private PacientesSQLiteHelpers  pacHelper;

    public  PacientesDAOSQLite(Context context){
        this.pacHelper= new PacientesSQLiteHelpers(context,"DBPacientes",null,2);
    }

    @Override
    public List<Pacientes> getAll() {
        SQLiteDatabase reader = this.pacHelper.getReadableDatabase();
        List<Pacientes> pacientes= new ArrayList<>();
        try {
            if(reader != null){
                Cursor c = reader.rawQuery("SELECT rut,nombre,apellido,fecha,"+
                        "area,covid,temperatura,tos,presion"+
                        "FROM pacientes",null);
                if (c.moveToFirst()){
                    do{
                        Pacientes p = new Pacientes();
                        p.setRut(c.getString(0));
                        p.setNombre(c.getString(1));
                        p.setApellido(c.getString(2));
                        p.setFecha(c.getString(3));
                        p.setArea(c.getString(4));
                        p.setsCovid(c.getString(5));
                        p.setTemperatura(c.getDouble(6));
                        p.setTos(c.getString(7));
                        p.setPresion(c.getInt(8));
                        pacientes.add(p);

                    }while (c.moveToNext());
                }
                reader.close();
            }
        }catch (Exception ex){
            pacientes=null;
        }
        return pacientes;
    }

    @Override
    public Pacientes save(Pacientes p) {
        SQLiteDatabase writer= this.pacHelper.getWritableDatabase();
        String sql=String.format("INSERT INTO pacientes(rut,nombre,apellido,fecha,area,covid,temperatura,tos,presion)"+
                " VALUES ('%s','%s','%s','%s','%s','%s','%.2f','%s','%d')", p.getRut(), p.getNombre(), p.getApellido()
                ,p.getFecha(),p.getArea(),p.getsCovid(),p.getTemperatura(),p.getTos(),p.getPresion());
        writer.execSQL(sql);
        writer.close();
        return p;
    }
}