package com.dini.activoscriticosnacionalesbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

@Entity 
@Table ( name = " TA_ACN_OPEACNUBIGEOS " )

public class OperadorUbigeo {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id_ubigeo_operador;
	private String codigo_operador;
	private String codigo_acn;
	private long id_acn;
	private long id_operador;
	
	private String ubigeo_operador;
	private String centro_poblado_ubigeo_operador;
	private String altitud_ubigeo_operador;
    private int estado_ubigeo_operador;
        
  //Auditoria de creación
    private long id_usuario_creacion;
    private Date fecha_creacion;
    private String ip_creacion;
    
  //Auditoria de modificación
    private long id_usuario_modificacion;
    private Date fecha_modificacion;
    private String ip_modificacion;
    
    public OperadorUbigeo() {
    }
    
	public long getId_acn() {
		return id_acn;
	}

	public void setId_acn(long id_acn) {
		this.id_acn = id_acn;
	}

	public long getId_operador() {
		return id_operador;
	}

	public void setId_operador(long id_operador) {
		this.id_operador = id_operador;
	}

	public long getId_ubigeo_operador() {
		return id_ubigeo_operador;
	}

	public void setId_ubigeo_operador(long id_ubigeo_operador) {
		this.id_ubigeo_operador = id_ubigeo_operador;
	}

	public String getCodigo_operador() {
		return codigo_operador;
	}

	public void setCodigo_operador(String codigo_operador) {
		this.codigo_operador = codigo_operador;
	}

	public String getCodigo_acn() {
		return codigo_acn;
	}

	public void setCodigo_acn(String codigo_acn) {
		this.codigo_acn = codigo_acn;
	}

	public String getUbigeo_operador() {
		return ubigeo_operador;
	}

	public void setUbigeo_operador(String ubigeo_operador) {
		this.ubigeo_operador = ubigeo_operador;
	}

	public String getCentro_poblado_ubigeo_operador() {
		return centro_poblado_ubigeo_operador;
	}

	public void setCentro_poblado_ubigeo_operador(String centro_poblado_ubigeo_operador) {
		this.centro_poblado_ubigeo_operador = centro_poblado_ubigeo_operador;
	}

	public String getAltitud_ubigeo_operador() {
		return altitud_ubigeo_operador;
	}

	public void setAltitud_ubigeo_operador(String altitud_ubigeo_operador) {
		this.altitud_ubigeo_operador = altitud_ubigeo_operador;
	}

	public int getEstado_ubigeo_operador() {
		return estado_ubigeo_operador;
	}

	public void setEstado_ubigeo_operador(int estado_ubigeo_operador) {
		this.estado_ubigeo_operador = estado_ubigeo_operador;
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

}