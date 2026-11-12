package com.example.tp6_grupo_16;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormularioContacto extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtTelefono;
    private Spinner spTelefono;
    private EditText txtTEmail;
    private Spinner spEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_contacto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtNombre=findViewById(R.id.editTextNombre);
        txtApellido=findViewById(R.id.editTextApellido);
        txtTelefono=findViewById(R.id.editTextPhone);
        spTelefono=findViewById(R.id.spTelefono);
        txtTEmail=findViewById(R.id.editTextEmail);
        spEmail=findViewById(R.id.spEmail);

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
    private Boolean VerificarDatos() {
        String nombre = txtNombre.getText().toString().trim();
        String apellido = txtApellido.getText().toString().trim();
        if (nombre.isEmpty() || !nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            txtNombre.setError("Ingrese un nombre valido");
            return false;
        }
        if (apellido.isEmpty() || !apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            txtApellido.setError("Ingrese un apellido valido");
            return false;
        }
        String telefono = txtTelefono.getText().toString().trim();
        if (!telefono.matches("[0-9]+")) {
            txtTelefono.setError("Ingrese un telefono valido");
            return false;
        }
        String Email = txtTEmail.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            txtTEmail.setError("Ingrese un email valido");
            return false;
        }
        return true;

    }
}
