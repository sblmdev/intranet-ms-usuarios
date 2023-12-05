package com.dini.activoscriticosnacionalesbackend.controladores;
import com.dini.activoscriticosnacionalesbackend.excepciones.ResourceNotFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dini.activoscriticosnacionalesbackend.entidades.Region;
import com.dini.activoscriticosnacionalesbackend.repositorios.RegionRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/region")
public class RegionControlador {
	
	@Autowired
    private RegionRepositorio regionRepositorio;

    @GetMapping("/listar")
    public List<Region> getAllRegiones() {
        return regionRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable(value = "id") String id_region)
        throws ResourceNotFoundException {
        Region region = regionRepositorio.findById(id_region)
          .orElseThrow(() -> new ResourceNotFoundException("Region no encontrada para id :: " + id_region));
        return ResponseEntity.ok().body(region);
    }

}