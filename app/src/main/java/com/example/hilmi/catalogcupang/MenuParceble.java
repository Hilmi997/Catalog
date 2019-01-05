package com.example.hilmi.catalogcupang;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hilmi on 19/12/2018.
 */



public class MenuParceble implements Parcelable {

    private String id;
    private String foto;
    private String title;
    private String harga;
    private String deskripsi;
    public final static Parcelable.Creator<MenuParceble> CREATOR = new Creator<MenuParceble>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MenuParceble createFromParcel(Parcel in) {
            return new MenuParceble(in);
        }

        public MenuParceble[] newArray(int size) {
            return (new MenuParceble[size]);
        }

    }
            ;

    protected MenuParceble(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.foto = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.harga = ((String) in.readValue((String.class.getClassLoader())));
        this.deskripsi = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MenuParceble() {
        //construc
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(foto);
        dest.writeValue(title);
        dest.writeValue(harga);
        dest.writeValue(deskripsi);
    }

    public int describeContents() {
        return 0;
    }

}