package cl.inacap.covidexamenes.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PacientesSQLiteHelpers extends SQLiteOpenHelper {

    private final String sqlCreate="CREATE TABLE " +
            "pacientes(rut TEXT NOT NULL PRIMARY KEY " +
            ",nombre TEXT" +
            ",apellido TEXT" +
            ",fecha TEXT" +
            ",area TEXT" +
            ",covid TEXT" +
            ",temperatura REAL" +
            ",tos TEXT" +
            ",presion INTEGER)";

    public PacientesSQLiteHelpers(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pacientes");
        sqLiteDatabase.execSQL(sqlCreate);

    }
}