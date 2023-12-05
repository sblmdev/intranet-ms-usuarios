package com.dini.activoscriticosnacionalesbackend.controladores;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/login")
public class LoginControlador {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping(value = "/obtenerUsuarios/{usuario}/{contrasenia}", produces = "application/json")
	private Object obtenerUsuario(@PathVariable String usuario, @PathVariable String contrasenia) {
		try {
			String sql = "SELECT * FROM usuarios WHERE nombre_usuario = '"+usuario+"' AND contrasena_usuario = '"+contrasenia+"' AND estado_usuario=1;";
			Map<String, Object> rows = jdbcTemplate.queryForMap(sql);
			return rows;
		}catch(Exception e) {
			System.out.println("Error de conexion");
			return null;
		}
	}

}