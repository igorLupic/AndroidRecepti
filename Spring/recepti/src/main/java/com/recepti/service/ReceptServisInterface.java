package com.recepti.service;

import java.util.List;
import com.recepti.entity.Recept;

public interface ReceptServisInterface {

	Recept findOne(Long recept_id);
    List<Recept> findAll();
    Recept save(Recept recept);
    void remove(Long recept_id);
    List<Recept> findAllByOrderByTezinaAsc();
    List<Recept> findAllByOrderByTezinaDesc();

}
