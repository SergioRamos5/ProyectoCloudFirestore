package com.example.proyectocloudfirestore;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IntentPrincipal extends AppCompatActivity {

    RecyclerView recyclerView;
    Adaptador adaptador;
    DatabaseReference dbPred;
    FirebaseRecyclerOptions<Ciudad> firebaseRecyclerOptions;
    ChildEventListener childEventListener;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        dbPred = FirebaseDatabase.getInstance().getReference().child("Ciudades");
        firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Ciudad>().setQuery(dbPred, Ciudad.class).build();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adaptador = new Adaptador(firebaseRecyclerOptions);
        recyclerView.setAdapter(adaptador);
    }
    @Override
    protected void onDestroy()
    {
        if (firebaseDatabase != null && childEventListener != null)
            firebaseDatabase.getReference("prediccion").removeEventListener(childEventListener);
        super.onDestroy();
    }

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
