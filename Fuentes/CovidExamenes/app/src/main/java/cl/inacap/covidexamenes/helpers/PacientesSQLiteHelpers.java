package cl.inacap.covidexamenes.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PacientesSQLiteHelpers extends SQLiteOpenHelper {

    private final String sqlCreate="CREATE TABLE "+
            "pacientes(rut TEXT PRIMARY KEY AUTOINCREMENT NOT NULL"+
            ",nombre TEXT"+
            ",apellido TEXT"+
            ",fecha TEXT"+
            ",area TEXT"+
            ",covid TEXT"+
            ",temperatura REAL"+
            ",tos TEXT"+
            ",presion INTEGER)";

    public PacientesSQLiteHelpers(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
        sqLiteDatabase.execSQL("INSERT INTO pacientes (rut,nombre,apellido,fecha,area,covid,temperatura,tos,presion)"+
                " VALUES ('20321269-0','diego','fernandez','15/11/2020','otro','si',37,'si',100)"  );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pacientes");
        sqLiteDatabase.execSQL(sqlCreate);
    }
}