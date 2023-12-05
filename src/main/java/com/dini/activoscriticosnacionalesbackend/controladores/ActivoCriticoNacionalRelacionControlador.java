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

import com.dini.activoscriticosnacionalesbackend.entidades.ActivoCriticoNacionalRelacion;
import com.dini.activoscriticosnacionalesbackend.repositorios.ActivoCriticoNacionalRelacionRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/activoCriticoNacionalRelacion")
public class ActivoCriticoNacionalRelacionControlador {
	
	@Autowired
    private ActivoCriticoNacionalRelacionRepositorio activoCriticoNacionalRelacionRepositorio;

    @GetMapping("/listar")
    public List<ActivoCriticoNacionalRelacion> getAllActivosCriticosNacionalesRelaciones() {
        return activoCriticoNacionalRelacionRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<ActivoCriticoNacionalRelacion> getActivoCriticoNacionalRelacionById(@PathVariable(value = "id") long id_acn_relacion)
        throws ResourceNotFoundException {
    	ActivoCriticoNacionalRelacion acn_relacion = activoCriticoNacionalRelacionRepositorio.findById(id_acn_relacion)
          .orElseThrow(() -> new ResourceNotFoundException("ActivoCriticoNacionalRelacion no encontrado para id :: " + id_acn_relacion));
        return ResponseEntity.ok().body(acn_relacion);
    }

    @PostMapping("/registrar")
    public ActivoCriticoNacionalRelacion createActivoCriticoNacionalRelacion(@Valid @RequestBody ActivoCriticoNacionalRelacion acn_relacion) {
    	Date fecha = new Date();
    	acn_relacion.setFecha_creacion(fecha);
        return activoCriticoNacionalRelacionRepositorio.save(acn_relacion);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ActivoCriticoNacionalRelacion> updateActivoCriticoNacionalRelacion(@PathVariable(value = "id") Long id_acn_relacion,
         @Valid @RequestBody ActivoCriticoNacionalRelacion acn_relacion_detalle) throws ResourceNotFoundException {
    	ActivoCriticoNacionalRelacion acn_relacion = activoCriticoNacionalRelacionRepositorio.findById(id_acn_relacion)
        .orElseThrow(() -> new ResourceNotFoundException("ActivoCriticoNacionalRelacion no encontrado para id :: " + id_acn_relacion));

        acn_relacion.setCodigo_objetivo_nacional(acn_relacion_detalle.getCodigo_objetivo_nacional());
        acn_relacion.setRelacion_acn_objetivo_nacional(acn_relacion_detalle.getRelacion_acn_objetivo_nacional());
        acn_relacion.setCodigo_capacidad_nacional(acn_relacion_detalle.getCodigo_capacidad_nacional());
        acn_relacion.setRelacion_acn_capacidad_nacional(acn_relacion_detalle.getRelacion_acn_capacidad_nacional());
        acn_relacion.setCuantificacion_acn_capacidad_nacional(acn_relacion_detalle.getCuantificacion_acn_capacidad_nacional());
        acn_relacion.setCodigo_clasificacion_acn(acn_relacion_detalle.getCodigo_clasificacion_acn());
        acn_relacion.setCodigo_acn(acn_relacion_detalle.getCodigo_acn());
        acn_relacion.setCodigo_sub_capacidad_nacional(acn_relacion_detalle.getCodigo_sub_capacidad_nacional());
        acn_relacion.setEstado_acn_relacion(acn_relacion_detalle.getEstado_acn_relacion());
        
        Date fecha = new Date();
        acn_relacion.setId_usuario_creacion(acn_relacion_detalle.getId_usuario_creacion());
        acn_relacion.setFecha_creacion(acn_relacion_detalle.getFecha_creacion());
        acn_relacion.setIp_creacion(acn_relacion_detalle.getIp_creacion());
        acn_relacion.setId_usuario_modificacion(acn_relacion_detalle.getId_usuario_modificacion());
        if(acn_relacion_detalle.getId_relacion_acn()==0) {
        	acn_relacion.setFecha_modificacion(null);
        }
        else {
        	acn_relacion.setFecha_modificacion(fecha);
        }
        acn_relacion.setIp_modificacion(acn_relacion_detalle.getIp_modificacion());
        
        final ActivoCriticoNacionalRelacion acn_relacion_actualizado = activoCriticoNacionalRelacionRepositorio.save(acn_relacion);
        return ResponseEntity.ok(acn_relacion_actualizado);
    }
    
}