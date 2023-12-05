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

import com.dini.activoscriticosnacionalesbackend.entidades.OperadorUbicacion;
import com.dini.activoscriticosnacionalesbackend.repositorios.OperadorUbicacionRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/operadorUbicacion")
public class OperadorUbicacionControlador {
	
	@Autowired
    private OperadorUbicacionRepositorio operadorUbicacionRepositorio;

    @GetMapping("/listar")
    public List<OperadorUbicacion> getAllOperadoresUbicaciones() {
        return operadorUbicacionRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<OperadorUbicacion> getOperadorUbicacionById(@PathVariable(value = "id") long id_operador_ubicacion)
        throws ResourceNotFoundException {
    	OperadorUbicacion operador_ubicacion = operadorUbicacionRepositorio.findById(id_operador_ubicacion)
          .orElseThrow(() -> new ResourceNotFoundException("OperadorUbicacion no encontrado para id :: " + id_operador_ubicacion));
        return ResponseEntity.ok().body(operador_ubicacion);
    }

    @PostMapping("/registrar")
    public OperadorUbicacion createOperadorUbicacion(@Valid @RequestBody OperadorUbicacion operador_ubicacion) {
    	Date fecha = new Date();
    	operador_ubicacion.setFecha_creacion(fecha);
        return operadorUbicacionRepositorio.save(operador_ubicacion);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<OperadorUbicacion> updateOperadorUbicacion(@PathVariable(value = "id") Long id_operador_ubicacion,
         @Valid @RequestBody OperadorUbicacion operador_ubicacion_detalle) throws ResourceNotFoundException {
    	OperadorUbicacion operador_ubicacion = operadorUbicacionRepositorio.findById(id_operador_ubicacion)
        .orElseThrow(() -> new ResourceNotFoundException("OperadorUbicacion no encontrado para id :: " + id_operador_ubicacion));

        operador_ubicacion.setCoordenadas_ubicacion_operador(operador_ubicacion_detalle.getCoordenadas_ubicacion_operador());
        operador_ubicacion.setVias_acceso_ubicacion_operador(operador_ubicacion_detalle.getVias_acceso_ubicacion_operador());
        operador_ubicacion.setCroquis_ubicacion_operador(operador_ubicacion_detalle.getCroquis_ubicacion_operador());
        operador_ubicacion.setDireccion_ubicacion_operador(operador_ubicacion_detalle.getDireccion_ubicacion_operador());
        operador_ubicacion.setDomicilio_legal_ubicacion_operador(operador_ubicacion_detalle.getDomicilio_legal_ubicacion_operador());
        operador_ubicacion.setPostal_direccion_ubicacion_operador(operador_ubicacion_detalle.getPostal_direccion_ubicacion_operador());
        operador_ubicacion.setPostal_domicilio_ubicacion_operador(operador_ubicacion_detalle.getPostal_domicilio_ubicacion_operador());
        operador_ubicacion.setFoto_satelital_ubicacion_operador(operador_ubicacion_detalle.getFoto_satelital_ubicacion_operador());
        operador_ubicacion.setExtension_ubicacion_operador(operador_ubicacion_detalle.getExtension_ubicacion_operador());
        operador_ubicacion.setFicha_registral_ubicacion_operador(operador_ubicacion_detalle.getFicha_registral_ubicacion_operador());
        operador_ubicacion.setCarreteras_ubicacion_operador(operador_ubicacion_detalle.getCarreteras_ubicacion_operador());
        operador_ubicacion.setOceanos_ubicacion_operador(operador_ubicacion_detalle.getOceanos_ubicacion_operador());
        operador_ubicacion.setDependencia_policial_ubicacion_operador(operador_ubicacion_detalle.getDependencia_policial_ubicacion_operador());
        operador_ubicacion.setCompañia_bombero_ubicacion_operador(operador_ubicacion_detalle.getCompañia_bombero_ubicacion_operador());
        operador_ubicacion.setCodigo_operador(operador_ubicacion_detalle.getCodigo_operador());
        operador_ubicacion.setCodigo_acn(operador_ubicacion_detalle.getCodigo_acn());
        
        operador_ubicacion.setEstado_ubicacion_operador(operador_ubicacion_detalle.getEstado_ubicacion_operador());

        Date fecha = new Date();
        operador_ubicacion.setId_usuario_creacion(operador_ubicacion_detalle.getId_usuario_creacion());
        operador_ubicacion.setFecha_creacion(operador_ubicacion_detalle.getFecha_creacion());
        operador_ubicacion.setIp_creacion(operador_ubicacion_detalle.getIp_creacion());
        operador_ubicacion.setId_usuario_modificacion(operador_ubicacion_detalle.getId_usuario_modificacion());
        if(operador_ubicacion_detalle.getId_ubicacion_operador()==0) {
        	operador_ubicacion.setFecha_modificacion(null);
        }
        else {
        	operador_ubicacion.setFecha_modificacion(fecha);
        }
        operador_ubicacion.setIp_modificacion(operador_ubicacion_detalle.getIp_modificacion());
        
        final OperadorUbicacion operador_ubicacion_actualizado = operadorUbicacionRepositorio.save(operador_ubicacion);
        return ResponseEntity.ok(operador_ubicacion_actualizado);
    }
    
}