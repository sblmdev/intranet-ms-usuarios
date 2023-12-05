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

import com.dini.activoscriticosnacionalesbackend.entidades.OperadorUbigeo;
import com.dini.activoscriticosnacionalesbackend.repositorios.OperadorUbigeoRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/operadorUbigeo")
public class OperadorUbigeoControlador {
	
	@Autowired
    private OperadorUbigeoRepositorio operadorUbigeoRepositorio;

    @GetMapping("/listar")
    public List<OperadorUbigeo> getAllOperadoresUbigeos() {
        return operadorUbigeoRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<OperadorUbigeo> getOperadorUbigeoById(@PathVariable(value = "id") long id_operador_ubigeo)
        throws ResourceNotFoundException {
    	OperadorUbigeo operador_ubigeo = operadorUbigeoRepositorio.findById(id_operador_ubigeo)
          .orElseThrow(() -> new ResourceNotFoundException("OperadorUbigeo no encontrado para id :: " + id_operador_ubigeo));
        return ResponseEntity.ok().body(operador_ubigeo);
    }

    @PostMapping("/registrar")
    public OperadorUbigeo createOperadorUbigeo(@Valid @RequestBody OperadorUbigeo operador_ubigeo) {
    	Date fecha = new Date();
    	operador_ubigeo.setFecha_creacion(fecha);
        return operadorUbigeoRepositorio.save(operador_ubigeo);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<OperadorUbigeo> updateOperadorUbigeo(@PathVariable(value = "id") Long id_operador_ubigeo,
         @Valid @RequestBody OperadorUbigeo operador_ubigeo_detalle) throws ResourceNotFoundException {
    	OperadorUbigeo operador_ubigeo = operadorUbigeoRepositorio.findById(id_operador_ubigeo)
        .orElseThrow(() -> new ResourceNotFoundException("OperadorUbigeo no encontrado para id :: " + id_operador_ubigeo));


        operador_ubigeo.setUbigeo_operador(operador_ubigeo_detalle.getUbigeo_operador());
        operador_ubigeo.setCentro_poblado_ubigeo_operador(operador_ubigeo_detalle.getCentro_poblado_ubigeo_operador());
        operador_ubigeo.setAltitud_ubigeo_operador(operador_ubigeo_detalle.getAltitud_ubigeo_operador());
        operador_ubigeo.setCodigo_operador(operador_ubigeo_detalle.getCodigo_operador());
        operador_ubigeo.setCodigo_acn(operador_ubigeo_detalle.getCodigo_acn());
        operador_ubigeo.setEstado_ubigeo_operador(operador_ubigeo_detalle.getEstado_ubigeo_operador());

        Date fecha = new Date();
        operador_ubigeo.setId_usuario_creacion(operador_ubigeo_detalle.getId_usuario_creacion());
        operador_ubigeo.setFecha_creacion(operador_ubigeo_detalle.getFecha_creacion());
        operador_ubigeo.setIp_creacion(operador_ubigeo_detalle.getIp_creacion());
        operador_ubigeo.setId_usuario_modificacion(operador_ubigeo_detalle.getId_usuario_modificacion());
        if(operador_ubigeo_detalle.getId_ubigeo_operador()==0) {
        	operador_ubigeo.setFecha_modificacion(null);
        }
        else {
        	operador_ubigeo.setFecha_modificacion(fecha);
        }
        operador_ubigeo.setIp_modificacion(operador_ubigeo_detalle.getIp_modificacion());
        
        final OperadorUbigeo operador_ubigeo_actualizado = operadorUbigeoRepositorio.save(operador_ubigeo);
        return ResponseEntity.ok(operador_ubigeo_actualizado);
    }
    
}