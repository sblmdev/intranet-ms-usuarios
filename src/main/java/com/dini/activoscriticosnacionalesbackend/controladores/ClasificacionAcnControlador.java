package com.dini.activoscriticosnacionalesbackend.controladores;

import com.dini.activoscriticosnacionalesbackend.excepciones.ResourceNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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

import com.dini.activoscriticosnacionalesbackend.entidades.CapacidadNacional;
import com.dini.activoscriticosnacionalesbackend.entidades.ClasificacionAcn;
import com.dini.activoscriticosnacionalesbackend.entidades.FileServer;
import com.dini.activoscriticosnacionalesbackend.entidades.Sector;
import com.dini.activoscriticosnacionalesbackend.entidades.SubCapacidadNacional;
import com.dini.activoscriticosnacionalesbackend.repositorios.ClasificacionAcnRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/clasificacionAcn")
public class ClasificacionAcnControlador {
	
	@Autowired
    private ClasificacionAcnRepositorio clasificacionAcnRepositorio;
	
	@Autowired
    private FileServerControlador fileServerControlador;
	
	@Autowired
	JdbcTemplate jdbctemplate;

    @GetMapping("/listar")
    public List<ClasificacionAcn> getAllClasificacionesAcn() {
        return clasificacionAcnRepositorio.findAll();
    }
    
