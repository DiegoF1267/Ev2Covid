package cl.inacap.covidexamenes.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cl.inacap.covidexamenes.R;
import cl.inacap.covidexamenes.dto.Pacientes;

public class PacientesArrayAdapter extends ArrayAdapter<Pacientes> {
    private Activity activity;
    private List<Pacientes> pacientes;

    public PacientesArrayAdapter(@NonNull Activity context, int resource, @NonNull List<Pacientes> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.pacientes = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = this.activity.getLayoutInflater();
        View fila = inflater.inflate(R.layout.pacientes_list, null, true);
        TextView nombreTv = fila.findViewById(R.id.nombre_pc_lv);
        TextView apellidoTv = fila.findViewById(R.id.apellido_pc_lv);
        TextView rutTv = fila.findViewById(R.id.rut_pc_lv);
        TextView fechaTv = fila.findViewById(R.id.fecha_pc_lv);
        Pacientes actual = pacientes.get(position);
        nombreTv.setText(actual.getNombre());
        apellidoTv.setText(actual.getApellido());
        rutTv.setText(actual.getRut());
        fechaTv.setText(actual.getFecha());
        return fila;
    }
}
