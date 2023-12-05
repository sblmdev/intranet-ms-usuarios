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

import com.dini.activoscriticosnacionalesbackend.entidades.Accionista;
import com.dini.activoscriticosnacionalesbackend.entidades.CapacidadNacional;
import com.dini.activoscriticosnacionalesbackend.entidades.FileServer;
import com.dini.activoscriticosnacionalesbackend.entidades.Funcionario;
import com.dini.activoscriticosnacionalesbackend.entidades.ObjetivoNacional;
import com.dini.activoscriticosnacionalesbackend.entidades.Operador;
import com.dini.activoscriticosnacionalesbackend.entidades.OperadorFuncionario;
import com.dini.activoscriticosnacionalesbackend.entidades.Pais;
import com.dini.activoscriticosnacionalesbackend.entidades.Sector;
import com.dini.activoscriticosnacionalesbackend.entidades.SectorFuncionario;
import com.dini.activoscriticosnacionalesbackend.repositorios.OperadorRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/operador")
public class OperadorControlador {
	
	@Autowired
    private OperadorRepositorio operadorRepositorio;
	
	@Autowired
    private FileServerControlador fileServerControlador;
	
	@Autowired
	JdbcTemplate jdbctemplate;

    @GetMapping("/listar")
    public List<Operador> getAllOperadores() {
        return operadorRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Operador> getOperadorById(@PathVariable(value = "id") long id_operador)
        throws ResourceNotFoundException {
    	Operador operador = operadorRepositorio.findById(id_operador)
          .orElseThrow(() -> new ResourceNotFoundException("Operador no encontrado para id :: " + id_operador));
        return ResponseEntity.ok().body(operador);
    }

    @PostMapping("/registrar")
    public Operador createOperador(@Valid @RequestBody Operador operador) {
    	Date fecha = new Date();
    	operador.setFecha_creacion(fecha);
    	
    	FileServer f = new FileServer();
    	List<FileServer> fileServers = fileServerControlador.getAllFilesServers();
    	for(int i=0; i<fileServers.size(); i++) {
    		if(fileServers.get(i).getEstado_file_server()==1) {
    			f = fileServers.get(i);
    		}
    	}
    	
    	File directorio = new File(f.getDir_file_server()+"operadores\\"+operador.getCodigo_operador());
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
    	
        return operadorRepositorio.save(operador);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Operador> updateOperador(@PathVariable(value = "id") Long id_operador,
         @Valid @RequestBody Operador operador_detalle) throws ResourceNotFoundException {
    	Operador operador = operadorRepositorio.findById(id_operador)
        .orElseThrow(() -> new ResourceNotFoundException("Operador no encontrado para id :: " + id_operador));

        operador.setNombre_operador(operador_detalle.getNombre_operador());
        operador.setConsorcio_operador(operador_detalle.getConsorcio_operador());
        operador.setFecha_inicio_operador(operador_detalle.getFecha_inicio_operador());
        operador.setTipo_operador(operador_detalle.getTipo_operador());
        operador.setTipo_nacionalidad_operador(operador_detalle.getTipo_nacionalidad_operador());
        operador.setNacionalidad_operador(operador_detalle.getNacionalidad_operador());
        operador.setEstado_operador(operador_detalle.getEstado_operador());
        
        Date fecha = new Date();
        operador.setId_usuario_creacion(operador_detalle.getId_usuario_creacion());
        operador.setFecha_creacion(operador_detalle.getFecha_creacion());
        operador.setIp_creacion(operador_detalle.getIp_creacion());
        operador.setId_usuario_modificacion(operador_detalle.getId_usuario_modificacion());
        if(operador_detalle.getId_operador()==0) {
        	operador.setFecha_modificacion(null);
        }
        else {
        	operador.setFecha_modificacion(fecha);
        }
        operador.setIp_modificacion(operador_detalle.getIp_modificacion());
        
        final Operador operador_actualizado = operadorRepositorio.save(operador);
        return ResponseEntity.ok(operador_actualizado);
    }
    
    @GetMapping("/listarCoincidencias/{buscar}")
	private List<Operador> operadoresCoincidencias(@PathVariable(value = "buscar") String cadena)
	        throws ResourceNotFoundException {
		String query = "SELECT * FROM ta_acn_operadores WHERE estado_operador = 1 AND nombre_operador like '%"+cadena+"%' ORDER BY nombre_operador";
		List<Operador> operadores= new ArrayList<Operador>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			Operador objeto = new Operador();
			
			objeto.setId_operador(Long.parseLong(String.valueOf(row.get("id_operador"))));
			objeto.setCodigo_operador((String) row.get("codigo_operador"));
			objeto.setNombre_operador((String) row.get("nombre_operador"));
			objeto.setCriptonomo_operador((String) row.get("criptonomo_operador"));
			objeto.setConsorcio_operador(Integer.parseInt(String.valueOf(row.get("consorcio_operador"))));
			objeto.setFecha_inicio_operador((Date) row.get("fecha_inicio_operador"));
			objeto.setTipo_operador(Integer.parseInt(String.valueOf(row.get("tipo_operador"))));
			objeto.setTipo_nacionalidad_operador(Integer.parseInt(String.valueOf(row.get("tipo_nacionalidad_operador"))));
			objeto.setNacionalidad_operador(Integer.parseInt(String.valueOf(row.get("nacionalidad_operador"))));
			objeto.setEstado_operador(Integer.parseInt(String.valueOf(row.get("estado_operador"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			operadores.add(objeto);
		}

		return operadores;
	}
    
    @GetMapping("/listarIguales/{buscar}")
	private List<Operador> operadoresIguales(@PathVariable(value = "buscar") String cadena)
	        throws ResourceNotFoundException {
		String query = "SELECT * FROM ta_acn_operadores WHERE UPPER(nombre_operador) = UPPER('"+cadena+"') ORDER BY fecha_creacion DESC";
		List<Operador> operadores= new ArrayList<Operador>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			Operador objeto = new Operador();
			
			objeto.setId_operador(Long.parseLong(String.valueOf(row.get("id_operador"))));
			objeto.setCodigo_operador((String) row.get("codigo_operador"));
			objeto.setNombre_operador((String) row.get("nombre_operador"));
			objeto.setCriptonomo_operador((String) row.get("criptonomo_operador"));
			objeto.setConsorcio_operador(Integer.parseInt(String.valueOf(row.get("consorcio_operador"))));
			objeto.setFecha_inicio_operador((Date) row.get("fecha_inicio_operador"));
			objeto.setTipo_operador(Integer.parseInt(String.valueOf(row.get("tipo_operador"))));
			objeto.setTipo_nacionalidad_operador(Integer.parseInt(String.valueOf(row.get("tipo_nacionalidad_operador"))));
			objeto.setNacionalidad_operador(Integer.parseInt(String.valueOf(row.get("nacionalidad_operador"))));
			objeto.setEstado_operador(Integer.parseInt(String.valueOf(row.get("estado_operador"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			operadores.add(objeto);
		}

		return operadores;
	}
    
    @GetMapping("/listarCoincidenciasPorTipo/{buscar}/{tipo}")
	private List<Operador> operadoresCoincidencias(@PathVariable(value = "buscar") String cadena, @PathVariable(value = "tipo") int tipo)
	        throws ResourceNotFoundException {
		String query = "SELECT * FROM ta_acn_operadores WHERE estado_operador = 1 AND nombre_operador like '%"+cadena+"%' AND tipo_operador="+tipo+" ORDER BY nombre_operador";
		List<Operador> operadores= new ArrayList<Operador>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			Operador objeto = new Operador();
			
			objeto.setId_operador(Long.parseLong(String.valueOf(row.get("id_operador"))));
			objeto.setCodigo_operador((String) row.get("codigo_operador"));
			objeto.setNombre_operador((String) row.get("nombre_operador"));
			objeto.setCriptonomo_operador((String) row.get("criptonomo_operador"));
			objeto.setConsorcio_operador(Integer.parseInt(String.valueOf(row.get("consorcio_operador"))));
			objeto.setFecha_inicio_operador((Date) row.get("fecha_inicio_operador"));
			objeto.setTipo_operador(Integer.parseInt(String.valueOf(row.get("tipo_operador"))));
			objeto.setTipo_nacionalidad_operador(Integer.parseInt(String.valueOf(row.get("tipo_nacionalidad_operador"))));
			objeto.setNacionalidad_operador(Integer.parseInt(String.valueOf(row.get("nacionalidad_operador"))));
			objeto.setEstado_operador(Integer.parseInt(String.valueOf(row.get("estado_operador"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			operadores.add(objeto);
		}

		return operadores;
	}
    
    @GetMapping("/listarActivos")
   	private List<Operador> operadoresActivos()
   	        throws ResourceNotFoundException {
   		String query = "SELECT * FROM ta_acn_operadores WHERE estado_operador = 1 ORDER BY nombre_operador";
   		List<Operador> operadores= new ArrayList<Operador>();
   		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
   		
   		for(Map row: rows) {
   			Operador objeto = new Operador();
   			
   			objeto.setId_operador(Long.parseLong(String.valueOf(row.get("id_operador"))));
   			objeto.setCodigo_operador((String) row.get("codigo_operador"));
   			objeto.setNombre_operador((String) row.get("nombre_operador"));
   			objeto.setCriptonomo_operador((String) row.get("criptonomo_operador"));
   			objeto.setConsorcio_operador(Integer.parseInt(String.valueOf(row.get("consorcio_operador"))));
   			objeto.setFecha_inicio_operador((Date) row.get("fecha_inicio_operador"));
   			objeto.setTipo_operador(Integer.parseInt(String.valueOf(row.get("tipo_operador"))));
   			objeto.setTipo_nacionalidad_operador(Integer.parseInt(String.valueOf(row.get("tipo_nacionalidad_operador"))));
   			objeto.setNacionalidad_operador(Integer.parseInt(String.valueOf(row.get("nacionalidad_operador"))));
   			objeto.setEstado_operador(Integer.parseInt(String.valueOf(row.get("estado_operador"))));
   			
   			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
   			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
   			objeto.setIp_creacion((String) row.get("ip_creacion"));
   			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
   			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
   			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
   			
   			operadores.add(objeto);
   		}

   		return operadores;
   	}
    
    @GetMapping("/listarActivosPorTipo/{tipo}")
	private List<Operador> operadoresActivosPorTipo(@PathVariable(value = "tipo") int tipo)
	        throws ResourceNotFoundException {
		String query = "SELECT * FROM ta_acn_operadores WHERE estado_operador = 1 AND tipo_operador = "+tipo+" ORDER BY nombre_operador";
		List<Operador> operadores= new ArrayList<Operador>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			Operador objeto = new Operador();
			
			objeto.setId_operador(Long.parseLong(String.valueOf(row.get("id_operador"))));
			objeto.setCodigo_operador((String) row.get("codigo_operador"));
			objeto.setNombre_operador((String) row.get("nombre_operador"));
			objeto.setCriptonomo_operador((String) row.get("criptonomo_operador"));
			objeto.setConsorcio_operador(Integer.parseInt(String.valueOf(row.get("consorcio_operador"))));
			objeto.setFecha_inicio_operador((Date) row.get("fecha_inicio_operador"));
			objeto.setTipo_operador(Integer.parseInt(String.valueOf(row.get("tipo_operador"))));
			objeto.setTipo_nacionalidad_operador(Integer.parseInt(String.valueOf(row.get("tipo_nacionalidad_operador"))));
			objeto.setNacionalidad_operador(Integer.parseInt(String.valueOf(row.get("nacionalidad_operador"))));
			objeto.setEstado_operador(Integer.parseInt(String.valueOf(row.get("estado_operador"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			operadores.add(objeto);
		}

		return operadores;
	}
    
    @PostMapping(value="/reporteGeneral", produces = "application/json")
    private String reporteGeneralOperadores(@Valid @RequestBody List<Object> lista)throws ResourceNotFoundException, InvalidFormatException {
    	
    	String query = "";
    	if(lista.get(6).toString().equals("")) {
    		if(lista.get(5).toString().equals("0")) {		
    			query = "SELECT * FROM ta_acn_operadores WHERE estado_operador=1 ORDER BY nombre_operador";
    		}
    		else {
    			query = "SELECT * FROM ta_acn_operadores WHERE estado_operador=1 AND tipo_operador="+lista.get(5).toString()+" ORDER BY nombre_operador";
    		}
    	}
    	else {
    		if(lista.get(5).toString().equals("0")) {
    			query = "SELECT * FROM ta_acn_operadores WHERE estado_operador=1 AND nombre_operador like '"+lista.get(6).toString()+"' ORDER BY nombre_operador";
    		}
    		else {
    			query = "SELECT * FROM ta_acn_operadores WHERE estado_operador=1 AND nombre_operador like '"+lista.get(6).toString()+"' AND tipo_operador="+lista.get(5).toString()+" ORDER BY nombre_operador";
    			System.out.println(query);    		}
    	}

    	boolean problema = false;
    	List<Operador> operadores = new ArrayList<Operador>();
		try {
			List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
			
			for(Map row: rows) {
				Operador objeto = new Operador();
				
				objeto.setId_operador(Long.parseLong(String.valueOf(row.get("id_operador"))));
				objeto.setCodigo_operador((String) row.get("codigo_operador"));
				objeto.setNombre_operador((String) row.get("nombre_operador"));
				objeto.setCriptonomo_operador((String) row.get("criptonomo_operador"));
				objeto.setConsorcio_operador(Integer.parseInt(String.valueOf(row.get("consorcio_operador"))));
				objeto.setFecha_inicio_operador((Date) row.get("fecha_inicio_operador"));
				objeto.setTipo_operador(Integer.parseInt(String.valueOf(row.get("tipo_operador"))));
				objeto.setTipo_nacionalidad_operador(Integer.parseInt(String.valueOf(row.get("tipo_nacionalidad_operador"))));
				objeto.setNacionalidad_operador(Integer.parseInt(String.valueOf(row.get("nacionalidad_operador"))));
				objeto.setEstado_operador(Integer.parseInt(String.valueOf(row.get("estado_operador"))));
				
				Pais pais = new Pais();
	        	try {
	   	   			String sql2 = "SELECT * FROM ta_acn_paises WHERE id_pais = "+objeto.getNacionalidad_operador();
	   	   			Map<String, Object> rows2 = jdbctemplate.queryForMap(sql2);
	   	   			objeto.setCodigo_operador(String.valueOf(rows2.get("gentilicio")));
	        	}catch(Exception e) {
	        		problema = true;
	        	}
				
				objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
				objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
				objeto.setIp_creacion((String) row.get("ip_creacion"));
				objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
				objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
				objeto.setIp_modificacion((String) row.get("ip_modificacion"));
				
				operadores.add(objeto);
			}
		}
		catch(Exception e) {
			problema = true;
		}
		
    	if(!problema) {
    		String excelFilePath = "Reporte Operadores.xlsx";
        	
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
                	cell.setCellValue("Operador");
                	columnCount ++;
                }
                
                if(lista.get(1).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Criptónomo");
                	columnCount ++;
                }
                
                if(lista.get(2).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Tipo de Entidad");
                	columnCount ++;
                }
                
                if(lista.get(3).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Tipo de Nacionalidad");
                	columnCount ++;
                }
                
                if(lista.get(4).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Nacionalidad");
                	columnCount ++;
                }
                
     
                for(int i=0; i<operadores.size(); i++) {
                	row = sheet.getRow(++rowCount);
                	
                	columnCount = 0;
                	cell = row.getCell(columnCount);
                    cell.setCellValue(i+1);
                    columnCount ++;
                    
                    if(lista.get(0).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(operadores.get(i).getNombre_operador());
                    	columnCount ++;
                    }
                    
                    if(lista.get(1).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(operadores.get(i).getCriptonomo_operador());
                    	columnCount ++;
                    }
                                        
                    if(lista.get(2).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	if(operadores.get(i).getTipo_operador()==1) {
                        	cell.setCellValue("Pública");
                    	}
                    	else {
                    		cell.setCellValue("Privada");
                    	}
                    	columnCount ++;
                    }
                         
                    if(lista.get(3).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	if(operadores.get(i).getTipo_nacionalidad_operador()==1) {
                        	cell.setCellValue("Nacional");
                    	}
                    	else {
                    		cell.setCellValue("Extranjera");
                    	}
                    	columnCount ++;
                    }
                    
                    if(lista.get(4).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(operadores.get(i).getCodigo_operador().substring(0, 1).toUpperCase() + operadores.get(i).getCodigo_operador().substring(1).toLowerCase());
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
    private String reporteParticularOperador(@PathVariable(value = "id") Long id_operador)throws ResourceNotFoundException, InvalidFormatException {
    	
    	boolean problema = false;
    	String codigo = "";
    	List<Operador> operadores = new ArrayList<Operador>();
    	List<Accionista> accionistas = new ArrayList<Accionista>();
    	try {
    		String query = "SELECT * FROM ta_acn_operadores WHERE id_operador="+id_operador.toString();
    		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
    		for(Map row: rows) {
    			Operador objeto = new Operador();
				
				objeto.setId_operador(Long.parseLong(String.valueOf(row.get("id_operador"))));
				objeto.setCodigo_operador((String) row.get("codigo_operador"));
				codigo = objeto.getCodigo_operador();
				objeto.setNombre_operador((String) row.get("nombre_operador"));
				objeto.setCriptonomo_operador((String) row.get("criptonomo_operador"));
				objeto.setConsorcio_operador(Integer.parseInt(String.valueOf(row.get("consorcio_operador"))));
				objeto.setFecha_inicio_operador((Date) row.get("fecha_inicio_operador"));
				objeto.setTipo_operador(Integer.parseInt(String.valueOf(row.get("tipo_operador"))));
				objeto.setTipo_nacionalidad_operador(Integer.parseInt(String.valueOf(row.get("tipo_nacionalidad_operador"))));
				objeto.setNacionalidad_operador(Integer.parseInt(String.valueOf(row.get("nacionalidad_operador"))));
				objeto.setEstado_operador(Integer.parseInt(String.valueOf(row.get("estado_operador"))));
				
	        	try {
	   	   			String sql2 = "SELECT * FROM ta_acn_paises WHERE id_pais = "+objeto.getNacionalidad_operador();
	   	   			Map<String, Object> rows2 = jdbctemplate.queryForMap(sql2);
	   	   			objeto.setCodigo_operador(String.valueOf(rows2.get("gentilicio")));
	        	}catch(Exception e) {
	        		problema = true;
	        	}
	        	  		
				objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
				objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
				objeto.setIp_creacion((String) row.get("ip_creacion"));
				objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
				objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
				objeto.setIp_modificacion((String) row.get("ip_modificacion"));
				
				operadores.add(objeto);
    		}
    	}
    	catch(Exception e) {
    		problema = true;
    	}
    	
    	try {
    		String query3 = "SELECT * FROM ta_acn_accionistas WHERE estado_accionista=1 AND codigo_operador='"+codigo+"'";
    		List<Map<String, Object>> rows3 = jdbctemplate.queryForList(query3);
    		for(Map row3: rows3) {
    			Accionista objeto3 = new Accionista();
    			
    			objeto3.setId_accionista(Long.parseLong(String.valueOf(row3.get("id_accionista"))));
    			objeto3.setCodigo_operador((String) row3.get("codigo_operador"));
    			objeto3.setNombre_accionista((String) row3.get("nombre_accionista"));
    			objeto3.setNacionalidad_accionista(Integer.parseInt(String.valueOf(row3.get("nacionalidad_accionista"))));
    			objeto3.setEstado_accionista(Integer.parseInt(String.valueOf(row3.get("estado_accionista"))));
    			
	        	try {
	   	   			String sql6 = "SELECT * FROM ta_acn_paises WHERE id_pais = "+objeto3.getNacionalidad_accionista();
	   	   			Map<String, Object> rows6 = jdbctemplate.queryForMap(sql6);
	   	   			objeto3.setCodigo_operador(String.valueOf(rows6.get("gentilicio")));
	        	}catch(Exception e) {
	        		problema = true;
	        	}
				
    			objeto3.setId_usuario_creacion(Long.parseLong(String.valueOf(row3.get("id_usuario_creacion"))));
    			objeto3.setFecha_creacion((Date) row3.get("fecha_creacion"));
    			objeto3.setIp_creacion((String) row3.get("ip_creacion"));
    			objeto3.setId_usuario_modificacion(Long.parseLong(String.valueOf(row3.get("id_usuario_modificacion"))));
    			objeto3.setFecha_modificacion((Date) row3.get("fecha_modificacion"));
    			objeto3.setIp_modificacion((String) row3.get("ip_modificacion"));
    			
    			accionistas.add(objeto3);
    		}
    	}
    	catch(Exception e) {
    		problema = true;
    	}
    	
    	
    	String sql = "SELECT * FROM ta_acn_opereplegales WHERE codigo_operador = '"+operadores.get(0).getCodigo_operador()+"' AND estado_operador_funcionario=1 order by codigo_funcionario";
       	List<OperadorFuncionario> operadores_funcionarios = new ArrayList<OperadorFuncionario>();
       	List<Map<String, Object>> rows4 = jdbctemplate.queryForList(sql);
   		for(Map row: rows4) {
   			OperadorFuncionario objeto = new OperadorFuncionario();
   			
   			objeto.setId_operador_funcionario(Long.parseLong(String.valueOf(row.get("id_operador_funcionario"))));
   			objeto.setCodigo_funcionario((String) row.get("codigo_funcionario"));
   			objeto.setCodigo_operador((String) row.get("codigo_operador"));
   			objeto.setCargo_operador_funcionario((String) row.get("cargo_operador_funcionario"));
   			objeto.setFijo_operador_funcionario((String) row.get("fijo_operador_funcionario"));
   			objeto.setAnexo_operador_funcionario((String) row.get("anexo_operador_funcionario"));
   			objeto.setMovil_operador_funcionario((String) row.get("movil_operador_funcionario"));
   			objeto.setMovil2_operador_funcionario((String) row.get("movil2_operador_funcionario"));
   			objeto.setEmail_operador_funcionario((String) row.get("email_operador_funcionario"));
   			objeto.setEmail2_operador_funcionario((String) row.get("email2_operador_funcionario"));
   			objeto.setInicio_operador_funcionario((Date) row.get("inicio_operador_funcionario"));
			objeto.setFin_operador_funcionario((Date) row.get("fin_operador_funcionario"));
   			objeto.setEstado_operador_funcionario(Integer.parseInt(String.valueOf(row.get("estado_sector_funcionario"))));
   			
   			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
   			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
   			objeto.setIp_creacion((String) row.get("ip_creacion"));
   			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
   			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
   			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
   			
   			operadores_funcionarios.add(objeto);
   		}
   		
   		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
   		for(int i=0; i<operadores_funcionarios.size(); i++) {
   			try {
   	   			String sql2 = "SELECT * FROM ta_acn_personas WHERE codigo_funcionario = '"+operadores_funcionarios.get(i).getCodigo_funcionario()+"'";
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
   			String excelFilePath = "Reporte Operador.xlsx";
   	    	
   	    	try {
   	            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
   	            Workbook workbook = WorkbookFactory.create(inputStream);
   	 
   	            Sheet sheet = workbook.getSheetAt(0);
   	 
   	            int rowCount = 4;
   	            Row row = sheet.getRow(++rowCount);
   	        	int columnCount = 2;
   	        	Cell cell = row.getCell(columnCount);
   	            cell.setCellValue(operadores.get(0).getNombre_operador());
   	            
   	            rowCount = 5;
	            row = sheet.getRow(++rowCount);
	        	columnCount = 2;
	        	cell = row.getCell(columnCount);
	        	cell.setCellValue(operadores.get(0).getCriptonomo_operador());

   	            rowCount = 6;
   	            row = sheet.getRow(++rowCount);
   	        	columnCount = 2;
   	        	cell = row.getCell(columnCount);
   	        	if(operadores.get(0).getTipo_operador()==1) {
   	        		cell.setCellValue("Pública");
   	        	}
   	        	else {
   	        		cell.setCellValue("Privada");
   	        	}
   	            
   	        	rowCount = 7;
   	            row = sheet.getRow(++rowCount);
   	        	columnCount = 2;
   	        	cell = row.getCell(columnCount);
   	        	if(operadores.get(0).getTipo_nacionalidad_operador()==1) {
   	        		cell.setCellValue("Nacional");
   	        	}
   	        	else {
   	        		cell.setCellValue("Extranjera");
   	        	}
   	        	
   	        	rowCount = 8;
	            row = sheet.getRow(++rowCount);
	        	columnCount = 2;
	        	cell = row.getCell(columnCount);
	        	cell.setCellValue(operadores.get(0).getCodigo_operador().substring(0, 1).toUpperCase() + operadores.get(0).getCodigo_operador().substring(1).toLowerCase());
   	            
	        	rowCount = 11;
   	            if(accionistas.size()!=0) {
   	            	System.out.println(rowCount);
   	            	
   		            row = sheet.getRow(rowCount);
   		            
   		        	columnCount = 0;
   		        	cell = row.getCell(columnCount);
   		        	cell.setCellValue("N");
   		        	
   		        	columnCount = 1;
   		        	cell = row.getCell(columnCount);
   		        	cell.setCellValue("Accionista");
   		        	
   		        	columnCount = 2;
   		        	cell = row.getCell(columnCount);
   		        	cell.setCellValue("Nacionalidad");
   		        	
   		        	rowCount++;
   		        	System.out.println(rowCount);
   	            	for(int m=0;m<accionistas.size();m++) {

   	            		row = sheet.getRow(rowCount);
	   	 	        	columnCount = 0;
	   	 	        	cell = row.getCell(columnCount);
	   	 	        	cell.setCellValue(m+1);
	   	 	        	
		   	 	        columnCount = 1;
	   	 	        	cell = row.getCell(columnCount);
	   	 	        	cell.setCellValue(accionistas.get(m).getNombre_accionista());

	   	 	        	columnCount = 2;
		 	        	cell = row.getCell(columnCount);
		 	        	cell.setCellValue(accionistas.get(m).getCodigo_operador());
		 	        	
		 	        	rowCount++;
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
    
}