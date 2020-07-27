package com.example.recepti.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Recept implements Parcelable {

    @SerializedName("recept_id")
    @Expose
    private int recept_id;
    @SerializedName("vreme")
    @Expose
    private int vreme;
    @SerializedName("priprema")
    @Expose
    private String priprema;
    @SerializedName("sastojci")
    @Expose
    private String sastojci;
    @SerializedName("tezina")
    @Expose
    private String tezina;
    @SerializedName("naziv")
    @Expose
    private String naziv;
    @SerializedName("komentari")
    @Expose
    private ArrayList<Komentar> komentari;

    public Recept(){
    }

    public Recept(int recept_id, int vreme, String priprema, String sastojci, String tezina,String naziv,ArrayList<Komentar> komentari) {
        this.recept_id = recept_id;
        this.vreme = vreme;
        this.priprema = priprema;
        this.sastojci = sastojci;
        this.tezina = tezina;
        this.naziv=naziv;
        this.komentari=komentari;
    }

    public int getRecept_id() {
        return recept_id;
    }

    public void setRecept_id(int recept_id) {
        this.recept_id = recept_id;
    }

    public int getVreme() {
        return vreme;
    }

    public void setVreme(int vreme) {
        this.vreme = vreme;
    }

    public String getPriprema() {
        return priprema;
    }

    public void setPriprema(String priprema) {
        this.priprema = priprema;
    }

    public String getSastojci() {
        return sastojci;
    }

    public void setSastojci(String sastojci) {
        this.sastojci = sastojci;
    }

    public String getTezina() {
        return tezina;
    }

    public void setTezina(String tezina) {
        this.tezina = tezina;
    }

    public String getNaziv(){return naziv;}

    public void setNaziv(String naziv){this.naziv = naziv;}

    public ArrayList<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(ArrayList<Komentar> komentari) {
        this.komentari = komentari;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.recept_id);
        dest.writeInt(this.vreme);
        dest.writeString(this.priprema);
        dest.writeString(this.sastojci);
        dest.writeString(this.tezina);
        dest.writeString(this.naziv);
        dest.writeList(this.komentari);
    }

    protected Recept(Parcel in) {
        this.recept_id = in.readInt();
        this.vreme = in.readInt();
        this.priprema = in.readString();
        this.sastojci = in.readString();
        this.tezina = in.readString();
        this.naziv = in.readString();
        this.komentari= new ArrayList<Komentar>();
        in.readList(this.komentari, Komentar.class.getClassLoader());
    }

    public static final Parcelable.Creator<Recept> CREATOR = new Parcelable.Creator<Recept>() {
        @Override
        public Recept createFromParcel(Parcel source) {
            return new Recept(source);
        }

        @Override
        public Recept[] newArray(int size) {
            return new Recept[size];
        }
    };
}
