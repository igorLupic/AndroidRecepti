package com.recepti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recepti.entity.Recept;

public interface ReceptRepository extends JpaRepository<Recept, Long>{
	List<Recept> findAllByOrderByTezinaAsc();
    List<Recept> findAllByOrderByTezinaDesc();



}
