package com.recepti.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="recept")
public class Recept implements Serializable{

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recept_id", unique = true, nullable = false)
    private Long recept_id;
	
	@Column(name="vreme", unique=false,nullable = false)
	private int vreme;
	
	@Column(name="priprema", unique=false,nullable=false)
	private String priprema;
	
	@Column(name="sastojci", unique = false,nullable = false)
	private String sastojci;
	
	@Column(name="tezina",unique = false,nullable = false)
	private String tezina;
	
	@Column(name="naziv",unique = false,nullable = false)
	private String naziv;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy = "recept")
	@JsonManagedReference
	private Set<Komentar> komentarRecepta = new HashSet<Komentar>();
	
	
	public Recept() {
	}

	public Recept(Long recept_id, int vreme, String priprema, String sastojci, String tezina,String naziv, Set<Komentar> komentarRecepta) {
		super();
		this.recept_id = recept_id;
		this.vreme = vreme;
		this.priprema = priprema;
		this.sastojci = sastojci;
		this.tezina= tezina;
		this.naziv=naziv;
		this.komentarRecepta=komentarRecepta;
	}

	public Long getRecept_id() {
		return recept_id;
	}

	public void setRecept_id(Long recept_id) {
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
	
	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv(String naziv) {
		this.naziv=naziv;
	}
	
	public Set<Komentar> getKomentarRecepta() {
		return komentarRecepta;
	}

	public void setKomentarRecepta(Set<Komentar> komentarRecepta) {
		this.komentarRecepta = komentarRecepta;
	}

	@Override
	public String toString() {
		return "Recept [recept_id=" + recept_id + ", vreme=" + vreme + ", priprema=" + priprema + ", sastojci="
				+ sastojci + ", tezina=" + tezina + ", naziv=" + naziv + ", komentarRecepta=" + komentarRecepta + "]";
	}

	
	
	
}
