package com.example.proyectocloudfirestore;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CiudadHolder extends RecyclerView.ViewHolder {

    EditText tNombre,tPais;


    public CiudadHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(Ciudad item) {
        tNombre.setText(item.getNombre() );
        tPais.setText(item.getPais());
    }
}
