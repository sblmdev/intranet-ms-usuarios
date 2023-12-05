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

import com.dini.activoscriticosnacionalesbackend.entidades.ActivoCriticoNacionalAlternativo;
import com.dini.activoscriticosnacionalesbackend.repositorios.ActivoCriticoNacionalAlternativoRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/activoCriticoNacionalAlternativo")
public class ActivoCriticoNacionalAlternativoControlador {
	
	@Autowired
    private ActivoCriticoNacionalAlternativoRepositorio activoCriticoNacionalAlternativoRepositorio;

    @GetMapping("/listar")
    public List<ActivoCriticoNacionalAlternativo> getAllActivosCriticosNacionalesAlternativos() {
        return activoCriticoNacionalAlternativoRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<ActivoCriticoNacionalAlternativo> getActivoCriticoNacionalAlternativoById(@PathVariable(value = "id") long id_acn_alternativo)
        throws ResourceNotFoundException {
    	ActivoCriticoNacionalAlternativo acn_alternativo = activoCriticoNacionalAlternativoRepositorio.findById(id_acn_alternativo)
          .orElseThrow(() -> new ResourceNotFoundException("ActivoCriticoNacionalAlternativo no encontrado para id :: " + id_acn_alternativo));
        return ResponseEntity.ok().body(acn_alternativo);
    }

    @PostMapping("/registrar")
    public ActivoCriticoNacionalAlternativo createActivoCriticoNacionalAlternativo(@Valid @RequestBody ActivoCriticoNacionalAlternativo acn_alternativo) {
    	Date fecha = new Date();
    	acn_alternativo.setFecha_creacion(fecha);
        return activoCriticoNacionalAlternativoRepositorio.save(acn_alternativo);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ActivoCriticoNacionalAlternativo> updateActivoCriticoNacionalAlternativo(@PathVariable(value = "id") Long id_acn_alternativo,
         @Valid @RequestBody ActivoCriticoNacionalAlternativo acn_alternativo_detalle) throws ResourceNotFoundException {
    	ActivoCriticoNacionalAlternativo acn_alternativo = activoCriticoNacionalAlternativoRepositorio.findById(id_acn_alternativo)
        .orElseThrow(() -> new ResourceNotFoundException("ActivoCriticoNacionalAlternativo no encontrado para id :: " + id_acn_alternativo));

        acn_alternativo.setExistencia_alternativo(acn_alternativo_detalle.getExistencia_alternativo());
        acn_alternativo.setTiempo_reposicion_alternativo(acn_alternativo_detalle.getTiempo_reposicion_alternativo());
        acn_alternativo.setRecibir_apoyo_alternativo(acn_alternativo_detalle.getRecibir_apoyo_alternativo());
        acn_alternativo.setCodigo_acn(acn_alternativo_detalle.getCodigo_acn());
        acn_alternativo.setEstado_acn_alternativo(acn_alternativo_detalle.getEstado_acn_alternativo());
        
        Date fecha = new Date();
        acn_alternativo.setId_usuario_creacion(acn_alternativo_detalle.getId_usuario_creacion());
        acn_alternativo.setFecha_creacion(acn_alternativo_detalle.getFecha_creacion());
        acn_alternativo.setIp_creacion(acn_alternativo_detalle.getIp_creacion());
        acn_alternativo.setId_usuario_modificacion(acn_alternativo_detalle.getId_usuario_modificacion());
        if(acn_alternativo_detalle.getId_alternativo_acn()==0) {
        	acn_alternativo.setFecha_modificacion(null);
        }
        else {
        	acn_alternativo.setFecha_modificacion(fecha);
        }
        acn_alternativo.setIp_modificacion(acn_alternativo_detalle.getIp_modificacion());
        
        final ActivoCriticoNacionalAlternativo acn_alternativo_actualizado = activoCriticoNacionalAlternativoRepositorio.save(acn_alternativo);
        return ResponseEntity.ok(acn_alternativo_actualizado);
    }
    
}