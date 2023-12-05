package com.dini.activoscriticosnacionalesbackend.controladores;

import com.dini.activoscriticosnacionalesbackend.excepciones.ResourceNotFoundException;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dini.activoscriticosnacionalesbackend.entidades.Archivo;
import com.dini.activoscriticosnacionalesbackend.entidades.CapacidadNacional;
import com.dini.activoscriticosnacionalesbackend.entidades.Evento;
import com.dini.activoscriticosnacionalesbackend.repositorios.EventoRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/evento")
public class EventoControlador {
	
	@Autowired
    private EventoRepositorio eventoRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
        Evento evento = eventoRepositorio.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado para id :: " + id));
        return ResponseEntity.ok().body(evento);
    }
    
    @PostMapping("/registrar")
    public Evento createEvento(@Valid @RequestBody Evento evento) {
    	Date fecha = new Date();
    	evento.setFecha_creacion(fecha);
    	
    	File directorio = new File("C:\\Users\\LENOVO\\Desktop\\archivos\\"+evento.getCodigo_acn()+"\\"+evento.getCodigo_evento());
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        return eventoRepositorio.save(evento);
    }

    @GetMapping("/listar")
    public List<Evento> getAllEventos() {
        return eventoRepositorio.findAll();
    }
      
}