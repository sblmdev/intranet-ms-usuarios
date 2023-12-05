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
import com.dini.activoscriticosnacionalesbackend.entidades.OperadorFuncionario;
import com.dini.activoscriticosnacionalesbackend.repositorios.OperadorFuncionarioRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/operadorFuncionario")
public class OperadorFuncionarioControlador {
	
	@Autowired
    private OperadorFuncionarioRepositorio operadorFuncionarioRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	 @GetMapping("/listar")
	 public List<OperadorFuncionario> getAllOperadorFuncionarios() {
	    return operadorFuncionarioRepositorio.findAll();
	 }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<OperadorFuncionario> getOperadorFuncionarioById(@PathVariable(value = "id") long id_operador_funcionario)
        throws ResourceNotFoundException {
    	OperadorFuncionario operador_funcionario = operadorFuncionarioRepositorio.findById(id_operador_funcionario)
          .orElseThrow(() -> new ResourceNotFoundException("Operador Funcionario no encontrado para id :: " + id_operador_funcionario));
        return ResponseEntity.ok().body(operador_funcionario);
    }
    
       
    @GetMapping("/listarPorOperador/{codigo}")
	private List<OperadorFuncionario> operadoresFuncionariosPorOperador(@PathVariable(value = "codigo") String codigo){
    	String sql = "SELECT * FROM ta_acn_opereplegales WHERE codigo_operador = '"+codigo+"'";
    	List<OperadorFuncionario> operadores_funcionarios = new ArrayList<OperadorFuncionario>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		for(Map row: rows) {
			OperadorFuncionario objeto = new OperadorFuncionario();
			
			objeto.setId_operador_funcionario(Long.parseLong(String.valueOf(row.get("id_operador_funcionario"))));
			objeto.setCodigo_funcionario((String) row.get("codigo_funcionario"));
			objeto.setCodigo_operador((String) row.get("codigo_operador"));
			objeto.setId_funcionario(Long.parseLong(String.valueOf(row.get("id_funcionario"))));
			objeto.setId_operador(Long.parseLong(String.valueOf(row.get("id_operador"))));
			objeto.setCargo_operador_funcionario((String) row.get("cargo_operador_funcionario"));
			objeto.setFijo_operador_funcionario((String) row.get("fijo_operador_funcionario"));
			objeto.setAnexo_operador_funcionario((String) row.get("anexo_operador_funcionario"));
			objeto.setMovil_operador_funcionario((String) row.get("movil_operador_funcionario"));
			objeto.setMovil2_operador_funcionario((String) row.get("movil2_operador_funcionario"));
			objeto.setEmail_operador_funcionario((String) row.get("email_operador_funcionario"));
			objeto.setEmail2_operador_funcionario((String) row.get("email2_operador_funcionario"));
			objeto.setInicio_operador_funcionario((Date) row.get("inicio_operador_funcionario"));
			objeto.setFin_operador_funcionario((Date) row.get("fin_operador_funcionario"));
			objeto.setEstado_operador_funcionario(Integer.parseInt(String.valueOf(row.get("estado_operador_funcionario"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			operadores_funcionarios.add(objeto);
		}
		return operadores_funcionarios;
	}
    
    @GetMapping("/listarActivosPorOperador/{codigo}")
   	private List<OperadorFuncionario> operadoresFuncionariosActivosPorOperador(@PathVariable(value = "codigo") String codigo){
       	String sql = "SELECT * FROM ta_acn_opereplegales WHERE codigo_operador = '"+codigo+"' AND estado_operador_funcionario=1 order by codigo_funcionario";
       	List<OperadorFuncionario> operadores_funcionarios = new ArrayList<OperadorFuncionario>();
   		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
   		
   		for(Map row: rows) {
   			OperadorFuncionario objeto = new OperadorFuncionario();
   			
   			objeto.setId_operador_funcionario(Long.parseLong(String.valueOf(row.get("id_operador_funcionario"))));
   			objeto.setCodigo_funcionario((String) row.get("codigo_funcionario"));
   			objeto.setCodigo_operador((String) row.get("codigo_operador"));
   			objeto.setId_funcionario(Long.parseLong(String.valueOf(row.get("id_funcionario"))));
			objeto.setId_operador(Long.parseLong(String.valueOf(row.get("id_operador"))));
   			objeto.setCargo_operador_funcionario((String) row.get("cargo_operador_funcionario"));
   			objeto.setFijo_operador_funcionario((String) row.get("fijo_operador_funcionario"));
   			objeto.setAnexo_operador_funcionario((String) row.get("anexo_operador_funcionario"));
   			objeto.setMovil_operador_funcionario((String) row.get("movil_operador_funcionario"));
   			objeto.setMovil2_operador_funcionario((String) row.get("movil2_operador_funcionario"));
   			objeto.setEmail_operador_funcionario((String) row.get("email_operador_funcionario"));
   			objeto.setEmail2_operador_funcionario((String) row.get("email2_operador_funcionario"));
   			objeto.setInicio_operador_funcionario((Date) row.get("inicio_operador_funcionario"));
			objeto.setFin_operador_funcionario((Date) row.get("fin_operador_funcionario"));
   			objeto.setEstado_operador_funcionario(Integer.parseInt(String.valueOf(row.get("estado_operador_funcionario"))));
   			
   			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
   			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
   			objeto.setIp_creacion((String) row.get("ip_creacion"));
   			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
   			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
   			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
   			
   			operadores_funcionarios.add(objeto);
   		}
   		return operadores_funcionarios;
   	}
    

    @PostMapping("/registrar")
    public OperadorFuncionario createOperadorFuncionario(@Valid @RequestBody OperadorFuncionario operador_funcionario) {
    	Date fecha = new Date();
    	operador_funcionario.setFecha_creacion(fecha);
        return operadorFuncionarioRepositorio.save(operador_funcionario);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<OperadorFuncionario> updateOperadorFuncionario(@PathVariable(value = "id") Long id_operador_funcionario,
         @Valid @RequestBody OperadorFuncionario operador_funcionario_detalle) throws ResourceNotFoundException {
    	OperadorFuncionario operador_funcionario = operadorFuncionarioRepositorio.findById(id_operador_funcionario)
        .orElseThrow(() -> new ResourceNotFoundException("Operador Funcionario no encontrado para id :: " + id_operador_funcionario));

        operador_funcionario.setCodigo_funcionario(operador_funcionario_detalle.getCodigo_funcionario());
        operador_funcionario.setCodigo_operador(operador_funcionario_detalle.getCodigo_operador());
        operador_funcionario.setId_funcionario(operador_funcionario_detalle.getId_funcionario());
        operador_funcionario.setId_operador(operador_funcionario_detalle.getId_operador());
        operador_funcionario.setEstado_operador_funcionario(operador_funcionario_detalle.getEstado_operador_funcionario());
        
        operador_funcionario.setCargo_operador_funcionario(operador_funcionario_detalle.getCargo_operador_funcionario());
        operador_funcionario.setFijo_operador_funcionario(operador_funcionario_detalle.getFijo_operador_funcionario());
        operador_funcionario.setAnexo_operador_funcionario(operador_funcionario_detalle.getAnexo_operador_funcionario());
        operador_funcionario.setMovil_operador_funcionario(operador_funcionario_detalle.getMovil_operador_funcionario());
        operador_funcionario.setMovil2_operador_funcionario(operador_funcionario_detalle.getMovil2_operador_funcionario());
        operador_funcionario.setEmail_operador_funcionario(operador_funcionario_detalle.getEmail_operador_funcionario());
        operador_funcionario.setEmail2_operador_funcionario(operador_funcionario_detalle.getEmail2_operador_funcionario());
        operador_funcionario.setInicio_operador_funcionario(operador_funcionario_detalle.getInicio_operador_funcionario());
        operador_funcionario.setFin_operador_funcionario(operador_funcionario_detalle.getFin_operador_funcionario());

        Date fecha = new Date();
        operador_funcionario.setId_usuario_creacion(operador_funcionario_detalle.getId_usuario_creacion());
        operador_funcionario.setFecha_creacion(operador_funcionario_detalle.getFecha_creacion());
        operador_funcionario.setIp_creacion(operador_funcionario_detalle.getIp_creacion());
        operador_funcionario.setId_usuario_modificacion(operador_funcionario_detalle.getId_usuario_modificacion());
        if(operador_funcionario_detalle.getId_operador_funcionario()==0) {
        	operador_funcionario.setFecha_modificacion(null);
        }
        else {
        	operador_funcionario.setFecha_modificacion(fecha);
        }
        operador_funcionario.setIp_modificacion(operador_funcionario_detalle.getIp_modificacion());
     
        final OperadorFuncionario operador_funcionario_actualizado = operadorFuncionarioRepositorio.save(operador_funcionario);
        return ResponseEntity.ok(operador_funcionario_actualizado);
    }
    
}