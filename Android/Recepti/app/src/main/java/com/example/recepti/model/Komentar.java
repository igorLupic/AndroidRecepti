package com.example.recepti.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Komentar implements Parcelable {

    @SerializedName("komentar_id")
    @Expose
    private int komentar_id;
    @SerializedName("komentator")
    @Expose
    private String komentator;
    @SerializedName("tekst")
    @Expose
    private String tekst;
    @SerializedName("komentarRecept")
    @Expose
    private Recept komentarRecept;

    public Komentar(){
    }

    public Komentar(int komentar_id, String komentator, String tekst,Recept komentarRecept) {
        this.komentar_id = komentar_id;
        this.komentator = komentator;
        this.tekst = tekst;
        this.komentarRecept=komentarRecept;
    }

    public int getKomentar_id() {
        return komentar_id;
    }

    public void setKomentar_id(int komentar_id) {
        this.komentar_id = komentar_id;
    }

    public String getKomentator() {
        return komentator;
    }

    public void setKomentator(String komentator) {
        this.komentator = komentator;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Recept getKomentarRecept() {
        return komentarRecept;
    }

    public void setKomentarRecept(Recept komentarRecept) {
        this.komentarRecept = komentarRecept;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.komentar_id);
        dest.writeString(this.komentator);
        dest.writeString(this.tekst);
        dest.writeParcelable(this.komentarRecept,flags);
    }

    protected Komentar(Parcel in) {
        this.komentar_id = in.readInt();
        this.komentator = in.readString();
        this.tekst = in.readString();
        this.komentarRecept = in.readParcelable(Recept.class.getClassLoader());

    }

    public static final Parcelable.Creator<Komentar> CREATOR = new Parcelable.Creator<Komentar>() {
        @Override
        public Komentar createFromParcel(Parcel source) {
            return new Komentar(source);
        }

        @Override
        public Komentar[] newArray(int size) {
            return new Komentar[size];
        }
    };

}
