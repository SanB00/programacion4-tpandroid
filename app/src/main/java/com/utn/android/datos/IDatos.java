
package com.utn.android.datos;

import java.util.ArrayList;

public interface IDatos<T> {

    void insert(T obj);

    ArrayList<T> getAll();

    T getById(int id);

    void update(T obj);

    void delete(int id);

}