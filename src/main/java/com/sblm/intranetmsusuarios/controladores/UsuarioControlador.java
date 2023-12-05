package com.sblm.intranetmsusuarios.controladores;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sblm.intranetmsusuarios.entidades.Usuario;
import com.sblm.intranetmsusuarios.excepciones.ResourceNotFoundException;
import com.sblm.intranetmsusuarios.repositorios.UsuarioRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/usuario")
public class UsuarioControlador {
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
    private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	JdbcTemplate jdbctemplate;

    @GetMapping("/listar")
    public List<Usuario> getAllUsuarios() {
        return usuarioRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable(value = "id") long id_usuario)
        throws ResourceNotFoundException {
    	Usuario usuario = usuarioRepositorio.findById(id_usuario)
          .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado para id :: " + id_usuario));
        return ResponseEntity.ok().body(usuario);
    }
    
    @GetMapping("/recuperarPorNombreUsuario/{username}")
    public ResponseEntity<Usuario> getUsuarioByUserName(@PathVariable(value = "username") String username)
        throws ResourceNotFoundException {
    	Usuario usuario = usuarioRepositorio.findByNombreUsuario(username)
          .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado para id :: " + username));
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("/registrar")
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
    	Date fecha = new Date();
    	usuario.setFechaCreacion(fecha);
        return usuarioRepositorio.save(usuario);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable(value = "id") Long id_usuario,
         @Valid @RequestBody Usuario usuario_detalle) throws ResourceNotFoundException {
    	Usuario usuario = usuarioRepositorio.findById(id_usuario)
        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado para id :: " + id_usuario));

        final Usuario usuario_actualizado = usuarioRepositorio.save(usuario);
        return ResponseEntity.ok(usuario_actualizado);
    }
    
    @PutMapping("/cambiarContrasena/{id}")
    public ResponseEntity<Usuario> cambiarContrasena(@PathVariable(value = "id") Long id_usuario,
         @Valid @RequestBody Usuario usuario_detalle) throws ResourceNotFoundException {
    	Usuario usuario = usuarioRepositorio.findById(id_usuario)
        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado para id :: " + id_usuario));

        usuario.setContrasena(passwordEncoder.encode(usuario_detalle.getContrasena()));
        
        final Usuario usuario_actualizado = usuarioRepositorio.save(usuario);
        return ResponseEntity.ok(usuario_actualizado);
    }
    
}