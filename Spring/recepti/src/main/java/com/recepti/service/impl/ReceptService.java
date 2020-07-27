package com.recepti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recepti.entity.Recept;
import com.recepti.repository.ReceptRepository;
import com.recepti.service.ReceptServisInterface;

@Service
public class ReceptService implements ReceptServisInterface{
	
	@Autowired
    ReceptRepository receptRepository;

    @Override
    public Recept findOne(Long recept_id) {
        return receptRepository.findById(recept_id).get();
    }

    @Override
    public List<Recept> findAll() {
        return receptRepository.findAll();
    }

    @Override
    public Recept save(Recept recept) {
        return receptRepository.save(recept);
    }

    @Override
    public void remove(Long recept_id) {
    	receptRepository.deleteById(recept_id);
    }

    @Override
    public List<Recept> findAllByOrderByTezinaAsc() {
        return receptRepository.findAllByOrderByTezinaAsc();
    }
    @Override
    public List<Recept> findAllByOrderByTezinaDesc() {
        return receptRepository.findAllByOrderByTezinaDesc();
    }
    
}
