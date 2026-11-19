package com.utn.android.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.utn.android.entidades.Persona;

import java.util.ArrayList;

public class PersonaDAO implements IDatos<Persona>{

   private AdminSQLiteOpenHelper admin;

    public PersonaDAO(Context context) {
        this.admin = new AdminSQLiteOpenHelper(context,"contactos", null, 1);
    }

    @Override
    public void insert(Persona obj) {
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("nombre", obj.getNombre());
        registro.put("apellido", obj.getApellido());
        registro.put("telefono", obj.getTelefono());
        registro.put("tipoTelefono", obj.getTipoTelefono());
        registro.put("email", obj.getEmail());
        registro.put("tipoEmail", obj.getTipoEmail());
        registro.put("direccion", obj.getDireccion());
        registro.put("fechaNacimiento", obj.getFechaNacimiento());
        registro.put("nivelEstudios", obj.getNivelEstudios());
        registro.put("intereses", obj.getIntereses());
        registro.put("recibeInfo", obj.isRecibeInfo());

        BaseDeDatos.insert("personas", null, registro);
        BaseDeDatos.close();
    }
    @Override
    public ArrayList getAll() {
        return null;
    }

    @Override
    public Persona getById(int id) {
        return null;
    }

    @Override
    public void update(Persona obj) {

    }

    @Override
    public void delete(int id) {

    }
}
