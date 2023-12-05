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

import com.dini.activoscriticosnacionalesbackend.entidades.OperadorFuenteRiesgo;
import com.dini.activoscriticosnacionalesbackend.repositorios.OperadorFuenteRiesgoRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/operadorFuenteRiesgo")
public class OperadorFuenteRiesgoControlador {
	
	@Autowired
    private OperadorFuenteRiesgoRepositorio operadorFuenteRiesgoRepositorio;

    @GetMapping("/listar")
    public List<OperadorFuenteRiesgo> getAllOperadoresFuentesRiesgos() {
        return operadorFuenteRiesgoRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<OperadorFuenteRiesgo> getOperadorFuenteRiesgoById(@PathVariable(value = "id") long id_operador_fuente_riesgo)
        throws ResourceNotFoundException {
    	OperadorFuenteRiesgo operador_fuente_riesgo = operadorFuenteRiesgoRepositorio.findById(id_operador_fuente_riesgo)
          .orElseThrow(() -> new ResourceNotFoundException("OperadorFuenteRiesgo no encontrado para id :: " + id_operador_fuente_riesgo));
        return ResponseEntity.ok().body(operador_fuente_riesgo);
    }

    @PostMapping("/registrar")
    public OperadorFuenteRiesgo createOperadorFuenteRiesgo(@Valid @RequestBody OperadorFuenteRiesgo operador_fuente_riesgo) {
    	Date fecha = new Date();
    	operador_fuente_riesgo.setFecha_creacion(fecha);
        return operadorFuenteRiesgoRepositorio.save(operador_fuente_riesgo);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<OperadorFuenteRiesgo> updateOperadorFuenteRiesgo(@PathVariable(value = "id") Long id_operador_fuente_riesgo,
         @Valid @RequestBody OperadorFuenteRiesgo operador_fuente_riesgo_detalle) throws ResourceNotFoundException {
    	OperadorFuenteRiesgo operador_fuente_riesgo = operadorFuenteRiesgoRepositorio.findById(id_operador_fuente_riesgo)
        .orElseThrow(() -> new ResourceNotFoundException("OperadorFuenteRiesgo no encontrado para id :: " + id_operador_fuente_riesgo));

        operador_fuente_riesgo.setNatural_riesgo_operador(operador_fuente_riesgo_detalle.getNatural_riesgo_operador());
        operador_fuente_riesgo.setSeguridad_riesgo_operador(operador_fuente_riesgo_detalle.getSeguridad_riesgo_operador());
        operador_fuente_riesgo.setPaz_riesgo_operador(operador_fuente_riesgo_detalle.getPaz_riesgo_operador());
        operador_fuente_riesgo.setDigital_riesgo_operador(operador_fuente_riesgo_detalle.getDigital_riesgo_operador());
        operador_fuente_riesgo.setMantenimiento_riesgo_operador(operador_fuente_riesgo_detalle.getMantenimiento_riesgo_operador());
        operador_fuente_riesgo.setPandemia_riesgo_operador(operador_fuente_riesgo_detalle.getPandemia_riesgo_operador());
        operador_fuente_riesgo.setObsolescencia_riesgo_operador(operador_fuente_riesgo_detalle.getObsolescencia_riesgo_operador());
        operador_fuente_riesgo.setOtro_riesgo_operador(operador_fuente_riesgo_detalle.getOtro_riesgo_operador());
        operador_fuente_riesgo.setCodigo_operador(operador_fuente_riesgo_detalle.getCodigo_operador());
        operador_fuente_riesgo.setCodigo_acn(operador_fuente_riesgo_detalle.getCodigo_acn());
        
        operador_fuente_riesgo.setEstado_riesgo_operador(operador_fuente_riesgo_detalle.getEstado_riesgo_operador());
        
        Date fecha = new Date();
        operador_fuente_riesgo.setId_usuario_creacion(operador_fuente_riesgo_detalle.getId_usuario_creacion());
        operador_fuente_riesgo.setFecha_creacion(operador_fuente_riesgo_detalle.getFecha_creacion());
        operador_fuente_riesgo.setIp_creacion(operador_fuente_riesgo_detalle.getIp_creacion());
        operador_fuente_riesgo.setId_usuario_modificacion(operador_fuente_riesgo_detalle.getId_usuario_modificacion());
        if(operador_fuente_riesgo_detalle.getId_riesgo_operador()==0) {
        	operador_fuente_riesgo.setFecha_modificacion(null);
        }
        else {
        	operador_fuente_riesgo.setFecha_modificacion(fecha);
        }
        operador_fuente_riesgo.setIp_modificacion(operador_fuente_riesgo_detalle.getIp_modificacion());
        
        final OperadorFuenteRiesgo operador_fuente_riesgo_actualizado = operadorFuenteRiesgoRepositorio.save(operador_fuente_riesgo);
        return ResponseEntity.ok(operador_fuente_riesgo_actualizado);
    }
    
}