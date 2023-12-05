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
import com.dini.activoscriticosnacionalesbackend.entidades.FileServer;
import com.dini.activoscriticosnacionalesbackend.entidades.Sector;
import com.dini.activoscriticosnacionalesbackend.entidades.SubCapacidadNacional;
import com.dini.activoscriticosnacionalesbackend.repositorios.SubCapacidadNacionalRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/subCapacidadNacional")
public class SubCapacidadNacionalControlador {
	
	@Autowired
    private SubCapacidadNacionalRepositorio subCapacidadNacionalRepositorio;
	
	@Autowired
	JdbcTemplate jdbctemplate;

	@Autowired
    private FileServerControlador fileServerControlador;
	
    @GetMapping("/listar")
    public List<SubCapacidadNacional> getAllSubCapacidadesNacionales() {
        return subCapacidadNacionalRepositorio.findAll();
    }
    
    @GetMapping("/listarActivos")
	private List<SubCapacidadNacional> subCapacidadesNacionalesActivas(){
		String query = "SELECT * FROM ta_acn_subcapnacionales "
				+ "WHERE estado_sub_capacidad_nacional = 1 "
				+ "ORDER BY nombre_sub_capacidad_nacional";
		List<SubCapacidadNacional> sub_capacidad_nacional = new ArrayList<SubCapacidadNacional>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			SubCapacidadNacional objeto = new SubCapacidadNacional();
			
			objeto.setId_sub_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_sub_capacidad_nacional"))));
			objeto.setCodigo_sub_capacidad_nacional((String) row.get("codigo_sub_capacidad_nacional"));
			objeto.setNombre_sub_capacidad_nacional((String) row.get("nombre_sub_capacidad_nacional"));
			objeto.setDescripcion_sub_capacidad_nacional((String) row.get("descripcion_sub_capacidad_nacional"));
			objeto.setEstado_sub_capacidad_nacional(Integer.parseInt(String.valueOf(row.get("estado_sub_capacidad_nacional"))));
			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			sub_capacidad_nacional.add(objeto);
		}
		return sub_capacidad_nacional;
	}
    
    @GetMapping("/listarActivosPorCapacidad/{capacidad}")
	private List<SubCapacidadNacional> subCapacidadesNacionalesActivasPorCapacidad(@PathVariable(value = "capacidad") String capacidad){
		String query = "SELECT * FROM ta_acn_subcapnacionales "
				+ "WHERE estado_sub_capacidad_nacional = 1 AND "
				+ "codigo_capacidad_nacional = '"+capacidad+"'"
				+ "ORDER BY nombre_sub_capacidad_nacional";
		List<SubCapacidadNacional> sub_capacidad_nacional = new ArrayList<SubCapacidadNacional>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			SubCapacidadNacional objeto = new SubCapacidadNacional();
			
			objeto.setId_sub_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_sub_capacidad_nacional"))));
			objeto.setCodigo_sub_capacidad_nacional((String) row.get("codigo_sub_capacidad_nacional"));
			objeto.setNombre_sub_capacidad_nacional((String) row.get("nombre_sub_capacidad_nacional"));
			objeto.setDescripcion_sub_capacidad_nacional((String) row.get("descripcion_sub_capacidad_nacional"));
			objeto.setEstado_sub_capacidad_nacional(Integer.parseInt(String.valueOf(row.get("estado_sub_capacidad_nacional"))));
			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			sub_capacidad_nacional.add(objeto);
		}
		return sub_capacidad_nacional;
	}
    
    @GetMapping("/listarCoincidencias/{buscar}")
   	private List<SubCapacidadNacional> subCapacidadesNacionalesCoincidencias(@PathVariable(value = "buscar") String cadena){
   		String query = "SELECT * FROM ta_acn_subcapnacionales "
   				+ "WHERE estado_sub_capacidad_nacional = 1 AND nombre_sub_capacidad_nacional like '"+cadena+"%'"
   				+ "ORDER BY nombre_sub_capacidad_nacional";
   		List<SubCapacidadNacional> sub_capacidad_nacional = new ArrayList<SubCapacidadNacional>();
   		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
   		
   		for(Map row: rows) {
   			SubCapacidadNacional objeto = new SubCapacidadNacional();
   			
   			objeto.setId_sub_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_sub_capacidad_nacional"))));
   			objeto.setCodigo_sub_capacidad_nacional((String) row.get("codigo_sub_capacidad_nacional"));
   			objeto.setNombre_sub_capacidad_nacional((String) row.get("nombre_sub_capacidad_nacional"));
   			objeto.setDescripcion_sub_capacidad_nacional((String) row.get("descripcion_sub_capacidad_nacional"));
   			objeto.setEstado_sub_capacidad_nacional(Integer.parseInt(String.valueOf(row.get("estado_sub_capacidad_nacional"))));
   			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
   			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
   			
   			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
   			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
   			objeto.setIp_creacion((String) row.get("ip_creacion"));
   			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
   			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
   			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
   			
   			sub_capacidad_nacional.add(objeto);
   		}
   		return sub_capacidad_nacional;
   	}
    
    @GetMapping("/listarIguales/{buscar}")
   	private List<SubCapacidadNacional> subCapacidadesNacionalesIguales(@PathVariable(value = "buscar") String cadena){
   		String query = "SELECT * FROM ta_acn_subcapnacionales WHERE UPPER(nombre_sub_capacidad_nacional) = UPPER('"+cadena+"')";
   		List<SubCapacidadNacional> sub_capacidad_nacional = new ArrayList<SubCapacidadNacional>();
   		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
   		
   		for(Map row: rows) {
   			SubCapacidadNacional objeto = new SubCapacidadNacional();
   			
   			objeto.setId_sub_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_sub_capacidad_nacional"))));
   			objeto.setCodigo_sub_capacidad_nacional((String) row.get("codigo_sub_capacidad_nacional"));
   			objeto.setNombre_sub_capacidad_nacional((String) row.get("nombre_sub_capacidad_nacional"));
   			objeto.setDescripcion_sub_capacidad_nacional((String) row.get("descripcion_sub_capacidad_nacional"));
   			objeto.setEstado_sub_capacidad_nacional(Integer.parseInt(String.valueOf(row.get("estado_sub_capacidad_nacional"))));
   			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
   			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
   			
   			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
   			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
   			objeto.setIp_creacion((String) row.get("ip_creacion"));
   			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
   			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
   			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
   			
   			sub_capacidad_nacional.add(objeto);
   		}
   		return sub_capacidad_nacional;
   	}
    
    @GetMapping("/listarCoincidenciasPorCapacidad/{buscar}/{capacidad}")
   	private List<SubCapacidadNacional> subCapacidadesNacionalesCoincidenciasPorCapacidad(@PathVariable(value = "buscar") String cadena, @PathVariable(value = "capacidad") String capacidad){
   		String query = "SELECT * FROM ta_acn_subcapnacionales "
   				+ "WHERE estado_sub_capacidad_nacional = 1 AND "
   				+ "nombre_sub_capacidad_nacional like '"+cadena+"%' AND "
   				+ "codigo_capacidad_nacional = '"+capacidad+"' "
   				+ "ORDER BY nombre_sub_capacidad_nacional";
   		List<SubCapacidadNacional> sub_capacidad_nacional = new ArrayList<SubCapacidadNacional>();
   		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
   		
   		for(Map row: rows) {
   			SubCapacidadNacional objeto = new SubCapacidadNacional();
   			
   			objeto.setId_sub_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_sub_capacidad_nacional"))));
   			objeto.setCodigo_sub_capacidad_nacional((String) row.get("codigo_sub_capacidad_nacional"));
   			objeto.setNombre_sub_capacidad_nacional((String) row.get("nombre_sub_capacidad_nacional"));
   			objeto.setDescripcion_sub_capacidad_nacional((String) row.get("descripcion_sub_capacidad_nacional"));
   			objeto.setEstado_sub_capacidad_nacional(Integer.parseInt(String.valueOf(row.get("estado_sub_capacidad_nacional"))));
   			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
   			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
   			
   			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
   			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
   			objeto.setIp_creacion((String) row.get("ip_creacion"));
   			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
   			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
   			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
   			
   			sub_capacidad_nacional.add(objeto);
   		}
   		return sub_capacidad_nacional;
   	}
    
    @GetMapping("/listarActivos/{capacidad}")
	private List<SubCapacidadNacional> subCapacidadesNacionalesActivas(@PathVariable(value = "capacidad") String codigo_capacidad_nacional){
		String query = "SELECT * FROM ta_acn_subcapnacionales "
				+ "WHERE estado_sub_capacidad_nacional = 1 "
				+ "AND codigo_capacidad_nacional='"+codigo_capacidad_nacional+"' "
				+ "ORDER BY nombre_sub_capacidad_nacional";
		List<SubCapacidadNacional> sub_capacidad_nacional = new ArrayList<SubCapacidadNacional>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			SubCapacidadNacional objeto = new SubCapacidadNacional();
			
			objeto.setId_sub_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_sub_capacidad_nacional"))));
			objeto.setCodigo_sub_capacidad_nacional((String) row.get("codigo_sub_capacidad_nacional"));
			objeto.setNombre_sub_capacidad_nacional((String) row.get("nombre_sub_capacidad_nacional"));
			objeto.setDescripcion_sub_capacidad_nacional((String) row.get("descripcion_sub_capacidad_nacional"));
			objeto.setEstado_sub_capacidad_nacional(Integer.parseInt(String.valueOf(row.get("estado_sub_capacidad_nacional"))));
			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			sub_capacidad_nacional.add(objeto);
		}
		return sub_capacidad_nacional;
	}
	
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<SubCapacidadNacional> getSubCapacidadNacionalById(@PathVariable(value = "id") long id_sub_capacidad_nacional)
        throws ResourceNotFoundException {
        SubCapacidadNacional sub_capacidad_nacional = subCapacidadNacionalRepositorio.findById(id_sub_capacidad_nacional)
          .orElseThrow(() -> new ResourceNotFoundException("SubCapacidadNacional no encontrado para id :: " + id_sub_capacidad_nacional));
        return ResponseEntity.ok().body(sub_capacidad_nacional);
    }

    @PostMapping("/registrar")
    public SubCapacidadNacional createSubCapacidadNacional(@Valid @RequestBody SubCapacidadNacional sub_capacidad_nacional) {
    	Date fecha = new Date();
    	sub_capacidad_nacional.setFecha_creacion(fecha);
    	
    	FileServer f = new FileServer();
    	List<FileServer> fileServers = fileServerControlador.getAllFilesServers();
    	for(int i=0; i<fileServers.size(); i++) {
    		if(fileServers.get(i).getEstado_file_server()==1) {
    			f = fileServers.get(i);
    		}
    	}
    	
    	File directorio = new File(f.getDir_file_server()+"sub_capacidades_nacionales\\"+sub_capacidad_nacional.getCodigo_sub_capacidad_nacional());
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
    	
        return subCapacidadNacionalRepositorio.save(sub_capacidad_nacional);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<SubCapacidadNacional> updateSubCapacidadNacional(@PathVariable(value = "id") Long id_sub_capacidad_nacional,
         @Valid @RequestBody SubCapacidadNacional sub_capacidad_nacional_detalle) throws ResourceNotFoundException {
        SubCapacidadNacional sub_capacidad_nacional = subCapacidadNacionalRepositorio.findById(id_sub_capacidad_nacional)
        .orElseThrow(() -> new ResourceNotFoundException("Sub Capacidad Nacional no encontrado para id :: " + id_sub_capacidad_nacional));

        sub_capacidad_nacional.setNombre_sub_capacidad_nacional(sub_capacidad_nacional_detalle.getNombre_sub_capacidad_nacional());
        sub_capacidad_nacional.setDescripcion_sub_capacidad_nacional(sub_capacidad_nacional_detalle.getDescripcion_sub_capacidad_nacional());
        sub_capacidad_nacional.setEstado_sub_capacidad_nacional(sub_capacidad_nacional_detalle.getEstado_sub_capacidad_nacional());
        sub_capacidad_nacional.setCodigo_capacidad_nacional(sub_capacidad_nacional_detalle.getCodigo_capacidad_nacional());
        sub_capacidad_nacional.setId_capacidad_nacional(sub_capacidad_nacional_detalle.getId_capacidad_nacional());
        
        Date fecha = new Date();
        sub_capacidad_nacional.setId_usuario_creacion(sub_capacidad_nacional_detalle.getId_usuario_creacion());
        sub_capacidad_nacional.setFecha_creacion(sub_capacidad_nacional_detalle.getFecha_creacion());
        sub_capacidad_nacional.setIp_creacion(sub_capacidad_nacional_detalle.getIp_creacion());
        sub_capacidad_nacional.setId_usuario_modificacion(sub_capacidad_nacional_detalle.getId_usuario_modificacion());
        if(sub_capacidad_nacional_detalle.getId_sub_capacidad_nacional()==0) {
        	sub_capacidad_nacional.setFecha_modificacion(null);
        }
        else {
        	sub_capacidad_nacional.setFecha_modificacion(fecha);
        }
        sub_capacidad_nacional.setIp_modificacion(sub_capacidad_nacional_detalle.getIp_modificacion());
        
        final SubCapacidadNacional sub_capacidad_nacional_actualizado = subCapacidadNacionalRepositorio.save(sub_capacidad_nacional);
        return ResponseEntity.ok(sub_capacidad_nacional_actualizado);
    }
    
    @PostMapping(value="/reporteGeneral", produces = "application/json")
    private String reporteGeneralSubCapacidadesNacionales(@Valid @RequestBody List<Object> lista)throws ResourceNotFoundException, InvalidFormatException {
    	
    	String query = "";
    	if(lista.get(4).toString().equals("")) {
    		if(lista.get(3).toString().equals("")) {
    			query = "SELECT * FROM ta_acn_subcapnacionales WHERE estado_sub_capacidad_nacional=1 ORDER BY nombre_sub_capacidad_nacional";
    		}
    		else {
    			query = "SELECT * FROM ta_acn_subcapnacionales WHERE estado_sub_capacidad_nacional=1 AND codigo_capacidad_nacional='"+lista.get(3).toString()+"' ORDER BY nombre_sub_capacidad_nacional";
    		}
    	}
    	else {
    		if(lista.get(3).toString().equals("")) {
    			query = "SELECT * FROM ta_acn_subcapnacionales WHERE estado_sub_capacidad_nacional=1 AND nombre_sub _capacidad_nacional like '"+lista.get(4).toString()+"%' ORDER BY nombre_sub_capacidad_nacional";
    		}
    		else {
    			query = "SELECT * FROM ta_acn_subcapnacionales WHERE estado_sub_capacidad_nacional=1 AND nombre_sub _capacidad_nacional like '"+lista.get(4).toString()+"%' AND codigo_capacidad_nacional='"+lista.get(3).toString()+"' ORDER BY nombre_sub_capacidad_nacional";
    		}
    		
    	}

    	boolean problema = false;
    	List<SubCapacidadNacional> sub_capacidades_nacionales = new ArrayList<SubCapacidadNacional>();
    	try {
    		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
    		
    		for(Map row: rows) {
    			System.out.println("problema");
    			SubCapacidadNacional objeto = new SubCapacidadNacional();
    			
    			objeto.setId_sub_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_sub_capacidad_nacional"))));
    			objeto.setCodigo_sub_capacidad_nacional((String) row.get("codigo_sub_capacidad_nacional"));
    			objeto.setNombre_sub_capacidad_nacional((String) row.get("nombre_sub_capacidad_nacional"));
    			objeto.setDescripcion_sub_capacidad_nacional((String) row.get("descripcion_sub_capacidad_nacional"));
    			objeto.setEstado_sub_capacidad_nacional(Integer.parseInt(String.valueOf(row.get("estado_sub_capacidad_nacional"))));
    			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
    			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
    			
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
        				System.out.println("iterando");
        				CapacidadNacional objeto2 = new CapacidadNacional();		
        				objeto2.setNombre_capacidad_nacional((String) row2.get("nombre_capacidad_nacional"));
        				capacidades_nacionales.add(objeto2);
        			}
        			objeto.setCodigo_capacidad_nacional(capacidades_nacionales.get(0).getNombre_capacidad_nacional());
    			}
    			catch(Exception e) {
    				problema = true;
    			}
    			
    			sub_capacidades_nacionales.add(objeto);
    		}
    		
    	}
    	catch(Exception e) {
    		problema = true;
    	}
    	
    	if(!problema) {
    		System.out.println("entró");
    		String excelFilePath = "Reporte Sub Capacidades Nacionales.xlsx";
        	
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
                
     
                for(int i=0; i<sub_capacidades_nacionales.size(); i++) {
                	row = sheet.getRow(++rowCount);
                	
                	columnCount = 0;
                	cell = row.getCell(columnCount);
                    cell.setCellValue(i+1);
                    columnCount ++;
                    
                    if(lista.get(0).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(sub_capacidades_nacionales.get(i).getNombre_sub_capacidad_nacional());
                    	columnCount ++;
                    }
                    
                    if(lista.get(2).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(sub_capacidades_nacionales.get(i).getCodigo_capacidad_nacional());
                    	columnCount ++;
                    }
                    
                    
                    if(lista.get(1).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(sub_capacidades_nacionales.get(i).getDescripcion_sub_capacidad_nacional());
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
    private String reporteParticularSubCapacidadNacional(@PathVariable(value = "id") Long id_sub_capacidad)throws ResourceNotFoundException, InvalidFormatException {
    	
    	boolean problema = false;
    	List<SubCapacidadNacional> sub_capacidades_nacionales = new ArrayList<SubCapacidadNacional>();
    	try {
    		String query = "SELECT * FROM ta_acn_subcapnacionales WHERE id_sub_capacidad_nacional="+id_sub_capacidad.toString();
    		System.out.println(query);
    		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
    		for(Map row: rows) {
    			SubCapacidadNacional objeto = new SubCapacidadNacional();
    			
    			objeto.setId_sub_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_sub_capacidad_nacional"))));
    			objeto.setCodigo_sub_capacidad_nacional((String) row.get("codigo_sub_capacidad_nacional"));
    			objeto.setNombre_sub_capacidad_nacional((String) row.get("nombre_sub_capacidad_nacional"));
    			objeto.setDescripcion_sub_capacidad_nacional((String) row.get("descripcion_sub_capacidad_nacional"));
    			objeto.setEstado_sub_capacidad_nacional(Integer.parseInt(String.valueOf(row.get("estado_sub_capacidad_nacional"))));
    			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
    			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
    			
    			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
    			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
    			objeto.setIp_creacion((String) row.get("ip_creacion"));
    			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
    			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
    			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
    			
    			sub_capacidades_nacionales.add(objeto);
    		}
    	}
    	catch(Exception e) {
    		problema = true;
    	}
    	
   		
   		if(!problema) {
   			String excelFilePath = "Reporte Sub Capacidad Nacional.xlsx";
   	    	
   	    	try {
   	    		
   	            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
   	            Workbook workbook = WorkbookFactory.create(inputStream);
   	 
   	            Sheet sheet = workbook.getSheetAt(0);
   	         
   	            int rowCount = 4;
   	            Row row = sheet.getRow(++rowCount);
   	        	int columnCount = 2;
   	        	Cell cell = row.getCell(columnCount);
   	            cell.setCellValue(sub_capacidades_nacionales.get(0).getNombre_sub_capacidad_nacional());
   	            
   	            rowCount = 5;
	            row = sheet.getRow(++rowCount);
	        	columnCount = 2;
	        	cell = row.getCell(columnCount);
	        	CapacidadNacional capacidad_nacional = new CapacidadNacional();
	        	try {
	   	   			String sql2 = "SELECT nombre_capacidad_nacional FROM ta_acn_capnacionales WHERE codigo_capacidad_nacional = '"+sub_capacidades_nacionales.get(0).getCodigo_capacidad_nacional()+"' AND estado_capacidad_nacional=1";
	   	   			Map<String, Object> rows2 = jdbctemplate.queryForMap(sql2);
	   	   			capacidad_nacional.setNombre_capacidad_nacional(String.valueOf(rows2.get("nombre_capacidad_nacional")));
	        	}catch(Exception e) {
	        		problema = true;
	        	}
	            
	        	if(!problema) {
	        		cell.setCellValue(capacidad_nacional.getNombre_capacidad_nacional());

	   	            rowCount = 6;
	   	            row = sheet.getRow(++rowCount);
	   	        	columnCount = 2;
	   	        	cell = row.getCell(columnCount);
	   	            cell.setCellValue(sub_capacidades_nacionales.get(0).getDescripcion_sub_capacidad_nacional());
	   	            
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
	        	}else {	
	        		return "";
	        	}
   	             
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