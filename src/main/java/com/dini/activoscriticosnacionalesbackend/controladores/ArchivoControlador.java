package com.dini.activoscriticosnacionalesbackend.controladores;

import com.dini.activoscriticosnacionalesbackend.excepciones.ResourceNotFoundException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dini.activoscriticosnacionalesbackend.entidades.Archivo;
import com.dini.activoscriticosnacionalesbackend.repositorios.ArchivoRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/archivo")
public class ArchivoControlador {
	
	@Autowired
    private ArchivoRepositorio archivoRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//List<String> files = new ArrayList<String>();
	//private final Path rootLocation = Paths.get("C:\\Users\\LENOVO\\Desktop\\archivos");

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Archivo> getArchivoById(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
        Archivo archivo = archivoRepositorio.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Archivo no encontrado para id :: " + id));
        return ResponseEntity.ok().body(archivo);
    }
    
    @PostMapping("/registrar")
    public Archivo createArchivo(@Valid @RequestBody Archivo archivo) {
    	Date fecha = new Date();
    	archivo.setFecha_creacion(fecha);
        return archivoRepositorio.save(archivo);
    }

    @GetMapping("/listar")
    public List<Archivo> getAllArchivos() {
        return archivoRepositorio.findAll();
    }
      
    @PostMapping("/guardarArchivo")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("ruta") String ruta,  @RequestParam("nuevo") String nuevo) {
    	Path rootLocation = Paths.get(ruta);
		String message;
		try {
			try {
				System.out.println(file.getOriginalFilename());
				Files.copy(file.getInputStream(), rootLocation.resolve(file.getOriginalFilename()));		
				File f1 = new File(ruta+"\\"+file.getOriginalFilename());
		        File f2 = new File(nuevo);

		        boolean correcto = f1.renameTo(f2);
		        if (correcto) {
		            System.out.println("El renombrado ha sido correcto");
		        } else {
		            System.out.println("El renombrado no se ha podido realizar");
		        }
			} catch (Exception e) {
				throw new RuntimeException("FAIL!");
			}
			//files.add(file.getOriginalFilename());
			
			message = file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "Failed to upload!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
}