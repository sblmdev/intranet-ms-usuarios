package com.dini.activoscriticosnacionalesbackend.controladores;
import com.dini.activoscriticosnacionalesbackend.excepciones.ResourceNotFoundException;

import java.util.ArrayList;
//import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.IOUtils;
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

import com.dini.activoscriticosnacionalesbackend.entidades.Funcionario;
import com.dini.activoscriticosnacionalesbackend.entidades.Sector;
import com.dini.activoscriticosnacionalesbackend.entidades.SectorFuncionario;
import com.dini.activoscriticosnacionalesbackend.entidades.Usuario;
import com.dini.activoscriticosnacionalesbackend.repositorios.SectorRepositorio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/sector")
public class SectorControlador {
	
	@Autowired
    private SectorRepositorio sectorRepositorio;
	
	@Autowired
	JdbcTemplate jdbctemplate;

	/*@PreAuthorize("hasRole('ADMINISTRADOR')")*/
    @GetMapping("/listar")
    public List<Sector> getAllSectores() {
        return sectorRepositorio.findAll();
    }
    
    @GetMapping("/listarActivos")
    private List<Sector> sectoresActivos() throws ResourceNotFoundException {
		String query = "SELECT * FROM ta_acn_sectores WHERE estado_sector=1 ORDER BY nombre_sector";
		List<Sector> sectores = new ArrayList<Sector>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			Sector objeto = new Sector();
			
			objeto.setId_sector(Long.parseLong(String.valueOf(row.get("id_sector"))));
			objeto.setCodigo_sector((String) row.get("codigo_sector"));
			objeto.setNombre_sector((String) row.get("nombre_sector"));
			objeto.setSigla_sector((String) row.get("sigla_sector"));
			objeto.setTipo_sector(Integer.parseInt(String.valueOf(row.get("tipo_sector"))));
			objeto.setDescripcion_sector((String) row.get("descripcion_sector"));
			objeto.setEstado_sector(Integer.parseInt(String.valueOf(row.get("estado_sector"))));

			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			sectores.add(objeto);
		}

