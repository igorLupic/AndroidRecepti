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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recepti.dto.ReceptDTO;
import com.recepti.entity.Recept;
import com.recepti.service.impl.ReceptService;

@RestController
@RequestMapping(value="api/recepti")
public class ReceptController {

	 	@Autowired
	 	ReceptService receptService;
	
	 	@GetMapping(value="/all")
	    public ResponseEntity<List<ReceptDTO>> getRecepti(){
	        List<Recept> recepti = receptService.findAll();
	        List<ReceptDTO> receptiDTO = new ArrayList<ReceptDTO>();
	        for (Recept c : recepti) {
	        	receptiDTO.add(new ReceptDTO(c));
	        }
	        return new ResponseEntity<List<ReceptDTO>>(receptiDTO, HttpStatus.OK);
	    }
	 	
	 	@GetMapping("/orderTezinaAsc")
	    public ResponseEntity<List<ReceptDTO>> orderByTezinaAsc(){
	        List<Recept> recepti = receptService.findAllByOrderByTezinaAsc();
	        List<ReceptDTO> receptiDTOS = new ArrayList<>();
	        for (Recept m : recepti){
	        	receptiDTOS.add(new ReceptDTO(m));
	        }
	        return new ResponseEntity<>(receptiDTOS, HttpStatus.OK);
	}
	
	 	@GetMapping("/orderTezinaDesc")
	    public ResponseEntity<List<ReceptDTO>> orderByTezinaDesc(){
	        List<Recept> recepti = receptService.findAllByOrderByTezinaDesc();
	        List<ReceptDTO> receptiDTOS = new ArrayList<>();
	        for (Recept m : recepti){
	        	receptiDTOS.add(new ReceptDTO(m));
	        }
	        return new ResponseEntity<>(receptiDTOS, HttpStatus.OK);
	    }


	    @GetMapping(value="/{id}")
	    public ResponseEntity<ReceptDTO> getRecept(@PathVariable("id") Long id) {

	    	Recept recept = receptService.findOne(id);
	        if (recept == null) {
	            return new ResponseEntity<ReceptDTO>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<ReceptDTO>(new ReceptDTO(recept), HttpStatus.OK);

	    }

	    @PostMapping(value="/add", consumes="application/json")
	    public ResponseEntity<ReceptDTO> saveRecept(@RequestBody ReceptDTO receptDTO){

	        Recept recept = new Recept();

	        recept.setVreme(receptDTO.getVreme());
	        recept.setPriprema(receptDTO.getPriprema());
	        recept.setSastojci(receptDTO.getSastojci());
	        recept.setTezina(receptDTO.getTezina());
	        recept.setNaziv(receptDTO.getNaziv());
	        
	        recept = receptService.save(recept);

	        return new ResponseEntity<ReceptDTO>(new ReceptDTO(recept), HttpStatus.CREATED);
	    }
		@PutMapping(value = "/edit", consumes = "application/json")
		public ResponseEntity<ReceptDTO> updateRecept(@RequestBody ReceptDTO receptDTO) {
			
			Recept recept = receptService.findOne(receptDTO.getRecept_id());
			if (recept == null)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			recept.setNaziv(receptDTO.getNaziv());
			recept.setVreme(receptDTO.getVreme());
		    recept.setPriprema(receptDTO.getPriprema());
		    recept.setSastojci(receptDTO.getSastojci());
		    recept.setTezina(receptDTO.getTezina());

		    recept = receptService.save(recept);
	        return new ResponseEntity<ReceptDTO>(new ReceptDTO(recept), HttpStatus.OK);
		}
		

	    @DeleteMapping(value="/delete/{id}")
	    public ResponseEntity<Void> deleteRecept(@PathVariable Long id){
	        Recept recept = receptService.findOne(id);
	        if(recept != null) {
	        	receptService.remove(id);
	            return new ResponseEntity<Void>(HttpStatus.OK);
	        }else {
	            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	        }
	    }

	 
}
