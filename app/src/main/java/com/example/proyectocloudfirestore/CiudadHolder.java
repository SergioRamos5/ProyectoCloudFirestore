package com.example.proyectocloudfirestore;

import android.net.Uri;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class CiudadHolder extends RecyclerView.ViewHolder {

    TextView tNombre;
    ImageView imagen;

    public CiudadHolder(@NonNull View itemView) {
        super(itemView);
        tNombre = itemView.findViewById(R.id.holder_paisNombre);
        imagen = itemView.findViewById(R.id.holder_imagen);
    }

    public void bind(Ciudad item) {
        tNombre.setText(item.getNombre() + "/" + item.getPais());

        StorageReference ref = FirebaseStorage.getInstance().getReference(item.getFoto());

        ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
        @Override
        public void onComplete(@NonNull Task<Uri> task) {
                Picasso.get().load(task.getResult()).into(imagen);
            }
        });
    }
}
