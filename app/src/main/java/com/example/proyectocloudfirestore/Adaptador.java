package com.example.proyectocloudfirestore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class Adaptador extends FirestoreRecyclerAdapter<Ciudad,CiudadHolder> implements View.OnClickListener, View.OnLongClickListener {

    private View.OnClickListener listener;
    private View.OnLongClickListener longListener;
    public Adaptador(@NonNull FirestoreRecyclerOptions<Ciudad> options) {
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
        view.setOnLongClickListener(this);
        return new CiudadHolder(view);
    }

    //region CLICK Listner
    void onClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v);
    }
    //endregion

    @Override
    public boolean onLongClick(View v) {
        if (longListener != null)
            longListener.onLongClick(v);
        return false;
    }

    void onClickLongListener(View.OnLongClickListener longListener)
    {
        this.longListener = longListener;
    }
}
