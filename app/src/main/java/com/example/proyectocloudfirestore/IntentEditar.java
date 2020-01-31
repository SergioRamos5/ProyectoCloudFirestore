package com.example.proyectocloudfirestore;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

public class IntentEditar extends AppCompatActivity {

    EditText nombre,comunidad, pais, poblaciom, abreviatura;
    FloatingActionButton fab;
    FirebaseFirestore firebaseFirestore;
    Ciudad ciudad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_editar_agregar);

        firebaseFirestore = FirebaseFirestore.getInstance();

        String key = getIntent().getExtras().getString("Clave");
        ciudad = getIntent().getExtras().getParcelable("Ciudad");

        abreviatura = findViewById(R.id.abreviatura);
        nombre = findViewById(R.id.nombre);
        comunidad = findViewById(R.id.comunidad);
        pais = findViewById(R.id.pais);
        poblaciom = findViewById(R.id.poblacion);

        abreviatura.setText(key);
        nombre.setText(ciudad.getNombre().toString());
        comunidad.setText(ciudad.getComunidad().toString());
        poblaciom.setText(ciudad.getPoblacion().toString());
        pais.setText(ciudad.getPais().toString());

        fab = findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!abreviatura.getText().toString().isEmpty() && !nombre.getText().toString().isEmpty() &&
                    !comunidad.getText().toString().isEmpty() &&
                    !pais.getText().toString().isEmpty() && !poblaciom.getText().toString().isEmpty())
                {
                    Ciudad c = new Ciudad(nombre.getText().toString(),comunidad.getText().toString(),pais.getText().toString(),poblaciom.getText().toString());
                    firebaseFirestore.collection("Ciudades").document(abreviatura.getText().toString()).set(c);
                }
                finish();
            }
        });
    }
}
