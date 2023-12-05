package com.dini.activoscriticosnacionalesbackend.controladores;
import com.dini.activoscriticosnacionalesbackend.excepciones.ResourceNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.dini.activoscriticosnacionalesbackend.entidades.ObjetivoNacional;
import com.dini.activoscriticosnacionalesbackend.entidades.SubCapacidadNacional;
import com.dini.activoscriticosnacionalesbackend.repositorios.FileServerRepositorio;
import com.dini.activoscriticosnacionalesbackend.repositorios.ObjetivoNacionalRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/objetivoNacional")
public class ObjetivoNacionalControlador {
	
	@Autowired
    private ObjetivoNacionalRepositorio objetivoNacionalRepositorio;
	
	@Autowired
    private FileServerControlador fileServerControlador;
	
	@Autowired
	JdbcTemplate jdbctemplate;

	//@PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/listar")
    public List<ObjetivoNacional> getAllObjetivosNacionales() {
        return objetivoNacionalRepositorio.findAll();
    }
    
    @GetMapping("/listarActivos")
	private List<ObjetivoNacional> objetivosNacionalesActivos(){
		String query = "SELECT * FROM ta_acn_objnacionales WHERE estado_objetivo_nacional = 1 ORDER BY nombre_objetivo_nacional";
		List<ObjetivoNacional> objetivos_nacionales = new ArrayList<ObjetivoNacional>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			ObjetivoNacional objeto = new ObjetivoNacional();
			
			objeto.setId_objetivo_nacional(Long.parseLong(String.valueOf(row.get("id_objetivo_nacional"))));
			objeto.setCodigo_objetivo_nacional((String) row.get("codigo_objetivo_nacional"));
			objeto.setNombre_objetivo_nacional((String) row.get("nombre_objetivo_nacional"));
			objeto.setDescripcion_objetivo_nacional((String) row.get("descripcion_objetivo_nacional"));
			objeto.setEstado_objetivo_nacional(Integer.parseInt(String.valueOf(row.get("estado_objetivo_nacional"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			objetivos_nacionales.add(objeto);
		}
		return objetivos_nacionales;
	}
    
    @GetMapping("/listarCoincidencias/{buscar}")
	private List<ObjetivoNacional> objetivosNacionalesCoincidencias(@PathVariable(value = "buscar") String cadena)
	        throws ResourceNotFoundException {
		String query = "SELECT * FROM ta_acn_objnacionales WHERE estado_objetivo_nacional = 1 AND nombre_objetivo_nacional like '"+cadena+"%' ORDER BY nombre_objetivo_nacional";
		List<ObjetivoNacional> objetivos_nacionales = new ArrayList<ObjetivoNacional>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			ObjetivoNacional objeto = new ObjetivoNacional();
			
			objeto.setId_objetivo_nacional(Long.parseLong(String.valueOf(row.get("id_objetivo_nacional"))));
			objeto.setCodigo_objetivo_nacional((String) row.get("codigo_objetivo_nacional"));
			objeto.setNombre_objetivo_nacional((String) row.get("nombre_objetivo_nacional"));
			objeto.setDescripcion_objetivo_nacional((String) row.get("descripcion_objetivo_nacional"));
			objeto.setEstado_objetivo_nacional(Integer.parseInt(String.valueOf(row.get("estado_objetivo_nacional"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			objetivos_nacionales.add(objeto);
		}

		return objetivos_nacionales;
	}
    
    @GetMapping("/listarIguales/{buscar}")
	private List<ObjetivoNacional> objetivosNacionalesIguales(@PathVariable(value = "buscar") String cadena)
	        throws ResourceNotFoundException {
		String query = "SELECT * FROM ta_acn_objnacionales WHERE UPPER(nombre_objetivo_nacional) = UPPER('"+cadena+"')";
		List<ObjetivoNacional> objetivos_nacionales = new ArrayList<ObjetivoNacional>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			ObjetivoNacional objeto = new ObjetivoNacional();
			
			objeto.setId_objetivo_nacional(Long.parseLong(String.valueOf(row.get("id_objetivo_nacional"))));
			objeto.setCodigo_objetivo_nacional((String) row.get("codigo_objetivo_nacional"));
			objeto.setNombre_objetivo_nacional((String) row.get("nombre_objetivo_nacional"));
			objeto.setDescripcion_objetivo_nacional((String) row.get("descripcion_objetivo_nacional"));
			objeto.setEstado_objetivo_nacional(Integer.parseInt(String.valueOf(row.get("estado_objetivo_nacional"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			objetivos_nacionales.add(objeto);
		}

		return objetivos_nacionales;
	}
    
    
    
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<ObjetivoNacional> getObjetivoNacionalById(@PathVariable(value = "id") long id_objetivo_nacional)
        throws ResourceNotFoundException {
        ObjetivoNacional objetivo_nacional = objetivoNacionalRepositorio.findById(id_objetivo_nacional)
          .orElseThrow(() -> new ResourceNotFoundException("ObjetivoNacional no encontrado para id :: " + id_objetivo_nacional));
        return ResponseEntity.ok().body(objetivo_nacional);
    }

    @PostMapping("/registrar")
    public ObjetivoNacional createObjetivoNacional(@Valid @RequestBody ObjetivoNacional objetivo_nacional) {
    	Date fecha = new Date();
    	objetivo_nacional.setFecha_creacion(fecha);
    	
    	FileServer f = new FileServer();
    	List<FileServer> fileServers = fileServerControlador.getAllFilesServers();
    	for(int i=0; i<fileServers.size(); i++) {
    		if(fileServers.get(i).getEstado_file_server()==1) {
    			f = fileServers.get(i);
    		}
    	}
    		
    	File directorio = new File(f.getDir_file_server()+"objetivos_nacionales\\"+objetivo_nacional.getCodigo_objetivo_nacional());
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
    	
        return objetivoNacionalRepositorio.save(objetivo_nacional);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ObjetivoNacional> updateObjetivoNacional(@PathVariable(value = "id") Long id_objetivo_nacional,
         @Valid @RequestBody ObjetivoNacional objetivo_nacional_detalle) throws ResourceNotFoundException {
        ObjetivoNacional objetivo_nacional = objetivoNacionalRepositorio.findById(id_objetivo_nacional)
        .orElseThrow(() -> new ResourceNotFoundException("ObjetivoNacional no encontrado para id :: " + id_objetivo_nacional));

        objetivo_nacional.setNombre_objetivo_nacional(objetivo_nacional_detalle.getNombre_objetivo_nacional());
        objetivo_nacional.setDescripcion_objetivo_nacional(objetivo_nacional_detalle.getDescripcion_objetivo_nacional());
        objetivo_nacional.setEstado_objetivo_nacional(objetivo_nacional_detalle.getEstado_objetivo_nacional());
        
        Date fecha = new Date();
        objetivo_nacional.setId_usuario_creacion(objetivo_nacional_detalle.getId_usuario_creacion());
        objetivo_nacional.setIp_creacion(objetivo_nacional_detalle.getIp_creacion());
        objetivo_nacional.setFecha_creacion(objetivo_nacional_detalle.getFecha_creacion());
        objetivo_nacional.setId_usuario_modificacion(objetivo_nacional_detalle.getId_usuario_modificacion());
        objetivo_nacional.setIp_modificacion(objetivo_nacional_detalle.getIp_modificacion());
        if(objetivo_nacional_detalle.getId_objetivo_nacional()==0) {
        	objetivo_nacional.setFecha_modificacion(null);
        }
        else {
        	objetivo_nacional.setFecha_modificacion(fecha);
        }
        objetivo_nacional.setFecha_modificacion(fecha);
        
        final ObjetivoNacional objetivo_nacional_actualizado = objetivoNacionalRepositorio.save(objetivo_nacional);
        return ResponseEntity.ok(objetivo_nacional_actualizado);
    }
    
    @PostMapping(value="/reporteGeneral", produces = "application/json")
    private String reporteGeneralObjetivosNacionales(@Valid @RequestBody List<Object> lista)throws ResourceNotFoundException, InvalidFormatException {
    	
    	String query = "";
    	if(lista.get(2).toString().equals("")) {
    		query = "SELECT * FROM ta_acn_objnacionales WHERE estado_objetivo_nacional=1 AND nombre_objetivo_nacional like '"+lista.get(2).toString()+"%' ORDER BY nombre_objetivo_nacional";
    	}
    	else {
    		query = "SELECT * FROM ta_acn_objnacionales WHERE estado_objetivo_nacional=1 ORDER BY nombre_objetivo_nacional";
    	}

    	boolean problema = false;
    	List<ObjetivoNacional> objetivos_nacionales = new ArrayList<ObjetivoNacional>();
    	try {
    		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);	
    		for(Map row: rows) {
    			ObjetivoNacional objeto = new ObjetivoNacional();
    			
    			objeto.setId_objetivo_nacional(Long.parseLong(String.valueOf(row.get("id_objetivo_nacional"))));
    			objeto.setCodigo_objetivo_nacional((String) row.get("codigo_objetivo_nacional"));
    			objeto.setNombre_objetivo_nacional((String) row.get("nombre_objetivo_nacional"));
    			objeto.setDescripcion_objetivo_nacional((String) row.get("descripcion_objetivo_nacional"));
    			objeto.setEstado_objetivo_nacional(Integer.parseInt(String.valueOf(row.get("estado_objetivo_nacional"))));
    			
    			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
    			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
    			objeto.setIp_creacion((String) row.get("ip_creacion"));
    			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
    			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
    			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
    			
    			objetivos_nacionales.add(objeto);
    		}	
    	}
    	catch(Exception e) {
    		problema = true;
    	}
    	
    	if(!problema) {
    		String excelFilePath = "Reporte Objetivos Nacionales.xlsx";
        	
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
                	cell.setCellValue("Objetivo Nacional");
                	columnCount ++;
                }
        
                if(lista.get(1).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Descripción");
                	columnCount ++;
                }
                
     
                for(int i=0; i<objetivos_nacionales.size(); i++) {
                	row = sheet.getRow(++rowCount);
                	
                	columnCount = 0;
                	cell = row.getCell(columnCount);
                    cell.setCellValue(i+1);
                    columnCount ++;
                    
                    if(lista.get(0).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(objetivos_nacionales.get(i).getNombre_objetivo_nacional());
                    	columnCount ++;
                    }
                    
                    if(lista.get(1).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(objetivos_nacionales.get(i).getNombre_objetivo_nacional());
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
    private String reporteParticularObjetivoNacinal(@PathVariable(value = "id") Long id_objetivo)throws ResourceNotFoundException, InvalidFormatException {
    	
    	boolean problema = false;
    	List<ObjetivoNacional> objetivos_nacionales = new ArrayList<ObjetivoNacional>();
    	try {
    		String query = "SELECT * FROM ta_acn_objnacionales WHERE id_objetivo_nacional="+id_objetivo.toString();
    		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
    		for(Map row: rows) {
    			ObjetivoNacional objeto = new ObjetivoNacional();
    			
    			objeto.setId_objetivo_nacional(Long.parseLong(String.valueOf(row.get("id_objetivo_nacional"))));
    			objeto.setCodigo_objetivo_nacional((String) row.get("codigo_objetivo_nacional"));
    			objeto.setNombre_objetivo_nacional((String) row.get("nombre_objetivo_nacional"));
    			objeto.setDescripcion_objetivo_nacional((String) row.get("descripcion_objetivo_nacional"));
    			objeto.setEstado_objetivo_nacional(Integer.parseInt(String.valueOf(row.get("estado_objetivo_nacional"))));
    			
    			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
    			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
    			objeto.setIp_creacion((String) row.get("ip_creacion"));
    			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
    			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
    			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
    			
    			objetivos_nacionales.add(objeto);
    		}
    	}
    	catch(Exception e) {
    		problema = true;
    	}
    	
   		
   		if(!problema) {
   			String excelFilePath = "Reporte Objetivo Nacional.xlsx";
   	    	
   	    	try {
   	            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
   	            Workbook workbook = WorkbookFactory.create(inputStream);
   	 
   	            Sheet sheet = workbook.getSheetAt(0);
   	 
   	            int rowCount = 4;
   	            Row row = sheet.getRow(++rowCount);
   	        	int columnCount = 2;
   	        	Cell cell = row.getCell(columnCount);
   	            cell.setCellValue(objetivos_nacionales.get(0).getNombre_objetivo_nacional());     
	        	
   	            rowCount = 5;
   	            row = sheet.getRow(++rowCount);
   	        	columnCount = 2;
   	        	cell = row.getCell(columnCount);
   	            cell.setCellValue(objetivos_nacionales.get(0).getDescripcion_objetivo_nacional());
   	            
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
