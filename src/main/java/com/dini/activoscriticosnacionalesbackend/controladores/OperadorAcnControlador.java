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

import com.dini.activoscriticosnacionalesbackend.entidades.ActivoCriticoNacionalSector;
import com.dini.activoscriticosnacionalesbackend.entidades.FileServer;
import com.dini.activoscriticosnacionalesbackend.entidades.OperadorAcn;
import com.dini.activoscriticosnacionalesbackend.repositorios.OperadorAcnRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/operadorAcn")
public class OperadorAcnControlador {
	
	@Autowired
    private OperadorAcnRepositorio operadorAcnRepositorio;
	
	@Autowired
    private FileServerControlador fileServerControlador;
	
	@Autowired
	JdbcTemplate jdbctemplate;

    @GetMapping("/listar")
    public List<OperadorAcn> getAllOperadoresAcn() {
        return operadorAcnRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<OperadorAcn> getOperadorDescripcionById(@PathVariable(value = "id") long id_operador_acn)
        throws ResourceNotFoundException {
    	OperadorAcn operador_acn = operadorAcnRepositorio.findById(id_operador_acn)
          .orElseThrow(() -> new ResourceNotFoundException("OperadorAcn no encontrado para id :: " + id_operador_acn));
        return ResponseEntity.ok().body(operador_acn);
    }

    @PostMapping("/registrar")
    public OperadorAcn createOperadorAcn(@Valid @RequestBody OperadorAcn operador_acn) {
    	Date fecha = new Date();
    	operador_acn.setFecha_creacion(fecha);
    	
    	FileServer f = new FileServer();
    	List<FileServer> fileServers = fileServerControlador.getAllFilesServers();
    	for(int i=0; i<fileServers.size(); i++) {
    		if(fileServers.get(i).getEstado_file_server()==1) {
    			f = fileServers.get(i);
    		}
    	}
    		
    	File directorio = new File(f.getDir_file_server()+"acns\\"+operador_acn.getCodigo_acn()+"\\"+operador_acn.getCodigo_operador());
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        
        directorio = new File(f.getDir_file_server()+"acns\\"+operador_acn.getCodigo_acn()+"\\"+operador_acn.getCodigo_operador()+"\\coordenadas");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
    	
        return operadorAcnRepositorio.save(operador_acn);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<OperadorAcn> updateOperadorAcn(@PathVariable(value = "id") Long id_operador_acn,
         @Valid @RequestBody OperadorAcn operador_acn_detalle) throws ResourceNotFoundException {
    	OperadorAcn operador_acn = operadorAcnRepositorio.findById(id_operador_acn)
        .orElseThrow(() -> new ResourceNotFoundException("OperadorAcn no encontrado para id :: " + id_operador_acn));

        operador_acn.setCodigo_operador(operador_acn_detalle.getCodigo_operador());
        operador_acn.setCodigo_acn(operador_acn_detalle.getCodigo_acn());
        operador_acn.setId_operador(operador_acn_detalle.getId_operador());
        operador_acn.setId_acn(operador_acn_detalle.getId_acn());
        operador_acn.setEstado_operador_acn(operador_acn_detalle.getEstado_operador_acn());
        operador_acn.setFecha_inicio_operador_acn(operador_acn_detalle.getFecha_inicio_operador_acn());
        
        Date fecha = new Date();
        operador_acn.setId_usuario_creacion(operador_acn_detalle.getId_usuario_creacion());
        operador_acn.setFecha_creacion(operador_acn_detalle.getFecha_creacion());
        operador_acn.setIp_creacion(operador_acn_detalle.getIp_creacion());
        operador_acn.setId_usuario_modificacion(operador_acn_detalle.getId_usuario_modificacion());
        if(operador_acn_detalle.getId_operador_acn()==0) {
        	operador_acn.setFecha_modificacion(null);
        }
        else {
        	operador_acn.setFecha_modificacion(fecha);
        }
        operador_acn.setIp_modificacion(operador_acn_detalle.getIp_modificacion());

        final OperadorAcn operador_acn_actualizado = operadorAcnRepositorio.save(operador_acn);
        return ResponseEntity.ok(operador_acn_actualizado);
    }
    
    @GetMapping("/listarActivos/{acn}")
	private List<OperadorAcn> operadoresAcnActivos(@PathVariable(value = "acn") String acn){
		String query = "SELECT * FROM ta_acn_opeacn WHERE estado_operador_acn = 1 AND codigo_acn = '"+acn+"'";
		List<OperadorAcn> acns = new ArrayList<OperadorAcn>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			OperadorAcn objeto = new OperadorAcn();
			
			objeto.setId_operador_acn(Long.parseLong(String.valueOf(row.get("id_operador_acn"))));
			objeto.setCodigo_acn((String) row.get("codigo_acn"));
			objeto.setId_acn(Long.parseLong(String.valueOf(row.get("id_acn"))));
			objeto.setCodigo_operador((String) row.get("codigo_operador"));
			objeto.setId_operador(Long.parseLong(String.valueOf(row.get("id_operador"))));
			objeto.setEstado_operador_acn(Integer.parseInt(String.valueOf(row.get("estado_operador_acn"))));
			objeto.setFecha_inicio_operador_acn((Date)row.get("fecha_inicio_operador_acn"));
			
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