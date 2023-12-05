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

import com.dini.activoscriticosnacionalesbackend.entidades.Funcionario;
import com.dini.activoscriticosnacionalesbackend.entidades.OperadorContacto;
import com.dini.activoscriticosnacionalesbackend.repositorios.OperadorContactoRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/operadorContacto")
public class OperadorContactoControlador {
	
	@Autowired
    private OperadorContactoRepositorio operadorContactoRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	 @GetMapping("/listar")
	 public List<OperadorContacto> getAllOperadorContactos() {
	    return operadorContactoRepositorio.findAll();
	 }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<OperadorContacto> getOperadorContactoById(@PathVariable(value = "id") long id_operador_contacto)
        throws ResourceNotFoundException {
    	OperadorContacto operador_contacto = operadorContactoRepositorio.findById(id_operador_contacto)
          .orElseThrow(() -> new ResourceNotFoundException("Operador contacto no encontrado para id :: " + id_operador_contacto));
        return ResponseEntity.ok().body(operador_contacto);
    }
    
       
    @GetMapping("/listarPorOperador/{codigo}")
	private List<OperadorContacto> operadoresContactosPorOperador(@PathVariable(value = "codigo") String codigo){
    	String sql = "SELECT * FROM ta_acn_opeacncontactos WHERE codigo_operador = '"+codigo+"'";
    	List<OperadorContacto> operadores_contactos = new ArrayList<OperadorContacto>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		for(Map row: rows) {
			OperadorContacto objeto = new OperadorContacto();
			
			objeto.setId_operador_contacto(Long.parseLong(String.valueOf(row.get("id_operador_contacto"))));
			objeto.setCodigo_funcionario((String) row.get("codigo_funcionario"));
			objeto.setCodigo_operador((String) row.get("codigo_operador"));
			objeto.setCodigo_acn((String) row.get("codigo_acn"));
			objeto.setCargo_operador_contacto((String) row.get("cargo_operador_contacto"));
			objeto.setFijo_operador_contacto((String) row.get("fijo_operador_contacto"));
			objeto.setAnexo_operador_contacto((String) row.get("anexo_operador_contacto"));
			objeto.setMovil_operador_contacto((String) row.get("movil_operador_contacto"));
			objeto.setMovil2_operador_contacto((String) row.get("movil2_operador_contacto"));
			objeto.setEmail_operador_contacto((String) row.get("email_operador_contacto"));
			objeto.setEmail2_operador_contacto((String) row.get("email2_operador_contacto"));
			objeto.setInicio_operador_contacto((Date) row.get("inicio_operador_contacto"));
			objeto.setFin_operador_contacto((Date) row.get("fin_operador_contacto"));
			objeto.setEstado_operador_contacto(Integer.parseInt(String.valueOf(row.get("estado_operador_contacto"))));
			objeto.setTipo_operador_contacto(Integer.parseInt(String.valueOf(row.get("tipo_operador_contacto"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			operadores_contactos.add(objeto);
		}
		return operadores_contactos;
	}
    
    @GetMapping("/listarActivosPorOperador/{codigo}")
   	private List<OperadorContacto> operadorescontactosActivosPorOperador(@PathVariable(value = "codigo") String codigo){
       	String sql = "SELECT * FROM ta_acn_opeacncontactos WHERE codigo_operador = '"+codigo+"' AND estado_operador_contacto=1 order by codigo_funcionario";
       	List<OperadorContacto> operadores_contactos = new ArrayList<OperadorContacto>();
   		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
   		
   		for(Map row: rows) {
   			OperadorContacto objeto = new OperadorContacto();
   			
   			objeto.setId_operador_contacto(Long.parseLong(String.valueOf(row.get("id_operador_contacto"))));
   			objeto.setCodigo_funcionario((String) row.get("codigo_funcionario"));
   			objeto.setCodigo_operador((String) row.get("codigo_operador"));
   			objeto.setCodigo_acn((String) row.get("codigo_acn"));
   			objeto.setCargo_operador_contacto((String) row.get("cargo_operador_contacto"));
   			objeto.setFijo_operador_contacto((String) row.get("fijo_operador_contacto"));
   			objeto.setAnexo_operador_contacto((String) row.get("anexo_operador_contacto"));
   			objeto.setMovil_operador_contacto((String) row.get("movil_operador_contacto"));
   			objeto.setMovil2_operador_contacto((String) row.get("movil2_operador_contacto"));
   			objeto.setEmail_operador_contacto((String) row.get("email_operador_contacto"));
   			objeto.setEmail2_operador_contacto((String) row.get("email2_operador_contacto"));
   			objeto.setInicio_operador_contacto((Date) row.get("inicio_operador_contacto"));
			objeto.setFin_operador_contacto((Date) row.get("fin_operador_contacto"));
   			objeto.setEstado_operador_contacto(Integer.parseInt(String.valueOf(row.get("estado_operador_contacto"))));
   			
   			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
   			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
   			objeto.setIp_creacion((String) row.get("ip_creacion"));
   			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
   			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
   			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
   			
   			operadores_contactos.add(objeto);
   		}
   		return operadores_contactos;
   	}
    
    @GetMapping("/listarActivosPorOperadorYAcn/{operador}/{acn}")
   	private List<OperadorContacto> operadorescontactosActivosPorOperadorYAcn(@PathVariable(value = "operador") String operador, @PathVariable(value = "acn") String acn){
       	String sql = "SELECT * FROM ta_acn_opeacncontactos WHERE codigo_operador = '"+operador+"' AND estado_operador_contacto=1 AND codigo_acn='"+acn+"' order by tipo_operador_contacto";
       	List<OperadorContacto> operadores_contactos = new ArrayList<OperadorContacto>();
   		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
   		
   		for(Map row: rows) {
   			OperadorContacto objeto = new OperadorContacto();
   			
   			objeto.setId_operador_contacto(Long.parseLong(String.valueOf(row.get("id_operador_contacto"))));
   			objeto.setCodigo_funcionario((String) row.get("codigo_funcionario"));
   			objeto.setId_funcionario(Long.parseLong(String.valueOf(row.get("id_funcionario"))));
   			objeto.setCodigo_operador((String) row.get("codigo_operador"));
   			objeto.setId_operador(Long.parseLong(String.valueOf(row.get("id_operador"))));
   			objeto.setCodigo_acn((String) row.get("codigo_acn"));
   			objeto.setId_acn(Long.parseLong(String.valueOf(row.get("id_acn"))));
   			objeto.setCargo_operador_contacto((String) row.get("cargo_operador_contacto"));
   			objeto.setFijo_operador_contacto((String) row.get("fijo_operador_contacto"));
   			objeto.setAnexo_operador_contacto((String) row.get("anexo_operador_contacto"));
   			objeto.setMovil_operador_contacto((String) row.get("movil_operador_contacto"));
   			objeto.setMovil2_operador_contacto((String) row.get("movil2_operador_contacto"));
   			objeto.setEmail_operador_contacto((String) row.get("email_operador_contacto"));
   			objeto.setEmail2_operador_contacto((String) row.get("email2_operador_contacto"));
   			objeto.setInicio_operador_contacto((Date) row.get("inicio_operador_contacto"));
			objeto.setFin_operador_contacto((Date) row.get("fin_operador_contacto"));
			objeto.setTipo_operador_contacto(Integer.parseInt(String.valueOf(row.get("tipo_operador_contacto"))));
   			objeto.setEstado_operador_contacto(Integer.parseInt(String.valueOf(row.get("estado_operador_contacto"))));
   			
   			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
   			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
   			objeto.setIp_creacion((String) row.get("ip_creacion"));
   			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
   			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
   			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
   			
   			operadores_contactos.add(objeto);
   		}
   		return operadores_contactos;
   	}

    @PostMapping("/registrar")
    public OperadorContacto createOperadorcontacto(@Valid @RequestBody OperadorContacto operador_contacto) {
    	Date fecha = new Date();
    	operador_contacto.setFecha_creacion(fecha);
        return operadorContactoRepositorio.save(operador_contacto);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<OperadorContacto> updateOperadorcontacto(@PathVariable(value = "id") Long id_operador_contacto,
         @Valid @RequestBody OperadorContacto operador_contacto_detalle) throws ResourceNotFoundException {
    	OperadorContacto operador_contacto = operadorContactoRepositorio.findById(id_operador_contacto)
        .orElseThrow(() -> new ResourceNotFoundException("Operador contacto no encontrado para id :: " + id_operador_contacto));

        operador_contacto.setCodigo_funcionario(operador_contacto_detalle.getCodigo_funcionario());
        operador_contacto.setCodigo_operador(operador_contacto_detalle.getCodigo_operador());
        operador_contacto.setCodigo_acn(operador_contacto_detalle.getCodigo_acn());
        operador_contacto.setEstado_operador_contacto(operador_contacto_detalle.getEstado_operador_contacto());
        
        operador_contacto.setCargo_operador_contacto(operador_contacto_detalle.getCargo_operador_contacto());
        operador_contacto.setFijo_operador_contacto(operador_contacto_detalle.getFijo_operador_contacto());
        operador_contacto.setAnexo_operador_contacto(operador_contacto_detalle.getAnexo_operador_contacto());
        operador_contacto.setMovil_operador_contacto(operador_contacto_detalle.getMovil_operador_contacto());
        operador_contacto.setMovil2_operador_contacto(operador_contacto_detalle.getMovil2_operador_contacto());
        operador_contacto.setEmail_operador_contacto(operador_contacto_detalle.getEmail_operador_contacto());
        operador_contacto.setEmail2_operador_contacto(operador_contacto_detalle.getEmail2_operador_contacto());
        operador_contacto.setInicio_operador_contacto(operador_contacto_detalle.getInicio_operador_contacto());
        operador_contacto.setFin_operador_contacto(operador_contacto_detalle.getFin_operador_contacto());

        Date fecha = new Date();
        operador_contacto.setId_usuario_creacion(operador_contacto_detalle.getId_usuario_creacion());
        operador_contacto.setFecha_creacion(operador_contacto_detalle.getFecha_creacion());
        operador_contacto.setIp_creacion(operador_contacto_detalle.getIp_creacion());
        operador_contacto.setId_usuario_modificacion(operador_contacto_detalle.getId_usuario_modificacion());
        if(operador_contacto_detalle.getId_operador_contacto()==0) {
        	operador_contacto.setFecha_modificacion(null);
        }
        else {
        	operador_contacto.setFecha_modificacion(fecha);
        }
        operador_contacto.setIp_modificacion(operador_contacto_detalle.getIp_modificacion());
     
        final OperadorContacto operador_contacto_actualizado = operadorContactoRepositorio.save(operador_contacto);
        return ResponseEntity.ok(operador_contacto_actualizado);
    }
    
}