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
import com.dini.activoscriticosnacionalesbackend.entidades.SectorFuncionario;
import com.dini.activoscriticosnacionalesbackend.repositorios.SectorFuncionarioRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/sectorFuncionario")
public class SectorFuncionarioControlador {
	
	@Autowired
    private SectorFuncionarioRepositorio sectorFuncionarioRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	 @GetMapping("/listar")
	 public List<SectorFuncionario> getAllSectoresFuncionarios() {
	    return sectorFuncionarioRepositorio.findAll();
	 }
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<SectorFuncionario> getSectorFuncionarioById(@PathVariable(value = "id") long id_sector_funcionario)
        throws ResourceNotFoundException {
    	SectorFuncionario sector_funcionario = sectorFuncionarioRepositorio.findById(id_sector_funcionario)
          .orElseThrow(() -> new ResourceNotFoundException("Sector Funcionario no encontrado para id :: " + id_sector_funcionario));
        return ResponseEntity.ok().body(sector_funcionario);
    }
    
       
    @GetMapping("/listarPorSector/{codigo}")
	private List<SectorFuncionario> sectoresFuncionariosPorSector(@PathVariable(value = "codigo") String codigo){
    	String sql = "SELECT * FROM ta_acn_secfuncionarios WHERE codigo_sector = '"+codigo+"'";
    	List<SectorFuncionario> sectores_funcionarios = new ArrayList<SectorFuncionario>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		for(Map row: rows) {
			SectorFuncionario objeto = new SectorFuncionario();
			
			objeto.setId_sector_funcionario(Long.parseLong(String.valueOf(row.get("id_sector_funcionario"))));
			objeto.setCodigo_funcionario((String) row.get("codigo_funcionario"));
			objeto.setCodigo_sector((String) row.get("codigo_sector"));
			objeto.setId_sector(Long.parseLong(String.valueOf(row.get("id_sector"))));
			objeto.setId_funcionario(Long.parseLong(String.valueOf(row.get("id_funcionario"))));
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
		return sectores_funcionarios;
	}
    
    @GetMapping("/listarActivosPorSector/{codigo}")
   	private List<SectorFuncionario> sectoresFuncionariosActivosPorSector(@PathVariable(value = "codigo") String codigo){
       	String sql = "SELECT * FROM ta_acn_secfuncionarios WHERE codigo_sector = '"+codigo+"' AND estado_sector_funcionario=1 order by codigo_funcionario";
       	List<SectorFuncionario> sectores_funcionarios = new ArrayList<SectorFuncionario>();
   		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
   		
   		for(Map row: rows) {
   			SectorFuncionario objeto = new SectorFuncionario();
   			
   			objeto.setId_sector_funcionario(Long.parseLong(String.valueOf(row.get("id_sector_funcionario"))));
   			objeto.setCodigo_funcionario((String) row.get("codigo_funcionario"));
   			objeto.setCodigo_sector((String) row.get("codigo_sector"));
			objeto.setId_sector(Long.parseLong(String.valueOf(row.get("id_sector"))));
			objeto.setId_funcionario(Long.parseLong(String.valueOf(row.get("id_funcionario"))));
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
   		return sectores_funcionarios;
   	}
    

    @PostMapping("/registrar")
    public SectorFuncionario createSectorFuncionario(@Valid @RequestBody SectorFuncionario sector_funcionario) {
    	Date fecha = new Date();
    	sector_funcionario.setFecha_creacion(fecha);
        return sectorFuncionarioRepositorio.save(sector_funcionario);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<SectorFuncionario> updateFuncionario(@PathVariable(value = "id") Long id_sector_funcionario,
         @Valid @RequestBody SectorFuncionario sector_funcionario_detalle) throws ResourceNotFoundException {
    	SectorFuncionario sector_funcionario = sectorFuncionarioRepositorio.findById(id_sector_funcionario)
        .orElseThrow(() -> new ResourceNotFoundException("Sector Funcionario no encontrado para id :: " + id_sector_funcionario));

        sector_funcionario.setCodigo_funcionario(sector_funcionario_detalle.getCodigo_funcionario());
        sector_funcionario.setCodigo_sector(sector_funcionario_detalle.getCodigo_sector());
        sector_funcionario.setId_funcionario(sector_funcionario_detalle.getId_funcionario());
        sector_funcionario.setId_sector(sector_funcionario_detalle.getId_sector());
        sector_funcionario.setEstado_sector_funcionario(sector_funcionario_detalle.getEstado_sector_funcionario());
        
        sector_funcionario.setCargo_sector_funcionario(sector_funcionario_detalle.getCargo_sector_funcionario());
        sector_funcionario.setFijo_sector_funcionario(sector_funcionario_detalle.getFijo_sector_funcionario());
        sector_funcionario.setAnexo_sector_funcionario(sector_funcionario_detalle.getAnexo_sector_funcionario());
        sector_funcionario.setMovil_sector_funcionario(sector_funcionario_detalle.getMovil_sector_funcionario());
        sector_funcionario.setMovil2_sector_funcionario(sector_funcionario_detalle.getMovil2_sector_funcionario());
        sector_funcionario.setEmail_sector_funcionario(sector_funcionario_detalle.getEmail_sector_funcionario());
        sector_funcionario.setEmail2_sector_funcionario(sector_funcionario_detalle.getEmail2_sector_funcionario());
        sector_funcionario.setInicio_sector_funcionario(sector_funcionario_detalle.getInicio_sector_funcionario());
        sector_funcionario.setFin_sector_funcionario(sector_funcionario_detalle.getFin_sector_funcionario());

        Date fecha = new Date();
        sector_funcionario.setId_usuario_creacion(sector_funcionario_detalle.getId_usuario_creacion());
        sector_funcionario.setFecha_creacion(sector_funcionario_detalle.getFecha_creacion());
        sector_funcionario.setIp_creacion(sector_funcionario_detalle.getIp_creacion());
        sector_funcionario.setId_usuario_modificacion(sector_funcionario_detalle.getId_usuario_modificacion());
        if(sector_funcionario_detalle.getId_sector_funcionario()==0) {
        	sector_funcionario.setFecha_modificacion(null);
        }
        else {
        	sector_funcionario.setFecha_modificacion(fecha);
        }
        sector_funcionario.setIp_modificacion(sector_funcionario_detalle.getIp_modificacion());
     
        final SectorFuncionario sector_funcionario_actualizado = sectorFuncionarioRepositorio.save(sector_funcionario);
        return ResponseEntity.ok(sector_funcionario_actualizado);
    }
    
}