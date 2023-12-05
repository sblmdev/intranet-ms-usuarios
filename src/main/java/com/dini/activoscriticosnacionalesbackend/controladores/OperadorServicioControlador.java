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

import com.dini.activoscriticosnacionalesbackend.entidades.OperadorServicio;
import com.dini.activoscriticosnacionalesbackend.repositorios.OperadorServicioRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/operadorServicio")
public class OperadorServicioControlador {
	
	@Autowired
    private OperadorServicioRepositorio operadorServicioRepositorio;

    @GetMapping("/listar")
    public List<OperadorServicio> getAllOperadoresServicios() {
        return operadorServicioRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<OperadorServicio> getOperadorServicioById(@PathVariable(value = "id") long id_operador_servicio)
        throws ResourceNotFoundException {
    	OperadorServicio operador_servicio = operadorServicioRepositorio.findById(id_operador_servicio)
          .orElseThrow(() -> new ResourceNotFoundException("OperadorServicio no encontrado para id :: " + id_operador_servicio));
        return ResponseEntity.ok().body(operador_servicio);
    }

    @PostMapping("/registrar")
    public OperadorServicio createOperadorServicio(@Valid @RequestBody OperadorServicio operador_servicio) {
    	Date fecha = new Date();
    	operador_servicio.setFecha_creacion(fecha);
        return operadorServicioRepositorio.save(operador_servicio);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<OperadorServicio> updateOperadorServicio(@PathVariable(value = "id") Long id_operador_servicio,
         @Valid @RequestBody OperadorServicio operador_servicio_detalle) throws ResourceNotFoundException {
    	OperadorServicio operador_servicio = operadorServicioRepositorio.findById(id_operador_servicio)
        .orElseThrow(() -> new ResourceNotFoundException("OperadorServicio no encontrado para id :: " + id_operador_servicio));

        operador_servicio.setMagnitud_servicio_operador(operador_servicio_detalle.getMagnitud_servicio_operador());
        operador_servicio.setMagnitud_minima_servicio_operador(operador_servicio_detalle.getMagnitud_minima_servicio_operador());
        operador_servicio.setMagnitud_maxima_servicio_operador(operador_servicio_detalle.getMagnitud_maxima_servicio_operador());
        operador_servicio.setNumero_trabajadores_servicio_operador(operador_servicio_detalle.getNumero_trabajadores_servicio_operador());
        operador_servicio.setPorcentaje_contribucion_servicio_operador(operador_servicio_detalle.getPorcentaje_contribucion_servicio_operador());
        operador_servicio.setCodigo_operador(operador_servicio_detalle.getCodigo_operador());
        operador_servicio.setCodigo_acn(operador_servicio_detalle.getCodigo_acn());
        
        operador_servicio.setEstado_servicio_operador(operador_servicio_detalle.getEstado_servicio_operador());

        Date fecha = new Date();
        operador_servicio.setId_usuario_creacion(operador_servicio_detalle.getId_usuario_creacion());
        operador_servicio.setFecha_creacion(operador_servicio_detalle.getFecha_creacion());
        operador_servicio.setIp_creacion(operador_servicio_detalle.getIp_creacion());
        operador_servicio.setId_usuario_modificacion(operador_servicio_detalle.getId_usuario_modificacion());
        if(operador_servicio_detalle.getId_servicio_operador()==0) {
        	operador_servicio.setFecha_modificacion(null);
        }
        else {
        	operador_servicio.setFecha_modificacion(fecha);
        }
        operador_servicio.setIp_modificacion(operador_servicio_detalle.getIp_modificacion());
        
        final OperadorServicio operador_servicio_actualizado = operadorServicioRepositorio.save(operador_servicio);
        return ResponseEntity.ok(operador_servicio_actualizado);
    }
    
}