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

import com.dini.activoscriticosnacionalesbackend.entidades.ActivoCriticoNacionalImpacto;
import com.dini.activoscriticosnacionalesbackend.repositorios.ActivoCriticoNacionalImpactoRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/activoCriticoNacionalImpacto")
public class ActivoCriticoNacionalImpactoControlador {
	
	@Autowired
    private ActivoCriticoNacionalImpactoRepositorio activoCriticoNacionalImpactoRepositorio;

    @GetMapping("/listar")
    public List<ActivoCriticoNacionalImpacto> getAllActivosCriticosNacionalesImpactos() {
        return activoCriticoNacionalImpactoRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<ActivoCriticoNacionalImpacto> getActivoCriticoNacionalImpactoById(@PathVariable(value = "id") long id_acn_impacto)
        throws ResourceNotFoundException {
    	ActivoCriticoNacionalImpacto acn_impacto = activoCriticoNacionalImpactoRepositorio.findById(id_acn_impacto)
          .orElseThrow(() -> new ResourceNotFoundException("ActivoCriticoNacionalImpacto no encontrado para id :: " + id_acn_impacto));
        return ResponseEntity.ok().body(acn_impacto);
    }

    @PostMapping("/registrar")
    public ActivoCriticoNacionalImpacto createActivoCriticoNacionalImpacto(@Valid @RequestBody ActivoCriticoNacionalImpacto acn_impacto) {
    	Date fecha = new Date();
    	acn_impacto.setFecha_creacion(fecha);
        return activoCriticoNacionalImpactoRepositorio.save(acn_impacto);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ActivoCriticoNacionalImpacto> updateActivoCriticoNacionalImpacto(@PathVariable(value = "id") Long id_acn_impacto,
         @Valid @RequestBody ActivoCriticoNacionalImpacto acn_impacto_detalle) throws ResourceNotFoundException {
    	ActivoCriticoNacionalImpacto acn_impacto = activoCriticoNacionalImpactoRepositorio.findById(id_acn_impacto)
        .orElseThrow(() -> new ResourceNotFoundException("ActivoCriticoNacionalImpacto no encontrado para id :: " + id_acn_impacto));

        acn_impacto.setEconomico_1_impacto(acn_impacto_detalle.getEconomico_1_impacto());
        acn_impacto.setEconomico_2_impacto(acn_impacto_detalle.getEconomico_2_impacto());
        acn_impacto.setEconomico_3_impacto(acn_impacto_detalle.getEconomico_3_impacto());
        acn_impacto.setEconomico_4_impacto(acn_impacto_detalle.getEconomico_4_impacto());
        acn_impacto.setEconomico_5_impacto(acn_impacto_detalle.getEconomico_5_impacto());
        acn_impacto.setPoblacion_1_impacto(acn_impacto_detalle.getPoblacion_1_impacto());
        acn_impacto.setPoblacion_2_impacto(acn_impacto_detalle.getPoblacion_2_impacto());
        acn_impacto.setPoblacion_3_impacto(acn_impacto_detalle.getPoblacion_3_impacto());
        acn_impacto.setPoblacion_4_impacto(acn_impacto_detalle.getPoblacion_4_impacto());
        acn_impacto.setAmbiental_1_impacto(acn_impacto_detalle.getAmbiental_1_impacto());
        acn_impacto.setCodigo_acn(acn_impacto_detalle.getCodigo_acn());
        acn_impacto.setEstado_acn_impacto(acn_impacto_detalle.getEstado_acn_impacto());
        
        Date fecha = new Date();
        acn_impacto.setId_usuario_creacion(acn_impacto_detalle.getId_usuario_creacion());
        acn_impacto.setFecha_creacion(acn_impacto_detalle.getFecha_creacion());
        acn_impacto.setIp_creacion(acn_impacto_detalle.getIp_creacion());
        acn_impacto.setId_usuario_modificacion(acn_impacto_detalle.getId_usuario_modificacion());
        if(acn_impacto_detalle.getId_impacto_acn()==0) {
        	acn_impacto.setFecha_modificacion(null);
        }
        else {
        	acn_impacto.setFecha_modificacion(fecha);
        }
        acn_impacto.setIp_modificacion(acn_impacto_detalle.getIp_modificacion());
        
        final ActivoCriticoNacionalImpacto acn_impacto_actualizado = activoCriticoNacionalImpactoRepositorio.save(acn_impacto);
        return ResponseEntity.ok(acn_impacto_actualizado);
    }
    
}