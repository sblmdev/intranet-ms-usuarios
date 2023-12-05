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

import com.dini.activoscriticosnacionalesbackend.entidades.CapacidadNacional;
import com.dini.activoscriticosnacionalesbackend.entidades.ClasificacionAcn;
import com.dini.activoscriticosnacionalesbackend.entidades.Documento;
import com.dini.activoscriticosnacionalesbackend.entidades.Funcionario;
import com.dini.activoscriticosnacionalesbackend.repositorios.DocumentoRepositorio;

@RestController 
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
@RequestMapping("/documento")
public class DocumentoControlador {
	
	@Autowired
    private DocumentoRepositorio documentoRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Documento> getDocumentoById(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
        Documento documento = documentoRepositorio.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Documento no encontrado para id :: " + id));
        return ResponseEntity.ok().body(documento);
    }
    
    @PostMapping("/registrar")
    public Documento createCDocumento(@Valid @RequestBody Documento documento) {
    	Date fecha = new Date();
    	documento.setFecha_creacion(fecha);
        return documentoRepositorio.save(documento);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Documento> updateDocumento(@PathVariable(value = "id") long id,
         @Valid @RequestBody Documento documento_detalle) throws ResourceNotFoundException {
        Documento documento = documentoRepositorio.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Documento no encontrado para id :: " + id));

        documento.setCodigo_documento(documento_detalle.getCodigo_documento());
        documento.setCodigo_referencia(documento_detalle.getCodigo_referencia());
        documento.setNombre_documento(documento_detalle.getNombre_documento());
        documento.setFecha_documento(documento_detalle.getFecha_documento());
        documento.setUrl_documento(documento_detalle.getUrl_documento());
        documento.setDir_documento(documento_detalle.getDir_documento());
        documento.setTipo_documento(documento_detalle.getTipo_documento());
        documento.setEstado_documento(documento_detalle.getEstado_documento());
        
        Date fecha = new Date();
        documento.setId_usuario_creacion(documento_detalle.getId_usuario_creacion());
        documento.setFecha_creacion(documento_detalle.getFecha_creacion());
        documento.setIp_creacion(documento_detalle.getIp_creacion());
        documento.setId_usuario_modificacion(documento_detalle.getId_usuario_modificacion());
        if(documento_detalle.getId_documento()==0) {
        	documento.setFecha_modificacion(null);
        }
        else {
        	documento.setFecha_modificacion(fecha);
        }
        documento.setIp_modificacion(documento_detalle.getIp_modificacion());
        
        final Documento documento_actualizado = documentoRepositorio.save(documento);
        return ResponseEntity.ok(documento_actualizado);
    } 
    
    
    @GetMapping("/recuperarPorReferenciaYTipo/{codigo}/{tipo}")
	private List<Documento> documentosActivosPorReferenciaYTipo(@PathVariable(value = "codigo") String codigo, @PathVariable(value = "tipo") int tipo){
		String query = "SELECT * FROM ta_acn_documentos WHERE estado_documento = 1 AND tipo_documento="+tipo+" AND codigo_referencia='"+codigo+"' ORDER BY fecha_documento";
		List<Documento> documentos = new ArrayList<Documento>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
		
		for(Map row: rows) {
			Documento objeto = new Documento();
			
			objeto.setId_documento(Long.parseLong(String.valueOf(row.get("id_documento"))));
			objeto.setCodigo_documento((String) row.get("codigo_documento"));
			objeto.setCodigo_referencia((String) row.get("codigo_referencia"));
			objeto.setNombre_documento((String) row.get("nombre_documento"));
			objeto.setFecha_documento((Date) row.get("fecha_documento"));
			objeto.setUrl_documento((String) row.get("url_documento"));
			objeto.setDir_documento((String) row.get("dir_documento"));
			objeto.setEstado_documento(Integer.parseInt(String.valueOf(row.get("estado_documento"))));
			objeto.setTipo_documento(Integer.parseInt(String.valueOf(row.get("tipo_documento"))));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			objeto.setId_usuario_modificacion(Long.parseLong(String.valueOf(row.get("id_usuario_modificacion"))));
			objeto.setFecha_modificacion((Date) row.get("fecha_modificacion"));
			objeto.setIp_modificacion((String) row.get("ip_modificacion"));
			
			documentos.add(objeto);
		}
		return documentos;
	}
    
    @GetMapping("/recuperarPorReferenciaYTipoUnico/{codigo}/{tipo}")
	private Documento documentosActivosPorReferenciaYTipoUnico(@PathVariable(value = "codigo") String codigo, @PathVariable(value = "tipo") int tipo){
		
		try {
			String query = "SELECT * FROM ta_acn_documentos WHERE estado_documento = 1 AND tipo_documento="+tipo+" AND codigo_referencia='"+codigo+"' ORDER BY fecha_documento";
			Map<String, Object> rows = jdbcTemplate.queryForMap(query);
			
			Documento documento = new Documento();
			documento.setId_documento(Long.parseLong(String.valueOf(rows.get("id_documento"))));
			documento.setCodigo_documento((String) rows.get("codigo_documento"));
			documento.setCodigo_referencia((String) rows.get("codigo_referencia"));
			documento.setNombre_documento((String) rows.get("nombre_documento"));
			documento.setFecha_documento((Date) rows.get("fecha_documento"));
			documento.setUrl_documento((String) rows.get("url_documento"));
			documento.setDir_documento((String) rows.get("dir_documento"));
			documento.setEstado_documento(Integer.parseInt(String.valueOf(rows.get("estado_documento"))));
			documento.setTipo_documento(Integer.parseInt(String.valueOf(rows.get("tipo_documento"))));
			
			documento.setId_usuario_creacion(Integer.parseInt(String.valueOf(rows.get("id_usuario_creacion"))));
			documento.setFecha_creacion((Date)rows.get("fecha_creacion"));
			documento.setIp_creacion(String.valueOf(rows.get("ip_creacion")));
			documento.setId_usuario_modificacion(Integer.parseInt(String.valueOf(rows.get("id_usuario_modificacion"))));
			documento.setFecha_modificacion((Date)rows.get("fecha_modificacion"));
			documento.setIp_modificacion(String.valueOf(rows.get("ip_modificacion")));
			
			return documento;
		}catch(Exception e) {
			return null;
		}
	}
    
    @GetMapping(value = "/recuperarPorCodigo/{codigo}", produces = "application/json")
	private Documento getDocumentoByCodigo(@PathVariable String codigo)  {

		try {
			String sql = "SELECT * FROM ta_acn_documentos WHERE codigo_documento = '"+codigo+"'";
			Map<String, Object> rows = jdbcTemplate.queryForMap(sql);
			
			Documento documento = new Documento();
			documento.setId_documento(Long.parseLong(String.valueOf(rows.get("id_documento"))));
			documento.setCodigo_documento((String) rows.get("codigo_documento"));
			documento.setCodigo_referencia((String) rows.get("codigo_referencia"));
			documento.setNombre_documento((String) rows.get("nombre_documento"));
			documento.setFecha_documento((Date) rows.get("fecha_documento"));
			documento.setUrl_documento((String) rows.get("url_documento"));
			documento.setDir_documento((String) rows.get("dir_documento"));
			documento.setEstado_documento(Integer.parseInt(String.valueOf(rows.get("estado_documento"))));
			documento.setTipo_documento(Integer.parseInt(String.valueOf(rows.get("tipo_documento"))));
			
			documento.setId_usuario_creacion(Integer.parseInt(String.valueOf(rows.get("id_usuario_creacion"))));
			documento.setFecha_creacion((Date)rows.get("fecha_creacion"));
			documento.setIp_creacion(String.valueOf(rows.get("ip_creacion")));
			documento.setId_usuario_modificacion(Integer.parseInt(String.valueOf(rows.get("id_usuario_modificacion"))));
			documento.setFecha_modificacion((Date)rows.get("fecha_modificacion"));
			documento.setIp_modificacion(String.valueOf(rows.get("ip_modificacion")));
			
			return documento;
		}catch(Exception e) {
			return null;
		}
	}
    
}