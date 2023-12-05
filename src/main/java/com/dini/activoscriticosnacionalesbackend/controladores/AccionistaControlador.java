package com.dini.activoscriticosnacionalesbackend.controladores;

import com.dini.activoscriticosnacionalesbackend.excepciones.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dini.activoscriticosnacionalesbackend.entidades.Accionista;
import com.dini.activoscriticosnacionalesbackend.repositorios.AccionistaRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/accionista")
public class AccionistaControlador {
	
	@Autowired
    private AccionistaRepositorio accionistaRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Accionista> getDocumentoById(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
        Accionista accionista = accionistaRepositorio.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Accionista no encontrado para id :: " + id));
        return ResponseEntity.ok().body(accionista);
    }
    
    @PostMapping("/registrar")
    public Accionista createAccionista(@Valid @RequestBody Accionista accionista) {
    	Date fecha = new Date();
    	accionista.setFecha_creacion(fecha);
        return accionistaRepositorio.save(accionista);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Accionista> updateAccionista(@PathVariable(value = "id") long id,
         @Valid @RequestBody Accionista accionista_detalle) throws ResourceNotFoundException {
        Accionista accionista = accionistaRepositorio.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Accionista no encontrado para id :: " + id));

        accionista.setCodigo_operador(accionista_detalle.getCodigo_operador());
        accionista.setId_operador(accionista_detalle.getId_operador());
        accionista.setNombre_accionista(accionista_detalle.getNombre_accionista());
        accionista.setPorcentaje_accionista(accionista_detalle.getPorcentaje_accionista());
        accionista.setNacionalidad_accionista(accionista_detalle.getNacionalidad_accionista());
        accionista.setEstado_accionista(accionista_detalle.getEstado_accionista());
        
        Date fecha = new Date();
        accionista.setId_usuario_creacion(accionista_detalle.getId_usuario_creacion());
        accionista.setFecha_creacion(accionista_detalle.getFecha_creacion());
        accionista.setIp_creacion(accionista_detalle.getIp_creacion());
        accionista.setId_usuario_modificacion(accionista_detalle.getId_usuario_modificacion());
        if(accionista_detalle.getId_accionista()==0) {
        	accionista.setFecha_modificacion(null);
        }
        else {
        	accionista.setFecha_modificacion(fecha);
        }
        accionista.setIp_modificacion(accionista_detalle.getIp_modificacion());
        
        final Accionista accionista_actualizado = accionistaRepositorio.save(accionista);
        return ResponseEntity.ok(accionista_actualizado);
    } 
    
    
    @GetMapping("/listarActivosPorOperador/{codigo}")
	private List<Accionista> accionistasActivosPorReferenciaYTipo(@PathVariable(value = "codigo") String codigo){
		String query = "SELECT * FROM ta_acn_accionistas WHERE estado_accionista = 1 AND codigo_operador='"+codigo+"'";
		List<Accionista> accionistas = new ArrayList<Accionista>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
		
		for(Map row: rows) {
			Accionista objeto = new Accionista();
			
			objeto.setId_accionista(Long.parseLong(String.valueOf(row.get("id_accionista"))));
			objeto.setCodigo_operador((String) row.get("codigo_operador"));
			objeto.setId_operador(Long.parseLong(String.valueOf(row.get("id_operador"))));
			objeto.setNombre_accionista((String) row.get("nombre_accionista"));
			objeto.setPorcentaje_accionista(Float.parseFloat(String.valueOf(row.get("porcentaje_accionista"))));
			objeto.setNacionalidad_accionista(Integer.parseInt(String.valueOf(row.get("nacionalidad_accionista"))));
			objeto.setEstado_accionista(Integer.parseInt(String.valueOf(row.get("estado_accionista"))));

			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			accionistas.add(objeto);
		}
		return accionistas;
	}
      
}