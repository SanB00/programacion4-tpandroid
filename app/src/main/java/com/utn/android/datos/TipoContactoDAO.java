package com.utn.android.datos;

import com.utn.android.entidades.TipoContacto;

import java.util.ArrayList;

public class TipoContactoDAO implements  IDatos<TipoContacto> {
    @Override
    public void insert(TipoContacto obj) {

    }

    @Override
    public ArrayList<TipoContacto> getAll() {
        // borrar cuando se implemente la conexión a la BBDD
        ArrayList<TipoContacto> listaTiposContacto = new ArrayList<>();

        listaTiposContacto.add(new TipoContacto(1, "Casa"));
        listaTiposContacto.add(new TipoContacto(2, "Trabajo"));
        listaTiposContacto.add(new TipoContacto(3, "Móvil"));
        return listaTiposContacto;
    }

    @Override
    public TipoContacto getById(int id) {
        return null;
    }

    @Override
    public void update(TipoContacto obj) {

    }

    @Override
    public void delete(int id) {

    }
}
