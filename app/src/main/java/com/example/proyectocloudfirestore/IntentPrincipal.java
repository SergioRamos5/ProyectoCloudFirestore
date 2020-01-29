package com.example.proyectocloudfirestore;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
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
                final String key = adaptador.getSnapshots().getSnapshot(pos).getId();
                ciudad = adaptador.getItem(pos);

                Intent intent = new Intent(IntentPrincipal.this, IntentEditar.class);
                intent.putExtra("Clave", key);
                intent.putExtra("Ciudad", ciudad);
                startActivity(intent);
            }
        });

        //region LONG CLICK
        adaptador.onClickLongListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                pos = recyclerView.getChildAdapterPosition(v);
                ciudad = adaptador.getItem(pos);

                final String key = adaptador.getSnapshots().getSnapshot(pos).getId();

                AlertDialog.Builder dialog = new AlertDialog.Builder(IntentPrincipal.this);
                dialog.setTitle("ELIMINAR")
                        .setMessage("¿Eliminar "+ciudad.getNombre()+"?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                firebaseFirestore.collection("Ciudades").document(key).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(IntentPrincipal.this, "Se ha elimnado el registro", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(IntentPrincipal.this, "Fallo al eliminar", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
                return false;
            }
        });
        //endregion

        adaptador.startListening();
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
