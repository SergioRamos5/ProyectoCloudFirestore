package com.example.proyectocloudfirestore;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.storage.StorageReference;

public class Ciudad implements Parcelable {

    private String nombre;
    private String comunidad;
    private String pais;
    private String poblacion;
    private String foto;

    protected Ciudad(Parcel in) {
        nombre = in.readString();
        comunidad = in.readString();
        pais = in.readString();
        poblacion = in.readString();
        foto = in.readString();
    }

    public static final Creator<Ciudad> CREATOR = new Creator<Ciudad>() {
        @Override
        public Ciudad createFromParcel(Parcel in) {
            return new Ciudad(in);
        }

        @Override
        public Ciudad[] newArray(int size) {
            return new Ciudad[size];
        }
    };

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Ciudad() {
    }

    public Ciudad(String nombre, String comunidad, String pais, String poblacion) {
        this.nombre = nombre;
        this.comunidad = comunidad;
        this.pais = pais;
        this.poblacion = poblacion;
    }





    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(comunidad);
        parcel.writeString(pais);
        parcel.writeString(poblacion);
        parcel.writeString(foto);
    }
}
