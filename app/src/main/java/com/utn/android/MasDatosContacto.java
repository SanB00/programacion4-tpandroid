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

public class MasDatosContacto extends AppCompatActivity {
    private String nombre;
    private String apellido;
    private String telefono;
    private String tipoTelefono;
    private String email;
    private String tipoEmail;
    private String direccion;
    private String fechaNacimiento;

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
        nombre = intent.getStringExtra("nombre");
        apellido = intent.getStringExtra("apellido");
        telefono = intent.getStringExtra("telefono");
        tipoTelefono = intent.getStringExtra("tipoTelefono");
        email = intent.getStringExtra("email");
        tipoEmail = intent.getStringExtra("tipoEmail");
        direccion = intent.getStringExtra("direccion");
        fechaNacimiento = intent.getStringExtra("fechaNacimiento");
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