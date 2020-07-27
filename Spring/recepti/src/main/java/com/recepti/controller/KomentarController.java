package com.recepti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recepti.dto.KomentarDTO;
import com.recepti.dto.ReceptDTO;
import com.recepti.entity.Komentar;
import com.recepti.entity.Recept;
import com.recepti.service.impl.KomentarService;
import com.recepti.service.impl.ReceptService;


@RestController
@RequestMapping(value="api/komentari")
public class KomentarController {
	
	@Autowired
 	KomentarService komentarService;
	
	@Autowired
	ReceptService receptService;

 	@GetMapping(value="/all")
    public ResponseEntity<List<KomentarDTO>> getRecepti(){
        List<Komentar> komentari = komentarService.findAll();
        List<KomentarDTO> komentariDTO = new ArrayList<KomentarDTO>();
        for (Komentar c : komentari) {
        	komentariDTO.add(new KomentarDTO(c));
        }
        return new ResponseEntity<List<KomentarDTO>>(komentariDTO, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<KomentarDTO> getKomentar(@PathVariable("id") Long id) {

    	Komentar komentar = komentarService.findOne(id);
        if (komentar == null) {
            return new ResponseEntity<KomentarDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<KomentarDTO>(new KomentarDTO(komentar), HttpStatus.OK);

    }

    @PostMapping(value="/add", consumes="application/json")
    public ResponseEntity<KomentarDTO> saveKomentar(@RequestBody KomentarDTO komentarDTO){

    	Komentar komentar = new Komentar();

    	komentar.setKomentator(komentarDTO.getKomentator());
    	komentar.setTekst(komentarDTO.getTekst());  
    	
    	Recept recept = receptService.findOne(komentarDTO.getRecept_id());
        
    	komentar.setRecept(recept);
    	komentar = komentarService.save(komentar);

        return new ResponseEntity<KomentarDTO>(new KomentarDTO(komentar), HttpStatus.CREATED);
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<Void> deleteKomentar(@PathVariable Long id){
        Komentar komentar = komentarService.findOne(id);
        if(komentar != null) {
        	komentarService.remove(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

	@GetMapping(value = "/getFor/{id}")
	public ResponseEntity<List<KomentarDTO>> getKomentarRecept(@PathVariable Long id) {
		
		List<Komentar> komentari = komentarService.findAll();
		List<KomentarDTO> komentariDTO = new ArrayList<KomentarDTO>();
		for (Komentar komentar : komentari) {
			if (komentar.getRecept().getRecept_id().equals(id)) {
				komentariDTO.add(new KomentarDTO(komentar));
			}
		}
		return new ResponseEntity<List<KomentarDTO>>(komentariDTO, HttpStatus.OK);
	}


}
