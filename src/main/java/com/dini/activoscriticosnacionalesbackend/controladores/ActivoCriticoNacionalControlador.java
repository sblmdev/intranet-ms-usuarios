package com.dini.activoscriticosnacionalesbackend.controladores;

import com.dini.activoscriticosnacionalesbackend.excepciones.ResourceNotFoundException;

import java.io.File;
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
import com.dini.activoscriticosnacionalesbackend.entidades.FileServer;
import com.dini.activoscriticosnacionalesbackend.entidades.ObjetivoNacional;
import com.dini.activoscriticosnacionalesbackend.repositorios.ActivoCriticoNacionalRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/activoCriticoNacional")
public class ActivoCriticoNacionalControlador {
	
	@Autowired
    private ActivoCriticoNacionalRepositorio activoCriticoNacionalRepositorio;
	
	@Autowired
    private FileServerControlador fileServerControlador;
	
	@Autowired
	JdbcTemplate jdbctemplate;

    @GetMapping("/listar")
    public List<ActivoCriticoNacional> getAllActivosCriticosNacionales() {
        return activoCriticoNacionalRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<ActivoCriticoNacional> getActivoCriticoNacionalById(@PathVariable(value = "id") long id_acn)
        throws ResourceNotFoundException {
    	ActivoCriticoNacional acn = activoCriticoNacionalRepositorio.findById(id_acn)
          .orElseThrow(() -> new ResourceNotFoundException("ActivoCriticoNacional no encontrado para id :: " + id_acn));
        return ResponseEntity.ok().body(acn);
    }

    @PostMapping("/registrar")
    public ActivoCriticoNacional createActivoCriticoNacional(@Valid @RequestBody ActivoCriticoNacional acn) {
    	Date fecha = new Date();
    	acn.setFecha_creacion(fecha);
    	
    	FileServer f = new FileServer();
    	List<FileServer> fileServers = fileServerControlador.getAllFilesServers();
    	for(int i=0; i<fileServers.size(); i++) {
    		if(fileServers.get(i).getEstado_file_server()==1) {
    			f = fileServers.get(i);
    		}
    	}
    		
    	File directorio = new File(f.getDir_file_server()+"acns\\"+acn.getCodigo_acn());
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
    	
        return activoCriticoNacionalRepositorio.save(acn);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ActivoCriticoNacional> updateActivoCriticoNacional(@PathVariable(value = "id") Long id_acn,
         @Valid @RequestBody ActivoCriticoNacional acn_detalle) throws ResourceNotFoundException {
    	ActivoCriticoNacional acn = activoCriticoNacionalRepositorio.findById(id_acn)
        .orElseThrow(() -> new ResourceNotFoundException("ActivoCriticoNacional no encontrado para id :: " + id_acn));

    	acn.setAcn_denominacion(acn_detalle.getAcn_denominacion()); 
        acn.setEstado_acn(acn_detalle.getEstado_acn());
        
        Date fecha = new Date(); 
        acn.setId_usuario_creacion(acn_detalle.getId_usuario_creacion());
        acn.setFecha_creacion(acn_detalle.getFecha_creacion());
        acn.setIp_creacion(acn_detalle.getIp_creacion());
        acn.setId_usuario_modificacion(acn_detalle.getId_usuario_modificacion());
        if(acn_detalle.getId_acn()==0) {
        	acn.setFecha_modificacion(null);
        }
        else {
        	acn.setFecha_modificacion(fecha);
        }
        acn.setIp_modificacion(acn_detalle.getIp_modificacion());
        
        final ActivoCriticoNacional acn_actualizado = activoCriticoNacionalRepositorio.save(acn);
        return ResponseEntity.ok(acn_actualizado);
    }
    
    @GetMapping("/listarActivos")
	private List<ActivoCriticoNacional> acnsActivos(){
		String query = "SELECT * FROM ta_acn_acn WHERE estado_acn = 1 ORDER BY acn_denominacion";
		List<ActivoCriticoNacional> acns = new ArrayList<ActivoCriticoNacional>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			ActivoCriticoNacional objeto = new ActivoCriticoNacional();
			
			objeto.setId_acn(Long.parseLong(String.valueOf(row.get("id_acn"))));
			objeto.setCodigo_acn((String) row.get("codigo_acn"));
			objeto.setAcn_denominacion((String) row.get("acn_denominacion"));
			objeto.setEstado_acn(Integer.parseInt(String.valueOf(row.get("estado_acn"))));
			
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
    
    @GetMapping("/listarCoincidencias/{buscar}")
	private List<ActivoCriticoNacional> acnsCoincidencias(@PathVariable(value = "buscar") String cadena)
	        throws ResourceNotFoundException {
    	String query = "SELECT * FROM ta_acn_acn WHERE estado_acn = 1 AND acn_denominacion like '%"+cadena+"%' ORDER BY acn_denominacion";
		List<ActivoCriticoNacional> acns = new ArrayList<ActivoCriticoNacional>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			ActivoCriticoNacional objeto = new ActivoCriticoNacional();
			
			objeto.setId_acn(Long.parseLong(String.valueOf(row.get("id_acn"))));
			objeto.setCodigo_acn((String) row.get("codigo_acn"));
			objeto.setAcn_denominacion((String) row.get("acn_denominacion"));
			objeto.setEstado_acn(Integer.parseInt(String.valueOf(row.get("estado_acn"))));
			
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