package com.utn.android.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.utn.android.entidades.Persona;

import java.util.ArrayList;

public class PersonaDAO implements IDatos<Persona>{

   private AdminSQLiteOpenHelper admin;

    public PersonaDAO(Context context) {
        this.admin = new AdminSQLiteOpenHelper(context,"contactos", null, 1);
    }

    @Override
    public boolean insert(Persona obj) {
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
        long resultado = BaseDeDatos.insert("personas", null, registro);
        BaseDeDatos.close();
        return resultado != -1;
    }
    @Override
    public ArrayList getAll() {
        ArrayList<Persona> listaContactos = new ArrayList<Persona>();
        Cursor mcursor = null;

        String[] columnas = {"id", "nombre", "apellido", "telefono", "tipoTelefono",
                             "email", "tipoEmail", "direccion", "fechaNacimiento",
                             "nivelEstudios", "intereses", "recibeInfo"};

        SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();
        mcursor = BaseDeDatos.query("personas", columnas, null, null, null, null, null);

        if(mcursor.moveToFirst()){
            do {
                Persona persona = new Persona();
                persona.setId(mcursor.getInt(0));
                persona.setNombre(mcursor.getString(1));
                persona.setApellido(mcursor.getString(2));
                persona.setTelefono(mcursor.getString(3));
                persona.setTipoTelefono(mcursor.getString(4));
                persona.setEmail(mcursor.getString(5));
                persona.setTipoEmail(mcursor.getString(6));
                persona.setDireccion(mcursor.getString(7));
                persona.setFechaNacimiento(mcursor.getString(8));
                persona.setNivelEstudios(mcursor.getString(9));
                persona.setIntereses(mcursor.getString(10));
                persona.setRecibeInfo(mcursor.getInt(11) == 1);
                listaContactos.add(persona);
            }while(mcursor.moveToNext());
            mcursor.close();
        }
        BaseDeDatos.close();
        return listaContactos;
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
