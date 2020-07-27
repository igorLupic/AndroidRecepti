package com.recepti.dto;

import java.io.Serializable;

import com.recepti.entity.Recept;

public class ReceptDTO implements Serializable{
	private Long recept_id;
	private int vreme;
	private String priprema;
	private String sastojci;
	private String tezina;
	private String naziv;
	
	public ReceptDTO(Long recept_id, int vreme, String priprema, String sastojci,String tezina,String naziv) {
		this.recept_id = recept_id;
		this.vreme = vreme;
		this.priprema = priprema;
		this.sastojci = sastojci;
		this.tezina = tezina;
		this.naziv=naziv;
	}
	
	public ReceptDTO() {
	}
	
	public ReceptDTO(Recept recept) {
		this(recept.getRecept_id(), recept.getVreme(),recept.getPriprema(), recept.getSastojci(),recept.getTezina(),recept.getNaziv());
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
	
}