		return sectores;
	}
    
    @GetMapping("/listarActivosPorTipo/{tipo}")
    private List<Sector> sectoresActivosPorTipo(@PathVariable(value = "tipo") int tipo)
	        throws ResourceNotFoundException {
		String query = "SELECT * FROM ta_acn_sectores WHERE estado_sector=1 AND tipo_sector= "+tipo+" ORDER BY nombre_sector";
		List<Sector> sectores = new ArrayList<Sector>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			Sector objeto = new Sector();
			
			objeto.setId_sector(Long.parseLong(String.valueOf(row.get("id_sector"))));
			objeto.setCodigo_sector((String) row.get("codigo_sector"));
			objeto.setNombre_sector((String) row.get("nombre_sector"));
			objeto.setSigla_sector((String) row.get("sigla_sector"));
			objeto.setTipo_sector(Integer.parseInt(String.valueOf(row.get("tipo_sector"))));
			objeto.setDescripcion_sector((String) row.get("descripcion_sector"));
			objeto.setEstado_sector(Integer.parseInt(String.valueOf(row.get("estado_sector"))));

			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			sectores.add(objeto);
		}

		return sectores;
	}
    
    
    @GetMapping("/listarCoincidencias/{buscar}")
    private List<Sector> sectoresCoincidencias(@PathVariable(value = "buscar") String cadena)
	        throws ResourceNotFoundException {
		String query = "SELECT * FROM ta_acn_sectores WHERE nombre_sector like '%"+cadena+"%' AND estado_sector=1 ORDER BY nombre_sector";
		List<Sector> sectores = new ArrayList<Sector>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			Sector objeto = new Sector();
			
			objeto.setId_sector(Long.parseLong(String.valueOf(row.get("id_sector"))));
			objeto.setCodigo_sector((String) row.get("codigo_sector"));
			objeto.setNombre_sector((String) row.get("nombre_sector"));
			objeto.setDescripcion_sector((String) row.get("descripcion_sector"));
			objeto.setSigla_sector((String) row.get("sigla_sector"));
			objeto.setTipo_sector(Integer.parseInt(String.valueOf(row.get("tipo_sector"))));
			objeto.setEstado_sector(Integer.parseInt(String.valueOf(row.get("estado_sector"))));

			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			sectores.add(objeto);
		}

		return sectores;
	}
    
    @GetMapping("/listarIguales/{buscar}")
    private List<Sector> sectoresIguales(@PathVariable(value = "buscar") String cadena)
	        throws ResourceNotFoundException {
		String query = "SELECT * FROM ta_acn_sectores WHERE nombre_sector = '"+cadena+"'";
		List<Sector> sectores = new ArrayList<Sector>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			Sector objeto = new Sector();
			
			objeto.setId_sector(Long.parseLong(String.valueOf(row.get("id_sector"))));
			objeto.setCodigo_sector((String) row.get("codigo_sector"));
			objeto.setNombre_sector((String) row.get("nombre_sector"));
			objeto.setDescripcion_sector((String) row.get("descripcion_sector"));
			objeto.setSigla_sector((String) row.get("sigla_sector"));
			objeto.setTipo_sector(Integer.parseInt(String.valueOf(row.get("tipo_sector"))));
			objeto.setEstado_sector(Integer.parseInt(String.valueOf(row.get("estado_sector"))));

			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			sectores.add(objeto);
		}

		return sectores;
	}
    
    @GetMapping("/listarCoincidenciasPorTipo/{buscar}/{tipo}")
    private List<Sector> sectoresCoincidenciasPorTipo(@PathVariable(value = "buscar") String cadena, @PathVariable(value = "tipo") int tipo)
	        throws ResourceNotFoundException {
		String query = "SELECT * FROM ta_acn_sectores WHERE nombre_sector like '%"+cadena+"%' AND estado_sector=1 AND tipo_sector="+tipo+" ORDER BY nombre_sector";
		List<Sector> sectores = new ArrayList<Sector>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			Sector objeto = new Sector();
			
			objeto.setId_sector(Long.parseLong(String.valueOf(row.get("id_sector"))));
			objeto.setCodigo_sector((String) row.get("codigo_sector"));
			objeto.setNombre_sector((String) row.get("nombre_sector"));
			objeto.setDescripcion_sector((String) row.get("descripcion_sector"));
			objeto.setSigla_sector((String) row.get("sigla_sector"));
			objeto.setTipo_sector(Integer.parseInt(String.valueOf(row.get("tipo_sector"))));
			objeto.setEstado_sector(Integer.parseInt(String.valueOf(row.get("estado_sector"))));

			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			sectores.add(objeto);
		}

		return sectores;
	}
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Sector> getSectorById(@PathVariable(value = "id") long id_sector)
        throws ResourceNotFoundException {
        Sector sector = sectorRepositorio.findById(id_sector)
          .orElseThrow(() -> new ResourceNotFoundException("Sector no encontrado para id :: " + id_sector));
        return ResponseEntity.ok().body(sector);
    }

    @PostMapping("/registrar")
    public Sector createSector(@Valid @RequestBody Sector sector) {
    	Date fecha = new Date();
    	sector.setFecha_creacion(fecha);
        return sectorRepositorio.save(sector);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Sector> updateSector(@PathVariable(value = "id") Long id_sector,
         @Valid @RequestBody Sector sector_detalle) throws ResourceNotFoundException {
        Sector sector = sectorRepositorio.findById(id_sector)
        .orElseThrow(() -> new ResourceNotFoundException("Sector no encontrado para id :: " + id_sector));

        sector.setNombre_sector(sector_detalle.getNombre_sector());
        sector.setDescripcion_sector(sector_detalle.getDescripcion_sector());
        sector.setEstado_sector(sector_detalle.getEstado_sector());
        sector.setSigla_sector(sector_detalle.getSigla_sector());
		sector.setTipo_sector(sector_detalle.getTipo_sector());
        
        Date fecha = new Date();
        sector.setId_usuario_creacion(sector_detalle.getId_usuario_creacion());
        sector.setFecha_creacion(sector_detalle.getFecha_creacion());
        sector.setIp_creacion(sector_detalle.getIp_creacion());
        sector.setId_usuario_modificacion(sector_detalle.getId_usuario_modificacion());
        if(sector_detalle.getId_sector()==0) {
        	sector.setFecha_modificacion(null);
        }
        else {
        	sector.setFecha_modificacion(fecha);
        }
        sector.setIp_modificacion(sector_detalle.getIp_modificacion());
        
        final Sector sector_actualizado = sectorRepositorio.save(sector);
        return ResponseEntity.ok(sector_actualizado);
    }
    
    
    @PostMapping(value="/reporteGeneral", produces = "application/json")
    private String reporteGeneralSectores(@Valid @RequestBody List<Object> lista)throws ResourceNotFoundException, InvalidFormatException {
    	
    	String query = "";
    	if(lista.get(5).toString().equals("")) {
    		if(lista.get(4).toString().equals("0")) {
    			query = "SELECT * FROM ta_acn_sectores WHERE estado_sector=1 ORDER BY nombre_sector";
    		}
    		else {
    			query = "SELECT * FROM ta_acn_sectores WHERE estado_sector=1 AND tipo_sector="+lista.get(4).toString()+" ORDER BY nombre_sector";
    		}		
    	}
    	else {
    		if(lista.get(4).toString().equals("0")) {
    			query = "SELECT * FROM ta_acn_sectores WHERE estado_sector=1 AND nombre_sector like '%"+lista.get(5)+"%' ORDER BY nombre_sector";
    		}
    		else {
    			query = "SELECT * FROM ta_acn_sectores WHERE estado_sector=1 AND tipo_sector="+lista.get(4)+" AND nombre_sector like '%"+lista.get(5)+"%' ORDER BY nombre_sector";
    		}	
    	}

		List<Sector> sectores = new ArrayList<Sector>();
		boolean problema = false;
		
		try {
			List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
			
			for(Map row: rows) {
				Sector objeto = new Sector();
				
				objeto.setId_sector(Long.parseLong(String.valueOf(row.get("id_sector"))));
				objeto.setCodigo_sector((String) row.get("codigo_sector"));
				objeto.setNombre_sector((String) row.get("nombre_sector"));
				objeto.setDescripcion_sector((String) row.get("descripcion_sector"));
				objeto.setSigla_sector((String) row.get("sigla_sector"));
				objeto.setTipo_sector(Integer.parseInt(String.valueOf(row.get("tipo_sector"))));
				objeto.setEstado_sector(Integer.parseInt(String.valueOf(row.get("estado_sector"))));

				
				objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
				objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
				objeto.setIp_creacion((String) row.get("ip_creacion"));
				objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
				objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
				objeto.setIp_modificacion((String) row.get("ip_modificacion"));
				
				sectores.add(objeto);
			}
		}
		catch(Exception e) {
			
		}
		
		if(!problema) {
			String excelFilePath = "Reporte Sectores.xlsx";
	    	
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
	            	cell.setCellValue("Sector / Entidad");
	            	columnCount ++;
	            }
	            
	            if(lista.get(2).toString().equals("true")) {
	            	cell = row.getCell(columnCount);
	            	cell.setCellValue("Acrónimo");
	            	columnCount ++;
	            }
	            
	            if(lista.get(3).toString().equals("true")) {
	            	cell = row.getCell(columnCount);
	            	cell.setCellValue("Tipo");
	            	columnCount ++;
	            }
	            
	            if(lista.get(1).toString().equals("true")) {
	            	cell = row.getCell(columnCount);
	            	cell.setCellValue("Descripción");
	            	columnCount ++;
	            }
	            
	 
	            for(int i=0; i<sectores.size(); i++) {
	            	row = sheet.getRow(++rowCount);
	            	
	            	columnCount = 0;
	            	cell = row.getCell(columnCount);
	                cell.setCellValue(i+1);
	                columnCount ++;
	                
	                if(lista.get(0).toString().equals("true")) {
	                	cell = row.getCell(columnCount);
	                	cell.setCellValue(sectores.get(i).getNombre_sector());
	                	columnCount ++;
	                }
	                
	                if(lista.get(2).toString().equals("true")) {
	                	cell = row.getCell(columnCount);
	                	cell.setCellValue(sectores.get(i).getSigla_sector());
	                	columnCount ++;
	                }
	                
	                if(lista.get(3).toString().equals("true")) {
	                	cell = row.getCell(columnCount);
	                	if(sectores.get(i).getTipo_sector()==1) {
	                		cell.setCellValue("Sector");
	                	}
	                	if(sectores.get(i).getTipo_sector()==2) {
	                		cell.setCellValue("Entidad");
	                	}
	                	columnCount ++;
	                }
	                
	                if(lista.get(1).toString().equals("true")) {
	                	cell = row.getCell(columnCount);
	                	cell.setCellValue(sectores.get(i).getDescripcion_sector());
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
    private String reporteParticularSector(@PathVariable(value = "id") Long id_sector)throws ResourceNotFoundException, InvalidFormatException {
    	
    	String query = "SELECT * FROM ta_acn_sectores WHERE id_sector="+id_sector.toString();
		List<Sector> sectores = new ArrayList<Sector>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		for(Map row: rows) {
			Sector objeto = new Sector();
			objeto.setId_sector(Long.parseLong(String.valueOf(row.get("id_sector"))));
			objeto.setCodigo_sector((String) row.get("codigo_sector"));
			objeto.setNombre_sector((String) row.get("nombre_sector"));
			objeto.setDescripcion_sector((String) row.get("descripcion_sector"));
			objeto.setSigla_sector((String) row.get("sigla_sector"));
			objeto.setTipo_sector(Integer.parseInt(String.valueOf(row.get("tipo_sector"))));
			objeto.setEstado_sector(Integer.parseInt(String.valueOf(row.get("estado_sector"))));

			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			sectores.add(objeto);
		}
		
		String sql = "SELECT * FROM ta_acn_secfuncionarios WHERE codigo_sector = '"+sectores.get(0).getCodigo_sector()+"' AND estado_sector_funcionario=1 order by codigo_funcionario";
       	List<SectorFuncionario> sectores_funcionarios = new ArrayList<SectorFuncionario>();
       	rows = jdbctemplate.queryForList(sql);
   		for(Map row: rows) {
   			SectorFuncionario objeto = new SectorFuncionario();
   			
   			objeto.setId_sector_funcionario(Long.parseLong(String.valueOf(row.get("id_sector_funcionario"))));
   			objeto.setCodigo_funcionario((String) row.get("codigo_funcionario"));
   			objeto.setCodigo_sector((String) row.get("codigo_sector"));
   			objeto.setCargo_sector_funcionario((String) row.get("cargo_sector_funcionario"));
   			objeto.setFijo_sector_funcionario((String) row.get("fijo_sector_funcionario"));
   			objeto.setAnexo_sector_funcionario((String) row.get("anexo_sector_funcionario"));
   			objeto.setMovil_sector_funcionario((String) row.get("movil_sector_funcionario"));
   			objeto.setMovil2_sector_funcionario((String) row.get("movil2_sector_funcionario"));
   			objeto.setEmail_sector_funcionario((String) row.get("email_sector_funcionario"));
   			objeto.setEmail2_sector_funcionario((String) row.get("email2_sector_funcionario"));
   			objeto.setInicio_sector_funcionario((Date) row.get("inicio_sector_funcionario"));
			objeto.setFin_sector_funcionario((Date) row.get("fin_sector_funcionario"));
   			objeto.setEstado_sector_funcionario(Integer.parseInt(String.valueOf(row.get("estado_sector_funcionario"))));
   			
   			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
   			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
   			objeto.setIp_creacion((String) row.get("ip_creacion"));
   			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
   			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
   			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
   			
   			sectores_funcionarios.add(objeto);
   		}
   		
   		boolean problema = false;
   		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
   		for(int i=0; i<sectores_funcionarios.size(); i++) {
   			try {
   	   			String sql2 = "SELECT * FROM ta_acn_personas WHERE codigo_funcionario = '"+sectores_funcionarios.get(i).getCodigo_funcionario()+"'";
   	   			Map<String, Object> rows2 = jdbctemplate.queryForMap(sql2);
   	   			
   	   			Funcionario funcionario = new Funcionario();
   	   			funcionario.setId_funcionario(Long.parseLong(String.valueOf(rows2.get("id_funcionario"))));
   	   			funcionario.setCodigo_funcionario(String.valueOf(rows2.get("codigo_funcionario")));
   	   			funcionario.setDni_funcionario(String.valueOf(rows2.get("dni_funcionario")));
   	   			funcionario.setPaterno_funcionario(String.valueOf(rows2.get("paterno_funcionario")));
   	   			funcionario.setMaterno_funcionario(String.valueOf(rows2.get("materno_funcionario")));
   	   			funcionario.setNombres_funcionario(String.valueOf(rows2.get("nombres_funcionario")));
   	   			
   	   			funcionario.setId_usuario_creacion(Integer.parseInt(String.valueOf(rows2.get("id_usuario_creacion"))));
   	   			funcionario.setFecha_creacion((Date)rows2.get("fecha_creacion"));
   	   			funcionario.setIp_creacion(String.valueOf(rows2.get("ip_creacion")));
   	   			funcionario.setId_usuario_modificacion(Integer.parseInt(String.valueOf(rows2.get("id_usuario_modificacion"))));
   	   			funcionario.setFecha_modificacion((Date)rows2.get("fecha_modificacion"));
   	   			funcionario.setIp_modificacion(String.valueOf(rows2.get("ip_modificacion")));

   	   			funcionarios.add(funcionario);
   	   		}catch(Exception e) {
   	   			problema = true;
   	   		}
   		}
   		
   		if(!problema) {
   			String excelFilePath = "Reporte Sector.xlsx";
   	    	
   	    	try {
   	            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
   	            Workbook workbook = WorkbookFactory.create(inputStream);
   	 
   	            Sheet sheet = workbook.getSheetAt(0);
   	 
   	            int rowCount = 4;
   	            Row row = sheet.getRow(++rowCount);
   	        	int columnCount = 2;
   	        	Cell cell = row.getCell(columnCount);
   	            cell.setCellValue(sectores.get(0).getNombre_sector());

   	            rowCount = 5;
   	            row = sheet.getRow(++rowCount);
   	        	columnCount = 2;
   	        	cell = row.getCell(columnCount);
   	            cell.setCellValue(sectores.get(0).getSigla_sector());
   	            
   	            rowCount = 6;
   	            row = sheet.getRow(++rowCount);
   	        	columnCount = 2;
   	        	cell = row.getCell(columnCount);
   	        	if(sectores.get(0).getTipo_sector()==1) {
   	        		cell.setCellValue("Sector");
   	        	}
   	        	else {
   	        		cell.setCellValue("Entidad");
   	        	}
   	            
   	        	rowCount = 9;
   	        	for(int i=0; i<sectores_funcionarios.size(); i++) {
   	                row = sheet.getRow(++rowCount);
   	            	columnCount = 0;
   	            	
   	            	cell = row.getCell(columnCount);
   	                cell.setCellValue(i+1);
   	                columnCount++;
   	                
   	                cell = row.getCell(columnCount);
   	                cell.setCellValue(funcionarios.get(i).getDni_funcionario());
   	                columnCount++;
   	                
   	            	cell = row.getCell(columnCount);
   	                cell.setCellValue(funcionarios.get(i).getNombres_funcionario()+", "+funcionarios.get(i).getPaterno_funcionario()+" "+funcionarios.get(i).getMaterno_funcionario());
   	                columnCount++;
   	                
   	                cell = row.getCell(columnCount);
   	                cell.setCellValue(sectores_funcionarios.get(i).getCargo_sector_funcionario());
   	                columnCount++;
   	                
   	                cell = row.getCell(columnCount);
   	                cell.setCellValue(sectores_funcionarios.get(i).getFijo_sector_funcionario());
   	                columnCount++;
   	                
   	                cell = row.getCell(columnCount);
   	                cell.setCellValue(sectores_funcionarios.get(i).getAnexo_sector_funcionario());
   	                columnCount++;
   	                
   	                cell = row.getCell(columnCount);
   	                cell.setCellValue(sectores_funcionarios.get(i).getMovil_sector_funcionario());
   	                columnCount++;
   	                
   	                cell = row.getCell(columnCount);
   	                cell.setCellValue(sectores_funcionarios.get(i).getMovil2_sector_funcionario());
   	                columnCount++;
   	                
   	                cell = row.getCell(columnCount);
   	                cell.setCellValue(sectores_funcionarios.get(i).getEmail_sector_funcionario());
   	                columnCount++;
   	                
   	                cell = row.getCell(columnCount);
   	                cell.setCellValue(sectores_funcionarios.get(i).getEmail2_sector_funcionario());
   	                columnCount++;
   	                
   	                rowCount++;
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
    
    
}