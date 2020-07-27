package com.recepti.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="komentar")
public class Komentar implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "komentar_id", unique = true, nullable = false)
    private Long komentar_id;

	@Column(name="komentator", unique = false, nullable = false)
	private String komentator;
	
	@Column(name="tekst", unique = false,nullable = false)
	private String tekst;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JsonBackReference
	private Recept recept;
	
	public Komentar() {
	}

	public Komentar(Long komentar_id, String komentator, String tekst,Recept recept) {
		super();
		this.komentar_id = komentar_id;
		this.komentator = komentator;
		this.tekst = tekst;
		this.recept=recept;
	}

	public Long getKomentar_id() {
		return komentar_id;
	}

	public void setKomentar_id(Long komentar_id) {
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
	

	public Recept getRecept() {
		return recept;
	}

	public void setRecept(Recept recept) {
		this.recept = recept;
	}

	@Override
	public String toString() {
		return "Komentar [komentar_id=" + komentar_id + ", komentator=" + komentator + ", tekst=" + tekst + ", recept="
				+ recept + "]";
	}

	
	
	
	
	
}
