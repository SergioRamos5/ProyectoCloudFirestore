package com.example.proyectocloudfirestore;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CiudadHolder extends RecyclerView.ViewHolder {

    TextView tNombre;


    public CiudadHolder(@NonNull View itemView) {
        super(itemView);
        tNombre = itemView.findViewById(R.id.holder_paisNombre);
    }

    public void bind(Ciudad item) {
        tNombre.setText(item.getNombre() + "/" + item.getPais());

    }
}
