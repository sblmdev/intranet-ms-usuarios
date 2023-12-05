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

import com.dini.activoscriticosnacionalesbackend.entidades.Provincia;
import com.dini.activoscriticosnacionalesbackend.repositorios.ProvinciaRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/provincia")
public class ProvinciaControlador {
	
	@Autowired
    private ProvinciaRepositorio provinciaRepositorio;
	
	@Autowired
	JdbcTemplate jdbctemplate;

    @GetMapping("/listar")
    public List<Provincia> getAllProvincias() {
        return provinciaRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Provincia> getProvinciaById(@PathVariable(value = "id") String id_provincia)
        throws ResourceNotFoundException {
        Provincia provincia = provinciaRepositorio.findById(id_provincia)
          .orElseThrow(() -> new ResourceNotFoundException("Provincia no encontrada para id :: " + id_provincia));
        return ResponseEntity.ok().body(provincia);
    }
    
    @GetMapping("/listarPorRegion/{region}")
	private List<Provincia> subCapacidadesNacionalesActivas(@PathVariable(value = "region") String id_region){
		String query = "SELECT * FROM ta_acn_provincias WHERE id_region='"+id_region+"'";
		List<Provincia> provincias = new ArrayList<Provincia>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			Provincia objeto = new Provincia();
			
			objeto.setId_provincia((String) row.get("id_provincia"));
			objeto.setProvincia((String) row.get("provincia"));
			objeto.setId_region((String) row.get("id_region"));

			provincias.add(objeto);
		}
		return provincias;
	}

}