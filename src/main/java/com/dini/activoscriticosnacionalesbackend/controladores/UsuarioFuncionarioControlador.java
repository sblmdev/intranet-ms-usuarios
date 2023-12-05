package com.dini.activoscriticosnacionalesbackend.controladores;

import com.dini.activoscriticosnacionalesbackend.excepciones.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
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

import com.dini.activoscriticosnacionalesbackend.entidades.Documento;
import com.dini.activoscriticosnacionalesbackend.entidades.UsuarioFuncionario;
import com.dini.activoscriticosnacionalesbackend.repositorios.UsuarioFuncionarioRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/usuarioFuncionario")
public class UsuarioFuncionarioControlador {
	
	@Autowired
    private UsuarioFuncionarioRepositorio usuarioFuncionarioRepositorio;
	
	@Autowired
	JdbcTemplate jdbctemplate;

    @GetMapping("/listar")
    public List<UsuarioFuncionario> getAllUsuariosFuncionarios() {
        return usuarioFuncionarioRepositorio.findAll();
    }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<UsuarioFuncionario> getUsuarioFuncionarioById(@PathVariable(value = "id") long id_usuario_funcionario)
        throws ResourceNotFoundException {
    	UsuarioFuncionario usuario_funcionario = usuarioFuncionarioRepositorio.findById(id_usuario_funcionario)
          .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado para id :: " + id_usuario_funcionario));
        return ResponseEntity.ok().body(usuario_funcionario);
    }

    @PostMapping("/registrar")
    public UsuarioFuncionario createUsuarioFuncionario(@Valid @RequestBody UsuarioFuncionario usuario_funcionario) {
    	Date fecha = new Date();
    	usuario_funcionario.setFecha_creacion(fecha);
    	System.out.println(usuario_funcionario.getProcedencia_usuario_funcionario());
        return usuarioFuncionarioRepositorio.save(usuario_funcionario);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<UsuarioFuncionario> updateUsuarioFuncionario(@PathVariable(value = "id") Long id_usuario_funcionario,
         @Valid @RequestBody UsuarioFuncionario usuario_funcionario_detalle) throws ResourceNotFoundException {
    	UsuarioFuncionario usuario_funcionario = usuarioFuncionarioRepositorio.findById(id_usuario_funcionario)
        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado para id :: " + id_usuario_funcionario));


        usuario_funcionario.setId_usuario(usuario_funcionario_detalle.getId_usuario());
        usuario_funcionario.setCodigo_usuario(usuario_funcionario_detalle.getCodigo_usuario());
        usuario_funcionario.setEstado_usuario_funcionario(usuario_funcionario_detalle.getEstado_usuario_funcionario());
        usuario_funcionario.setFijo_usuario_funcionario(usuario_funcionario_detalle.getFijo_usuario_funcionario());
        usuario_funcionario.setAnexo_usuario_funcionario(usuario_funcionario_detalle.getFijo_usuario_funcionario());
        usuario_funcionario.setMovil_usuario_funcionario(usuario_funcionario_detalle.getMovil_usuario_funcionario());
        usuario_funcionario.setMovil2_usuario_funcionario(usuario_funcionario_detalle.getMovil2_usuario_funcionario());
        usuario_funcionario.setCorreo_usuario_funcionario(usuario_funcionario_detalle.getCorreo_usuario_funcionario());
        usuario_funcionario.setFecha_alta_usuario_funcionario(usuario_funcionario_detalle.getFecha_alta_usuario_funcionario());
        usuario_funcionario.setFecha_baja_usuario_funcionario(usuario_funcionario_detalle.getFecha_baja_usuario_funcionario());
        usuario_funcionario.setProcedencia_usuario_funcionario(usuario_funcionario_detalle.getProcedencia_usuario_funcionario());
        
        Date fecha = new Date();  
        usuario_funcionario.setId_usuario_creacion(usuario_funcionario_detalle.getId_usuario_creacion());
        usuario_funcionario.setFecha_creacion(usuario_funcionario_detalle.getFecha_creacion());
        usuario_funcionario.setIp_creacion(usuario_funcionario_detalle.getIp_creacion());
        usuario_funcionario.setId_usuario_modificacion(usuario_funcionario_detalle.getId_usuario_modificacion());
        if(usuario_funcionario_detalle.getId_usuario_funcionario()==0) {
        	usuario_funcionario.setFecha_modificacion(null);
        }
        else {
        	usuario_funcionario.setFecha_modificacion(fecha);
        }
        usuario_funcionario.setIp_modificacion(usuario_funcionario_detalle.getIp_modificacion());

        final UsuarioFuncionario usuario_funcionario_actualizado = usuarioFuncionarioRepositorio.save(usuario_funcionario);
        return ResponseEntity.ok(usuario_funcionario_actualizado);
    }
    
    @GetMapping("/recuperarPorUsuarioActivo/{id}")
    private UsuarioFuncionario usuarioFuncionarioPorUsuario(@PathVariable(value = "id") long id){
		String query = "SELECT * FROM ta_acn_usufuncionarios WHERE estado_usuario_funcionario = 1 AND id_usuario="+id;
		List<UsuarioFuncionario> usuarios_funcionarios = new ArrayList<UsuarioFuncionario>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			UsuarioFuncionario objeto = new UsuarioFuncionario();
			
			objeto.setId_usuario_funcionario(Long.parseLong(String.valueOf(row.get("id_usuario_funcionario"))));
			objeto.setId_usuario(Long.parseLong(String.valueOf(row.get("id_usuario"))));
			objeto.setCodigo_usuario((String) row.get("codigo_usuario"));
			objeto.setEstado_usuario_funcionario(Integer.parseInt(String.valueOf(row.get("estado_usuario_funcionario"))));
			
			objeto.setFijo_usuario_funcionario((String) row.get("fijo_usuario_funcionario"));
			objeto.setAnexo_usuario_funcionario((String) row.get("anexo_usuario_funcionario"));
			objeto.setMovil_usuario_funcionario((String) row.get("movil_usuario_funcionario"));
			objeto.setMovil2_usuario_funcionario((String) row.get("movil2_usuario_funcionario"));
			objeto.setCorreo_usuario_funcionario((String) row.get("correo_usuario_funcionario"));
			objeto.setFecha_alta_usuario_funcionario((Date) row.get("fecha_alta_usuario_funcionario"));
			objeto.setFecha_baja_usuario_funcionario((Date) row.get("fecha_baja_usuario_funcionario"));
			objeto.setProcedencia_usuario_funcionario((String) row.get("procedencia_usuario_funcionario"));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			usuarios_funcionarios.add(objeto);
		}
		return usuarios_funcionarios.get(0);
	}
    

}