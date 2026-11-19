package com.utn.android;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.utn.android.datos.TipoContactoDAO;
import com.utn.android.entidades.TipoContacto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

        ArrayList<TipoContacto> listaTipoContacto = new TipoContactoDAO().getAll();
        spEmail = findViewById(R.id.spEmail);
        cargarSpinnerTipoContacto(spEmail, listaTipoContacto);
        spTelefono = findViewById(R.id.spTelefono);
        cargarSpinnerTipoContacto(spTelefono, listaTipoContacto);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etTelefono = findViewById(R.id.etTelefono);
        spTelefono = findViewById(R.id.spTelefono);
        etEmail = findViewById(R.id.etEmail);
        spEmail = findViewById(R.id.spEmail);
        etDireccion = findViewById(R.id.etDireccion);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        etFechaNacimiento.setFocusable(false);
        etFechaNacimiento.setOnClickListener(v -> mostrarSelectorFecha());

        cargarDatosPrueba();
    }

    @SuppressLint("SetTextI18n")
    private void cargarDatosPrueba() {

        etNombre.setText("Sofía que tu no eres María");
        etApellido.setText("Rauw");
        etTelefono.setText("1123456789");

        etEmail.setText("RauwAlejandro@gmail.com");

        etDireccion.setText("Av. Argentina 123");
        etFechaNacimiento.setText("15/05/2000");

        // Seleccionar opciones de los Spinner
        spTelefono.setSelection(0);
        spEmail.setSelection(1);
    }

    private void mostrarSelectorFecha() {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(this,
                (view, anio, mes, dia) -> {
                    etFechaNacimiento.setText(String.format(Locale.getDefault(), "%02d/%02d/%04d", dia, mes + 1, anio));
                    etFechaNacimiento.setError(null);
                },
                c.get(Calendar.YEAR) - 18, c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    private boolean esUnaFechaValida(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        sdf.setLenient(false);
        try {
            Date d = sdf.parse(fecha);
            return d != null && !d.after(new Date());
        } catch (ParseException e) {
            return false;
        }
    }

    private void cargarSpinnerTipoContacto(Spinner spinner, ArrayList<TipoContacto> listaTipoContacto) {
        ArrayAdapter<TipoContacto> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, listaTipoContacto
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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

    private Boolean seVerificaronLosDatos() {
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

        String email = etEmail.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Ingrese un email valido");
            valido = false;
        }

        String direccion = etDireccion.getText().toString().trim();
        if (direccion.isEmpty()) {
            etDireccion.setError("Ingrese una dirección valida");
            valido = false;
        }

        String fechaNac = etFechaNacimiento.getText().toString().trim();
        if (fechaNac.isEmpty()) {
            etFechaNacimiento.setError("Ingrese una fecha de nacimiento");
            Toast.makeText(this, "Seleccione la fecha de nacimiento", Toast.LENGTH_SHORT).show();
            valido = false;
        } else if (!esUnaFechaValida(fechaNac)) {
            etFechaNacimiento.setError("Ingrese una fecha válida (DD/MM/AAAA)");
            Toast.makeText(this, "La fecha de nacimiento no es válida", Toast.LENGTH_SHORT).show();
            valido = false;
        }

        return valido;
    }

    public void eventoContinuar(View view) {
        if (!seVerificaronLosDatos()) {
            return;
        }
        Intent intent = new Intent(this, MasDatosContacto.class);
        intent.putExtra("nombre", etNombre.getText().toString().trim());
        intent.putExtra("apellido", etApellido.getText().toString().trim());
        intent.putExtra("telefono", etTelefono.getText().toString().trim());
        intent.putExtra("tipoTelefono", spTelefono.getSelectedItem().toString());
        intent.putExtra("email", etEmail.getText().toString().trim());
        intent.putExtra("tipoEmail", spEmail.getSelectedItem().toString());
        intent.putExtra("direccion", etDireccion.getText().toString().trim());
        intent.putExtra("fechaNacimiento", etFechaNacimiento.getText().toString().trim());
        startActivity(intent);
    }


}