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

import com.dini.activoscriticosnacionalesbackend.entidades.OperadorDescripcion;
import com.dini.activoscriticosnacionalesbackend.repositorios.OperadorDescripcionRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/operadorDescripcion")
public class OperadorDescripcionControlador {
	
	@Autowired
    private OperadorDescripcionRepositorio operadorDescripcionRepositorio;

    @GetMapping("/listar")
    public List<OperadorDescripcion> getAllOperadoresDescripciones() {
        return operadorDescripcionRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<OperadorDescripcion> getOperadorDescripcionById(@PathVariable(value = "id") long id_operador_descripcion)
        throws ResourceNotFoundException {
    	OperadorDescripcion operador_descripcion = operadorDescripcionRepositorio.findById(id_operador_descripcion)
          .orElseThrow(() -> new ResourceNotFoundException("OperadorDescripcion no encontrado para id :: " + id_operador_descripcion));
        return ResponseEntity.ok().body(operador_descripcion);
    }

    @PostMapping("/registrar")
    public OperadorDescripcion createOperadorDescripcion(@Valid @RequestBody OperadorDescripcion operador_descripcion) {
    	Date fecha = new Date();
    	operador_descripcion.setFecha_creacion(fecha);
        return operadorDescripcionRepositorio.save(operador_descripcion);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<OperadorDescripcion> updateOperadorDescripcion(@PathVariable(value = "id") Long id_operador_descripcion,
         @Valid @RequestBody OperadorDescripcion operador_descripcion_detalle) throws ResourceNotFoundException {
    	OperadorDescripcion operador_descripcion = operadorDescripcionRepositorio.findById(id_operador_descripcion)
        .orElseThrow(() -> new ResourceNotFoundException("OperadorDescripcion no encontrado para id :: " + id_operador_descripcion));

        operador_descripcion.setInfraestructura_descripcion_operador(operador_descripcion_detalle.getInfraestructura_descripcion_operador());
        operador_descripcion.setServicios_descripcion_operador(operador_descripcion_detalle.getServicios_descripcion_operador());
        operador_descripcion.setFecha_descripcion_operador(operador_descripcion_detalle.getFecha_descripcion_operador());
        operador_descripcion.setValor_economico_descripcion_operador(operador_descripcion_detalle.getValor_economico_descripcion_operador());
        operador_descripcion.setCodigo_operador(operador_descripcion_detalle.getCodigo_operador());
        operador_descripcion.setCodigo_acn(operador_descripcion_detalle.getCodigo_acn());
        
        operador_descripcion.setEstado_descripcion_operador(operador_descripcion_detalle.getEstado_descripcion_operador());
        
        Date fecha = new Date();
        operador_descripcion.setId_usuario_creacion(operador_descripcion_detalle.getId_usuario_creacion());
        operador_descripcion.setFecha_creacion(operador_descripcion_detalle.getFecha_creacion());
        operador_descripcion.setIp_creacion(operador_descripcion_detalle.getIp_creacion());
        operador_descripcion.setId_usuario_modificacion(operador_descripcion_detalle.getId_usuario_modificacion());
        if(operador_descripcion_detalle.getId_descripcion_operador()==0) {
        	operador_descripcion.setFecha_modificacion(null);
        }
        else {
        	operador_descripcion.setFecha_modificacion(fecha);
        }
        operador_descripcion.setIp_modificacion(operador_descripcion_detalle.getIp_modificacion());

        final OperadorDescripcion operador_descripcion_actualizado = operadorDescripcionRepositorio.save(operador_descripcion);
        return ResponseEntity.ok(operador_descripcion_actualizado);
    }
    
}