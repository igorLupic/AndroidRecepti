package com.recepti.dto;

import java.io.Serializable;

import com.recepti.entity.Komentar;

public class KomentarDTO implements Serializable{ 
	private Long komentar_id;
	private String komentator;
	private String tekst;
	private Long recept_id;
	
	public KomentarDTO() {
		
	}
	
	public KomentarDTO(Long komentar_id, String komentator, String tekst,Long recept_id) {
		super();
		this.komentar_id = komentar_id;
		this.komentator = komentator;
		this.tekst = tekst;
		this.recept_id=recept_id;
	}
	
	public KomentarDTO(Komentar komentar) {
		this(komentar.getKomentar_id(), komentar.getKomentator(),komentar.getTekst(),komentar.getRecept().getRecept_id());
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

	public Long getRecept_id() {
		return recept_id;
	}

	public void setRecept_id(Long recept_id) {
		this.recept_id = recept_id;
	}
	
	
	
}
