package com.recepti.service;

import java.util.List;

import com.recepti.entity.Komentar;

public interface KomentarSeviceInterface {
	
	Komentar findOne(Long komentar_id);
    List<Komentar> findAll();
    Komentar save(Komentar komentar);
    void remove(Long komentar_id);
	
	
}
