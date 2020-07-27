package com.recepti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recepti.entity.Komentar;

public interface KomentarRepository extends JpaRepository<Komentar, Long>{

}
