package com.dini.activoscriticosnacionalesbackend.controladores;

import com.dini.activoscriticosnacionalesbackend.excepciones.ResourceNotFoundException;

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

import com.dini.activoscriticosnacionalesbackend.entidades.Codigo;
import com.dini.activoscriticosnacionalesbackend.repositorios.CodigoRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/codigo")
public class CodigoControlador {
	
	@Autowired
    private CodigoRepositorio codigoRepositorio;

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Codigo> getCodigoById(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
        Codigo codigo = codigoRepositorio.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Codigo no encontrado para id :: " + id));
        return ResponseEntity.ok().body(codigo);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Codigo> updateCodigo(@PathVariable(value = "id") long id,
         @Valid @RequestBody Codigo codigo_detalle) throws ResourceNotFoundException {
        Codigo codigo = codigoRepositorio.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Codigo no encontrado para id :: " + id));

        codigo.setCapacidades_nacionales(codigo_detalle.getCapacidades_nacionales());
        codigo.setSub_capacidades_nacionales(codigo_detalle.getSub_capacidades_nacionales());
        codigo.setClasificaciones_acn(codigo_detalle.getClasificaciones_acn());
        codigo.setObjetivos_nacionales(codigo_detalle.getObjetivos_nacionales());
        codigo.setActivos_criticos_nacionales(codigo_detalle.getActivos_criticos_nacionales());
        codigo.setOperadores(codigo_detalle.getOperadores());
        codigo.setUsuarios(codigo_detalle.getUsuarios());
        codigo.setSectores(codigo_detalle.getSectores());
        codigo.setFuncionarios(codigo_detalle.getFuncionarios());
        codigo.setDocumentos(codigo_detalle.getDocumentos());
        codigo.setContactos(codigo_detalle.getContactos());
        codigo.setEventos(codigo_detalle.getEventos());
        codigo.setArchivos(codigo_detalle.getArchivos());
        codigo.setFiles_servers(codigo_detalle.getFiles_servers());
        
        final Codigo codigo_actualizado = codigoRepositorio.save(codigo);
        return ResponseEntity.ok(codigo_actualizado);
    } 
}