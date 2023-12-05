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

import com.dini.activoscriticosnacionalesbackend.entidades.Pais;
import com.dini.activoscriticosnacionalesbackend.repositorios.PaisRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/pais")
public class PaisControlador {
	
	@Autowired
    private PaisRepositorio paisRepositorio;

    @GetMapping("/listar")
    public List<Pais> getAllPaises() {
        return paisRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Pais> getPaisById(@PathVariable(value = "id") Long id_pais)
        throws ResourceNotFoundException {
        Pais pais = paisRepositorio.findById(id_pais)
          .orElseThrow(() -> new ResourceNotFoundException("Pais no encontrada para id :: " + id_pais));
        return ResponseEntity.ok().body(pais);
    }

}