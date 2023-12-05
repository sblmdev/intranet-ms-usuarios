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

import com.dini.activoscriticosnacionalesbackend.entidades.ActivoCriticoNacional;
import com.dini.activoscriticosnacionalesbackend.entidades.ActivoCriticoNacionalSector;
import com.dini.activoscriticosnacionalesbackend.repositorios.ActivoCriticoNacionalSectorRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/activoCriticoNacionalSector")
public class ActivoCriticoNacionalSectorControlador {
	
	@Autowired
    private ActivoCriticoNacionalSectorRepositorio activoCriticoNacionalSectorRepositorio;
	
	@Autowired
	JdbcTemplate jdbctemplate;

    @GetMapping("/listar")
    public List<ActivoCriticoNacionalSector> getAllActivosCriticosNacionalesSectores() {
        return activoCriticoNacionalSectorRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<ActivoCriticoNacionalSector> getActivoCriticoNacionalSectorById(@PathVariable(value = "id") long id_acn_sector)
        throws ResourceNotFoundException {
    	ActivoCriticoNacionalSector acn_sector = activoCriticoNacionalSectorRepositorio.findById(id_acn_sector)
          .orElseThrow(() -> new ResourceNotFoundException("ActivoCriticoNacionalSector no encontrado para id :: " + id_acn_sector));
        return ResponseEntity.ok().body(acn_sector);
    }

    @PostMapping("/registrar")
    public ActivoCriticoNacionalSector createActivoCriticoNacionalSector(@Valid @RequestBody ActivoCriticoNacionalSector acn_sector) {
    	Date fecha = new Date();
    	acn_sector.setFecha_creacion(fecha);
        return activoCriticoNacionalSectorRepositorio.save(acn_sector);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ActivoCriticoNacionalSector> updateActivoCriticoNacionalSector(@PathVariable(value = "id") Long id_acn_sector,
         @Valid @RequestBody ActivoCriticoNacionalSector acn_sector_detalle) throws ResourceNotFoundException {
    	ActivoCriticoNacionalSector acn_sector = activoCriticoNacionalSectorRepositorio.findById(id_acn_sector)
        .orElseThrow(() -> new ResourceNotFoundException("ActivoCriticoNacionalSector no encontrado para id :: " + id_acn_sector));

        acn_sector.setCodigo_acn(acn_sector_detalle.getCodigo_acn());
        acn_sector.setCodigo_sector(acn_sector_detalle.getCodigo_sector());
        acn_sector.setId_acn(acn_sector_detalle.getId_acn());
        acn_sector.setId_sector(acn_sector_detalle.getId_sector());
        acn_sector.setTipo_sector(acn_sector_detalle.getTipo_sector());
        acn_sector.setEstado_acn_sector(acn_sector_detalle.getEstado_acn_sector());
        
        Date fecha = new Date();
        acn_sector.setId_usuario_creacion(acn_sector_detalle.getId_usuario_creacion());
        acn_sector.setFecha_creacion(acn_sector_detalle.getFecha_creacion());
        acn_sector.setIp_creacion(acn_sector_detalle.getIp_creacion());
        acn_sector.setId_usuario_modificacion(acn_sector_detalle.getId_usuario_modificacion());
        if(acn_sector_detalle.getId_acn_sector()==0) {
        	acn_sector.setFecha_modificacion(null);
        }
        else {
        	acn_sector.setFecha_modificacion(fecha);
        }
        acn_sector.setIp_modificacion(acn_sector_detalle.getIp_modificacion());
        
        final ActivoCriticoNacionalSector acn_sector_actualizado = activoCriticoNacionalSectorRepositorio.save(acn_sector);
        return ResponseEntity.ok(acn_sector_actualizado);
    }
    
    @GetMapping("/listarActivos/{acn}")
	private List<ActivoCriticoNacionalSector> acnsSectoresActivos(@PathVariable(value = "acn") String acn){
		String query = "SELECT * FROM ta_acn_acnsectores WHERE estado_acn_sector = 1 AND codigo_acn = '"+acn+"' ORDER BY tipo_sector";
		List<ActivoCriticoNacionalSector> acns = new ArrayList<ActivoCriticoNacionalSector>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			ActivoCriticoNacionalSector objeto = new ActivoCriticoNacionalSector();
			
			objeto.setId_acn_sector(Long.parseLong(String.valueOf(row.get("id_acn_sector"))));
			objeto.setCodigo_acn((String) row.get("codigo_acn"));
			objeto.setId_acn(Long.parseLong(String.valueOf(row.get("id_acn"))));
			objeto.setCodigo_sector((String) row.get("codigo_sector"));
			objeto.setId_sector(Long.parseLong(String.valueOf(row.get("id_sector"))));
			objeto.setEstado_acn_sector(Integer.parseInt(String.valueOf(row.get("estado_acn_sector"))));
			objeto.setTipo_sector(Integer.parseInt(String.valueOf(row.get("tipo_sector"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			acns.add(objeto);
		}
		return acns;
	}
    
}