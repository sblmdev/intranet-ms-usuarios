package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_DOCUMENTOS" )

public class Documento {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_documento;
	
	@Column(length = 20)
	private String codigo_documento;
	
	@Column(length = 20)
	private String codigo_referencia;
	
	@Column(length = 500)
	private String nombre_documento;
		
	private Date fecha_documento;
	
	@Column(length = 500)
	private String url_documento;
	
	@Column(length = 500)
	private String dir_documento;
	
	private int estado_documento;
	private int tipo_documento;
    
  //Auditoria de creación
    private long id_usuario_creacion;
    private Date fecha_creacion;
    
    @Column(length = 20)
    private String ip_creacion;
    
  //Auditoria de modificación
    private long id_usuario_modificacion;
    private Date fecha_modificacion;
    
    @Column(length = 20)
    private String ip_modificacion;
 
    public Documento(){
    }

	public long getId_documento() {
		return id_documento;
	}

	public void setId_documento(long id_documento) {
		this.id_documento = id_documento;
	}

	public String getCodigo_documento() {
		return codigo_documento;
	}

	public void setCodigo_documento(String codigo_documento) {
		this.codigo_documento = codigo_documento;
	}

	public String getNombre_documento() {
		return nombre_documento;
	}

	public void setNombre_documento(String nombre_documento) {
		this.nombre_documento = nombre_documento;
	}

	public String getUrl_documento() {
		return url_documento;
	}

	public void setUrl_documento(String url_documento) {
		this.url_documento = url_documento;
	}

	public String getDir_documento() {
		return dir_documento;
	}

	public void setDir_documento(String dir_documento) {
		this.dir_documento = dir_documento;
	}

	public int getEstado_documento() {
		return estado_documento;
	}

	public void setEstado_documento(int estado_documento) {
		this.estado_documento = estado_documento;
	}

	public int getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(int tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	public long getId_usuario_creacion() {
		return id_usuario_creacion;
	}

	public void setId_usuario_creacion(long id_usuario_creacion) {
		this.id_usuario_creacion = id_usuario_creacion;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public String getIp_creacion() {
		return ip_creacion;
	}

	public void setIp_creacion(String ip_creacion) {
		this.ip_creacion = ip_creacion;
	}

	public long getId_usuario_modificacion() {
		return id_usuario_modificacion;
	}

	public void setId_usuario_modificacion(long id_usuario_modificacion) {
		this.id_usuario_modificacion = id_usuario_modificacion;
	}

	public Date getFecha_modificacion() {
		return fecha_modificacion;
	}

	public void setFecha_modificacion(Date fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}

	public String getIp_modificacion() {
		return ip_modificacion;
	}

	public void setIp_modificacion(String ip_modificacion) {
		this.ip_modificacion = ip_modificacion;
	}

	public String getCodigo_referencia() {
		return codigo_referencia;
	}

	public void setCodigo_referencia(String codigo_referencia) {
		this.codigo_referencia = codigo_referencia;
	}

	public Date getFecha_documento() {
		return fecha_documento;
	}

	public void setFecha_documento(Date fecha_documento) {
		this.fecha_documento = fecha_documento;
	}
	
}