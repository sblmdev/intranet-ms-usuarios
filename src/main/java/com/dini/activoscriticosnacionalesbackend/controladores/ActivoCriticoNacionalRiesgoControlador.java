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

import com.dini.activoscriticosnacionalesbackend.entidades.ActivoCriticoNacionalRiesgo;
import com.dini.activoscriticosnacionalesbackend.repositorios.ActivoCriticoNacionalRiesgoRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/activoCriticoNacionalRiesgo")
public class ActivoCriticoNacionalRiesgoControlador {
	
	@Autowired
    private ActivoCriticoNacionalRiesgoRepositorio activoCriticoNacionalRiesgoRepositorio;

    @GetMapping("/listar")
    public List<ActivoCriticoNacionalRiesgo> getAllActivosCriticosNacionalesRiesgos() {
        return activoCriticoNacionalRiesgoRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<ActivoCriticoNacionalRiesgo> getActivoCriticoNacionalRiesgoById(@PathVariable(value = "id") long id_acn_riesgo)
        throws ResourceNotFoundException {
    	ActivoCriticoNacionalRiesgo acn_riesgo = activoCriticoNacionalRiesgoRepositorio.findById(id_acn_riesgo)
          .orElseThrow(() -> new ResourceNotFoundException("ActivoCriticoNacionalRiesgo no encontrado para id :: " + id_acn_riesgo));
        return ResponseEntity.ok().body(acn_riesgo);
    }

    @PostMapping("/registrar")
    public ActivoCriticoNacionalRiesgo createActivoCriticoNacionalRiesgo(@Valid @RequestBody ActivoCriticoNacionalRiesgo acn_riesgo) {
    	Date fecha = new Date();
    	acn_riesgo.setFecha_creacion(fecha);
        return activoCriticoNacionalRiesgoRepositorio.save(acn_riesgo);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ActivoCriticoNacionalRiesgo> updateActivoCriticoNacional(@PathVariable(value = "id") Long id_acn_riesgo,
         @Valid @RequestBody ActivoCriticoNacionalRiesgo acn_riesgo_detalle) throws ResourceNotFoundException {
    	ActivoCriticoNacionalRiesgo acn_riesgo = activoCriticoNacionalRiesgoRepositorio.findById(id_acn_riesgo)
        .orElseThrow(() -> new ResourceNotFoundException("ActivoCriticoNacionalRiesgo no encontrado para id :: " + id_acn_riesgo));

        acn_riesgo.setNatural_riesgo(acn_riesgo_detalle.getNatural_riesgo());
        acn_riesgo.setSeguridad_riesgo(acn_riesgo_detalle.getSeguridad_riesgo());
        acn_riesgo.setPaz_riesgo(acn_riesgo_detalle.getPaz_riesgo());
        acn_riesgo.setDigital_riesgo(acn_riesgo_detalle.getDigital_riesgo());
        acn_riesgo.setMantenimiento_riesgo(acn_riesgo_detalle.getMantenimiento_riesgo());
        acn_riesgo.setPandemia_riesgo(acn_riesgo_detalle.getPandemia_riesgo());
        acn_riesgo.setObsolescencia_riesgo(acn_riesgo_detalle.getObsolescencia_riesgo());
        acn_riesgo.setOtro_riesgo(acn_riesgo_detalle.getOtro_riesgo());
        acn_riesgo.setGravedad_riesgo(acn_riesgo_detalle.getGravedad_riesgo());
        acn_riesgo.setConsecuencia_riesgo(acn_riesgo_detalle.getConsecuencia_riesgo());
        acn_riesgo.setEfecto_riesgo(acn_riesgo_detalle.getEfecto_riesgo());
        acn_riesgo.setArea_influencia_riesgo(acn_riesgo_detalle.getArea_influencia_riesgo());
        acn_riesgo.setCodigo_acn(acn_riesgo_detalle.getCodigo_acn());
        acn_riesgo.setEstado_acn_riesgo(acn_riesgo_detalle.getEstado_acn_riesgo());
        
        Date fecha = new Date();
        acn_riesgo.setId_usuario_creacion(acn_riesgo_detalle.getId_usuario_creacion());
        acn_riesgo.setFecha_creacion(acn_riesgo_detalle.getFecha_creacion());
        acn_riesgo.setIp_creacion(acn_riesgo_detalle.getIp_creacion());
        acn_riesgo.setId_usuario_modificacion(acn_riesgo_detalle.getId_usuario_modificacion());
        if(acn_riesgo_detalle.getId_riesgo_acn()==0) {
        	acn_riesgo.setFecha_modificacion(null);
        }
        else {
        	acn_riesgo.setFecha_modificacion(fecha);
        }
        acn_riesgo.setIp_modificacion(acn_riesgo_detalle.getIp_modificacion());
        
        final ActivoCriticoNacionalRiesgo acn_riesgo_actualizado = activoCriticoNacionalRiesgoRepositorio.save(acn_riesgo);
        return ResponseEntity.ok(acn_riesgo_actualizado);
    }
    
}