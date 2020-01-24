package com.example.proyectocloudfirestore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Adaptador extends FirebaseRecyclerAdapter<Ciudad,CiudadHolder> implements View.OnClickListener {

    private View.OnClickListener listener;

    public Adaptador(@NonNull FirebaseRecyclerOptions<Ciudad> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CiudadHolder holder, int position, @NonNull Ciudad model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public CiudadHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder, parent, false);
        view.setOnClickListener(this);
        return new CiudadHolder(view);
    }

    void onClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v);
    }
}
