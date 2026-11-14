package com.example.tp6_grupo_16;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormularioContacto extends AppCompatActivity {
    private EditText etNombre;
    private EditText etApellido;
    private EditText etTelefono;
    private Spinner spTelefono;
    private EditText etEmail;
    private Spinner spEmail;
    private EditText etDireccion;
    private EditText etFechaNacimiento;

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
        etNombre=findViewById(R.id.etNombre);
        etApellido=findViewById(R.id.etApellido);
        etTelefono=findViewById(R.id.etTelefono);
        spTelefono=findViewById(R.id.spTelefono);
        etEmail=findViewById(R.id.etEmail);
        spEmail=findViewById(R.id.spEmail);
        etDireccion = findViewById(R.id.etDireccion);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);

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
        boolean valido = true;

        String nombre = etNombre.getText().toString().trim();
        if (nombre.isEmpty() || !nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            etNombre.setError("Ingrese un nombre valido");
            valido = false;
        }

        String apellido = etApellido.getText().toString().trim();
        if (apellido.isEmpty() || !apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            etApellido.setError("Ingrese un apellido valido");
            valido = false;
        }

        String telefono = etTelefono.getText().toString().trim();
        if (!telefono.matches("^[0-9]+(-[0-9]+)*$")) {
            etTelefono.setError("Ingrese un telefono valido");
            valido = false;
        }

        String Email = etEmail.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            etEmail.setError("Ingrese un email valido");
            valido = false;
        }

        String Direccion = etDireccion.getText().toString().trim();
        if (Direccion.isEmpty()) {
            etDireccion.setError("Ingrese una dirección valida");
            valido = false;
        }

        String fechaNac = etFechaNacimiento.getText().toString().trim();
        if (fechaNac.isEmpty()) {
            etFechaNacimiento.setError("Ingrese una fecha de nacimiento");
            valido = false;
        } else if (!fechaNac.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/([0-9]{4})$")) {
            etFechaNacimiento.setError("Ingrese una fecha válida (DD/MM/AAAA)");
            valido = false;
        }

        return valido;
    }

    public void eventoContinuar(View view) {
        if(!VerificarDatos()){
            return;
        }

    }
}
