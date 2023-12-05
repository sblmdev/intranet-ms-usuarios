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
import com.dini.activoscriticosnacionalesbackend.repositorios.CapacidadNacionalRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/capacidadNacional")
public class CapacidadNacionalControlador {
	
	@Autowired
    private CapacidadNacionalRepositorio capacidadNacionalRepositorio;
	
	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Autowired
    private FileServerControlador fileServerControlador;
	
	@GetMapping("/listar")
    public List<CapacidadNacional> getAllCapacidadessNacionales() {
        return capacidadNacionalRepositorio.findAll();
    }
	
	@GetMapping("/listarActivos")
	private List<CapacidadNacional> capacidadesNacionalesActivas(){
		String query = "SELECT * FROM ta_acn_capnacionales WHERE estado_capacidad_nacional = 1 ORDER BY nombre_capacidad_nacional";
		List<CapacidadNacional> capacidad_nacional = new ArrayList<CapacidadNacional>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			CapacidadNacional objeto = new CapacidadNacional();
			
			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
			objeto.setId_sector(Long.parseLong(String.valueOf(row.get("id_sector"))));
			objeto.setCodigo_sector((String) row.get("codigo_sector"));
			objeto.setNombre_capacidad_nacional((String) row.get("nombre_capacidad_nacional"));
			objeto.setDescripcion_capacidad_nacional((String) row.get("descripcion_capacidad_nacional"));
			objeto.setEstado_capacidad_nacional(Integer.parseInt(String.valueOf(row.get("estado_capacidad_nacional"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			capacidad_nacional.add(objeto);
		}
		return capacidad_nacional;
	}
	
	@GetMapping("/listarCoincidencias/{buscar}")
	private List<CapacidadNacional> capacidadesNacionalesCoincidencias(@PathVariable(value = "buscar") String cadena){
		String query = "SELECT * FROM ta_acn_capnacionales WHERE nombre_capacidad_nacional like '"
						+cadena+"%' AND estado_capacidad_nacional=1 ORDER BY nombre_capacidad_nacional";
		List<CapacidadNacional> capacidad_nacional = new ArrayList<CapacidadNacional>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			CapacidadNacional objeto = new CapacidadNacional();
			
			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
			objeto.setId_sector(Long.parseLong(String.valueOf(row.get("id_sector"))));
			objeto.setCodigo_sector((String) row.get("codigo_sector"));
			objeto.setNombre_capacidad_nacional((String) row.get("nombre_capacidad_nacional"));
			objeto.setDescripcion_capacidad_nacional((String) row.get("descripcion_capacidad_nacional"));
			objeto.setEstado_capacidad_nacional(Integer.parseInt(String.valueOf(row.get("estado_capacidad_nacional"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			capacidad_nacional.add(objeto);
		}
		return capacidad_nacional;
	}
	
	@GetMapping("/listarIguales/{buscar}")
	private List<CapacidadNacional> capacidadesNacionalesIguales(@PathVariable(value = "buscar") String cadena){
		String query = "SELECT * FROM ta_acn_capnacionales WHERE UPPER(nombre_capacidad_nacional) = UPPER('"+cadena+"')";
		List<CapacidadNacional> capacidad_nacional = new ArrayList<CapacidadNacional>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			CapacidadNacional objeto = new CapacidadNacional();
			
			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
			objeto.setId_sector(Long.parseLong(String.valueOf(row.get("id_sector"))));
			objeto.setCodigo_sector((String) row.get("codigo_sector"));
			objeto.setNombre_capacidad_nacional((String) row.get("nombre_capacidad_nacional"));
			objeto.setDescripcion_capacidad_nacional((String) row.get("descripcion_capacidad_nacional"));
			objeto.setEstado_capacidad_nacional(Integer.parseInt(String.valueOf(row.get("estado_capacidad_nacional"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			capacidad_nacional.add(objeto);
		}
		return capacidad_nacional;
	}
	
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<CapacidadNacional> getCapacidadNacionalById(@PathVariable(value = "id") long id_capacidad_nacional)
        throws ResourceNotFoundException {
        CapacidadNacional capacidad_nacional = capacidadNacionalRepositorio.findById(id_capacidad_nacional)
          .orElseThrow(() -> new ResourceNotFoundException("CapacidadNacional no encontrado para id :: " + id_capacidad_nacional));
        return ResponseEntity.ok().body(capacidad_nacional);
    }
    

    @GetMapping(value = "/recuperarPorCodigo/{codigo}", produces = "application/json")
	private CapacidadNacional getCapacidadNacionalByCodigo(@PathVariable String codigo)  {

		try {
			String sql = "SELECT * FROM ta_acn_capnacionales WHERE estado_capacidad_nacional = 1 AND codigo_capacidad_nacional='"+codigo+"'";
			Map<String, Object> row = jdbctemplate.queryForMap(sql);
			
			CapacidadNacional capacidad_nacional = new CapacidadNacional();
			capacidad_nacional.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
			capacidad_nacional.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
			capacidad_nacional.setId_sector(Long.parseLong(String.valueOf(row.get("id_sector"))));
			capacidad_nacional.setCodigo_sector((String) row.get("codigo_sector"));
			capacidad_nacional.setNombre_capacidad_nacional((String) row.get("nombre_capacidad_nacional"));
			capacidad_nacional.setDescripcion_capacidad_nacional((String) row.get("descripcion_capacidad_nacional"));
			capacidad_nacional.setEstado_capacidad_nacional(Integer.parseInt(String.valueOf(row.get("estado_capacidad_nacional"))));
				
			capacidad_nacional.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			capacidad_nacional.setFecha_creacion((Date) row.get("fecha_creacion"));
			capacidad_nacional.setIp_creacion((String) row.get("ip_creacion"));
			capacidad_nacional.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			capacidad_nacional.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			capacidad_nacional.setIp_modificacion((String) row.get("ip_modificacion"));

			
			return capacidad_nacional;
		}catch(Exception e) {
			return null;
		}
	} 

    @PostMapping("/registrar")
    public CapacidadNacional createCapacidadNacional(@Valid @RequestBody CapacidadNacional capacidad_nacional) {
    	Date fecha = new Date();
    	capacidad_nacional.setFecha_creacion(fecha);
    	
    	FileServer f = new FileServer();
    	List<FileServer> fileServers = fileServerControlador.getAllFilesServers();
    	for(int i=0; i<fileServers.size(); i++) {
    		if(fileServers.get(i).getEstado_file_server()==1) {
    			f = fileServers.get(i);
    		}
    	}
    	
    	File directorio = new File(f.getDir_file_server()+"capacidades_nacionales\\"+capacidad_nacional.getCodigo_capacidad_nacional());
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
    	
        return capacidadNacionalRepositorio.save(capacidad_nacional);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CapacidadNacional> updateCapacidadNacional(@PathVariable(value = "id") Long id_capacidad_nacional,
         @Valid @RequestBody CapacidadNacional capacidad_nacional_detalle) throws ResourceNotFoundException {
        CapacidadNacional capacidad_nacional = capacidadNacionalRepositorio.findById(id_capacidad_nacional)
        .orElseThrow(() -> new ResourceNotFoundException("ObjetivoNacional no encontrado para id :: " + id_capacidad_nacional));

        capacidad_nacional.setCodigo_capacidad_nacional(capacidad_nacional_detalle.getCodigo_capacidad_nacional());
        capacidad_nacional.setId_sector(capacidad_nacional_detalle.getId_sector());
        capacidad_nacional.setCodigo_sector(capacidad_nacional_detalle.getCodigo_sector());
        capacidad_nacional.setNombre_capacidad_nacional(capacidad_nacional_detalle.getNombre_capacidad_nacional());
        capacidad_nacional.setDescripcion_capacidad_nacional(capacidad_nacional_detalle.getDescripcion_capacidad_nacional());
        capacidad_nacional.setEstado_capacidad_nacional(capacidad_nacional_detalle.getEstado_capacidad_nacional());
        
        System.out.println(capacidad_nacional_detalle.getEstado_capacidad_nacional());
        
        Date fecha = new Date();    
        capacidad_nacional.setId_usuario_creacion(capacidad_nacional_detalle.getId_usuario_creacion());
        capacidad_nacional.setIp_creacion(capacidad_nacional_detalle.getIp_creacion());
        capacidad_nacional.setFecha_creacion(capacidad_nacional_detalle.getFecha_creacion());
        capacidad_nacional.setId_usuario_modificacion(capacidad_nacional_detalle.getId_usuario_modificacion());
        if(capacidad_nacional_detalle.getId_capacidad_nacional()==0) {
        	capacidad_nacional.setFecha_modificacion(null);
        }
        else {
        	capacidad_nacional.setFecha_modificacion(fecha);
        }
        capacidad_nacional.setIp_modificacion(capacidad_nacional_detalle.getIp_modificacion());
        
        final CapacidadNacional capacidad_nacional_actualizado = capacidadNacionalRepositorio.save(capacidad_nacional);
        return ResponseEntity.ok(capacidad_nacional_actualizado);
    }
    
    @PostMapping(value="/reporteGeneral", produces = "application/json")
    private String reporteGeneralCapacidadesNacionales(@Valid @RequestBody List<Object> lista)throws ResourceNotFoundException, InvalidFormatException {
    	
    	String query = "";
    	if(lista.get(3).toString().equals("")) {
    		query = "SELECT * FROM ta_acn_capnacionales WHERE estado_capacidad_nacional=1 ORDER BY nombre_capacidad_nacional";
    	}
    	else {
    		query = "SELECT * FROM ta_acn_capnacionales WHERE estado_capacidad_nacional=1 AND nombre_capacidad_nacional like '"+lista.get(3)+"%' ORDER BY nombre_capacidad_nacional";
    	}

    	boolean problema = false;
    	List<CapacidadNacional> capacidades_nacionales = new ArrayList<CapacidadNacional>();
		try {
			List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
			
			for(Map row: rows) {
				CapacidadNacional objeto = new CapacidadNacional();
				
				objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
				objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
				objeto.setId_sector(Long.parseLong(String.valueOf(row.get("id_sector"))));
				objeto.setCodigo_sector((String) row.get("codigo_sector"));
				objeto.setNombre_capacidad_nacional((String) row.get("nombre_capacidad_nacional"));
				objeto.setDescripcion_capacidad_nacional((String) row.get("descripcion_capacidad_nacional"));
				objeto.setEstado_capacidad_nacional(Integer.parseInt(String.valueOf(row.get("estado_capacidad_nacional"))));
				
				objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
				objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
				objeto.setIp_creacion((String) row.get("ip_creacion"));
				objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
				objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
				objeto.setIp_modificacion((String) row.get("ip_modificacion"));
				
				String query2 = "SELECT * FROM ta_acn_sectores WHERE estado_sector=1 AND codigo_sector='"+objeto.getCodigo_sector()+"' ORDER BY nombre_sector";
				List<Sector> sectores = new ArrayList<Sector>();
				
				try {
					List<Map<String, Object>> rows2 = jdbctemplate.queryForList(query2);
					for(Map row2: rows2) {
						Sector objeto2 = new Sector();		
						objeto2.setNombre_sector((String) row2.get("nombre_sector"));
						sectores.add(objeto2);
					}
					objeto.setCodigo_sector(sectores.get(0).getNombre_sector());
				}
				catch(Exception e) {
					problema = true;
				}
				
				capacidades_nacionales.add(objeto);
			}
		}
		catch(Exception e) {
			problema = true;
		}
		
    	if(!problema) {
    		String excelFilePath = "Reporte Capacidades Nacionales.xlsx";
        	
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
                	cell.setCellValue("Capacidad Nacional");
                	columnCount ++;
                }
                
                if(lista.get(2).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Sector / Entidad");
                	columnCount ++;
                }
                
                if(lista.get(1).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Descripción");
                	columnCount ++;
                }
                
     
                for(int i=0; i<capacidades_nacionales.size(); i++) {
                	row = sheet.getRow(++rowCount);
                	
                	columnCount = 0;
                	cell = row.getCell(columnCount);
                    cell.setCellValue(i+1);
                    columnCount ++;
                    
                    if(lista.get(0).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(capacidades_nacionales.get(i).getNombre_capacidad_nacional());
                    	columnCount ++;
                    }
                    
                    if(lista.get(2).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(capacidades_nacionales.get(i).getCodigo_sector());
                    	columnCount ++;
                    }
                    
                    
                    if(lista.get(1).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(capacidades_nacionales.get(i).getDescripcion_capacidad_nacional());
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
    
    @GetMapping(value="/reporteParticular/{id}", produces = "application/json")
    private String reporteParticularCapacidadNacional(@PathVariable(value = "id") Long id_capacidad)throws ResourceNotFoundException, InvalidFormatException {
    	
    	boolean problema = false;
    	List<CapacidadNacional> capacidades_nacionales = new ArrayList<CapacidadNacional>();
    	try {
    		String query = "SELECT * FROM ta_acn_capnacionales WHERE id_capacidad_nacional="+id_capacidad.toString();
    		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
    		for(Map row: rows) {
    			CapacidadNacional objeto = new CapacidadNacional();
    			
    			objeto.setId_capacidad_nacional(Long.parseLong(String.valueOf(row.get("id_capacidad_nacional"))));
    			objeto.setCodigo_capacidad_nacional((String) row.get("codigo_capacidad_nacional"));
    			objeto.setId_sector(Long.parseLong(String.valueOf(row.get("id_sector"))));
    			objeto.setCodigo_sector((String) row.get("codigo_sector"));
    			objeto.setNombre_capacidad_nacional((String) row.get("nombre_capacidad_nacional"));
    			objeto.setDescripcion_capacidad_nacional((String) row.get("descripcion_capacidad_nacional"));
    			objeto.setEstado_capacidad_nacional(Integer.parseInt(String.valueOf(row.get("estado_capacidad_nacional"))));
    			
    			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
    			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
    			objeto.setIp_creacion((String) row.get("ip_creacion"));
    			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
    			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
    			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
    			
    			capacidades_nacionales.add(objeto);
    		}
    	}
    	catch(Exception e) {
    		problema = true;
    	}
    	
   		
   		if(!problema) {
   			String excelFilePath = "Reporte Capacidad Nacional.xlsx";
   	    	
   	    	try {
   	            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
   	            Workbook workbook = WorkbookFactory.create(inputStream);
   	 
   	            Sheet sheet = workbook.getSheetAt(0);
   	 
   	            int rowCount = 4;
   	            Row row = sheet.getRow(++rowCount);
   	        	int columnCount = 2;
   	        	Cell cell = row.getCell(columnCount);
   	            cell.setCellValue(capacidades_nacionales.get(0).getNombre_capacidad_nacional());
   	            
   	            rowCount = 5;
	            row = sheet.getRow(++rowCount);
	        	columnCount = 2;
	        	cell = row.getCell(columnCount);
	        	Sector sector = new Sector();
	        	try {
	   	   			String sql2 = "SELECT nombre_sector FROM ta_acn_sectores WHERE codigo_sector = '"+capacidades_nacionales.get(0).getCodigo_sector()+"' AND estado_sector=1";
	   	   			Map<String, Object> rows2 = jdbctemplate.queryForMap(sql2);
	   	   			sector.setNombre_sector(String.valueOf(rows2.get("nombre_sector")));
	        	}catch(Exception e) {
	        		problema = true;
	        	}
	            
	        	if(!problema) {
	        		cell.setCellValue(sector.getNombre_sector());

	   	            rowCount = 6;
	   	            row = sheet.getRow(++rowCount);
	   	        	columnCount = 2;
	   	        	cell = row.getCell(columnCount);
	   	            cell.setCellValue(capacidades_nacionales.get(0).getDescripcion_capacidad_nacional());
	   	            
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