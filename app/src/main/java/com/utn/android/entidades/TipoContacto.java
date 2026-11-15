package com.utn.android.entidades;

public class TipoContacto {
    int id;
    String descripcion;

    public TipoContacto(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        //return "TipoContacto{" +  "descripcion='" + descripcion + '\'' + '}';
        return descripcion;
    }
}
