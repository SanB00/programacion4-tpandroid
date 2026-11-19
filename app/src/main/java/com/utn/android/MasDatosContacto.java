package com.utn.android;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.utn.android.datos.PersonaDAO;
import com.utn.android.entidades.Persona;

public class MasDatosContacto extends AppCompatActivity {
    private Persona objPersona;
    private RadioGroup rgEstudios;
    private CheckBox chkDeportes, chkMusica, chkTecnologia, chkArte;
    private Switch swNotificaciones;
    private TextView error;


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
        rgEstudios = findViewById(R.id.rgEstudios);
        chkDeportes = findViewById(R.id.chkDeporte);
        chkMusica = findViewById(R.id.chkMusica);
        chkArte = findViewById(R.id.chkArte);
        chkTecnologia = findViewById(R.id.chkTecnologia);
        swNotificaciones = findViewById(R.id.swInfo);
        error = findViewById(R.id.tvError);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> {
            if (verificar()) {

                PersonaDAO dao = new PersonaDAO(this);
                boolean guardado = dao.insert(objPersona);

                if (guardado) {
                    error.setText("Contacto guardado correctamente");
                    error.setTextColor(Color.GREEN);
                    error.setVisibility(View.VISIBLE);
                    btnGuardar.setEnabled(false);
                } else {
                    error.setText("Error al guardar el contacto");
                    error.setTextColor(Color.RED);
                    error.setVisibility(View.VISIBLE);
                }
            }
        });
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
    public boolean verificar() {
        error.setVisibility(View.GONE);
        int idSeleccionado = rgEstudios.getCheckedRadioButtonId();
        if (idSeleccionado == -1) {
            error.setText("Seleccione al menos un estudio");
            error.setVisibility(View.VISIBLE);
            return false;
        }
        RadioButton rbSeleccionado = findViewById(idSeleccionado);
        objPersona.setNivelEstudios(rbSeleccionado.getText().toString());
        String intereses = "";
        if (chkDeportes.isChecked()) {
            intereses += "Deporte, ";
        }
        if (chkMusica.isChecked()) {
            intereses += "Musica, ";
        }
        if (chkTecnologia.isChecked()) {
            intereses += "Tecnologia, ";
        }
        if (chkArte.isChecked()) {
            intereses += "Arte, ";
        }

        if (intereses.isEmpty()) {
            error.setText("Seleccione al menos un interes");
            error.setVisibility(View.VISIBLE);
            return false;
        }
        objPersona.setIntereses(intereses);
        objPersona.setRecibeInfo(swNotificaciones.isChecked());
        return true;
    }
}