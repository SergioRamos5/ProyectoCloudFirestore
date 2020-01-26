package com.example.proyectocloudfirestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class IntentPrincipal extends AppCompatActivity {

    RecyclerView recyclerView;
    Adaptador adaptador;
    FirebaseFirestore firebaseFirestore;
    FloatingActionButton fab;
    int pos;
    Ciudad ciudad;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        firebaseFirestore = FirebaseFirestore.getInstance();

        Query query = firebaseFirestore.collection("Ciudades");

        FirestoreRecyclerOptions<Ciudad> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Ciudad>().setQuery(query, Ciudad.class).build();

        adaptador = new Adaptador(firestoreRecyclerOptions);
        adaptador.notifyDataSetChanged();

        adaptador.onClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = recyclerView.getChildAdapterPosition(v);
                ciudad = adaptador.getItem(pos);

            }
        });

        recyclerView.setAdapter(adaptador);

        fab = findViewById(R.id.btFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentPrincipal.this, IntentAgregar.class);
                startActivity(intent);
            }
        });

    }

   /* @Override
    protected void onDestroy()
    {
        if (firebaseDatabase != null && childEventListener != null)
            firebaseDatabase.getReference("prediccion").removeEventListener(childEventListener);
        super.onDestroy();
    }*/

    @Override
    protected void onStart()
    {
        adaptador.startListening();
        super.onStart();
    }

    @Override
    protected void onStop()
    {
        adaptador.stopListening();
        super.onStop();
    }
}
