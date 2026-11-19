package com.utn.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import com.utn.android.datos.PersonaDAO;

import com.utn.android.entidades.Persona;

//import com.utn.android.R;

public class ListarContactos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listar_contactos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView lvContactos = findViewById(R.id.lvContactos);

        PersonaDAO personaDAO = new PersonaDAO(this);

        ArrayList<Persona> listaContactos = personaDAO.getAll();
        ArrayAdapter<Persona> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaContactos
        );

        lvContactos.setAdapter(adapter);

// Detectar cuando seleccionan un contacto
        lvContactos.setOnItemClickListener((parent, view, position, id) -> {

            Persona persona = listaContactos.get(position);

            mostrarDetalleContacto(persona);
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_agregar) {
            Intent intent = new Intent(this, FormularioContacto.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_listado) {
            Intent intent = new Intent(this, ListarContactos.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void mostrarDetalleContacto(Persona persona) {
        String detalle = "ID: " + persona.getId()
                + "\nNombre: " + persona.getNombre()
                + "\nApellido: " + persona.getApellido()
                + "\nEmail: " + persona.getEmail()
                + "\nTeléfono: " + persona.getTelefono()
                + "\nFecha nacimiento: " + persona.getFechaNacimiento();

        new AlertDialog.Builder(this)
                .setTitle("Información del contacto")
                .setMessage(detalle)
                .setPositiveButton("Cerrar", null)
                .show();

    }
}