package com.recepti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recepti.entity.Komentar;
import com.recepti.repository.KomentarRepository;
import com.recepti.service.KomentarSeviceInterface;

@Service
public class KomentarService implements KomentarSeviceInterface{
	
	@Autowired
    KomentarRepository komentarRepository;

    @Override
    public Komentar findOne(Long komentar_id) {
        return komentarRepository.findById(komentar_id).get();
    }

    @Override
    public List<Komentar> findAll() {
        return komentarRepository.findAll();
    }

    @Override
    public Komentar save(Komentar komentar) {
        return komentarRepository.save(komentar);
    }

    @Override
    public void remove(Long komentar_id) {
    	komentarRepository.deleteById(komentar_id);
    }

}
