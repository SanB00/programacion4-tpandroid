package com.utn.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.utn.android.R;
import com.utn.android.entidades.Persona;

public class MasDatosContacto extends AppCompatActivity {
    private Persona objPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mas_datos_contacto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();

        String nombre = intent.getStringExtra("nombre");
        String apellido = intent.getStringExtra("apellido");
        String telefono = intent.getStringExtra("telefono");
        String tipoTelefono = intent.getStringExtra("tipoTelefono");
        String email = intent.getStringExtra("email");
        String tipoEmail = intent.getStringExtra("tipoEmail");
        String direccion = intent.getStringExtra("direccion");
        String fechaNacimiento = intent.getStringExtra("fechaNacimiento");

        objPersona = new Persona(nombre, apellido, telefono, tipoTelefono, email, tipoEmail, direccion, fechaNacimiento);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();

        if(id==R.id.menu_agregar){
            Intent intent = new Intent(this,FormularioContacto.class);
            startActivity(intent);
            return true;
        }
        else if(id==R.id.menu_listado){
            Intent intent=new Intent(this,ListarContactos.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}