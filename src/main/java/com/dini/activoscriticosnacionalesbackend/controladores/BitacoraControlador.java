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

import com.dini.activoscriticosnacionalesbackend.entidades.Bitacora;
import com.dini.activoscriticosnacionalesbackend.entidades.ObjetivoNacional;
import com.dini.activoscriticosnacionalesbackend.repositorios.BitacoraRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/bitacora")
public class BitacoraControlador {
	
	@Autowired
    private BitacoraRepositorio bitacoraRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<Bitacora> getAllBitacoras() {
        return bitacoraRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Bitacora> getBitacoraById(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
        Bitacora bitacora = bitacoraRepositorio.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Bitacora no encontrado para id :: " + id));
        return ResponseEntity.ok().body(bitacora);
    }
    
    @PostMapping("/registrar")
    public Bitacora createBitacora(@Valid @RequestBody Bitacora bitacora) {
    	Date fecha = new Date();
    	bitacora.setFecha(fecha);
        return bitacoraRepositorio.save(bitacora);
    }
    
    @GetMapping("/listarPorUsuario/{codigo}")
	private List<Bitacora> bitacorasPorUsuario(@PathVariable(value = "codigo") String codigo){
		String query = "SELECT * FROM ta_acn_bitacoras WHERE codigo_usuario = '"+codigo+"'";
		List<Bitacora> bitacoras = new ArrayList<Bitacora>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
		
		for(Map row: rows) {
			Bitacora objeto = new Bitacora();
			
			objeto.setId_bitacora(Long.parseLong(String.valueOf(row.get("id_bitacora"))));
			objeto.setCodigo_usuario((String) row.get("codigo_usuario"));
			objeto.setAccion(Integer.parseInt(String.valueOf(row.get("accion"))));
			objeto.setDescripcion((String) row.get("descripcion"));
			objeto.setFecha((Date) row.get("fecha"));
			objeto.setIp((String) row.get("ip"));
			
			bitacoras.add(objeto);
		}
		return bitacoras;
	}
      
}