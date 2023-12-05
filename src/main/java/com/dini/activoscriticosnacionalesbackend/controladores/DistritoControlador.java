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

import com.dini.activoscriticosnacionalesbackend.entidades.Distrito;
import com.dini.activoscriticosnacionalesbackend.entidades.Provincia;
import com.dini.activoscriticosnacionalesbackend.repositorios.DistritoRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/distrito")
public class DistritoControlador {
	
	@Autowired
    private DistritoRepositorio distritoRepositorio;
	
	@Autowired
	JdbcTemplate jdbctemplate;

    @GetMapping("/listar")
    public List<Distrito> getAllDistritos() {
        return distritoRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Distrito> getDistritoById(@PathVariable(value = "id") String id_distrito)
        throws ResourceNotFoundException {
        Distrito distrito = distritoRepositorio.findById(id_distrito)
          .orElseThrow(() -> new ResourceNotFoundException("Distrito no encontrado para id :: " + id_distrito));
        return ResponseEntity.ok().body(distrito);
    }
    
    @GetMapping("/listarPorProvincia/{provincia}")
	private List<Distrito> subCapacidadesNacionalesActivas(@PathVariable(value = "provincia") String id_provincia){
		String query = "SELECT * FROM ta_acn_distritos WHERE id_provincia='"+id_provincia+"'";
		List<Distrito> distritos = new ArrayList<Distrito>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			Distrito objeto = new Distrito();
			
			objeto.setId_distrito(String.valueOf(row.get("id_distrito")));
			objeto.setDistrito((String) row.get("distrito"));
			objeto.setId_provincia(String.valueOf(row.get("id_provincia")));
			objeto.setId_region(String.valueOf(row.get("id_region")));

			distritos.add(objeto);
		}
		return distritos;
	}
    
    @GetMapping("/listarPorProvinciaYRegion/{provincia}/{region}")
	private List<Distrito> subCapacidadesNacionalesActivas(@PathVariable(value = "provincia") String id_provincia, @PathVariable(value = "region") String id_region ){
		String query = "SELECT * FROM ta_acn_distritos WHERE id_provincia='"+id_provincia+"' AND id_region='"+id_region+"'";
		List<Distrito> distritos = new ArrayList<Distrito>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			Distrito objeto = new Distrito();
			
			objeto.setId_distrito(String.valueOf(row.get("id_distrito")));
			objeto.setDistrito((String) row.get("distrito"));
			objeto.setId_provincia(String.valueOf(row.get("id_provincia")));
			objeto.setId_region(String.valueOf(row.get("id_region")));

			distritos.add(objeto);
		}
		return distritos;
	}


}