    @GetMapping("/listarActivos")
	private List<ClasificacionAcn> clasificacionesAcnActivas(){
		String query = "SELECT * FROM ta_acn_clasificacionesacn "
				+ "WHERE estado_clasificacion_acn = 1 "
				+ "ORDER BY nombre_clasificacion_acn";
		List<ClasificacionAcn> clasificaciones_acn = new ArrayList<ClasificacionAcn>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			ClasificacionAcn objeto = new ClasificacionAcn();
			
			objeto.setId_clasificacion_acn(Long.parseLong(String.valueOf(row.get("id_clasificacion_acn"))));
			objeto.setCodigo_clasificacion_acn((String) row.get("codigo_clasificacion_acn"));
			objeto.setNombre_clasificacion_acn((String) row.get("nombre_clasificacion_acn"));
			objeto.setDescripcion_clasificacion_acn((String) row.get("descripcion_clasificacion_acn"));
			objeto.setEstado_clasificacion_acn(Integer.parseInt(String.valueOf(row.get("estado_clasificacion_acn"))));
			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
			objeto.setCodigo_sub_capacidad_nacional((String) row.get("codigo_sub_capacidad_nacional"));
			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
			objeto.setId_sub_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_sub_capacidad_nacional"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			clasificaciones_acn.add(objeto);
		}
		return clasificaciones_acn;
	}
    
    @GetMapping("/listarActivosPorCapacidad/{capacidad}")
	private List<ClasificacionAcn> clasificacionesAcnActivasPorCapacidad(@PathVariable(value = "capacidad") String codigo_capacidad){

		String query = "SELECT * FROM ta_acn_clasificacionesacn "
				+ "WHERE estado_clasificacion_acn = 1 "
				+ "AND codigo_capacidad_nacional='"+codigo_capacidad+"' "
				+ "ORDER BY nombre_clasificacion_acn";
		List<ClasificacionAcn> clasificaciones_acn = new ArrayList<ClasificacionAcn>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			ClasificacionAcn objeto = new ClasificacionAcn();

			objeto.setId_clasificacion_acn(Long.parseLong(String.valueOf(rows.get(0).get("id_clasificacion_acn"))));
			objeto.setCodigo_clasificacion_acn((String) row.get("codigo_clasificacion_acn"));
			objeto.setNombre_clasificacion_acn((String) row.get("nombre_clasificacion_acn"));
			objeto.setDescripcion_clasificacion_acn((String) row.get("descripcion_clasificacion_acn"));
			objeto.setEstado_clasificacion_acn(Integer.parseInt(String.valueOf(rows.get(0).get("estado_clasificacion_acn"))));
			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
			objeto.setCodigo_sub_capacidad_nacional((String) row.get("codigo_sub_capacidad_nacional"));
			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
			objeto.setId_sub_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_sub_capacidad_nacional"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(rows.get(0).get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(rows.get(0).get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			clasificaciones_acn.add(objeto);
		}
		return clasificaciones_acn;
	}
    
    @GetMapping("/listarActivosPorSubCapacidad/{subCapacidad}")
	private List<ClasificacionAcn> clasificacionesAcnActivas(@PathVariable(value = "subCapacidad") String codigo_sub_capacidad){

		String query = "SELECT * FROM ta_acn_clasificacionesacn "
				+ "WHERE estado_clasificacion_acn = 1 "
				+ "AND codigo_sub_capacidad_nacional='"+codigo_sub_capacidad+"' "
				+ "ORDER BY nombre_clasificacion_acn";
		List<ClasificacionAcn> clasificaciones_acn = new ArrayList<ClasificacionAcn>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			ClasificacionAcn objeto = new ClasificacionAcn();

			objeto.setId_clasificacion_acn(Long.parseLong(String.valueOf(rows.get(0).get("id_clasificacion_acn"))));
			objeto.setCodigo_clasificacion_acn((String) row.get("codigo_clasificacion_acn"));
			objeto.setNombre_clasificacion_acn((String) row.get("nombre_clasificacion_acn"));
			objeto.setDescripcion_clasificacion_acn((String) row.get("descripcion_clasificacion_acn"));
			objeto.setEstado_clasificacion_acn(Integer.parseInt(String.valueOf(rows.get(0).get("estado_clasificacion_acn"))));
			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
			objeto.setCodigo_sub_capacidad_nacional((String) row.get("codigo_sub_capacidad_nacional"));
			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
			objeto.setId_sub_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_sub_capacidad_nacional"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(rows.get(0).get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(rows.get(0).get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			clasificaciones_acn.add(objeto);
		}
		return clasificaciones_acn;
	}
    
    @GetMapping("/listarCoincidencias/{buscar}")
	private List<ClasificacionAcn> clasificacionesAcnCoincidencias(@PathVariable(value = "buscar") String cadena){
		String query = "SELECT * FROM ta_acn_clasificacionesacn "
				+ "WHERE estado_clasificacion_acn = 1 AND nombre_clasificacion_acn like '"+cadena+"%'"
				+ "ORDER BY nombre_clasificacion_acn";
		List<ClasificacionAcn> clasificaciones_acn = new ArrayList<ClasificacionAcn>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			ClasificacionAcn objeto = new ClasificacionAcn();
			
			objeto.setId_clasificacion_acn(Long.parseLong(String.valueOf(row.get("id_clasificacion_acn"))));
			objeto.setCodigo_clasificacion_acn((String) row.get("codigo_clasificacion_acn"));
			objeto.setNombre_clasificacion_acn((String) row.get("nombre_clasificacion_acn"));
			objeto.setDescripcion_clasificacion_acn((String) row.get("descripcion_clasificacion_acn"));
			objeto.setEstado_clasificacion_acn(Integer.parseInt(String.valueOf(row.get("estado_clasificacion_acn"))));
			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
			objeto.setCodigo_sub_capacidad_nacional((String) row.get("codigo_sub_capacidad_nacional"));
			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
			objeto.setId_sub_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_sub_capacidad_nacional"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			clasificaciones_acn.add(objeto);
		}
		return clasificaciones_acn;
	}
    
    @GetMapping("/listarIguales/{buscar}")
	private List<ClasificacionAcn> clasificacionesAcnIguales(@PathVariable(value = "buscar") String cadena){
		String query = "SELECT * FROM ta_acn_clasificacionesacn WHERE UPPER(nombre_clasificacion_acn) = UPPER('"+cadena+"')";
		List<ClasificacionAcn> clasificaciones_acn = new ArrayList<ClasificacionAcn>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			ClasificacionAcn objeto = new ClasificacionAcn();
			
			objeto.setId_clasificacion_acn(Long.parseLong(String.valueOf(row.get("id_clasificacion_acn"))));
			objeto.setCodigo_clasificacion_acn((String) row.get("codigo_clasificacion_acn"));
			objeto.setNombre_clasificacion_acn((String) row.get("nombre_clasificacion_acn"));
			objeto.setDescripcion_clasificacion_acn((String) row.get("descripcion_clasificacion_acn"));
			objeto.setEstado_clasificacion_acn(Integer.parseInt(String.valueOf(row.get("estado_clasificacion_acn"))));
			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
			objeto.setCodigo_sub_capacidad_nacional((String) row.get("codigo_sub_capacidad_nacional"));
			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
			objeto.setId_sub_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_sub_capacidad_nacional"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			clasificaciones_acn.add(objeto);
		}
		return clasificaciones_acn;
	}
    
    @GetMapping("/listarCoincidenciasPorCapacidad/{buscar}/{capacidad}")
   	private List<ClasificacionAcn> clasificacionesAcnCoincidenciasPorCapacidad(@PathVariable(value = "buscar") String cadena, @PathVariable(value = "capacidad") String capacidad){
   		String query = "SELECT * FROM ta_acn_clasificacionesacn "
   				+ "WHERE estado_clasificacion_acn = 1 AND "
   				+ "codigo_capacidad_nacional = '"+capacidad+"' AND "
   				+ "nombre_clasificacion_acn like '"+cadena+"%'"
   				+ "ORDER BY nombre_clasificacion_acn";
   		List<ClasificacionAcn> clasificaciones_acn = new ArrayList<ClasificacionAcn>();
   		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
   		
   		for(Map row: rows) {
   			ClasificacionAcn objeto = new ClasificacionAcn();
   			
   			objeto.setId_clasificacion_acn(Long.parseLong(String.valueOf(row.get("id_clasificacion_acn"))));
   			objeto.setCodigo_clasificacion_acn((String) row.get("codigo_clasificacion_acn"));
   			objeto.setNombre_clasificacion_acn((String) row.get("nombre_clasificacion_acn"));
   			objeto.setDescripcion_clasificacion_acn((String) row.get("descripcion_clasificacion_acn"));
   			objeto.setEstado_clasificacion_acn(Integer.parseInt(String.valueOf(row.get("estado_clasificacion_acn"))));
   			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
   			objeto.setCodigo_sub_capacidad_nacional((String) row.get("codigo_sub_capacidad_nacional"));
   			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
			objeto.setId_sub_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_sub_capacidad_nacional"))));
   			
   			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
   			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
   			objeto.setIp_creacion((String) row.get("ip_creacion"));
   			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
   			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
   			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
   			
   			clasificaciones_acn.add(objeto);
   		}
   		return clasificaciones_acn;
   	}
    
    @GetMapping("/listarCoincidenciasPorSubCapacidad/{buscar}/{subcapacidad}")
   	private List<ClasificacionAcn> clasificacionesAcnCoincidenciasPorSubCapacidad(@PathVariable(value = "buscar") String cadena, @PathVariable(value = "subcapacidad") String subcapacidad){
   		String query = "SELECT * FROM ta_acn_clasificacionesacn "
   				+ "WHERE estado_clasificacion_acn = 1 AND "
   				+ "codigo_sub_capacidad_nacional = '"+subcapacidad+"' AND "
   				+ "nombre_clasificacion_acn like '"+cadena+"%'"
   				+ "ORDER BY nombre_clasificacion_acn";
   		List<ClasificacionAcn> clasificaciones_acn = new ArrayList<ClasificacionAcn>();
   		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
   		
   		for(Map row: rows) {
   			ClasificacionAcn objeto = new ClasificacionAcn();
   			
   			objeto.setId_clasificacion_acn(Long.parseLong(String.valueOf(row.get("id_clasificacion_acn"))));
   			objeto.setCodigo_clasificacion_acn((String) row.get("codigo_clasificacion_acn"));
   			objeto.setNombre_clasificacion_acn((String) row.get("nombre_clasificacion_acn"));
   			objeto.setDescripcion_clasificacion_acn((String) row.get("descripcion_clasificacion_acn"));
   			objeto.setEstado_clasificacion_acn(Integer.parseInt(String.valueOf(row.get("estado_clasificacion_acn"))));
   			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
   			objeto.setCodigo_sub_capacidad_nacional((String) row.get("codigo_sub_capacidad_nacional"));
   			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
			objeto.setId_sub_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_sub_capacidad_nacional"))));
   			
   			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
   			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
   			objeto.setIp_creacion((String) row.get("ip_creacion"));
   			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
   			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
   			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
   			
   			clasificaciones_acn.add(objeto);
   		}
   		return clasificaciones_acn;
   	}
    
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<ClasificacionAcn> getClasificacionAcnById(@PathVariable(value = "id") long id_clasificacion_acn)
        throws ResourceNotFoundException {
        ClasificacionAcn clasificacion_acn = clasificacionAcnRepositorio.findById(id_clasificacion_acn)
          .orElseThrow(() -> new ResourceNotFoundException("Clasificacion Acn no encontrado para id :: " + id_clasificacion_acn));
        return ResponseEntity.ok().body(clasificacion_acn);
    }

    @PostMapping("/registrar")
    public ClasificacionAcn createClasificacionAcn(@Valid @RequestBody ClasificacionAcn clasificacion_acn) {
    	Date fecha = new Date();
    	clasificacion_acn.setFecha_creacion(fecha);
    	
    	FileServer f = new FileServer();
    	List<FileServer> fileServers = fileServerControlador.getAllFilesServers();
    	for(int i=0; i<fileServers.size(); i++) {
    		if(fileServers.get(i).getEstado_file_server()==1) {
    			f = fileServers.get(i);
    		}
    	}
    	
    	File directorio = new File(f.getDir_file_server()+"clasificaciones_acn\\"+clasificacion_acn.getCodigo_clasificacion_acn());
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        
        return clasificacionAcnRepositorio.save(clasificacion_acn);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ClasificacionAcn> updateClasificacionAcn(@PathVariable(value = "id") Long id_clasificacion_acn,
         @Valid @RequestBody ClasificacionAcn clasificacion_acn_detalle) throws ResourceNotFoundException {
        ClasificacionAcn clasificacion_acn = clasificacionAcnRepositorio.findById(id_clasificacion_acn)
        .orElseThrow(() -> new ResourceNotFoundException("Clasificacion Acn no encontrado para id :: " + id_clasificacion_acn));

        clasificacion_acn.setNombre_clasificacion_acn(clasificacion_acn_detalle.getNombre_clasificacion_acn());
        clasificacion_acn.setDescripcion_clasificacion_acn(clasificacion_acn_detalle.getDescripcion_clasificacion_acn());
        clasificacion_acn.setEstado_clasificacion_acn(clasificacion_acn_detalle.getEstado_clasificacion_acn());
        clasificacion_acn.setCodigo_capacidad_nacional(clasificacion_acn_detalle.getCodigo_capacidad_nacional());
        clasificacion_acn.setCodigo_sub_capacidad_nacional(clasificacion_acn_detalle.getCodigo_sub_capacidad_nacional());
        clasificacion_acn.setId_capacidad_nacional(clasificacion_acn_detalle.getId_capacidad_nacional());
        clasificacion_acn.setId_sub_capacidad_nacional(clasificacion_acn_detalle.getId_sub_capacidad_nacional());
        
        Date fecha = new Date();
        clasificacion_acn.setId_usuario_creacion(clasificacion_acn_detalle.getId_usuario_creacion());
        clasificacion_acn.setFecha_creacion(clasificacion_acn_detalle.getFecha_creacion());
        clasificacion_acn.setIp_creacion(clasificacion_acn_detalle.getIp_creacion());
        clasificacion_acn.setId_usuario_modificacion(clasificacion_acn_detalle.getId_usuario_modificacion());
        if(clasificacion_acn_detalle.getId_clasificacion_acn()==0) {
        	clasificacion_acn.setFecha_modificacion(null);
        }
        else {
        	clasificacion_acn.setFecha_modificacion(fecha);
        }
        clasificacion_acn.setIp_modificacion(clasificacion_acn_detalle.getIp_modificacion());
        
        final ClasificacionAcn clasificacion_acn_actualizado = clasificacionAcnRepositorio.save(clasificacion_acn);
        return ResponseEntity.ok(clasificacion_acn_actualizado);
    }
    
    @PostMapping(value="/reporteGeneral", produces = "application/json")
    private String reporteGeneralClasificacionesAcn(@Valid @RequestBody List<Object> lista)throws ResourceNotFoundException, InvalidFormatException {
    	
    	String query = "";
    	if(lista.get(6).toString().equals("")) {
    		if(lista.get(5).toString().equals("")) {
    			if(lista.get(4).toString().equals("")) {
    				query = "SELECT * FROM ta_acn_clasificacionesacn WHERE estado_clasificacion_acn=1 ORDER BY nombre_clasificacion_acn";
    			}
    			else {
    				query = "SELECT * FROM ta_acn_clasificacionesacn WHERE estado_clasificacion_acn=1 AND codigo_sub_capacidad_nacional='"+lista.get(4).toString()+"' ORDER BY nombre_clasificacion_acn";
    			}
    		}
    		else {
    			if(lista.get(4).toString().equals("")) {
    				query = "SELECT * FROM ta_acn_clasificacionesacn WHERE estado_clasificacion_acn=1 AND codigo_capacidad_nacional='"+lista.get(5).toString()+"' ORDER BY nombre_clasificacion_acn";
    			}
    			else {
    				query = "SELECT * FROM ta_acn_clasificacionesacn WHERE estado_clasificacion_acn=1 AND codigo_capacidad_nacional='"+lista.get(5).toString()+"' AND codigo_sub_capacidad_nacional='"+lista.get(4).toString()+"' ORDER BY nombre_clasificacion_acn";
    			}
    		}
    	}
    	else {
    		if(lista.get(5).toString().equals("")) {
    			if(lista.get(4).toString().equals("")) {
    				query = "SELECT * FROM ta_acn_clasificacionesacn WHERE estado_clasificacion_acn=1 AND nombre_clasificacion_acn like = '"+lista.get(6).toString()+"%' ORDER BY nombre_clasificacion_acn";
    			}
    			else {
    				query = "SELECT * FROM ta_acn_clasificacionesacn WHERE estado_clasificacion_acn=1 AND nombre_clasificacion_acn like = '"+lista.get(6).toString()+"%' AND codigo_sub_capacidad_nacional='"+lista.get(4).toString()+"' ORDER BY nombre_clasificacion_acn";
    			}
    		}
    		else {
    			if(lista.get(4).toString().equals("")) {
    				query = "SELECT * FROM ta_acn_clasificacionesacn WHERE estado_clasificacion_acn=1 AND nombre_clasificacion_acn like = '"+lista.get(6).toString()+"%' AND codigo_capacidad_nacional='"+lista.get(5).toString()+"' ORDER BY nombre_clasificacion_acn";
    			}
    			else {
    				query = "SELECT * FROM ta_acn_clasificacionesacn WHERE estado_clasificacion_acn=1 AND nombre_clasificacion_acn like = '"+lista.get(6).toString()+"%' AND codigo_capacidad_nacional='"+lista.get(5).toString()+"' AND codigo_sub_capacidad_nacional='"+lista.get(3).toString()+"' ORDER BY nombre_clasificacion_acn";
    			}
    		}
    	}

    	boolean problema = false;
    	List<ClasificacionAcn> clasificaciones_acn = new ArrayList<ClasificacionAcn>();
    	try {
    		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
    		
    		for(Map row: rows) {
    			
    			ClasificacionAcn objeto = new ClasificacionAcn();
    			
    			objeto.setId_clasificacion_acn(Long.parseLong(String.valueOf(row.get("id_clasificacion_acn"))));
    			objeto.setCodigo_clasificacion_acn((String) row.get("codigo_clasificacion_acn"));
    			objeto.setNombre_clasificacion_acn((String) row.get("nombre_clasificacion_acn"));
    			objeto.setDescripcion_clasificacion_acn((String) row.get("descripcion_clasificacion_acn"));
    			objeto.setEstado_clasificacion_acn(Integer.parseInt(String.valueOf(row.get("estado_clasificacion_acn"))));
    			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
    			objeto.setCodigo_sub_capacidad_nacional((String) row.get("codigo_sub_capacidad_nacional"));
    			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
    			objeto.setId_sub_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_sub_capacidad_nacional"))));
    			
    			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
    			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
    			objeto.setIp_creacion((String) row.get("ip_creacion"));
    			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
    			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
    			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
    			
    			try {
    				String query2 = "SELECT * FROM ta_acn_capnacionales WHERE estado_capacidad_nacional=1 AND codigo_capacidad_nacional='"+objeto.getCodigo_capacidad_nacional()+"' ORDER BY nombre_capacidad_nacional";
        			List<CapacidadNacional> capacidades_nacionales = new ArrayList<CapacidadNacional>();
        			List<Map<String, Object>> rows2 = jdbctemplate.queryForList(query2);
        			for(Map row2: rows2) {
        				CapacidadNacional objeto2 = new CapacidadNacional();		
        				objeto2.setNombre_capacidad_nacional((String) row2.get("nombre_capacidad_nacional"));
        				capacidades_nacionales.add(objeto2);
        			}
        			objeto.setCodigo_capacidad_nacional(capacidades_nacionales.get(0).getNombre_capacidad_nacional());
    			}
    			catch(Exception e) {
    				problema = true;
    			}
    			
    			if(!objeto.getCodigo_sub_capacidad_nacional().equals("")) {
   
    				try {
        				String query3 = "SELECT * FROM ta_acn_subcapnacionales WHERE estado_sub_capacidad_nacional=1 AND codigo_sub_capacidad_nacional='"+objeto.getCodigo_sub_capacidad_nacional()+"' ORDER BY nombre_sub_capacidad_nacional";
            			List<SubCapacidadNacional> sub_capacidades_nacionales = new ArrayList<SubCapacidadNacional>();
            			List<Map<String, Object>> rows3 = jdbctemplate.queryForList(query3);
            			for(Map row3: rows3) {
            				SubCapacidadNacional objeto3 = new SubCapacidadNacional();		
            				objeto3.setNombre_sub_capacidad_nacional((String) row3.get("nombre_sub_capacidad_nacional"));
            				sub_capacidades_nacionales.add(objeto3);
            			}
            			objeto.setCodigo_sub_capacidad_nacional(sub_capacidades_nacionales.get(0).getNombre_sub_capacidad_nacional());
        			}
        			catch(Exception e) {
        				problema = true;
        			}
    			}
    			
    			clasificaciones_acn.add(objeto);
    			
    		}
    		
    	}
    	catch(Exception e) {
    		problema = true;
    	}
    	
    	if(!problema) {
    		String excelFilePath = "Reporte Clasificaciones ACN.xlsx";
        	
        	try {
        		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
                Workbook workbook = WorkbookFactory.create(inputStream);
     
                Sheet sheet = workbook.getSheetAt(0);
     
                int rowCount = 4;
                
                Row row = sheet.getRow(++rowCount);
            	
            	int columnCount = 0;
            	Cell cell = row.getCell(columnCount);
                cell.setCellValue("N°");
                columnCount ++;
                
                if(lista.get(0).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Clasificación ACN");
                	columnCount ++;
                }
                
                if(lista.get(3).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Sub Capacidad Nacional");
                	columnCount ++;
                }
                
                if(lista.get(2).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Capacidad Nacional");
                	columnCount ++;
                }
                
                if(lista.get(1).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Descripción");
                	columnCount ++;
                }
                
     
                for(int i=0; i<clasificaciones_acn.size(); i++) {
                	row = sheet.getRow(++rowCount);
                	
                	columnCount = 0;
                	cell = row.getCell(columnCount);
                    cell.setCellValue(i+1);
                    columnCount ++;
                    
                    if(lista.get(0).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(clasificaciones_acn.get(i).getNombre_clasificacion_acn());
                    	columnCount ++;
                    }
                    
                    if(lista.get(2).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	if(clasificaciones_acn.get(i).getCodigo_sub_capacidad_nacional().equals("")) {
                        	cell.setCellValue("Sin Sub Capacidad Nacional");
                    	}
                    	else {
                        	cell.setCellValue(clasificaciones_acn.get(i).getCodigo_sub_capacidad_nacional());
                    	}
                    	columnCount ++;
                    }
                    
                    
                    if(lista.get(3).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(clasificaciones_acn.get(i).getCodigo_capacidad_nacional());
                    	columnCount ++;
                    }
                    
                    if(lista.get(1).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(clasificaciones_acn.get(i).getDescripcion_clasificacion_acn());
                    	columnCount ++;
                    }
                        
                }
                
                inputStream.close();

                FileOutputStream outputStream = new FileOutputStream("C:/Proyectos Angular/activos-criticos-nacionales/activos-criticos-nacionales-backend/temporal.xlsx");
                workbook.write(outputStream);
                outputStream.close();
                
                File file = new File("C:/Proyectos Angular/activos-criticos-nacionales/activos-criticos-nacionales-backend/temporal.xlsx");
                
                byte[] input_file = Files.readAllBytes(Paths.get("C:/Proyectos Angular/activos-criticos-nacionales/activos-criticos-nacionales-backend/temporal.xlsx"));
                
                byte[] encodedBytes = Base64.encodeBase64(input_file);
                
                String excelInBase64 = new String(encodedBytes);
                
                if(file.exists()) {
                	file.delete();
                }
                
                return excelInBase64;
        	}
        	catch(Exception e) {
        		return "";	
        	}
    	}
    	else {
    		return "";
    	}
        	
	}
    
    @GetMapping(value="/reporteParticular/{id}", produces = "application/json")
    private String reporteParticularClasificacionAcn(@PathVariable(value = "id") Long id_clasificacion)throws ResourceNotFoundException, InvalidFormatException {
    	
    	boolean problema = false;
    	List<ClasificacionAcn> clasificaciones_acn = new ArrayList<ClasificacionAcn>();
    	try {
    		String query = "SELECT * FROM ta_acn_clasificacionesacn WHERE id_clasificacion_acn="+id_clasificacion.toString();
    		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
    		for(Map row: rows) {
    			ClasificacionAcn objeto = new ClasificacionAcn();
    			
    			objeto.setId_clasificacion_acn(Long.parseLong(String.valueOf(row.get("id_clasificacion_acn"))));
    			objeto.setCodigo_clasificacion_acn((String) row.get("codigo_clasificacion_acn"));
    			objeto.setNombre_clasificacion_acn((String) row.get("nombre_clasificacion_acn"));
    			objeto.setDescripcion_clasificacion_acn((String) row.get("descripcion_clasificacion_acn"));
    			objeto.setEstado_clasificacion_acn(Integer.parseInt(String.valueOf(row.get("estado_clasificacion_acn"))));
    			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
    			objeto.setCodigo_sub_capacidad_nacional((String) row.get("codigo_sub_capacidad_nacional"));
    			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
    			objeto.setId_sub_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_sub_capacidad_nacional"))));
    			
    			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
    			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
    			objeto.setIp_creacion((String) row.get("ip_creacion"));
    			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
    			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
    			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
    			
    			try {
    				String query2 = "SELECT * FROM ta_acn_capnacionales WHERE estado_capacidad_nacional=1 AND codigo_capacidad_nacional='"+objeto.getCodigo_capacidad_nacional()+"' ORDER BY nombre_capacidad_nacional";
        			List<CapacidadNacional> capacidades_nacionales = new ArrayList<CapacidadNacional>();
        			List<Map<String, Object>> rows2 = jdbctemplate.queryForList(query2);
        			for(Map row2: rows2) {
        				CapacidadNacional objeto2 = new CapacidadNacional();		
        				objeto2.setNombre_capacidad_nacional((String) row2.get("nombre_capacidad_nacional"));
        				capacidades_nacionales.add(objeto2);
        			}
        			objeto.setCodigo_capacidad_nacional(capacidades_nacionales.get(0).getNombre_capacidad_nacional());
    			}
    			catch(Exception e) {
    				problema = true;
    			}
    			
    			if(!objeto.getCodigo_sub_capacidad_nacional().equals("")) {
   
    				try {
        				String query3 = "SELECT * FROM ta_acn_subcapnacionales WHERE estado_sub_capacidad_nacional=1 AND codigo_sub_capacidad_nacional='"+objeto.getCodigo_sub_capacidad_nacional()+"' ORDER BY nombre_sub_capacidad_nacional";
            			List<SubCapacidadNacional> sub_capacidades_nacionales = new ArrayList<SubCapacidadNacional>();
            			List<Map<String, Object>> rows3 = jdbctemplate.queryForList(query3);
            			for(Map row3: rows3) {
            				SubCapacidadNacional objeto3 = new SubCapacidadNacional();		
            				objeto3.setNombre_sub_capacidad_nacional((String) row3.get("nombre_sub_capacidad_nacional"));
            				sub_capacidades_nacionales.add(objeto3);
            			}
            			objeto.setCodigo_sub_capacidad_nacional(sub_capacidades_nacionales.get(0).getNombre_sub_capacidad_nacional());
        			}
        			catch(Exception e) {
        				problema = true;
        			}
    			}
    			
    			clasificaciones_acn.add(objeto);
    		}
    	}
    	catch(Exception e) {
    		problema = true;
    	}
    	
   		
   		if(!problema) {
   			String excelFilePath = "Reporte Clasificacion ACN.xlsx";
   	    	
   	    	try {
   	            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
   	            Workbook workbook = WorkbookFactory.create(inputStream);
   	 
   	            Sheet sheet = workbook.getSheetAt(0);
   	 
   	            int rowCount = 4;
   	            Row row = sheet.getRow(++rowCount);
   	        	int columnCount = 2;
   	        	Cell cell = row.getCell(columnCount);
   	            cell.setCellValue(clasificaciones_acn.get(0).getNombre_clasificacion_acn());
   	            
   	            rowCount = 5;
	            row = sheet.getRow(++rowCount);
	        	columnCount = 2;
	        	cell = row.getCell(columnCount);
	        	if(clasificaciones_acn.get(0).getCodigo_sub_capacidad_nacional().equals("")) {
	        		cell.setCellValue("Sin Sub Capacidad Nacional");	
	        	}
	        	else {
	        		cell.setCellValue(clasificaciones_acn.get(0).getCodigo_sub_capacidad_nacional());
	        	}
	        	
   	            rowCount = 6;
   	            row = sheet.getRow(++rowCount);
   	        	columnCount = 2;
   	        	cell = row.getCell(columnCount);
   	            cell.setCellValue(clasificaciones_acn.get(0).getCodigo_capacidad_nacional());
   	            
   	            rowCount = 7;
	            row = sheet.getRow(++rowCount);
	        	columnCount = 2;
	        	cell = row.getCell(columnCount);
	            cell.setCellValue(clasificaciones_acn.get(0).getDescripcion_clasificacion_acn());
	            
   	            
   	            inputStream.close();

   	            FileOutputStream outputStream = new FileOutputStream("C:/Proyectos Angular/activos-criticos-nacionales/activos-criticos-nacionales-backend/temporal.xlsx");
   	            workbook.write(outputStream);
   	            outputStream.close();
   	            
   	            File file = new File("C:/Proyectos Angular/activos-criticos-nacionales/activos-criticos-nacionales-backend/temporal.xlsx");
   	            
   	            byte[] input_file = Files.readAllBytes(Paths.get("C:/Proyectos Angular/activos-criticos-nacionales/activos-criticos-nacionales-backend/temporal.xlsx"));
   	            
   	            byte[] encodedBytes = Base64.encodeBase64(input_file);
   	            
   	            String excelInBase64 = new String(encodedBytes);
   	            
   	            if(file.exists()) {
   	            	file.delete();
   	            }
   	            
   	            return excelInBase64;
   	        } catch (IOException ex) {
   	            ex.printStackTrace();
   	            return "";
   	        }
   	        catch (EncryptedDocumentException ex) {
   	            ex.printStackTrace();
   	            return "";
   	        }
   		}
   		else {
   			return "";
   		}
   		   	    	
	}
    
}