package com.example.recepti.model;


public class KomentarDTO {

    private int komentar_id;

    private String komentator;

    private String tekst;

    private long recept_id;

    public KomentarDTO(){
    }

    public KomentarDTO(int komentar_id, String komentator, String tekst,long recept_id) {
        this.komentar_id = komentar_id;
        this.komentator = komentator;
        this.tekst = tekst;
        this.recept_id=recept_id;
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

    public long getRecept_id() {
        return recept_id;
    }

    public void setRecept_id(long recept_id) {
        this.recept_id = recept_id;
    }
}
