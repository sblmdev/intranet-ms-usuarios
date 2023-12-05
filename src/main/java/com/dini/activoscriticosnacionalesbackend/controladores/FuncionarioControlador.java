package com.dini.activoscriticosnacionalesbackend.controladores;

import com.dini.activoscriticosnacionalesbackend.excepciones.ResourceNotFoundException;

import java.io.File;
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

import com.dini.activoscriticosnacionalesbackend.entidades.FileServer;
import com.dini.activoscriticosnacionalesbackend.entidades.Funcionario;
import com.dini.activoscriticosnacionalesbackend.repositorios.FuncionarioRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/funcionario")
public class FuncionarioControlador {
	
	@Autowired
    private FuncionarioRepositorio funcionarioRepositorio;
	
	@Autowired
    private FileServerControlador fileServerControlador;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	 @GetMapping("/listar")
	 public List<Funcionario> getAllFuncionarios() {
	    return funcionarioRepositorio.findAll();
	 }
	 
    @GetMapping(value = "/recuperarPorDni/{dni}", produces = "application/json")
	private Funcionario getFuncionarioByDni(@PathVariable String dni)  {

		try {
			String sql = "SELECT * FROM ta_acn_personas WHERE dni_funcionario = '"+dni+"'";
			Map<String, Object> rows = jdbcTemplate.queryForMap(sql);
			
			Funcionario funcionario = new Funcionario();
			funcionario.setId_funcionario(Long.parseLong(String.valueOf(rows.get("id_funcionario"))));
			funcionario.setCodigo_funcionario(String.valueOf(rows.get("codigo_funcionario")));
			funcionario.setDni_funcionario(String.valueOf(rows.get("dni_funcionario")));
			funcionario.setPaterno_funcionario(String.valueOf(rows.get("paterno_funcionario")));
			funcionario.setMaterno_funcionario(String.valueOf(rows.get("materno_funcionario")));
			funcionario.setNombres_funcionario(String.valueOf(rows.get("nombres_funcionario")));
			funcionario.setImagen_funcionario(String.valueOf(rows.get("imagen_funcionario")));
			
			funcionario.setId_usuario_creacion(Integer.parseInt(String.valueOf(rows.get("id_usuario_creacion"))));
			funcionario.setFecha_creacion((Date)rows.get("fecha_creacion"));
			funcionario.setIp_creacion(String.valueOf(rows.get("ip_creacion")));
			funcionario.setId_usuario_modificacion(Integer.parseInt(String.valueOf(rows.get("id_usuario_modificacion"))));
			funcionario.setFecha_modificacion((Date)rows.get("fecha_modificacion"));
			funcionario.setIp_modificacion(String.valueOf(rows.get("ip_modificacion")));
			
			return funcionario;
		}catch(Exception e) {
			return null;
		}
	}
	 
    @GetMapping(value = "/recuperarPorCodigo/{codigo}", produces = "application/json")
   	private Funcionario getFuncionarioByCodigo(@PathVariable String codigo)  {
   		try {
   			String sql = "SELECT * FROM ta_acn_personas WHERE codigo_funcionario = '"+codigo+"'";
   			Map<String, Object> rows = jdbcTemplate.queryForMap(sql);
   			
   			Funcionario funcionario = new Funcionario();
   			funcionario.setId_funcionario(Long.parseLong(String.valueOf(rows.get("id_funcionario"))));
   			funcionario.setCodigo_funcionario(String.valueOf(rows.get("codigo_funcionario")));
   			funcionario.setDni_funcionario(String.valueOf(rows.get("dni_funcionario")));
   			funcionario.setPaterno_funcionario(String.valueOf(rows.get("paterno_funcionario")));
   			funcionario.setMaterno_funcionario(String.valueOf(rows.get("materno_funcionario")));
   			funcionario.setNombres_funcionario(String.valueOf(rows.get("nombres_funcionario")));
   			funcionario.setImagen_funcionario(String.valueOf(rows.get("imagen_funcionario")));
   			
   			funcionario.setId_usuario_creacion(Integer.parseInt(String.valueOf(rows.get("id_usuario_creacion"))));
   			funcionario.setFecha_creacion((Date)rows.get("fecha_creacion"));
   			funcionario.setIp_creacion(String.valueOf(rows.get("ip_creacion")));
   			funcionario.setId_usuario_modificacion(Integer.parseInt(String.valueOf(rows.get("id_usuario_modificacion"))));
   			funcionario.setFecha_modificacion((Date)rows.get("fecha_modificacion"));
   			funcionario.setIp_modificacion(String.valueOf(rows.get("ip_modificacion")));

   			return funcionario;
   		}catch(Exception e) {
   			return null;
   		}
   	}
   	 
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable(value = "id") long id_funcionario)
        throws ResourceNotFoundException {
    	Funcionario funcionario = funcionarioRepositorio.findById(id_funcionario)
          .orElseThrow(() -> new ResourceNotFoundException("Funcionario no encontrado para id :: " + id_funcionario));
        return ResponseEntity.ok().body(funcionario);
    }

    @PostMapping("/registrar")
    public Funcionario createFuncionario(@Valid @RequestBody Funcionario funcionario) {
    	Date fecha = new Date();
    	funcionario.setFecha_creacion(fecha);
    	
    	FileServer f = new FileServer();
    	List<FileServer> fileServers = fileServerControlador.getAllFilesServers();
    	for(int i=0; i<fileServers.size(); i++) {
    		if(fileServers.get(i).getEstado_file_server()==1) {
    			f = fileServers.get(i);
    		}
    	}
    	
    	File directorio = new File(f.getDir_file_server()+"funcionarios\\"+funcionario.getCodigo_funcionario());
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        return funcionarioRepositorio.save(funcionario);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable(value = "id") Long id_funcionario,
         @Valid @RequestBody Funcionario funcionario_detalle) throws ResourceNotFoundException {
    	Funcionario funcionario = funcionarioRepositorio.findById(id_funcionario)
        .orElseThrow(() -> new ResourceNotFoundException("Funcionario no encontrado para id :: " + id_funcionario));

        funcionario.setCodigo_funcionario(funcionario_detalle.getCodigo_funcionario());
        funcionario.setDni_funcionario(funcionario_detalle.getDni_funcionario());
        funcionario.setPaterno_funcionario(funcionario_detalle.getPaterno_funcionario());
        funcionario.setMaterno_funcionario(funcionario_detalle.getMaterno_funcionario());
        funcionario.setNombres_funcionario(funcionario_detalle.getNombres_funcionario());
        funcionario.setImagen_funcionario(funcionario_detalle.getImagen_funcionario());
 
        Date fecha = new Date();
        funcionario.setId_usuario_creacion(funcionario_detalle.getId_usuario_creacion());
        funcionario.setFecha_creacion(funcionario_detalle.getFecha_creacion());
        funcionario.setIp_creacion(funcionario_detalle.getIp_creacion());
        funcionario.setId_usuario_modificacion(funcionario_detalle.getId_usuario_modificacion());
        if(funcionario_detalle.getId_funcionario()==0) {
        	funcionario.setFecha_modificacion(null);
        }
        else {
        	funcionario.setFecha_modificacion(fecha);
        }
        funcionario.setIp_modificacion(funcionario_detalle.getIp_modificacion());
     
        final Funcionario funcionario_actualizado = funcionarioRepositorio.save(funcionario);
        return ResponseEntity.ok(funcionario_actualizado);
    }
    
}