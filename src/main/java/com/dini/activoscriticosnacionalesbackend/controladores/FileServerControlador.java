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
import com.dini.activoscriticosnacionalesbackend.entidades.FileServer;
import com.dini.activoscriticosnacionalesbackend.repositorios.FileServerRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/fileServer")
public class FileServerControlador {
	
	@Autowired
    private FileServerRepositorio fileServerRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<FileServer> getAllFilesServers() {
        return fileServerRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<FileServer> getDocumentoById(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
        FileServer fileServer = fileServerRepositorio.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("FileServer no encontrado para id :: " + id));
        return ResponseEntity.ok().body(fileServer);
    }
    
    @PostMapping("/registrar")
    public FileServer createAccionista(@Valid @RequestBody FileServer fileServer) {
    	Date fecha = new Date();
    	fileServer.setFecha_creacion(fecha);
        return fileServerRepositorio.save(fileServer);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<FileServer> updateAccionista(@PathVariable(value = "id") long id,
         @Valid @RequestBody FileServer fs_detalle) throws ResourceNotFoundException {
        FileServer fs = fileServerRepositorio.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Accionista no encontrado para id :: " + id));

        fs.setCodigo_file_server(fs_detalle.getCodigo_file_server());
        fs.setUrl_file_server(fs_detalle.getUrl_file_server());
        fs.setDir_file_server(fs_detalle.getDir_file_server());
        fs.setEstado_file_server(fs_detalle.getEstado_file_server());
        
        Date fecha = new Date();
        fs.setId_usuario_creacion(fs_detalle.getId_usuario_creacion());
        fs.setFecha_creacion(fs_detalle.getFecha_creacion());
        fs.setIp_creacion(fs_detalle.getIp_creacion());
        fs.setId_usuario_modificacion(fs_detalle.getId_usuario_modificacion());
        if(fs_detalle.getId_file_server()==0) {
        	fs.setFecha_modificacion(null);
        }
        else {
        	fs.setFecha_modificacion(fecha);
        }
        fs.setIp_modificacion(fs_detalle.getIp_modificacion());
        
        final FileServer fs_actualizado = fileServerRepositorio.save(fs);
        return ResponseEntity.ok(fs_actualizado);
    } 
      
}